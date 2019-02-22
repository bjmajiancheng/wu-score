package com.wutuobang.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by majiancheng on 2018/4/15.
 */
@Component
public class CommonConstant {

    /**
     * 每页总数
     */
    public static final int PAGE_SIZE = 10;

    //1、企业账号中申请人添加、预约、取消预约、资料修改时，需要短信验证码
    public String applicationMessage = "系统提示：您的验证码为：%s，有效期为5分钟，请勿向他人提供收到的信息。";

    //2、申请人自主测评通过后，短信通知申请人、经办人
    //public String autoevaluationpassMessage = "系统提示：%s，恭喜您已通过自主测评，下一步可以进行网上预约。";
    public String autoevaluationpassMessage = "系统提示：%s，恭喜您已通过自主测评，下一步根据系统提示上传材料。";

    //3、申请人预约成功后，取消预约时需要经办人短信验证码
    public String reservationMessage = "系统提示：您的验证码为：%s，有效期为5分钟，请勿向他人提供收到的信息。";

    //4、申请人信息添加成功、取消预约成功，短信通知申请人、经办人
    public String addapplicationApplicantMessage = "系统提示：%s，您已成功添加申请人信息。";

    //4、申请人信息添加成功、取消预约成功，短信通知申请人、经办人
    public String addapplicationOperatorMessage = "系统提示：%s，您已成功取消预约。";

    //5、网上预审结果，短信反馈给申请人
    public String prereviewApplicantMessage = "系统提示：%s，恭喜您已通过网上预审，下一步可以进行网上预约。";

    //5、网上预审结果，短信反馈给申请人
    public String prereviewOperatorMessage = "系统提示：%s，您的申请信息网上预审未通过。";

    public String getApplicationMessage() {
        return applicationMessage;
    }

    public void setApplicationMessage(String applicationMessage) {
        this.applicationMessage = applicationMessage;
    }

    public String getAutoevaluationpassMessage() {
        return autoevaluationpassMessage;
    }

    public void setAutoevaluationpassMessage(String autoevaluationpassMessage) {
        this.autoevaluationpassMessage = autoevaluationpassMessage;
    }

    public String getReservationMessage() {
        return reservationMessage;
    }

    public void setReservationMessage(String reservationMessage) {
        this.reservationMessage = reservationMessage;
    }

    public String getAddapplicationApplicantMessage() {
        return addapplicationApplicantMessage;
    }

    public void setAddapplicationApplicantMessage(String addapplicationApplicantMessage) {
        this.addapplicationApplicantMessage = addapplicationApplicantMessage;
    }

    public String getAddapplicationOperatorMessage() {
        return addapplicationOperatorMessage;
    }

    public void setAddapplicationOperatorMessage(String addapplicationOperatorMessage) {
        this.addapplicationOperatorMessage = addapplicationOperatorMessage;
    }

    public String getPrereviewApplicantMessage() {
        return prereviewApplicantMessage;
    }

    public void setPrereviewApplicantMessage(String prereviewApplicantMessage) {
        this.prereviewApplicantMessage = prereviewApplicantMessage;
    }

    public String getPrereviewOperatorMessage() {
        return prereviewOperatorMessage;
    }

    public void setPrereviewOperatorMessage(String prereviewOperatorMessage) {
        this.prereviewOperatorMessage = prereviewOperatorMessage;
    }
}
