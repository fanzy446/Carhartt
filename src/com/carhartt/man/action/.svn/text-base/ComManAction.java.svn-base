package com.carhartt.man.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import test.weapon.ModClassModel;
import test.weapon.NewClassModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.carhartt.man.model.Commodity;
import com.carhartt.man.service.ComManService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ComManAction extends ActionSupport {
	private ComManService comManService;
	private Commodity commodity;
	private String clazz;
	private NewClassModel newClassModel;
	private ModClassModel modClassModel;
	private String params;
	private String broadClassName;
	private Map<String, Object> jsonMap;
	// ClassMan:searchCommodity
	private String searchMode;
	private String nodeName;
	private String parentName;
	// 图片上传
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;
	// 删除商品
	private String itemId;
	// 查询商品
	private String tbar_itemName;
	private String tbar_dateFrom;
	private String tbar_dateTo;
	private String tbar_priceFrom;
	private String tbar_priceTo;
	private String tbar_countFrom;
	private String tbar_countTo;
	private int start;
	private int limit;

	public String addCommodity() {
		try {
			String id = comManService.addCommodity(commodity);
			System.out.println("===========upload============");
			System.out.println("title:" + title);
			System.out.println("upload:" + upload);
			System.out.println("uploadContentType:" + uploadContentType);
			System.out.println("uploadFileName:" + uploadFileName);
			System.out.println("savePath:" + savePath);
			System.out.println("id:" + id);

			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
					+ id + ".jpg");
			FileInputStream fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			System.out.println("end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonMap.clear();
		jsonMap.put("success", true);
		return Action.SUCCESS;
	}

	public String delCommodity() {
		comManService.delCommodity(itemId);
		jsonMap.clear();
		jsonMap.put("success", true);
		return Action.SUCCESS;
	}

	public String modCommodity() {
		comManService.modCommodity(commodity);
		if (upload != null) {
			// 删除图片
			File file = new File(getSavePath() + "\\" + commodity.getItemId()
					+ ".jpg");
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			// 存储图片
			try {
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + commodity.getItemId() + ".jpg");
				FileInputStream fis = new FileInputStream(getUpload());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jsonMap.clear();
		jsonMap.put("success", true);
		return Action.SUCCESS;
	}

	public String searchCommodity() {
		try {
			System.out.println("tbar_itemName:" + tbar_itemName);
			System.out.println("tbar_dateFrom:" + tbar_dateFrom);
			System.out.println("tbar_dateTo:" + tbar_dateTo);
			System.out.println("tbar_priceFrom:" + tbar_priceFrom);
			System.out.println("tbar_priceTo:" + tbar_priceTo);
			System.out.println("tbar_countFrom:" + tbar_countFrom);
			System.out.println("tbar_countTo:" + tbar_countTo);
			System.out.println("start:" + start);
			System.out.println("limit:" + limit);
			System.out.println("SearchByClass Properties:");
			System.out.println("parentName:" + parentName);
			System.out.println("nodeName:" + nodeName);
			List result = comManService.getItemList(tbar_itemName,
					tbar_dateFrom, tbar_dateTo, tbar_priceFrom, tbar_priceTo,
					tbar_countFrom, tbar_countTo, parentName, nodeName, start,
					limit);
			jsonMap.clear();
			List commodities = (List) result.get(0);
			jsonMap.put("commodities", commodities);
			jsonMap.put("totalCount", result.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String findAllBroadClass() {
		jsonMap.clear();
		JSONArray bcList = comManService.getBroadClass();
		jsonMap.put("broadClass", bcList);
		return Action.SUCCESS;
	}

	public String findAllSubClass() {
		jsonMap.clear();
		JSONArray subClass = comManService.getBroadSubClass();
		jsonMap.put("success", true);
		jsonMap.put("subClass", subClass);
		return Action.SUCCESS;
	}

	// ======================================================
	// ======================类别管理========================
	// ======================================================

	public String addClass() {
		comManService.addClass(getClazz(), getNewClassModel());
		jsonMap.clear();
		jsonMap.put("success", true);
		return Action.SUCCESS;
	}

	public String readClass() {
		jsonMap.clear();
		List children = comManService.getClassTree();
		jsonMap.put("success", true);
		jsonMap.put("children", children);
		return Action.SUCCESS;
	}

	public String deleteClass() {
		jsonMap.clear();
		try {
			JSONObject json = comManService.deleteClass(parentName, nodeName);
			jsonMap.put("errCode", json.get("errCode"));
			jsonMap.put("errMsg", json.get("errMsg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String modClass() {
		comManService.modClass(modClassModel);
		jsonMap.clear();
		jsonMap.put("success", true);
		return Action.SUCCESS;
	}

	public ComManAction() {
		jsonMap = new HashMap();
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public ComManService getComManService() {
		return comManService;
	}

	public void setComManService(ComManService comManService) {
		this.comManService = comManService;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getBroadClassName() {
		return broadClassName;
	}

	public void setBroadClassName(String broadClassName) {
		this.broadClassName = broadClassName;
	}

	public NewClassModel getNewClassModel() {
		return newClassModel;
	}

	public void setNewClassModel(NewClassModel newClassModel) {
		this.newClassModel = newClassModel;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public ModClassModel getModClassModel() {
		return modClassModel;
	}

	public void setModClassModel(ModClassModel modClassModel) {
		this.modClassModel = modClassModel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTbar_itemName() {
		return tbar_itemName;
	}

	public void setTbar_itemName(String tbarItemName) {
		tbar_itemName = tbarItemName;
	}

	public String getTbar_dateFrom() {
		return tbar_dateFrom;
	}

	public void setTbar_dateFrom(String tbarDateFrom) {
		tbar_dateFrom = tbarDateFrom;
	}

	public String getTbar_dateTo() {
		return tbar_dateTo;
	}

	public void setTbar_dateTo(String tbarDateTo) {
		tbar_dateTo = tbarDateTo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getTbar_priceFrom() {
		return tbar_priceFrom;
	}

	public void setTbar_priceFrom(String tbarPriceFrom) {
		tbar_priceFrom = tbarPriceFrom;
	}

	public String getTbar_priceTo() {
		return tbar_priceTo;
	}

	public void setTbar_priceTo(String tbarPriceTo) {
		tbar_priceTo = tbarPriceTo;
	}

	public String getTbar_countFrom() {
		return tbar_countFrom;
	}

	public void setTbar_countFrom(String tbarCountFrom) {
		tbar_countFrom = tbarCountFrom;
	}

	public String getTbar_countTo() {
		return tbar_countTo;
	}

	public void setTbar_countTo(String tbarCountTo) {
		tbar_countTo = tbarCountTo;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
