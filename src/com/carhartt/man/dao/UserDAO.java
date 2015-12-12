package com.carhartt.man.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.carhartt.man.model.User
 * @author MyEclipse Persistence Tools
 */

public class UserDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_PASSWORD = "userPassword";
	public static final String USER_ROLE = "userRole";
	public static final String USER_STATE = "userState";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}
	
	public List getUserList(final int startPosition , final int length) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(User.class);
				c.addOrder(Order.desc("userId"));
				c.setFirstResult(startPosition);
				c.setMaxResults(length);
				return c.list();
			}
		});
	}
	
	public List searchUsers(final Integer id,  final String email, final short state, final int start, final int limit){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(User.class);
				if (email != null && !email.equals("")){
					c.add(Restrictions.eq("userEmail", email));
				}
				if (id != 0){
					c.add(Restrictions.eq("userId", id));
				}
				if(state != 2)
					c.add(Restrictions.eq("userState", state));
				c.addOrder(Order.desc("userId"));
				c.setFirstResult(start);
				c.setMaxResults(limit);
				return c.list();
			}
		});
	}
	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.carhartt.man.model.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findByUserRole(Object userRole) {
		return findByProperty(USER_ROLE, userRole);
	}

	public List findByUserState(Object userState) {
		return findByProperty(USER_STATE, userState);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	
	public int getAllRecordNum(final Integer id,  final String email, final short state){
//		String hql = "select count(*) from User as user";
//		Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
//	  	return count.intValue();
		Session session = getSession();
		Criteria c = session.createCriteria(User.class);
		if (email != null && !email.equals("")){
			c.add(Restrictions.eq("userEmail", email));
		}
		if (id != 0){
			c.add(Restrictions.eq("userId", id));
		}
		if(state != 2 && state != -1)
			c.add(Restrictions.eq("userState", state));
		Integer total=(Integer)c.setProjection(Projections.rowCount()).uniqueResult(); 
		c.setProjection(null);
		log.debug("get num of user record! with condition: id:" + id
				+ " email:" + email + "state: " + state + " and result is count = "
				+ total);
		return total;
	}
	
	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
//	public boolean userInfoModify(Integer id, Integer role, String remark){
//		getHibernateTemplate().execute(action)
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		String qry = "update user t set ";
//		if (role != -1) qry+= "t.userRole=" + role+" ";
//		if (remark!=null) qry+= "t.remark=" + "'" + remark + "' ";
//		qry += "where t.userId="+id;
//		Query query = session.createQuery(qry);  
//        query.executeUpdate();
//        session.getTransaction().commit(); 
//		return false;
//	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
}