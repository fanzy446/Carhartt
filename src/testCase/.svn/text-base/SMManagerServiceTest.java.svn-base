//package testCase;
//
//import static org.junit.Assert.*;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.carhartt.man.dao.AnnouncementDAO;
//import com.carhartt.man.dao.MessageDAO;
//import com.carhartt.man.model.Announcement;
//import com.carhartt.man.model.Message;
//import com.carhartt.man.service.ComManService;
//import com.carhartt.man.service.SMManService;
//
//public class SMManagerServiceTest {
//	public ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	public SMManService smService =(SMManService)context.getBean("sMManService");
//	public AnnouncementDAO annDao =(AnnouncementDAO)context.getBean("AnnouncementDAO");
//	public MessageDAO msgDao =(MessageDAO)context.getBean("MessageDAO");
//
//
//	@Before
//	public void setUp() throws Exception {
//	}
//	
//	@Test
//	public void testfindbyId() {
//		JSONObject msg = smService.findMsgbyId(2);
//		JSONObject a = new JSONObject();
//		JSONObject ann = smService.findAnnbyId(1);
//		assertEquals(ann.get("announceTitle") == "RJVFTEKJHC", msg.get("mesgTitle") == "WIHJTTLCQQ");
////		assertEquals(a, ann);
//	}
//	
//	public void testfindbyIdfail(){
//		JSONObject msg = smService.findMsgbyId(0);
//		JSONObject a = new JSONObject();
//		assertEquals(a, msg);
//	}
//	
//	@Test
//	public void testModify(){
//		boolean isSuccess = smService.annModify(2, "YDDUBEFMAD", "TODAYISABEAUTIIFULDAY ", "1");
//		if(isSuccess) isSuccess = smService.MsgModify(3, "YDDUBEFMAD", "TODAYISABEAUTIIFULDAY!", "0");
//		assertEquals(true, isSuccess);
//	}
//	
//	@Test
//	public void testModifyFail(){
//		boolean isSuccess = smService.annModify(2, "", "TODAYISABEAUTIIFULDAY ", "1");
//		if (!isSuccess) isSuccess = smService.annModify(2, "YDDUBEFMAD", "", "1");
//		if (!isSuccess) isSuccess = smService.annModify(0, "YDDUBEFMAD", "TODAYISABEAUTIIFULDAY", "1");
//		assertEquals(false, isSuccess);
//	}
//	
//	@Test
//	public void testsearchMsg() {
//		JSONObject sm = smService.searchMsg(0, "", 0, "", 0, 10);
////		JSONArray a = new JSONArray();
//		assertEquals(true, sm.getInt("count") != 0);
//	}
//	
//
//
////	@Test
////	public void testCounter() {
////		int a = smService.getMsgRecordCount();
////		int b = smService.getAnnRecordCount();
////		assertEquals(1, a);
////		assertEquals(1, b);
////	}
//	
////	@Test
////	public void testModifyMsg() {
////		boolean sm = smService.MsgModify(1, "hahaha", "today is a beautify day!", "0");
////		assertEquals(true, sm);
////	}
//	@Test
//	public void testGetAnnList() {
//		JSONObject sm = smService.getAnnouncementList(0,10);
////		JSONArray a = new JSONArray();
//		assertEquals(true, sm.getInt("count")!=0);
//	}
//	
//	@Test
//	public void testDeleteAnn(){
//		int id = 1;
//		assertEquals(false, smService.deleteAnnouncement(id));
//	}
//	
//	@Test
//	public void testGetMsgList() {
//		JSONObject sm = smService.getMessageList(0,12);
////		JSONObject a = new JSONObject();
//		assertEquals(true, sm.getInt("count") != 0);
//	}
//	
//	@Test
//	public void testDeleteMsg(){
//		int id = 132;
//		assertEquals(false, smService.deleteMessage(id));
//	}
//	
//}
