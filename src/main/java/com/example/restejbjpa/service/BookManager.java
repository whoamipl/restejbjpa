package com.example.restejbjpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.restejbjpa.domain.Book;
import com.example.restejbjpa.domain.Person;

@Stateless
public class BookManager {
	
	@PersistenceContext
	EntityManager em;
	
	public void addBook(Book book){	
		em.persist(book);
		//book.setTitle("Ogniem i Mieciem");
	}
	
	public Book getBook(Long id){
		Book retrieved = em.find(Book.class, id);
		return retrieved;
	}
	
	public List<Person> getAuthorsOfBook(Long id){
		Book retrieved = em.find(Book.class, id);
		List<Person> result = new ArrayList<>(retrieved.getAuthors());
		return result;
	}
		
	@SuppressWarnings("unchecked")
	public List<Object[]> getBookOfAuthrByAuthorName(String firstName){		
		return em.createNamedQuery("bookAuthor.findByAthorFirstName").setParameter("firstName", firstName).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getAll(){
		return em.createNamedQuery("book.all").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> findByYop(int yop){
		return em.createNamedQuery("book.findByYop").setParameter("yop", yop).getResultList();
	}
	
	public void deletAll(){
		em.createNamedQuery("book.delete.all").executeUpdate();
	}
	
}
