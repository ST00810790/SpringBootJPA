package com.suwathi.database.databasedemo.jpa;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.suwathi.database.databasedemo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PersonJpaRepository {

	// connect to the database
	@PersistenceContext
	EntityManager em;

	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = em.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}

	public Person findById(int id) {
		return em.find(Person.class, id);// JPA
	}

	public Person insert(Person person) {
		return em.merge(person);
	}

	public Person updatePerson(int id, String name, String location,Date birthDate) {
		Person person = findById(id);
		person.setName(name);
		person.setLocation(location);
		person.setBirthDate(birthDate);
		em.merge(person);
		return person;
		
	}
	
}