package com.carhartt.man.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.carhartt.man.config.CarharttConfig;
import com.carhartt.man.service.SMManService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SynManAction extends ActionSupport{
	Logger logger = Logger.getLogger(this.getClass());
	private Map<String, Object> jsonMap;
	private SMManService sMManService;
	private int start;
	private int limit;
	private String error;
	
	// Announce公告
	private int announceId;
	private String announceTitle;
	private String announceContent;
	private String remark;
	
	// Message反馈消息
	private int messageId;
	private String userId;
	private String notifiedTitle;
	private String notifiedContent;
	private String mesgTitle;
	private int mesgType;
	private int isReply;
	
	//广告
	private String advertisementId;
	private String startTime;
	private String endTime;
	private int price;
	private int ratio;
	private String url;
	private int kind;
	private int position;
	private int state;
	private String adpositionId;
	private int adinadpid;
	
	
	// 图片上传
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;
	
	
	public String AddAdvertise(){
		try {
			jsonMap.clear();
			if(adpositionId == null || adpositionId.equals("")){
				adpositionId = "0";
			}
			int id = sMManService.addAdvertisement(startTime, endTime, url, Integer.valueOf(adpositionId), state);
			System.out.println("===========upload============");
			System.out.println("title:" + title);
			System.out.println("upload:" + upload);
			System.out.println("uploadContentType:" + uploadContentType);
			System.out.println("uploadFileName:" + uploadFileName);
			System.out.println("savePath:" + savePath);
			System.out.println("id:" + id);
			if(id == 0){
				jsonMap.put("success", false);
				jsonMap.put("error", "插入错误!");
				return SUCCESS;
			}else if(id == -1){
				jsonMap.put("error", "目前广告位已有广告挂起!");
				jsonMap.put("success", false);
				return SUCCESS;
			}
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
					+ id + ".jpg");
			FileInputStream fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.clear();
		jsonMap.put("success", true);
		return Action.SUCCESS;
	}
	
	public String alterAdvertise(){
		//更新广告
		jsonMap.clear();
		if(adpositionId == null || adpositionId.equals("")){
			adpositionId = "0";
		}
		if(adpositionId == null || adpositionId.equals("")){
			adpositionId = "0";
		}
		int issucceed = sMManService.ModifyAdvertisement(startTime, endTime, url, Integer.valueOf(advertisementId), Integer.valueOf(adpositionId), adinadpid, state);
		if(issucceed >= 1){
			if (upload != null) {
				System.out.println("删除图片!");
				// 删除图片
				File file = new File(getSavePath() + "\\" + issucceed
						+ ".jpg");
				if (file.isFile() && file.exists()) {
					file.delete();
				}
				// 存储图片
				try {
					FileOutputStream fos = new FileOutputStream(getSavePath()
							+ "\\" + issucceed + ".jpg");
					FileInputStream fis = new FileInputStream(getUpload());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			jsonMap.put("success", true);
			return Action.SUCCESS;
		}else if(issucceed == -1){
			jsonMap.put("error", "广告位已有广告正在播放!");
		}
		jsonMap.put("success", false);
		return Action.SUCCESS;
	}
	
	
	//修改广告位信息 
	public String UpdateAdPosition(){
		jsonMap.clear();
		logger.info("adpostionId:" + adpositionId +"kind:" + kind +"position:" + 
				position + "price:" + price +
				"ratio:"+ratio);
		try{
			if(adpositionId == null || adpositionId.equals("")){
				adpositionId = "0";
			}
			// 修改公告
			if(sMManService.adpModify(Integer.valueOf(adpositionId), 
					Float.valueOf(price), 
					(short)kind, 
					Float.valueOf(ratio), 
					(short)position)){
				jsonMap.put("success", true);
			}else{
				error = "数据库操作失败!";
				jsonMap.put("error", error);
				jsonMap.put("success", false);
			}
		}catch(Exception e){
			error = "数据库操作失败!";
			jsonMap.put("error", error);
			jsonMap.put("success", false);
		}
		return SUCCESS;
	}
	
	//搜索所有的广告位
	public String getAdPosition(){
		jsonMap.clear();
		jsonMap.put("success", true);
		if(adpositionId == null || adpositionId.equals("")){
			adpositionId = "0";
		}
		JSONObject adpList = sMManService.searchAdp(Integer.valueOf(adpositionId), (short)kind, start, limit);
		jsonMap.put("adpostionList", adpList.get("list"));
		jsonMap.put("totalCount", adpList.get("count"));
		return SUCCESS;
	}
	
	//搜索所有广告
	public String searchAdvertise(){
		jsonMap.clear();
		jsonMap.put("success", true);
		if(advertisementId == null || advertisementId.equals("")){
			advertisementId = "0";
		}
		JSONObject announceList = sMManService.searchAdv(Integer.valueOf(advertisementId), (short)state, (short)kind, start, limit);
		jsonMap.put("advertiseList", announceList.get("list"));
		jsonMap.put("totalCount", announceList.get("count"));
		return SUCCESS;
	}
	
	public String searchAnnounce() {
		jsonMap.clear();
		jsonMap.put("success", true);
		JSONObject announceList = sMManService.getAnnouncementList(start, limit);
		jsonMap.put("announceList", announceList.get("list"));
		jsonMap.put("totalCount", announceList.get("count"));
		return SUCCESS;
	}
	
	public String searchFeedback(){
		jsonMap.clear();
		logger.info("start:" + start + "limit:" + limit +"userId:" + userId +"mesgTitle:" + mesgTitle 
				+"mesgType:" + mesgType +"remark:" + remark + "isReply:"+ isReply);
		if(userId == null || userId.equals("")){
			userId = "0";
		}
		JSONObject feedbackList = sMManService.searchMsg(Integer.valueOf(userId), mesgTitle, mesgType, remark, isReply,start, limit);
		jsonMap.put("feedbackList", feedbackList.get("list"));
		// 获得满足条件totalCount的准确值.
		jsonMap.put("totalCount", feedbackList.get("count"));
		jsonMap.put("success", true);
		return SUCCESS;
	}
	
	public String DeleteAnnounceById(){
		jsonMap.clear();
		logger.info("announceId:" + announceId);
		try{
			sMManService.deleteAnnouncement(announceId);
			jsonMap.put("error", null);
		}catch(Exception e){
			error = "数据库操作失败!";
			jsonMap.put("error", error);
		}
		return SUCCESS;
	}
	
	public String UpdateAnnounceInfo(){
		jsonMap.clear();
		logger.info("announceId:" + announceId +"announceTitle:" + 
				announceTitle + "announceContent:" + announceContent +
				"remark:"+remark);
		try{
			// 修改公告
			if(sMManService.annModify(announceId, 0, announceTitle, announceContent, remark)){
				jsonMap.put("success", true);
			}else{
				error = "数据库操作失败!";
				jsonMap.put("error", error);
				jsonMap.put("success", false);
			}
		}catch(Exception e){
			error = "数据库操作失败!";
			jsonMap.put("error", error);
			jsonMap.put("success", false);
		}
		return SUCCESS;
	}
	
	public String AddAnnounceInfo(){
		jsonMap.clear();
		logger.info("announceTitle:" + 
				announceTitle + "announceContent:" + announceContent +
				"remark:"+remark);
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			int managerId = Integer.valueOf(String.valueOf(session.getAttribute(CarharttConfig.USERID)));
			// 添加公告
			if(sMManService.annModify(0, managerId, announceTitle, announceContent, remark)){
				jsonMap.put("success", true);
			}else{
				error = "数据库操作失败!";
				jsonMap.put("error", error);
				jsonMap.put("success", false);
			}
		}catch(Exception e){
			error = "数据库操作失败!";
			jsonMap.put("error", error);
			jsonMap.put("success", false);
		}
		return SUCCESS;
	}
	
	public String addNotification(){
		jsonMap.clear();
		jsonMap.put("success", false);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int managerId = Integer.valueOf(String.valueOf(session.getAttribute(CarharttConfig.USERID)));
		logger.info("userId:" + userId +" notifiedTitle:" + 
				notifiedTitle + " notifiedContent:" + notifiedContent + " managerId:"+managerId);
		
		if(userId == null || userId.equals("")){
			userId = "0";
		}
		//获取登录账号的用户id,发送通知到userId中 ,标记为新消息.时间为当前时间. 
		if(sMManService.addNotification(messageId, notifiedTitle, notifiedContent, managerId, Integer.valueOf(userId))){
			jsonMap.put("success", true);
		}else{
			error = "发送消息至用户: "+ userId +"失败！";
			jsonMap.put("error", error);
		}
		return SUCCESS;
	}
	
	
	public String alterFeedbackById(){
		jsonMap.clear();
		jsonMap.put("success", false);
		logger.info("messageId:" + messageId + "remark:" + remark);
		if(sMManService.alterFeedbackById(messageId, remark)){
			jsonMap.put("success", true);
		}
		return SUCCESS;
	}
	public SynManAction() {
		jsonMap = new HashMap();
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
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

	public SMManService getsMManService() {
		return sMManService;
	}

	public void setsMManService(SMManService sMManService) {
		this.sMManService = sMManService;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public int getAnnounceId() {
		return announceId;
	}

	public void setAnnounceId(int announceId) {
		this.announceId = announceId;
	}

	public String getAnnounceTitle() {
		return announceTitle;
	}

	public void setAnnounceTitle(String announceTitle) {
		this.announceTitle = announceTitle;
	}

	public String getAnnounceContent() {
		return announceContent;
	}

	public void setAnnounceContent(String announceContent) {
		this.announceContent = announceContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getNotifiedTitle() {
		return notifiedTitle;
	}

	public void setNotifiedTitle(String notifiedTitle) {
		this.notifiedTitle = notifiedTitle;
	}

	public String getNotifiedContent() {
		return notifiedContent;
	}

	public void setNotifiedContent(String notifiedContent) {
		this.notifiedContent = notifiedContent;
	}

	public String getMesgTitle() {
		return mesgTitle;
	}

	public void setMesgTitle(String mesgTitle) {
		this.mesgTitle = mesgTitle;
	}

	public int getMesgType() {
		return mesgType;
	}

	public void setMesgType(int mesgType) {
		this.mesgType = mesgType;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public int getIsReply() {
		return isReply;
	}

	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}


	public String getAdvertisementId() {
		return advertisementId;
	}


	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAdpositionId(String adpositionId) {
		this.adpositionId = adpositionId;
	}

	public String getAdpositionId() {
		return adpositionId;
	}

	public int getAdinadpid() {
		return adinadpid;
	}

	public void setAdinadpid(int adinadpid) {
		this.adinadpid = adinadpid;
	}	
	
}
