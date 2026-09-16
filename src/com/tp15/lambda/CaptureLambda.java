package com.tp15.lambda;

import java.util.function.IntUnaryOperator;

public class CaptureLambda {
    private int valeur = 100;

    public static void main(String[] args) {
        int facteur = 10;
        IntUnaryOperator multiplicateur = n -> n * facteur;
        
        System.out.println("5 * " + facteur + " = " + multiplicateur.applyAsInt(5));
        
        new CaptureLambda().demoThis();
    }
    
    private void demoThis() {
        Runnable r = () -> {
            System.out.println("Valeur capturée: " + this.valeur);
        };
        r.run();
    }
}