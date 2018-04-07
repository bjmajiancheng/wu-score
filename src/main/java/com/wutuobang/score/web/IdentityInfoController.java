package com.wutuobang.score.web;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by majiancheng on 2018/3/30.
 */
@Controller
@RequestMapping(value = "/identityInfo")
public class IdentityInfoController {

    @Autowired
    private IIdentityInfoService identityInfoService;

    @Autowired
    private IHouseMoveService houseMoveService;

    @Autowired
    private IHouseRelationshipService houseRelationshipService;

    @Autowired
    private IHouseOtherService houseOtherService;

    @Autowired
    private IHouseProfessionService houseProfessionService;

    @Autowired
    private IRegionService regionService;

    /**
     * 前往新增用户页面
     *
     * @return
     */
    @RequestMapping(value = "/toAddIdentityInfo", method = RequestMethod.GET)
    public String toAddIdentityInfo() {
        return "application/applicationAdd.html";
    }

    /**
     * 添加申请人信息
     *
     * @param request
     * @param identityInfoJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addIdentityInfo", method = RequestMethod.POST)
    public ResultParam addIdentityInfo(HttpServletRequest request,
            @RequestParam("identityInfoJson") String identityInfoJson, @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(identityInfoJson)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {

            CompanyInfoModel currUser = ShiroUtils.getUserEntity();

            //申请人信息
            IdentityInfoModel identityInfoModel = JSON.parseObject(identityInfoJson, IdentityInfoModel.class);
            if (identityInfoModel != null) {
                identityInfoModel.setCompanyId(currUser.getId());
                if (identityInfoModel.getHouseMoveModel() != null) {
                    identityInfoModel.setRegion(identityInfoModel.getHouseMoveModel().getRegion());
                }
                identityInfoService.insert(identityInfoModel);
            }

            //户籍迁移信息
            HouseMoveModel houseMoveModel = identityInfoModel.getHouseMoveModel();
            if (houseMoveModel != null) {
                houseMoveModel.setIdentityInfoId(identityInfoModel.getId());
                houseMoveService.insert(houseMoveModel);
            }

            //申请人家庭关系
            List<HouseRelationshipModel> houseRelationshipModels = identityInfoModel.getHouseRelationshipModelList();
            if (CollectionUtils.isNotEmpty(houseRelationshipModels)) {
                for (HouseRelationshipModel houseRelationship : houseRelationshipModels) {
                    houseRelationship.setIdentityInfoId(identityInfoModel.getId());
                }
                houseRelationshipService.batchInsert(houseRelationshipModels);
            }

            //申请人其他信息
            HouseOtherModel houseOtherModel = identityInfoModel.getHouseOtherModel();
            if (houseOtherModel != null) {
                houseOtherModel.setIdentityInfoId(identityInfoModel.getId());
                houseOtherService.insert(houseOtherModel);
            }

            //职业资格证书
            HouseProfessionModel houseProfessionModel = identityInfoModel.getHouseProfessionModel();
            if (houseProfessionModel != null) {
                houseProfessionModel.setIdentityInfoId(identityInfoModel.getId());
                houseProfessionService.insert(houseProfessionModel);
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取申请人列表信息
     *
     * @param request
     * @param queryStr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultParam list(HttpServletRequest request, @RequestParam("queryStr") String queryStr) {

        try {

            PageData<IdentityInfoModel> pageData = identityInfoService.findPage(queryStr);

            List<IdentityInfoModel> identityInfos = pageData.getData();
            if (CollectionUtils.isNotEmpty(identityInfos)) {
                List<Integer> regionIds = new ArrayList<Integer>(identityInfos.size());

                for (IdentityInfoModel identityInfo : identityInfos) {
                    regionIds.add(identityInfo.getRegion());
                }

                Map<Integer, RegionModel> regionMap = regionService.getMapByIds(regionIds);
                for (IdentityInfoModel identityInfo : identityInfos) {
                    RegionModel regionModel = regionMap.get(identityInfo.getRegion());
                    if (regionModel != null) {
                        identityInfo.setRegionName(regionModel.getName());
                    }
                }
            }

            return new ResultParam(ResultParam.SUCCESS_RESULT, pageData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }
}
