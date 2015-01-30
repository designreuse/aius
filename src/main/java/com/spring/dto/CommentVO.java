package com.spring.dto;

public class CommentVO {
	private int at_id;
	private int cmt_id;
	private String cmt_writer;
	private String cmt_data;
	private int cmt_hit;
	private String cmt_date;
	private String cmt_time;
	public int getAt_id() {
		return at_id;
	}
	public void setAt_id(int article_id) {
		this.at_id = article_id;
	}
	public int getCmt_id() {
		return cmt_id;
	}
	public void setCmt_id(int cmt_id) {
		this.cmt_id = cmt_id;
	}
	public String getCmt_writer() {
		return cmt_writer;
	}
	public void setCmt_writer(String cmt_writer) {
		this.cmt_writer = cmt_writer;
	}
	public String getCmt_data() {
		return cmt_data;
	}
	public void setCmt_data(String cmt_data) {
		this.cmt_data = cmt_data;
	}
	public int getCmt_hit() {
		return cmt_hit;
	}
	public void setCmt_hit(int cmt_hit) {
		this.cmt_hit = cmt_hit;
	}
	public String getCmt_date() {
		return cmt_date;
	}
	public void setCmt_date(String cmt_date) {
		this.cmt_date = cmt_date;
	}
	public String getCmt_time() {
		return cmt_time;
	}
	public void setCmt_time(String cmt_time) {
		this.cmt_time = cmt_time;
	}
	
	
}
