package com.srjna.srjnalab;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class About extends ActionBarActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private RoundedImageView rv;
    ImageView myfbIv;
    ImageView srjnafbIv;
    ImageView srjnaytIv;
    TextView app_dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = (RoundedImageView) findViewById(R.id.roundedImageView);
        rv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.developer));

        app_dev = (TextView) findViewById(R.id.app_dev);
        app_dev.setText(Html.fromHtml("<font color=black>App Developer</font><br><font color=#b2b2b2>+91-9660721831</font>"));
        app_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + "09660721831";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
        myfbIv = (ImageView) findViewById(R.id.myfbIv);
        srjnafbIv = (ImageView) findViewById(R.id.srjnafbIv);
        srjnaytIv = (ImageView) findViewById(R.id.srjnaytIv);
        myfbIv.setOnClickListener(this);
        srjnafbIv.setOnClickListener(this);
        srjnaytIv.setOnClickListener(this);


    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_about, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a pasrjnat activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public void onClick(View v) {
        if (v == myfbIv) {
            String url = "http://m.facebook.com/ankush38u";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (v == srjnafbIv) {
            String url = "http://m.facebook.com/srjnagroup";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (v == srjnaytIv) {
            String url = "http://m.youtube.com/channel/UCJccc_N6nxOtOd_2r2OCRRg";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }
}