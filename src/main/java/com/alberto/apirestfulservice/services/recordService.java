/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alberto.apirestfulservice.services;

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.records;
import com.alberto.apirestfulservice.repositories.recordRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class recordService {

    @Autowired
    recordRepository repository;

    public List<records> getAllRecord() {
        List<records> itemList = repository.findAll();

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<records>();
        }
    }

    public records getRecordById(Long id) throws RecordNotFoundException {
        Optional<records> item = repository.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }

    public List<records> getAllRecordsByIdUser(Long id) {
        List<records> itemList = repository.getAllRecordsByIdUser(id);

        if (itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<records>();
        }
    }
    
    public records createRecord(records entity) {
        entity = repository.save(entity);
        return entity;
    }

    public records UpdateRecord(records entity) throws RecordNotFoundException {
        if (entity.getId() != null) {
            Optional<records> item = repository.findById(entity.getId());
            if (item.isPresent()) {
                records newEntity = item.get();
                // newEntity.setId(entity.getId());
                newEntity.setIdTrai(entity.getIdTrai());
                newEntity.setLocalDateTime(entity.getLocalDateTime());
                newEntity.setIdu(entity.getIdu());
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of item given", 0l);
        }
    }

    public void deleteRecordById(Long id) throws RecordNotFoundException {
        Optional<records> item = repository.findById(id);

        if (item.isPresent()) {
            repository.deleteFromRecord(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id", id);
        }
    }
    
}