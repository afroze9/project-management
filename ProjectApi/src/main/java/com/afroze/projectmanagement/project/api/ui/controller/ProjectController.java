package com.afroze.projectmanagement.project.api.ui.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @GetMapping("/private")
    public String GetProjectsPrivate(@AuthenticationPrincipal Jwt jwt) {
        return "Projects sub: " + jwt.getSubject();
    }

    @GetMapping("/public")
    public String GetProjectsPublic() {
        return "Projects public";
    }
}
