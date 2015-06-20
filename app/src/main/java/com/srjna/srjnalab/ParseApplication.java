package com.srjna.srjnalab;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anki on 11-06-2015.
 */
public class ParseApplication extends Application {
    private static final String YOUR_APPLICATION_ID = "s88Iekq71hLsr2mwePiiUIugF6BDGkWE7wykFbdo";
    private static final String YOUR_CLIENT_KEY = "NW1K75YgAKjqVlcZumJJN5BCGex73VQFxnQG5uLS";
    private ArrayList<String> all;
    private HashMap<String,String> hm; //hm of key=pid and value = title;

    public ArrayList<String> getAll() {
        return all;
    }

    public HashMap<String, String> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, String> hm) {
        this.hm = hm;
    }

    public void setAll(ArrayList<String> all) {
        this.all = all;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        all = new ArrayList<String>();

        // Add your initialization code here
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }
}