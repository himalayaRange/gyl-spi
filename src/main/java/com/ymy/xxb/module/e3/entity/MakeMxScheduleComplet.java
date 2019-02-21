package com.ymy.xxb.module.e3.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 完工单
 * 
 * created by wangyi.
 */
public class MakeMxScheduleComplet implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
     * 表ID
     */
    private Long id;

    /**
     * 完工单号
     */
    private String wgNo;

    /**
     * 制造单号
     */
    private String makeNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 款号
     */
    private String kh;

    /**
     * 色号
     */
    private String colorCode;

    /**
     * 缸差
     */
    private String gc;

    /**
     * XS
     */
    private Integer xsNum;

    /**
     * S
     */
    private Integer sNum;

    /**
     * M
     */
    private Integer mNum;

    /**
     * L
     */
    private Integer lNum;

    /**
     * XL
     */
    private Integer xlNum;

    /**
     * 合计
     */
    private Integer totalNum;

    /**
     * 完工日期
     */
    private Date completTime;

    /**
     * 中台状态  0：待同步  1：同步成功 2：同步失败
     */
    private String ztStatus;

    /**
     * 中台同步日期
     */
    private Date ztSynchronizedTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyUser;
    
    private String s_size;
    
    private String m_size;
    
    private String l_size;
    
    /**
     * 0：自制，制造排期单生成完工单   1：外购，合同生成完工单
     */
    private String isType;
    
    /**
     * 审核状态 1：已审核  0：未审核
     */
    private String auditStatus;
    
    /**
     * 仓库性质
     */
    private String entrepotNature;
    
    /**
     * 备注，用于成衣
     */
    private String memon;
    
    private String contract_no;
    
    /**
     * 排期单 make_mx_schedule ID
     */
    private String scheduleId;
    
    
	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getMemon() {
		return memon;
	}

	public void setMemon(String memon) {
		this.memon = memon;
	}

	public String getEntrepotNature() {
		return entrepotNature;
	}

	public void setEntrepotNature(String entrepotNature) {
		this.entrepotNature = entrepotNature;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	public String getS_size() {
		return s_size;
	}

	public void setS_size(String s_size) {
		this.s_size = s_size;
	}

	public String getM_size() {
		return m_size;
	}

	public void setM_size(String m_size) {
		this.m_size = m_size;
	}

	public String getL_size() {
		return l_size;
	}

	public void setL_size(String l_size) {
		this.l_size = l_size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWgNo() {
		return wgNo;
	}

	public void setWgNo(String wgNo) {
		this.wgNo = wgNo;
	}

	public String getMakeNo() {
		return makeNo;
	}

	public void setMakeNo(String makeNo) {
		this.makeNo = makeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getGc() {
		return gc;
	}

	public void setGc(String gc) {
		this.gc = gc;
	}

	public Integer getXsNum() {
		return xsNum;
	}

	public void setXsNum(Integer xsNum) {
		this.xsNum = xsNum;
	}

	public Integer getsNum() {
		return sNum;
	}

	public void setsNum(Integer sNum) {
		this.sNum = sNum;
	}

	public Integer getmNum() {
		return mNum;
	}

	public void setmNum(Integer mNum) {
		this.mNum = mNum;
	}

	public Integer getlNum() {
		return lNum;
	}

	public void setlNum(Integer lNum) {
		this.lNum = lNum;
	}

	public Integer getXlNum() {
		return xlNum;
	}

	public void setXlNum(Integer xlNum) {
		this.xlNum = xlNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Date getCompletTime() {
		return completTime;
	}

	public void setCompletTime(Date completTime) {
		this.completTime = completTime;
	}

	public String getZtStatus() {
		return ztStatus;
	}

	public void setZtStatus(String ztStatus) {
		this.ztStatus = ztStatus;
	}

	public Date getZtSynchronizedTime() {
		return ztSynchronizedTime;
	}

	public void setZtSynchronizedTime(Date ztSynchronizedTime) {
		this.ztSynchronizedTime = ztSynchronizedTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
    
}