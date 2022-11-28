package com.cieep.a07_ejerciciologinpersistentegson;

public class Constantes {

    public static final String USUARIOS = "usuarios";
    public static final String CONTACTOS = "contactos";

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DATOS = "datos";


    public static String codifica(String password) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            stringBuilder.append(  (char) (password.charAt(i) + 1) );
        }
        return stringBuilder.toString();
    }

    public static String decodifica(String passwordCifrado) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < passwordCifrado.length(); i++) {
            stringBuilder.append(  (char) (passwordCifrado.charAt(i) - 1) );
        }
        return stringBuilder.toString();
    }

}
