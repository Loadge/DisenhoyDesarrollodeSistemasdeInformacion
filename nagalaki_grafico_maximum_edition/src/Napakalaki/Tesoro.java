package Napakalaki;

public class Tesoro implements Carta{
    private String Nombre;
    private int BonusMinimo;
    private int BonusMaximo;
    private int PiezasOro;
    private TipoTesoro Tipo;
    
    public Tesoro(String unNombre, TipoTesoro unTipo, int unBMin, int unBMax, int unasPiezasOro){
        Nombre = unNombre;
        BonusMinimo = unBMin;
        BonusMaximo = unBMax;
        PiezasOro = unasPiezasOro;
        Tipo = unTipo;
    }
    
    public void SetNombre(String unNombre){
        Nombre = unNombre;
    }
    public void SetBonusMinimo(int unBonusMinimo){
        BonusMinimo = unBonusMinimo;
    }
    public void SetBonusMaximo(int unBonusMaximo){
        BonusMaximo = unBonusMaximo;
    }
    public int GetBonusMinimo(){
        return getValorBasico();
    }
    public int GetBonusMaximo(){
        return getValorEspecial();
    }
    public int GetPiezasOro(){
        return PiezasOro;
    }
    public TipoTesoro GetTipo(){
        return Tipo;
    }

    @Override
    public String getNombre() {
        return Nombre;
    }

    @Override
    public int getValorBasico() {
        return BonusMinimo;
    }

    @Override
    public int getValorEspecial() {
        return BonusMaximo;
    }
};

