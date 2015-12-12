package com.carhartt.man.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Broadclass;
import com.carhartt.man.model.Item;
import com.carhartt.man.model.Shoppingcart;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Shoppingcart entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Shoppingcart
 * @author MyEclipse Persistence Tools
 */

public class ShoppingcartDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ShoppingcartDAO.class);
	// property constants
	public static final String PURCHASE_NUM = "purchaseNum";
	public static final String ITEM_PRICE = "itemPrice";
	public static final String ITEM_DESC = "itemDesc";
	public static final String REMARK = "remark";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public List findExistCart(final User user, final Item item) {
		List result = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				String hql = "from Shoppingcart cart where cart.user.userId=? and cart.item.itemId=?";
				final Query query = session.createQuery(hql);
				query.setParameter(0, user.getUserId());
				query.setParameter(1, item.getItemId());
				return query.list();
			}
		});
		return result;
	}
	
	public void save(Shoppingcart transientInstance) {
		log.debug("saving Shoppingcart instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Shoppingcart persistentInstance) {
		log.debug("deleting Shoppingcart instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Shoppingcart findById(java.lang.Integer id) {
		log.debug("getting Shoppingcart instance with id: " + id);
		try {
			Shoppingcart instance = (Shoppingcart) getHibernateTemplate().get(
					"com.carhartt.man.model.Shoppingcart", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Shoppingcart instance) {
		log.debug("finding Shoppingcart instance by example");
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
		log.debug("finding Shoppingcart instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Shoppingcart as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPurchaseNum(Object purchaseNum) {
		return findByProperty(PURCHASE_NUM, purchaseNum);
	}

	public List findByItemPrice(Object itemPrice) {
		return findByProperty(ITEM_PRICE, itemPrice);
	}

	public List findByItemDesc(Object itemDesc) {
		return findByProperty(ITEM_DESC, itemDesc);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}
	
	public List findByUserId(Object userID) {
		return findByProperty(USER_ID, userID);
	}

	public List findAll() {
		log.debug("finding all Shoppingcart instances");
		try {
			String queryString = "from Shoppingcart";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Shoppingcart merge(Shoppingcart detachedInstance) {
		log.debug("merging Shoppingcart instance");
		try {
			Shoppingcart result = (Shoppingcart) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Shoppingcart instance) {
		log.debug("attaching dirty Shoppingcart instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Shoppingcart instance) {
		log.debug("attaching clean Shoppingcart instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ShoppingcartDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ShoppingcartDAO) ctx.getBean("ShoppingcartDAO");
	}
}