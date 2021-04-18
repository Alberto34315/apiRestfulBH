/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alberto.apirestfulservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "records")
public class records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@JsonBackReference(value = "lr")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "FK_IDTRAINIG")
    @JsonIgnoreProperties(value = {"lr"}, allowSetters = true)
    private training idTrai;

    @Column(name = "Date", columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;
    
    @JsonBackReference(value = "lrecords")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "IDUSER")
    @JsonIgnoreProperties(value = {"lrecords"}, allowSetters = true)
    private user idu;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public training getIdTrai() {
        return idTrai;
    }

    public void setIdTrai(training idTrai) {
          if (idTrai == null) {
            idTrai = new training();
        }
        this.idTrai = idTrai;
        List<records> list = this.idTrai.getLr();
        if (list == null) {
            list = new ArrayList();
        }
        if (!list.contains(this)) {
            list.add(this);
        }
    }

    public user getIdu() {
		return idu;
	}

	public void setIdu(user idu) {
		  if (idu == null) {
			  idu = new user();
	        }
		this.idu = idu;
		   List<records> list = this.idu.getLrecords();
	        if (list == null) {
	            list = new ArrayList();
	        }
	        if (!list.contains(this)) {
	            list.add(this);
	        }
	}


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    
}
