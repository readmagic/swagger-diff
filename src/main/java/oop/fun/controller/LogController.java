package oop.fun.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.BooleanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oop.fun.dao.CommitLogRepo;
import oop.fun.dao.GroupRepo;
import oop.fun.entity.CommitLog;
import oop.fun.pojo.dto.GroupListDTO;
import oop.fun.pojo.vo.ProjectSelectedVO;
import oop.fun.render.RenderJson;
import oop.fun.service.ChangeLogService;
import oop.fun.service.FetchDocService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午2:25
 */
@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class LogController {
    private final ChangeLogService changeLogService;
    private final GroupRepo groupRepo;
    private final CommitLogRepo commitLogRepo;
    private final FetchDocService fetchDocService;

    @PostMapping(path = "/generateByProject")
    public String generateByProject(String projectId) {
        changeLogService.generateByProject(projectId);
        return RenderJson.success();
    }
    @PostMapping(path = "/fetchDoc")
    public String fetchDoc(String projectId,String url) {
        String host = url.replace("http://","").split("/")[0];
        if(url.contains("swagger-ui.html")){
            host =  url.replace("http://","").replace("/swagger-ui.html","");
        }
        String finalHost = host;
        ThreadUtil.execAsync(()->{
            fetchDocService.fetchGroupAndSave("http://"+ finalHost,projectId);
        });
         return RenderJson.success();
     }

    @GetMapping(path = "/detail")
    public String detail(String id) {
        return changeLogService.detail(id);
    }

    @GetMapping(path = "/search")
    public String search(String projectId, String groupId) {
        return RenderJson.success(changeLogService.list(projectId, groupId));
    }

    @GetMapping(path = "/groups")
    public String groups(String projectId) {
        ProjectSelectedVO result = new ProjectSelectedVO();
        result.setShowGenerateBtn(false);
        List<GroupListDTO> groups = groupRepo.findByProjectId(projectId).stream().map(x -> {
            GroupListDTO one = new GroupListDTO();
            one.setId(x.getId());
            one.setName(x.getName());
            if (BooleanUtil.isFalse(result.getShowGenerateBtn())) {
                List<CommitLog> commitLogs = commitLogRepo.findByGroupIdAndArchivedIsFalse(x.getId());
                if (commitLogs.size() >= 2) {
                    result.setShowGenerateBtn(true);
                }
            }
            return one;
        }).collect(Collectors.toList());
        result.setGroups(groups);
        return RenderJson.success(result);
    }


}
