package com.techacademy.controller;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; // 追加
import org.springframework.validation.annotation.Validated; // 追加
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService reportservice;

    public ReportController(ReportService service) {
        this.reportservice = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getReportList(@AuthenticationPrincipal UserDetail userdetail,Model model) {
        model.addAttribute("reportlist", reportservice.getReportList());
        // 全件検索結果をModelに登録
        model.addAttribute("reportCount", reportservice.getReportList().size());

        // employee/list.htmlに画面遷移
        return "report/list";
    }



  //詳細画面表示--------------------------
    @GetMapping("/detail/{id}")
    public String getReportdetail(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetail userdetail,Model model) {
        if(id != null) {
            model.addAttribute("report", reportservice.getReport(id));

        }
        // employee詳細画面に遷移
        return "report/detail";
    }

    /** report登録画面を表示------------ */
    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetail userDetail, @ModelAttribute Report report) {
        Employee employee = new Employee();
        employee.setName(userDetail.getEmployee().getName());
        report.setEmployee(employee);
        return "report/register";
    }
    @PostMapping("/register")
    public String postRegister(@AuthenticationPrincipal UserDetail userDetail, @Validated Report report,
            BindingResult res, Model model) {
        if (res.hasErrors()) {
            return getRegister(userDetail, report);
        }
        report.setEmployee(userDetail.getEmployee());
        report.setCreatedAt(new Date(new java.util.Date().getTime()));
        report.setUpdatedAt(new Date(new java.util.Date().getTime()));
        reportservice.saveReport(report);
        return "redirect:/report/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        if (id != null) {
            Report report = reportservice.getReport(id);
            model.addAttribute("report", report);
        }
        return "report/update";
    }
    @PostMapping("/update/{id}")
    public String postUpadate(@Validated Report report, BindingResult res, Model model) {
        Report upReport = reportservice.getReport(report.getId());
        upReport.setReportDate(report.getReportDate());
        upReport.setTitle(report.getTitle());
        upReport.setContent(report.getContent());
        upReport.setCreatedAt(report.getCreatedAt());
        upReport.setUpdatedAt(new Date(new java.util.Date().getTime()));
        reportservice.saveReport(upReport);
        return "redirect:/report/list";
    }
}









