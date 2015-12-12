package com.carhartt.man.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Orderdetials;

/**
 * A data access object (DAO) providing persistence and search support for
 * Orderdetials entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Orderdetials
 * @author MyEclipse Persistence Tools
 */

public class OrderdetialsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(OrderdetialsDAO.class);
	// property constants
	public static final String ITEM_DESC = "itemDesc";
	public static final String ITEM_COUNT = "itemCount";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(Orderdetials transientInstance) {
		log.debug("saving Orderdetials instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Orderdetials persistentInstance) {
		log.debug("deleting Orderdetials instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orderdetials findById(java.lang.Integer id) {
		log.debug("getting Orderdetials instance with id: " + id);
		try {
			Orderdetials instance = (Orderdetials) getHibernateTemplate().get(
					"com.carhartt.man.model.Orderdetials", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Orderdetials instance) {
		log.debug("finding Orderdetials instance by example");
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
		log.debug("finding Orderdetials instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Orderdetials as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByItemDesc(Object itemDesc) {
		return findByProperty(ITEM_DESC, itemDesc);
	}

	public List findByItemCount(Object itemCount) {
		return findByProperty(ITEM_COUNT, itemCount);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Orderdetials instances");
		try {
			String queryString = "from Orderdetials";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Orderdetials merge(Orderdetials detachedInstance) {
		log.debug("merging Orderdetials instance");
		try {
			Orderdetials result = (Orderdetials) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Orderdetials instance) {
		log.debug("attaching dirty Orderdetials instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orderdetials instance) {
		log.debug("attaching clean Orderdetials instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrderdetialsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrderdetialsDAO) ctx.getBean("OrderdetialsDAO");
	}
	
	public List fetchOrder()
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Orderdetials";
				final Query query = session.createQuery(hql);
				List result = query.list();
				for(Object obj : result)
				{
					Hibernate.initialize(((Orderdetials)obj).getOrder());
				}
				return result;
			}
		});
	}
	
	public List fetchItem()
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Orderdetials";
				final Query query = session.createQuery(hql);
				List result = query.list();
				for(Object obj : result)
				{
					Hibernate.initialize(((Orderdetials)obj).getItem());
				}
				return result;
			}
		});
	}
}