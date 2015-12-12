package com.carhartt.man.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.model.User;

public interface UserManagerService{
	public JSONObject searchUsers(Integer id, String Email, short state, int start, int limit);
	public JSONObject getUserInfo(int userId);
	public boolean userInfoModify(Integer id, short role, String remark);
	public boolean FreezeAccount(Integer user_id, int type);
//	public int getRecordCount();
	public JSONObject getUserList(final int startPosition , final int length);
}
