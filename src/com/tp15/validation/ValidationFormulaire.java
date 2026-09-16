package com.tp15.validation;

import java.util.List;
import java.util.regex.Pattern;

public class ValidationFormulaire {
    public static void main(String[] args) {
        Validateur<String> validateurEmail = new Validateur<String>()
            .ajouterRegle(s -> s != null && !s.isEmpty(), "L'email ne peut pas être vide")
            .ajouterRegle(s -> s.contains("@"), "L'email doit contenir @")
            .ajouterRegle(s -> Pattern.matches(".+@.+\\..+", s), "Format d'email invalide");
        
        List<String> emails = List.of("user@example.com", "", "invalide", "user@domaine");
        
        for (String email : emails) {
            List<String> erreurs = validateurEmail.valider(email);
            if (erreurs.isEmpty()) {
                System.out.println("Email valide: " + email);
            } else {
                System.out.println("Email invalide: " + email);
                erreurs.forEach(err -> System.out.println("  - " + err));
            }
        }
    }
}