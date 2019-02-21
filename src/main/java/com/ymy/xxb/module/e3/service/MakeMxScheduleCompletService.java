package com.ymy.xxb.module.e3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymy.xxb.module.e3.entity.MakeMxScheduleComplet;
import com.ymy.xxb.module.e3.mapper.MakeMxScheduleCompletMapper;

@Service
public class MakeMxScheduleCompletService {
	
	@Autowired
	MakeMxScheduleCompletMapper makeMxScheduleCompletMapper;
	
	public MakeMxScheduleComplet selectByWgNo(String wgNo) {
		return makeMxScheduleCompletMapper.selectByWgNo(wgNo);
	}

	public Integer updateAuditStatus(Long id) {
		return makeMxScheduleCompletMapper.updateAuditStatus(id);
	}
}
