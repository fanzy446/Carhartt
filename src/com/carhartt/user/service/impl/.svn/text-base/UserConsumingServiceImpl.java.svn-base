package com.carhartt.user.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.dao.EvaluateDAO;
import com.carhartt.man.dao.FavoritesDAO;
import com.carhartt.man.dao.ItemDAO;
import com.carhartt.man.dao.OrderDAO;
import com.carhartt.man.dao.OrderdetialsDAO;
import com.carhartt.man.dao.ShoppingcartDAO;
import com.carhartt.man.dao.UserDAO;
import com.carhartt.man.dao.VSaleDAO;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Evaluate;
import com.carhartt.man.model.Favorites;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.SearchCondition;
import com.carhartt.man.model.Shoppingcart;
import com.carhartt.man.model.Subclass;
import com.carhartt.man.model.VSale;
import com.carhartt.user.service.UserConsumingService;

public class UserConsumingServiceImpl implements UserConsumingService {

	private UserDAO userDao;
	private ItemDAO itemDao;
	private FavoritesDAO favoritesDao;
	private EvaluateDAO evaluateDao;
	private ShoppingcartDAO shoppingcartDao;
	private VSaleDAO vsaleDao;

	public List<Item> searchItems(SearchCondition condition, int start,
			int limit) {
		// TODO Auto-generated method stub
		return itemDao.getItemList(condition, start, limit);
	}

	public int getSaleForItem(Item item) {
		VSale temp = vsaleDao.findById(item.getItemId());
		System.out.println(JSONObject.fromObject(temp));
		return temp.getSale();
	}

	public boolean addFavorites(Favorites favorites) {
		// TODO Auto-generated method stub
		if (favoritesDao.isFavortiteExist(favorites.getUser(),
				favorites.getItem())) {
			return false;
		} else {
			favoritesDao.save(favorites);
		}
		return true;
	}

	// 可能要加个itemdetail表
	public Item checkComDetail(Item item) {
		// TODO Auto-generated method stub
		return itemDao.findByIdWithClass(item.getItemId());
	}

	public List<Evaluate> getComments(Item item, int start, int limit) {
		// TODO Auto-generated method stub
		return evaluateDao.fetchEvaluate(item, start, limit);
	}

	public long getCommentCount(Item item) {
		return evaluateDao.getEvaluatePageNum(item);
	}

	public boolean comment(Evaluate evaluate) {
		// TODO Auto-generated method stub
		evaluateDao.save(evaluate);
		return true;
	}

	public boolean addShoppingCart(Shoppingcart cart) {
		// TODO Auto-generated method stub
		List result = shoppingcartDao.findExistCart(cart.getUser(),
				cart.getItem());
		if (result.size() == 0) {
			shoppingcartDao.save(cart);
		} else if (result.size() == 1) {
			Shoppingcart exist = (Shoppingcart) result.get(0);
			exist.setPurchaseNum(cart.getPurchaseNum() + exist.getPurchaseNum());
			shoppingcartDao.attachDirty(exist);
		} else {
			return false;
		}
		return true;
	}

	public ItemDAO getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	public FavoritesDAO getFavoritesDao() {
		return favoritesDao;
	}

	public void setFavoritesDao(FavoritesDAO favoritesDao) {
		this.favoritesDao = favoritesDao;
	}

	public EvaluateDAO getEvaluateDao() {
		return evaluateDao;
	}

	public void setEvaluateDao(EvaluateDAO evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	public ShoppingcartDAO getShoppingcartDao() {
		return shoppingcartDao;
	}

	public void setShoppingcartDao(ShoppingcartDAO shoppingcartDao) {
		this.shoppingcartDao = shoppingcartDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public VSaleDAO getVsaleDao() {
		return vsaleDao;
	}

	public void setVsaleDao(VSaleDAO vsaleDao) {
		this.vsaleDao = vsaleDao;
	}

}
