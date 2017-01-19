package com.manoj.fitnessdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class Session  {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        this.editor = prefs.edit();
    }



    public void setLoggedIn(boolean logggedIn){
        editor.putBoolean("loggedInmode",logggedIn);
        editor.commit();
    }
    public boolean loggedIn(){
        return  prefs.getBoolean("loggedInmode",false);
    }
}
