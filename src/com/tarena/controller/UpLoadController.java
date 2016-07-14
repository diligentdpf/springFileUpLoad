package com.tarena.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UpLoadController {

    @RequestMapping("/toUpload.form")
    public String toUpload() {
        return "upload";

    }

    // 单个文件上传
    @RequestMapping(value = "/upload.form")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest req, HttpServletResponse res, ModelMap model) throws Exception {
        System.out.println("================");
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("UTF-8");
        // 从当前请求对象中获取session对象--》servletContext对象--》绝对路径realPath
        String path = req.getSession().getServletContext().getRealPath("test");
        // 从页面传来的文件file对象中获取到文件名
        String fileName = file.getOriginalFilename();
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存文件
        try {
            file.transferTo(targetFile);
            // model.addAttribute("fileUrl",req.getContextPath()+"/upload/"+fileName);
            model.addAttribute("fileUrl", req.getContextPath() + "/test/" + fileName);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            model.addAttribute("message", e.getStackTrace());
            return "error";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            model.addAttribute("message", e.getStackTrace());
            return "error";
        }
        return "result";
    }


    // 批量文件上传
    @RequestMapping(value = "uploads.form")
    public String uploads(@RequestParam(value = "file", required = false) MultipartFile[] files,
            HttpServletRequest req, ModelMap model) {
        List<String> urls = new ArrayList<String>();
        for (MultipartFile file : files) {
            String path = req.getSession().getServletContext().getRealPath("upload");
            String fileName = file.getOriginalFilename();
            System.out.println(path);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // 保存文件
            try {
                file.transferTo(targetFile);
                urls.add(req.getContextPath() + "/upload/" + fileName);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        model.addAttribute("fileUrls", urls);
        return "result";

    }
}
