package com.carhartt.man.dao;
// default package

import java.sql.SQLException;
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

import com.carhartt.man.model.Adposition;
import com.carhartt.man.model.Advertisement;
import com.carhartt.man.model.Manager;

/**
 * A data access object (DAO) providing persistence and search support for
 * Adposition entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Adposition
 * @author MyEclipse Persistence Tools
 */

public class AdpositionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AdpositionDAO.class);
	// property constants
	public static final String PRICE = "price";
	public static final String KIND = "kind";
	public static final String RATIO = "ratio";
	public static final String POSITION = "position";

	protected void initDao() {
		// do nothing
	}
	
	public Adposition findById(java.lang.Integer id) {
		log.debug("getting Adposition instance with id: " + id);
		try {
			Adposition instance = (Adposition) getHibernateTemplate().get(
					"com.carhartt.man.model.Adposition", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void save(Adposition transientInstance) {
		log.debug("saving Adposition instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Adposition persistentInstance) {
		log.debug("deleting Adposition instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	

	public List findByExample(Adposition instance) {
		log.debug("finding Adposition instance by example");
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
		log.debug("finding Adposition instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Adposition as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByKind(Object kind) {
		return findByProperty(KIND, kind);
	}

	public List findByRatio(Object ratio) {
		return findByProperty(RATIO, ratio);
	}

	public List findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List findAll() {
		log.debug("finding all Adposition instances");
		try {
			String queryString = "from Adposition";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public int getAllNum(final Integer adpId, final short kind, final int start, final int limit){
		Session session = getSession();
		Criteria c = session.createCriteria(Adposition.class);
		if(adpId > 0){
			c.add(Restrictions.eq("adpositionId", adpId));
		}
		if(kind>=0 && kind <=3){
			c.add(Restrictions.eq("kind", kind));
		}
//		Criteria adinadpCriteria = c.createCriteria("adinadps"); 
//		Criteria advCriteria = adinadpCriteria.createCriteria("advertisement");
		Integer total=(Integer)c.setProjection(Projections.rowCount()).uniqueResult(); 
		c.setProjection(null);
		return total;
	}
	
	
	public List searchAdp(final Integer adpId, final short kind, final int start, final int limit){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(Adposition.class);
				if(adpId > 0){
					c.add(Restrictions.eq("adpositionId", adpId));
				}
				if(kind>=0 && kind <=3){
					c.add(Restrictions.eq("kind", kind));
				}
//				Criteria adinadpCriteria = c.createCriteria("adinadps"); 
//				Criteria advCriteria = adinadpCriteria.createCriteria("advertisement");
				c.addOrder(Order.desc("adpositionId"));
				c.setFirstResult(start);
				c.setMaxResults(limit);
				return c.list();
			}
		});
	}
	public Adposition merge(Adposition detachedInstance) {
		log.debug("merging Adposition instance");
		try {
			Adposition result = (Adposition) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Adposition instance) {
		log.debug("attaching dirty Adposition instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Adposition instance) {
		log.debug("attaching clean Adposition instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdpositionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdpositionDAO) ctx.getBean("AdpositionDAO");
	}
}