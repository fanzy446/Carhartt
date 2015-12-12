package com.carhartt.man.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.carhartt.man.service.UserManagerService;
import com.opensymphony.xwork2.ActionSupport;

public class UserManAction extends ActionSupport {

	Logger logger = Logger.getLogger(this.getClass());
	private UserManagerService userManagerService;
	private int start;
	private int limit;
	private String userId;
	private String userEmail;
	private int userRole;
	private String remark;
	private int userState;

	private int type;

	private Map<String, Object> jsonMap;

	public UserManAction() {
		jsonMap = new HashMap();
	}

	public String searchUser() {
		jsonMap.clear();
		jsonMap.put("success", true);
		logger.info("start:" + start + "limit:" + limit + "userId:" + userId
				+ "userEmail:" + userEmail + "userState:" + userState);
		if(userId == null || userId.equals("")){
			userId=new String("0");
		}
		JSONObject userlist = userManagerService.searchUsers(Integer.valueOf(userId), 
				userEmail,(short)userState, start, limit);
		jsonMap.put("users", userlist.get("list"));
		jsonMap.put("totalCount", userlist.get("count"));
		return SUCCESS;
	}

	public String FrozenUserById() {
		jsonMap.clear();
		jsonMap.put("success", false);
		if (userManagerService.FreezeAccount(Integer.valueOf(userId), Integer
				.valueOf(type))) {
			jsonMap.put("success", true);
		}
		return SUCCESS;
	}

	public String getUserDetialsById() {
		jsonMap.clear();
		jsonMap.put("success", false);
		logger.info("userId:" + userId);
		JSONObject userdetials = userManagerService.getUserInfo(Integer.valueOf(userId));
		if (userdetials != null) {
			JSONArray jsonarray = new JSONArray();
			jsonarray.add(userdetials);
			jsonMap.put("success", true);
			jsonMap.put("SList", jsonarray);
		}
		return SUCCESS;
	}

	public String UpdateUserInfo() {
		jsonMap.clear();
		jsonMap.put("success", false);
		logger.info("remark:" + remark + "userRole:" + userRole);
		if(userManagerService.userInfoModify(Integer.valueOf(userId), (short)userRole, remark)){
			jsonMap.put("success", true);
		}
		return SUCCESS;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
