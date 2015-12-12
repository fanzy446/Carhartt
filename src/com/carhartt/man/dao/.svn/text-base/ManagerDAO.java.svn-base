package com.carhartt.man.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.carhartt.man.model.Manager;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Manager entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Manager
 * @author MyEclipse Persistence Tools
 */

public class ManagerDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ManagerDAO.class);
	// property constants
	public static final String MANAGER_NAME = "managerName";
	public static final String MANAGER_PASS = "managerPass";
	public static final String MANAGER_TYPE = "managerType";
	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(Manager transientInstance) {
		log.debug("saving Manager instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Manager persistentInstance) {
		log.debug("deleting Manager instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Manager findById(java.lang.Integer id) {
		log.debug("getting Manager instance with id: " + id);
		try {
			Manager instance = (Manager) getHibernateTemplate().get(
					"com.carhartt.man.model.Manager", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Manager instance) {
		log.debug("finding Manager instance by example");
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
		log.debug("finding Manager instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Manager as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List getManagerList() {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(Manager.class);
				c.addOrder(Order.desc("managerId"));
//				c.setFirstResult(startPosition);
//				c.setMaxResults(length);
				return c.list();
			}
		});
	}
	
	public int getAllRecordNum(){
		String hql = "select count(*) from Manager as manager";
		Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
	  	return count.intValue();
	}
	
	public List findByManagerName(Object managerName) {
		return findByProperty(MANAGER_NAME, managerName);
	}

	public List findByManagerPass(Object managerPass) {
		return findByProperty(MANAGER_PASS, managerPass);
	}

	public List findByManagerType(Object managerType) {
		return findByProperty(MANAGER_TYPE, managerType);
	}

	public List findByEmployeeName(Object employeeName) {
		return findByProperty(EMPLOYEE_NAME, employeeName);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Manager instances");
		try {
			String queryString = "from Manager";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Manager merge(Manager detachedInstance) {
		log.debug("merging Manager instance");
		try {
			Manager result = (Manager) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Manager instance) {
		log.debug("attaching dirty Manager instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Manager instance) {
		log.debug("attaching clean Manager instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ManagerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ManagerDAO) ctx.getBean("ManagerDAO");
	}
}