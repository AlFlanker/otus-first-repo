package com.gmail.alexflanker89.lesson.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class Localization {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
