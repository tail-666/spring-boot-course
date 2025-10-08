package top.ittqy.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Author: 27258
 * @Date: 2025/9/26
 */
// @Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("MyInterceptor preHandle: {}",requestURI);
        LocalDateTime beginTime= LocalDateTime.now();
        request.setAttribute("beginTime",beginTime);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LocalDateTime beginTime =(LocalDateTime) request.getAttribute("beginTime");
        LocalDateTime endTime = LocalDateTime.now();

        //计算请求时间
        Duration duration = Duration.between(beginTime, endTime);
        long millis = duration.toMillis();
        String requestURI = request.getRequestURI();
        log.info("MyInterceptor 执行结束: {} 耗时: {}",requestURI,millis);
    }
}
