package cc.ziyiz.service;

import cc.ziyiz.pojo.User;

public interface UserService {
    // find users based on username
    User findByUserName(String username);

    // register
    void register(String username, String password);

    // update
    void update(User user);

    // update profile-picture
    void updateAvatar(String avatarUrl);

    // update password
    void updatePwd(String newPwd);
}
