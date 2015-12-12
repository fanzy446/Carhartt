package com.carhartt.man.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Broadclass entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Broadclass
 * @author MyEclipse Persistence Tools
 */

public class BroadclassDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BroadclassDAO.class);
	// property constants
	public static final String BROAD_CLASS_NAME = "broadClassName";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public List getBroadClass() {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						Criteria c = session.createCriteria(Broadclass.class);
						return c.list();
					}
				});
	}

	public List getBroadWithSub() {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						Criteria c = session.createCriteria(Broadclass.class);
						List result = c.list();
						for (Object obj : result) {
							Hibernate.initialize(((Broadclass) obj)
									.getSubclasses());
						}
						return result;
					}
				});
	}

	public void save(Broadclass transientInstance) {
		log.debug("saving Broadclass instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Broadclass persistentInstance) {
		log.debug("deleting Broadclass instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Broadclass findById(java.lang.Integer id) {
		log.debug("getting Broadclass instance with id: " + id);
		try {
			Broadclass instance = (Broadclass) getHibernateTemplate().get(
					"com.carhartt.man.model.Broadclass", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Broadclass instance) {
		log.debug("finding Broadclass instance by example");
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
		log.debug("finding Broadclass instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Broadclass as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBroadClassName(Object broadClassName) {
		return findByProperty(BROAD_CLASS_NAME, broadClassName);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Broadclass instances");
		try {
			String queryString = "from Broadclass";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Broadclass merge(Broadclass detachedInstance) {
		log.debug("merging Broadclass instance");
		try {
			Broadclass result = (Broadclass) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Broadclass instance) {
		log.debug("attaching dirty Broadclass instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Broadclass instance) {
		log.debug("attaching clean Broadclass instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(Broadclass instance) {
		getHibernateTemplate().update(instance);
	}

	public static BroadclassDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BroadclassDAO) ctx.getBean("BroadclassDAO");
	}
}