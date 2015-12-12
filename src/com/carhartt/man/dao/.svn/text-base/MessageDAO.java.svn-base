package com.carhartt.man.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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

import com.carhartt.man.model.Advertisement;
import com.carhartt.man.model.Message;
import com.carhartt.man.model.User;

/**
 * A data access object (DAO) providing persistence and search support for
 * Message entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.carhartt.man.model.Message
 * @author MyEclipse Persistence Tools
 */

public class MessageDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(MessageDAO.class);
	// property constants
	public static final String MESG_TYPE = "mesgType";
	public static final String MESG_TITLE = "mesgTitle";
	public static final String MESG_CONTENT = "mesgContent";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}
	
	public List searchMsg(final User user, final String mesgTitle, final int mesgType,
			final String remark, final int isReply, final int start, final int limit){
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				log.debug("search Message "+ " mesgTitle: "+ mesgTitle
						+ " mesgType: " + mesgType + "remark: " + remark);
				Criteria c = session.createCriteria(Message.class);
				if (mesgTitle != null && !mesgTitle.equals("")){
					c.add(Restrictions.like("mesgTitle", mesgTitle));
				}
				if (!remark.equals("") && !remark.equals("3")){
					c.add(Restrictions.eq("remark", remark));
				}
				if (user != null){
					c.add(Restrictions.eq("user", user));
				}
				if(mesgType < 6 && mesgType >= 0)
					c.add(Restrictions.eq("mesgType", (short)mesgType));
				if(isReply != -1){
					if(isReply == 0){
						c.add(Restrictions.eq("isreply", (short)isReply));
					}else if(isReply >0){
						c.add(Restrictions.gt("isreply", (short)0));
					}
				}
				c.addOrder(Order.desc("messageId"));
				c.setFirstResult(start);
				c.setMaxResults(limit);
				return c.list();
			}
		});
	}

	public int getAllRecordNum(){
		String hql = "select count(*) from Manager as manager";
		Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
		log.debug("get num of message!count = " + count);
		return count.intValue();
	}
	
	
	
	public int getAllRecordNum(final User user, final String mesgTitle, final int mesgType,
			final String remark, int isReply){
//		String hql = "select count(*) from User as user";
//		Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
//	  	return count.intValue();
		Session session = getSession();
		Criteria c = session.createCriteria(Message.class);
		if (mesgTitle != null && !mesgTitle.equals("")){
			c.add(Restrictions.like("mesgTitle", mesgTitle));
		}
		if (remark != null && !remark.equals("3")){
			c.add(Restrictions.eq("remark", remark));
		}
		if (user != null){
			c.add(Restrictions.eq("user", user));
		}
		if(mesgType < 6 && mesgType >= 0)
			c.add(Restrictions.eq("mesgType", (short)mesgType));
		if(isReply != -1){
			if(isReply == 0){
				c.add(Restrictions.eq("isreply", (short)isReply));
			}else if(isReply >0){
				c.add(Restrictions.gt("isreply", (short)0));
			}
		}
		Integer total=(Integer)c.setProjection(Projections.rowCount()).uniqueResult(); 
		c.setProjection(null);
		log.debug("get num of message with condition mesgTitle:" + mesgTitle +
				"remark:" + remark + "mesgType: " + mesgType +" !count = " + total);
		return total;
	}
	
	public List getMsgList(final int startPosition, final int length) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(Message.class);
				c.addOrder(Order.desc("messageId"));
				c.setFirstResult(startPosition);
				c.setMaxResults(length);
				return c.list();
			}
		});
	}
	
	public void save(Message transientInstance) {
		log.debug("saving Message instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Message persistentInstance) {
		log.debug("deleting Message instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Message findById(java.lang.Integer id) {
		log.debug("getting Message instance with id: " + id);
		try {
			Message instance = (Message) getHibernateTemplate().get(
					"com.carhartt.man.model.Message", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Message instance) {
		log.debug("finding Message instance by example");
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
		log.debug("finding Message instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Message as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMesgType(Object mesgType) {
		return findByProperty(MESG_TYPE, mesgType);
	}

	public List findByMesgTitle(Object mesgTitle) {
		return findByProperty(MESG_TITLE, mesgTitle);
	}

	public List findByMesgContent(Object mesgContent) {
		return findByProperty(MESG_CONTENT, mesgContent);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Message instances");
		try {
			String queryString = "from Message";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Message merge(Message detachedInstance) {
		log.debug("merging Message instance");
		try {
			Message result = (Message) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Message instance) {
		log.debug("attaching dirty Message instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Message instance) {
		log.debug("attaching clean Message instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MessageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MessageDAO) ctx.getBean("MessageDAO");
	}
}