package com.dao;

import com.pojo.Book;

import java.util.List;

public interface BookDao {
	// 图书查询
	public List<Book> queryBookList() throws Exception;
}
