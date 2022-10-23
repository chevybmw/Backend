/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.N_M.Controller;

import com.portfolio.N_M.Dto.dtoSujeto;
import com.portfolio.N_M.Entity.Sujeto;
import com.portfolio.N_M.Security.Controller.Mensaje;
import com.portfolio.N_M.Service.SSujeto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/sujeto")
@CrossOrigin(origins = "http://localhost:4200")
public class CSujeto {
    @Autowired
    
    SSujeto sSujeto;
    
   
    @GetMapping("/lista")
    public ResponseEntity<List<Sujeto>> list(){
        List<Sujeto> list = sSujeto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSujeto dtosujeto){
     if (StringUtils.isBlank(dtosujeto.getNombre())) 
         return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
     if(sSujeto.existsByNombre(dtosujeto.getNombre()))
         return new ResponseEntity(new Mensaje("Ese nombre existe"), HttpStatus.BAD_REQUEST);
     
     if (StringUtils.isBlank(dtosujeto.getDescripcion())) 
         return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
     
     Sujeto sujeto = new Sujeto(dtosujeto.getNombre(), dtosujeto.getDescripcion(), dtosujeto.getApellido(), dtosujeto.getImg());
     sSujeto.save(sujeto);
     
     return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
     
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSujeto dtosujeto){
      //validamos si existe el id
        if(!sSujeto.existsById(id)) 
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
      //compara nombre de experiencias
        if(sSujeto.existsByNombre(dtosujeto.getNombre()) && sSujeto.getByNombre(dtosujeto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        //no puede estar vacio el campo
        if (StringUtils.isBlank(dtosujeto.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(dtosujeto.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Sujeto sujeto = sSujeto.getOne(id).get();
        sujeto.setNombre(dtosujeto.getNombre());
        sujeto.setApellido(dtosujeto.getApellido());
        sujeto.setDescripcion(dtosujeto.getDescripcion());
        sujeto.setImg(dtosujeto.getImg());
        
        sSujeto.save(sujeto);
        return new ResponseEntity(new Mensaje("Persona actualizado"), HttpStatus.OK);
        
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //validamos si existe el id
        if(!sSujeto.existsById(id)) 
          return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sSujeto.delete(id);
        
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Sujeto> getById(@PathVariable("id") int id){
        if(!sSujeto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Sujeto sujeto = sSujeto.getOne(id).get();
        return new ResponseEntity(sujeto, HttpStatus.OK);
    }
   
    
    
}
