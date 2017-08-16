package cn.netkiller.oauth.server.controller;

//import java.security.Principal;
import java.util.Collection;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import cn.netkiller.oauth.server.model.Party;
import cn.netkiller.oauth.server.model.Person;
import cn.netkiller.oauth.server.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonRepository personRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Person>> getPeople() {
		return new ResponseEntity<Collection<Person>>(personRepo.findAll(), HttpStatus.OK);
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public ResponseEntity<Person> getPerson(@PathVariable long id) {
	// Person person = personRepo.findOne(id);
	//
	// if (person != null) {
	// return new ResponseEntity<Person>(personRepo.findOne(id), HttpStatus.OK);
	// } else {
	// return new ResponseEntity<Person>(null, HttpStatus.NOT_FOUND);
	// }
	// }
	//
	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<?> addPerson(@RequestBody Person person) {
	// return new ResponseEntity<Person>(personRepo.save(person),
	// HttpStatus.CREATED);
	// }
	//
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// public ResponseEntity<Void> deletePerson(@PathVariable long id, Principal
	// principal) {
	// Person currentPerson = personRepo.findByUsername(principal.getName());
	//
	// if (currentPerson.getId() == id) {
	// personRepo.delete(id);
	// return new ResponseEntity<Void>(HttpStatus.OK);
	// } else {
	// return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	// }
	// }
	//
	// @RequestMapping(value = "/{id}/parties", method = RequestMethod.GET)
	// public ResponseEntity<Set<Party>> getPersonParties(@PathVariable long id) {
	// Person person = personRepo.findOne(id);
	//
	// if (person != null) {
	// return new ResponseEntity<Set<Party>>(person.getParties(), HttpStatus.OK);
	// } else {
	// return new ResponseEntity<Set<Party>>(null, HttpStatus.NOT_FOUND);
	// }
	// }

}
