package com.carhartt.man.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Subclass;

/**
 * A data access object (DAO) providing persistence and search support for
 * Subclass entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Subclass
 * @author MyEclipse Persistence Tools
 */

public class SubclassDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SubclassDAO.class);
	// property constants
	public static final String SUB_CLASS_NAME = "subClassName";
	public static final String BROAD_CLASS = "broadclass";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(Subclass transientInstance) {
		log.debug("saving Subclass instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Subclass persistentInstance) {
		log.debug("deleting Subclass instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Subclass findById(java.lang.Integer id) {
		log.debug("getting Subclass instance with id: " + id);
		try {
			Subclass instance = (Subclass) getHibernateTemplate().get(
					"com.carhartt.man.model.Subclass", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Subclass instance) {
		log.debug("finding Subclass instance by example");
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
		log.debug("finding Subclass instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Subclass as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySubClassName(Object subClassName) {
		return findByProperty(SUB_CLASS_NAME, subClassName);
	}

	public List findByBroadClass(Object instance) {
		return findByProperty(BROAD_CLASS, instance);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Subclass instances");
		try {
			String queryString = "from Subclass";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Subclass merge(Subclass detachedInstance) {
		log.debug("merging Subclass instance");
		try {
			Subclass result = (Subclass) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Subclass instance) {
		log.debug("attaching dirty Subclass instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subclass instance) {
		log.debug("attaching clean Subclass instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SubclassDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SubclassDAO) ctx.getBean("SubclassDAO");
	}

	public void update(Subclass instance) {
		getHibernateTemplate().update(instance);
	}
}