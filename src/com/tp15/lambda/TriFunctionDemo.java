package com.tp15.lambda;

public class TriFunctionDemo {
    public static void main(String[] args) {
        TriFunction<Double, Double, Double, Double> volume = (longueur, largeur, hauteur) -> 
            longueur * largeur * hauteur;
        
        TriFunction<Double, Double, Double, String> volumeEnLitres = 
            volume.andThen(v -> v * 1000).andThen(v -> v + " litres");
        
        double l = 2.0, w = 3.0, h = 4.0;
        System.out.println("Volume: " + volume.apply(l, w, h) + " m³");
        System.out.println("Volume en litres: " + volumeEnLitres.apply(l, w, h));
    }
}