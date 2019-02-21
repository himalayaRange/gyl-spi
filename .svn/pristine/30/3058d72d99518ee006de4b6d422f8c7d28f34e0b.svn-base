package com.ymy.xxb.module.e3.mapper;

import org.springframework.stereotype.Repository;
import com.ymy.xxb.module.e3.entity.MakeMxScheduleComplet;

@Repository
public interface MakeMxScheduleCompletMapper {
	
	/**
	 * 根据完工单号查询完工单信息
	 * @param wgNo
	 * @return
	 */
	public MakeMxScheduleComplet selectByWgNo(String wgNo);
	
	/**
	 * 根据ID更新完工单未审核
	 * @param id
	 * @return
	 */
	public Integer updateAuditStatus(Long id);
	
}
