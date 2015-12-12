package com.carhartt.man.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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
import com.carhartt.man.model.Consignee;

/**
 * A data access object (DAO) providing persistence and search support for
 * Consignee entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Consignee
 * @author MyEclipse Persistence Tools
 */

public class ConsigneeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ConsigneeDAO.class);
	// property constants
	public static final String CONSIGNEE_NAME = "consigneeName";
	public static final String FULL_ADDRESS = "fullAddress";
	public static final String POSTCODE = "postcode";
	public static final String CONSIGNEE_PHONE = "consigneePhone";
	public static final String CONSIGNEE_TEL = "consigneeTel";
	public static final String CONSIGNEE_EMAIL = "consigneeEmail";
	public static final String CONSIGNEE_MESG = "consigneeMesg";
	public static final String REMARK = "remark";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(Consignee transientInstance) {
		log.debug("saving Consignee instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Consignee persistentInstance) {
		log.debug("deleting Consignee instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Consignee findById(java.lang.Integer id) {
		log.debug("getting Consignee instance with id: " + id);
		try {
			Consignee instance = (Consignee) getHibernateTemplate().get(
					"com.carhartt.man.model.Consignee", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Consignee instance) {
		log.debug("finding Consignee instance by example");
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
		log.debug("finding Consignee instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Consignee as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsigneeName(Object consigneeName) {
		return findByProperty(CONSIGNEE_NAME, consigneeName);
	}

	public List findByFullAddress(Object fullAddress) {
		return findByProperty(FULL_ADDRESS, fullAddress);
	}

	public List findByPostcode(Object postcode) {
		return findByProperty(POSTCODE, postcode);
	}

	public List findByConsigneePhone(Object consigneePhone) {
		return findByProperty(CONSIGNEE_PHONE, consigneePhone);
	}

	public List findByConsigneeTel(Object consigneeTel) {
		return findByProperty(CONSIGNEE_TEL, consigneeTel);
	}

	public List findByConsigneeEmail(Object consigneeEmail) {
		return findByProperty(CONSIGNEE_EMAIL, consigneeEmail);
	}

	public List findByConsigneeMesg(Object consigneeMesg) {
		return findByProperty(CONSIGNEE_MESG, consigneeMesg);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}
	
	public List findByUserId(Object userid) {
		return findByProperty(USER_ID, userid);
	}

	public List findAll() {
		log.debug("finding all Consignee instances");
		try {
			String queryString = "from Consignee";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Consignee merge(Consignee detachedInstance) {
		log.debug("merging Consignee instance");
		try {
			Consignee result = (Consignee) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Consignee instance) {
		log.debug("attaching dirty Consignee instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Consignee instance) {
		log.debug("attaching clean Consignee instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ConsigneeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ConsigneeDAO) ctx.getBean("ConsigneeDAO");
	}
	
	public List fetchUser()
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Consignee";
				final Query query = session.createQuery(hql);
				List result = query.list();
				for(Object obj : result)
				{
					Hibernate.initialize(((Consignee)obj).getUser());
				}
				return result;
			}
		});
	}
	
}