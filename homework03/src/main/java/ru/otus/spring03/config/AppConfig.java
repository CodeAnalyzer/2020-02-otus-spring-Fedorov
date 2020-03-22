package ru.otus.spring03.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import ru.otus.spring03.dao.QuestionDaoSimple;
import ru.otus.spring03.config.AppSettings;

@Configuration
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/i18n/bundle");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    QuestionDaoSimple questionDaoSimple(AppSettings settings) {
        return new QuestionDaoSimple(settings.fileName, settings.locale);
    }

}
