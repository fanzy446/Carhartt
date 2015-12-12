package com.carhartt.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.carhartt.man.model.User;
import com.carhartt.man.model.Verification;
import com.carhartt.man.service.BasicManService;
import com.carhartt.user.service.UserBasicService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserBasicAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(UserBasicAction.class);
	private UserBasicService userBasicService;
	private User user;
	private Verification verification;
	private int pageNow;
	private int perPage;
	private Map<String, Object> jsonMap = new HashMap<String, Object>();

	public String userLogin() {
		int code = userBasicService.userLogin(user);
		jsonMap.clear();
		jsonMap.put("code", code);
		return Action.SUCCESS;
	}

	public String findBackPass() {
		int code = 0;
		if ((code = userBasicService.findBackPass(user)) == 0) {
			return Action.SUCCESS;
		}
		jsonMap.clear();
		jsonMap.put("code", code);
		return Action.ERROR;
	}

	public String register() {
		int code = userBasicService.register(user);
		jsonMap.clear();
		jsonMap.put("code", code);
		return Action.SUCCESS;
	}

	public String userLogout() {
		userBasicService.userLogout(user);
		return Action.SUCCESS;
	}

	public String certificate() {
		int code = 0;
		if ((code = userBasicService.certificate(verification)) == 0) {
			return Action.SUCCESS;
		}
		jsonMap.clear();
		jsonMap.put("code", code);
		return Action.ERROR;
	}

	public String activate() {
		int code = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		String time = request.getParameter("time");
		String message = request.getParameter("message");
		System.out.println("userId" + userId + ";time" + time + ";message" + message);
		// 解码
		User temp = new User();
		temp.setUserId(Integer.parseInt(userId));
		Verification ver = new Verification();
		ver.setUser(temp);
		ver.setTime(Long.parseLong(time));
		ver.setMessage(message);

		if ((code = userBasicService.activate(ver)) == 0) {
			return Action.SUCCESS;
		}
		jsonMap.clear();
		jsonMap.put("code", code);
		return Action.ERROR;
	}

	public String sendEmail() {
		int code = 0;
		jsonMap.clear();
		if ((code = userBasicService.sendEmail(verification)) == 0) {
			return Action.SUCCESS;
		}
		jsonMap.clear();
		jsonMap.put("code", code);
		return Action.ERROR;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public UserBasicService getUserBasicService() {
		return userBasicService;
	}

	public void setUserBasicService(UserBasicService userBasicService) {
		this.userBasicService = userBasicService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Verification getVerification() {
		return verification;
	}

	public void setVerification(Verification verification) {
		this.verification = verification;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
}
