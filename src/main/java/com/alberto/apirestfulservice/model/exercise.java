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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "exercise")
public class exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "nameExercise")
	private String nameExercise;


	@Column(name = "description")
	private String description;


	@Column(name = "type")
	private String type;


	@Column(name = "repTime")
	private int repTime;


	@Column(name = "photo",columnDefinition="TEXT")
	private String photo;
	
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "exercises")
	@JsonIgnoreProperties("exercises")
	private List<training> t;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDUSER")
	@JsonIgnoreProperties("le")
	private user creator;
	
	public user getCreator() {
		return creator;
	}

	public void setCreator(user creator) {
		if (creator == null) {
			creator = new user();
		}
		this.creator = creator;
		List<exercise> list = this.creator.getLe();
		if (list == null) {
			list = new ArrayList();
		}
		if (!list.contains(this)) {
			list.add(this);
		}
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameExercise() {
		return nameExercise;
	}

	public void setNameExercise(String nameExercise) {
		this.nameExercise = nameExercise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRepTime() {
		return repTime;
	}

	public void setRepTime(int repTime) {
		this.repTime = repTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<training> getT() {
		return t;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setT(List<training> t) {
		if(t==null) {
			t = new ArrayList<training>();
		}
		this.t = t;
		for (training training : t) {
			List<exercise> list=training.getExercises();
			if(list==null) {
				list=new ArrayList<exercise>();
			}
			if(!list.contains(this)) {
				list.add(this);
			}
		}
	}

	@Override
	public String toString() {
		return "exercise [id=" + id + ", nameExercise=" + nameExercise + ", description=" + description + ", repTime="
				+ repTime + ", photo=" + photo + ", t=" + t + "]";
	}

}
