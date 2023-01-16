package com.example.myapplication;

public class Model
{
    private String id;
    private String adresse;
    private String ville;
    private String prix_valeur;
    private String prix_nom;
    private String id_model;

    public Model(String id, String adresse, String ville, String prix_valeur, String prix_nom,String id_model) {
        this.id = id;
        this.id_model = id_model;
        this.adresse = adresse;
        this.ville = ville;
        this.prix_valeur = prix_valeur;
        this.prix_nom = prix_nom;
    }


    public Model(){

    }

    public String getId_model() {
        return id_model;
    }

    public void setId_model(String id_model) {
        this.id_model = id_model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPrix_valeur() {
        return prix_valeur;
    }

    public void setPrix_valeur(String prix_valeur) {
        this.prix_valeur = prix_valeur;
    }

    public String getPrix_nom() {
        return prix_nom;
    }

    public void setPrix_nom(String prix_nom) {
        this.prix_nom = prix_nom;
    }
}
