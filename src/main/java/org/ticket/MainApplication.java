package org.ticket;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import java.io.File;

public class MainApplication {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();

        springContext.scan("org.ticket");

        MessageDispatcherServlet servlet = new MessageDispatcherServlet(springContext);
        servlet.setTransformWsdlLocations(true);

        Tomcat.addServlet(context, "spring-ws", servlet);
        context.addServletMappingDecoded("/ws/*", "spring-ws");

        System.out.println("Starting Embedded Tomcat on port 8080...");
        tomcat.start();
        tomcat.getServer().await();
    }
}
