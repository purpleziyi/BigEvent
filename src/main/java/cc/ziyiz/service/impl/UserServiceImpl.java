package cc.ziyiz.service.impl;

import cc.ziyiz.mapper.UserMapper;
import cc.ziyiz.pojo.User;
import cc.ziyiz.service.UserService;
import cc.ziyiz.utils.Md5Util;

import cc.ziyiz.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUserName(String username) {
        // call method in Mapper-layer with SQL
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // Encryption processing -- MD5
        // Put the plain text password as argument and return an encrypted string
        String md5String = Md5Util.getMD5String(password);

        // add new username and encrypted string
        userMapper.add(username,md5String);

    }

    @Override
//    @Transactional
    public void update(User user) {
        // need to update the updateTime-attribute
        user.setUpdateTime(LocalDateTime.now());
        // call method in Mapper
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();  // get user-info through ThreadLocal
        Integer id = (Integer) map.get("id");   // find userId from the use-info
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        // must update password based on userId
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
