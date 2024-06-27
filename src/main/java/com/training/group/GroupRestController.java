package com.training.group;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/groups")
public class GroupRestController {

	@GetMapping("/{id}")
	public String getGroups(@PathVariable String id) {
		return " id " + id;
	}
	@PostMapping("/User")
	public String postGroups( ) {
		return " post helloworld";
	}
	@PutMapping
	public String putGroups() {
		return " put hello rohit";
	}
	@DeleteMapping
	public String deleteGroups() {
		return " delete hello kumar";
	}
	
}
