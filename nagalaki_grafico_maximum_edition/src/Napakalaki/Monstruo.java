package Napakalaki;

import java.util.ArrayList;

public class Monstruo implements Carta{
    private String Nombre;
    private int Nivel;
    private int GananciaTesoros;
    private int GananciaNiveles;
    private String MalRollo;
    private int NivelesPerdidos;
    private ArrayList <TipoTesoro> TipoVisiblesPerdidos;
    private ArrayList <TipoTesoro> TipoOcultosPerdidos;
    private int OcultosPerdidos;
    private int VisiblesPerdidos;
    private int nivelContraSectario;
    
    public Monstruo(String unNombre, int unNivel, int unaGT, int unaGN, String unMR, int unosNP, ArrayList<TipoTesoro> unTipoVisiblesPerdidos, ArrayList <TipoTesoro> unTipoOcultosPerdidos, int unosVP, int unosOP, int nCS){
        Nombre = unNombre;
        Nivel = unNivel;
        GananciaTesoros = unaGT;
        GananciaNiveles = unaGN;
        MalRollo = unMR;
        NivelesPerdidos = unosNP;
        if(unTipoVisiblesPerdidos != null) {
            TipoVisiblesPerdidos  = new ArrayList(unTipoVisiblesPerdidos);
        }
        else {
            TipoVisiblesPerdidos = new ArrayList<TipoTesoro>();
        }
        
        if(unTipoOcultosPerdidos != null) {
            TipoOcultosPerdidos  = new ArrayList(unTipoOcultosPerdidos);
        }
        else {
            TipoOcultosPerdidos = new ArrayList<TipoTesoro>();
        }
        
        OcultosPerdidos = unosOP;
        VisiblesPerdidos = unosVP;
        nivelContraSectario = nCS;
        
    }
    public boolean malRolloesMuerte(){
        if(NivelesPerdidos==Jugador.getNIVEL_MAXIMO()){
            return true;
        }
        return false;
    }
    public ArrayList<ArrayList> cualEsTuMalRollo(){
        ArrayList<ArrayList> malRollo = new ArrayList();
        
        //if(!TipoVisiblesPerdidos.isEmpty())
        malRollo.add(TipoVisiblesPerdidos); 
        //if(!TipoOcultosPerdidos.isEmpty())
        malRollo.add(TipoOcultosPerdidos);
        
        return malRollo;
    }
    public int obtenerGananciaTesoros(){
        return this.GananciaTesoros;
    }
    
    
    
    public void SetNombre(String unNombre){
       Nombre = unNombre;
    }
    public void SetNivel(int unNivel){
        Nivel = unNivel;
    }
    public void SetGananciaTesoros(int unaGananciaTesoros){
        GananciaTesoros = unaGananciaTesoros;
    }
    public void SetGananciaNiveles(int unaGananciaNiveles){
        GananciaNiveles = unaGananciaNiveles;
    }
    public void SetMalRollo(String unMalRollo){
        MalRollo = unMalRollo;
    }
    public void SetNivelesPerdidos(int unosNivelesPerdidos){
        NivelesPerdidos = unosNivelesPerdidos;
    }
    public void SetOcultosPerdidos(int unosOcultosPerdidos){
        NivelesPerdidos = unosOcultosPerdidos;
    }
    public void SetVisiblesPerdidos(int unosVisiblesPerdidos){
        NivelesPerdidos = unosVisiblesPerdidos;
    }
    public int GetNivel(){
        return getValorBasico();
    }
    public int GetGananciaTesoros(){
        return GananciaTesoros;
    }
    public int GetGananciaNiveles(){
        return GananciaNiveles;
    }
    public String GetMalRollo(){
        return MalRollo;
    }
    public int GetNivelesPerdidos(){
        return NivelesPerdidos;
    }
    public int GetOcultosPerdidos(){
        return OcultosPerdidos;
    }
    public int GetVisiblesPerdidos(){
        return VisiblesPerdidos;
    }

    @Override
    public String getNombre() {
        return Nombre;
    }

    @Override
    public int getValorBasico() {
        return Nivel;
    }

    @Override
    public int getValorEspecial() {
        return nivelContraSectario;
    }
}
