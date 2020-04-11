package com.rong.controller;

import com.rong.common.Constants;
import com.rong.common.RedisUtils;
import com.rong.common.Result;
import com.rong.common.StatusCode;
import com.rong.entity.Book;
import com.rong.entity.Chapter;
import com.rong.entity.Collect;
import com.rong.entity.User;
import com.rong.service.BookService;
import com.rong.service.CollectService;
import com.rong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;
import java.util.UUID;

/**
 * @Description: 小说
 * @Author: QR
 * @Date: 2020-01-07 17:14
 **/
@RequestMapping("/book")
@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private RedisUtils redisUtils;


    public BookController(RedisUtils redisUtils, BookService bookService, UserService userService, CollectService collectService, RedisTemplate<Object, Object> redisTemplate) {
        this.bookService = bookService;
        this.userService = userService;
        this.collectService = collectService;
        this.redisTemplate = redisTemplate;
        this.redisUtils = redisUtils;
    }

    /**
     * 根据小说名字 获取小说的书名和特定的章节名进行查看
     *
     * @param chapter 小说章节信息
     * @return 小说章节内容
     */
    @RequestMapping("/findBookContent")
    public Result<String> findBookContent(@RequestBody Chapter chapter) throws Exception {

        StringBuilder result = new StringBuilder();

        Chapter chapterMsg = bookService.findChapterPath(chapter);

        // 获取文件流
        BufferedReader reader = new BufferedReader(new FileReader(new File(chapterMsg.getChapterPath())));
        String line = reader.readLine();

        while (line != null) {
            result.append(line);
            line = reader.readLine();
        }

        return new Result<String>(true, StatusCode.OK, null, result.toString());

    }

    /**
     *  删除小说
     * @param book
     */
    @RequestMapping("/deleteBook")
    public void deleteBook(@RequestBody Book book){
      bookService.deleteBook(book);
    }

    /**
     * 收藏书籍
     *
     * @param book 小说信息
     * @return null
     */
    @RequestMapping("/collectBook")
    public Result<String> collectBook(@RequestBody Book book) throws Exception {

        // 获取缓存里面存放的user信息
        User user = (User) redisTemplate.boundValueOps(Objects.requireNonNull(redisTemplate.boundValueOps(Constants.AUTHORIZATION).get())).get();
        User userMsg = userService.findUserName(user);
        // 存放收藏信息
        Collect collect = new Collect();
        collect.setBookId(book.getId());
        collect.setUserId(userMsg.getId());
        // 查询该用户是否收藏过该书籍
        Collect collectResult = collectService.findCollectResult(collect);
        if (collectResult == null) {
            collect.setBookName(book.getBookName());
            collect.setId(UUID.randomUUID().toString().replace("-", ""));
            collect.setUserName(userMsg.getUserName());
            collect.setCollectStatus(Constants.COLLECT_STATUS_Y);
            collectService.saveCollect(collect);
            return new Result<>(true, StatusCode.OK, "收藏成功");
        } else if (Constants.COLLECT_STATUS_N.equals(collectResult.getCollectStatus())) {
            // 代表重新收藏
            collectResult.setCollectStatus(Constants.COLLECT_STATUS_Y);
            collectService.updateCollectStatus(collectResult);
            return new Result<>(true, StatusCode.OK, "重新收藏成功");
        }

        return new Result<>(false, StatusCode.REPERROR, "该书籍已经收藏");
    }

    /**
     * 取消收藏
     *
     * @param book 小说信息
     * @return 取消收藏的状态
     */
    @RequestMapping("/cancelCollect")
    public Result<String> cancelCollect(HttpServletRequest request, HttpServletResponse response, @RequestBody Book book) {

        // 获取缓存里面存放的user信息
        User user = (User) redisTemplate.boundValueOps(Objects.requireNonNull(redisTemplate.boundValueOps(Constants.AUTHORIZATION).get())).get();
        User userMsg = userService.findUserName(user);
        // 存放收藏信息
        Collect collect = new Collect();
        collect.setBookId(book.getId());
        collect.setUserId(userMsg.getId());
        // 查询用 收藏书籍的信息
        Collect collectResult = collectService.findCollectResult(collect);
        // 修改收藏状态
        collectResult.setCollectStatus(Constants.COLLECT_STATUS_N);
        collectService.updateCollectStatus(collectResult);

        return new Result<>(true, StatusCode.OK, "取消收藏成功");

    }


    /**
     * 停留页面15s 请求增加一次阅读量
     * 若从缓存里取的数据小于数据库已存的阅读量 则直接将数据库的阅读量加1
     *
     * @param book 小说信息
     * @return 阅读数量
     */
    @RequestMapping("/getReadCount")
    public Result<Integer> getReadCount(@RequestBody Book book) {
        // 获取小说当前的阅读量
        Book readBook = bookService.findReadCount(book);
        // 避免脏数据
        int incr = readBook.getReadCount();
        // 同步代码块
        synchronized (BookController.class){
            incr ++;
        }
        book.setReadCount(incr);
        bookService.updateReadCount(book);
        return new Result<Integer>(true, StatusCode.OK, "阅读量增加成功", incr);
    }

    /**
     * 查看小说详情页时 给小说的阅读量在缓存中持久化
     *
     * @param book 小说主键
     * @return 小说详情
     */
    @RequestMapping("/findBookDetail")
    public Result<Book> findBookDetail(@RequestBody Book book) {
        Book bookDetail = bookService.findBookDetail(book);
        return new Result<>(true, StatusCode.OK, "查询小说详情成功", bookDetail);
    }

    @RequestMapping("/saveBook")
    public Result saveBook(@RequestBody Book book){
        bookService.saveBook(book);
        return new Result<>(true, StatusCode.OK, "保存成功");
    }

}
