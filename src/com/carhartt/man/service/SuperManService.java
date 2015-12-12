package com.carhartt.man.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface SuperManService {
	public JSONObject searchSuper(int startPosition , int length);
	public boolean superManagerInfoModify(int managerId, String managerName,
			String managerPass, short managerType, String employeeName, String remark);
	public boolean addManager(String managerName,
			String managerPass, short managerType, String employeeName, String remark);
	public boolean deleteManagerById(int managerId);
//	public int getTotalCount();
}
