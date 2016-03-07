package Napakalaki;

import java.util.ArrayList;

public class JugadorSectario extends Jugador{
    public static int numeroSectarios=0;
    private Sectario miCartaSectario;
    
    public static int getNumeroSectarios(){
        return numeroSectarios;
    }
    private static void incrementarSectarios(){
        
    }
    public JugadorSectario(Jugador jugador, Sectario carta){
        super(jugador);
        miCartaSectario = carta;
        numeroSectarios++;
    }
    
   
    public int obtenerNivelCombate(){
        
        int nivCombate = this.obtenerNivel();

        if(tienesCollar()){
            for (Tesoro tesoro : super.tesorosVisibles) {
                nivCombate += tesoro.GetBonusMaximo();
            }
        }else{
            for (Tesoro tesoro : super.tesorosVisibles) {
                nivCombate += tesoro.GetBonusMinimo();
            }
        }
        nivCombate += miCartaSectario.getValorEspecial();

        return nivCombate;
    }

 @Override
    public JugadorSectario convertirme(Sectario cartaSectario){
        return this;
        
    }

    public boolean puedoConvertirme() {
        return false;
    }

    public int nivelCombate() {
        return miCartaSectario.getValorEspecial();
                
    }


    protected int calcularNiveles(ArrayList<Tesoro> tesoros) {
        return (super.calcularNiveles(tesoros)*2);
    }

    @Override
    protected int getNivelContrincante(Monstruo monstruo) {
        return monstruo.getValorEspecial();
    }

    
    

    
    
    
    
    
}
