
package com.portfolio.N_M.Interface;

import com.portfolio.N_M.Entity.Persona;
import java.util.List;



public interface IPersonaService {
  //Traer una lista de personas  
    public List<Persona> getPersona();
    
    //guardar un objeto de tipo persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto pero lo buscamos por id
    public void deletePersona(Long id);
    
    //Buscar una persona por ID
    public Persona findPersona(Long id);

}
