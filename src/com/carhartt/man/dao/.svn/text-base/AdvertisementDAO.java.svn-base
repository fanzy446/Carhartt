package com.carhartt.man.dao;
// default package


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Adinadp;
import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Advertisement;
import com.carhartt.man.model.Message;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Advertisement entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Advertisement
 * @author MyEclipse Persistence Tools
 */

public class AdvertisementDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AdvertisementDAO.class);
	// property constants
	public static final String ADPHOTO = "adphoto";
	public static final String URL = "url";

	protected void initDao() {
		// do nothing
	}

//	public Advertisement findById(java.lang.Integer id) {
//		log.debug("getting Advertisement instance with id: " + id);
//		try {
//			Advertisement instance = (Advertisement) getHibernateTemplate()
//					.get("Advertisement", id);
//			return instance;
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
	public Advertisement findById(java.lang.Integer id) {
		log.debug("getting Advertisement instance with id: " + id);
		try {
			Advertisement instance = (Advertisement) getHibernateTemplate().get(
					"com.carhartt.man.model.Advertisement", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void save(Advertisement transientInstance) {
		log.debug("saving Advertisement instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Advertisement persistentInstance) {
		log.debug("deleting Advertisement instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public List searchAAdv(final Integer id, final Integer posId, final short kind){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(Advertisement.class);
				if(id > 0){
					c.add(Restrictions.eq("advertisementId", id));
				}
				if(posId > 0){
					Criteria adpCriteria = c.createCriteria("adposition"); 
					adpCriteria.add(Restrictions.eq("adpositionId", posId));
					if(kind>=0 && kind <= 3){
						adpCriteria.add(Restrictions.eq("kind", kind));
					}
				}
				return c.list();
			}
		});
	}
	public List searchAdv(final Integer id, final short state, final short kind,
			final int start, final int limit){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(Advertisement.class);
				if(id > 0){
					c.add(Restrictions.eq("advertisementId", id));
				}
				if(state>=0 && state <=2){
					Criteria adinadpCriteria = c.createCriteria("adinadps"); 
					adinadpCriteria.add(Restrictions.eq("state", state));
					if(kind>=0 && kind <= 3){
						Criteria adpCriteria = adinadpCriteria.createCriteria("adposition");
						adpCriteria.add(Restrictions.eq("kind", kind));
					}
				}else if(kind>=0&&kind<=3){
					Criteria adinadpCriteria = c.createCriteria("adinadps"); 
					Criteria adpCriteria = adinadpCriteria.createCriteria("adposition");
					adpCriteria.add(Restrictions.eq("kind", kind));
				}
				c.addOrder(Order.desc("advertisementId"));
				c.setFirstResult(start);
				c.setMaxResults(limit);
				return c.list();
			}
		});
	}
	
	public int getRecordNum(Integer id, final short state, final short kind){
		Session session = getSession();
		Criteria c = session.createCriteria(Advertisement.class);
		if(id > 0){
			c.add(Restrictions.eq("advertisementId", id));
		}
		if(state>=0 && state <=2){
			Criteria adinadpCriteria = c.createCriteria("adinadps"); 
			adinadpCriteria.add(Restrictions.eq("state", state));
			if(kind>=0 && kind <= 3){
				Criteria adpCriteria = adinadpCriteria.createCriteria("adposition");
				adpCriteria.add(Restrictions.eq("kind", kind));
			}
		}else if(kind>=0&&kind<=3){
			Criteria adinadpCriteria = c.createCriteria("adinadps"); 
			Criteria adpCriteria = adinadpCriteria.createCriteria("adposition");
			adpCriteria.add(Restrictions.eq("kind", kind));
		}
		Integer total=(Integer)c.setProjection(Projections.rowCount()).uniqueResult(); 
		c.setProjection(null);
		return total;
	}
	
	

	public List findByExample(Advertisement instance) {
		log.debug("finding Advertisement instance by example");
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
		log.debug("finding Advertisement instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Advertisement as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdphoto(Object adphoto) {
		return findByProperty(ADPHOTO, adphoto);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}
	
	public List findAll() {
		log.debug("finding all Advertisement instances");
		try {
			String queryString = "from Advertisement";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Advertisement merge(Advertisement detachedInstance) {
		log.debug("merging Advertisement instance");
		try {
			Advertisement result = (Advertisement) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Advertisement instance) {
		log.debug("attaching dirty Advertisement instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Advertisement instance) {
		log.debug("attaching clean Advertisement instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdvertisementDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AdvertisementDAO) ctx.getBean("AdvertisementDAO");
	}
}