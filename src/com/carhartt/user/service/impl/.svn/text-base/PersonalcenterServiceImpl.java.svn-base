package com.carhartt.user.service.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.crypto.spec.PSource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.dao.AnnouncementDAO;
import com.carhartt.man.dao.ConsigneeDAO;
import com.carhartt.man.dao.EvaluateDAO;
import com.carhartt.man.dao.FavoritesDAO;
import com.carhartt.man.dao.ItemDAO;
import com.carhartt.man.dao.MessageDAO;
import com.carhartt.man.dao.NotificationDAO;
import com.carhartt.man.dao.OrderDAO;
import com.carhartt.man.dao.OrderdetialsDAO;
import com.carhartt.man.dao.ShoppingcartDAO;
import com.carhartt.man.dao.UserDAO;
import com.carhartt.man.dao.UserinfoDAO;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.dao.VerificationDAO;
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
import com.carhartt.user.service.PersonalcenterService;

public class PersonalcenterServiceImpl implements PersonalcenterService {
	private final static String SAVE_PATH = "pages/source/image/item/";

	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	public UserinfoDAO getUserinfodao() {
		return userinfodao;
	}

	public void setUserinfodao(UserinfoDAO userinfodao) {
		this.userinfodao = userinfodao;
	}

	public ConsigneeDAO getConsigneedao() {
		return consigneedao;
	}

	public void setConsigneedao(ConsigneeDAO consigneedao) {
		this.consigneedao = consigneedao;
	}

	public ShoppingcartDAO getShoppingcartdao() {
		return shoppingcartdao;
	}

	public void setShoppingcartdao(ShoppingcartDAO shoppingcartdao) {
		this.shoppingcartdao = shoppingcartdao;
	}

	public OrderDAO getOrderdao() {
		return orderdao;
	}

	public void setOrderdao(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}

	public FavoritesDAO getFavoritesdao() {
		return favoritesdao;
	}

	public void setFavoritesdao(FavoritesDAO favoritesdao) {
		this.favoritesdao = favoritesdao;
	}

	public MessageDAO getMessagedao() {
		return messagedao;
	}

	public void setMessagedao(MessageDAO messagedao) {
		this.messagedao = messagedao;
	}

	public UserDAO userdao;
	public UserinfoDAO userinfodao;
	public ConsigneeDAO consigneedao;
	public ShoppingcartDAO shoppingcartdao;
	public OrderDAO orderdao;
	public FavoritesDAO favoritesdao;
	public MessageDAO messagedao;
	public OrderdetialsDAO orderdetailsdao;
	public ItemDAO itemdao;
	public VerificationDAO verificationdao;
	public EvaluateDAO evaluatedao;
	public AnnouncementDAO announcementdao;
	public NotificationDAO notificationdao;

	public ItemDAO getItemdao() {
		return itemdao;
	}

	public void setItemdao(ItemDAO itemdao) {
		this.itemdao = itemdao;
	}

	public VerificationDAO getVerificationdao() {
		return verificationdao;
	}

	public void setVerificationdao(VerificationDAO verificationdao) {
		this.verificationdao = verificationdao;
	}

	public OrderdetialsDAO getOrderdetailsdao() {
		return orderdetailsdao;
	}

	public void setOrderdetailsdao(OrderdetialsDAO orderdetailsdao) {
		this.orderdetailsdao = orderdetailsdao;
	}

	public User finduser(int userid) {
		return userdao.findById(userid);
	}

	public Boolean ChangePassword(User user) {
		userdao.merge(user);
		return true;
	}

	public Userinfo GetInfo(int userid) {
		// User user = userdao.findById(userid);
		return (Userinfo) userinfodao.findByProperty("userId", userid).get(0);
	}

	public Boolean ChangeInfo(Userinfo userinfo) {
		userinfodao.merge(userinfo);
		return true;
	}

	public List AllConsignee(User user) {
		return consigneedao.findByProperty("user", user);
	}

	public Consignee GetOneConsignee(int Consigneeid) {
		return consigneedao.findById(Consigneeid);
	}

	public Boolean AddConsignee(Consignee consignee) {
		consigneedao.save(consignee);
		return true;
	}

	public Boolean DeleteConsignee(Consignee consignee) {
		consigneedao.delete(consignee);
		return true;
	}

	public Boolean ChangeConsignee(Consignee consignee) {
		consigneedao.merge(consignee);
		return null;
	}

	public List AllGoodofShoppingcart(User user) {
		return shoppingcartdao.findByProperty("user", user);
	}

	public Boolean DeleteGoodFromShoppingcart(Shoppingcart shoppingcart) {
		shoppingcartdao.delete(shoppingcart);
		return true;
	}

	public Boolean AddOrder(Order order) {
		orderdao.save(order);
		return true;
	}

	public List AllGoodofFavorite(int userid) {
		return favoritesdao.findByProperty("userId", userid);
	}

	public Boolean DeleteGoodFromFavorites(Favorites favorites) {
		favoritesdao.delete(favorites);
		return null;
	}

	public Boolean AddGoodFromFavoritestoCart(int userid, int favoriteId) {
		Shoppingcart shoppingcart = new Shoppingcart();
		shoppingcart.setItem(favoritesdao.findById(favoriteId).getItem());
		shoppingcart.setUser(userdao.findById(userid));
		shoppingcart.setPurchaseNum(1);
		shoppingcart.setItemPrice(favoritesdao.findById(favoriteId).getItem()
				.getItemPrice());
		shoppingcartdao.save(shoppingcart);
		return true;
	}

	public List AllOrders(int userid) {
		return orderdao.findByProperty("userId", userid);
	}

	public Order GetOneOrder(int OrderId) {
		return orderdao.findById(OrderId);
	}

	public List GetOrderDetial(Order order) {
		return orderdetailsdao.findByProperty("order", order);
	}

	public Boolean DeleteOrder(int OrderId) {
		Order order = orderdao.findById(OrderId);
		order.setOrderState((short) 4);
		orderdao.merge(order);
		return true;
	}

	public Boolean ChangeOrder(Order order) {
		//确认收货时给个当前时间
		order.setReceivingTime(new Timestamp(System.currentTimeMillis()));
		orderdao.merge(order);
		return true;
	}

	public List AllMessage() {
		return messagedao.findAll();
	}

	public List GetOneUserAllMessage(int userid) {
		return messagedao.findByProperty("userId", userid);
	}

	public Message getOneMessage(int messageid) {
		return messagedao.findById(messageid);
	}

	public Item GetOneItem(int itemid) {
		/*
		 * System.out.println("service"); Item item2 = itemdao.findById(1744);
		 * System.out.println(item2.getItemName()); Item item =
		 * itemdao.findById(itemid);
		 * 
		 * System.out.println(item.getItemName());
		 */

		return itemdao.findById(itemid);
	}

	public Boolean AddMessage(Message message) {
		messagedao.save(message);
		return true;
	}

	public JSONArray getFavorites(int userId) {
		// TODO Auto-generated method stub

		User user = userdao.findById(userId);
		Iterator iter = favoritesdao.getFavorites(user).iterator();
		JSONObject data = new JSONObject();
		JSONArray result = new JSONArray();
		while (iter.hasNext()) {
			Favorites favorite = (Favorites) iter.next();
			data.put("favoriteId", favorite.getFavoriteId());
			Item item = favorite.getItem();
			data.put("itemId", item.getItemId());
			data.put("itemPrice", item.getItemPrice());
			data.put("itemCount", item.getItemCount());
			data.put("itemPhoto", SAVE_PATH + item.getItemPhoto() + ".jpg");
			result.add(data);
		}
		return result;
	}

	public String delFavorite(int favoriteId) {
		// TODO Auto-generated method stub
		favoritesdao.delete(favoritesdao.findById(favoriteId));
		return null;
	}

	public List AllConsignee(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Verification Getverification(User user) {
		System.out.print("Enter Service!!!");
		List list = verificationdao.findByProperty("user", user);
		System.out.print(list.size());
		return (Verification) list.get(list.size() - 1);
	}

	public Boolean DeleteVarification(Verification verification) {
		verificationdao.delete(verification);
		return true;
	}

	public Boolean SaveVarification(Verification verification) {
		verificationdao.save(verification);
		return true;
	}

	public Shoppingcart GetShoppingcart(int cartid) {
		return shoppingcartdao.findById(cartid);
	}

	public Orderdetials GetoneDetail(int detailid) {
		return orderdetailsdao.findById(detailid);
	}

	public boolean SaveEvaluate(Evaluate evaluate) {
		evaluatedao.save(evaluate);
		return true;
	}

	public EvaluateDAO getEvaluatedao() {
		return evaluatedao;
	}

	public void setEvaluatedao(EvaluateDAO evaluatedao) {
		this.evaluatedao = evaluatedao;
	}

	public boolean SaveOrderdetials(Orderdetials orderdetials) {
		orderdetailsdao.merge(orderdetials);
		return true;
	}

	public AnnouncementDAO getAnnouncementdao() {
		return announcementdao;
	}

	public void setAnnouncementdao(AnnouncementDAO announcementdao) {
		this.announcementdao = announcementdao;
	}

	public boolean SaveChangeCart(Shoppingcart shoppingcart) {
		shoppingcartdao.merge(shoppingcart);
		return true;
	}

	public List AllAnnouncement() {

		return announcementdao.findAll();
	}

	public Notification GetOneNotification(int Notificationid) {
		return notificationdao.findById(Notificationid);
	}

	public NotificationDAO getNotificationdao() {
		return notificationdao;
	}

	public void setNotificationdao(NotificationDAO notificationdao) {
		this.notificationdao = notificationdao;
	}

	public Evaluate GetOneEvaluate(int id) {
		return evaluatedao.findById(id);
	}

}
