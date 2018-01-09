package com.example.restejbjpa;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.parsing.Parser;
import org.junit.*;

import com.example.restejbjpa.domain.Person;
import com.jayway.restassured.RestAssured;
import sun.nio.cs.Surrogate;

public class PersonServiceITest {
	
	private static final String PERSON_FIRST_NAME = "Jasiu";
	private static final String PERSON_LAST_NAME = "Przyslowiowy-Kowalski";
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restejbjpa/api";
	}
	
    @Test
	@Before
	public void addPerson(){

		Person person = new Person(PERSON_FIRST_NAME, PERSON_LAST_NAME, 1976);

		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(person).
	    when().
	    post("/person/").
				then().assertThat().statusCode(201);
	}

	@Test
	@After
	public void getPerson(){
		RestAssured.defaultParser = Parser.JSON;
		Person actual = get("/person/3").as(Person.class);
		Person expected = new Person(PERSON_FIRST_NAME, PERSON_LAST_NAME, 1976);
		assertEquals(expected.getFirstName(),actual.getFirstName());
	}

	@Test
	public void testGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		gson.toJson(new Person("Michał", "Fierek", 1994));
		System.out.println(gson.toJson(new Person("Michał", "Fierek", 1994)));
	}
}
