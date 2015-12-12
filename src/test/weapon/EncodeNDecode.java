package test.weapon;

import java.security.MessageDigest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.carhartt.man.dao.VSaleDAO;
import com.carhartt.man.model.VSale;

public class EncodeNDecode {
	// MD5加码。32位
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = inStr.getBytes();
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {

			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}

	// 测试主函数
	public static void main(String args[]) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		VSaleDAO dao= (VSaleDAO) ctx.getBean("VSaleDAO");
		VSale sale=dao.findById(32758);
		System.out.println(sale.getSale());
		
	}
}
// package test.weapon;
//
// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
//
// public class EncodeNDecode {
// public String str;
//
// public void transFormMD5(String text) {
// try {
// MessageDigest md = MessageDigest.getInstance("MD5");
// md.update(text.getBytes());
// byte b[] = md.digest();
// int x;
// StringBuffer buf = new StringBuffer("");
// for (int i = 0; i < b.length; i++) {
// x = b[i];
// if (x < 0)
// x += 256;
// if (x < 16)
// buf.append("0");
// buf.append(Integer.toHexString(x));
// }
// str = buf.toString();
// System.out.println("32位加密后的字符串: " + buf.toString());// 32位的加密
// System.out
// .println("16位加密后的字符串: " + buf.toString().substring(8, 24));// 16位的加密
// } catch (NoSuchAlgorithmException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//
// public static void main(String agrs[]) {
// EncodeNDecode tm = new EncodeNDecode();
// tm.transFormMD5("1");// 进行转换
// }
// }