package oop.fun.dao;

import oop.fun.entity.ChangeLog;
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
public interface ChangeLogRepo extends MongoRepository<ChangeLog, String> {
    List<ChangeLog> findByGroupIdOrderByCreateTimeDesc(String groupId);
    List<ChangeLog> findByGroupIdInOrderByCreateTimeDesc(List<String> groupId);
}
