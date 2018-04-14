package com.wutuobang.score.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2018/4/14.
 */
@Controller
@RequestMapping("/attachmentFile")
public class AttachmentFileController {

    /**
     * 文件下载页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/download.html")
    public String download(HttpServletRequest request) {
        return "file/download.html";
    }
}
