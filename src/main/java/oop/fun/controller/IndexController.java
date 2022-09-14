package oop.fun.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oop.fun.dao.ProjectRepo;
import oop.fun.entity.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/14
 * Time: 下午2:08
 */
@Slf4j
@Controller
@RequestMapping()
@RequiredArgsConstructor
public class IndexController {
    private final ProjectRepo projectRepo;

    @GetMapping(path = "/")
    public String index(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects",projects);
        return "index";
    }
}
