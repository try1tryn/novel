package com.rong.controller;

import com.rong.entity.Book;
import com.rong.entity.Chapter;
import com.rong.mapper.BookMapper;
import com.rong.mapper.ChapterMapper;
import com.rong.service.BookService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;



/**
 * @Description: 小说爬虫
 * @Author: QR
 * @Date: 2020-01-07 17:14
 **/
@RestController
@RequestMapping("/jsoupData")
public class JsoupController {

    @Autowired
    private BookService bookService;

    /**
     * 获取小说内容 存入数据库
     * @throws Exception
     */
    @RequestMapping("/getMsg")
    public void getMsg() throws Exception {
        String path = "https://www.ttshu.com/";
        Document document = Jsoup.connect(path).get();
        // 获取li元素对象集合
        Elements liElements = document.select(".i_ft_1");
        for (Element liElement : liElements) {
            Book book = new Book();
            book.setReadCount(0);
            book.setId(UUID.randomUUID().toString().replace("-", ""));
            book.setBookImg("https://www.ttshu.com/" + liElement.select("img").attr("src"));
            book.setBookName(liElement.select("a").eq(1).text());
            book.setAuthor(liElement.select("div").eq(2).text().split(" ")[1]);
            book.setIntroduction(liElement.select("div").eq(2).text().split(" ")[3]);
            book.setBookStatus("0");
            // 获取小说详情 转存储为文本 将路径储存在数据库
            String href = "https://www.ttshu.com/" + liElement.select("a").eq(0).attr("href").replace("book", "content");
            Document hrefDocument = Jsoup.connect(href).get();
            // 小说详情
            Elements bookDetail = hrefDocument.select("table").eq(21).select("td");
            String localPath = null;
            int i = 0;
            for (Element element : bookDetail) {

                Chapter chapter = new Chapter();
                chapter.setId(UUID.randomUUID().toString().replace("-", ""));
                chapter.setBookId(book.getId());
                chapter.setBookName(book.getBookName());

                // 每本小说 暂时只缓存10个章节
                if (i == 10) {
                    break;
                }
                i++;
                // 章节网址
                String detail = "https://www.ttshu.com" + element.select("a").attr("href");
                Document parse = Jsoup.parse(new URL(detail).openStream(), "GB2312", detail);
                String httpStr = parse.select("script").eq(9).attr("src");

                URL url = new URL("https://www.ttshu.com" + httpStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //默认就是Get，可以采用post，大小写都行
                connection.setRequestMethod("GET");
                //是否允许缓存，默认true。
                connection.setUseCaches(Boolean.FALSE);
                //设置请求头信息
                connection.addRequestProperty("Connection", "close");
                //设置连接主机超时（单位：毫秒）
                connection.setConnectTimeout(8000);
                //设置从主机读取数据超时（单位：毫秒）
                connection.setReadTimeout(8000);

                // 小说章节内容
                String content = Jsoup.parse(connection.getInputStream(), "GB2312", "https://www.ttshu.com" + httpStr).body().text();

                // 转存为文件 存储在本地
                byte[] bytes = content.getBytes();

                // 文件名
                String fileName = element.text();

                // 存储路径
                localPath = "E:\\javaStudy\\file" + "\\" + book.getBookName();

                File dir = new File(localPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File file = new File(localPath + File.separator + fileName + ".txt");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(bytes);
                // 关闭文件流
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                chapter.setChapterPath(localPath + File.separator + fileName + ".txt");
                chapter.setChapter(fileName);
                bookService.saveChapter(chapter);
            }
            book.setBookPath(localPath);
            bookService.saveBook(book);
        }
    }

}
