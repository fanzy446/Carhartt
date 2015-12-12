package com.carhartt.man.service.impl;

import java.util.List;

import com.carhartt.man.dao.ManagerDAO;
import com.carhartt.man.model.Manager;
import com.carhartt.man.service.BasicManService;

public class BasicManServiceImpl implements BasicManService{
	private ManagerDAO managerDAO;
	
	public int UserLogin(String userName, String password, int userType) {
		// TODO Auto-generated method stub
		List<Manager> managerlist = managerDAO.findByManagerName(userName);
		if (managerlist.size()>0){
			Manager manager = managerlist.get(0);
			if(manager.getManagerPass().equals(password)
					&& manager.getManagerType() == userType){
				return manager.getManagerId();
			}
		}
		return 0;
	}

	public ManagerDAO getManagerDAO() {
		return managerDAO;
	}

	public void setManagerDAO(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}
	
}
