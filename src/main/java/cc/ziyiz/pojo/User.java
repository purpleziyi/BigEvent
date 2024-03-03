package cc.ziyiz.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Data
public class User {

    private Integer id;//PRIMARY KEY ID
    private String username;

    @JsonIgnore  // For security, when converting to json,ignore the password,
                 // password attribute will not be in the final json string.
    private String password;

//    @NotEmpty
//    @Pattern( regexp = "^\\S{1,10}$")
    private String nickname;

//    @NotEmpty
//    @Email
    private String email;
    private String userPic;  //url-format 用户头像地址

    private LocalDateTime createTime; //create_time
    private LocalDateTime updateTime; //update_time
}
