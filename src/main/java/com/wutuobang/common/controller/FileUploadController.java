package com.wutuobang.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wutuobang.common.biz.IAttachmentFileBiz;
import com.wutuobang.common.model.AttachmentFileModel;
import com.wutuobang.common.model.AttachmentModel;
import com.wutuobang.common.service.IAttachmentFileService;
import com.wutuobang.common.utils.ResultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by majiancheng on 2018/4/6.
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    private IAttachmentFileBiz attachmentFileBiz;

    /**
     * 上传图片
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadImages(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        try {
            if (file == null || file.isEmpty()) {
                writer.println(JSON.toJSONString(ResultParam.error("请选择文件!!!")));
                return;
            }

            AttachmentModel attachmentFile = attachmentFileBiz
                    .uploadFile(request, file, AttachmentFileModel.IS_SYSTEM_NO);

            ResultParam param = new ResultParam(ResultParam.SUCCESS_RESULT.getCode(), "图片上传成功!!",
                    attachmentFile.getAttachmentUrl());
            writer.println(JSON.toJSONString(param));

            return;
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常, 请稍后重试!!");
            return;
        }
    }

}
