package com.ymy.xxb.module.e3.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ymy.xxb.module.ModuleConstant;
import com.ymy.xxb.module.e3.bean.E3ConfigurationBean;
import com.ymy.xxb.module.e3.controller.ext.BasicController;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
@Controller
@RequestMapping(ModuleConstant.API_MODULE_E3_PREFIX)
public class CompletionOrderPushController extends BasicController{
		@Autowired
		private E3ConfigurationBean e3ConfigurationBean;
		
		private static final String API_CONTR_PREFIX = "/completionOrder/push";

		private static final Logger logger = LoggerFactory.getLogger(CompletionOrderPushController.class);
		
		private static final MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
	
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
    	/**
		 * 获取中台AccessToken
		 * @return
    	 * @throws UnsupportedEncodingException 
		 */
		@ResponseBody
		@RequestMapping(value = API_CONTR_PREFIX + "/getAccessToken" , method = RequestMethod.POST)
		public void getAccessToken(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
			Map<String,String> httpRequestMap = Maps.newHashMap();
			// 构建公共参数
			Map<String, Object> buildCommonParamter = buildCommonParamter();
			// 构建差异参数
			Map<String, Object> buildTokenParamter = buildTokenParamter();
			//String dataContent = "[{\"password\":\"8888\",\"username\":\"admin\"}]";
			String dataContent = "[" + JSON.toJSONString(buildTokenParamter) + "]";
			String date = buildCommonParamter.get("date").toString();
			int number = Integer.valueOf(buildCommonParamter.get("number").toString());
			// 获取的签名
			String sign = sign(e3ConfigurationBean.getPrivateKey(), e3ConfigurationBean.getAk(), e3ConfigurationBean.getPk(), number , date , dataContent);
			// 封装Http请求参数
			httpRequestMap.put("ak", e3ConfigurationBean.getAk());
			httpRequestMap.put("pk", e3ConfigurationBean.getPk());
			httpRequestMap.put("number", String.valueOf(number));
			httpRequestMap.put("date", date);
			httpRequestMap.put("sign", sign);
			httpRequestMap.put("dataContent", dataContent);
			httpRequestMap.put("token", "");
			String result = okHttp3Post(e3ConfigurationBean.getTokenURL(), JSON.toJSONString(httpRequestMap));
			doResult(response, true, result, "请求成功！");
		}
		
		/**
		 * 采购入库通知单新增
		 * @param request
		 * @param response
		 */
		@SuppressWarnings("unchecked")
		@ResponseBody
		@RequestMapping(value = API_CONTR_PREFIX + "/createPurchaseInAsnBill" , method = RequestMethod.POST)
		public void CreatePurchaseInAsnBill(HttpServletRequest request , HttpServletResponse response) {
			bizContentMap = this.requestContext(request);
			Map<String,Object> httpRequestMap = Maps.newHashMap();
			List<Map<String,Object>> transDataDetail = Lists.newArrayList();
			String e3Token = "";
			String billDate = "";
			String supplierCode = "";
			String wareHouseCode = "";
			String sourceBillNo = "";
			Object billDateObject = bizContentMap.get("billDate"); // 业务日期，完工单的审核时间
			Object supplierCodeObject = bizContentMap.get("supplierCode"); // 供应商代码
			Object wareHouseCodeObject = bizContentMap.get("wareHouseCode"); // 仓库代码
			Object transDataObject = bizContentMap.get("transData");
			Object sourceBillNoObject = bizContentMap.get("sourceBillNo");// 完工单号
			Object token = bizContentMap.get("token");
			if(billDateObject != null) {
				billDate = billDateObject.toString();
			}
			if(supplierCodeObject != null) {
				supplierCode = supplierCodeObject.toString();
			}
			if(wareHouseCodeObject != null) {
				wareHouseCode = wareHouseCodeObject.toString();
			}
			if(transDataObject != null) {
				transDataDetail = (List<Map<String,Object>>)transDataObject;
			}
			if(token != null) {
				e3Token = token.toString();
			}
			if(sourceBillNoObject != null) {
				sourceBillNo = sourceBillNoObject.toString();
			}
			// 构建公共参数
			Map<String, Object> buildCommonParamter = buildCommonParamter();
			// 构建差异参数
			Map<String, Object> buildCreatePurchaseInAsnBillParamter = buildCreatePurchaseInAsnBillParamter(billDate , supplierCode ,wareHouseCode , sourceBillNo ,transDataDetail);
			List<Map<String,Object>> paramArray = new ArrayList<Map<String,Object>>();
			paramArray.add(buildCreatePurchaseInAsnBillParamter);
			String dataContent = JSON.toJSONString(paramArray);
		    //String dataContent = "[" + JSON.toJSONString(buildCreatePurchaseInAsnBillParamter) +"]";
			// 获取的签名
			int number = Integer.valueOf(buildCommonParamter.get("number").toString());
			String date = buildCommonParamter.get("date").toString();
			String sign = sign(e3ConfigurationBean.getPrivateKey(), e3ConfigurationBean.getAk(), e3ConfigurationBean.getPk(), number, date, dataContent);
			// 封装Http请求参数
			httpRequestMap.put("ak", e3ConfigurationBean.getAk());
			httpRequestMap.put("pk", e3ConfigurationBean.getPk());
			httpRequestMap.put("number", String.valueOf(number));
			httpRequestMap.put("date", date);
			httpRequestMap.put("sign", sign);
			httpRequestMap.put("dataContent", dataContent);
			httpRequestMap.put("token", e3Token);
			String requestJson = JSON.toJSONString(httpRequestMap);
			String result = okHttp3Post(e3ConfigurationBean.getCreatepurchaseasnbillURL(), requestJson);
			
			doResult(response, true, result, "请求成功！");
		}
		
		
	   /**
	    * 签名原文
	    * @param ak
	    * @param pk
	    * @param number
	    * @param date
	    * @param data
	    * @return
	    */
	   public String orgSignSrc(String ak , String pk , int number , String date , String data) {
		   StringBuffer sb = new StringBuffer();
		   sb = sb.append("AK=").append(ak)
				.append("&PK=").append(pk)
				.append("&Number=").append(number)
				.append("&Date=").append(date)
				.append("&Data=").append(data);
		   
		   return sb.toString();
	   }
	   
	   /**
	    * 构建获取Token登录参数
	    * @param paramter
	    * @return
	 * @throws UnsupportedEncodingException 
	    */
	   public Map<String,Object> buildTokenParamter() throws UnsupportedEncodingException {
		   Map<String,Object> userInfoMap = Maps.newHashMap();
		   userInfoMap.put("username", e3ConfigurationBean.getUsername());
		   userInfoMap.put("password", e3ConfigurationBean.getPassword() == null ?  "" : Base64.encodeBase64String(e3ConfigurationBean.getPassword().getBytes("UTF-8")));
		   return userInfoMap;
	   }
	   
	   /**
		 * 构建创建下单的参数
		 * @param paramter
		 * @return
		 */
		public Map<String,Object> buildCreatePurchaseInAsnBillParamter(String makeMxCompleteBillDate , String supplierCode , String wareHouseCode , String sourceBillNo ,List<Map<String,Object>> transDataDetail) {
			 Map<String,Object> paramter = new ConcurrentHashMap<String, Object>();
			 // 业务日期，取完工单的创建时间
			 String billDate = makeMxCompleteBillDate;
			 String discount = "1.0";
			 String status = "0";
			 // 采购组织代码
			 String channelCode = "101";
			 // 供应商代码
			 //String supplierCode = ""; 
			 // 仓库代码
			 //String wareHouseCode = "";
			 // 库区代码
			 String whareaTypeCode = "000";
			 // 库存组织代码
			 String stockChannelCode = "101";
			 // 购结算组织代码
			 String purSettleChannelCode = "101";
			 String ownertype = "0";
			 // 业务性质代码，JX001是测试环境，正式需要修改
			 //String businessTypeCode = "300179";
			 String businessTypeCode = "JX001";
			 // 本位币代码
			 String baseCurrencyCode = "101";
			 // 币别代码
			 String currencyTypeCode = "101";
			 // 购货主代码
			 String purOwnerCode = "101";
			 // 创建组织代码
			 String createChannelCode = "101";
			 // 业务类型代码
			 String bsTypeCode = "101";
			 // 单据状态
			 String downBillStatus = "2";
			 // 详情JSON
			 //String details = JSON.toJSONString(transDataDetail);
			 // 封装请求参数
			 paramter.put("sourceBillNo", sourceBillNo); 
			 paramter.put("billDate", billDate);
			 paramter.put("discount", discount);
			 paramter.put("status", status);
			 paramter.put("channelCode", channelCode);
			 paramter.put("supplierCode", supplierCode);
			 paramter.put("wareHouseCode", wareHouseCode);
			 paramter.put("whareaTypeCode", whareaTypeCode);
			 paramter.put("stockChannelCode", stockChannelCode);
			 paramter.put("purSettleChannelCode", purSettleChannelCode);
			 paramter.put("ownertype", ownertype);
			 paramter.put("businessTypeCode", businessTypeCode);
			 paramter.put("baseCurrencyCode", baseCurrencyCode);
			 paramter.put("currencyTypeCode", currencyTypeCode);
			 paramter.put("purOwnerCode", purOwnerCode);
			 paramter.put("createChannelCode", createChannelCode);
			 paramter.put("bsTypeCode", bsTypeCode);
			 paramter.put("downBillStatus", downBillStatus) ;// 单据状态，默认直接审核了
			 paramter.put("details", transDataDetail);
			return paramter;
		}
	   
		/**
		 * 构建公共参数
		 * @return
		 */
		public Map<String,Object> buildCommonParamter() {
			Map<String,Object> commonParamter = new ConcurrentHashMap<String,Object>();
			int number = (int)((Math.random()*9+1)*1000);
			String date = sdf.format(new Date());
			commonParamter.put("number", number);
			commonParamter.put("date", date);
			return commonParamter;
		}
		
		/**
		 * MD5WithRSA签名
		 * @param privateKey
		 * @param publicKey
		 * @param pk
		 * @param number
		 * @param date
		 * @param otherParam
		 * @return
		 */
	   public String sign(String privateKey, String publicKey, String pk, int number, String date, String otherParam) {
		    logger.info("AK:" + publicKey);
		    logger.info("PK:" + pk);
		    String signSrc = orgSignSrc(publicKey, pk, number, date, otherParam);
		    byte[] privateByte = Base64.decodeBase64(privateKey);
		    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateByte);
		    try {
		      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		      PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		      Signature signature = Signature.getInstance("MD5withRSA");
		      signature.initSign(privateK);
		      signature.update(signSrc.getBytes());
		      return Base64.encodeBase64String(signature.sign());
		    } catch (Exception ex) {
		      logger.error("sign exception : " + ex.getMessage());
		    }
		    return null;
		  }
	
	   /**
	    * 获取尺码编码
	    * @param size
	    * @return
	    */
	   public String getSizeCode(String size) {
		   if("S".equals(size)) {
			   return "001";
		   } else if("M".equals(size)) {
			   return "002";
		   } else if("L".equals(size)) {
			   return "003";
		   } else if("XL".equals(size)) {
			   return "004";
		   } else if("XXL".equals(size)) {
			   return "005";
		   } else if("XXXL".equals(size)) {
			   return "006";
		   } else if("XS".equals(size)) {
			   return "007";
		   } else {
			   return "";
		   }
	   }
	   
	   public String okHttp3Post(String url , String json ) {
		   OkHttpClient client = new OkHttpClient.Builder()
			        .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
			        .readTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
			        .writeTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
			        .build();
		   RequestBody body = RequestBody.create(mediaType, json);
		   Request request = new Request.Builder().url(url).post(body).build();
		   Response execute;
			try {
				execute = client.newCall(request).execute();
				return execute.body().string();
			} catch (IOException e) {
				e.printStackTrace();
			 }
			return null;
	   }

}
