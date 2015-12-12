//package testCase;
//
//import static org.junit.Assert.*;
//
//import java.util.Iterator;
//
//import net.sf.json.JSONObject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.carhartt.man.dao.ManagerDAO;
//import com.carhartt.man.model.Manager;
//import com.carhartt.man.service.SMManService;
//import com.carhartt.man.service.SuperManService;
//
//public class superManagerServiceTest {
//	public ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	public SuperManService supermService =(SuperManService)context.getBean("superManService");
//	public ManagerDAO managerDao =(ManagerDAO)context.getBean("ManagerDAO");
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void testSearch() {
//		JSONObject actual = supermService.searchSuper(0, 10);
//		JSONObject expected = new JSONObject();
//		assertEquals(true, actual.getInt("count") != 0);
//	}
//	
//	@Test
//	public void testModify(){
//		boolean isSuccess = supermService.superManagerInfoModify(6, "AHMDSHLN", "CGADRYLK", (short)5, "NTBSOYSCXW", "");
//		assertEquals(true, isSuccess);
//	}
//	
//	@Test
//	public void testAdd(){
//		boolean isSuccess = supermService.addManager("LINMIANCHENG", "HJNBRDDU", (short)2, "LNQGRDDHRR", "1");
//		assertEquals(true, isSuccess);
//	}
//	
//	@Test
//	public void testDelete(){
//		Iterator temp = managerDao.findByManagerName("LINMIANCHENG").iterator();
//		if(temp.hasNext()){
//			boolean isSuccess = supermService.deleteManagerById(((Manager)temp.next()).getManagerId());
//			assertEquals(true, isSuccess);
//		}else{
//			fail("delete instance error");
//		}
//		
//	}
//}
