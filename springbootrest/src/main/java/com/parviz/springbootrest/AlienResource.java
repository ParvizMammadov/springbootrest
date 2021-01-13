package com.parviz.springbootrest;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlienResource {
	
	@Autowired
	AlienRepository repo;
	
	@GetMapping("aliens")
	public List<Alien> getAliens() {
		
		List<Alien> aliens = (List<Alien>) repo.findAll();
		
		return aliens;
	}
	
	@GetMapping("aliens/alien/{id}")
	public Alien getAlien(@PathVariable("id") int id) {
		
		Alien alien = repo.findById(id).orElse(null);
		
		return alien;
	}
	
	@PostMapping(path="aliens/alien", consumes= {"application/json"})
	public String addAlien(@RequestBody Alien alien) {
		
		if(alien == null)
			return "No aliens to be added";
		
		repo.save(alien);
		
		return "Alien saved successfully";
	}
	
	@PutMapping(path="aliens/alien", consumes= {"application/json"})
	public String updateAlien(@RequestBody Alien alien) {
		
		if(alien == null)
			return "No aliens to be updated";
		
		repo.save(alien);
		
		return "Alien updated successfully";
	}
	
	@DeleteMapping(path="aliens/alien/{id}", consumes= {"application/json"})
	public String deleteAlien(@PathVariable("id") int id) {
		
		Alien alien = getAlien(id);
		if(alien == null)
			return "Alien not found. Couldn't delete Alien";
		
		repo.delete(getAlien(id));
		return "Alien deleted successfully";
	}
}
