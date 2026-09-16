package com.tp15.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Validateur<T> {
    private final List<Regle<T>> regles = new ArrayList<>();
    
    public Validateur<T> ajouterRegle(Predicate<T> test, String messageErreur) {
        regles.add(new Regle<>(test, messageErreur));
        return this;
    }
    
    public List<String> valider(T valeur) {
        List<String> erreurs = new ArrayList<>();
        for (Regle<T> regle : regles) {
            if (!regle.test.test(valeur)) {
                erreurs.add(regle.messageErreur);
            }
        }
        return erreurs;
    }
    
    private static class Regle<T> {
        final Predicate<T> test;
        final String messageErreur;
        
        Regle(Predicate<T> test, String messageErreur) {
            this.test = test;
            this.messageErreur = messageErreur;
        }
    }
}