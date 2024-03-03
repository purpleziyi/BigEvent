package cc.ziyiz.controller;

import cc.ziyiz.pojo.Result;
import cc.ziyiz.pojo.User;
import cc.ziyiz.service.UserService;
import cc.ziyiz.utils.JwtUtil;
import cc.ziyiz.utils.Md5Util;
import cc.ziyiz.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register( String username, String password){

        if(username != null && username.length()>= 5 && username.length()<= 16 &&
          password != null && password.length() >= 5 && password.length() <= 16){
            // find users (with Service-method)
            User u = userService.findByUserName(username);
            if( u == null ){
                // username is available, can be registered
                userService.register(username,password);
                return Result.success();
            } else {
                // username is unavailable
                return Result.error("This username has been taken!");

            }
        }else{
            return Result.error("Input is illegal!");
        }

    }

    @PostMapping("/login")
    public Result<String> login( String username, String password){

        if(username != null && username.length()>= 5 && username.length()<= 16 &&
           password != null && password.length() >= 5 && password.length() <= 16){

            // find users (with Service-method)
            User loginUser = userService.findByUserName(username);

            // check whether the user exists
            if(loginUser == null) {
                return Result.error("Wrong username");
            }

            // Check whether the password is correct
            if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
                // successfully login
                Map<String,Object> claims = new HashMap<>();
                claims.put("id",loginUser.getId());
                claims.put("username",loginUser.getUsername());
                String token = JwtUtil.genToken(claims);
                // store token into redis
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                operations.set(token,token,1, TimeUnit.HOURS);

                return Result.success(token);
            }

            return Result.error("Wrong password");

        }else{
            return Result.error("Input is illegal!");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo( /*@RequestHeader(name = "Authorization") String token*/ ) {

//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");

        // introduce ThreadLocal for reducing parameters passed & sharing data in the same thread
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody   User user) {
        userService.update(user);
        return Result.success();
    }

    // PatchMapping : Update one part of resources
    @PatchMapping("updateAvatar")  // ensure the argument is an legal URL
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token){
        // 1. Check parameters manually
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("Missing required parameters");
        }

        // when all parameters are successfully passed in
        // check original password: get original password through userService by userName, and then compare it with old_pwd
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("The original password is incorrect!");
        }

        // when original password = old_pwd
        // check if newPwd and rePwd are the same
        if (!rePwd.equals(newPwd)){
            return Result.error("The password is different from the new password you just set");
        }

        // 2. Call service to complete password update
        userService.updatePwd(newPwd);

        // get token from the value of RequestHeader"Authorization"
        // delete the corresponding token in Redis
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();

    }

}
