package com.carhartt.man.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.dao.AdinadpDAO;
import com.carhartt.man.dao.AdpositionDAO;
import com.carhartt.man.dao.AdvertisementDAO;
import com.carhartt.man.dao.AnnouncementDAO;
import com.carhartt.man.dao.ItemDAO;
import com.carhartt.man.dao.ManagerDAO;
import com.carhartt.man.dao.MessageDAO;
import com.carhartt.man.dao.NotificationDAO;
import com.carhartt.man.dao.UserDAO;
import com.carhartt.man.model.Adinadp;
import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Advertisement;
import com.carhartt.man.model.Announcement;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.Manager;
import com.carhartt.man.model.Message;
import com.carhartt.man.model.Notification;
import com.carhartt.man.model.User;
import com.carhartt.man.service.SMManService;

public class SMManServiceImpl implements SMManService{
	AnnouncementDAO announcementDao;
	UserDAO userDao;
	AdvertisementDAO advertisementDao;
	MessageDAO msgDao;
	ManagerDAO managerDao;
	ItemDAO itemDao;
	AdinadpDAO adiDao;
	AdpositionDAO adpDao;
	public AdpositionDAO getAdpDao() {
		return adpDao;
	}

	public void setAdpDao(AdpositionDAO adpDao) {
		this.adpDao = adpDao;
	}

	public AdinadpDAO getAdiDao() {
		return adiDao;
	}

	public void setAdiDao(AdinadpDAO adiDao) {
		this.adiDao = adiDao;
	}

	NotificationDAO notificationDao;
	
	public UserDAO getUserDao() {
		return userDao;
	}

	public ItemDAO getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public AnnouncementDAO getAnnouncementDao() {
		return announcementDao;
	}

	public void setAnnouncementDao(AnnouncementDAO announcementDao) {
		this.announcementDao = announcementDao;
	}

	public AdvertisementDAO getAdvertisementDao() {
		return advertisementDao;
	}

	public void setAdvertisementDao(AdvertisementDAO advertisementDao) {
		this.advertisementDao = advertisementDao;
	}

	public MessageDAO getMsgDao() {
		return msgDao;
	}

	public void setMsgDao(MessageDAO msgDao) {
		this.msgDao = msgDao;
	}



	public boolean deleteAnnouncement(Integer id) {
		// TODO Auto-generated method stub
		Announcement ann = null;
		ann = announcementDao.findById(id);
		if (ann == null) return false;
		announcementDao.delete(ann);
		return true;
	}

	public boolean advertisementModifyOrUpdate(Advertisement adm) {
		// TODO Auto-generated method stub
		return false;
	}


	public JSONObject getAdvertisementList(int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject getAnnouncementList(int start, int limit) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray anList = new JSONArray();
		int count = announcementDao.getAllRecordNum();
		int end = count;
		if(start >= count) {
			data.put("list", anList);
			data.put("count", count);
			return data;
		}
		if(start+limit <= count){
			end = start+limit;
		}
		List anListTemp = announcementDao.getAnList(start, end);
		Iterator iter=anListTemp.iterator();
		while(iter.hasNext()){
			Announcement ann = (Announcement)iter.next();
			data.put("announceId", ann.getAnnounceId());
			data.put("announceTitle", ann.getAnnounceTitle());
			data.put("announceContent", ann.getAnnounceContent());
			data.put("announceTime", ann.getAnnounceTime().toString());
			data.put("managerId", ann.getManager().getManagerId());
			data.put("remark", ann.getRemark());
			anList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", anList);
		obj.put("count", count);
		return obj;
	}

	public JSONObject getMessageList(int start, int limit) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray msgList = new JSONArray();
		int count = msgDao.getAllRecordNum();
		int end = count;
		if(start >= count) {
			data.put("list", msgList);
			data.put("count", count);
			return data;
		}
		if(start+limit <= count){
			end = start+limit;
		}
		List msgListTemp = msgDao.getMsgList(start, end);

		Iterator iter=msgListTemp.iterator();
		
		while(iter.hasNext()){
			Message msg = (Message)iter.next();
			data.put("count", count);
			data.put("messageId", msg.getMessageId());
			data.put("userId", msg.getUser().getUserId());
			data.put("mesgType", msg.getMesgType());
			data.put("mesgTitle", msg.getMesgTitle());
			data.put("mesgContent", msg.getMesgContent());
			data.put("mesgTime", msg.getMesgTime().toString());
			data.put("remark", msg.getRemark());
			msgList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", msgList);
		obj.put("count", count);
		return obj;
	}

	public boolean deleteMessage(Integer id) {
		// TODO Auto-generated method stub
		Message msg = null;  // new Message();
//		msg.setMessageId(id);
		msg = msgDao.findById(id);
		if(msg == null) return false;
		msgDao.delete(msg);
		return true;
	}

//	public int getAnnRecordCount() {
//		// TODO Auto-generated method stub
//		return announcementDao.getAllRecordNum();
//	}
//
//	public int getMsgRecordCount(){
//		return msgDao.getAllRecordNum();
//	}

	public boolean deleteMsg(Integer id) {
		// TODO Auto-generated method stub
		Message msg = null;
		msg = msgDao.findById(id);
		if (msg == null) return false;
		msgDao.delete(msg);
		return true;
	}

	public boolean deleteAnn(Integer id) {
		// TODO Auto-generated method stub
		Announcement ann = null;
		ann = announcementDao.findById(id);
		if (ann == null) return false;
		announcementDao.delete(ann);
		return true;
	}

	public JSONObject findMsgbyId(Integer id) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		Message msg = null;
		msg = msgDao.findById(id);
		if (msg == null) return data;
		data.put("messageId", msg.getMessageId());
		data.put("userId", msg.getUser().getUserId());
		data.put("mesgType", msg.getMesgType());
		data.put("mesgTitle", msg.getMesgTitle());
		data.put("mesgContent", msg.getMesgContent());
		data.put("mesgTime", msg.getMesgTime().toString());
		data.put("remark", msg.getRemark());
		return data;
	}

	public JSONObject findAnnbyId(Integer id) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		Announcement ann = null;
		ann = announcementDao.findById(id);
		if(ann == null) return data;
		data.put("announceId", ann.getAnnounceId());
		data.put("managerId", ann.getManager().getManagerId());
		data.put("announceTitle", ann.getAnnounceTitle());
		data.put("announceContent", ann.getAnnounceContent());
		data.put("announceTime", ann.getAnnounceTime().toString());
		data.put("remark", ann.getRemark());
		return data;
	}

	public boolean MsgModify(Integer id, String title, String content,
			String remark) {
		// TODO Auto-generated method stub
		if (id <= 0 || title == null  || title.equals("") || content == null
				|| content.equals("")){
			return false;
		}
		else{
			Message msg = null;
			msg = msgDao.findById(id);
			if (msg == null){
				return false;
			}

			msg.setMesgContent(content);
			msg.setMesgTitle(title);
			msg.setRemark(remark);
			msgDao.attachDirty(msg);
		}
		return true;
	}

	public JSONObject searchMsg(Integer userId, String mesgTitle, int mesgType,
			String remark, int isReply, int start, int limit) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray msgList = new JSONArray();
		User user = null;
		if (userId != 0){
			user = userDao.findById(userId);
			if(user == null){
				data.put("list", msgList);
				data.put("count", 0);
				return data;
			}
		}
		int count = msgDao.getAllRecordNum(user, mesgTitle, mesgType, remark, isReply);
		int end = count;
		if(start >= count) {
			data.put("list", msgList);
			data.put("count", count);
			return data;
		}
		if (start + limit < end){
			end = start + limit;
		}
		List msgListTemp = msgDao.searchMsg(user , mesgTitle, mesgType, remark, isReply, start, end);
		Iterator iter = msgListTemp.iterator();
		while(iter.hasNext()){
			Message msg = (Message)iter.next();
			// 添加传到客户端的信息
			data.put("messageId", msg.getMessageId());
			data.put("userId", msg.getUser().getUserId());
			data.put("mesgType", msg.getMesgType());
			data.put("mesgTitle", msg.getMesgTitle());
			data.put("mesgContent", msg.getMesgContent());
			data.put("mesgTime", msg.getMesgTime().toString());
			data.put("remark", msg.getRemark());
			data.put("isReply", msg.getIsreply());
			msgList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", msgList);
		obj.put("count", count);
		return obj;
	}

	public boolean annModify(Integer id, Integer managerId, String title, String content,
			String remark) {
		// TODO Auto-generated method stub
		if (id < 0 || title.equals("") || title == null || content.equals("") || content == null)
			return false;
		else{
			Announcement ann = null;
			// 修改动作
			if(id != 0){
				ann = announcementDao.findById(id);
			}else{
				//插入动作
				ann = new Announcement();
				Manager manager = managerDao.findById(managerId);
				if(manager == null){
					return false;
				}
				ann.setManager(manager);
				ann.setAnnounceTime(new Timestamp(System.currentTimeMillis()));
			}
			if (ann == null){
				return false;
			}
			ann.setAnnounceContent(content);
			ann.setAnnounceTitle(title);
			ann.setRemark(remark);
			announcementDao.attachDirty(ann);
		}
		return true;
	}
	
	public boolean alterFeedbackById(Integer id, String state) {
		// TODO Auto-generated method stub
		if (state == null || state.equals(""))
			return false;
		else{
			Message mesg = null;
			mesg = msgDao.findById(id);
			if (mesg == null){
				return false;
			}
			mesg.setRemark(state);
			msgDao.attachDirty(mesg);
		}
		return true;
	}

	//添加通知
	public boolean addNotification(Integer messageId, String notifiedTitle,
		String notifiedContent, int senderId, int receiverId) {
		// TODO Auto-generated method stub
		try{
			User receiver = userDao.findById(receiverId);
			Manager sender = managerDao.findById(senderId);
			if(receiver == null || sender == null){
				return false;
			}
			Notification notification = new Notification();
			notification.setNotifiedContent(notifiedContent);
			notification.setNotifiedTitle(notifiedTitle);
			notification.setSender(sender);
			notification.setReceiver(receiver);
			notification.setNotifiedTime(new Timestamp(System.currentTimeMillis()));
			Integer id = notificationDao.save(notification);
			Message msg = msgDao.findById(messageId);
			msg.setIsreply(id.shortValue());
			msgDao.attachDirty(msg);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	
	public ManagerDAO getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDAO managerDao) {
		this.managerDao = managerDao;
	}

	public NotificationDAO getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDAO notificationDao) {
		this.notificationDao = notificationDao;
	}

	public JSONObject searchAdv(Integer id, short state, short kind, int start,
			int limit) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray advList = new JSONArray();
		int count = advertisementDao.getRecordNum(id, state, kind);
		int end = count;
		if(start >= count) {
			data.put("list", advList);
			data.put("count", count);
			return data;
		}
		if (start + limit < end){
			end = start + limit;
		}
		List advListTemp = advertisementDao.searchAdv(id , state, kind, start, end);
		HashSet advSetTemp = new HashSet(advListTemp);
		Iterator iter = advSetTemp.iterator();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		while(iter.hasNext()){
			Advertisement temp = (Advertisement)iter.next();
			// 添加传到客户端的信息
			data.put("advertisementId", temp.getAdvertisementId());
			data.put("startTime", sdf.format(temp.getStartTime()).toString());
			data.put("endTime", sdf.format(temp.getEndTime()).toString());
			if(temp.getAdphoto() != null){
				data.put("adphoto", temp.getAdphoto().toString());
			}
			if(temp.getUrl() != null){
				data.put("url", temp.getUrl());
			}
			Iterator iter2 = temp.getAdinadps().iterator();
			while(iter2.hasNext()){
				Adinadp aip = (Adinadp)iter2.next();
				if(aip.getState() == state || (state >2 ||state < 0)){
					data.put("adpositionId", aip.getAdposition().getAdpositionId());
					data.put("position", aip.getAdposition().getAdpositionId());
					data.put("state", aip.getState());
					data.put("adInadpId", aip.getAdInadpId());
					data.put("kind", aip.getAdposition().getKind());
					advList.add(data);
				}
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("list", advList);
		obj.put("count", count);
		return obj;
	}

	public boolean advModify(Integer advId, Integer posId, String startTime, String endTime,
			String adphoto, String url, short position, short state, short kind) {
		// TODO Auto-generated method stub
		if (advId <= 0 || startTime == null  || startTime.equals("") || endTime == null
				|| endTime.equals("") || adphoto == null  || adphoto.equals("") ||
				url == null || url.equals("")){
				return false;
		}
		else{
			Advertisement adv = null;

			List temp = advertisementDao.searchAAdv(advId, posId, kind);
			if(temp.size() <= 0){
				return false;
			}
			adv = (Advertisement) temp.get(0);
			if (adv == null){
				return false;
			}
			adv.setStartTime(Timestamp.valueOf(startTime));
			adv.setEndTime(Timestamp.valueOf(endTime));
			adv.setAdphoto(adphoto);
			adv.setUrl(url);
			advertisementDao.attachDirty(adv);
			
//			Adinadp aip = new Adinadp();
//			Adposition adp = new Adposition();
//			adp.setKind(kind);
//			adp.setAdpositionId(posId);
//			aip.setAdposition(adp);
//			List temp = adiDao.findByExample(aip);
		}
		return true;
	}

	public JSONObject searchAdp(Integer adpId, short kind, int start, int limit) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		JSONObject data = new JSONObject();
		JSONArray adpList = new JSONArray();
		int count = adpDao.getAllNum(adpId, kind, start, limit);
		int end = count;
		if(start >= count) {
			data.put("list", adpList);
			data.put("count", count);
			return data;
		}
		if (start + limit < end){
			end = start + limit;
		}
		Iterator iter = adpDao.searchAdp(adpId, kind, start, limit).iterator();
		while(iter.hasNext()){
			Adposition temp = (Adposition)iter.next();
			// 添加传到客户端的信息
			data.put("adpositionId",temp.getAdpositionId());
			data.put("price", temp.getPrice());
			data.put("kind", temp.getKind());
			data.put("ratio", temp.getRatio());
			data.put("position", temp.getPosition());
			Iterator iter2 = temp.getAdinadps().iterator();
			while(iter2.hasNext()){
				Adinadp aip = (Adinadp)iter2.next();
				if(aip.getState() == 0){
					data.put("advertisementId", aip.getAdvertisement().getAdvertisementId());
				}
			}
			adpList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", adpList);
		obj.put("count", count);
		return obj;
	}
	
	public boolean adpModify(Integer id, Float price, Short kind, Float ratio,
			Short position) {
		// TODO Auto-generated method stub
		if(id <= 0 || price <0 || kind <0 || kind >3 || ratio < 0 || position < 0){
			return false;
		}else{
			Adposition adp = new Adposition();
			adp.setAdpositionId(id);
			adp.setPrice(price);
			adp.setKind(kind);
			adp.setRatio(ratio);
			adp.setPosition(position);
			adpDao.attachDirty(adp);
		}
		return true;
	}

	public int addAdvertisement(String startTime, String endTime, String url,
			int adpostionId, int state) {
		// TODO Auto-generated method stub
		if(startTime == null || startTime.equals("") || 
				endTime == null || endTime.equals("") ||
				url == null || url.equals("")){
					return 0;
				}
		Adinadp adi = new Adinadp();
		adi.setState((short)state);
		Adposition adp = new Adposition();
		adp.setAdpositionId(adpostionId);
		adi.setAdposition(adp);
		if(state == 0){
			Iterator iter  = adiDao.findByState((short)0).iterator();
//			 判断广告位上是否已有广告挂放.
			while(iter.hasNext()){
				Adinadp temp = (Adinadp)iter.next();
				// 添加传到客户端的信息
				if(temp.getAdposition().getAdpositionId() == adpostionId){
					return -1;
				}
			}
		}
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		Advertisement adv = new Advertisement();
		try {
			d = (Date) f.parseObject(startTime);
			Timestamp start = new Timestamp(d.getTime());
			d = (Date) f.parseObject(endTime);
			Timestamp end = new Timestamp(d.getTime());
			adv.setEndTime(end);
			adv.setStartTime(start);
			adv.setUrl(url);
			advertisementDao.attachDirty(adv);
			adv.setAdphoto(adv.getAdvertisementId().toString());
			advertisementDao.attachDirty(adv);
			adi.setAdvertisement(adv);
			adiDao.attachDirty(adi);
			return adv.getAdvertisementId();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}	
	
	public int ModifyAdvertisement(String startTime, String endTime, String url,
			int advertisementId, int adpostionId, int adinadpid, int state){
		// TODO Auto-generated method stub
		if(startTime == null || startTime.equals("") || 
				endTime == null || endTime.equals("") ||
				url == null || url.equals("")){
					return 0;
				}
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		Advertisement adv = new Advertisement();
		if(state == 0){
			Iterator iter  = adiDao.findByState((short)0).iterator();
//			 判断广告位上是否已有广告挂放.
			while(iter.hasNext()){
				Adinadp temp = (Adinadp)iter.next();
				// 添加传到客户端的信息
				if(temp.getAdposition().getAdpositionId() == adpostionId && temp.getAdvertisement().getAdvertisementId() != advertisementId){
					return -1;
				}
			}
		}
		try {
			d = (Date) f.parseObject(startTime);
			Timestamp start = new Timestamp(d.getTime());
			d = (Date) f.parseObject(endTime);
			Timestamp end = new Timestamp(d.getTime());
			adv.setEndTime(end);
			adv.setStartTime(start);
			adv.setUrl(url);
			adv.setAdvertisementId(advertisementId);
			adv.setAdphoto(String.valueOf(advertisementId));
			advertisementDao.attachDirty(adv);
			Adinadp adi = adiDao.findById(adinadpid);
			adi.setState((short)state);
			Adposition adp = new Adposition();
			adp.setAdpositionId(adpostionId);
			adi.setAdposition(adp);
			adiDao.attachDirty(adi);
			return advertisementId;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public boolean advModify(Integer advId, Integer posId, Integer adinadpId,
			String startTime, String endTime, String adphoto, String url,
			short state) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
