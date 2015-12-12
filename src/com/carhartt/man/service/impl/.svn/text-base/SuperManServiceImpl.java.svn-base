package com.carhartt.man.service.impl;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.carhartt.man.model.Manager;

import com.carhartt.man.dao.ManagerDAO;

import com.carhartt.man.service.SuperManService;

public class SuperManServiceImpl implements SuperManService{
	
	private ManagerDAO managerDAO;
	
	public ManagerDAO getManagerDAO() {
		return managerDAO;
	}

	public void setManagerDAO(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}
	public JSONObject searchSuper(int startPosition , int limit ) {
		JSONObject data = new JSONObject();
		JSONArray managerList = new JSONArray();
		List manList = managerDAO.getManagerList();
		int count = manList.size();
		int end = count;
		if(startPosition >= count) {
			data.put("list", managerList);
			data.put("count", count);
			return data;
		}
		if(startPosition + limit < end){
			end = startPosition + limit;
		}
		Iterator iter=manList.subList(startPosition, end).iterator();
		while(iter.hasNext()){
			Manager manager = (Manager)iter.next();
			// 添加传到客户端的信息
			data.put("managerId", manager.getManagerId());
			data.put("managerName", manager.getManagerName());
			data.put("managerPass", manager.getManagerPass());
			data.put("managerType", manager.getManagerType());
			data.put("employeeName", manager.getEmployeeName());
			data.put("remark", manager.getRemark());
			managerList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", managerList);
		obj.put("count", count);
		return obj;
	}

	public boolean superManagerInfoModify(int managerId, String managerName,
			String managerPass, short managerType, String employeeName,
			String remark) {
		// TODO Auto-generated method stub
		Manager manager = null;
		manager = managerDAO.findById(managerId);
		if(manager == null) return false;
		manager.setEmployeeName(employeeName);
		manager.setManagerName(managerName);
		manager.setManagerPass(managerPass);
		manager.setManagerType(managerType);
		manager.setRemark(remark);
		managerDAO.attachDirty(manager);
		return true;
	}

	public boolean deleteManagerById(int managerId) {
		// TODO Auto-generated method stub
		Manager manager = null;
		manager = managerDAO.findById(managerId);
		if(manager == null) return false;
//		if(managerName != manager.getManagerName()) return false;
//		if(managerPass != manager.getManagerPass()) return false;
		managerDAO.delete(manager);
		return true;
	}

	public boolean addManager(String managerName, String managerPass,
			short managerType, String employeeName, String remark) {
		// TODO Auto-generated method stub
		if(managerDAO.findByManagerName(managerName).size() != 0){
			return false;
		}
		Manager manager = new Manager();
		manager.setEmployeeName(employeeName);
		manager.setManagerName(managerName);
		manager.setManagerPass(managerPass);
		manager.setManagerType(managerType);
		manager.setRemark(remark);
		managerDAO.save(manager);
		return true;
	}

//	public int getTotalCount() {
//		// TODO Auto-generated method stub
//		return managerDAO.getAllRecordNum();
//	}
	
}
