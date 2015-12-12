package com.carhartt.man.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Commodity;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.SearchCondition;
import com.carhartt.man.model.Subclass;

/**
 * A data access object (DAO) providing persistence and search support for Item
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.carhartt.man.model.Item
 * @author MyEclipse Persistence Tools
 */

public class ItemDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ItemDAO.class);
	// property constants

	public static final String ITEM_ID = "itemId";
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_STATE = "state";
	public static final String BROAD_CLASS = "broadclass";
	public static final String SUB_CLASS = "subclass";
	public static final String ITEM_PUB_DATE = "itemPubDate";
	public static final String ITEM_PHOTO = "itemPhoto";
	public static final String ITEM_PRICE = "itemPrice";
	public static final String ITEM_COUNT = "itemCount";
	public static final String ITEM_UNIT = "itemUnit";
	public static final String ITEM_DESC = "itemDesc";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public Item findByIdWithClass(final int id)
	{
		return (Item)super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						String hql =
						// "select new Commodity(item.itemId, item.itemName, item.broadclass, item.subclass, item.itemPubDate, item.itemPhoto, item.itemPrice, item.itemCount, item.itemUnit, item.itemDesc, vsale.itemSale, item.remark)"
						// +
						" from Item item where item.itemId=?";
						final Query query = session.createQuery(hql);
						query.setParameter(0, id);
						List result = query.list();
						for (Object obj : result) {
							Hibernate.initialize(((Item)obj).getBroadclass());
							Hibernate.initialize(((Item)obj).getSubclass());
						}

						return result;
					}
				}).get(0);
	}
	
	public List getItemList(final SearchCondition condition, final int start,
			final int limit) {
		// TODO Auto-generated method stub
		List items = super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						String hql =
						// "select new Commodity(item.itemId, item.itemName, item.broadclass, item.subclass, item.itemPubDate, item.itemPhoto, item.itemPrice, item.itemCount, item.itemUnit, item.itemDesc, vsale.itemSale, item.remark)"
						// +
						" from Item item, VSale vsale where item.itemId=vsale.itemId and item.state=1";
						if (condition.getKey() != null
								&& condition.getKey().length() != 0) {
							hql += " and item.itemName like '%"
									+ condition.getKey() + "%'";
						}
						if (condition.getDateFrom() != null
								&& condition.getDateFrom().length() != 0) {
							try {
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(condition
										.getDateFrom());
								Timestamp ts = new Timestamp(d.getTime());
								hql += " and item.itemPubDate >" + ts + "";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (condition.getDateTo() != null
								&& condition.getDateTo().length() != 0) {
							try {
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(condition
										.getDateTo());
								Calendar cal = Calendar.getInstance();
								cal.setTime(d);
								cal.add(Calendar.DATE, 1);
								Timestamp ts = new Timestamp(cal
										.getTimeInMillis());
								hql += " and item.itemPubDate <" + ts + "";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (condition.getPriceFrom() != null
								&& condition.getPriceFrom().length() != 0) {
							hql += " and item.itemPrice >"
									+ condition.getPriceFrom() + "";
						}
						if (condition.getPriceTo() != null
								&& condition.getPriceTo().length() != 0) {
							hql += " and item.itemPrice <"
									+ condition.getPriceTo() + "";
						}

						if (condition.getCountFrom() != null
								&& condition.getCountFrom().length() != 0) {
							hql += " and item.itemCount >"
									+ condition.getCountFrom() + "";
						}
						if (condition.getCountTo() != null
								&& condition.getCountTo().length() != 0) {
							hql += " and item.itemCount <"
									+ condition.getCountTo() + "";
						}
						if (condition.getBroadclass() != null) {
							hql += " and item.broadclass.broadClassId="
									+ condition.getBroadclass()
											.getBroadClassId() + "";
						}
						if (condition.getSubclass() != null) {
							hql += " and item.subclass.subClassId="
									+ condition.getSubclass().getSubClassId()
									+ "";
						}
						if (condition.getOrderBy() != null
								&& condition.getOrderBy().length() != 0) {
							if (condition.getOrderBy().equals("sale")) {
								hql += " order by vsale.sale";
							} else {
								hql += " order by " + condition.getOrderBy();
							}
							if (condition.isOrderSeq()) {
								hql += " asc";
							}

							else {
								hql += " desc";
							}

						}
						final Query query = session.createQuery(hql);
						query.setFirstResult(start);
						query.setMaxResults(limit);
						List result = query.list();
						System.out.println(result.size());
						for (Object obj : result) {
							Hibernate.initialize(((Item) ((Object[]) obj)[0])
									.getBroadclass());
							Hibernate.initialize(((Item) ((Object[]) obj)[0])
									.getSubclass());
						}

						return result;
					}
				});
		List total = super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						String hql =
						// "select new Commodity(item.itemId, item.itemName, item.broadclass, item.subclass, item.itemPubDate, item.itemPhoto, item.itemPrice, item.itemCount, item.itemUnit, item.itemDesc, vsale.itemSale, item.remark)"
						// +
						"select count(*) from Item item, VSale vsale where item.itemId=vsale.itemId and item.state=1";
						if (condition.getKey() != null
								&& condition.getKey().length() != 0) {
							hql += " and item.itemName like '%"
									+ condition.getKey() + "%'";
						}
						if (condition.getDateFrom() != null
								&& condition.getDateFrom().length() != 0) {
							try {
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(condition
										.getDateFrom());
								Timestamp ts = new Timestamp(d.getTime());
								hql += " and item.itemPubDate >" + ts + "";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (condition.getDateTo() != null
								&& condition.getDateTo().length() != 0) {
							try {
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(condition
										.getDateTo());
								Calendar cal = Calendar.getInstance();
								cal.setTime(d);
								cal.add(Calendar.DATE, 1);
								Timestamp ts = new Timestamp(cal
										.getTimeInMillis());
								hql += " and item.itemPubDate <" + ts + "";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (condition.getPriceFrom() != null
								&& condition.getPriceFrom().length() != 0) {
							hql += " and item.itemPrice >"
									+ condition.getPriceFrom() + "";
						}
						if (condition.getPriceTo() != null
								&& condition.getPriceTo().length() != 0) {
							hql += " and item.itemPrice <"
									+ condition.getPriceTo() + "";
						}

						if (condition.getCountFrom() != null
								&& condition.getCountFrom().length() != 0) {
							hql += " and item.itemCount >"
									+ condition.getCountFrom() + "";
						}
						if (condition.getCountTo() != null
								&& condition.getCountTo().length() != 0) {
							hql += " and item.itemCount <"
									+ condition.getCountTo() + "";
						}
						if (condition.getBroadclass() != null) {
							hql += " and item.broadclass.broadClassId="
									+ condition.getBroadclass()
											.getBroadClassId() + "";
						}
						if (condition.getSubclass() != null) {
							hql += " and item.subclass.subClassId="
									+ condition.getSubclass().getSubClassId()
									+ "";
						}
						if (condition.getOrderBy() != null
								&& condition.getOrderBy().length() != 0) {
							if (condition.getOrderBy().equals("sale")) {
								hql += " order by vsale.sale";
							} else {
								hql += " order by " + condition.getOrderBy();
							}
							if (condition.isOrderSeq()) {
								hql += " asc";
							}

							else {
								hql += " desc";
							}

						}
						final Query query = session.createQuery(hql);
//						query.setFirstResult(start);
//						query.setMaxResults(limit);
						List result = query.list();
//						System.out.println(result.size());
//						for (Object obj : result) {
//							Hibernate.initialize(((Item) ((Object[]) obj)[0])
//									.getBroadclass());
//							Hibernate.initialize(((Item) ((Object[]) obj)[0])
//									.getSubclass());
//						}
						return result;
					}
				});
		ArrayList result = new ArrayList();
		result.add(items);
		result.add((Long)(total.get(0)));
		return result;
	}

	public List getItemListOfMan(final String itemName, final String dateFrom,
			final String dateTo, final String priceFrom, final String priceTo,
			final String countFrom, final String countTo,
			final Broadclass broadclass, final Subclass subclass,
			final int start, final int limit) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						Criteria c = session.createCriteria(Item.class);
						c.add(Restrictions.eq(ITEM_STATE, true));
						if (itemName != null && itemName.length() != 0) {
							c.add(Restrictions.like(ITEM_NAME, itemName + "%"));
						}
						if (dateFrom != null && dateFrom.length() != 0) {
							try {
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(dateFrom);
								Timestamp ts = new Timestamp(d.getTime());
								c.add(Restrictions.ge(ITEM_PUB_DATE, ts));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (dateTo != null && dateTo.length() != 0) {
							try {
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(dateTo);
								Calendar cal = Calendar.getInstance();
								cal.setTime(d);
								cal.add(Calendar.DATE, 1);
								Timestamp ts = new Timestamp(cal
										.getTimeInMillis());
								c.add(Restrictions.lt(ITEM_PUB_DATE, ts));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (priceFrom != null && priceFrom.length() != 0) {
							c.add(Restrictions.ge(ITEM_PRICE,
									Float.parseFloat(priceFrom)));
						}
						if (priceTo != null && priceTo.length() != 0) {
							c.add(Restrictions.le(ITEM_PRICE,
									Float.parseFloat(priceTo)));
						}

						if (countFrom != null && countFrom.length() != 0) {
							c.add(Restrictions.ge(ITEM_COUNT,
									Integer.parseInt(countFrom)));
						}
						if (countTo != null && countTo.length() != 0) {
							c.add(Restrictions.le(ITEM_COUNT,
									Integer.parseInt(countTo)));
						}
						if (broadclass != null) {
							c.add(Restrictions.eq(BROAD_CLASS, broadclass));
						}
						if (subclass != null) {
							c.add(Restrictions.eq(SUB_CLASS, subclass));
						}

						Integer total = (Integer) c.setProjection(
								Projections.rowCount()).uniqueResult();
						c.setProjection(null);
						c.setFirstResult(start);
						c.setMaxResults(limit);
						c.addOrder(Order.desc(ITEM_ID));  
						List result = c.list();
						for (Object obj : result) {
							Hibernate.initialize(((Item) obj).getBroadclass());
							Hibernate.initialize(((Item) obj).getSubclass());
						}
						List list = new ArrayList();
						list.add(result);
						list.add(total);
						return list;
					}
				});
	}

	public void save(Item transientInstance) {
		log.debug("saving Item instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Item persistentInstance) {
		log.debug("deleting Item instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Item findById(java.lang.Integer id) {
		log.debug("getting Item instance with id: " + id);
		try {
			Item instance = (Item) getHibernateTemplate().get(
					"com.carhartt.man.model.Item", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Item instance) {
		log.debug("finding Item instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Item instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Item as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByItemName(Object itemName) {
		return findByProperty(ITEM_NAME, itemName);
	}

	public List findByItemPhoto(Object itemPhoto) {
		return findByProperty(ITEM_PHOTO, itemPhoto);
	}

	public List findByItemPrice(Object itemPrice) {
		return findByProperty(ITEM_PRICE, itemPrice);
	}

	public List findByItemCount(Object itemCount) {
		return findByProperty(ITEM_COUNT, itemCount);
	}

	public List findByItemUnit(Object itemUnit) {
		return findByProperty(ITEM_UNIT, itemUnit);
	}

	public List findByItemDesc(Object itemDesc) {
		return findByProperty(ITEM_DESC, itemDesc);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findBySubclass(final Object subclass) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria c = session.createCriteria(Item.class);
						c.add(Restrictions.eq(ITEM_STATE, true));
						c.add(Restrictions.eq(SUB_CLASS, subclass));
						List result = c.list();
						return result;
					}
				});
	}

	public List findAll() {
		log.debug("finding all Item instances");
		try {
			String queryString = "from Item";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Item merge(Item detachedInstance) {
		log.debug("merging Item instance");
		try {
			Item result = (Item) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Item instance) {
		log.debug("attaching dirty Item instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Item instance) {
		log.debug("attaching clean Item instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ItemDAO) ctx.getBean("ItemDAO");
	}

	public void update(Item instance) {
		getHibernateTemplate().update(instance);
	}

	public List fetchLatestItemOfSub(final Subclass subclass, final int num) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Item item where item.state = 1 and item.subclass.subClassId=? order by item.itemPubDate desc";
				final Query query = session.createQuery(hql);
				query.setParameter(0, subclass.getSubClassId());
				query.setMaxResults(num);
				return query.list();
			}
		});
	}

	public List fetchLatestItemOfBroad(final Broadclass broadclass,
			final int num) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Item item where item.state = 1 and item.broadclass.broadClassId=? order by item.itemPubDate desc";
				final Query query = session.createQuery(hql);
				query.setParameter(0, broadclass.getBroadClassId());
				query.setMaxResults(num);
				return query.list();
			}
		});
	}
}