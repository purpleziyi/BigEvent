package cc.ziyiz.interceptors;

import cc.ziyiz.pojo.Result;
import cc.ziyiz.utils.JwtUtil;
import cc.ziyiz.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component  // Inject the current interceptor-object into the LoginInterceptor-container
public class LoginInterceptor  implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override  // Intercept within this method
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // verify token
        // get token through the request-object
        String token = request.getHeader("Authorization");

        // parse token (the logic is the same as the code in ArticleController
        try {
            // get the same token from Redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if(redisToken == null){
                // token has been expired
                throw new RuntimeException();
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);  // get business data

            // store the business data into ThreadLocal
            ThreadLocalUtil.set(claims);

            // allow the data
            return true;
        } catch (Exception e) {
            // fail in logging, HTTP-status code = 401
            response.setStatus(401);
            // intercept the data
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // clear the data in the ThreadLocal
        ThreadLocalUtil.remove();
    }
}
