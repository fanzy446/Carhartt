package com.carhartt.man.dao;
// default package

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

import com.carhartt.man.config.CarharttConfig;
import com.carhartt.man.model.Adinadp;
import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Advertisement;

/**
 * A data access object (DAO) providing persistence and search support for
 * Adinadp entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Adinadp
 * @author MyEclipse Persistence Tools
 */

public class AdinadpDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AdinadpDAO.class);
	// property constants
	public static final String STATE = "state";
	private static final String itemUrl = CarharttConfig.URL + "pages/jsp/user/productDetail.jsp?itemId=";

	protected void initDao() {
		// do nothing
	}

	public List fetchValidAd(final Adposition adposition){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Adinadp as adinadp where adinadp.adposition=? and adinadp.state=0";
				final Query query = session.createQuery(hql);
				query.setParameter(0, adposition);
				List result = query.list();
				for(Object obj : result)
				{
					Adinadp adinadp = (Adinadp)obj;
					Hibernate.initialize(adinadp.getAdvertisement());
					String url = adinadp.getAdvertisement().getUrl();
					boolean isItem = false;
					try
					{
						Integer.parseInt(url);
						isItem = true;
					}
					catch(Exception e)
					{
						isItem = false;
					}
					if(isItem)
					{					
						url = itemUrl + url;
					}
					adinadp.getAdvertisement().setUrl(url);
				}
				return result;
			}
		});		
	}
	
	public void save(Adinadp transientInstance) {
		log.debug("saving Adinadp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Adinadp persistentInstance) {
		log.debug("deleting Adinadp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

//	public Adinadp findById(java.lang.Integer id) {
//		log.debug("getting Adinadp instance with id: " + id);
//		try {
//			Adinadp instance = (Adinadp) getHibernateTemplate().get("Adinadp",
//					id);
//			return instance;
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
	
	public Adinadp findById(java.lang.Integer id) {
		log.debug("getting Adinadp instance with id: " + id);
		try {
			Adinadp instance = (Adinadp) getHibernateTemplate().get(
					"com.carhartt.man.model.Adinadp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Adinadp instance) {
		log.debug("finding Adinadp instance by example");
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
		log.debug("finding Adinadp instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Adinadp as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Adinadp instances");
		try {
			String queryString = "from Adinadp";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Adinadp merge(Adinadp detachedInstance) {
		log.debug("merging Adinadp instance");
		try {
			Adinadp result = (Adinadp) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Adinadp instance) {
		log.debug("attaching dirty Adinadp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Adinadp instance) {
		log.debug("attaching clean Adinadp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdinadpDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdinadpDAO) ctx.getBean("AdinadpDAO");
	}
}