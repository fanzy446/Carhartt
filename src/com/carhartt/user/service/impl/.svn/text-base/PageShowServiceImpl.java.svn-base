package com.carhartt.user.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import test.weapon.ClassTree;

import com.carhartt.man.dao.AdinadpDAO;
import com.carhartt.man.dao.AdpositionDAO;
import com.carhartt.man.dao.AdvertisementDAO;
import com.carhartt.man.dao.AnnouncementDAO;
import com.carhartt.man.dao.BroadclassDAO;
import com.carhartt.man.dao.ItemDAO;
import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Subclass;
import com.carhartt.user.service.PageShowService;

public class PageShowServiceImpl implements PageShowService {

	private ItemDAO itemDao;
	private BroadclassDAO broadclassDao;
	private AdpositionDAO adpositionDao;
	private AnnouncementDAO announcementDao;
	private AdinadpDAO adinadpDao;
	
	public List fetchLatestItems(Broadclass broadclass, int num) {
		// TODO Auto-generated method stub
		return itemDao.fetchLatestItemOfBroad(broadclass, num);
	}
	
	public List fetchClasses() {
		// TODO Auto-generated method stub
		return broadclassDao.getBroadWithSub();
	}

	public List fetchAnouncement(int pageNow, int perPage) {
		// TODO Auto-generated method stub		
		return announcementDao.fetchTopRecord(pageNow, perPage);
	}

	public List fetchAdvertisement(int kind) {
		// TODO Auto-generated method stub
		Adposition adposition = new Adposition();
		System.out.println(kind);
		adposition.setKind((short)kind);
		List<Adposition> results = adpositionDao.findByExample(adposition);
		for(Adposition adp : results)
		{
			adp.setAdinadps(new HashSet(adinadpDao.fetchValidAd(adp)));
		}
		return results;
	}

	public BroadclassDAO getBroadclassDao() {
		return broadclassDao;
	}

	public void setBroadclassDao(BroadclassDAO broadclassDao) {
		this.broadclassDao = broadclassDao;
	}

	public AdpositionDAO getAdpositionDao() {
		return adpositionDao;
	}

	public void setAdpositionDao(AdpositionDAO adpositionDao) {
		this.adpositionDao = adpositionDao;
	}

	public AnnouncementDAO getAnnouncementDao() {
		return announcementDao;
	}

	public void setAnnouncementDao(AnnouncementDAO announcementDao) {
		this.announcementDao = announcementDao;
	}

	public ItemDAO getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	public AdinadpDAO getAdinadpDao() {
		return adinadpDao;
	}

	public void setAdinadpDao(AdinadpDAO adinadpDao) {
		this.adinadpDao = adinadpDao;
	}

}
