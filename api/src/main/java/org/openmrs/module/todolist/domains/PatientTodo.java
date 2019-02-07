/*
package org.openmrs.module.todolist.domains;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PatientTodo")
@Table(name = "patienttodos")
public class PatientTodo extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id")
	private List<Todo> todos;
	
	@Override
	public Integer getId() {
		return this.Id;
	}
	
	@Override
	public void setId(Integer integer) {
		this.Id = integer;
	}

    public PatientTodo(Integer id) {
        Id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
*/
