package oop.fun.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import oop.fun.pojo.dto.GroupListDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/14
 * Time: 下午3:52
 */
@Setter
@Getter
public class ProjectSelectedVO {
    private Boolean showGenerateBtn;
    private List<GroupListDTO> groups;
}
