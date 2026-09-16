package com.tp15.lambda;

public class PersonneTri {
    private String nom;
    private String prenom;
    private int age;

    public PersonneTri(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + age + " ans)";
    }
}