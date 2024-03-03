package cc.ziyiz.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String coverImg; // pic-url

    private String state;//  [Published] or [Draft]
    private Integer categoryId;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
