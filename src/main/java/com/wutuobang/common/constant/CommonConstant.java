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
    @Value("${application.message}")
    public String applicationMessage;

    //2、申请人自主测评通过后，短信通知申请人、经办人
    @Value("${autoEvaluationPass.message}")
    public String autoevaluationpassMessage;

    //3、申请人预约成功后，取消预约时需要经办人短信验证码
    @Value("${reservation.message}")
    public String reservationMessage;

    //4、申请人信息添加成功、取消预约成功，短信通知申请人、经办人
    @Value("${addApplication.applicant.message}")
    public String addapplicationApplicantMessage;

    //4、申请人信息添加成功、取消预约成功，短信通知申请人、经办人
    @Value("${addApplication.operator.message}")
    public String addapplicationOperatorMessage;

    //5、网上预审结果，短信反馈给申请人
    @Value("${preReview.applicant.message}")
    public String prereviewApplicantMessage;

    //5、网上预审结果，短信反馈给申请人
    @Value("${preReview.operator.message}")
    public String prereviewOperatorMessage;

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
