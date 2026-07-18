package org.ticket.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "org.ticket")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:application-dev.properties")
public class AppConfig {

}
