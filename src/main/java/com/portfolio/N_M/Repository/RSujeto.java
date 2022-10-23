/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.N_M.Repository;

import com.portfolio.N_M.Entity.Sujeto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RSujeto extends JpaRepository<Sujeto, Integer> {
    public Optional<Sujeto> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
