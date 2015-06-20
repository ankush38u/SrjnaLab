package com.srjna.srjnalab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class SplashScreen extends ActionBarActivity {
    public static final int ERROR_IN_PARSE = 0;
    public static final int SUCCESSFULLY_RETRIVED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        (new AsyncRunner()).execute();
    }

    private class AsyncRunner extends AsyncTask<Void, Void, Integer> {
       // ProgressDialog pdLoading = new ProgressDialog(SplashScreen.this);

        @Override
        protected void onPreExecute() {
           // pdLoading.setMessage("\tLoading ... ");
          //  pdLoading.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
            ParseQuery<ParseObject> p = query;
            List<ParseObject> ob = null;
            Iterator<ParseObject> it = null;
            try {
                ob = query.find();
                it = ob.iterator();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
                return SplashScreen.ERROR_IN_PARSE;

            }
            ArrayList<String> all = new ArrayList<String>();
            HashMap<String, String> hm = new HashMap<String, String>();
            all.clear();

            while (it.hasNext()) {
                ParseObject po = it.next();
                String title = po.getString("title");
                String pid = po.getString("pid");
                Log.d("ankush", title);
                hm.put(pid, title);
            }
            ParseApplication pa = (ParseApplication) getApplicationContext(); //my applicationContext class
            pa.setHm(hm);  //now i have a global hashmap which have title and id
            Collection<String> myCollection = (Collection<String>) hm.values();
            ArrayList<String> arrayList = new ArrayList<String>();
            Iterator<String> itr = myCollection.iterator();
            while (itr.hasNext()) {
                String st = (String) itr.next();
                arrayList.add(st);
            }
            pa.setAll(arrayList); // this all arraylist is for adapter for autocomplete text box
            return SplashScreen.SUCCESSFULLY_RETRIVED;
        }

        @Override
        protected void onPostExecute(Integer status) {

          //    pdLoading.dismiss();
            if (status == SplashScreen.SUCCESSFULLY_RETRIVED) {
                startActivity(new Intent(SplashScreen.this, SrjnaHome.class));
                finish();
            } else {
                Toast.makeText(SplashScreen.this, "Internet Problem!", Toast.LENGTH_SHORT).show();
                finish();
                //else net problem
            }
        }
    }

}
