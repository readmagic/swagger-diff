package oop.fun.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/14
 * Time: 下午2:54
 */
@Setter
@Getter
public class ChangeLogListVO {
    private String id;
    private String projectName;
    private String groupName;
    private Boolean changed;
    private String version;
    private String createTime;
}
