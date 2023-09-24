package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.vo.SiteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.SiteService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Auth(Role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SiteService siteService;

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping({"", "/main"})
    public String main(Model model) {
        SiteVo site = siteService.getContents();
        model.addAttribute("site", site);
        return "admin/main";
    }

    @RequestMapping(value = "main/update", method = RequestMethod.POST)
    public String update(SiteVo vo
            , @RequestParam(value = "file1") MultipartFile file) {
        String profile = fileUploadService.restore(file);
        if (profile != null) {
            vo.setProfile(profile);
        }
        siteService.update(vo);
        return "redirect:/admin";
    }


    @RequestMapping("/board")
    public String board() {
        return "admin/board";
    }

    @RequestMapping("/guestbook")
    public String guestbook() {
        return "admin/guestbook";
    }

    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }

}
