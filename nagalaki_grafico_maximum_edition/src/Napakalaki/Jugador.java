package Napakalaki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class Jugador {
    
    private String nombre;
    private int nivel = 1;
    public static final int NIVEL_MINIMO = 1;
    public static final int NIVEL_MAXIMO = 10;
    public static final int TESOROS_OCULTOS_MAXIMO = 4;
    public static final int TESOROS_VISIBLES_MAXIMO = 6;
    protected LinkedList<Tesoro> tesorosVisibles = new LinkedList();
    protected LinkedList<Tesoro> tesorosOcultos = new LinkedList();
    protected ArrayList<TipoTesoro> malRolloVisible = new ArrayList();
    protected ArrayList<TipoTesoro> malRolloOculto = new ArrayList();
    protected int numeroVisibles=0;
    protected int numeroOcultos=0;
    int NivelCombate;
    
    public Jugador(String nombre){
        this.nombre = nombre;
    }
    public Jugador(Jugador jugador){
       nombre = jugador.nombre;
   }
    public int obtenerNivel(){
        return nivel;}
    public void modificarNivel(int incDec){
        this.setNivel(incDec);
    }
    public void robarTesoro(Tesoro unTesoro){
        tesorosOcultos.add(unTesoro);
        
    }
    
    private boolean puedoEquipar(Tesoro tesoro){
        if(tesoro.GetTipo() == TipoTesoro.DOSMANOS || tesoro.GetTipo() == TipoTesoro.MANO){ //Tipo problemático.
            if(tesoro.GetTipo()==TipoTesoro.DOSMANOS){
                //Hay que comprobar si ya tenemos equipado uno o más tesoros de una mano, o de dos manos.
                for(Tesoro T : tesorosVisibles){
                    if(T.GetTipo() == TipoTesoro.MANO || T.GetTipo() == TipoTesoro.DOSMANOS){
                        return false;
                    }                   
                }
                return true;
            }else if(tesoro.GetTipo()== TipoTesoro.MANO ){
                //contador == 1 dice que tenemos una mano equipada
                //contador == 2 dice que, o bien tenemos 2 de una mano, o tenemos 1 de dos manos.
                int contador=0;
                 for(Iterator<Tesoro> itTes = tesorosVisibles.iterator(); itTes.hasNext();){
                    Tesoro actual = itTes.next();
                    if(actual.GetTipo() == TipoTesoro.MANO){
                        contador++;
                    }else if (actual.GetTipo() == TipoTesoro.DOSMANOS){
                        contador = contador + 2;
                    }
                }
                 if(contador<=1){
                     return true;
                 }else{
                     return false;
                 }
            }
        }else{
            for(Tesoro T : tesorosVisibles){
                if(T == tesoro || T.GetTipo() == tesoro.GetTipo()){
                    return false;
                }
            }      
        }
        return true;
    }
    
    
    
    //Copiar los parámetros en listas auxiliares.
    //Recorrer malRolloVisible
    //  Si se encuentra un tipo que está en la lista auxiliar
    //      borrar de la lista auxliar, y de malRolloVisible
    //Recorrer malRolloOculto
    //  Si se encuentra un tipo que está en la lista auxiliar
    //      borrar de la lista auxliar, y de malRolloVisible
    
    //Si numeroVisibles >0,
    //      tachar tantos elementos en la lista auxiliar como numeroVisibles.
    //      decrementar tantos numeroVisibles como eliminados en el paso anterior.  Al final tiene que quedarse a 0.
    //Si numeroOcultos >0,
    //      tachar tantos elementos en la lista auxiliar como numeroOcultos.
    //      decrementar tantos numeroOcultos como eliminados en el paso anterior.   Al final tiene que quedarse a 0.
    private void actualizarMalRollo(List<Tesoro> tesVisibles, List<Tesoro> tesOcultos){
        List<Tesoro> auxVisibles = tesVisibles;
        List<Tesoro> auxOcultos = tesOcultos;
        ArrayList<TipoTesoro> auxMRVisible = malRolloVisible;
        ArrayList<TipoTesoro> auxMROculto = malRolloOculto;
        
        for (TipoTesoro Tv : auxMRVisible){
            for(Tesoro Tip : auxVisibles){
                TipoTesoro tipo_aux = Tip.GetTipo();
                if(Tv == tipo_aux){
                    malRolloVisible.remove(Tv); //Borrar de malRolloVisible.
                    tesorosVisibles.remove(Tip);   //Borrar del aux.
                }
            }
        }
        for (TipoTesoro To : auxMROculto){
            for(Tesoro Tip : auxOcultos){
                TipoTesoro tipo_aux = Tip.GetTipo();
                if(To == tipo_aux){
                    malRolloOculto.remove(To); //Borrar de malRolloOculto.
                    tesorosOcultos.remove(Tip);   //Borrar del aux.
                }
            }
        }
        //----------------------------------------
        boolean fin = false;
        while(numeroVisibles > 0 && !fin){
            if(tesVisibles.size() == 0){
                fin = true;
            }else{
                tesorosVisibles.removeLast();
                numeroVisibles--;
            }
            if(tesorosVisibles.size() == 0 && numeroVisibles >0){ //No podemos descartarnos de más cosas.
                numeroVisibles = 0;
            }
        }
        fin = false;
        while(numeroOcultos > 0  && !fin){
            if(tesOcultos.size() == 0){
                fin = true;
            }else{
                tesorosOcultos.removeLast();
                numeroOcultos--;
            }
            if(tesorosOcultos.size() == 0 && numeroOcultos >0){ //No podemos descartarnos de más cosas.
                numeroOcultos = 0;
            }
        }
        
    }
    public boolean cumploMalRollo(){
        if((numeroOcultos == 0 && numeroVisibles==0)
                            &&
           (malRolloVisible.isEmpty() && malRolloOculto.isEmpty())){
           return true; 
        }
        return false;
        
    }
    public boolean descartarTesoros(List<Tesoro> tesorosOcu, List<Tesoro> tesorosVisibles){
        actualizarMalRollo(tesorosVisibles, tesorosOcu);
        boolean cumploMR = cumploMalRollo();
        
        return cumploMR;
        
        
        
        
    }
    //Función auxiliar que sirve para calcular los niveles que se pueden comprar con el oro de de los tesoros que quieras descartarte.
    protected int calcularNiveles(List<Tesoro> tesoros){
        int niveles=0;
        int acumulador=0;
        for(Tesoro T : tesoros){
            acumulador = acumulador + T.GetPiezasOro();
        }
        niveles = acumulador/1000;
        return niveles;
    }
    
    public int puedoPasar(){
         if((numeroOcultos != 0)){
             return numeroOcultos;
         }else if((malRolloVisible.isEmpty() && malRolloOculto.isEmpty())){
             return -1;
         }else{
             return 0;
         }
    }
    public boolean TengoTesoros() { return false;}
    public boolean comprarNiveles(List<Tesoro> tesoros) {
        boolean puedo;
        int niveles = calcularNiveles(tesoros);
        if(niveles + nivel < 10){
            puedo=true;
        }else{
            puedo=false;
        }
        if(puedo){
            nivel += niveles;
        }
        tesorosOcultos.removeAll(tesoros); //Los tesoros se borran tanto si se compran niveles como si no.
        tesorosVisibles.removeAll(tesoros);
        return puedo;    
    }
    public void incDecNivel(int inDec){
        int aux = this.nivel;
        
        aux += inDec;
        if(aux >= NIVEL_MINIMO && aux <= NIVEL_MAXIMO){
            this.nivel = aux;
        }else if(aux < NIVEL_MINIMO){
            this.nivel = NIVEL_MINIMO;
        }else{
            this.nivel = NIVEL_MAXIMO;
        }
    }
    public int getnivelCombate() {

        int nivCombate = this.obtenerNivel();

        if(tienesCollar()){
            for (Tesoro tesoro : tesorosVisibles) {
                nivCombate += tesoro.GetBonusMaximo();
            }
        }else{
            for (Tesoro tesoro : tesorosVisibles) {
                nivCombate += tesoro.GetBonusMinimo();
            }
        }
        NivelCombate = nivCombate;

        return nivCombate;
    }
    public int combatir(Monstruo monstruoEnJuego) {
        int resultado;
        boolean muerte;
        ArrayList<ArrayList> miMalRollo;
        int malRolloVisibles;
        int malRolloOcultos;
        
        int niveles_perdidos;
        
        //Combatir
        int nivelM=monstruoEnJuego.GetNivel();
        int nivelJ=this.getnivelCombate();
        int nivelesGanados;
        
        if(nivelJ>nivelM){
            nivelesGanados = monstruoEnJuego.GetGananciaNiveles();
            if(nivel + nivelesGanados >= Jugador.getNIVEL_MAXIMO()){
                resultado = 10;
            }else{
                resultado = 1;
            }
            this.incDecNivel(nivelesGanados);
            
        }else{
            Random dado = new Random();
            int dado_final;
            dado_final = dado.nextInt(6)+1; //Número entre 1 y 6.
            if(dado_final <5){
                muerte = monstruoEnJuego.malRolloesMuerte();
                if(muerte){
                    resultado = -2;
                    this.modificarNivel(Jugador.getNIVEL_MINIMO());
                }else{  // !muerte
                    resultado = -1;
                    
                    miMalRollo = monstruoEnJuego.cualEsTuMalRollo();
                    malRolloVisibles = monstruoEnJuego.GetVisiblesPerdidos();
                    malRolloOcultos = monstruoEnJuego.GetOcultosPerdidos();
                    
                    this.incluirMalRollo(miMalRollo);
                    this.incluirMalRolloVisible(malRolloVisibles);
                    this.incluirMalRolloOculto(malRolloOcultos);
                    
                    niveles_perdidos = monstruoEnJuego.GetNivelesPerdidos();
                    this.incDecNivel(niveles_perdidos);
                }
            }else{  //El dado nos salva:
                if(puedoConvertirme()){
                    
                    convertirme(robarSectarios());
                }
                resultado = 0;
            }
        }
        return resultado;
    }
    public Sectario robarSectarios(){
        return Napakalaki.getInstance().getsiguienteSectario();
    }
    public ArrayList<Tesoro> dameTodosTusTesoros() {
        ArrayList<Tesoro> auxFinal = new ArrayList();
        
        //Copia de todos los tesoros del jugador para retornarla más tarde:
        auxFinal.addAll(tesorosVisibles);
        auxFinal.addAll(tesorosOcultos);
        
        //Destrucción de todos los tesoros del jugador:
        tesorosVisibles.clear();
        tesorosOcultos.clear();
        
        return auxFinal;
    }
    public void incluirMalRollo(ArrayList<ArrayList> malRollo){ 
        //malRollo contiene en la primera posición el mal rollo visible.
        //malRollo contiene en la segunda posición el mal rollo oculto.
        this.malRolloVisible.clear();
        this.malRolloOculto.clear();
        ArrayList<TipoTesoro> visibles = new ArrayList();
        visibles = malRollo.get(0);
        ArrayList<TipoTesoro> ocultos = new ArrayList();
        ocultos = malRollo.get(1);
        //if(!malRollo.isEmpty() && malRollo.get(0) !=null) {
            for(TipoTesoro Tipo : visibles){
                this.malRolloVisible.add(Tipo);
            }
        //}
        
        //if(malRollo.size() == 2 && malRollo.get(1)!=null) {
            for(TipoTesoro Tipo : ocultos){
                this.malRolloOculto.add(Tipo);
            }
        //}
            
        }
    public void incluirMalRolloVisible(int visiblesADescartar){
        numeroVisibles = visiblesADescartar;
    }
    public void incluirMalRolloOculto(int ocultosADescartar){
        numeroOcultos = ocultosADescartar;
    }
    public Tesoro devuelveElCollar() { 
        Tesoro aux;
        for(Tesoro T : tesorosVisibles){
            if(T.GetTipo() == TipoTesoro.COLLAR && (T.getNombre()).equals("La fuerza de Mr.T")){
                tesorosVisibles.remove(T);
                return T;
            }
        }
        return null;
    }
    public boolean tienesCollar() {
        for(Tesoro T : tesorosVisibles){
            if(T.GetTipo() == TipoTesoro.COLLAR && (T.getNombre()).equals("La fuerza de Mr.T")){
                return true;
            }
        }
        return false;    
    }
    public void equiparTesoros(List<Tesoro> listaTesoros){      
        boolean puedo;
        for(Tesoro T : listaTesoros){
            puedo = puedoEquipar(T);
            if(puedo){
                tesorosVisibles.add(T);
                tesorosOcultos.remove(T);
            }
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public static int getNIVEL_MAXIMO() {
        return NIVEL_MAXIMO;
    }
    public static int getNIVEL_MINIMO(){
        return NIVEL_MINIMO;
    }
    public boolean puedoConvertirme(){
        return (Napakalaki.dado() == 6);    
    }
    
    public JugadorSectario convertirme(Sectario cartaSectario){
        JugadorSectario sectario = new JugadorSectario(this, cartaSectario);
        int indice = Napakalaki.getInstance().getListaJugadores().indexOf(this);
        
        Napakalaki.getInstance().getListaJugadores().remove(this);
        Napakalaki.getInstance().getListaJugadores().add(indice, sectario);
        
        return sectario;
    }
    
    protected int getNivelContrincante(Monstruo monstruo){
       return monstruo.getValorBasico();
   }

    public LinkedList<Tesoro> getTesorosVisibles() {
        return tesorosVisibles;
    }

    public LinkedList<Tesoro> getTesorosOcultos() {
        return tesorosOcultos;
    }
    
    public String getMRVisible(){
        if(malRolloVisible.size() == 1){
            return ("Un/a "+malRolloVisible.get(0).toString());
        }else if(malRolloVisible.size() == 2){
            return ("Un/a "+malRolloVisible.get(0).toString()+", y un/a "+malRolloVisible.get(1).toString());
        }else{
            return ("No hay mal rollo");
        }
    }
    
    public String getMROculto(){
        if(malRolloOculto.size() == 1){
            return ("Un/a "+malRolloOculto.get(0).toString());
        }else if(malRolloOculto.size() == 2){
            return ("Un/a "+malRolloOculto.get(0).toString()+", y un/a "+malRolloOculto.get(1).toString());
        }else{
            return ("No hay mal rollo");
        }
    }

    public int getNumeroVisibles() {
        return numeroVisibles;
    }

    public int getNumeroOcultos() {
        return numeroOcultos;
    }
    
}
