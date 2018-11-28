package com.example.djairb.chatfirebaseproject;

import android.content.Context;
import android.content.SharedPreferences;

public class Auxiliar {
    private static final String PREFS_NAME = "MyApp_Settings";
    private static final int MODE = 0;
    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";
    private SharedPreferences settings;


    public Auxiliar(Context context){
        settings = context.getSharedPreferences(PREFS_NAME, MODE);


    }

    public void saveLoginCurrentUser(String cpf){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CHAVE_IDENTIFICADOR, cpf);
        editor.commit();

    }

    public String retornarCpfUsuarioLogado(){
        return settings.getString(CHAVE_IDENTIFICADOR, null);
    }

}
