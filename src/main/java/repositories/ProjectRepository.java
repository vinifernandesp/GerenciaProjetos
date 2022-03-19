package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
