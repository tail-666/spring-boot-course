package top.ittqy.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ittqy.filter_interceptor.filter.CorsFilter;
import top.ittqy.filter_interceptor.filter.LogFilter;
import top.ittqy.filter_interceptor.filter.RateLimitFilter;

import java.util.ArrayList;
import java.util.List;

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
    private final LogFilter logFilter;
    private final CorsFilter corsFilter;
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

    /**
     * 注册限流过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<RateLimitFilter> RateLimitFilterRegistrationBean() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rateLimitFilter);
        // 晚于日志过滤器
        registrationBean.setOrder(3);
        // 仅对/api/pay/*路径进行限流
        registrationBean.addUrlPatterns("/api/pay/*");
        return registrationBean;
    }

    /**
     * 注册日志过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<LogFilter> logFilterFilterRegistrationBean() {
        FilterRegistrationBean<LogFilter> registration = new FilterRegistrationBean<>();
        // 设置过滤器实例
        registration.setFilter(logFilter);
        // 设置拦截路径（/*匹配所有请求）
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registration.setUrlPatterns(urlPatterns);
        // 设置执行顺序（值越小越先执行）
        registration.setOrder(2);
        // 排除静态资源（可选）
        registration.addInitParameter("exclusions", "*.js,*.css,*.png");
        return registration;
    }

    /**
     * 注册跨域过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter);
        registration.setUrlPatterns(List.of("/*"));
        // 最高优先级，确保跨域处理最先执行
        registration.setOrder(0);
        return registration;
    }
}
