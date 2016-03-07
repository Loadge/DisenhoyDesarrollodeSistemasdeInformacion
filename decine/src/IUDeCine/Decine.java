/* 
Con este main tenéis que probar todo el diseño que os hemos dejado.
Si ejecutáis este código veréis que funciona (permite incluir UsuariosVIP)
Una vez incluidos los usuarios VIP, con la opción 9 de muestran su correos, para elegir uno de ellos
*/ 

package IUDeCine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import decine.Genero;
import decine.DeCine;
import java.util.Set;

/**
 *
 * @author ana
 */
public class Decine {

    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) throws IOException {        
      // Obtención de la única instancia de DeCine, DeCine es un singleton. 
        DeCine unaDeCine = DeCine.getDeCine();      
      
    //Definición de la variable in que va a permitir leer String desde teclado.  
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	   
     
    // Ejemplos de lectura desde teclado con in.readLine()
	// String s = in.readLine();        //Lectura de un String
	// double d= Double.parseDouble(in.readLine());         // lectura de un duoble
	// int i = Integer.parseInt(in.readLine());         // lectura de un int               
                // Genero estado = Genero.valueOf(in.readLine());       // lectura de un enum     
       
        int opcion = 0; //variable que controla la opción de menú
          
        do{ //do-while que controlara todo el menú
                 try{ // tratamiento de las excepciones. Bloque try en el que se puede producir una excepción y la capturamos
		 
                                //Terminar de diseñar el menú (usando System.out.println(...)) con las opciones que faltan
		 // Podéis hacer vuestros propios diseños de interfaz, esta es la interfaz mínima que tenéis que entregar
                                System.out.println("******** ALTAS*****************************");
                                System.out.println(// " 1. Dar de alta un película \n" +
                                                             "2. Dar de alta un actor \n"  + 
                                                             "3. Dar de alta un director \n" + 
                                                             "4. Dar de alte un UsuarioVIP \n" +
                                                             "5. Dar de alta un Festival/Premio" );	
                                 
                                System.out.println("\n ******  MODIFICACIONES *****************************");                              
                                // 	6. Incluir un directr en una  película
                                // 	7. Incluir un actor en una película
                                // 	8. Incluir premio de película
                                // 	9. Incluir crítica de película
                                   System.out.println("\n ******  CONSULTA  *****************************");
                                //             10. Consultar película 
		    System.out.println("\n ****** TERMINAR *****************************");          
                                    System.out.println("0. Terminar");
		 
                                // Lectura de un int, para darle valor a opcion.
                                   opcion = Integer.parseInt(in.readLine()); 
		
                                // Estructura switch con todas las opciones de menú. Algunos de ellos ya lo tenéis hecho
                                // Tenéis que terminar las opciones que están incompletas y las que no están hechas
                                switch(opcion){
                                    case 1: /*todo lo que hay que hacer para dar de alta una película
                                                        lectura de los valores de inicialización de la pelicula, son:                                                        
                                    
                                                        * Titulo: ----
                                                        * Titulo Original ---
                                                        * Fecha de Estreno
                                                        * Duracion:----
                                                        * Guion --
                                                        * Musica---
                                                         * Pais:---
                                                         * Web oficial ---
                                                         * Productora --
                                                        * Sinopsis: ----
                                                        * Recaudacion
                                                         * Generos:---,---,---.                              
                                                    Una vez leída, mensaje a unaDeCine para que incluya una nueva película */
                                    break;
                                    case 2:/*Dar de alta un actor */
                                                    System.out.println("Nombre del Actor:");
                                                    String nombreA = in.readLine();
                                                    System.out.println("Nacionalidad del Actor:");
                                                    String nacionalidadA = in.readLine();
                                                    System.out.println("Web oficial del Actor:");
                                                    String webA = in.readLine();
                                                    unaDeCine.incluirActor(nombreA,nacionalidadA,webA);                 
                                    break;
                                    case 3: /*Dar de alta a un Director */                         
                                                    System.out.println("Nombre del Director:");
                                                    String nombreD = in.readLine();
                                                    System.out.println("Nacionalidad del Directorr:");
                                                    String nacionalidadD = in.readLine();
                                                    System.out.println("Web oficial del Director:");
                                                    String webD = in.readLine();
                                                    unaDeCine.incluirDirector(nombreD,nacionalidadD,webD); 
                                     break;
                                     case 4: /* Dar de alta a un usuario VIP  */
                                                    System.out.println("Nombre del Usuario VIP:");
                                                    String nombreU = in.readLine();
                                                    System.out.println("Correo del Usuario VIP:");
                                                    String correo = in.readLine(); 
                                                    unaDeCine.incluirUsuario(nombreU,correo); 
                                       break;             
                                       case 5: /* Dar de alta un Festival  o Premio  */
                                                    System.out.println("Nombre del Festival o Premio:");
                                                    String nombreF = in.readLine();                                                     
                                                    unaDeCine.incluirFestivalPremio(nombreF); 
                                       break;
                                       case 6: /* Incluir  un director en una película
                                                        Obtener a todos los directores disponibles
                                                        Lectura del nombre del director 
                                                        Obtener todas las películas disponibles (título y año de estreno)
                                                        Lectura  del título de la pelicula.
                                                        Lectura del año de estreno
                                                        mensaje a unaDeCine para que incluya a ese director en esa película 
                                                */
                                       break;
                                       case 7: /* Incluir  un actor en una pelicula
                                                        Obtener a todos los actores disponibles
                                                        Lectura del nombre del actor
                                                       Obtener todas las películas disponibles (título y año de estreno)
                                                        Lectura  del título de la pelicula.
                                                        Lectura del año de estreno
                                                        mensaje a unaDeCine para que incluya a ese actor en la pelicula 
                                             */
                                       break;
                                       case 8: /*Incluir un premio a pelicula
                                                        Obtener  los Premios o Festivales  disponibles en el sistem                                                          
                                                        Lectura del nombre del premio o festival
                                                        Obtener todas las películas disponibles (título y año de estreno)
                                                        Lectura  del título de la pelicula.
                                                        Lectura del año de estreno
                                                        mensaje a unaDeCine para que incluya  ese premio a la pelicula 
                                             */
                                       break;
                                       case 9: /* Incluir una critica a pelicula
                                                        Obtener los usuarios VIP disponibles en el sistema*/
                                                        System.out.println("Usurios en el sistema:");
                                                        Set<String> correos = unaDeCine.obtenerUsuariosVIP();
                                                        for(String cor:correos){
                                                            System.out.println("correo de usuario : " + cor);
                                                        }
                                                        //Lectura del correo del usuario
                                                        System.out.println("Elegir un correo de usuario");
                                                        correo = in.readLine();                       
                                                        /* Obtener todas las películas disponibles (título y año de estreno)
                                                        Lectura  del título de la pelicula.
                                                        Lectura del año de estreno
                                                        Lectura del titulo de la critica.
                                                        Lectura del comentario de la critica
                                                        mensaje a unaDeCine para que incluya  esa critica a la pelicula 
                                             */
                                         break;
                                         case 10: /* Consultar una pelicula por distintos criterios:
                                                Lectura del criterio de consulta 
                                                (1 == por titulo, 1 == por Director, 3 == por Actor, 4== genero, 5 == año de estreno)
                                                Lectura del valor de la busqueda
                                                mensaje a unaDeCine para que realice la busqueda
                                                muestra del resultado de la busqueda (esta salida tiene que estar bien elaborada)
                                             */
                                          break;
                                }
            }catch(Exception ex){ // captura de la excepción
                System.err.println(ex);
            } 
        }while(opcion !=0);        
    } // de main(...)
    
}
