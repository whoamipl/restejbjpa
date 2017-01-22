package com.example.restejbjpa.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restejbjpa.domain.Book;
import com.example.restejbjpa.domain.Car;
import com.example.restejbjpa.domain.Person;
import com.example.restejbjpa.service.BookManager;
import com.example.restejbjpa.service.PersonManager;

@Path("book")
public class BookRestService {
	
	@EJB
	BookManager bm;
	
	@EJB
	PersonManager pm;
	
	
	@GET
	@Path("/manytomany")
	@Produces(MediaType.TEXT_PLAIN)
	public String testRelations(){
		
		Person p = new Person("Niki", "Lauda", 1945);
		
		Car c1 = new Car("Fiat", "Punto");
		Car c2 = new Car("Ford", "Fiesta");

		List<Car> cars = new ArrayList<>();
		cars.add(c1);
		cars.add(c2);
		
		p.addCars(cars);		
		pm.addPerson(p);
		
	
		System.out.println("Id c: " + c1.getId());
		
		System.out.println("\n\n@@@ Size of owners: " + c1.getOwners().size());
		
		//Car retrieved = pm.getCar(c1.getId());
		//Car retrieved = pm.updateCar(c1);
		
		
		//System.out.println("\n\n@@@ Size of owners: " + retrieved.getOwners().size());

		return "ManyToMany";
	}
	
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book){
		
		Person author1 = new Person("Jan", "Zieliński", 1823);		
		Person author2 = new Person("Paweł", "Kwiatkowski", 1833);	
		
		List<Person> authors = new ArrayList<>();
		authors.add(author1);
		authors.add(author2);
		
		book.setAuthors(authors);
		bm.addBook(book);
		
		//book.setTitle("Pan Wołodyjowski");
		return Response.	status(Response.Status.CREATED).build();	
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("id") Long id){
		
		Book retrieved = bm.getBook(id);
		
		//retrieved.setAuthors(bm.getAuthorsOfBook(id));
		
		//System.out.println("Authors :" + retrieved.getAuthors().size());
		
		return retrieved;
	}
	
	@GET
	@Path("/query/authors/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAuthorsOfBook(@PathParam("id") Long id){		
		return bm.getAuthorsOfBook(id) ;
	}
	
	@GET
	@Path("/query/yop/{yop}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBook(@PathParam("yop") int yop){
		return bm.findByYop(yop);
	}
	
	@GET
	@Path("/query/booksauthor/{aFirstName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksAuthor(@PathParam("aFirstName") String firstName){
		
		List<Object[]> rawAuthors = bm.getBookOfAuthrByAuthorName(firstName);
		JsonArrayBuilder authors = Json.createArrayBuilder();
		
		for(Object[] rawAuthor: rawAuthors){
			
			String fName = (String) rawAuthor[0];
			String lName = (String) rawAuthor[1];
			String title = (String) rawAuthor[2];
			int yop = (Integer) rawAuthor[3];
			
			authors.add(Json.createObjectBuilder()
					.add("firstName", fName)
					.add("lastName", lName)
					.add("title", title)
					.add("yop", yop));
			
		}
		
		JsonObject json =  Json.createObjectBuilder().add("result", authors).build();
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getAll(){
		return bm.getAll();
	}
	
	@DELETE
	public Response deletAll(){
		bm.deletAll();
		return Response.status(Response.Status.OK).build();
	}

}
