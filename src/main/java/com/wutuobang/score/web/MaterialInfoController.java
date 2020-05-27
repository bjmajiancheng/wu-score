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
import com.wutuobang.score.model.*;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IHouseMoveService houseMoveService;

    @Autowired
    private IHouseProfessionService houseProfessionService;

    @Autowired
    private IHouseOtherService houseOtherService;

    @Autowired
    private IHouseRelationshipService houseRelationshipService;

    @Autowired
    private IPbScoreRecordService pbScoreRecordService;

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

            //用户所有已上传的材料信息
            List<OnlinePersonMaterialModel> allOnlinePersonMaterials = onlinePersonMaterialService
                    .find(Collections.singletonMap("personId", (Object) identityInfoId));
            Map<Integer, String> allReasonMap = new HashMap<Integer, String>();
            if (CollectionUtils.isNotEmpty(allOnlinePersonMaterials)) {
                for (OnlinePersonMaterialModel onlinePersonMaterial : allOnlinePersonMaterials) {
                    if (StringUtils.isEmpty(onlinePersonMaterial.getReason())) {
                        continue;
                    }
                    String reason = allReasonMap.get(onlinePersonMaterial.getMaterialInfoId());

                    if (StringUtils.isEmpty(reason)) {
                        allReasonMap.put(onlinePersonMaterial.getMaterialInfoId(), onlinePersonMaterial.getReason());
                    } else {
                        allReasonMap.put(onlinePersonMaterial.getMaterialInfoId(),
                                reason + "<br/>" + onlinePersonMaterial.getReason());
                    }
                }
            }

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

            Map<String, Object> findMap = new HashMap<>();
            findMap.put("isUpload", 1);
            findMap.put("sortColumns", "SORTCOLUMNS");
            List<MaterialInfoModel> materialInfos = materialInfoService.find(findMap);
            if (CollectionUtils.isNotEmpty(materialInfos)) {
                for (MaterialInfoModel materialInfo : materialInfos) {
                    OnlinePersonMaterialModel tmpMaterialModel = onlinePersonMaterialMap.get(materialInfo.getId());
                    if (tmpMaterialModel != null) {
                        String reason = allReasonMap.get(materialInfo.getId());
                        if (StringUtils.isNotEmpty(reason)) {
                            tmpMaterialModel.setReason(reason);
                        }
                    }
                    materialInfo.setOnlinePersonMaterial(tmpMaterialModel);
                }
            }


            //判断当前状态是否是重新上传材料
            IdentityInfoModel identityInfo = identityInfoService.getById(identityInfoId);
            if (identityInfo.getUnionApproveStatus1() == 4 || identityInfo.getReservationStatus() == 7 || identityInfo.getReservationStatus() == 8 || identityInfo.getReservationStatus() == 10 || identityInfo.getReservationStatus() == 11
                    || identityInfo.getPoliceApproveStatus() == 2 || identityInfo.getRensheAcceptStatus() == 2) {
                mv.addObject("reUpload", 1);
            } else {
                mv.addObject("reUpload", 0);
            }

            if (identityInfo.getMaterialStatus() != null && identityInfo.getMaterialStatus() == 1) {
                mv.addObject("reUpload", 1);
            }
            mv.addObject("identityInfo", identityInfo);

            /*
            2020年1月18日，根据申请人的录入信息选择合适的上传项给申请人
             */
            for (int i=0;i<materialInfos.size();i++){
                /*
                教育相关信息
                本科及以上、大专有4个上传项
                    毕业证书原件电子图片
                    《中国高等教育学历认证报告》原件电子图片或《教育部学历证书电子注册备案表》电子图片
                    教委佐证材料1
                    教委佐证材料2
                 高级技工学校高级班
                    高级技工学校高级班学历证书（原件）
                    与高级技工学校高级班学历证书对应的技能职业资格证书（高级工）（照片页）
                    与高级技工学校高级班学历证书对应的技能职业资格证书（高级工）（成绩页）
                    与高级技工学校高级班学历证书对应的技能职业资格证书（高级工）（级别页）
                 其他
                    无
                 */
                HouseOtherModel houseOtherModel = houseOtherService.getByIdentityInfoId(identityInfo.getId());
                List<HouseRelationshipModel> houseRelationshipModelList = houseRelationshipService.getByIdentityInfoId(identityInfo.getId());
                HouseMoveModel houseMoveModel = houseMoveService.getByIdentityInfoId(identityInfo.getId());
                HouseProfessionModel houseProfessionModel = houseProfessionService.getByIdentityInfoId(identityInfo.getId());

                if (houseOtherModel.getCultureDegree()!=null && (houseOtherModel.getCultureDegree()==4 || houseOtherModel.getCultureDegree()==5)){ // 本科及以上、大专
                    if (materialInfos.get(i).getId()==3 || materialInfos.get(i).getId()==8 || materialInfos.get(i).getId()==9 || materialInfos.get(i).getId()==1081){
                        materialInfos.remove(i--);
                    }
                }
                if (houseOtherModel.getCultureDegree()!=null && houseOtherModel.getCultureDegree()==1011){ // 高级技工学校高级班
                    if (materialInfos.get(i).getId()==1 || materialInfos.get(i).getId()==2 || materialInfos.get(i).getId()==1091 || materialInfos.get(i).getId()==1092){
                        materialInfos.remove(i--);
                    }
                }
                if (houseOtherModel.getCultureDegree()!=null && houseOtherModel.getCultureDegree()==1013){ // 其他
                    if (materialInfos.get(i).getId()==1 || materialInfos.get(i).getId()==2 || materialInfos.get(i).getId()==1091 || materialInfos.get(i).getId()==1092 ||
                            materialInfos.get(i).getId()==3 || materialInfos.get(i).getId()==8 || materialInfos.get(i).getId()==9 || materialInfos.get(i).getId()==1081){
                        materialInfos.remove(i--);
                    }
                }

                /*
                民政相关信息，婚姻状态的要上传5个材料
                    结婚证第一页
                    配偶身份证原件正面
                    结婚证第二页
                    配偶身份证原件反面
                    民政佐证材料

                    2：未婚；3：丧偶；4：离异
                 */
                if (houseMoveModel!=null && houseMoveModel.getMarriageStatus()!=null && (houseMoveModel.getMarriageStatus()==2 || houseMoveModel.getMarriageStatus()==3 || houseMoveModel.getMarriageStatus()==4) ){
                    if (materialInfos.get(i).getId()==17 || materialInfos.get(i).getId()==18 || materialInfos.get(i).getId()==1080 || materialInfos.get(i).getId()==1090 || materialInfos.get(i).getId()==1153){
                        materialInfos.remove(i--);
                    }
                }

                /*
                退役军人事务局相关信息
                    服务期间立工情况为无时，不需要有任何的上传材料，删掉如下材料
                    立功证书原件（封皮）
                    立功证书第一页
                    立功证书第二页
                    立功证书第三页
                    立功证书第四页
                    立功证书第五页
                    立功证书第六页
                    立功证书第七页
                    立功证书第八页
                    立功证书第九页
                    立功证书第十页
                    立功证章
                    《个人记功登记（报告）表》正面
                    《个人记功登记（报告）表》反面
                 */
                if (houseOtherModel.getSoldierMeritorious()!=null && houseOtherModel.getSoldierMeritorious()==50){
                    if (materialInfos.get(i).getId()==1129
                            || materialInfos.get(i).getId()==1130
                            || materialInfos.get(i).getId()==1131
                            || materialInfos.get(i).getId()==1132
                            || materialInfos.get(i).getId()==1133
                            || materialInfos.get(i).getId()==1134
                            || materialInfos.get(i).getId()==1135
                            || materialInfos.get(i).getId()==1136
                            || materialInfos.get(i).getId()==1137
                            || materialInfos.get(i).getId()==1138
                            || materialInfos.get(i).getId()==1139
                            || materialInfos.get(i).getId()==1140
                            || materialInfos.get(i).getId()==1141
                            || materialInfos.get(i).getId()==1142){
                        materialInfos.remove(i--);
                    }
                }
                /*
                卫健委相关信息
                    配偶死亡的要上传：配偶死亡证明
                    收养子女的要上传：收养证明
                 */
                if (houseMoveModel.getMarriageStatus()!=null && houseMoveModel.getMarriageStatus()!= 3){
                    if (materialInfos.get(i).getId()==1060){
                        materialInfos.remove(i--);
                    }
                }
                boolean flag = true;
                if (houseRelationshipModelList.size()>0){
                    for (HouseRelationshipModel houseRelationshipModel : houseRelationshipModelList){
                        if (houseRelationshipModel.getRelationship()!=null && !houseRelationshipModel.getRelationship().equalsIgnoreCase("配偶")){
                            if (houseRelationshipModel.getIsAdopt()!=null && Integer.parseInt(houseRelationshipModel.getIsAdopt())==1){
                                flag = false;
                            }
                        }
                    }
                }
                if (flag){ // 即，没有收养的子女
                    if (materialInfos.get(i).getId()==1059){
                        materialInfos.remove(i--);
                    }
                }

                /*
                公安相关信息
                落户类别：
                    持有不动产权证或者房屋所有权证
                    购买住房还未办理不动产权证，但持有购房合同和税收缴款书（此选项不允许子女随迁）
                    无住房，但单位已设立集体户口（此选项不允许子女随迁）
                    无住房，且单位也未设立集体户口，自愿落户在市、区政府下设人力资源中介机构集体户（此选项不允许子女随迁）
                 上面4个选项分别对应不同的上传材料

                 */
                if (houseMoveModel.getSettledNature()!=null && houseMoveModel.getSettledNature()==1){
                    if (materialInfos.get(i).getId()==1179
                            || materialInfos.get(i).getId()==1180
                            || materialInfos.get(i).getId()==1181
                            || materialInfos.get(i).getId()==1182
                            || materialInfos.get(i).getId()==1158
                            || materialInfos.get(i).getId()==1159
                            || materialInfos.get(i).getId()==1160){
                        //materialInfos.remove(i--);
                    }
                }
                if (houseMoveModel.getSettledNature()!=null && houseMoveModel.getSettledNature()==2){
                    if (materialInfos.get(i).getId()==1182
                            || materialInfos.get(i).getId()==1154
                            || materialInfos.get(i).getId()==1155
                            || materialInfos.get(i).getId()==1158
                            || materialInfos.get(i).getId()==1159
                            || materialInfos.get(i).getId()==1160){
                        //materialInfos.remove(i--);
                    }
                }
                if (houseMoveModel.getSettledNature()!=null && houseMoveModel.getSettledNature()==3){
                    if (materialInfos.get(i).getId()==1179
                            || materialInfos.get(i).getId()==1180
                            || materialInfos.get(i).getId()==1181
                            || materialInfos.get(i).getId()==1182
                            || materialInfos.get(i).getId()==1154
                            || materialInfos.get(i).getId()==1155
                            || materialInfos.get(i).getId()==1160){
                        materialInfos.remove(i--);
                    }
                }
                if (houseMoveModel.getSettledNature()!=null && houseMoveModel.getSettledNature()==4){
                    if (materialInfos.get(i).getId()==1179
                            || materialInfos.get(i).getId()==1180
                            || materialInfos.get(i).getId()==1181
                            || materialInfos.get(i).getId()==1182
                            || materialInfos.get(i).getId()==1154
                            || materialInfos.get(i).getId()==1155
                            || materialInfos.get(i).getId()==1158
                            || materialInfos.get(i).getId()==1159){
                        //materialInfos.remove(i--);
                    }
                }

                /*
                人社相关信息
                    不想干了
                 */
                if (houseProfessionModel.getProfessionType()!=null && houseProfessionModel.getProfessionType()==1){ // 无
                    if (materialInfos.get(i).getId()==10
                            || materialInfos.get(i).getId()==1046
                            || materialInfos.get(i).getId()==11
                            /*|| materialInfos.get(i).getId()==13
                            || materialInfos.get(i).getId()==12
                            || materialInfos.get(i).getId()==1047*/){
                        materialInfos.remove(i--);
                    }
                }
                if (houseProfessionModel.getProfessionType()!=null && houseProfessionModel.getProfessionType()==3){ // 具有技能人员职业资格
                    if (materialInfos.get(i).getId()==10
                            || materialInfos.get(i).getId()==1046
                            || materialInfos.get(i).getId()==11){
                        materialInfos.remove(i--);
                    }
                }
                if (houseProfessionModel.getProfessionType()!=null && houseProfessionModel.getProfessionType()==2){ // 具有专业技术人员职业资格
//                    if (materialInfos.get(i).getId()==13
//                            || materialInfos.get(i).getId()==12
//                            || materialInfos.get(i).getId()==1047){
//                        materialInfos.remove(i--);
//                    }
                }
                if (houseOtherModel.getAwardsTitle()!=null && houseOtherModel.getAwardsTitle()==46){
                    if (materialInfos.get(i).getId()==22
                            || materialInfos.get(i).getId()==23
                            || materialInfos.get(i).getId()==24){
                        materialInfos.remove(i--);
                    }
                }

                /*
                居住信息页面
                 */
                if (identityInfo.getOurHouse()!=null && Integer.parseInt(identityInfo.getOurHouse())==2){ // 是否自有住房，2：否
                    if (materialInfos.get(i).getId()==1020
                            || materialInfos.get(i).getId()==1161
                            || materialInfos.get(i).getId()==1162
                            || materialInfos.get(i).getId()==1163
//                            || materialInfos.get(i).getId()==1094
//                            || materialInfos.get(i).getId()==1110
                            || materialInfos.get(i).getId()==1166
                            || materialInfos.get(i).getId()==1167
                            || materialInfos.get(i).getId()==1168
                            || materialInfos.get(i).getId()==1169
                            || materialInfos.get(i).getId()==1170
                            || materialInfos.get(i).getId()==1171
                            || materialInfos.get(i).getId()==1172
                            || materialInfos.get(i).getId()==1173
                            || materialInfos.get(i).getId()==1174
                            || materialInfos.get(i).getId()==1175
                            || materialInfos.get(i).getId()==1176
                            || materialInfos.get(i).getId()==1177
                            || materialInfos.get(i).getId()==1201){
                        // 2020年4月8日
                        //materialInfos.remove(i--);
                    }
                }
                if (identityInfo.getOurHouse()!=null && Integer.parseInt(identityInfo.getOurHouse())==1){ // 是否自有住房，1：是
                    if (identityInfo.getRightProperty()!=null && Integer.parseInt(identityInfo.getRightProperty())==1){ // 持有“不动产权证”
                        if (identityInfo.getHouseProperty()!=null && Integer.parseInt(identityInfo.getHouseProperty())==1){
                            if (materialInfos.get(i).getId()==1020
                                    || materialInfos.get(i).getId()==1161
                                    || materialInfos.get(i).getId()==1162
                                    || materialInfos.get(i).getId()==1163){
                               // materialInfos.remove(i--);
                            }
                        }
                        if (identityInfo.getHouseProperty()!=null && Integer.parseInt(identityInfo.getHouseProperty()) != 1){
                            if (/*materialInfos.get(i).getId()==1020
                                    || materialInfos.get(i).getId()==1161
                                    || materialInfos.get(i).getId()==1162
                                    || materialInfos.get(i).getId()==1163*/
//                                     materialInfos.get(i).getId()==1094
//                                    || materialInfos.get(i).getId()==1110
                                     materialInfos.get(i).getId()==1166
                                    || materialInfos.get(i).getId()==1167
                                    || materialInfos.get(i).getId()==1168){
                                //materialInfos.remove(i--);
                            }
                        }
                    }
                    if (identityInfo.getRightProperty()!=null && Integer.parseInt(identityInfo.getRightProperty())==2){
                        if (materialInfos.get(i).getId()==1169
                                || materialInfos.get(i).getId()==1170
                                || materialInfos.get(i).getId()==1171
                                || materialInfos.get(i).getId()==1172
                                || materialInfos.get(i).getId()==1173
                                || materialInfos.get(i).getId()==1174
                                || materialInfos.get(i).getId()==1175
                                || materialInfos.get(i).getId()==1176
                                || materialInfos.get(i).getId()==1177
                                || materialInfos.get(i).getId()==1201){
                            // 2020年4月8日
                            //materialInfos.remove(i--);
                        }
                    }
                }

            }
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

            if (CollectionUtils.isEmpty(onlinePersonMaterials)) {
                int delCount = onlinePersonMaterialService.deleteByPersonId(identityInfoId);
            }

            if (CollectionUtils.isNotEmpty(onlinePersonMaterials)) {
                //用户已上传的材料信息
                List<OnlinePersonMaterialModel> beforeMaterialModels = onlinePersonMaterialService
                        .getByPersonId(identityInfoId);

                Map<Integer, OnlinePersonMaterialModel> beforeMaterialMap = new HashMap<Integer, OnlinePersonMaterialModel>();
                for (OnlinePersonMaterialModel beforeMaterialModel : beforeMaterialModels) {
                    beforeMaterialMap.put(beforeMaterialModel.getId(), beforeMaterialModel);
                }

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
                        onlinePersonMaterial.setStatus(0);
                        toUpdateOnlinePersonMaterials.add(onlinePersonMaterial);

                        beforeMaterialMap.remove(onlinePersonMaterial.getId());
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

                List<Integer> delIds = new ArrayList<Integer>(beforeMaterialMap.keySet());
                if (CollectionUtils.isNotEmpty(delIds)) {
                    int delCount = onlinePersonMaterialService.delByIds(delIds);
                }

                //待补件功能修改
                if (identityInfoModel.getUnionApproveStatus1() == 4 || identityInfoModel.getUnionApproveStatus2() == 4
                        || identityInfoModel.getPoliceApproveStatus() == 2
                        || identityInfoModel.getRensheAcceptStatus() == 2
                        || (identityInfoModel.getMaterialStatus()!=null && identityInfoModel.getMaterialStatus() == 1)) {
                    IdentityInfoModel updateIdentityInfo = new IdentityInfoModel();
                    updateIdentityInfo.setId(identityInfoModel.getId());
                    if (identityInfoModel.getUnionApproveStatus1() == 4) {
                        updateIdentityInfo.setUnionApproveStatus1(1);
                    }
                    if (identityInfoModel.getUnionApproveStatus2() == 4) {
                        updateIdentityInfo.setUnionApproveStatus2(1);
                    }
                    if (identityInfoModel.getPoliceApproveStatus() == 2) {
                        updateIdentityInfo.setPoliceApproveStatus(1);
                    }
                    if (identityInfoModel.getRensheAcceptStatus() == 2) {
                        updateIdentityInfo.setRensheAcceptStatus(1);
                    }
                    if (identityInfoModel.getMaterialStatus()!=null && identityInfoModel.getMaterialStatus() == 1) {
                        /*
                        2019年1月28日
                        材料上传页面，材料驳回后，申请人再次上传通过后，
                         */
                        updateIdentityInfo.setMaterialStatus(2);

                        /**
                         * 2020年5月27日优化材料补正
                         */
                        List<PbScoreRecordModel> list = pbScoreRecordService.getByPersonId(identityInfoId);
                        for (PbScoreRecordModel pbScoreRecordModel : list){
                            if (pbScoreRecordModel.getStatus()==5){
                                pbScoreRecordModel.setStatus(2); // 由补正状态改为待审核状态
                                pbScoreRecordService.update(pbScoreRecordModel);
                            }
                        }
                    }
                    identityInfoService.update(updateIdentityInfo);
                }
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
