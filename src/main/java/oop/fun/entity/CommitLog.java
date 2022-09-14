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
@Document(collection = "t_commit_log")
public class CommitLog implements Serializable {
    @Id
    private String id = new ObjectId().toString();
    private LocalDateTime createTime = LocalDateTime.now();

    private String groupId;
    private String md5;
    private String specSwagger;
    //是否归档(归档相当于处理好以后形成了changleLog日志)
    private Boolean archived;
}
