package com.ymy.xxb.constant;

public enum ErrorEnum {
	FAILED_ACCESSTOKEN("accessToken验证是失败" , 1000),
	FAILED_CHARSET("编码验证失败", 2001),
	FAILED_FORMAT("格式验证失败", 2002),
	FAILED_SIGNTYPE("签名类型验证失败", 2003),
	FAILED_VERSION("版本验证失败", 2004),
	FAILED_MERCH_APPID("商户APPID验证失败", 3001),
	FAILED_MARCH_SECRETKEY("商户秘钥验证失败",3002),
	FAILED_TIMESTAMP("时间戳验证失败", 4001),
	FAILED_SALT("盐值验证失败", 4002),
	FAILED_BIZCONTENT("交易内容验证失败", 4002),
	FAILED_SIGN("签名验证失败" , 4003);
	
	private String errDesc;
	
	private int errCode;
	
	private ErrorEnum(String errDesc , int errCode) {
		this.errDesc = errDesc;
		this.errCode = errCode;
	}
	
	public static String getErrDescName(int errCode) {
		for(ErrorEnum v : ErrorEnum.values()) {
			if(v.getErrCode() == errCode) {
				return v.getErrDesc();
			}
		}
		return null;
	}

	public static int getErrCodeName(String errDesc) {
		for(ErrorEnum v : ErrorEnum.values()) {
			if(v.getErrDesc().equals(errDesc)) {
				return v.getErrCode();
			}
		}
		return 0;
	}
	
	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	
	
}
