package org.openmrs.module.todolist.domains;

import org.openmrs.BaseOpenmrsData;

import javax.persistence.*;

@Entity(name = "Todo")
@Table(name = "todos")
public class Todo extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Basic
	@Column(name = "title")
	private String title;
	
	@Basic
	@Column(name = "description")
	private String description;
	
	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer integer) {
		this.id = integer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
