package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Person;
import entities.Project;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	List<Person> findByProjects(Project project);
}
