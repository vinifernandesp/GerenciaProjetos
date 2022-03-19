package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date startDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date expectedDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date endDate;
	
	private String description;
	
	private String status;
	
	private Double budget;
	
	private String risk;
	
	@NotNull
	private Person manager;
	
	@ManyToMany(mappedBy="projects")
	private List<Person> people = new ArrayList<>();
	
	public Project() {
		super();
	}
	
	public Project(Long id, @NotNull String name, Date startDate, Date expectedDate, Date endDate, String description, String status, Double budget, String risk, @NotNull Person manager, List<Person> people) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.expectedDate = expectedDate;
		this.endDate = endDate;
		this.description = description;
		this.status = status;
		this.budget = budget;
		this.risk = risk;
		this.manager = manager;
		this.people = people;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getExpectedDate() {
		return expectedDate;
	}
	
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Double getBudget() {
		return budget;
	}
	
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	
	public String getRisk() {
		return risk;
	}
	
	public void setRisk(String risk) {
		this.risk = risk;
	}
	
	public Person getManager() {
		return manager;
	}
	
	public void setManager(Person manager) {
		this.manager = manager;
	}
	
	public List<Person> getPeople() {
		return people;
	}
	
	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
