package com.ll.security_demo.controller;

import com.ll.security_demo.dto.SignupRequest;
import com.ll.security_demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class authController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("authorities", userDetails.getAuthorities());
            model.addAttribute("password", userDetails.getPassword());
        }
        return "dashboard";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupRequest signupRequest, BindingResult bindingResult){

        if(!signupRequest.getPassword().equals(signupRequest.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "mismatch", "비밀번호가 일치하지 않습니다.");
        }

        if(bindingResult.hasErrors()) {
            return "signup";
        }
        if(userService.existsByUsername(signupRequest.getUsername())) {
            bindingResult.rejectValue("username", "duplicate", "이미 사용중인 아이디 입니다.");
            return "signup";
        }
        if(userService.existsByEmail(signupRequest.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "이미 사용중인 이메일 입니다.");
            return "signup";
        }


        userService.register(signupRequest);

        return "redirect:/login";
    }
}
