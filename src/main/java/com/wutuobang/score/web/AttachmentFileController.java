package com.wutuobang.score.web;

import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.CommonQuestionModel;
import com.wutuobang.score.model.DocumentModel;
import com.wutuobang.score.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2018/4/14.
 */
@Controller
@RequestMapping("/attachmentFile")
public class AttachmentFileController {

    @Autowired
    private IDocumentService documentService;

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

    /**
     * 获取常见问题列表信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultParam list(HttpServletRequest request, @RequestParam("pageNo") Integer pageNo) {
        try {
            PageData<DocumentModel> pageData = documentService.findPage(pageNo);

            return new ResultParam(ResultParam.SUCCESS_RESULT, pageData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }
}
