package com.example.es2;
//Scrivi una applicazione web Spring Boot che alla endpoint GET v2/ciao/Lombardia?nome=Giuseppe
//risponde con un oggetto JSON formato cosi:
//{
//    "nome": "Giuseppe",
//    "provincia": "Lombardia",
//    "saluto": "Ciao Giuseppe, com'è il tempo in Lombardia?"
//}

public class UserV2 {
    public String nome;
    public String provincia;
    public String saluto;

    public UserV2(String nome, String provincia, String saluto) {
        this.nome = nome;
        this.provincia = provincia;
        this.saluto = saluto;
    }
}
