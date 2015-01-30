package com.spring.dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class DaoFactory {
	@Bean
	public Board BoardDao() {
		return new Board(connectionMaker());
	}
	@Bean
	public Member MemberDao() {
		return new Member(connectionMaker());
	}
	@Bean
	public AttachFile AttachFileDao() {
		return new AttachFile(connectionMaker());
	}
	@Bean
	public Comment CommentDao() {
		return new Comment(connectionMaker());
	}
	@Bean
	public ConnectionMaker connectionMaker() {
		return new UConnectionMaker();
	}
}
