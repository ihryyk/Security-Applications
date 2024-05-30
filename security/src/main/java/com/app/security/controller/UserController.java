package com.app.security.controller;

import com.app.security.service.UserService;
import com.app.security.service.security.LoginAttemptService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final LoginAttemptService loginAttemptService;

    @GetMapping("/blocked")
    String getBlockedUsers(Model model) {
        Set<String> blockedUsers = loginAttemptService.getBlockedUsers();
        model.addAttribute("blockedUsers", blockedUsers);
        return "blocked_users";
    }

}
