package com.spring.dto;

public class FileVO {
	private int article_id;
	private String attach_file_path;
	private String attach_file_name;
	private int file_id;
	private double file_size;
	private String file_upload_date;
	private String file_upload_time;
	public double getFile_size() {
		return file_size;
	}
	public void setFile_size(double file_size) {
		this.file_size = file_size;
	}
	public String getFile_upload_date() {
		return file_upload_date;
	}
	public void setFile_upload_date(String file_upload_date) {
		this.file_upload_date = file_upload_date;
	}
	public String getFile_upload_time() {
		return file_upload_time;
	}
	public void setFile_upload_time(String file_upload_time) {
		this.file_upload_time = file_upload_time;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getAttach_file_path() {
		return attach_file_path;
	}
	public void setAttach_file_path(String attach_file_path) {
		this.attach_file_path = attach_file_path;
	}
	public String getAttach_file_name() {
		return attach_file_name;
	}
	public void setAttach_file_name(String attach_file_name) {
		this.attach_file_name = attach_file_name;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
}
