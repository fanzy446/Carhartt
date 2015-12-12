package com.carhartt.man.service;

import java.util.List;

import test.weapon.ClassTree;
import test.weapon.ModClassModel;
import test.weapon.NewClassModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.model.Commodity;
import com.carhartt.man.model.Item;

public interface ComManService {
	public String addCommodity(Commodity commodity);

	public String delCommodity(String itemId);

	public String modCommodity(Commodity commodity);

	public List getClassTree();

	public JSONArray getBroadClass();

//	public JSONArray getSubClass(String bcName);

	public JSONArray getBroadSubClass();

	public void deleteItem(Integer id);

	public Item getItem(Integer id);

	public List getItemList(String itemName, String dateFrom, String dateTo,
			String priceFrom, String priceTo, String countFrom, String countTo,
			String parentName, String nodeName, int start, int limit);

	public void addItem(Item item);

	public void updateItem(Item item);

	public String addClass(String clazz, NewClassModel newClassModel);

	public String modClass(ModClassModel modClassModel);

	public JSONObject deleteClass(String parentName, String nodeName);
}
