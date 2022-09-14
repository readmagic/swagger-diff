package oop.fun.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午2:38
 */
@Setter
@Getter
@Document(collection = "t_group")
public class Group {
    @Id
    private String id = new ObjectId().toString();
    private LocalDateTime createTime = LocalDateTime.now();

    private String projectId;
    private String name;

}
