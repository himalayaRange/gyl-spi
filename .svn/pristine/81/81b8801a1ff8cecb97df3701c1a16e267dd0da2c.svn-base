package com.ymy.xxb.module.e3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.base.Objects;
import com.ymy.xxb.module.ModuleConstant;
import com.ymy.xxb.module.e3.controller.ext.BasicController;
import com.ymy.xxb.module.e3.entity.MakeMxScheduleComplet;
import com.ymy.xxb.module.e3.service.MakeMxScheduleCompletService;
/**
 * E3 推送 到 SCM 
 * @author wangyi
 */
@Controller
@RequestMapping(ModuleConstant.API_MODULE_E3_PREFIX)
public class CompletionOrderPullController extends BasicController{
	private static final Logger logger = LoggerFactory.getLogger(CompletionOrderPullController.class);
	
	private static final String API_CONTR_PREFIX = "/completionOrder/pull";
	
	@Autowired
	private MakeMxScheduleCompletService makeMxScheduleCompletService;
	/**
	 * 撤销功能
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = API_CONTR_PREFIX + "/cancle" , method = RequestMethod.POST)
	public void cancle(HttpServletRequest request , HttpServletResponse response) {
		logger.info("-------- start cancle method ---------");
		// 获取中台传过来的完工单号
		bizContentMap = this.requestContext(request);
		Object object = bizContentMap.get("wgNo");
		if(object == null) {
			doResult(response, false, null, "参数完工单为空！");
		} else {
			MakeMxScheduleComplet msc = makeMxScheduleCompletService.selectByWgNo(object.toString());
			if(msc == null) {
				doResult(response, false, null, "该完工单号在SCM不存在，请联系SCM管理员再执行此操作！");
			} else {
				String auditStatus = msc.getAuditStatus(); // 审核状态
				String ztStatus = msc.getZtStatus(); // 中台同步状态
				if(Objects.equal("1", auditStatus) && Objects.equal("1", ztStatus)) {
					msc.setAuditStatus("0");
					msc.setZtStatus("0");
					Integer result = makeMxScheduleCompletService.updateAuditStatus(msc.getId());
					if(result == 1) {
						doResult(response, true, null, "完工单：" + object.toString() + "撤销成功！");
					} else {
						doResult(response, false, null, "完工单：" + object.toString() + "撤销出现异常！");
					}
				} else {
					doResult(response, false, null, "auditStatus and ztStatus must all equles 1 , 请联系SCM管理员再执行此操作！");
				}
			}
		}
	} 
	
	/**
	 * 退货通知单
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = API_CONTR_PREFIX + "/returnSalesMemo" ,  method = RequestMethod.POST)
	public void returnSalesMemo(HttpServletRequest request , HttpServletResponse response) {
		logger.info("-------- start returnSalesMemo method ---------");
		doResult(response, true, "退货通知单功能测试通过！", "请求成功！");
	}
	
}
