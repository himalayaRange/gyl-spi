package com.ymy.xxb.module.e3.bean.ext;

import java.io.Serializable;

public class HttpUrlBean implements Serializable {

	private static final long serialVersionUID = 2309833094284403267L;
	/**
	 * 获取Token URL
	 */
	private String tokenURL;

	/**
	 * 新建入库通知单URL
	 */
	public String createpurchaseasnbillURL;
	
	public String getTokenURL() {
		return tokenURL;
	}
	
	
	public String getCreatepurchaseasnbillURL() {
		return createpurchaseasnbillURL;
	}


	public void setCreatepurchaseasnbillURL(String createpurchaseasnbillURL) {
		this.createpurchaseasnbillURL = createpurchaseasnbillURL;
	}


	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}

}
