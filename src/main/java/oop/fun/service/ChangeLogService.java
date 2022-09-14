package oop.fun.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import oop.fun.dao.ChangeLogRepo;
import oop.fun.dao.CommitLogRepo;
import oop.fun.dao.GroupRepo;
import oop.fun.dao.ProjectRepo;
import oop.fun.entity.ChangeLog;
import oop.fun.entity.CommitLog;
import oop.fun.entity.Group;
import oop.fun.exception.BusinessAssertException;
import oop.fun.pojo.vo.ChangeLogListVO;
import oop.fun.render.RenderHtml;
import oop.fun.utils.diff.SwaggerDiff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午6:07
 */
@Service
@RequiredArgsConstructor
public class ChangeLogService {
    private final CommitLogRepo commitLogRepo;
    private final ChangeLogRepo changeLogRepo;
    private final GroupRepo groupRepo;
    private final ProjectRepo projectRepo;

    public void generateByGroup(String groupId) {
        List<CommitLog> commitLogs = commitLogRepo.findByGroupIdAndArchivedIsFalse(groupId);
        if (commitLogs.size() >= 2) {
            CommitLog latestCommitLog = commitLogRepo.getFirstByGroupIdAndArchivedIsFalseOrderByCreateTimeDesc(groupId);
            CommitLog oldestCommitLog = commitLogRepo.getFirstByGroupIdAndArchivedIsFalseOrderByCreateTimeAsc(groupId);
            SwaggerDiff swaggerDiff = SwaggerDiff.compare(oldestCommitLog.getSpecSwagger(), latestCommitLog.getSpecSwagger());
            ChangeLog changeLog = new ChangeLog();
            changeLog.setGroupId(groupId);
            String latestTime = DateUtil.format(latestCommitLog.getCreateTime(), DatePattern.PURE_DATE_PATTERN);
            String oldestTime = DateUtil.format(oldestCommitLog.getCreateTime(), DatePattern.PURE_DATE_PATTERN);
            RenderHtml renderHtml = new RenderHtml("变更日志");

            String html = renderHtml.render(swaggerDiff, oldestTime, latestTime);
            if (CollUtil.isEmpty(swaggerDiff.getNewEndpoints()) && CollUtil.isEmpty(swaggerDiff.getMissingEndpoints()) && CollUtil.isEmpty(swaggerDiff.getChangedEndpoints())) {
                changeLog.setChanged(false);
            } else {
                changeLog.setChanged(true);
            }
            changeLog.setVersion(oldestTime + " - " + latestTime);
            changeLog.setSwaggerDiffHtml(html);
            changeLogRepo.save(changeLog);
            for (CommitLog commitLog : commitLogs) {
                if (!commitLog.getId().equals(latestCommitLog.getId())) {
                    commitLog.setArchived(true);
                }
            }
            commitLogRepo.saveAll(commitLogs);
        }

    }

    public void generateByProject(String projectId) {
        List<Group> groups = groupRepo.findByProjectId(projectId);
        for (Group group : groups) {
            generateByGroup(group.getId());
        }
    }

    public String detail(String id) {
        ChangeLog changeLog = changeLogRepo.findById(id).orElseThrow(() -> new BusinessAssertException("id不正确"));
        return changeLog.getSwaggerDiffHtml();
    }

    public List<ChangeLogListVO> list(String projectId, String groupId) {
        List<ChangeLogListVO> result = Lists.newArrayList();
        List<ChangeLog> changeLogs;
        if (StrUtil.isNotEmpty(groupId)) {
            changeLogs = changeLogRepo.findByGroupIdOrderByCreateTimeDesc(groupId);
        } else {
            List<String> groupIds = groupRepo.findByProjectId(projectId).stream().map(x -> x.getId()).collect(Collectors.toList());
            changeLogs = changeLogRepo.findByGroupIdInOrderByCreateTimeDesc(groupIds);
        }

        for (ChangeLog changeLog : changeLogs) {
            ChangeLogListVO one = new ChangeLogListVO();
            groupRepo.findById(changeLog.getGroupId()).ifPresent(x -> {
                one.setGroupName(x.getName());
                projectRepo.findById(x.getProjectId()).ifPresent(y -> {
                    one.setProjectName(y.getName());
                });
            });
            one.setId(changeLog.getId());
            one.setVersion(changeLog.getVersion());
            one.setChanged(changeLog.getChanged());
            one.setCreateTime(DateUtil.format(changeLog.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            result.add(one);
        }

        return result;
    }
}
