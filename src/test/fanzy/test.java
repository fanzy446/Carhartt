package test.fanzy;

import java.util.ArrayList;

import com.carhartt.man.model.Broadclass;

public class test {
	
	public static void main(StringBuffer[] args) {
		ArrayList<StringBuffer> temp = new ArrayList<StringBuffer>();
		temp.add(new StringBuffer("a"));
		temp.add(new StringBuffer("b"));
		StringBuffer temp1 = temp.get(1);
		System.out.println(temp1.hashCode()==temp.get(1).hashCode());
		System.out.println(temp1.hashCode()==temp.get(1).hashCode());
		System.out.println(temp.get(1) + "");
	}
}

