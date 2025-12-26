package shop;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import shop.configuration.AppConfig;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool(50, 5, 120_000);
        Server server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        // Spring context
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(AppConfig.class);

        // DispatcherServlet
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        context.addServlet(servletHolder, "/");

        // DelegatingFilterProxy для Spring Security
        FilterHolder securityFilter = new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain"));
        context.addFilter(securityFilter, "/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(context);

        server.start();
        server.join();
    }
}
