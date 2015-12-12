package com.carhartt.user.service;

import java.util.List;
import java.util.Map;

import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Evaluate;
import com.carhartt.man.model.Favorites;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.SearchCondition;
import com.carhartt.man.model.Shoppingcart;
import com.carhartt.man.model.Subclass;

public interface UserConsumingService {
	public List searchItems(SearchCondition condition, int start, int limit);
	public int getSaleForItem(Item item);
	public boolean addFavorites(Favorites favorites);
	public Item checkComDetail(Item item);
	public List getComments(Item item, int pageNow, int perPage);
	public long getCommentCount(Item item);
	public boolean comment(Evaluate evaluate);
	public boolean addShoppingCart(Shoppingcart cart);
}
