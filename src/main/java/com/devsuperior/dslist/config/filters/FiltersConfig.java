package com.devsuperior.dslist.config.filters;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    private static final String URL_FILTER = "/games/*";
    private static final String URL_FILTER_VAR2 = "/picpay/*";

    @Bean
    public FilterRegistrationBean<ValidationHeaderFilter> validationHeaderFilter() {

        FilterRegistrationBean<ValidationHeaderFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new ValidationHeaderFilter());
        filterFilterRegistrationBean.addUrlPatterns(URL_FILTER);
        filterFilterRegistrationBean.addUrlPatterns(URL_FILTER_VAR2);
        filterFilterRegistrationBean.setOrder(1);

        return filterFilterRegistrationBean;
    }
}
