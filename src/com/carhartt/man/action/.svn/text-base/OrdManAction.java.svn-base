package com.carhartt.man.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.carhartt.man.service.OMManService;
import com.opensymphony.xwork2.ActionSupport;

public class OrdManAction extends ActionSupport {
	private OMManService ommanService;
	private Map<String, Object> jsonMap;
	private String orderId;
	private String dateFrom;
	private String dateTo;
	private int orderState;
	private int start;
	private int limit;
	
	//综合管理中的订单
	private int userId;
	


	public String getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}


	public String getDateTo() {
		return dateTo;
	}


	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public int getOrderState() {
		return orderState;
	}


	public void setOrderState(int orderState) {
		this.orderState = orderState;
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


	public OrdManAction() {
		jsonMap = new HashMap();
	}
	
	public String getOrdersByUserId(){
		jsonMap.clear();
		jsonMap.put("success", true);
		System.out.println(userId);
		System.out.println(orderState);
		JSONArray orderlist = ommanService.searchOrdersByUserId(userId,
				(short)orderState, start, limit);
		jsonMap.put("orders", orderlist);
		return SUCCESS;
	}
	
	public String getOrders() {
//		jsonMap.clear();
//		jsonMap.put("success", true);
//		JSONArray orderlist = ommanService.getOrders();
//		System.out.println(orderlist);
//		jsonMap.put("orders", orderlist);
//		return SUCCESS;
		jsonMap.clear();
		jsonMap.put("success", true);
		if(orderId == null || orderId.equals("")){
			orderId=new String("0");
		}
//		System.out.println(orderId);
//		System.out.println(orderDate);
//		System.out.println(orderState);
//		System.out.println(start);
//		System.out.println(limit);
		JSONObject orderlist = ommanService.searchOrders(Integer.valueOf(orderId), dateFrom, dateTo,
				(short)orderState, start, limit);
//			userManagerService.getUserList(start, limit);
		jsonMap.put("orders", orderlist.get("list"));
		jsonMap.put("totalCount", orderlist.get("count"));
		return SUCCESS;
	}
	
	public String orderCancle() {
		jsonMap.clear();
		jsonMap.put("success", false);
		System.out.println(orderId);
		if (ommanService.orderCancle(Integer.valueOf(orderId))) {
			jsonMap.put("success", true);
		}
		return SUCCESS;
	}
	
	public String orderDeliver() {
		jsonMap.clear();
		jsonMap.put("success", false);
		System.out.println(orderId);
		if (ommanService.orderDeliver(Integer.valueOf(orderId))) {
			jsonMap.put("success", true);
		}
		return SUCCESS;
	}
	
	public String searchOrder() {
		jsonMap.clear();
		jsonMap.put("success", true);
		if(orderId == null || orderId.equals("")){
			orderId=new String("0");
		}
		JSONObject orderlist = ommanService.searchOrders(Integer.valueOf(orderId), dateFrom, dateTo,
				(short)orderState, start, limit);
//			userManagerService.getUserList(start, limit);
		jsonMap.put("orders", orderlist.get("list"));
		jsonMap.put("totalCount", orderlist.get("count"));
		return SUCCESS;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}


	public OMManService getOmmanService() {
		return ommanService;
	}


	public void setOmmanService(OMManService ommanService) {
		this.ommanService = ommanService;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
