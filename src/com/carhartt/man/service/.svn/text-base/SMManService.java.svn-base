package com.carhartt.man.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.model.Advertisement;
import com.carhartt.man.model.Announcement;

public interface SMManService {
	public boolean deleteAnnouncement(Integer id);
	public boolean advertisementModifyOrUpdate(Advertisement adm);
	
	public JSONObject getAdvertisementList(final int start, final int limit);
	public JSONObject getAnnouncementList(final int start, final int limit);
	public JSONObject getMessageList(final int start, final int limit);
	public JSONObject searchMsg(Integer userId, String mesgTitle, int mesgType, String remark, int isReply, int start, int limit);  //多条件查找
	public JSONObject searchAdv(Integer id, short state, short kind, int start, int limit);
	public JSONObject searchAdp(Integer adpId, short kind, int start, int limit);
	public boolean deleteMessage(Integer id);
	public boolean deleteMsg(Integer id);  //根据id删除记录
	public boolean deleteAnn(Integer id);
	
	public JSONObject findMsgbyId(Integer id);  //根据id查找
	public JSONObject findAnnbyId(Integer id);
	
	public boolean alterFeedbackById(Integer id, String state); //修改反馈信息的状态
	public boolean annModify(Integer id, Integer managerId, String title, String content,
			String remark);
	public boolean MsgModify(Integer id, String title, String content, String remark);
	public boolean advModify(Integer advId, Integer posId, Integer adinadpId, String startTime, String endTime, String adphoto, String url, short state);
	public boolean adpModify(Integer id, Float price, Short kind, Float ratio, Short position);
	//添加回复
	public boolean addNotification(Integer messageId, String notifiedTitle, String notifiedContent, int sender, int receiver);
	//添加广告
	public int addAdvertisement(String startTime, String endTime, String url, int adpostionId, int state);
	//修改广告
	public int ModifyAdvertisement(String startTime, String endTime, String url,
			int advertisementId, int adpostionId, int adinadpid, int state);
}
