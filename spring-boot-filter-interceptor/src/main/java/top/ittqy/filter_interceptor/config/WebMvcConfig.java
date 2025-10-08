package top.ittqy.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ittqy.filter_interceptor.interceptor.BusinessLogInterceptor;
import top.ittqy.filter_interceptor.interceptor.RoleAuthInterceptor;
import top.ittqy.filter_interceptor.interceptor.TimeStatInterceptor;

/**
 * @Author: 27258
 * @Date: 2025/10/8
 * @Description: 拦截器+跨域配置
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final TimeStatInterceptor timeStatInterceptor;
    private final BusinessLogInterceptor businessLogInterceptor;
    private final RoleAuthInterceptor roleAuthInterceptor;

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册耗时统计拦截器
        // registry.addInterceptor(timeStatInterceptor)
        //         // 拦截所有路径
        //         .addPathPatterns("/**")
        //         // 排除静态资源和错误页
        //         .excludePathPatterns("/static/**", "/error")
        //         // 执行顺序（值越小越先执行）
        //         .order(1);
        // // 注册业务日志拦截器
        // registry.addInterceptor(businessLogInterceptor).addPathPatterns("/**")
        //         // 晚于 TimeStatInterceptor 执行
        //         .order(2);
        // 注册权限拦截器
        registry.addInterceptor(roleAuthInterceptor).addPathPatterns("/api/**")
                // 注册登录接口不拦截
                .excludePathPatterns("/user/login", "/user/register")
                // 晚于 TimeStatInterceptor 执行
                .order(3);
    }
    /**
     * 跨域配置
     */
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("http://localhost:5173")
    //             .allowedMethods("GET", "POST", "PUT", "DELETE")
    //             .allowedHeaders("*")
    //             .allowCredentials(true)
    //             .maxAge(3600);
    // }
}