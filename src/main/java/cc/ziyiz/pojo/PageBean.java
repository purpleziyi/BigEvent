package cc.ziyiz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Paging return result object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private Long total;// Total number of items
    private List<T> items;// dataset of current page
}
