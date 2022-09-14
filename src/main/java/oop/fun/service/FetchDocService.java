package oop.fun.service;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import oop.fun.dao.CommitLogRepo;
import oop.fun.dao.GroupRepo;
import oop.fun.dao.ProjectRepo;
import oop.fun.entity.CommitLog;
import oop.fun.entity.Group;
import oop.fun.entity.Project;
import oop.fun.pojo.fo.CommitFO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午2:52
 */
@Service
@RequiredArgsConstructor
public class FetchDocService {
    private static final String GROUP_API = "/swagger-resources";
    private static final String SWAGGER_FLAG = "swagger";

    private final ProjectRepo projectRepo;
    private final GroupRepo groupRepo;
    private final CommitLogRepo commitLogRepo;

    @Async
    public void saveProjectAndFetchDoc(CommitFO input) {
        Project project = projectRepo.getByName(input.getProjectName());
        if (Objects.isNull(project)) {
            project = new Project();
            project.setName(input.getProjectName());
            projectRepo.save(project);
        }
        fetchGroupAndSave(input.getProjectHost(), project.getId());
    }

    public void fetchGroupAndSave(String host, String projectId) {
        String response = HttpUtil.get(host + GROUP_API);
        JSONArray groups = JSONUtil.parseArray(response);
        for (int i = 0; i < groups.size(); i++) {
            JSONObject groupJson = groups.getJSONObject(i);
            String name = groupJson.getStr("name");
            String location = groupJson.getStr("location");
            Group group = groupRepo.getByProjectIdAndName(projectId, name);
            if (Objects.isNull(group)) {
                group = new Group();
                group.setName(name);
                group.setProjectId(projectId);
                groupRepo.save(group);
            }
            fetchDoc(host, location, group.getId());
        }
    }

    private void fetchDoc(String host, String location, String groupId) {
        String response = HttpUtil.get(host + location);
        Boolean isJson = JSONUtil.isTypeJSON(response);
        if (BooleanUtil.isTrue(isJson)) {
            if (BooleanUtil.isFalse(StrUtil.contains(response, SWAGGER_FLAG))) {
                return;
            }
            String md5 = SecureUtil.md5(response);
            CommitLog commitLog = commitLogRepo.getByMd5AndGroupId(md5, groupId);
            if (Objects.isNull(commitLog)) {
                commitLog = new CommitLog();
                commitLog.setMd5(md5);
                commitLog.setSpecSwagger(response);
                commitLog.setArchived(false);
                commitLog.setGroupId(groupId);
                commitLogRepo.save(commitLog);
            }
        }
    }
}
