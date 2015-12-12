//package testCase;
//
//import static org.junit.Assert.*;
//
//import java.sql.Date;
//import java.util.Iterator;
//
//import javax.annotation.Resource;
//
//
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.carhartt.man.dao.UserDAO;
//import com.carhartt.man.model.User;
//import com.carhartt.man.service.UserManagerService;
//
//public class UserManagerServiceTestCase {
//	public ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	public UserDAO userDao =(UserDAO)context.getBean("UserDAO");
//	public UserManagerService um =(UserManagerService)context.getBean("userManagerService");
//	
//	@Before
//	public void setUp() throws Exception {
//	}
//	
//	@Test
//	public void testGetUserInfo(){  // 测试后去用户信息函数
//		JSONObject temp = new JSONObject();
//		temp.put("passwHint", "HYZRJJASDCKCJPFFUNME");
//		temp.put("passAnswer", "JMSVSKPRHXDOFNPEUJBV");
//		temp.put("realName", "RSYKTDERQP");
//		temp.put("idCard", "EWWMWWFMHMEJUYTDCT");
//		temp.put("userPhone","JIJMEDIGIFS");
//		temp.put("userGender", "男");
//		temp.put("userCredits", "573");
//		temp.put("userId", 193);
//		assertEquals(temp, um.getUserInfo(193));
//	}
//	
//	
//	@Test
//	public void testGetAll() {
//		JSONObject temp = um.getUserList(0, 10);
//		assertEquals(true, temp.getInt("count") != 0);
////		int count = userDao.getAllRecordNum(0, "", (short)1);
////		assertEquals(51, count);
//	}
//	
//	@Test 
//	public void testFreezeAccount(){
//		boolean isSuccess = um.FreezeAccount(161, 1);
//		assertEquals(true, isSuccess);
//	}
//	
//	@Test
//	public void testGetAllTooSmall() {
//		JSONObject temp = um.getUserList(0, 0);
//		JSONArray list = new JSONArray();
//		assertEquals(list, temp.get("list"));
////		int count = userDao.getAllRecordNum(0, "", (short)1);
////		assertEquals(51, count);
//	}
//	
//	@Test
//	public void testGetAllMax() {
//		JSONObject temp = um.getUserList(0, 10000000);
//		assertEquals(true, temp.getInt("count") != 0);
////		int count = userDao.getAllRecordNum(0, "", (short)1);
////		assertEquals(51, count);
//	}
//	
//	@Test
//	public void testSearch(){
//		JSONObject temp = um.searchUsers(161, "", (short)2, 0, 10);
//		assertEquals(true, temp.getInt("count") != 0);
//	}
//	
//	@Test
//	public void testSearchNoMatch(){
//		JSONObject temp = um.searchUsers(161, "SDFSD", (short)2, 0, 10);
//		assertEquals(true, temp.getInt("count") == 0);
//	}
//	
//	@Test
//	public void testUpdate(){
//		boolean isSuccess = um.userInfoModify(161, (short)2, "test");
//		assertEquals(true, isSuccess);
//	}
//	
//	@Test
//	public void testUpdateNoMatch(){
//		boolean isSuccess = um.userInfoModify(-1, (short)2, "test");
//		assertEquals(false, isSuccess);
//	}
//	
//	
//
//}

