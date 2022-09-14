package oop.fun.dao;

import oop.fun.entity.CommitLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 上午11:02
 */
@Repository
public interface CommitLogRepo extends MongoRepository<CommitLog, String> {
    CommitLog getByMd5AndGroupId(String md5,String groupId);

    CommitLog getFirstByGroupIdAndArchivedIsFalseOrderByCreateTimeDesc(String groupId);
    CommitLog getFirstByGroupIdAndArchivedIsFalseOrderByCreateTimeAsc(String groupId);
    List<CommitLog> findByGroupIdAndArchivedIsFalse(String groupId);

}
