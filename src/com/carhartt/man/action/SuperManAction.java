package com.carhartt.man.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.config.CarharttConfig;
import com.carhartt.man.service.SuperManService;
import com.opensymphony.xwork2.ActionSupport;

public class SuperManAction extends ActionSupport{
	private SuperManService superManService;
	private Map<String, Object> jsonMap;
	
	Logger logger = Logger.getLogger(this.getClass());
	private String managerId;
	private String managerName;
	private String managerPass;
	private String employeeName;
	private String remark;
	private short managerType;
	
	private int start;
	private int limit;
	
	private String error;
	
	public String searchSuper(){
		jsonMap.clear();
		jsonMap.put("success", true);
		JSONObject managerList = superManService.searchSuper(start, limit);
		jsonMap.put("totalCount", managerList.get("count"));
		jsonMap.put("managerList", managerList.get("list"));
		return SUCCESS;
	}
	
	public String DeleteManagerById(){
		jsonMap.clear();
		jsonMap.put("success", false);
		superManService.deleteManagerById(Integer.valueOf(managerId));
		return SUCCESS;
	}
	
	
	public String AddManager(){
		jsonMap.clear();
		logger.info("remark:" + remark + "managerId:" + managerId+ "managerName:" + managerName+ "managerPass:" + managerPass+ "employeeName:" + employeeName);
		if(managerName == null || managerName.equals("") || managerPass == null || managerPass.equals("")){
			error = "登陆账号和密码不允许为空!";
			jsonMap.put("success", false);
		}else{
			if(superManService.addManager(managerName, CarharttConfig.Md5(managerPass), managerType, employeeName, remark)){
				jsonMap.put("success", true);
				return SUCCESS;
			}else{
				error = "登陆账号已经存在!请重输!";
				jsonMap.put("success", false);
				
			}
		}
		jsonMap.put("error", error);
		return SUCCESS;
	}
	
	public String UpdateManagerInfo(){
		jsonMap.clear();
		jsonMap.put("success", false);
		logger.info("remark:" + remark + "managerId:" + managerId+ "managerName:" + managerName+ "managerPass:" + CarharttConfig.Md5(managerPass)+ "employeeName:" + employeeName);
		if(superManService.superManagerInfoModify(Integer.valueOf(managerId), managerName, CarharttConfig.Md5(managerPass),managerType,employeeName,remark)){
			jsonMap.put("success", true);
		}
		return SUCCESS;
		}
	
	public SuperManAction() {
		jsonMap = new HashMap();
	}

	public SuperManService getSuperManService() {
		return superManService;
	}
	
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPass() {
		return managerPass;
	}

	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public short getManagerType() {
		return managerType;
	}

	public void setManagerType(short managerType) {
		this.managerType = managerType;
	}

	public void setSuperManService(SuperManService superManService) {
		this.superManService = superManService;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
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
	

}
