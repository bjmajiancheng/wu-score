package com.wutuobang.common.controller;

import com.wutuobang.common.biz.IAttachmentFileBiz;
import com.wutuobang.common.model.AttachmentFileModel;
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

        PrintWriter writer = response.getWriter();

        try {
            if (file == null || file.isEmpty()) {
                writer.println("请选择文件!!!");
                return;
            }

            AttachmentFileModel attachmentFile = attachmentFileBiz
                    .uploadFile(request, file, AttachmentFileModel.IS_SYSTEM_NO);

            writer.println("上传成功!!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常, 请稍后重试!!");
            return;
        }
    }

}
