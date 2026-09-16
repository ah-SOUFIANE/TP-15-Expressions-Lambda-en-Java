package com.tp15.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAvance {
    public static void main(String[] args) {
        List<Personne> personnes = Arrays.asList(
            new Personne("Jean", 25, "Paris"),
            new Personne("Marie", 30, "Lyon"),
            new Personne("Pierre", 20, "Paris"),
            new Personne("Sophie", 35, "Lyon"),
            new Personne("Paul", 40, "Marseille")
        );
        
        Map<String, List<Personne>> parVille = personnes.stream()
                                                      .collect(Collectors.groupingBy(Personne::getVille));
        System.out.println("Personnes par ville: " + parVille);
        
        double ageMoyen = personnes.stream()
                                  .mapToInt(Personne::getAge)
                                  .average()
                                  .orElse(0);
        System.out.println("Âge moyen: " + ageMoyen);
        
        Personne plusAgee = personnes.stream()
                                    .max((p1, p2) -> p1.getAge() - p2.getAge())
                                    .orElse(null);
        System.out.println("Personne la plus âgée: " + plusAgee);
        
        List<String> parisiens = personnes.stream()
                                         .filter(p -> p.getVille().equals("Paris"))
                                         .map(Personne::getNom)
                                         .map(String::toUpperCase)
                                         .collect(Collectors.toList());
        System.out.println("Parisiens: " + parisiens);
    }
}