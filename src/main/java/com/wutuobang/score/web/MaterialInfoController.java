/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.model.AttachmentModel;
import com.wutuobang.common.service.IAttachmentService;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.CompanyInfoModel;
import com.wutuobang.score.model.IdentityInfoModel;
import com.wutuobang.score.model.OnlinePersonMaterialModel;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wutuobang.score.model.MaterialInfoModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/materialInfo")
public class MaterialInfoController {

    @Autowired
    private IMaterialInfoService materialInfoService;

    @Autowired
    private IIdentityInfoService identityInfoService;

    @Autowired
    private IOnlinePersonMaterialService onlinePersonMaterialService;

    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 跳转到上传文件页面
     *
     * @param request
     * @param identityInfoId
     * @return
     */
    @RequestMapping(value = "/updateFile/{identityInfoId}.html", method = RequestMethod.GET)
    public ModelAndView toUpdateFile(HttpServletRequest request,
            @PathVariable("identityInfoId") Integer identityInfoId) {
        if (identityInfoId == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
        try {
            ModelAndView mv = new ModelAndView("file/uploadFile.html");

            //用户已上传的材料信息
            List<OnlinePersonMaterialModel> onlinePersonMaterials = onlinePersonMaterialService
                    .getByPersonId(identityInfoId);

            Map<Integer, OnlinePersonMaterialModel> onlinePersonMaterialMap = new HashMap<Integer, OnlinePersonMaterialModel>();
            if (CollectionUtils.isNotEmpty(onlinePersonMaterials)) {
                for (OnlinePersonMaterialModel onlinePersonMaterial : onlinePersonMaterials) {
                    onlinePersonMaterialMap.put(onlinePersonMaterial.getMaterialInfoId(), onlinePersonMaterial);
                }
            }

            //所有材料信息
            List<MaterialInfoModel> materialInfos = materialInfoService.find(new HashMap<String, Object>());
            if (CollectionUtils.isNotEmpty(materialInfos)) {
                for (MaterialInfoModel materialInfo : materialInfos) {
                    materialInfo.setOnlinePersonMaterial(onlinePersonMaterialMap.get(materialInfo.getId()));
                }
            }

            mv.addObject("identityInfo", identityInfoService.getById(identityInfoId));
            mv.addObject("materialInfos", materialInfos);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
    }

    /**
     * 保存用户上传文件信息
     *
     * @param request
     * @param materialInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveMaterialInfo", method = RequestMethod.POST)
    public ResultParam saveMaterialInfo(HttpServletRequest request, @RequestParam("materialInfo") String materialInfo,
            @RequestParam("identityInfoId") Integer identityInfoId, @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(materialInfo) || identityInfoId == null) {
            return ResultParam.SYSTEM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {
            List<OnlinePersonMaterialModel> onlinePersonMaterials = JSON
                    .parseArray(materialInfo, OnlinePersonMaterialModel.class);

            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);

            if (CollectionUtils.isNotEmpty(onlinePersonMaterials)) {
                List<Integer> materialIds = new ArrayList<Integer>(onlinePersonMaterials.size());
                for (OnlinePersonMaterialModel onlinePersonMaterial : onlinePersonMaterials) {
                    if (onlinePersonMaterial.getMaterialId() != null && !materialIds
                            .contains(onlinePersonMaterial.getMaterialId())) {
                        materialIds.add(onlinePersonMaterial.getMaterialId());
                    }
                }

                Map<Integer, AttachmentModel> attachmentMap = attachmentService.findMapByIds(materialIds);

                List<OnlinePersonMaterialModel> toAddOnlinePersonMaterials = new ArrayList<OnlinePersonMaterialModel>();
                List<OnlinePersonMaterialModel> toUpdateOnlinePersonMaterials = new ArrayList<OnlinePersonMaterialModel>();
                for (OnlinePersonMaterialModel onlinePersonMaterial : onlinePersonMaterials) {
                    onlinePersonMaterial.setPersonId(identityInfoModel.getId());
                    onlinePersonMaterial.setBatchId(identityInfoModel.getBatchId());

                    AttachmentModel attachmentModel = attachmentMap.get(onlinePersonMaterial.getMaterialId());
                    if (attachmentModel == null) continue;
                    onlinePersonMaterial.setMaterialId(attachmentModel.getAttachmentId());
                    onlinePersonMaterial.setMaterialName(attachmentModel.getAttachmentName());
                    onlinePersonMaterial.setMaterialUri(attachmentModel.getAttachmentUrl());

                    if (onlinePersonMaterial.getId() == null) {
                        onlinePersonMaterial.setStatus(0);
                        onlinePersonMaterial.setCtime(new Date());
                        toAddOnlinePersonMaterials.add(onlinePersonMaterial);
                    } else {
                        toUpdateOnlinePersonMaterials.add(onlinePersonMaterial);
                    }
                }

                int updateCount = 0;
                if (CollectionUtils.isNotEmpty(toAddOnlinePersonMaterials)) {
                    updateCount += onlinePersonMaterialService.batchInsert(toAddOnlinePersonMaterials);
                }
                if (CollectionUtils.isNotEmpty(toUpdateOnlinePersonMaterials)) {
                    for (OnlinePersonMaterialModel onlinePersonMaterial : toUpdateOnlinePersonMaterials) {
                        updateCount += onlinePersonMaterialService.update(onlinePersonMaterial);
                    }
                }
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
