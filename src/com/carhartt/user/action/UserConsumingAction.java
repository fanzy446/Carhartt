package com.carhartt.user.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.carhartt.man.model.Evaluate;
import com.carhartt.man.model.Favorites;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.SearchCondition;
import com.carhartt.man.model.Shoppingcart;
import com.carhartt.man.model.User;
import com.carhartt.man.service.BasicManService;
import com.carhartt.user.service.UserConsumingService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserConsumingAction extends ActionSupport {

	private UserConsumingService userConsumingService;
	private SearchCondition condition;
	private int start;
	private int limit;
	private Favorites favorites;
	private Evaluate evaluate;
	private Shoppingcart cart;
	private Item item;
	private Map<String, Object> jsonMap = new HashMap<String, Object>();

	public String searchItems() {
		try {
			jsonMap.clear();
			// System.out.println(JSONObject.fromObject(condition));
			List<Item> list = userConsumingService.searchItems(condition,
					start, limit);
			JsonConfig jsonConfigForClass = new JsonConfig();
			jsonConfigForClass.setExcludes(new String[] { "broadclass",
					"subclass", "hibernateLazyInitializer", "evaluates",
					"orderdetialses", "favoriteses", "shoppingcarts" });
			System.out.println(JSONArray.fromObject(list, jsonConfigForClass));
			jsonMap.put("items", JSONArray.fromObject(list, jsonConfigForClass));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String addFavorites() {
		try {
			jsonMap.clear();
			int code = 1;
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			if (session.getAttribute("userId") != null) {
				User user = new User();
				user.setUserId((Integer) session.getAttribute("userId"));
				favorites.setFavoriteTime(new Timestamp(System
						.currentTimeMillis()));
				favorites.setUser(user);
				if (userConsumingService.addFavorites(favorites)) {
					code = 0;
				} else {
					code = -1;
				}
			} else {
				code = -2;
			}
			jsonMap.put("code", code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String checkComDetail() {
		jsonMap.clear();
		try {
			Item itemDetail = userConsumingService.checkComDetail(item);
			JsonConfig configForItem = new JsonConfig();
			configForItem.setExcludes(new String[] { "broadclass",
					"hibernateLazyInitializer", "items", "evaluates",
					"orderdetialses", "favoriteses", "shoppingcarts" });
			JsonConfig configForEvaluate = new JsonConfig();
			configForEvaluate.setExcludes(new String[] { "item", "favoriteses",
					"userinfos", "consignees", "hibernateLazyInitializer",
					"evaluates", "orders", "messages", "shoppingcarts",
					"notifications" });
			JSONObject itemDetails = JSONObject.fromObject(itemDetail,
					configForItem);
			System.out.println(itemDetails);
			itemDetails.put("sale", userConsumingService.getSaleForItem(item));
			jsonMap.put("itemDetail", itemDetails);
			jsonMap.put("comment", JSONArray.fromObject(
					userConsumingService.getComments(item, start, limit),
					configForEvaluate));
			jsonMap.put("commentSum",
					userConsumingService.getCommentCount(item));
			condition.setSubclass(itemDetail.getSubclass());
			List<Item> list = userConsumingService.searchItems(condition, 0, 4);
			JsonConfig jsonConfigForClass = new JsonConfig();
			jsonConfigForClass.setExcludes(new String[] { "broadclass",
					"subclass", "hibernateLazyInitializer", "evaluates",
					"orderdetialses", "favoriteses", "shoppingcarts" });
			// System.out.println(JSONObject.fromObject(list,
			// jsonConfigForClass));
			jsonMap.put("items", JSONArray.fromObject(list, jsonConfigForClass));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String getComments() {
		jsonMap.clear();
		JsonConfig configForEvaluate = new JsonConfig();
		configForEvaluate.setExcludes(new String[] { "item", "favoriteses",
				"userinfos", "consignees", "hibernateLazyInitializer",
				"evaluates", "orders", "messages", "shoppingcarts",
				"notifications" });
		jsonMap.put("comment", JSONArray.fromObject(
				userConsumingService.getComments(item, start, limit),
				configForEvaluate));
		System.out.println(JSONArray.fromObject(
				userConsumingService.getComments(item, start, limit),
				configForEvaluate));
		return Action.SUCCESS;
	}

	public String comment() {
		jsonMap.clear();
		if (userConsumingService.comment(evaluate)) {
			return Action.SUCCESS;
		}
		jsonMap.put("hint", "评论失败");
		return Action.ERROR;
	}

	public String addShoppingCart() {
		jsonMap.clear();
		int code = 1;
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (session.getAttribute("userId") != null) {
			cart.setAddTime(new Timestamp(System.currentTimeMillis()));

			User user = new User();
			user.setUserId((Integer) session.getAttribute("userId"));
			cart.setUser(user);
			if (userConsumingService.addShoppingCart(cart)) {
				code = 0;
			} else {
				code = -1;
			}
		} else {
			code = -2;
		}
		jsonMap.put("code", code);
		return Action.SUCCESS;
	}

	public UserConsumingService getUserConsumingService() {
		return userConsumingService;
	}

	public void setUserConsumingService(
			UserConsumingService userConsumingService) {
		this.userConsumingService = userConsumingService;
	}

	public SearchCondition getCondition() {
		return condition;
	}

	public void setCondition(SearchCondition condition) {
		this.condition = condition;
	}

	public Favorites getFavorites() {
		return favorites;
	}

	public void setFavorites(Favorites favorites) {
		this.favorites = favorites;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public Shoppingcart getCart() {
		return cart;
	}

	public void setCart(Shoppingcart cart) {
		this.cart = cart;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

}
