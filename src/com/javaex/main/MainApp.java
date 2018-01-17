package com.javaex.main;

import java.util.List;

import com.javaex.dao.AuthorDao;
import com.javaex.dao.BookDao;
import com.javaex.vo.AuthorVo;
import com.javaex.vo.BookVo;

public class MainApp {
	public static void main(String[] args) {
		
		BookVo vo = new BookVo("별빛", "다림", "2008-02-21", 3);

		BookDao bDao = new BookDao();
		bDao.InsertBook(vo);
		
		
		List<BookVo> bookList = bDao.selectBookList();
		
		for(BookVo bDao1 : bookList) {
			System.out.println(bDao1);  
			
			
		
	/*	AuthorVo vo = new AuthorVo("강풀", "26년");
		
		AuthorDao aDao = new AuthorDao();
		aDao.InsertAuthor(vo);

		List<AuthorVo> authorList = aDao.selectAuthorList();
		
		for(AuthorVo aDao1 : authorList) {
		System.out.println(aDao1);  
		}*/
	}
}
}