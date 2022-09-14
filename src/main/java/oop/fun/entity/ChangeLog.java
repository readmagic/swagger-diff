package oop.fun.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 上午11:02
 */
@Setter
@Getter
@Document(collection = "t_change_log")
public class ChangeLog implements Serializable {
    @Id
    private String id = new ObjectId().toString();
    private LocalDateTime createTime = LocalDateTime.now();

    private String groupId;
    private String swaggerDiffHtml;
    //是否有改变
    private Boolean changed;
    private String version;





}
