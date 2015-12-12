package com.carhartt.man.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSONArray;

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

import com.carhartt.man.model.Evaluate;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Evaluate entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Evaluate
 * @author MyEclipse Persistence Tools
 */

public class EvaluateDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(EvaluateDAO.class);
	// property constants
	public static final String EVALUATE_TITLE = "evaluateTitle";
	public static final String EVALUATE_CONTENT = "evaluateContent";
	public static final String EVALUATE_STATE = "evaluateState";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(Evaluate transientInstance) {
		log.debug("saving Evaluate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Evaluate persistentInstance) {
		log.debug("deleting Evaluate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Evaluate findById(java.lang.Integer id) {
		log.debug("getting Evaluate instance with id: " + id);
		try {
			Evaluate instance = (Evaluate) getHibernateTemplate().get(
					"com.carhartt.man.model.Evaluate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Evaluate instance) {
		log.debug("finding Evaluate instance by example");
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
		log.debug("finding Evaluate instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Evaluate as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEvaluateTitle(Object evaluateTitle) {
		return findByProperty(EVALUATE_TITLE, evaluateTitle);
	}

	public List findByEvaluateContent(Object evaluateContent) {
		return findByProperty(EVALUATE_CONTENT, evaluateContent);
	}

	public List findByEvaluateState(Object evaluateState) {
		return findByProperty(EVALUATE_STATE, evaluateState);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Evaluate instances");
		try {
			String queryString = "from Evaluate";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Evaluate merge(Evaluate detachedInstance) {
		log.debug("merging Evaluate instance");
		try {
			Evaluate result = (Evaluate) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Evaluate instance) {
		log.debug("attaching dirty Evaluate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Evaluate instance) {
		log.debug("attaching clean Evaluate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EvaluateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EvaluateDAO) ctx.getBean("EvaluateDAO");
	}
	
	public long getEvaluatePageNum(final Item item)
	{
		List result = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "select count(*) from Evaluate evaluate where evaluate.item.itemId=?";
				final Query query = session.createQuery(hql);
				query.setParameter(0, item.getItemId());
				List result = query.list();
				return result;
			}
		});
		return (Long)(result.get(0));
	}
	public List fetchEvaluate(final Item item, final int start, final int limit)
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Evaluate evaluate where evaluate.item.itemId=? order by evaluate.evaluateTime desc";
				final Query query = session.createQuery(hql);
				query.setParameter(0, item.getItemId());
				query.setMaxResults(limit);
				query.setFirstResult(start);
				List result = query.list();
				for(int i = 0; i < result.size(); i++)
				{
					User user = ((Evaluate)result.get(i)).getUser();
					Hibernate.initialize(user);
				}
				return result;
			}
		});
	}
}