package com.example.sharedpreferencesvidu2;

import android.content.Context;
import android.content.SharedPreferences;

public class PreManager {
    Context context;
    PreManager(Context context){
        this.context =  context;
    }

    public void saveLoginDeteils(String email, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogedOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email","").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password","").isEmpty();
        return isEmailEmpty||isPasswordEmpty;
    }
}
