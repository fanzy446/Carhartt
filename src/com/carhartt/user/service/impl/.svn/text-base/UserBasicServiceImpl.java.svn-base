package com.carhartt.user.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.carhartt.man.config.CarharttConfig;
import com.carhartt.man.dao.AdvertisementDAO;
import com.carhartt.man.dao.AnnouncementDAO;
import com.carhartt.man.dao.UserDAO;
import com.carhartt.man.dao.UserinfoDAO;
import com.carhartt.man.dao.VerificationDAO;
import com.carhartt.man.model.User;
import com.carhartt.man.model.Userinfo;
import com.carhartt.man.model.Verification;
import com.carhartt.user.service.UserBasicService;
import com.carhartt.util.Mail;
import com.carhartt.util.VerificationCreator;
import com.opensymphony.xwork2.ActionContext;

public class UserBasicServiceImpl implements UserBasicService {

	private UserDAO userDao;
	private UserinfoDAO userinfoDao;
	private VerificationDAO verificationDao;

	/**
	 * @return 0-成功 -1-用户不存在 -2-邮箱或密码错误 -3-用户未激活 -4-用户被冻结
	 */
	public int userLogin(User user) {
		// TODO Auto-generated method stub
		List<User> users = userDao.findByUserEmail(user.getUserEmail());
		if (users.size() > 0) {
			if ((users.get(0).getUserPassword()).equals(user.getUserPassword())) {
				switch (users.get(0).getUserState()) {
				case 0:
					System.out.println("userLogin:user login succeeds");
					HttpSession session = ServletActionContext.getRequest().getSession();
					session.setAttribute("userId", users.get(0).getUserId());
					session.setAttribute("userName",users.get(0).getUserName());
					return users.get(0).getUserId();
				case 1:
					System.out.println("userLogin:user doesn't activate");
					return -3;
				case 2:
					System.out.println("userLogin:user is frozen");
					return -4;
				}
			} else {
				System.out.println("userLogin:user wrong email or password");
				return -2;
			}
		} else {
			System.out.println("userLogin:user not exists");
		}
		return -1;
	}

	/**
	 * @return 0-成功 -1-邮件发送失败 -2-用户不存在
	 */
	public int findBackPass(User user) {
		// TODO Auto-generated method stub
		List<User> users = userDao.findByUserEmail(user.getUserEmail());
		if (users.size() > 0) {
			System.out.println("findBackPass success");
			// 插入验证信息
			Verification ver = VerificationCreator.create(users.get(0));
			verificationDao.save(ver);
			// 发送邮件
			return sendEmail(ver);
		}
		System.out.println("findBackPass user not exists");
		return -2;
	}

	/**
	 * @return 0-成功 -1-邮件发送失败 -2-邮箱已被注册
	 */
	public int register(User user) {
		// TODO Auto-generated method stub
		String email = user.getUserEmail();
		List users = userDao.findByUserEmail(email);
		if (users.size() == 0) {
			user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
			user.setUserState((short) 1);
			user = userDao.merge(user);
			System.out.println("register successfully");
			// 插入验证信息
			Verification ver = VerificationCreator.create(user);
			verificationDao.save(ver);
			// 发送激活邮件
			return sendEmail(ver);
		} else {
			System.out.println("register email already exists");
			return -2;
		}
	}

	/**
	 * @return 0-成功
	 */
	public int userLogout(User user) {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		return 0;
	}

	/**
	 * @return 0-成功 -1-验证码过期 -2-验证信息错误
	 */
	public int certificate(Verification verification) {
		// 验证验证信息
		// Verification example = new Verification();
		// example.setUser(verification.getUser());
		// example.setMessage(verification.getMessage());
		List<Verification> verifications = verificationDao.findByProperties(
				new String[] { "user", "message" ,"time"},
				new Object[] { verification.getUser(),
						verification.getMessage(),
						verification.getTime()});
		if (verifications.size() > 0) {
			if (System.currentTimeMillis() - verification.getTime() <= 60000 * 10) {
//				verificationDao.delete(verifications.get(0));
				System.out.println("certificate successfully");
				return 0;
			} else {
				System.out.println("certificate expired");
				return -1;
			}
		} else {
			System.out.println("certificate error");
		}
		return -2;
	}

	/**
	 * @return 0-成功 -1-激活链接过期 -2-验证信息错误
	 */
	public int activate(Verification verification) {
		// TODO Auto-generated method stub
		int code = 0;
		if ((code = certificate(verification)) == 0) {
			System.out.println("activate successfully");
			User user = userDao.findById(verification.getUser().getUserId());
			user.setUserState((short) 0);		
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName",user.getUserName());
			userDao.attachDirty(user);
			Userinfo ui = new Userinfo();
			ui.setUser(user);
			ui.setUserGender("男");
			ui.setUserCredits(0);
			userinfoDao.save(ui);
			return 0;
		}
		System.out.println("activate failingly");
		return code;
	}

	/**
	 * @return 0-成功 -1-发送失败
	 */
	public int sendEmail(Verification verification) {
		String verificationUrl = CarharttConfig.URL + "UserBasic/activate?";
		verificationUrl += "userId=" + verification.getUser().getUserId()
				+ "&time=" + verification.getTime() + "&message="
				+ verification.getMessage();
		String to = verification.getUser().getUserEmail();
		String subject = "欢迎注册Carhartt网上商城";
		String content = "点击以下链接完成注册<br/>" + "<a href=\"" + verificationUrl
				+ "\">" + verificationUrl + "</a>";
		System.out.println(content);
		if (Mail.sendAndCc(to, subject, content)) {
			System.out.println("sendEmail successfully");
			return 0;
		} else {
			System.out.println("sendEmail failingly");
			return -1;
		}

	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public UserinfoDAO getUserinfoDao() {
		return userinfoDao;
	}

	public void setUserinfoDao(UserinfoDAO userinfoDao) {
		this.userinfoDao = userinfoDao;
	}

	public VerificationDAO getVerificationDao() {
		return verificationDao;
	}

	public void setVerificationDao(VerificationDAO verificationDao) {
		this.verificationDao = verificationDao;
	}
}
