package Napakalaki;

import java.util.ArrayList;

public class Sectario implements Carta{
    private String nombre;
    private int gananciaNivel;
    
    public Sectario(String unNombre, int unaGN) {
        nombre = unNombre;
        gananciaNivel = unaGN;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getValorBasico() {
        return gananciaNivel;
    }

    @Override
    public int getValorEspecial() {
        return getValorBasico() * JugadorSectario.getNumeroSectarios();
    }
    
    
}
