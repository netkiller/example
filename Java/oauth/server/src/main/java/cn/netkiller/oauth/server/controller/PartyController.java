//package cn.netkiller.oauth.server.controller;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.netkiller.oauth.server.model.Party;
//import cn.netkiller.oauth.server.repository.PartyRepository;
//
//@RestController
//@RequestMapping("/parties")
//public class PartyController {
//
//	@Autowired
//	private PartyRepository partyRepo;
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<Collection<Party>> getParties() {
//		return new ResponseEntity<Collection<Party>>(partyRepo.findAll(), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Party> getParty(@PathVariable long id) {
//		Party party = partyRepo.findOne(id);
//
//		if (party != null) {
//			return new ResponseEntity<Party>(partyRepo.findOne(id), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Party>(null, HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<?> addParty(@RequestBody Party party) {
//		return new ResponseEntity<Party>(partyRepo.save(party), HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> deletePartyn(@PathVariable long id) {
//		partyRepo.delete(id);
//
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//
//}
