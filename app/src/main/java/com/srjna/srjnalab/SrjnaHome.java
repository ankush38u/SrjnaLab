package com.srjna.srjnalab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Iterator;
import java.util.List;


public class SrjnaHome extends ActionBarActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView clickableTv;
    private TextView editTextSearch;
    private AutoCompleteTextView autoCompleteTextView;
    //  private AutoCompleteTextView autoCompleteTextView;
    private FloatingActionButton fab;
    public static final int ERROR_IN_PARSE = 0;
    public static final int SUCCESSFULLY_RETRIVED = 1;
    public static final int PRODUCT_NOT_FOUND = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srjna_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.logo);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        clickableTv = (TextView) findViewById(R.id.clickableTv);
        clickableTv.setOnClickListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_srjna_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_about_dev){
            startActivity(new Intent(this,About.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == clickableTv) {
            String[] argu = new String[2];
            argu[0] = "pid";
            argu[1] = editTextSearch.getText().toString();
            if (editTextSearch.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(SrjnaHome.this, "Please put Product id", Toast.LENGTH_SHORT).show();
            } else (new AsyncCaller()).execute(argu);

     /*      // Test creation of object
            ParseObject testObject = new ParseObject("Product");
            testObject.put("title", "bar");
            testObject.saveInBackground();*/


     /*       // Locate the class table named "Product" in Parse.com
            List<ParseObject> ob=null;
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "Product");
            query.orderByDescending("_created_at");
            try {
                ob = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            Iterator<ParseObject> it= ob.iterator();
            while(it.hasNext()){
                ParseObject po=it.next();
            String title=    po.getString("title");
             String pid=   po.getString("pid");
                Toast.makeText(this,title+" : "+pid,Toast.LENGTH_SHORT).show();

            }
    */


        }
        if (v == fab) {

            boolean wrapInScrollView = true;
            MaterialDialog dialog = new MaterialDialog.Builder(this)  //... initialization via the builder
                    .title(R.string.myDialogTitle)
                    .customView(R.layout.dialog_view, wrapInScrollView)
                    .positiveText(R.string.positive).callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            String[] argu = new String[2];
                            argu[0] = "title";
                            argu[1] = autoCompleteTextView.getText().toString();
                            if (argu[1].equalsIgnoreCase("")) {
                                Toast.makeText(SrjnaHome.this, "Please input", Toast.LENGTH_SHORT).show();
                            } else (new AsyncCaller()).execute(argu);
                        }
                    }).titleColorRes(R.color.divider)
                    .contentColor(Color.WHITE) // notice no 'res' postfix for literal color
                    .dividerColorRes(R.color.divider)
                    .positiveColorRes(R.color.colorAccent)
                    .widgetColorRes(R.color.ColorPrimaryDark)
                    .show();

            View my_dialog = dialog.getCustomView();
            autoCompleteTextView = (AutoCompleteTextView) my_dialog.findViewById(R.id.autoCompleteTv);
            ParseApplication pa = (ParseApplication) getApplicationContext();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, pa.getAll());
            //set adapter to the autocompletetext
            autoCompleteTextView.setAdapter(adapter);


        }
    }


    private class AsyncCaller extends AsyncTask<String, Void, Integer> {
        ProgressDialog pdLoading = new ProgressDialog(SrjnaHome.this);
        Intent dataIntent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.show();
        }

        @Override
        protected Integer doInBackground(String... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");
            //  query.whereEqualTo("pid", "p101");
            query.whereEqualTo(params[0], params[1]); //argumente to async task we will send as key,value like pid =p101
            ParseQuery<ParseObject> p = query;
            List<ParseObject> ob = null;
            Iterator<ParseObject> it = null;
            try {
                ob = query.find();
                it = ob.iterator();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
                return SrjnaHome.ERROR_IN_PARSE;
            }
            // ArrayList<String> all = new ArrayList<String>();

            if (it.hasNext()) {
                ParseObject po = it.next();
                String title = po.getString("title");
                String pid = po.getString("pid");
                Log.d("ankush", pid);
                Log.d("ankush", title);
                // Toast.makeText(SrjnaHome.this,title+" : "+pid,Toast.LENGTH_SHORT).show();


                //set all this data to intent to open new activity
                dataIntent = new Intent(SrjnaHome.this, ProductPage.class);
                Bundle productBundle = new Bundle();
                productBundle.putString("pid", po.getString("pid")); //key,value
                productBundle.putString("title", po.getString("title"));
                productBundle.putString("img_link", po.getString("img_link"));
                productBundle.putString("principle", po.getString("principle"));
                productBundle.putString("applications", po.getString("applications"));
                dataIntent.putExtra("product_data", productBundle);
                return SrjnaHome.SUCCESSFULLY_RETRIVED;
            } else {
                return SrjnaHome.PRODUCT_NOT_FOUND;
            }

        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            pdLoading.dismiss();
            //this method will be running on UI thread
            if (result == SrjnaHome.SUCCESSFULLY_RETRIVED) {
                startActivity(dataIntent);
            } else if (result == SrjnaHome.ERROR_IN_PARSE) {
                Toast.makeText(SrjnaHome.this, "Sorry! Server side Glitch!Please try agian!", Toast.LENGTH_SHORT).show();
            } else if (result == SrjnaHome.PRODUCT_NOT_FOUND) {
                Toast.makeText(SrjnaHome.this, "Product Not Found,Enter correct Info!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
