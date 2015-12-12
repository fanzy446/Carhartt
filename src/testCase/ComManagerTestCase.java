//package testCase;
//
//import static org.junit.Assert.*;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import net.sf.json.JSONArray;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.carhartt.man.dao.BroadclassDAO;
//import com.carhartt.man.dao.UserDAO;
//import com.carhartt.man.model.Commodity;
//import com.carhartt.man.service.ComManService;
//
//public class ComManagerTestCase {
//	public ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	public ComManService cmService =(ComManService)context.getBean("comManService");
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void testBroadSub() {
//		JSONArray a = new JSONArray();
//		assertEquals(a, cmService.getBroadSubClass());
//	}
//	
//	@Test
//	public void testAdd(){
//		Commodity commodity = new Commodity();
//		commodity.setBroadClassName("衣服");
//		commodity.setSubClassName("流行");
//		commodity.setItemName("sdsf");
//		commodity.setItemDesc("sdf");
////		commodity.setItemPubDate(new Date(11, 12, 21));
//		commodity.setItemUnit("sdf");
//		commodity.setItemPrice(Float.valueOf(23));
//		commodity.setRemark("sdfsdf");
//		commodity.setItemCount("11");
//		cmService.addCommodity(commodity);
//	}
//	@Test
//	public void testGetItemList(){
//		List cm = cmService.getItemList();
//		List test = new ArrayList<Commodity>();
//		assertEquals("", ((Commodity)cm.get(0)).getBroadClassName());
//	}
//	
//	@Test
//	public void testGetClassTree(){
//		List cm = cmService.getClassTree();
////		JSONObject aa = new JSONo
//	}
//}
