package oop.fun.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oop.fun.pojo.fo.CommitFO;
import oop.fun.render.RenderJson;
import oop.fun.service.FetchDocService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 下午2:24
 */
@Slf4j
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class CommitController {
    private final FetchDocService fetchDocService;

    @PostMapping(path = "/commit")
    public String commit(CommitFO input) {
        fetchDocService.saveProjectAndFetchDoc(input);
        return RenderJson.success();
    }
}
