package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import entities.Person;
import entities.Project;
import repositories.PersonRepository;
import repositories.ProjectRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Person findPerson(Long personId) {
		Optional<Person> person = personRepository.findById(personId);
		return person.orElseThrow(() -> new RuntimeException("Object not found! Id: " + personId + ", Type: " + Person.class.getName()));
	}
	
	public Page<Person> findAllPeople(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return personRepository.findAll(pageRequest);
	}
	
	public List<Person> findPeopleByProject(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		
		if(project.isEmpty()) {
			throw new RuntimeException("Object not found! Id: " + projectId + ", Type: " + Project.class.getName());
		}
		
		return personRepository.findByProjects(project.get());
	}
	
	public Person addProject(Long personId, Long projectId) {
		Optional<Person> person = personRepository.findById(personId);
		if(person.isEmpty()) {
			throw new RuntimeException("Object not found! Id: " + projectId + ", Type: " + Person.class.getName());
		}
		
		Optional<Project> project = projectRepository.findById(projectId);
		if(project.isEmpty()) {
			throw new RuntimeException("Object not found! Id: " + projectId + ", Type: " + Project.class.getName());
		}
		
		project.get().getPeople().add(person.get());
		person.get().getProjects().add(project.get());
		
		return personRepository.saveAndFlush(person.get());
	}
}
