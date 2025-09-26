package top.ittqy.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ittqy.filter_interceptor.filter.RateLimitFilter;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 */
@Configuration
@AllArgsConstructor
public class FilterConfig {
    // private final MyFilter myFilter;
    // private final YourFilter yourFilter;
    private final RateLimitFilter rateLimitFilter;

    // @Bean
    // public FilterRegistrationBean<MyFilter> MyFilterRegistrationBean() {
    //     FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
    //     registrationBean.setFilter(myFilter);
    //     registrationBean.setOrder(3);
    //     registrationBean.addUrlPatterns("/api/test");
    //     return registrationBean;
    // }
    //
    // @Bean
    // public FilterRegistrationBean<YourFilter> YourFilterRegistrationBean() {
    //     FilterRegistrationBean<YourFilter> registrationBean = new FilterRegistrationBean<>();
    //     registrationBean.setFilter(yourFilter);
    //     registrationBean.setOrder(1);
    //     registrationBean.addUrlPatterns("/api/test");
    //     return registrationBean;
    // }
    @Bean
    public FilterRegistrationBean<RateLimitFilter> RateLimitFilterRegistrationBean() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rateLimitFilter);
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/api/pay/*");
        return registrationBean;
    }
}
