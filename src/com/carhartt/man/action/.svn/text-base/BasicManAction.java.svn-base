package com.carhartt.man.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.carhartt.man.config.CarharttConfig;
import com.carhartt.man.service.BasicManService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BasicManAction extends ActionSupport {

	Logger logger = Logger.getLogger(this.getClass());
	private BasicManService basicManService;
	private String managerName;
	private String password;
	private int userType;
	private Map<String, Object> jsonMap;

	public String UserLogin() {
		jsonMap.clear();
		jsonMap.put("success", false);
		logger.info("managerName:" + managerName + "Login to Server!");
		int managerId = basicManService.UserLogin(managerName, CarharttConfig.Md5(password),
				userType);
		if (managerId != 0) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if (session != null) {
				session.setAttribute(CarharttConfig.USERNAME, managerName);
				session.setAttribute(CarharttConfig.USERID, managerId);
				session.setMaxInactiveInterval(24 * 60 * 60);
			} else {
				jsonMap.put("success", false);
				return SUCCESS;
			}
			jsonMap.put("success", true);
			if (userType == 0) {
				return CarharttConfig.SUPER_MANAGER;
			} else if (userType == 1) {
				return CarharttConfig.COM_MANAGER;
			} else if (userType == 2) {
				return CarharttConfig.ORDER_MANAGER;
			} else if (userType == 3) {
				return CarharttConfig.SYN_MANAGER;
			} else if (userType == 4) {
				return CarharttConfig.USER_MANAGER;
			}
		}
		return ERROR;
	}
	
	public String UserLogout() {
		jsonMap.clear();
		jsonMap.put("success", true);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userName = (String) session
				.getAttribute(CarharttConfig.USERNAME);
		logger.info("userName:" + userName + "logout to Server!");
		jsonMap.put("userName", userName);
		session.removeAttribute(CarharttConfig.USERNAME);
		session.removeAttribute(CarharttConfig.USERID);
		session.invalidate();
		return SUCCESS;
	}

	public BasicManAction() {
		jsonMap = new HashMap();
	}

	public void setBasicManService(BasicManService basicManService) {
		this.basicManService = basicManService;
	}

	public BasicManService getBasicManService() {
		return basicManService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}