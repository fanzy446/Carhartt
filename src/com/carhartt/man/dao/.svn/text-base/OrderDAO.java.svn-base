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
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Order;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for Order
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.carhartt.man.model.Order
 * @author MyEclipse Persistence Tools
 */

public class OrderDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(OrderDAO.class);
	// property constants
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String ORDER_STATE = "orderState";
	public static final String EVALUATION_GRADE = "evaluationGrade";
	public static final String EXTRA_ESTIMATE = "extraEstimate";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public List getOrdeList() {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						Criteria c = session.createCriteria(Order.class);
						// c.addOrder(Order.desc("userId"));
						return c.list();
					}
				});
	}

	public List searchOrdersByUserId(final Integer userId, final short state,
			final int start, final int limit) {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						Criteria c = session.createCriteria(Order.class);
						if (userId != 0) {
							c.add(Restrictions.eq("userId", userId));
						}
						if (state != 4)
							c.add(Restrictions.eq("orderState", state));
						c.addOrder(org.hibernate.criterion.Order
								.desc("orderId"));
						c.setFirstResult(start);
						c.setMaxResults(limit);
						return c.list();
					}
				});
	}

	public List searchOrders(final Integer id, final String dateFrom,
			final String dateTo, final short state, final int start,
			final int limit) {
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub

						Criteria c = session.createCriteria(Order.class);
						try {
							if (dateFrom != null && !dateFrom.equals("")) {

								System.out.println(dateFrom);
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(dateFrom);
								Timestamp ts = new Timestamp(d.getTime());
								c.add(Restrictions.gt("createTime", ts));

								// c.add(Restrictions.eq("createTime", date));
							}
							if (dateTo != null && !dateTo.equals("")) {

								System.out.println(dateTo);
								Format f = new SimpleDateFormat(
										"yyyy-MM-dd'T'HH:mm:ss");
								Date d = (Date) f.parseObject(dateTo);
								Timestamp ts = new Timestamp(d.getTime());
								c.add(Restrictions.lt("createTime", ts));

								// c.add(Restrictions.eq("createTime", date));
							}
							if (id != 0) {
								c.add(Restrictions.eq("orderId", id));
							}
							if (state != 4)
								c.add(Restrictions.eq("orderState", state));
							c.addOrder(org.hibernate.criterion.Order
									.desc("orderId"));
							c.setFirstResult(start);
							c.setMaxResults(limit);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return c.list();
					}
				});
	}

	public int getAllRecordNum(final Integer id, final String dateFrom,
			final String dateTo, final short state) {
		// String hql = "select count(*) from User as user";
		// Long count =
		// (Long)getHibernateTemplate().find(hql).listIterator().next();
		// return count.intValue();
		Session session = getSession();
		Criteria c = session.createCriteria(Order.class);
		try {

			if (dateFrom != null && !dateFrom.equals("")) {

				System.out.println(dateFrom);
				Format f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Date d = (Date) f.parseObject(dateFrom);
				Timestamp ts = new Timestamp(d.getTime());
				c.add(Restrictions.gt("createTime", ts));

				// c.add(Restrictions.eq("createTime", date));
			}
			if (dateTo != null && !dateTo.equals("")) {

				System.out.println(dateTo);
				Format f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Date d = (Date) f.parseObject(dateTo);
				Timestamp ts = new Timestamp(d.getTime());
				c.add(Restrictions.lt("createTime", ts));

				// c.add(Restrictions.eq("createTime", date));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (id != 0) {
			c.add(Restrictions.eq("orderId", id));
		}
		if (state != 4)
			c.add(Restrictions.eq("orderState", state));
		Integer total = (Integer) c.setProjection(Projections.rowCount())
				.uniqueResult();
		c.setProjection(null);
		return total;
	}

	public void save(Order transientInstance) {
		log.debug("saving Order instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Order persistentInstance) {
		log.debug("deleting Order instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Order findById(java.lang.Integer id) {
		log.debug("getting Order instance with id: " + id);
		try {
			Order instance = (Order) getHibernateTemplate().get(
					"com.carhartt.man.model.Order", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Order instance) {
		log.debug("finding Order instance by example");
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
		log.debug("finding Order instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Order as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTotalPrice(Object totalPrice) {
		return findByProperty(TOTAL_PRICE, totalPrice);
	}

	public List findByOrderState(Object orderState) {
		return findByProperty(ORDER_STATE, orderState);
	}

	public List findByEvaluationGrade(Object evaluationGrade) {
		return findByProperty(EVALUATION_GRADE, evaluationGrade);
	}

	public List findByExtraEstimate(Object extraEstimate) {
		return findByProperty(EXTRA_ESTIMATE, extraEstimate);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Order instances");
		try {
			String queryString = "from Order";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Order merge(Order detachedInstance) {
		log.debug("merging Order instance");
		try {
			Order result = (Order) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Order instance) {
		log.debug("attaching dirty Order instance");
		try {
			getHibernateTemplate().merge(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Order instance) {
		log.debug("attaching clean Order instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrderDAO) ctx.getBean("OrderDAO");
	}

}