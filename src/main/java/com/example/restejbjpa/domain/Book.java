package com.example.restejbjpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "book.all", query = "Select b from Book b"),
	@NamedQuery(name = "book.delete.all", query = "Delete from Book "),
	@NamedQuery(name = "book.findByYop", query = "Select b from Book b where b.yop = :yop"),
	@NamedQuery(name = "bookAuthor.findByAthorFirstName",
	query = "Select a.firstName, a.lastName, b.title, b.yop from Book b JOIN b.authors a where a.firstName = :firstName")
	})
@XmlRootElement
public class Book {
	
	private Long id;
	
	private String title;
	
	private int yop;
	
	private List<Person> authors = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYop() {
		return yop;
	}

	public void setYop(int yop) {
		this.yop = yop;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

}
