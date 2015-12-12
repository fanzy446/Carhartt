package com.carhartt.user.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.util.Mail;

import com.carhartt.man.model.Announcement;
import com.carhartt.man.model.Consignee;
import com.carhartt.man.model.Evaluate;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.Message;
import com.carhartt.man.model.Notification;
import com.carhartt.man.model.Order;
import com.carhartt.man.model.Orderdetials;
import com.carhartt.man.model.Shoppingcart;
import com.carhartt.man.model.User;
import com.carhartt.man.model.Userinfo;
import com.carhartt.man.model.Verification;
import com.carhartt.user.service.UserConsumingService;
import com.carhartt.user.service.impl.PersonalcenterServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.carhartt.util.VerificationCreator;

public class PersonalCenterAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userid;
	private String userName;
	private int userRole;// 用户角色
	private String realname;
	private String idCard;
	private String userPhone;
	private String userGender;// 性别
	private int userCredits;// 消费积分
	private String passwhint;
	private String passanswer;
	private String useremail;
	private String man;
	private String woman;
	private PersonalcenterServiceImpl PS;
	private Map<String, Object> jsonMap = new HashMap();
	private UserConsumingService userConsumingService;
	private String favoriteId;
	private String favorites_Items_Id_Str;
	private String itemId;

	public UserConsumingService getUserConsumingService() {
		return userConsumingService;
	}

	public void setUserConsumingService(
			UserConsumingService userConsumingService) {
		this.userConsumingService = userConsumingService;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getFavorites_Items_Id_Str() {
		return favorites_Items_Id_Str;
	}

	public void setFavorites_Items_Id_Str(String favoritesItemsIdStr) {
		favorites_Items_Id_Str = favoritesItemsIdStr;
	}

	/* 获取个人信息action */
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public String getPasswhint() {
		return passwhint;
	}

	public void setPasswhint(String passwhint) {
		this.passwhint = passwhint;
	}

	public String getPassanswer() {
		return passanswer;
	}

	public void setPassanswer(String passanswer) {
		this.passanswer = passanswer;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public String getWoman() {
		return woman;
	}

	public void setWoman(String woman) {
		this.woman = woman;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public int getUserCredits() {
		return userCredits;
	}

	public void setUserCredits(int userCredits) {
		this.userCredits = userCredits;
	}

	public PersonalcenterServiceImpl getPS() {
		return PS;
	}

	public void setPS(PersonalcenterServiceImpl pS) {
		PS = pS;
	}

	public String getpersonalinfo() {

		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		User user = PS.finduser(userid);
		userName = user.getUserName();
		// userRole = user.getUserRole();
		useremail = user.getUserEmail();
		Userinfo userinfo = PS.GetInfo(userid);
		realname = userinfo.getRealName();
		idCard = userinfo.getIdCard();
		userPhone = userinfo.getUserPhone();
		userGender = userinfo.getUserGender();
		userCredits = userinfo.getUserCredits();
		passwhint = userinfo.getPasswHint();
		passanswer = userinfo.getPassAnswer();
		jsonMap.put("name", userName);
		// jsonMap.put("role", userRole);
		jsonMap.put("realname", realname);
		jsonMap.put("idcard", idCard);
		jsonMap.put("phone", userPhone);
		jsonMap.put("gender", userGender);
		jsonMap.put("credits", userCredits);
		jsonMap.put("passwhint", passwhint);
		jsonMap.put("passanswer", passanswer);
		jsonMap.put("useremail", useremail);
		return SUCCESS;
	}

	public String getMesgContent() {
		return mesgContent;
	}

	public void setMesgContent(String mesgContent) {
		this.mesgContent = mesgContent;
	}

	public String getMesgTitle() {
		return mesgTitle;
	}

	public void setMesgTitle(String mesgTitle) {
		this.mesgTitle = mesgTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConsigneeEmail() {
		return consigneeEmail;
	}

	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/* 修改个人信息 */
	public String changepersonalinfo() {
		System.out.println("Enter changepersonalinfo Action!!");
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		User user = PS.finduser(userid);

		user.setUserEmail(user.getUserEmail());
		user.setUserPassword(user.getUserPassword());
		user.setUserRole(user.getUserRole());
		user.setUserState(user.getUserState());
		user.setRegisterTime(user.getRegisterTime());
		user.setUserName(userName);
		PS.ChangePassword(user);
		Userinfo userinfo = PS.GetInfo(userid);
		userinfo.setRealName(realname);
		userinfo.setIdCard(idCard);
		userinfo.setUserPhone(userPhone);
		// 友成加的
		userinfo.setPassAnswer(passanswer);
		userinfo.setPasswHint(passwhint);
		System.out.println(man);
		if (man.equals("1")) {
			userinfo.setUserGender("男");
		} else {
			userinfo.setUserGender("女");
		}
		// userinfo.setUserCredits(userCredits);
		PS.ChangeInfo(userinfo);

		// 换session里面的值
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("userName", user.getUserName());
		System.out.println("finish!!");
		return SUCCESS;
	}

	/* 修改密码action */
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	String to;// 收件人的邮件地址，必须是真实地址
	String copyto;// 抄送人邮件地址
	String subject = "Carhartt商城";// 邮件标题
	String content;// 邮件内容
	String oldpassword;
	String newpassword;
	String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String changepassword() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		System.out.println("Enter changepassword Action!!!");
		jsonMap.clear();
		User user = PS.finduser(userid);
		System.out.println("Enter changepassword Action!!!Second!!!");
		Verification verification = PS.Getverification(user);
		System.out.println(value);
		System.out.println(verification.getMessage());
		System.out.println(value.equals(verification.getMessage()));
		if (value.equals(verification.getMessage()) == true) {
			System.out.println("验证码通过！");
			if (user.getUserPassword().equals(oldpassword)) {
				user.setUserPassword(newpassword);
				PS.ChangePassword(user);
				jsonMap.put("statement", 1);
				PS.DeleteVarification(verification);
				return SUCCESS;
			} else {
				jsonMap.put("statement", 0);
				return SUCCESS;
			}
		} else {
			System.out.println("验证码不通过！");
			jsonMap.put("statement", 2);
			return SUCCESS;
		}
	}

	public String sendnumber() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		User user = PS.finduser(userid);
		to = user.getUserEmail();
		System.out.println(to);
		System.out.println("2!!!!!");
		Verification verification = VerificationCreator.create(user);
		System.out.println("3!!!!!");
		try {
			PS.SaveVarification(verification);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("4!!!!!");
		content = "尊敬的用户：\n你的账号正在修改密码。你的验证码是" + verification.getMessage()
				+ "\n若不是本人操作，请及时修改你的密码。";
		System.out.println("5!!!!!");
		Mail.sendAndCc(to, subject, content);
		System.out.println("success!!!!!");
		return SUCCESS;
	}

	/* 获取某用户所有订单的action */
	public String getorders()// 获取所有的订单
	{
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");

		System.out.println("Enter getorders action!");

		jsonMap.clear();
		List allorders = PS.AllOrders(userid);

		for (int i = 0; i < allorders.size(); i++) {

			Map<String, Object> jsonMap1 = new HashMap();
			Order one = (Order) allorders.get(i);
			jsonMap1.put("ConsigneeName", one.getConsignee().getConsigneeName());
			jsonMap1.put("time", one.getCreateTime());
			jsonMap1.put("address", one.getConsignee().getFullAddress());
			jsonMap1.put("orderid", one.getOrderId());
			jsonMap1.put("totalprice", one.getTotalPrice());
			jsonMap1.put("state", one.getOrderState());
			jsonMap1.put("orderid", one.getOrderId());
			jsonMap.put(Integer.toString(i), jsonMap1);
		}
		jsonMap.put("size", allorders.size());
		System.out.println(jsonMap.toString());
		return SUCCESS;
	}

	public String getoneorder2() {
		System.out.println("dsasa");
		jsonMap.clear();
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		Consignee consigee = PS.GetOneConsignee(order.getConsignee()
				.getConsigneeId());
		JSONObject data = new JSONObject();
		// List detial = PS.GetOrderDetial(order);
		data.put("orderId", orderid);
		data.put("createTime", "2010-11-21");
		data.put("orderState", order.getOrderState());
		data.put("consigneeName", consigee.getConsigneeName());
		data.put("consigneePhone", consigee.getConsigneePhone());
		data.put("address", consigee.getFullAddress());
		data.put("postCode", consigee.getPostcode());
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"order","item"});
		// System.out.print(JSONArray.fromObject(detial, config));
		jsonMap.put("orderDetail", data);
		System.out.println(jsonMap);
		return SUCCESS;
	}

	public String getalldetails2() {
		// System.out.println("Enter get action!!!");
		jsonMap.clear();
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		List detial = PS.GetOrderDetial(order);
		JSONObject data = new JSONObject();
		JSONArray orderDetailList = new JSONArray();
		for (int i = 0; i < detial.size(); i++) {
			Orderdetials one = (Orderdetials) detial.get(i);
			// Map<String, Object> temp = new HashMap();
			Item item = PS.GetOneItem(one.getItem().getItemId());
			data.put("item", item.getItemName());
			data.put("price", item.getItemPrice());
			data.put("count", one.getItemCount());
			data.put("remark", one.getRemark());
			data.put("detailid", one.getOrderDetialId());
			orderDetailList.add(data);
		}
		jsonMap.put("orderDetailCom", orderDetailList);
		System.out.println(jsonMap);
		return SUCCESS;
	}

	/* 获取某一订单详细详情的cation */

	String orderid;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getoneorder() {
		jsonMap.clear();
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		Consignee consigee = PS.GetOneConsignee(order.getConsignee()
				.getConsigneeId());
		List detial = PS.GetOrderDetial(order);
		jsonMap.put("CreateTime", order.getCreateTime());
		jsonMap.put("OrderState", order.getOrderState());
		jsonMap.put("ConsigneeName", consigee.getConsigneeName());
		jsonMap.put("ConsigneePhone", consigee.getConsigneePhone());
		jsonMap.put("Address", consigee.getFullAddress());
		jsonMap.put("PostCode", consigee.getPostcode());
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"order","item"});
		// System.out.print(JSONArray.fromObject(detial, config));
		System.out.println(jsonMap);
		return SUCCESS;
	}

	public String getalldetails() {
		System.out.println("Enter get action!!!");
		jsonMap.clear();
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		List detial = PS.GetOrderDetial(order);
		for (int i = 0; i < detial.size(); i++) {
			Orderdetials one = (Orderdetials) detial.get(i);
			Map<String, Object> temp = new HashMap();
			Item item = PS.GetOneItem(one.getItem().getItemId());
			temp.put("item", item.getItemName());
			temp.put("price", item.getItemPrice());
			temp.put("count", one.getItemCount());
			if (one.getRemark() != null) {
				Evaluate evaluate = PS.GetOneEvaluate(Integer.parseInt(one
						.getRemark()));
				temp.put("remark", evaluate.getEvaluateContent());
			} else
				temp.put("remark", one.getRemark());
			temp.put("detailid", one.getOrderDetialId());
			jsonMap.put(Integer.toString(i), temp);
		}
		jsonMap.put("size", detial.size());
		System.out.println(jsonMap);
		return SUCCESS;
	}

	private int state;

	public String changeordercancel() {
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		order.setOrderState((short) 2);
		PS.ChangeOrder(order);
		return SUCCESS;
	}

	public String changeorderreceive() {
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		order.setOrderState((short) 3);
		PS.ChangeOrder(order);
		return SUCCESS;
	}

	public String changeorderdelete() {
		System.out.println("Enter delete Action!");
		Order order = PS.GetOneOrder(Integer.parseInt(orderid));
		order.setOrderState((short) 4);
		System.out.println(order.getOrderState());
		PS.ChangeOrder(order);
		return SUCCESS;
	}

	/* 获取某一用户所有的反馈的action */
	int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getoneuserallmessage() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		jsonMap.clear();
		List allmessage = PS.GetOneUserAllMessage(userid);
		int i = page * 10 - 10;
		if (allmessage.size() == 0) {
			jsonMap.put("size", 0);
			return SUCCESS;
		}
		if (i > allmessage.size()) {
			jsonMap.put("size", 11);
			return SUCCESS;
		}
		int j = i + 10;
		for (int k = 0; i < j && i < allmessage.size(); i++, k++) {
			Map<String, Object> jsonMap1 = new HashMap();
			Message message = (Message) allmessage.get(i);
			jsonMap1.put("Type", message.getMesgType());
			jsonMap1.put("Title", message.getMesgTitle());
			jsonMap1.put("time", message.getMesgTime());
			jsonMap1.put("messageid", message.getMessageId());
			jsonMap1.put("reply", message.getIsreply());
			jsonMap.put(Integer.toString(k), jsonMap1);
		}
		if (i == allmessage.size()) {
			jsonMap.put("size", allmessage.size() - page * 10 + 10);
		} else {
			jsonMap.put("size", 10);
		}
		jsonMap.put("statement", 1);
		/*
		 * JsonConfig config = new JsonConfig(); config.setExcludes(new
		 * String[]{"user"}); System.out.print(JSONArray.fromObject(jsonMap,
		 * config));
		 */
		return SUCCESS;
	}

	/* 获取某一反馈的详情action */
	String messageid;

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getonemessage() {
		jsonMap.clear();
		Message message = PS.getOneMessage(Integer.parseInt(messageid));

		jsonMap.put("type", message.getMesgType());
		jsonMap.put("time", message.getMesgTime());
		jsonMap.put("content", message.getMesgContent());
		if (message.getIsreply() != 0) {
			Notification notification = PS.GetOneNotification(message
					.getIsreply());
			jsonMap.put("return", notification.getNotifiedContent());
		} else
			jsonMap.put("return", "");
		jsonMap.put("title", message.getMesgTitle());
		System.out.println(jsonMap);
		return SUCCESS;
	}

	/* 添加反馈 */
	String mesgContent;
	String mesgTitle;
	int mesgType;
	User user;

	public String addmessage() {
		jsonMap.clear();
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		user = PS.finduser(userid);
		Message newmeassge = new Message();
		newmeassge.setMesgContent(mesgContent);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		newmeassge.setMesgTime(d);
		newmeassge.setIsreply((short) 0);
		newmeassge.setMesgType((short) mesgType);
		newmeassge.setMesgTitle(mesgTitle);
		newmeassge.setUser(user);
		PS.AddMessage(newmeassge);
		jsonMap.put("statement", "1");
		return SUCCESS;
	}

	public int getMesgType() {
		return mesgType;
	}

	public void setMesgType(int mesgType) {
		this.mesgType = mesgType;
	}

	/* 获取某一用户的所有收货地址 */
	public String getaddress() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		jsonMap.clear();
		user = PS.finduser(userid);
		System.out.println(user.getUserEmail());
		List alladdress = PS.AllConsignee(user);
		for (int i = 0; i < alladdress.size(); i++) {
			Map<String, Object> jsonMap1 = new HashMap();
			Consignee adresss = (Consignee) alladdress.get(i);
			jsonMap1.put("ConsigneeName", adresss.getConsigneeName());
			jsonMap1.put("ConsigneePhone", adresss.getConsigneePhone());
			jsonMap1.put("ConsigneeAddress", adresss.getFullAddress());
			jsonMap1.put("ConsigneePostcode", adresss.getPostcode());
			jsonMap1.put("ConsigneeId", adresss.getConsigneeId());
			// 加一个字段，来确定是否显示订单
			jsonMap1.put("state", adresss.getRemark());
			jsonMap.put(Integer.toString(alladdress.size() - 1 - i), jsonMap1);
		}
		jsonMap.put("size", alladdress.size());
		/* 排除外键关键字 */
		/*
		 * JsonConfig config = new JsonConfig(); config.setExcludes(new
		 * String[]{"user"}); jsonMap.put("alladdress",
		 * JSONArray.fromObject(alladdress, config));
		 */
		System.out.println(jsonMap);
		return SUCCESS;
	}

	/* 获取某一收货地址详情 */
	String consigneeid;

	public String getConsigneeid() {
		return consigneeid;
	}

	public void setConsigneeid(String consigneeid) {
		this.consigneeid = consigneeid;
	}

	public String getoneaddress() {
		jsonMap.clear();
		Consignee consignee = PS.GetOneConsignee(Integer.parseInt(consigneeid));
		jsonMap.put("address", consignee);
		return SUCCESS;
	}

	/* 添加入收货地址 */
	String consigneeEmail;
	String consigneeName;
	String consigneePhone;
	String consigneeTel;
	String fullAddress;
	String postcode;

	public String addaddress() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		System.out.println("Enter addaress Action!!!");
		jsonMap.clear();
		Consignee consignee = new Consignee();
		user = PS.finduser(userid);
		System.out.println(user.getUserEmail());
		consignee.setUser(user);
		System.out.println(consignee.getUser().getUserEmail());
		consignee.setConsigneeName(consigneeName);
		System.out.println(consignee.getConsigneeName());
		consignee.setConsigneePhone(consigneePhone);
		consignee.setFullAddress(fullAddress);
		consignee.setPostcode(postcode);
		consignee.setRemark("1");
		PS.AddConsignee(consignee);
		jsonMap.put("newid", consignee.getConsigneeId());
		jsonMap.put("statement", "1");
		return SUCCESS;
	}

	int num;

	public String deladdress() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		System.out.println(num);
		user = PS.finduser(userid);
		System.out.println(user.getUserEmail());
		List alladdress = PS.AllConsignee(user);
		/*
		 * int count = 0; int i; for (i = alladdress.size(); i > 0; i--) { if
		 * (count == num + 1) break; if (((Consignee)
		 * alladdress.get(i)).getRemark().equals("1")) count++;
		 * 
		 * }
		 */
		int i = 0;
		int count = 0;
		for (i = 0; i < alladdress.size(); i++) {

			if (((Consignee) alladdress.get(i)).getRemark().equals("1")) {
				count++;
			}
			if (count == num)
				break;
		}
		System.out.print("hahahah " + i);
		Consignee adresss = (Consignee) alladdress.get(i);
		/* PS.DeleteConsignee(adresss); */
		// 删除地址只是将其标志为0
		adresss.setRemark("0");
		PS.ChangeConsignee(adresss);
		return SUCCESS;
	}

	/* 获得某一用户的购物车 */
	public String getoneshoppingcart() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		jsonMap.clear();
		user = PS.finduser(userid);
		List shoppingcart = PS.AllGoodofShoppingcart(user);
		for (int i = 0; i < shoppingcart.size(); i++) {
			Shoppingcart oneshoppingcart = (Shoppingcart) shoppingcart.get(i);
			Item item = PS.GetOneItem(oneshoppingcart.getItem().getItemId());
			Map<String, Object> jsonMaptemp = new HashMap();
			jsonMaptemp.put("itemid", item.getItemId());
			jsonMaptemp.put("itemname", item.getItemName());
			jsonMaptemp.put("number", oneshoppingcart.getPurchaseNum());
			jsonMaptemp.put("itemprice", item.getItemPrice());
			jsonMaptemp.put("cartid", oneshoppingcart.getCartId());
			jsonMap.put(Integer.toString(i), jsonMaptemp);
		}
		System.out.println(jsonMap);
		return SUCCESS;
	}

	int cartid;

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String delshoppingcart() {
		System.out.print(cartid);
		Shoppingcart oneshoppingcart = PS.GetShoppingcart(cartid);
		PS.DeleteGoodFromShoppingcart(oneshoppingcart);
		return SUCCESS;
	}

	int change;

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public String changeshoppingcart() {
		if (change == 0) {
			Shoppingcart oneshoppingcart = PS.GetShoppingcart(cartid);
			oneshoppingcart
					.setPurchaseNum(oneshoppingcart.getPurchaseNum() - 1);
			PS.SaveChangeCart(oneshoppingcart);
		} else {
			Shoppingcart oneshoppingcart = PS.GetShoppingcart(cartid);
			oneshoppingcart
					.setPurchaseNum(oneshoppingcart.getPurchaseNum() + 1);
			PS.SaveChangeCart(oneshoppingcart);
		}
		return SUCCESS;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getFavorites() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		System.out.println((Integer) session.getAttribute("userId"));
		JSONArray favorites = null;
		try {
			favorites = PS.getFavorites((Integer) session
					.getAttribute("userId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("favorites", favorites);
		jsonMap.put("success", true);
		return SUCCESS;
	}

	public String delFavorite() {
		System.out.println(favoriteId);
		PS.delFavorite(Integer.parseInt(favoriteId));
		jsonMap.put("success", true);
		return SUCCESS;
	}

	public String addToCart() {
		Shoppingcart cart = new Shoppingcart();
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = new User();
		user.setUserId((Integer) session.getAttribute("userId"));
		// user.setUserId(userid);
		cart.setUser(user);
		cart.setPurchaseNum(1);
		cart.setAddTime(new Timestamp(System.currentTimeMillis()));
		Item item = new Item();
		item.setItemId(Integer.parseInt(itemId));
		cart.setItem(item);
		userConsumingService.addShoppingCart(cart);
		jsonMap.put("success", true);
		return SUCCESS;
	}

	float totalprice;
	String detail;
	String context;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String addorder() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		Order order = new Order();
		user = PS.finduser(userid);
		order.setUser(user);
		Consignee consignee = PS.GetOneConsignee(Integer.parseInt(consigneeid));
		order.setConsignee(consignee);
		order.setTotalPrice(totalprice);
		order.setEvaluationGrade((short) 0);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		order.setCreateTime(d);
		order.setOrderState((short) 0);
		PS.AddOrder(order);

		String[] sArray = detail.split("@@");
		for (int i = 0; i < sArray.length; i++) {
			String[] sArray2 = sArray[i].split("##");
			Orderdetials orderdetials = new Orderdetials();
			orderdetials.setItem(PS.GetOneItem(Integer.parseInt(sArray2[0])));
			orderdetials.setOrder(order);
			orderdetials.setItemCount(Integer.parseInt(sArray2[1]));
			PS.SaveOrderdetials(orderdetials);
			Shoppingcart oneshoppingcart = PS.GetShoppingcart(Integer
					.parseInt(sArray2[2]));
			PS.DeleteGoodFromShoppingcart(oneshoppingcart);
		}
		return SUCCESS;
	}

	int detailid;
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String addevaluate() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		user = PS.finduser(userid);
		Orderdetials ordertials = PS.GetoneDetail(detailid);
		Item item = PS.GetOneItem(ordertials.getItem().getItemId());
		Evaluate evaluate = new Evaluate();
		evaluate.setItem(item);
		evaluate.setUser(user);
		evaluate.setEvaluateTitle("暂无");
		evaluate.setEvaluateState((short) 0);
		evaluate.setEvaluateContent(text);
		Timestamp d = new Timestamp(System.currentTimeMillis());
		evaluate.setEvaluateTime(d);
		PS.SaveEvaluate(evaluate);
		ordertials.setRemark(Integer.toString(evaluate.getEvaluateId()));
		PS.SaveOrderdetials(ordertials);
		return SUCCESS;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}

	public String getannouncement() {
		ActionContext context = ActionContext.getContext();
		userid = (Integer) context.getSession().get("userId");
		Userinfo userinfo = PS.GetInfo(userid);
		userCredits = userinfo.getUserCredits();

		List list = PS.AllAnnouncement();
		jsonMap.clear();
		
		for (int i = 0; i < 3; i++) {
			Announcement announcement = (Announcement) list.get(list.size() - i
					- 1);
			Map<String, Object> jsonMaptemp = new HashMap();
			jsonMaptemp.put("Title", announcement.getAnnounceTitle());
			jsonMaptemp.put("Content", announcement.getAnnounceContent());
			jsonMap.put(Integer.toString(i), jsonMaptemp);
		}
		jsonMap.put("rank", userCredits);
		System.out.println(jsonMap);
		return SUCCESS;
	}
}
