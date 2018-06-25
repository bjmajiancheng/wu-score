package com.wutuobang.common.biz;

import com.wutuobang.common.model.AttachmentFileModel;
import com.wutuobang.common.model.AttachmentModel;
import com.wutuobang.common.service.IAttachmentFileService;
import com.wutuobang.common.service.IAttachmentService;
import com.wutuobang.common.utils.DateStyle;
import com.wutuobang.common.utils.DateUtil;
import com.wutuobang.common.utils.FileUtil;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * Created by majiancheng on 2018/4/6.
 */
@Component("attachmentFileBiz")
public class IAttachmentFileBiz {

    @Autowired
    private IAttachmentFileService attachmentFileService;

    @Autowired
    private IAttachmentService attachmentService;

    @Value("${data.upload.path}")
    private String uploadFolder;

    /**
     * 上传文件
     *
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    public AttachmentModel uploadFile(HttpServletRequest request, MultipartFile file, int isSystem, int attachmentType)
            throws IOException {
        Date currDate = new Date();

        String savePath = uploadFolder + "/" + ShiroUtils.getCurrUserName() + "/" + DateUtil
                .DateToString(currDate, DateStyle.YYYYMMDD) + "/";

        /*String downloadPath = request.getContextPath() + "/shopPic/" + ShiroUtils.getCurrUserName() + "/" + DateUtil
                .DateToString(currDate, DateStyle.YYYYMMDD) + "/";*/

        String path = request.getContextPath();
        String downloadPath =
                request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                        + "/shopPic/" + ShiroUtils.getCurrUserName() + "/" + DateUtil
                        .DateToString(currDate, DateStyle.YYYYMMDD) + "/";

        File targetFile = new File(savePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String fileName = file.getOriginalFilename();

        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String newFileName = DateUtil.DateToString(currDate, DateStyle.YYYYMMDDHHMMSS) + String
                .format("%04d", new Random().nextInt(1000)) + "_" + fileName;

        FileUtil.saveFileFromInputStream(file.getInputStream(), savePath, newFileName);

        AttachmentModel attachmentModel = new AttachmentModel();
        attachmentModel.setAttachmentName(fileName);
        attachmentModel.setAttachmentType(attachmentType);
        attachmentModel.setAttachmentSuffix(fileExt);
        attachmentModel.setAttachmentPath(savePath + newFileName);
        attachmentModel.setAttachmentUrl(downloadPath + newFileName);
        attachmentModel.setAttachmentSize(file.getSize());
        int count = attachmentService.insert(attachmentModel);

        return attachmentModel;
    }

}
