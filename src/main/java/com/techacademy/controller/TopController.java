package com.techacademy.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;
@Controller
public class TopController {

    private final ReportService service;

    public TopController(ReportService service) {
        this.service = service;
    }
    /** トップページを表示 */
    @GetMapping("/")
    public String getTop(@AuthenticationPrincipal UserDetail user,Model model,Integer id) {
        List<Report> userlist=service.findByEmployee(user);
        model.addAttribute("reportlist",userlist);
        model.addAttribute("reportCount",userlist.size());


        // top.htmlに画面遷移
        return "top";
    }
}