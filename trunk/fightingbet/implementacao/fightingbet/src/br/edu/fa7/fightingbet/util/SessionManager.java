package br.edu.fa7.fightingbet.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import br.edu.fa7.fightingbet.activity.LoginActivity;
import br.edu.fa7.fightingbet.model.Usuario;
 
public class SessionManager {
    
	SharedPreferences pref;
    Editor editor;
    Context ctx;
     
    int PRIVATE_MODE = 0;
     
    private static final String PREF_NAME = "FightingBetLoginPref";
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TOKEN = "token";
     
    public SessionManager(Context context){
        this.ctx = context;
        pref = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }
     
    public void criarSessionLogin(Usuario usuario){
    	editor = pref.edit();
    	editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, usuario.getNome());
        editor.putString(KEY_EMAIL, usuario.getEmail());
        editor.putString(KEY_TOKEN, usuario.getToken());
        editor.commit();
    }   
     
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(ctx, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        }
    }
     
    public Usuario getUsuario(){
        return new Usuario(pref.getString(KEY_NAME, null), pref.getString(KEY_EMAIL, null), pref.getString(KEY_TOKEN, null));
    }
     
    public void logout(){
    	editor = pref.edit();
        editor.clear();
        editor.commit();
         
        Intent i = new Intent(ctx, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ctx.startActivity(i);
    }
     
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}