//package testCase;
//
//import static org.junit.Assert.*;
//
//import net.sf.json.JSONArray;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.carhartt.man.dao.OrderDAO;
//import com.carhartt.man.service.OMManService;
//
//public class OMMangerServiceTest {
//	public ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	public OMManService OMMS =(OMManService)context.getBean("ommanService");
//	@Test
//	public void test() {
//		 JSONArray a = OMMS.getOrders();
//		 JSONArray b = new JSONArray();
//		 assertEquals(b, a);
//	}
//
//}
