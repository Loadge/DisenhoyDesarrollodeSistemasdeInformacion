/*Integrantes:
 * Miguel Sánchez Tello
 * Samuel Peralta Antequera
 * Jorge Chamorro Padial
 */


package Napakalaki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class Napakalaki {
    
    
    
    private Vista vista;
    private static final Napakalaki instance = new Napakalaki();
    private LinkedList<Monstruo> descarteMonstruos = new LinkedList();
    private LinkedList<Monstruo> mazoMonstruos = new LinkedList();
    private LinkedList<Tesoro> mazoTesoros = new LinkedList();
    private LinkedList<Tesoro> descarteTesoros = new LinkedList();
    private LinkedList<Sectario> mazoSectarios = new LinkedList();
    private LinkedList<Sectario> descarteSectarios = new LinkedList();
    private Jugador jugadorActivo;
    private Monstruo monstruoActivo;
    private ArrayList<Jugador> listaJugadores = new ArrayList();

    private int indexJugador;
    private Tesoro siguienteTesoro;
    private Sectario siguienteSectario;
    
    
    public static int dado(){
        Random rnd = new Random();
        return rnd.nextInt(6) + 1;

    }
    
    private Napakalaki() {}
    
    public static Napakalaki getInstance(){
        return instance;
    }
    
      
    public void comenzarJuego(String[] nombres) throws Exception{
        if (nombres.length <1 ||nombres.length >4){
            throw new Exception("Número de jugadores incorrecto. Debe ser 3 o 4.");
        }
        inicializarJuego();
        inicializarJugadores(nombres);
        
        barajar(0);
        barajar(1);
        repartirCartas();
        siguienteTurno();    
    }
    
    private void inicializarJuego(){
        
       //mazoTesoros.add(new Tesoro(nombre, tipo, bonusMinimo, bonusMaximo, piezas));
        //mazoMonstruos.add(new Monstruo(nombre, nivel, gananciaTeso, gananciaNivel, 
        // malRollo, nivelesPerdidos, tiposVisiblesPerdidos, tipoOcultosPerdidos, 
        //   visiblesPerdidos, ocultosPerdidos));
        
        mazoTesoros.add(new Tesoro("La fuerza de Mr.T", TipoTesoro.COLLAR,0,0,1000));
        mazoTesoros.add(new Tesoro("Botas de lluvia ácida", TipoTesoro.DOSMANOS,1,1,800));
        mazoTesoros.add(new Tesoro("Shogulador",TipoTesoro.DOSMANOS,1,1,600));
        mazoTesoros.add(new Tesoro("A prueba de babas",TipoTesoro.ARMADURA,2,5,400));
        mazoTesoros.add(new Tesoro("Fez alópodo",TipoTesoro.CASCO,3,5,700));
        mazoTesoros.add(new Tesoro("Cuchillo de sushi arcano",TipoTesoro.MANO,2,3,300));
        mazoTesoros.add(new Tesoro("Botas de investigación",TipoTesoro.CALZADO,3,4,600));
        mazoTesoros.add(new Tesoro("Necrotelecom",TipoTesoro.CASCO,2,3,300));
        mazoTesoros.add(new Tesoro("Casco minero",TipoTesoro.CASCO,2,4,400));
        mazoTesoros.add(new Tesoro("Zapato deja-amigos",TipoTesoro.CALZADO,0,1,500));
        mazoTesoros.add(new Tesoro("Necro-payboycón",TipoTesoro.MANO,3,5,300));
        mazoTesoros.add(new Tesoro("Lanzallamas",TipoTesoro.DOSMANOS,4,8,800));
        mazoTesoros.add(new Tesoro("Necro-gnomicón", TipoTesoro.MANO,2,4,200));
        mazoTesoros.add(new Tesoro("Gaita",TipoTesoro.DOSMANOS,4,5,500));
        mazoTesoros.add(new Tesoro("La rebeca metálica",TipoTesoro.ARMADURA,2,3,400));
        mazoTesoros.add(new Tesoro("Hacha prehistórica",TipoTesoro.MANO,2,5,500));
        mazoTesoros.add(new Tesoro("Mazo de los antiguos",TipoTesoro.MANO,3,4,200));
        mazoTesoros.add(new Tesoro("Ametralladora Thompson",TipoTesoro.DOSMANOS,4,8,600));
        mazoTesoros.add(new Tesoro("Insecticida",TipoTesoro.MANO,2,3,300));
        mazoTesoros.add(new Tesoro("Camiseta de la UGR",TipoTesoro.ARMADURA,1,7,100));
        mazoTesoros.add(new Tesoro("Porra preternatural",TipoTesoro.MANO,2,3,200));
        mazoTesoros.add(new Tesoro("¡Sí mi amo!",TipoTesoro.CASCO,4,7,0));
        mazoTesoros.add(new Tesoro("Linterna a 2 manos",TipoTesoro.DOSMANOS,3,6,400));
        mazoTesoros.add(new Tesoro("Clavo de raíl ferroviario",TipoTesoro.MANO,3,6,400));
        mazoTesoros.add(new Tesoro("Garabato místico",TipoTesoro.MANO,2,2,300));
        mazoTesoros.add(new Tesoro("Escopeta de 3 cañones",TipoTesoro.DOSMANOS,4,6,700));
        mazoTesoros.add(new Tesoro("Capucha de Cthulhu",TipoTesoro.CASCO,3,5,500));
        mazoTesoros.add(new Tesoro("Necromicón",TipoTesoro.DOSMANOS,5,7,800));
        mazoTesoros.add(new Tesoro("Necro-comicón",TipoTesoro.MANO,1,1,100));
        mazoTesoros.add(new Tesoro("El aparato del Pr. Tesla",TipoTesoro.ARMADURA,4,8,900));
        mazoTesoros.add(new Tesoro("Varita de atizamiento",TipoTesoro.MANO,3,4,400));
        mazoTesoros.add(new Tesoro("Tentáculo de pega",TipoTesoro.CASCO,0,1,200));
        

        
        mazoMonstruos.add(new Monstruo("El rey de rosa",13,4,2,"Pierdes 5 niveles y 3 tesoros visibles.",5,null,null,3,0,13));
        mazoMonstruos.add(new Monstruo("El gorrón en el umbral",10,3,1,"Pierdes todos tus tesoros visibles.",0,null,null,6,0,10));
        mazoMonstruos.add(new Monstruo("Semillas Cthulhu",4,2,1,"Pierdes 2 niveles y 2 tesoros ocultos.",2,null,null,0,2,4));
        
        ArrayList<TipoTesoro> malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.CALZADO);
        
        mazoMonstruos.add(new Monstruo("El sopor de Dunwich",2,1,1,"El primordial bostezo contagioso. Pierdes el calzado visible.",0,malrolloVisible,null,1,0,2));
        mazoMonstruos.add(new Monstruo("La que redacta en las tinieblas",2,1,1,"Toses los pulmones y pierdes 2 niveles.",2,null,null,0,0,2));

        malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.ARMADURA);
        ArrayList<TipoTesoro> malrolloOculto = new ArrayList<TipoTesoro>();
        malrolloOculto.add(TipoTesoro.ARMADURA);
        mazoMonstruos.add(new Monstruo("Bichgooth",2,1,1,"Sientes bichos bajo la ropa. Descarta la armadura visible.",0,malrolloVisible,null,1,0,2));
        mazoMonstruos.add(new Monstruo("Byakhees de bonanza",8,2,1,"Pierdes tu armadura visible y otra oculta.",0,malrolloVisible,malrolloOculto,0,0,8));
        mazoMonstruos.add(new Monstruo("H.P. Munchcraft",6,2,1,"Pierdes la armadura visible",0,malrolloVisible,null,1,0,6));
        mazoMonstruos.add(new Monstruo("Los hondos",8,2,1,"Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto.",Jugador.getNIVEL_MAXIMO(),null,null,6,4,8));
        mazoMonstruos.add(new Monstruo("Ángeles de la noche ibicenca",14,4,1,"Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y mano oculta.",0,null,null,1,1,14));
          
        malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.CASCO);
     
        mazoMonstruos.add(new Monstruo("El espía",5,1,1,"Te asusta en la noche. Pierdes un casco visible.",0,malrolloVisible,null,1,0,5));        
        mazoMonstruos.add(new Monstruo("Chibithulhu",2,1,1,"Embobados con el lindo primigenio te descartas de tu casco visible.",0,malrolloVisible,null,1,0,2));

        malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.DOSMANOS);       
        
        mazoMonstruos.add(new Monstruo("Roboggoth",8,2,1,"La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible.",2,malrolloVisible,null,1,0,8));
        mazoMonstruos.add(new Monstruo("Yskhtihyssg-Goth",12,3,1,"No le hace gracia que pronuncien mal su nombre. Estás muerto.",Jugador.getNIVEL_MAXIMO(),null,null,6,4,12));
        mazoMonstruos.add(new Monstruo("Pollipólipo volante",3,1,1,"Da mucho asquito. Pierdes 3 niveles.",3,null,null,0,0,3));
        mazoMonstruos.add(new Monstruo("La Familia Feliz",1,4,1,"La familia te atrapa. Estás muerto.",Jugador.getNIVEL_MAXIMO(),null,null,6,4,1));
        malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.MANO); 
        
        mazoMonstruos.add(new Monstruo("Dameargo",2,1,1,"Te intentas escaquear. Pierdes una mano visible.",0,malrolloVisible,null,1,0,2));
        mazoMonstruos.add(new Monstruo("El Lenguas",20,1,1,"Menudo susto te llevas. Pierdes 2 niveles y 5 visibles.",2,null,null,5,0,0));
        //PREGUNTAR!!!!____!!!!!!!??????mazoMonstruos.add(new Monstruo("Bicéfalo",20,1,1,"Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.",3,TipoTesoro.MANO,null,2,0,0));
        
        malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.MANO);
        mazoMonstruos.add(new Monstruo("El mal indecible impronunciable",10,3,1,"Pierdes 1 mano visible",0,malrolloVisible,null,0,1,8));
        mazoMonstruos.add(new Monstruo("Testigos Oculares",6,2,1,"Pierdes tus tesoros visibles. Jajaja.",0,null,null,6,4,8));
        mazoMonstruos.add(new Monstruo("El gran cthulhu",20,2,5,"Hoy no es tu día de suerte. Mueres.",Jugador.getNIVEL_MAXIMO(),null,null,6,4,24));
        mazoMonstruos.add(new Monstruo("Serpiente Político",8,2,1,"Tu gobierno te recorta 2 niveles",2,null,null,0,0,6));
        
        malrolloVisible = new ArrayList<TipoTesoro>();
        malrolloVisible.add(TipoTesoro.CASCO);
        malrolloVisible.add(TipoTesoro.ARMADURA);
        mazoMonstruos.add(new Monstruo("Felpuggoth",2,1,1,"Pierdes tu casco y tu armadura visible. Pierdes 3 tesoros ocultos.",0,malrolloVisible,null,0,1,7));
        mazoMonstruos.add(new Monstruo("Shoggoth",16,4,2,"Pierdes 2 niveles.",2,null,null,0,0,12));
        mazoMonstruos.add(new Monstruo("Lolitagooth",2,1,1,"Pintalabios negro. Pierdes 2 niveles.",2,null,null,0,0,5));
        
        //Nombres diferentes, ya que en el guión aparecen todos con el mismo nombre.
        mazoSectarios.add(new Sectario("Vampiro", 1));
        mazoSectarios.add(new Sectario("Ladrón", 2));
        mazoSectarios.add(new Sectario("Mafioso", 1));
        mazoSectarios.add(new Sectario("Fakir", 2));
        mazoSectarios.add(new Sectario("Hechicera", 1));
        mazoSectarios.add(new Sectario("Abogado", 1));
        
    }
    private void inicializarJugadores(String[] nombres){
        if (listaJugadores.size() > 0){
            listaJugadores.clear();
        }
        for(int i=0; i < nombres.length ; i++){
            Jugador jugador = new Jugador(nombres[i]);
            listaJugadores.add(jugador);
        }
    }
    
    private void barajar(int tipo) {
        if (tipo == 0) {
            Collections.shuffle(mazoMonstruos);
        } else if (tipo == 1) {
            Collections.shuffle(mazoTesoros);
        }
    }
    
    private void repartirCartas(){
        for(Jugador J : listaJugadores){
            Random dado = new Random();
            int dado_final;
            dado_final = dado.nextInt(6)+1; //Número entre 1 y 6.
            int numTesoros;
            if(dado_final == 1){
                numTesoros=1;
            }else if(dado_final <= 5){
                numTesoros=2;
            }else{
                numTesoros=3;
            }
            for(int i=0; i < numTesoros ; i++){
                J.robarTesoro(mazoTesoros.pollLast());
            }
        }
    }
    
    public int desarrollarCombate() {
        int resultado=jugadorActivo.combatir(monstruoActivo);
        
        //Tras combatir:
        int tesoros_ganados;
        if(resultado == 1){
            tesoros_ganados = monstruoActivo.obtenerGananciaTesoros();
            for(int i=0; i < tesoros_ganados ; i++){
               jugadorActivo.robarTesoro(mazoTesoros.pollLast());
            }
            if (jugadorActivo.tienesCollar()){
                Tesoro collar = jugadorActivo.devuelveElCollar();
                descarteTesoros.add(collar);
            }
        }else if (resultado == -2){
            ArrayList<Tesoro> tesoros = jugadorActivo.dameTodosTusTesoros();
            descarteTesoros.addAll(tesoros);
        }
        return resultado;
    }
    //FALTAN CASOS.
    public int siguienteTurno() {
        int numTesoros;
        
        int fin=0;
        if(jugadorActivo == null){
            jugadorActivo = primerJugador();
            monstruoActivo = siguienteMonstruo();       
            
        }else{
            jugadorActivo = siguienteJugador();
        }
        
        fin = jugadorActivo.puedoPasar();
        
        if(fin == 0){
            siguienteJugador();
            boolean tieneTesoros = jugadorActivo.TengoTesoros();
            if(!tieneTesoros){  //Roba.
                Random dado = new Random();
                int dado_final;
                dado_final = dado.nextInt(6)+1; //Número entre 1 y 6.
                if(dado_final == 1){
                    numTesoros=1;
                }else if(dado_final <= 5){
                    numTesoros=2;
                }else{
                    numTesoros=3;
                }
                
                while(numTesoros > 0){
                    jugadorActivo.robarTesoro(mazoTesoros.pollLast());
                    numTesoros--;
                }

            }
        }
        monstruoActivo = siguienteMonstruo();
        return fin;
    }
    
    private Jugador primerJugador(){ //Solo se llama al iniciar el juego.
        Random rand = new Random();
        int elegido;
        
        elegido = (rand.nextInt(listaJugadores.size()));  //Número entre 0 y tamaño.
        indexJugador=elegido;
        return listaJugadores.get(indexJugador);
    }
    
    private Monstruo primerMonstruo(){  //Se llama para elegir el siguiente monstruo de la baraja. (Propósito diferente al método "primerJugador()")
        Monstruo m = null;
        if (mazoMonstruos.isEmpty()) {
            mazoMonstruos = descarteMonstruos;
            descarteMonstruos.clear();
        }
        m = mazoMonstruos.pollLast();
        return m;
    }
    
//    private Tesoro primerTesoro() {return null;}
    
    private Monstruo siguienteMonstruo(){
        return primerMonstruo();
    }
    
    private Jugador siguienteJugador(){
        indexJugador = (indexJugador+1)%listaJugadores.size();
        return listaJugadores.get(indexJugador);
    }
    
    //"puedo" debería utilizarse para descartar o no tesoros.
    public boolean comprarNivelesJugador(List<Tesoro> tesoros){
        boolean puedo= jugadorActivo.comprarNiveles(tesoros);
        
        descarteTesoros.removeAll(tesoros);
        
        return puedo;
    
    
    
    
    
    
    }
    
    public boolean descartarTesoros(List<Tesoro> tesorosVisDes,
                                    List<Tesoro> tesorosOcuDes ){
        boolean cumploMR = jugadorActivo.descartarTesoros(tesorosVisDes, tesorosOcuDes);
        if(!tesorosVisDes.isEmpty()){
            descarteTesoros.addAll(tesorosVisDes);
        }
        if(!tesorosOcuDes.isEmpty()){
            descarteTesoros.addAll(tesorosOcuDes);
        }
        
        return cumploMR;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public Jugador getJugadorActivo() {
        return jugadorActivo;
    }
    public Monstruo getMonstruoActivo(){
        return monstruoActivo;
    }
    public Tesoro getsiguienteTesoro(){
        Tesoro t = null;
        if (mazoTesoros.isEmpty()) {
            mazoTesoros = descarteTesoros;
            descarteTesoros.clear();
        }
        t = mazoTesoros.pollLast();
        return t;
    }
    public Sectario getsiguienteSectario(){
        Sectario s = null;
        if (mazoSectarios.isEmpty()) {
            mazoSectarios = descarteSectarios;
            descarteSectarios.clear();
        }
        s = mazoSectarios.pollLast();
        return s;
    }
    
    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }
}
