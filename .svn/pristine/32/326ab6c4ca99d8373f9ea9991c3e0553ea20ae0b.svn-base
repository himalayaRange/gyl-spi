package com.ymy.xxb.module;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.base.Objects;
import com.ymy.xxb.helper.JwtUtil;
import com.ymy.xxb.module.e3.controller.ext.BasicController;
import com.ymy.xxb.vo.AccessToken;

@Controller
public class IndexController extends BasicController{
	@Autowired
	private AccessToken accessToken;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/index")
    public String index(Model model){
    	logger.info("-------------供应链 SPI 首页-----------");
    	
    	model.addAttribute("version", "1.4.3.RELEASE");
    	return "index";
    }
    
    @ResponseBody
    @RequestMapping(value = "/passport/platform/getAccessToken" , method = RequestMethod.POST)
    public void accessToken(HttpServletRequest request , HttpServletResponse response) {
    	bizContentMap = requestContext(request);
    	Object accountObject = bizContentMap.get("account");
		Object passwordObject = bizContentMap.get("password");
    	if(accountObject == null || passwordObject == null ) {
    		doResult(response, false, null, "账户或密码不正确！");
    	} else {
    		Map<String, String> mergeAccount = mergeAccount();
        	String passport = mergeAccount.get(accountObject.toString());
        	if(passport == null || "".equals(passport)) {
        		doResult(response, false, null, "当前账户未授权，请联系网站管理员！");
        	}
        	try {
    			String passportBase64String = Base64.encodeBase64String(passport.getBytes("UTF-8"));
    			if(Objects.equal(passwordObject.toString(), passportBase64String)) {
    				accessToken.setUserId("1");
    				accessToken.setUserName("ymy.xxb.devloper");
    				accessToken.setRoleId("1");
    				accessToken.setRoleName("supAdmin");
    				String token = JwtUtil.createJWT(accessToken);
    				doResult(response, true, token, "请求成功！");
    			} else {
    				doResult(response, false, null, "您的账户或密码错误，请稍后再试！");
    			}
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    			doResult(response, false, null, "系统出小差啦，请稍后再试！");
    		}
    	}
    }
    
    /**
     * 合并账户
     * @return
     */
    public Map<String,String> mergeAccount() {
    	Map<String,String> totalAccount = new HashMap<String,String>();
    	// .配置文件中账户信息
    	String merchAccounts = accessToken.getMerchAccounts();
    	Map<String,String> accountOrignProperties = AccessToken.getMerchAccounts(merchAccounts);
    	totalAccount.putAll(accountOrignProperties);
    	// .加入数据库中账户信息，并进行合并  TODO
    	
    	return totalAccount;
    }
    
}
