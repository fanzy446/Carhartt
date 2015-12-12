package com.carhartt.user.service;

import java.util.List;

import com.carhartt.man.model.Consignee;
import com.carhartt.man.model.Evaluate;
import com.carhartt.man.model.Favorites;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.Message;
import com.carhartt.man.model.Notification;
import com.carhartt.man.model.Order;
import com.carhartt.man.model.Orderdetials;
import com.carhartt.man.model.Shoppingcart;
import com.carhartt.man.model.User;
import com.carhartt.man.model.Userinfo;
import com.carhartt.man.model.Verification;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface PersonalcenterService {
	public User finduser(int userid);
	public Boolean ChangePassword(User user);//修改密码
	public Userinfo GetInfo(int userid);//获取用户基本信息
	public Boolean ChangeInfo(Userinfo userinfo);//保存修改后的用户基本信息
	public List AllConsignee(User user);//获取所有的收货人地址
	public Consignee GetOneConsignee(int Consigneeid);//获取某地址
	public Boolean AddConsignee(Consignee consignee);//增加收人地址
	public Boolean DeleteConsignee(Consignee consignee);//删除收货地址
	public Boolean ChangeConsignee(Consignee consignee);//保存修改后的收货地址
	public List AllGoodofShoppingcart(User user);//获取所有的购物车中的商品
	public Boolean DeleteGoodFromShoppingcart(Shoppingcart shopingcart);//从购物车中删除商品
	public Shoppingcart GetShoppingcart(int cartid);
	public Boolean AddOrder(Order order);//增加订单，提交订单
	public List AllGoodofFavorite(int userid);//收藏夹中的所有商品
	public Boolean DeleteGoodFromFavorites(Favorites favorites);//从收藏夹中删除商品
	public boolean SaveChangeCart(Shoppingcart shoppingcart);
	public Boolean AddGoodFromFavoritestoCart(int userid,int favoriteId);//从收藏夹加入购物车
	public List AllOrders(int userid);//所有订单
	public Order GetOneOrder(int OrderId);//获取某一订单的详细信息
	public List GetOrderDetial(Order Order);//获取某一订单内购物的详细信息
	public Boolean DeleteOrder(int OrderId);//删除订单
	public Boolean ChangeOrder(Order order);//修改订单，比如取消订单,确认收货
	public List AllMessage();//所有反馈
	public Boolean AddMessage(Message message);
	public List GetOneUserAllMessage(int userid);//用户
	public Message getOneMessage(int messageid);//查找某一条反馈
	public Item GetOneItem(int itemid);

	public JSONArray getFavorites(int userId);//查找
	public String delFavorite(int favoriteId);

	public Verification Getverification(User user);
	public Boolean DeleteVarification(Verification verification);
	public Boolean SaveVarification(Verification verification);
	public Orderdetials GetoneDetail(int detailid);
	public boolean SaveEvaluate(Evaluate evaluate);
	public Evaluate GetOneEvaluate(int id);
	public boolean SaveOrderdetials(Orderdetials orderdetials);
	
	public List AllAnnouncement();
	
	public Notification GetOneNotification(int Notificationid);
}
