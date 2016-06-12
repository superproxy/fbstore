package faststore.configserver.admin.util;

import faststore.configserver.api.group.GroupService;
import faststore.configserver.service.group.GroupServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringInitByWeb implements ServletContextListener {
    private static WebApplicationContext springContext;

    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

        StaticApplicationContext innerContext = new StaticApplicationContext(springContext);
        innerContext.registerSingleton(GroupService.class.getName(), GroupServiceImpl.class);

    }


    public void contextDestroyed(ServletContextEvent event) {
    }

    public static ApplicationContext getApplicationContext() {
        return springContext;
    }


}
