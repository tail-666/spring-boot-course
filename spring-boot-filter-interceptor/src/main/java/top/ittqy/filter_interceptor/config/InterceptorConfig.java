package top.ittqy.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ittqy.filter_interceptor.interceptor.BusinessLogInterceptor;
import top.ittqy.filter_interceptor.interceptor.TimeStatInterceptor;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 * @Description: 拦截器配置
 */

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    // private final MyInterceptor myInterceptor;
    // private final YourInterceptor yourInterceptor;
    //
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(myInterceptor).addPathPatterns("/api/test");
    //     registry.addInterceptor(yourInterceptor).addPathPatterns("/api/test");
    // }

    private final TimeStatInterceptor timeStatInterceptor;
    private final BusinessLogInterceptor businessLogInterceptor;
    // private final ParamValidateInterceptor paramValidateInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册耗时统计拦截器
        registry.addInterceptor(timeStatInterceptor)
                // 拦截所有路径
                .addPathPatterns("/**")
                // 排除静态资源和错误页
                .excludePathPatterns("/static/**", "/error")
                // 执行顺序（值越小越先执行）
                .order(1);
        // 注册业务日志拦截器
        registry.addInterceptor(businessLogInterceptor)
                .addPathPatterns("/**")
                // 晚于 TimeStatInterceptor 执行
                .order(2);


        // // 注册参数校验拦截器
        // registry.addInterceptor(paramValidateInterceptor)
        //         .addPathPatterns("/api/*")
        //         // 优先于权限拦截器
        //         .order(0);
    }

}
