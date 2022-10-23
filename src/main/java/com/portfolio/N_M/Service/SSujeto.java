/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.N_M.Service;

import com.portfolio.N_M.Entity.Sujeto;
import com.portfolio.N_M.Repository.RSujeto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SSujeto {
    @Autowired
    RSujeto rSujeto;
    
       public List<Sujeto> list(){
        return rSujeto.findAll();
    }
    
    public Optional<Sujeto> getOne(int id){
        return rSujeto.findById(id);
    }
    public Optional<Sujeto> getByNombre(String nombre){
        return rSujeto.findByNombre(nombre);
    }
    
    public void save(Sujeto sujeto){
        rSujeto.save(sujeto);
    }
    public void delete(int id){
        rSujeto.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rSujeto.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rSujeto.existsByNombre(nombre);
    }

    
}
