package oop.fun.dao;

import oop.fun.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午2:21
 */
@Repository
public interface ProjectRepo extends MongoRepository<Project, String> {
    Project getByName(String name);

}
