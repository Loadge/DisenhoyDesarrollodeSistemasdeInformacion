/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package decine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ana
 */
public class DeCine { 
    
    // Definición de atributos 
    private Map<String,UsuarioVIP> misUsuarios = new HashMap();
    private Map<String, Actor> misActores = new HashMap();
    private Map <String, Director> misDirectores = new HashMap();
    private Map <String, FestivalPremio> misFestivales = new HashMap();
    // incluir los que faltan
    
    // implementación del Singleton
    private static DeCine instancia = new DeCine();
    public static DeCine getDeCine(){
        return instancia;
    }
    private DeCine(){}
    
    // Búsqueda de un determinado elemento en un Map
    private UsuarioVIP buscarUsuario(String correo) throws Exception{    
        if (!misUsuarios.containsKey(correo)) throw new Exception ("no existe el usuario con ese correo");
        return misUsuarios.get(correo);
    }
    
    //
    
    
    // Obtener todos los id (key) de una lista de objetos.
    public Set<String> obtenerUsuariosVIP(){
     return  misUsuarios.keySet();    
    }
     // .... Para todos los demas igual la busqueda de uno concreto o de todas sus claves
   
    // Inicialización del sistema 
    public void incluirActor(String nombre, String nacionalidad, String web){
        misActores.put(nombre, new Actor(nombre,nacionalidad, web));
    }
    public void incluirDirector(String nombre, String nacionalidad, String web){
        misDirectores.put(nombre, new Director(nombre,nacionalidad, web));
    }
    public void incluirFestivalPremio(String nombre){
        misFestivales.put(nombre, new FestivalPremio(nombre));
    }
     public void incluirUsuario(String nombre, String correo){
        misUsuarios.put(correo, new UsuarioVIP(nombre,correo));
    }
     
     
     
}
