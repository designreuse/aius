package com.spring.etc;

public class Btype {
	public String bt_return(String _b) {
		if (_b.equals("notice")) 		return"NOTICE";
		else if (_b.equals("free")) 	return"FREEBOARD";
		else if (_b.equals("html5"))	return"HTML5";
		else if (_b.equals("nodejs"))	return"NODE.JS";
		else if (_b.equals("swift"))	return"SWIFT";
		else if (_b.equals("gallery"))	return"GALLERY";
		else if (_b.equals("jquery"))	return"JQUERY";
		else if (_b.equals("cpp"))		return"CPP";
		else if (_b.equals("project"))	return"PROJECT";
		else if (_b.equals("javabook"))	return"JAVABOOK";
		else if (_b.equals("java"))		return"JAVA";
		else if (_b.equals("css"))		return"CSS";
		else if (_b.equals("jsp"))		return"JSP";
		else							return"BOARD";
	}
}
