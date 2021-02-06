package com.alberto.apirestfulservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "training")
public class training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
    @Column(name = "title")
	private String title;
	

	@Column(name = "LISTEXERCISE")
	@JsonIgnoreProperties("t")
    @JoinTable(
            name = "listexercise",
            joinColumns = @JoinColumn(name = "FK_TRAINING", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_EXERCISE", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<exercise> exercises;

	@JsonIgnoreProperties("lt")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUSER")
	private user creator;


    @Column(name = "time")
	private Long time;

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<exercise> getExercises() {
		return exercises;
	}


	public void setExercises(List<exercise> exercises) {
		this.exercises = exercises;
		for (exercise exercise : exercises) {
			List<training> list=exercise.getT();
			if(list==null) {
				list=new ArrayList<training>();
			}
			if(!list.contains(this)) {
				list.add(this);
			}
		}
	}


	public user getCreator() {
		return creator;
	}


	public void setCreator(user creator) {
		this.creator = creator;
		List<training> list=this.creator.getT();
		if(list==null) {
			list=new ArrayList();
		}
		if(!list.contains(this)) {
			list.add(this);
		}
	}


	public Long getTime() {
		return time;
	}


	public void setTime(Long time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "training [id=" + id + ", title=" + title + ", exercises=" + exercises + ", creator=" + creator
				+ ", time=" + time + "]";
	}

	
	
	
}
