package com.spring.etc;

public class Btype {
	public String bt_return(String _b) {
		if (_b.equals("notice")) 		return"��������";
		else if (_b.equals("free")) 	return"�����Խ���";
		else if (_b.equals("html5"))	return"HTML5";
		else if (_b.equals("nodejs"))	return"Node.js";
		else if (_b.equals("swift"))	return"������Ʈ";
		else if (_b.equals("gallery"))	return"������";
		else if (_b.equals("jquery"))	return"��������";
		else if (_b.equals("cpp"))		return"C++";
		else if (_b.equals("project"))	return"������Ʈ";
		else if (_b.equals("javabook"))	return"�ڹ� å ����";
		else if (_b.equals("java"))		return"JAVA";
		else if (_b.equals("css"))		return"CSS";
		else if (_b.equals("jsp"))		return"JSP";
		else							return"�Խ���";
	}
}
