package com.carhartt.man.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.model.Order;
import com.carhartt.man.model.Orderdetials;

public interface OMManService {
	public JSONArray getOrders();   //��ȡ���ж���
//	public List getOrder(Order Order);  //���ո��������ȡ����
	public Orderdetials getOrderInfo(Integer id);  //��ȡ��������
	public boolean orderCancle(Integer id);  //ȡ��
	public boolean orderDeliver(Integer id);  // ����ȷ��
	public JSONObject searchOrders(Integer id, String fromDate, String toDate,short state, int start, int limit);
	
	public JSONArray searchOrdersByUserId(Integer id, short state, int start, int limit);
}
