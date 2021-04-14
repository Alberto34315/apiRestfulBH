/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alberto.apirestfulservice.controllers;

import com.alberto.apirestfulservice.exception.RecordNotFoundException;
import com.alberto.apirestfulservice.model.records;
import com.alberto.apirestfulservice.services.recordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/records")
public class recordServiceController {

    @Autowired
    recordService service;

    @ApiOperation(value = "getAllRecord", notes = "Esta funcion nos devolvera una lista de todos los entrenamientos realizados, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = records.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<records>> getAllRecord() {
        List<records> list = service.getAllRecord();

        return new ResponseEntity<List<records>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getRecordById", notes = "Esta funcion nos devolvera un entrenamiento realizado por su id, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = records.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{id}")
    public ResponseEntity<records> getRecordById(@PathVariable("id") Long id) throws RecordNotFoundException {
        records entity = service.getRecordById(id);

        return new ResponseEntity<records>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getAllRecordsByIdUser", notes = "Esta funcion nos devolvera una lista de entrenamientos realizados por el id del usuario que lo creo, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = records.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<records>> getAllRecordsByIdUser(@PathVariable("id") Long id) throws RecordNotFoundException {
        List<records> list = service.getAllRecordsByIdUser(id);

        return new ResponseEntity<List<records>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "createRecord", notes = "Esta funcion nos creara un entrenamiento realizado si le pasamos un objeto de tipo records, y nos devolvera el record creado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = records.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<records> createRecord(@Valid @RequestBody records myItem) {
        //System.out.println(myItem);
        records created = service.createRecord(myItem);
        return new ResponseEntity<records>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "UpdateRecord", notes = "Esta funcion nos actualizara un entrenamiento realizado si le pasamos un objeto de tipo records, y nos devolvera el record actualizado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = records.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<records> UpdateRecord(@Valid @RequestBody records myItem) throws RecordNotFoundException {
        records updated = service.UpdateRecord(myItem);
        return new ResponseEntity<records>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "deleteRecordById", notes = "Esta funcion nos eliminara un entrenamiento realizado si le pasamos su id (Long), y nos devolvera un HttpStatus")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = records.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{id}")
    public HttpStatus deleteRecordById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteRecordById(id);
        return HttpStatus.ACCEPTED;
    }
}
