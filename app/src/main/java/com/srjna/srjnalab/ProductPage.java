package com.srjna.srjnalab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
/**
 * Created by anki on 13-06-2015.
 */

public class ProductPage extends ActionBarActivity {
    private Toolbar toolbar;
    private String title;
    private String pid;
    private String img_link;
    private String principle;
    private String applications;
    private ImageView iv;

    private TextView principleTv;
    private TextView applicationTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);




        Intent dataIntent = getIntent();
        Bundle dataBundle = dataIntent.getBundleExtra("product_data");
        title = dataBundle.getString("title");
        pid = dataBundle.getString("pid");
        img_link = dataBundle.getString("img_link");
        principle = dataBundle.getString("principle");
        applications = dataBundle.getString("applications");

        collapsingToolbar.setTitle(title);
        principleTv = (TextView) findViewById(R.id.principleTv);
        applicationTv = (TextView) findViewById(R.id.applicationTv);
        iv = (ImageView) findViewById(R.id.imageV);

      //using Glide library for image loader
        Glide.with(ProductPage.this)
                .load(img_link)
                .into(iv);


        principleTv.setText(principle);
        applicationTv.setText(applications);
    }


}
