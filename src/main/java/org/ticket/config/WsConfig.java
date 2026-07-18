package org.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WsConfig implements WsConfigurer {
    @Bean
    public XsdSchema ticketSchema() {
        return new SimpleXsdSchema(new ClassPathResource("tickets.xsd"));
    }

    @Bean(name = "tickets")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema ticketSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("TicketPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://ticket.org/ws");
        wsdl.setSchema(ticketSchema);
        return wsdl;
    }
}