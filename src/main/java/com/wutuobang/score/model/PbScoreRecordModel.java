package com.wutuobang.score.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rui15 on 2018/12/7.
 * t_pb_score_record：打分项，每个人都有20个打分项
 */
public class PbScoreRecordModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;//NUMBER(11)id
    private String accept_number;//VARCHAR2(32 CHAR)受理编号
    private Integer batch_id;//NUMBER(11)批次ID
    private Integer indicator_id;//NUMBER(11)指标ID
    private  String indicator_name;//VARCHAR2(64 CHAR)指标名称
    private Integer person_id;//NUMBER(11)申请人ID
    private  String person_name;//VARCHAR2(64 CHAR)申请人
    private  String person_id_num;//VARCHAR2(32 CHAR)申请人身份证
    private  String person_mobile_phone;//VARCHAR2(32 CHAR)申请人手机号
    private Integer company_id;//NUMBER(11)企业ID
    private  String company_name;//VARCHAR2(128 CHAR)企业名称
    private Integer status;//NUMBER(11)办理进度
    private BigDecimal score_value;//NUMBER分数
    private Integer item_id;//NUMBER(11)打分选项ID
    private Date accept_date;//DATE受理日期
    private Date submit_date;//DATE送达日期
    private Date score_date;//DATE打分日期
    private Integer op_user_id;//NUMBER(11)审核人id
    private  String op_user;//VARCHAR2(64 CHAR)审核人
    private Integer op_role_id;//NUMBER(11)审核部门id
    private  String op_role;//VARCHAR2(64 CHAR)审核部门
    private  String score_detail;//VARCHAR2(255 CHAR)打分说明
    private  Date c_time;//DATE创建时间
    private Integer accept_address_id;//NUMBER(11)

    private  String toreviewreason;// 申请人申请复核的理由
    private  Date toreviewtime;//申请人申请复核的时间
    private Integer idreviewend;// 申请复核是否完毕，1：结束
    private String scoreReason;

    public String getScoreReason() {
        return scoreReason;
    }

    public void setScoreReason(String scoreReason) {
        this.scoreReason = scoreReason;
    }

    public String getToreviewreason() {
        return toreviewreason;
    }

    public void setToreviewreason(String toreviewreason) {
        this.toreviewreason = toreviewreason;
    }

    public Date getToreviewtime() {
        return toreviewtime;
    }

    public void setToreviewtime(Date toreviewtime) {
        this.toreviewtime = toreviewtime;
    }

    public Integer getIdreviewend() {
        return idreviewend;
    }

    public void setIdreviewend(Integer idreviewend) {
        this.idreviewend = idreviewend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccept_number() {
        return accept_number;
    }

    public void setAccept_number(String accept_number) {
        this.accept_number = accept_number;
    }

    public Integer getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Integer batch_id) {
        this.batch_id = batch_id;
    }

    public Integer getIndicator_id() {
        return indicator_id;
    }

    public void setIndicator_id(Integer indicator_id) {
        this.indicator_id = indicator_id;
    }

    public String getIndicator_name() {
        return indicator_name;
    }

    public void setIndicator_name(String indicator_name) {
        this.indicator_name = indicator_name;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_id_num() {
        return person_id_num;
    }

    public void setPerson_id_num(String person_id_num) {
        this.person_id_num = person_id_num;
    }

    public String getPerson_mobile_phone() {
        return person_mobile_phone;
    }

    public void setPerson_mobile_phone(String person_mobile_phone) {
        this.person_mobile_phone = person_mobile_phone;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getScore_value() {
        return score_value;
    }

    public void setScore_value(BigDecimal score_value) {
        this.score_value = score_value;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Date getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(Date accept_date) {
        this.accept_date = accept_date;
    }

    public Date getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(Date submit_date) {
        this.submit_date = submit_date;
    }

    public Date getScore_date() {
        return score_date;
    }

    public void setScore_date(Date score_date) {
        this.score_date = score_date;
    }

    public Integer getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(Integer op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getOp_user() {
        return op_user;
    }

    public void setOp_user(String op_user) {
        this.op_user = op_user;
    }

    public Integer getOp_role_id() {
        return op_role_id;
    }

    public void setOp_role_id(Integer op_role_id) {
        this.op_role_id = op_role_id;
    }

    public String getOp_role() {
        return op_role;
    }

    public void setOp_role(String op_role) {
        this.op_role = op_role;
    }

    public String getScore_detail() {
        return score_detail;
    }

    public void setScore_detail(String score_detail) {
        this.score_detail = score_detail;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public Integer getAccept_address_id() {
        return accept_address_id;
    }

    public void setAccept_address_id(Integer accept_address_id) {
        this.accept_address_id = accept_address_id;
    }
}
