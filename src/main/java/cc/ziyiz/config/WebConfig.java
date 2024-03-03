package cc.ziyiz.config;

import cc.ziyiz.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // get the interceptor-object from LoginInterceptor-container
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override  // register the interceptor we created
    public void addInterceptors(InterceptorRegistry registry) {

        // NB! login-interface and register-interface are not intercepted
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
    }
}

