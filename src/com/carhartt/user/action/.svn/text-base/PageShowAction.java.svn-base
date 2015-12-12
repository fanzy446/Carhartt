package com.carhartt.user.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import com.carhartt.man.model.Adinadp;
import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Subclass;
import com.carhartt.man.model.User;
import com.carhartt.user.service.PageShowService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PageShowAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(UserBasicAction.class);
	private PageShowService pageShowService;
	private User user;
	private int pageNow;
	private int perPage;
	private int kind;
	private Map<String, Object> jsonMap = new HashMap<String, Object>();

	/**
	 * 
	 * @return "Class", "Advertisement", "Announcement"
	 */
	/*
	 * public String showIndex() { jsonMap.clear(); List<Broadclass> classes =
	 * pageShowService.fetchClasses(); for (int i = 0; i < classes.size(); i++)
	 * { Broadclass bc = classes.get(i); bc.setItems(new HashSet(pageShowService
	 * .fetchLatestItems(bc, 10))); Iterator iterator =
	 * bc.getSubclasses().iterator(); while (iterator.hasNext()) { Subclass sc =
	 * (Subclass) iterator.next(); sc.setItems(null); } // bc.setItems(null);
	 * classes.set(i, bc); } // System.out.println(JSONArray.fromObject(classes,
	 * jsonConfig)); kind = 1; List advertisement =
	 * pageShowService.fetchAdvertisement(kind);
	 * 
	 * for( Object per : advertisement) { Adposition ad = (Adposition) per;
	 * for(Object obj : ad.getAdinadps()) { Adinadp temp = (Adinadp)obj;
	 * temp.getAdvertisement().setAdinadps(null); } }
	 * 
	 * // List announcement = pageShowService.fetchAnouncement(1, 3);
	 * 
	 * JsonConfig jsonConfigForClass = new JsonConfig();
	 * jsonConfigForClass.setExcludes(new String[] { "broadclass", "subclass",
	 * "hibernateLazyInitializer", "evaluates", "orderdetialses", "favoriteses",
	 * "shoppingcarts" }); JsonConfig jsonConfigForAd = new JsonConfig();
	 * jsonConfigForAd.setExcludes(new String[] { "adposition",
	 * "hibernateLazyInitializer", "broadclass", "subclass", "evaluates",
	 * "orderdetialses", "favoriteses", "shoppingcarts" });
	 * 
	 * // JsonConfig jsonConfigForAnn = new JsonConfig(); //
	 * jsonConfigForAnn.setExcludes(new String[] { "manager" }); //
	 * jsonMap.put("Class", JSONArray.fromObject(classes, jsonConfigForClass));
	 * System.out.println(JSONArray.fromObject(classes, jsonConfigForClass));
	 * jsonMap.put("Advertisement", JSONArray.fromObject(advertisement,
	 * jsonConfigForAd)); // jsonMap.put("Announcement", //
	 * JSONArray.fromObject(announcement, jsonConfigForAnn));
	 * 
	 * return Action.SUCCESS; }
	 */
	public String showIndex() {
		//读取大类小类商品
		jsonMap.clear();
		List<Broadclass> classes = pageShowService.fetchClasses();
		for (int i = 0; i < classes.size(); i++) {
			Broadclass bc = classes.get(i);
			bc.setItems(new HashSet(pageShowService.fetchLatestItems(bc, 10)));
			Iterator iterator = bc.getSubclasses().iterator();
			while (iterator.hasNext()) {
				Subclass sc = (Subclass) iterator.next();
				sc.setItems(null);
			}
			// bc.setItems(null);
			classes.set(i, bc);
		}
		JsonConfig jsonConfigForClass = new JsonConfig();
		jsonConfigForClass.setExcludes(new String[] { "broadclass", "subclass",
				"hibernateLazyInitializer", "evaluates", "orderdetialses",
				"favoriteses", "shoppingcarts" });
//		System.out.println(JSONArray.fromObject(classes, jsonConfigForClass));
		jsonMap.put("Class", JSONArray.fromObject(classes, jsonConfigForClass));
		//读取广告
		// System.out.println(JSONArray.fromObject(classes, jsonConfig));
		List advertisement = pageShowService.fetchAdvertisement(kind);

		for (Object per : advertisement) {
			Adposition ad = (Adposition) per;
			for (Object obj : ad.getAdinadps()) {
				Adinadp temp = (Adinadp) obj;
				temp.getAdvertisement().setAdinadps(null);
			}
		}
		JsonConfig jsonConfigForAd = new JsonConfig();
		jsonConfigForAd
				.setExcludes(new String[] { "adposition",
						"hibernateLazyInitializer", "broadclass", "subclass",
						"evaluates", "orderdetialses", "favoriteses",
						"shoppingcarts" });
		System.out.println(JSONArray.fromObject(advertisement, jsonConfigForAd));
		jsonMap.put("Advertisement", JSONArray.fromObject(advertisement, jsonConfigForAd));
		
		//读取公告
//		List announcement = pageShowService.fetchAnouncement(1, 3);
//		JsonConfig jsonConfigForAnn = new JsonConfig();
//		jsonConfigForAnn.setExcludes(new String[] { "manager" });
		// jsonMap.put("Announcement",
		// JSONArray.fromObject(announcement, jsonConfigForAnn));
		return Action.SUCCESS;
	}

	public PageShowService getPageShowService() {
		return pageShowService;
	}

	public void setPageShowService(PageShowService pageShowService) {
		this.pageShowService = pageShowService;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		PageShowAction.logger = logger;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

}
