package com.wutuobang.common.controller;

import com.wutuobang.common.biz.IAttachmentFileBiz;
import com.wutuobang.common.model.AttachmentFileModel;
import com.wutuobang.common.service.IAttachmentFileService;
import com.wutuobang.common.utils.ResultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2018/4/6.
 */
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
    public ResultParam uploadImages(HttpServletRequest request,
            @RequestParam(value = "file", required = true) MultipartFile file) {

        try {
            if (file == null || file.isEmpty()) {
                return ResultParam.error("请选择文件!!");
            }

            AttachmentFileModel attachmentFile = attachmentFileBiz
                    .uploadFile(request, file, AttachmentFileModel.IS_SYSTEM_NO);

            return new ResultParam(ResultParam.SUCCESS_RESULT, attachmentFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
