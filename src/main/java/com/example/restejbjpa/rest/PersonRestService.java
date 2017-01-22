package com.example.restejbjpa.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restejbjpa.domain.Person;

@Path("person")
@Stateless
public class PersonRestService {	
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("personId") Long id){
		return em.find(Person.class, id);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons(){
		return em.createNamedQuery("person.getAll").getResultList();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){
		em.persist(person);
		return Response.status(201).entity("Person").build(); 
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "REST Persons Service is running now!";
	}
	
	@DELETE
	public Response clearPersons(){
		em.createNamedQuery("person.deleteAll").executeUpdate();
		return Response.status(200).build();
	}

}
