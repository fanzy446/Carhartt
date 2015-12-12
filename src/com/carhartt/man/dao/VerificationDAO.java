package com.carhartt.man.dao;
// default package

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Verification;

/**
 * A data access object (DAO) providing persistence and search support for
 * Verification entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Verification
 * @author MyEclipse Persistence Tools
 */

public class VerificationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(VerificationDAO.class);
	// property constants
	public static final String TIME = "time";
	public static final String MESSAGE = "message";

	protected void initDao() {
		// do nothing
	}

	public void save(Verification transientInstance) {
		System.out.println("asdasd");
		log.debug("saving Verification instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Verification persistentInstance) {
		log.debug("deleting Verification instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Verification findById(java.lang.Short id) {
		log.debug("getting Verification instance with id: " + id);
		try {
			Verification instance = (Verification) getHibernateTemplate().get(
					"Verification", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Verification instance) {
		log.debug("finding Verification instance by example");
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
		log.debug("finding Verification instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Verification as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProperties(String[] propertyNames, Object[] values){
//		log.debug("finding Verification instance with property: "
//				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Verification as model where model."
					+ propertyNames[0] + "= ?";
			for(int i = 1; i < propertyNames.length; i++)
			{
				queryString += (" and model." + propertyNames[i] + "= ?");
			}
			return getHibernateTemplate().find(queryString, values);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}	
	} 
	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findByMessage(Object message) {
		return findByProperty(MESSAGE, message);
	}

	public List findAll() {
		log.debug("finding all Verification instances");
		try {
			String queryString = "from Verification";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Verification merge(Verification detachedInstance) {
		log.debug("merging Verification instance");
		try {
			Verification result = (Verification) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Verification instance) {
		log.debug("attaching dirty Verification instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Verification instance) {
		log.debug("attaching clean Verification instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VerificationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (VerificationDAO) ctx.getBean("VerificationDAO");
	}
}