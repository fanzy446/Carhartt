package com.carhartt.man.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import test.weapon.ClassTree;
import test.weapon.ModClassModel;
import test.weapon.NewClassModel;
import test.weapon.SubClass;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.dao.BroadclassDAO;
import com.carhartt.man.dao.ItemDAO;
import com.carhartt.man.dao.SubclassDAO;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Commodity;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.Subclass;
import com.carhartt.man.service.ComManService;

public class ComManServiceImpl implements ComManService {
	private ItemDAO itemDAO;
	private BroadclassDAO broadclasaDAO;
	private SubclassDAO subclassDao;
	private final static String SAVE_PATH = "pages/source/image/item/";

	public SubclassDAO getSubclassDao() {
		return subclassDao;
	}

	public void setSubclassDao(SubclassDAO subclassDao) {
		this.subclassDao = subclassDao;
	}

	public BroadclassDAO getBroadclasaDAO() {
		return broadclasaDAO;
	}

	public void setBroadclasaDAO(BroadclassDAO broadclasaDAO) {
		this.broadclasaDAO = broadclasaDAO;
	}

	public String addCommodity(Commodity c) {
		// TODO Auto-generated method stub
		Item item = new Item();
		item.setItemName(c.getItemName());
		item.setBroadclass((Broadclass) (broadclasaDAO.findByBroadClassName(c
				.getBroadClassName()).get(0)));
		item.setSubclass((Subclass) subclassDao.findBySubClassName(
				c.getSubClassName()).get(0));

		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = (Date) f.parseObject(c.getItemPubDate());
			Timestamp ts = new Timestamp(d.getTime());
			item.setItemPubDate(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		item.setItemPrice(c.getItemPrice());
		item.setItemCount(Integer.valueOf(c.getItemCount()));
		item.setItemUnit(c.getItemUnit());
		item.setItemDesc(c.getItemDesc());
		item.setState(true);
		item.setRemark(c.getRemark());
		itemDAO.save(item);
		item.setItemPhoto(item.getItemId().toString());
		itemDAO.update(item);
		return item.getItemId().toString();
	}

	public String delCommodity(String itemId) {
		// TODO Auto-generated method stub
		try {
			Item item = new Item();
			item = itemDAO.findById(Integer.parseInt(itemId));
			item.setState(false);
			itemDAO.update(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String modCommodity(Commodity c) {
		// TODO Auto-generated method stub
		try {
			System.out.println(c.getItemName());
			Item item = itemDAO.findById(Integer.parseInt(c.getItemId()));
			item.setItemName(c.getItemName());
			item.setBroadclass((Broadclass) (broadclasaDAO
					.findByBroadClassName(c.getBroadClassName()).get(0)));
			item.setSubclass((Subclass) subclassDao.findBySubClassName(
					c.getSubClassName()).get(0));
			Format f = new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			try {
				d = (Date) f.parseObject(c.getItemPubDate());
				Timestamp ts = new Timestamp(d.getTime());
				item.setItemPubDate(ts);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item.setItemPrice(c.getItemPrice());
			item.setItemCount(Integer.valueOf(c.getItemCount()));
			item.setItemUnit(c.getItemUnit());
			item.setItemDesc(c.getItemDesc());
			item.setRemark(c.getRemark());
			item.setItemPhoto(c.getItemId());
			itemDAO.update(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public JSONArray getBroadClass() {
		// TODO Auto-generated method stub
		System.out.println("getBroadClass()");
		JSONObject data = new JSONObject();
		JSONArray bcList = new JSONArray();
		Iterator iter = broadclasaDAO.getBroadClass().iterator();
		while (iter.hasNext()) {
			Broadclass bc = (Broadclass) iter.next();
			data.put("broadClassId", bc.getBroadClassId());
			data.put("broadClassName", bc.getBroadClassName());
			bcList.add(data);
		}
		return bcList;
	}

	public JSONArray getBroadSubClass() {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray list = new JSONArray();
		Iterator iter = broadclasaDAO.getBroadWithSub().iterator();
		while (iter.hasNext()) {
			Broadclass bc = (Broadclass) iter.next();
			Iterator sub_iter = bc.getSubclasses().iterator();
			while (sub_iter.hasNext()) {
				Subclass sub = (Subclass) sub_iter.next();
				data.put("broadClassName", bc.getBroadClassName());
				data.put("subClassName", sub.getSubClassName());
				list.add(data);
			}
		}
		return list;
	}

	public void addItem(Item item) {
		// item�ľ�����ϢҪ�ӿͻ��˻��
		this.itemDAO.save(item);

	}

	public void updateItem(Item item) {
		// ��ǰ�˴�������item���
		this.itemDAO.save(item);
	}

	public void deleteItem(Integer id) {
		// TODO Auto-generated method stub
		Item item = new Item();
		// id����ǰ̨������
		item.setItemId(id);
		this.itemDAO.delete(item);
	}

	public Item getItem(Integer id) {
		return this.itemDAO.findById(id);
	}

	public List getItemList(String itemName, String dateFrom, String dateTo,
			String priceFrom, String priceTo, String countFrom, String countTo,
			String parentName, String nodeName, int start, int limit) {
		// TODO Auto-generated method stub
		Broadclass broadclass = null;
		Subclass subclass = null;
		if (nodeName != null && nodeName.length() != 0) {
			if (!nodeName.equals("商品类别")) {
				if (parentName != null && parentName.length() != 0) {
					broadclass = (Broadclass) broadclasaDAO
							.findByBroadClassName(parentName).get(0);
					subclass = (Subclass) subclassDao.findBySubClassName(
							nodeName).get(0);
				} else {
					broadclass = (Broadclass) broadclasaDAO
							.findByBroadClassName(nodeName).get(0);
				}
			}
		}

		List<Commodity> itemList = new ArrayList<Commodity>();
		List result = itemDAO.getItemListOfMan(itemName, dateFrom, dateTo,
				priceFrom, priceTo, countFrom, countTo, broadclass, subclass,
				start, limit);
		// System.out.println(((List)result.get(0)).get(0)));
		Iterator iter = ((List) result.get(0)).iterator();
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			Commodity cdt = new Commodity();
			cdt.setBroadClassName(item.getBroadclass().getBroadClassName());
			cdt.setItemCount(String.valueOf(item.getItemCount()));
			cdt.setItemDesc(item.getItemDesc());
			cdt.setItemId(String.valueOf(item.getItemId()));
			cdt.setItemName(item.getItemName());
			cdt.setItemPrice(item.getItemPrice());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.format(item.getItemPubDate());
			cdt.setItemPubDate(sdf.format(item.getItemPubDate()));
			cdt.setItemUnit(item.getItemUnit());
			cdt.setRemark(item.getRemark());
			cdt.setSubClassName(item.getSubclass().getSubClassName());
			cdt.setItemPhoto(SAVE_PATH + item.getItemPhoto() + ".jpg");
			itemList.add(cdt);
		}
		List list = new ArrayList();
		list.add(itemList);
		list.add(result.get(1));
		return list;
	}

	// ======================================================
	// ======================类别管理========================
	// ======================================================

	public String addClass(String clazz, NewClassModel newClassModel) {
		// TODO Auto-generated method stub
		if (clazz.equals("broad")) {
			// 添加大类
			Broadclass b = new Broadclass();
			b.setBroadClassName(newClassModel.getNewBroadName());
			broadclasaDAO.save(b);
		} else {
			// 在大类下添加小类
			try {
				Subclass s = new Subclass();
				List lb = broadclasaDAO.findByBroadClassName(newClassModel
						.getOldBroadName());
				s.setBroadclass((Broadclass) lb.get(0));
				s.setSubClassName(newClassModel.getNewSubName());
				subclassDao.save(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List getClassTree() {
		// TODO Auto-generated method stub
		List<ClassTree> bctList = new ArrayList<ClassTree>();
		Iterator iter = broadclasaDAO.getBroadWithSub().iterator();
		while (iter.hasNext()) {
			Broadclass bc = (Broadclass) iter.next();
			List<ClassTree> sctList = new ArrayList<ClassTree>();
			Iterator sub_iter = bc.getSubclasses().iterator();
			while (sub_iter.hasNext()) {
				Subclass sub = (Subclass) sub_iter.next();
				sctList
						.add(new ClassTree(sub.getSubClassId(), sub
								.getSubClassName(), true, bc
								.getBroadClassName(), null));
			}
			bctList.add(new ClassTree(bc.getBroadClassId(), bc
					.getBroadClassName(), false, null, sctList));
		}
		return bctList;
	}

	public String modClass(ModClassModel modClassModel) {
		// TODO Auto-generated method stub
		System.out.println(modClassModel.getModType());
		if (modClassModel.getModType().equals("broad")) {
			Broadclass b = (Broadclass) broadclasaDAO.findByBroadClassName(
					modClassModel.getOldBroadName()).get(0);
			b.setBroadClassName(modClassModel.getNewBroadName());
			broadclasaDAO.update(b);

		} else {
			Subclass s = (Subclass) subclassDao.findBySubClassName(
					modClassModel.getOldSubName()).get(0);
			Broadclass b = (Broadclass) broadclasaDAO.findByBroadClassName(
					modClassModel.getNewBroadName()).get(0);
			s.setBroadclass(b);
			s.setSubClassName(modClassModel.getNewSubName());
			subclassDao.update(s);
		}
		return null;
	}

	public JSONObject deleteClass(String parentName, String nodeName) {
		// TODO Auto-generated method stub
		System.out.println(parentName + "========");
		System.out.println(nodeName + "========");
		JSONObject data = new JSONObject();
		if (parentName.length() == 0) {
			// 删除大类
			Broadclass b = (Broadclass) broadclasaDAO.findByBroadClassName(
					nodeName).get(0);
			List ls = subclassDao.findByBroadClass(b);
			if (ls.size() == 0) {
				System.out.println("大类无小类，可以删除");
				broadclasaDAO.delete(b);
				data.put("errCode", 0);
				data.put("errMsg", "删除成功");
				return data;
			} else {
				System.out.println("大类有小类，不可以删除");
				data.put("errCode", 1);
				data.put("errMsg", "请先删除大类目录下的小类");
				return data;
			}

		} else {
			// 删除小类
			Subclass s = (Subclass) subclassDao.findBySubClassName(nodeName)
					.get(0);
			List ls = itemDAO.findBySubclass(s);
			if (ls.size() == 0) {
				System.out.println("无商品，可以删除");
				subclassDao.delete(s);
				data.put("errCode", 0);
				data.put("errMsg", "删除成功");
				return data;
			} else {
				System.out.println("有商品，不可删除");
				data.put("errCode", 2);
				data.put("errMsg", "请先删除小类目录下的商品");
				return data;
			}
		}
	}

}
