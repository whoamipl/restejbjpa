package com.example.restejbjpa.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.restejbjpa.domain.Book;

@XmlRootElement
public class BookResponse {
	
	private List<Book> book = new ArrayList<>();

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}
}
