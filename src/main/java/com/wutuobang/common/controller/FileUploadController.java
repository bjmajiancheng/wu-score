package com.wutuobang.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wutuobang.common.biz.IAttachmentFileBiz;
import com.wutuobang.common.model.AttachmentFileModel;
import com.wutuobang.common.model.AttachmentModel;
import com.wutuobang.common.service.IAttachmentFileService;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.web.CompanyInfoController;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by majiancheng on 2018/4/6.
 */
@Controller
@RequestMapping
public class FileUploadController {

    @Autowired
    private IAttachmentFileBiz attachmentFileBiz;

    @Value("${data.upload.path}")
    private String uploadFolder;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * 上传图片
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/fileUpload/uploadImage", method = RequestMethod.POST)
    public void uploadImages(HttpServletRequest request, HttpServletResponse response/*,
            @RequestParam(value = "file", required = true) MultipartFile file*/) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = null;

        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Set<String> fileNames = fileMap.keySet();
        for (String fileName : fileNames) {
            file = fileMap.get(fileName);
            break;
        }

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
                    .uploadFile(request, file, AttachmentFileModel.IS_SYSTEM_NO, 0);

            ResultParam param = new ResultParam(ResultParam.SUCCESS_RESULT.getCode(), "图片上传成功!!", attachmentFile);
            writer.println(JSON.toJSONString(param));

            return;
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常, 请稍后重试!!");
            return;
        }
    }

    /**
     * 上传图片
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/fileUpload/updateFile", method = RequestMethod.POST, produces = "text/plain")
    public void updateFile(HttpServletRequest request, HttpServletResponse response/*,
            @RequestParam(value = "file", required = true) MultipartFile file*/) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("updateFile");

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
                    .uploadFile(request, file, AttachmentFileModel.IS_SYSTEM_NO, 1);

            ResultParam param = new ResultParam(ResultParam.SUCCESS_RESULT.getCode(), "文件上传成功!!", attachmentFile);
            writer.println(JSON.toJSONString(param));

            System.out.println("result:" + JSON.toJSONString(param));

            return;
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常, 请稍后重试!!");
            return;
        }
    }

    /**
     * 图片展示信息
     *
     * @param request
     * @param response
     * @param fileFolder
     * @param fileName
     */
    @RequestMapping(value = "/shopPic/{userName}/{fileFolder}/{fileName}.{suffix}")
    public void showPic(HttpServletRequest request, HttpServletResponse response,
            @PathVariable(value = "userName") String userName, @PathVariable(value = "fileFolder") String fileFolder,
            @PathVariable(value = "fileName") String fileName, @PathVariable(value = "suffix") String suffix) {
        /*byte[] b = new byte[20];
        try {
            b = BASE64.decryptBASE64(fileName);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String str = new String(b);*/
/*
        String[] strArr = fileName.split("\\.");*/

        String fileUrl = uploadFolder + "/" + userName + "/" + fileFolder + "/" + fileName + "." + suffix;
        try {

            FileInputStream fileStream = new FileInputStream(fileUrl);
            if (fileStream == null) {
                System.out.println("没有那个文件或目录:fileUrl:" + fileUrl);
                return;
            }

            //载入图像
            BufferedImage buffImg = ImageIO.read(fileStream);
            if (buffImg == null) {
                System.out.println("载入图像为空:fileUrl:" + fileUrl);
                return;
            }

            response.setContentType("image/" + suffix);
            response.setCharacterEncoding("UTF-8");

            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(buffImg, suffix, sos);
            sos.close();


            /*File filePath = new File(fileUrl);
            if (filePath.exists()) {
                //读图片
                FileInputStream inputStream = new FileInputStream(filePath);
                int available = inputStream.available();
                byte[] data = new byte[available];
                inputStream.read(data);
                inputStream.close();
                //写图片
                response.setContentType("image/" + suffix);
                response.setCharacterEncoding("UTF-8");
                OutputStream stream = new BufferedOutputStream(response.getOutputStream());
                stream.write(data);
                stream.flush();
                stream.close();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
