package org.openmrs.module.todolist.domains;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Task")
@Table(name = "tasks")
public class Task extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "task_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Basic
	@Column(name = "name")
	private String name;
	
	@Basic
	@Column(name = "description", length = 5000)
	private String description;
	
	@Basic
	@Column(name = "completed")
	private Boolean completed;
	
	@Override
	public String getUuid() {
		return super.getUuid();
	}
	
	@Override
	public void setUuid(String uuid) {
		super.setUuid(uuid);
	}
	
	public Task() {
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getCompleted() {
		return completed;
	}
	
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Task task = (Task) o;
		return Objects.equals(id, task.id) && Objects.equals(patient, task.patient) && Objects.equals(name, task.name)
		        && Objects.equals(description, task.description) && Objects.equals(completed, task.completed);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, patient, name, description, completed);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
