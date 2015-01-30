package com.spring.etc;

public class Btype {
	public String bt_return(String _b) {
		if (_b.equals("notice")) 		return"공지사항";
		else if (_b.equals("free")) 	return"자유게시판";
		else if (_b.equals("html5"))	return"HTML5";
		else if (_b.equals("nodejs"))	return"Node.js";
		else if (_b.equals("swift"))	return"스위프트";
		else if (_b.equals("gallery"))	return"갤러리";
		else if (_b.equals("jquery"))	return"제이쿼리";
		else if (_b.equals("cpp"))		return"C++";
		else if (_b.equals("project"))	return"프로젝트";
		else if (_b.equals("javabook"))	return"자바 책 저술";
		else if (_b.equals("java"))		return"JAVA";
		else if (_b.equals("css"))		return"CSS";
		else if (_b.equals("jsp"))		return"JSP";
		else							return"게시판";
	}
}
