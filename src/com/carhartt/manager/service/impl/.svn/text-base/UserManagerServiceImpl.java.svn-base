//package com.carhartt.manager.service.impl;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import com.carhartt.man.dao.UserDAO;
//import com.carhartt.man.model.Broadclass;
//import com.carhartt.man.model.User;
//import com.carhartt.manager.service.UserManagerService;
//
//public class UserManagerServiceImpl implements UserManagerService{
//	private UserDAO userDao;
//	public UserDAO getUserDao() {
//		return userDao;
//	}
//
//	public void setUserDao(UserDAO userDao) {
//		this.userDao = userDao;
//	}
//
//	public User getUserInfo(User user) {
//		// TODO Auto-generated method stub
//		User instance = userDao.findById(user.getUserId());
//		return instance;
//		
//	}
//	
//	public List getUserList(final int startPosition , final int length){
//		Iterator iter=userDao.getPosterList(startPosition , length).iterator();
//		List<Broadclass> bcList = new ArrayList<Broadclass>();
//		while(iter.hasNext()){
//			Broadclass bc = (Broadclass)iter.next();
//			bcList.add(bc);
//		}
//		return bcList;
//	}
//
//	public void FreezeAccount(Integer user_id) {
//		User user = userDao.findById(user_id);
//		user.setUserState((short) 1);
//		userDao.save(user);
//		// TODO Auto-generated method stub
//	}
//
//
//}
