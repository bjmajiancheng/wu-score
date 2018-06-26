package com.wutuobang.common.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by majiancheng on 2018/4/15.
 */
public class CommonConstant {

    /**
     * 每页总数
     */
    public static final int PAGE_SIZE = 10;

    //1、企业账号中申请人添加、预约、取消预约、资料修改时，需要短信验证码
    @Value("${application.message}")
    public static String APPLICATION_MESSAGE;

    //2、申请人自主测评通过后，短信通知申请人、经办人
    @Value("${autoEvaluationPass.message}")
    public static String AUTOEVALUATIONPASS_MESSAGE;

    //3、申请人预约成功后，取消预约时需要经办人短信验证码
    @Value("${reservation.message}")
    public static String RESERVATION_MESSAGE;

    //4、申请人信息添加成功、取消预约成功，短信通知申请人、经办人
    @Value("${addApplication.applicant.message}")
    public static String ADDAPPLICATION_APPLICANT_MESSAGE;

    //4、申请人信息添加成功、取消预约成功，短信通知申请人、经办人
    @Value("${addApplication.operator.message}")
    public static String ADDAPPLICATION_OPERATOR_MESSAGE;

    //5、网上预审结果，短信反馈给申请人
    @Value("${preReview.applicant.message}")
    public static String PREREVIEW_APPLICANT_MESSAGE;

    //5、网上预审结果，短信反馈给申请人
    @Value("${preReview.operator.message}")
    public static String PREREVIEW_OPERATOR_MESSAGE;
}
