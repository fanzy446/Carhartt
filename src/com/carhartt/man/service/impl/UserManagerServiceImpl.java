package com.carhartt.man.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.dao.UserDAO;
import com.carhartt.man.dao.UserinfoDAO;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.User;
import com.carhartt.man.model.Userinfo;
import com.carhartt.man.service.UserManagerService;

public class UserManagerServiceImpl implements UserManagerService{
	private UserDAO userDao;
	private UserinfoDAO userInfoDao;
	private static final Logger log = LoggerFactory.getLogger(UserManagerServiceImpl.class);

	public UserDAO getUserDao() {
		return userDao;
	}

	public UserinfoDAO getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserinfoDAO userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public JSONObject getUserInfo(int userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(userId);
		List<Userinfo> instance = (List<Userinfo>)userInfoDao.findByProperty("user", user);
		if(instance.size() >= 1){
			JSONObject data = new JSONObject();
			data.put("passwHint", instance.get(0).getPasswHint());
			data.put("passAnswer", instance.get(0).getPassAnswer());
			data.put("realName", instance.get(0).getRealName());
			data.put("idCard", instance.get(0).getIdCard());
			data.put("userPhone", instance.get(0).getUserPhone());
			data.put("userGender", instance.get(0).getUserGender());
			data.put("userCredits", instance.get(0).getUserCredits());
			data.put("userId", userId);
			return data;
		}
		return null;
	}
	
	public JSONObject getUserList(final int startPosition , final int length){
		JSONObject data = new JSONObject();
		JSONArray usList = new JSONArray();
		int count = userDao.getAllRecordNum(0, "", (short)-1);
		int end = count;
		if(startPosition >= count) {
			data.put("list", usList);
			data.put("count", count);
			return data;
		}
		if(startPosition + length < end){
			end = startPosition + length;
		}
		List userList = userDao.getUserList(startPosition , end);
		Iterator iter = userList.iterator();
		while(iter.hasNext()){
			User user = (User)iter.next();
			// 添加传到客户端的信息
			data.put("userId", user.getUserId());
			data.put("userName", user.getUserName());
			data.put("userEmail", user.getUserEmail());
			data.put("registerTime", user.getRegisterTime().toString());
			data.put("userRole", user.getUserRole());
			data.put("userState", user.getUserState());
			data.put("remark", user.getRemark());
			usList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", usList);
		obj.put("count", count);
		return obj;
	}

	public JSONObject searchUsers(Integer id, String email, short state, int start, int limit) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray usList = new JSONArray();
		int count = userDao.getAllRecordNum(id, email, state);
		int end = count;
		if(start >= count) {
			data.put("list", usList);
			data.put("count", count);
			return data;
		}
		if(start + limit < end){
			end = start + limit;
		}
		List userList = userDao.searchUsers(id , email, state, start, end);
		Iterator iter = userList.iterator();
		while(iter.hasNext()){
			User user = (User)iter.next();
			// 添加传到客户端的信息
			data.put("userId", user.getUserId());
			data.put("userName", user.getUserName());
			data.put("userEmail", user.getUserEmail());
			data.put("registerTime", user.getRegisterTime().toString());
			data.put("userRole", user.getUserRole());
			data.put("userState", user.getUserState());
			data.put("remark", user.getRemark());
			Iterator iter2 = user.getUserinfos().iterator();
//			List<Userinfo> instance = (List<Userinfo>)userInfoDao.findByProperty("user", user);
			if(iter2.hasNext()){
				Userinfo uif = (Userinfo) iter2.next();
				data.put("passwHint", uif.getPasswHint());
				data.put("passAnswer", uif.getPassAnswer());
				data.put("realName", uif.getRealName());
				data.put("idCard", uif.getIdCard());
				data.put("userPhone", uif.getUserPhone());
				data.put("userGender", uif.getUserGender());
				data.put("userCredits", uif.getUserCredits());
			}
			usList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", usList);
		obj.put("count", count);
		return obj;
	}
	
	
	public boolean FreezeAccount(Integer user_id, int type) {
		User user = userDao.findById(user_id);
		if(user == null){
			return false;
		}
		if(type == 1){
			user.setUserState((short) 2);
		}else{
			user.setUserState((short) 0);
		}
		userDao.attachDirty(user);
		return true;
		// TODO Auto-generated method stub
	}
	
	
	public static void main(String[] agrs){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserDAO userDao =(UserDAO)context.getBean("UserDAO");
		Iterator iter = userDao.getUserList(0, 25).iterator();
		if (iter.hasNext()){
			User user = (User)iter.next();
			System.out.println(user.getUserEmail());
		}else{
			System.out.println("failed");
		}
	}

	public boolean userInfoModify(Integer id, short role, String remark) {
		// TODO Auto-generated method stub
		User user = null;
		user = userDao.findById(id);
		if (user == null) {
			log.debug("userinfo update fail : user isn't exist! ");
			return false;
		}
		user.setUserRole(role);
		user.setRemark(remark);
		userDao.attachDirty(user);
		return true;
	}
	
//	public int getRecordCount(){
//		return userDao.getAllRecordNum();
//	}
	


}
