package com.carhartt.util;

import com.carhartt.man.dao.VerificationDAO;
import com.carhartt.man.model.User;
import com.carhartt.man.model.Verification;

public class VerificationCreator {
	public static Verification create(User user)
	{
		Verification result = new Verification();
		result.setUser(user);
		result.setTime(System.currentTimeMillis());
		result.setMessage((int)(Math.random()*1000000) + "");
		/*VerificationDAO verificationDao = new VerificationDAO();
		verificationDao.save(result);*/
		return result;
	}
}
