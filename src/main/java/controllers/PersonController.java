package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.PersonService;

@RestController
@RequestMapping(value="/categorias")
public class PersonController {

	@Autowired
	private PersonService service;
	
	
}
