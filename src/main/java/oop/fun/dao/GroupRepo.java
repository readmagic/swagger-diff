package oop.fun.dao;

import oop.fun.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午2:21
 */
@Repository
public interface GroupRepo extends MongoRepository<Group, String> {
    Group getByProjectIdAndName(String projectId,String name);
    List<Group> findByProjectId(String projectId);
}
