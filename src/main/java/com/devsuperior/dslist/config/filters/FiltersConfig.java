package com.devsuperior.dslist.config.filters;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Desabilitando temporiaramente!!! Dando erro!!! Testar o motivo pelo qual tá quebrando a aplicação em outro momento
//@Configuration
public class FiltersConfig {

    private static final String URL_FILTER = "/games/*";

    @Bean
    public FilterRegistrationBean<ValidationHeaderFilter> validationHeaderFilter() {

        FilterRegistrationBean<ValidationHeaderFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new ValidationHeaderFilter());
        filterFilterRegistrationBean.addUrlPatterns(URL_FILTER);
        filterFilterRegistrationBean.setOrder(1);

        return filterFilterRegistrationBean;
    }
}
