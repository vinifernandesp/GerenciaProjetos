package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import entities.Project;
import entities.enums.StatusProject;
import repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project insert(Project newProject) {
		newProject.setId(null);
		newProject.setStatus(StatusProject.EM_ANALISE.getDescription());
		
		return projectRepository.saveAndFlush(newProject);
	}
	
	public Project update(Project projectToUpdate) {
		Optional<Project> project = projectRepository.findById(projectToUpdate.getId());
		if(project.isEmpty()) {
			throw new RuntimeException("Object not found to Update! Id: " + projectToUpdate.getId() + ", Type: " + Project.class.getName());
		}
		
		projectToUpdate.setStatus(project.get().getStatus());
		
		return projectRepository.saveAndFlush(projectToUpdate);
	}
	
	public Project updateStatus(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		if(project.isEmpty()) {
			throw new RuntimeException("Object not found to Update Status! Id: " + projectId + ", Type: " + Project.class.getName());
		}
		
		StatusProject currentStatus = StatusProject.toEnum(null, project.get().getStatus());
		if (StatusProject.ENCERRADO.equals(currentStatus)) {
			throw new RuntimeException("Object cannot be Deleted! Status Violation");
		}
		
		StatusProject nextStatusToUpdate = StatusProject.toEnum(currentStatus.getCod() + 1, null);
		
		project.get().setStatus(nextStatusToUpdate.getDescription());
		
		return projectRepository.saveAndFlush(project.get());
	}
	
	public Project cancelProject(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		if(project.isEmpty()) {
			throw new RuntimeException("Object not found to Update Status! Id: " + projectId + ", Type: " + Project.class.getName());
		}
		
		project.get().setStatus(StatusProject.CANCELADO.getDescription());
		
		return projectRepository.saveAndFlush(project.get());
	}
	
	public void delete(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		if(project.isEmpty()) {
			throw new RuntimeException("Object not found to Delete! Id: " + projectId + ", Type: " + Project.class.getName());
		}
		
		StatusProject status = StatusProject.toEnum(null, project.get().getStatus());
		
		if(
				StatusProject.INICIADO.equals(status) ||
				StatusProject.EM_ANDAMENTO.equals(status) ||
				StatusProject.ENCERRADO.equals(status)) {
			throw new RuntimeException("Object cannot be Deleted! Status Violation");
		}
		
		projectRepository.delete(project.get());
	}
	
	public Project findProject(Long projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		return project.orElseThrow(() -> new RuntimeException("Object not found! Id: " + projectId + ", Type: " + Project.class.getName()));
	}
	
	public Page<Project> findAllProjects(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return projectRepository.findAll(pageRequest);
	}
}
