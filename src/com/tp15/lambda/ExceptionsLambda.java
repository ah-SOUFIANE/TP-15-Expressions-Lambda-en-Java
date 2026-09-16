package com.tp15.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionsLambda {
    public static void main(String[] args) {
        try {
            List<String> fichiers = Files.list(Paths.get("."))
                                        .map(p -> {
                                            try {
                                                return Files.size(p) + " - " + p.getFileName();
                                            } catch (IOException e) {
                                                return "Erreur: " + p.getFileName();
                                            }
                                        })
                                        .collect(Collectors.toList());
            System.out.println("Fichiers: " + fichiers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            List<Long> tailles = Files.list(Paths.get("."))
                                     .map(IOFunction.unchecked(Files::size))
                                     .collect(Collectors.toList());
            System.out.println("Tailles: " + tailles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}