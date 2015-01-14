/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.servicios.excepciones;

import py.gestion.adm.persistencia.Persona;

/**
 *
 * @author ACER
 */
public class DocumentoDuplicadoExcepction extends Exception{
    
    private Persona persona;

    public DocumentoDuplicadoExcepction(Persona persona) {
        this.persona = persona;
    }
   
    
    

    @Override
    public String getMessage() {
        return "El documento " + persona.getTipoDocumento()+":"+ persona.getNroDocumento()+ " ya existe!" ;
    }
   
     
}
