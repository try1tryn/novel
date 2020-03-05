package com.rong.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;


/**
 * @description: session监听器
 * @author: QR
 * @create: 2020-01-01 22:08
 **/
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    public static final Logger logger= LoggerFactory.getLogger(SessionListener.class);

    /**
     * 在session中添加对象时触发此操作 调用setAttribute触发
     */
    @Override
    public void  attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        logger.info("--session添加属性--");
//        HttpSession session=httpSessionBindingEvent.getSession();
//        logger.info("key----:"+httpSessionBindingEvent.getName());
//        logger.info("value---:"+httpSessionBindingEvent.getValue());

    }

    /** 修改、删除session中添加对象时触发此操作  调用 removeAttribute触发 **/
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        logger.info("--正在修改、删除session属性--");
    }

    /** 在Session属性被重新设置时 **/
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        logger.info("--Session属性被重新设置--");
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info("---Session创建----");
//        HttpSession session = event.getSession();
//        ServletContext application = session.getServletContext();
//        // 在application范围由一个HashSet集保存所有的session
//        HashSet sessions = (HashSet) application.getAttribute("sessions");
//        if (sessions == null) {
//            sessions = new HashSet();
//            application.setAttribute("sessions", sessions);
//        }
//        // 新创建的session均添加到HashSet集中
//        sessions.add(session);
//        // 可以在别处从application范围中取出sessions集合
//        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {
        logger.info("---Session销毁----");
//        HttpSession session = event.getSession();
//        logger.info("deletedSessionId: "+session.getId());
//        System.out.println(session.getCreationTime());
//        System.out.println(session.getLastAccessedTime());
//        ServletContext application = session.getServletContext();
//        HashSet sessions = (HashSet) application.getAttribute("sessions");
//        // 销毁的session均从HashSet集中移除
//        sessions.remove(session);
    }

}
