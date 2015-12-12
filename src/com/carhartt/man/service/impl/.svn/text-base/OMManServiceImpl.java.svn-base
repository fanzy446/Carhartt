package com.carhartt.man.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.carhartt.man.dao.OrderDAO;
import com.carhartt.man.dao.OrderdetialsDAO;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.Order;
import com.carhartt.man.model.Orderdetials;
import com.carhartt.man.model.User;
import com.carhartt.man.service.OMManService;

public class OMManServiceImpl implements OMManService {
	private OrderDAO orderDao;
	private OrderdetialsDAO orderDetailDAO;
	
	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public OrderdetialsDAO getOrderDetailDAO() {
		return orderDetailDAO;
	}

	public void setOrderDetailDAO(OrderdetialsDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}


	public JSONArray getOrders() {
		List<Order> listOrders = orderDao.getOrdeList();
//		JsonConfig jsonConfig = new JsonConfig(); //建立配置文件
//		jsonConfig.setIgnoreDefaultExcludes(false); //设置默认忽略
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
//		JSONArray jsonArray = JSONArray.fromObject(listOrders,jsonConfig); 
//		JsonConfig config = new JsonConfig();
//		config.setJsonPropertyFilter(new PropertyFilter() {
//			public boolean apply(Object source, String name, Object value) {
//				if (name.equals("orderdetialses")||name.equals("consignee")) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		});
		JSONObject data = new JSONObject();
		JSONArray ordList = new JSONArray();
//		JSONArray ordList = JSONArray.fromObject(listOrders, config);
		 Iterator iter=orderDao.getOrdeList().iterator();
		 while(iter.hasNext()){
		 Order ord = (Order)iter.next();
		 data.put("orderId", ord.getOrderId());
		 data.put("consignee", ord.getConsignee().getConsigneeId());
		 data.put("user", ord.getUser().getUserId());
		 data.put("totalPrice", ord.getTotalPrice());
		 data.put("createTime", ord.getCreateTime().toString());
		 data.put("orderState", ord.getOrderState());
		 data.put("deliveryTime", ord.getDeliveryTime().toString());
		 data.put("receivingTime", ord.getReceivingTime().toString());
		 data.put("evaluationGrade", ord.getEvaluationGrade());
		 data.put("extraEstimate", ord.getExtraEstimate());
		 ordList.add(data);
		 }

		return ordList;
	}
	
	//Orderdetails model可以直接用不需要转化为json对象，因为 
	public Orderdetials getOrderInfo(Integer id) {
		// TODO Auto-generated method stub
		return orderDetailDAO.findById(id);
	}
	
	public boolean orderCancle(Integer id) {
		// TODO Auto-generated method stub
//		Order ord = new Order();
//		ord.setOrderId(id);
//		ord.setOrderState((short)2);
//		orderDao.attachDirty(ord);
//		Order ord = orderDao.findById(id);
//		ord.setOrderState((short)2);
//		orderDao.attachDirty(ord);
		Order order = orderDao.findById(id);
		if(order == null){
			return false;
		}
		order.setOrderState((short) 2);
		orderDao.attachDirty(order);
		return true;
	}

	public boolean orderDeliver(Integer id) {
		// TODO Auto-generated method stub
//		Order ord = new Order();
//		ord.setOrderId(id);
//		ord.setOrderState((short)3);
//		orderDao.attachDirty(ord);
//		Order ord = orderDao.findById(id);
//		ord.setOrderState((short)3);
//		orderDao.attachDirty(ord);
		Order order = orderDao.findById(id);
		if(order == null){
			return false;
		}
		order.setOrderState((short) 1);
		order.setDeliveryTime(new Timestamp(System.currentTimeMillis()));
		orderDao.attachDirty(order);
		return true;
	}

	public JSONObject searchOrders(Integer id, String dateFrom, String dateTo,short state, int start, int limit) {
		JSONObject data = new JSONObject();
		JSONArray orderList = new JSONArray();
		Iterator iter = orderDao.searchOrders(id , dateFrom , dateTo, state, start, limit).iterator();
		int count = orderDao.getAllRecordNum(id, dateFrom, dateTo, state);
		while(iter.hasNext()){
			Order ord = (Order)iter.next();
			// 添加传到客户端的信息
			data.put("orderId", ord.getOrderId());
			data.put("consignee", ord.getConsignee().getConsigneeId());
			 data.put("user", ord.getUser().getUserId());
			 data.put("totalPrice", ord.getTotalPrice());
			 data.put("createTime", ord.getCreateTime().toString());
			 data.put("orderState", ord.getOrderState());
			 if(ord.getDeliveryTime() != null){
					data.put("deliveryTime", ord.getDeliveryTime().toString());
			}
			else data.put("deliveryTime", "尚未发货");
			if(ord.getReceivingTime() != null){
					data.put("receivingTime", ord.getReceivingTime().toString());
			}
			else data.put("receivingTime", "尚未收货");
			 data.put("evaluationGrade", ord.getEvaluationGrade());
			 data.put("extraEstimate", ord.getExtraEstimate());
			 data.put("remark", ord.getRemark());
			orderList.add(data);
		}
		JSONObject obj = new JSONObject();
		obj.put("list", orderList);
		obj.put("count", count);
		return obj;
	}

	public JSONArray searchOrdersByUserId(Integer id, short state, int start,
			int limit) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray orderList = new JSONArray();
		Iterator iter = orderDao.searchOrdersByUserId(id , state, start, limit).iterator();
		while(iter.hasNext()){
			Order ord = (Order)iter.next();
			// 添加传到客户端的信息
			data.put("orderId", ord.getOrderId());
			data.put("consignee", ord.getConsignee().getConsigneeId());
			data.put("user", ord.getUser().getUserId());
			data.put("totalPrice", ord.getTotalPrice());
			data.put("createTime", ord.getCreateTime().toString());
			data.put("orderState", ord.getOrderState());
			if(ord.getDeliveryTime() != null){
				data.put("deliveryTime", ord.getDeliveryTime().toString());
			}
			else data.put("deliveryTime", "尚未发货");
			if(ord.getReceivingTime() != null){
				data.put("receivingTime", ord.getReceivingTime().toString());
			}
			else data.put("receivingTime", "尚未收货");
			data.put("evaluationGrade", ord.getEvaluationGrade());
			data.put("extraEstimate", ord.getExtraEstimate());
			data.put("remark", ord.getRemark());
			orderList.add(data);
		}
		return orderList;
	}

//	public List getOrder(Order Order) {
//		// TODO Auto-generated method stub
//		List<Order> orderList = new ArrayList<Order>();
//		Iterator iter=orderDao.findByExample(Order).iterator();
//		while(iter.hasNext()){
//			Order ord = (Order)iter.next();
//			orderList.add(ord);
//		}
//		return orderList;
//
//	}

}
