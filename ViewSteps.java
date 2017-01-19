package com.manoj.fitnessdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class ViewSteps extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> re;
    ArrayList<String> fb;
    DBHelper bdh;
    SQLiteDatabase db;
    TextView t;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewsteps);
        lv = (ListView) findViewById(R.id.gridVi1);
        session = new Session(this);
        t = (TextView) findViewById(R.id.textretrieve);
        bdh = new DBHelper(this);
        db = bdh.getWritableDatabase();
        fb = new ArrayList<String>();
        if(!session.loggedIn()){
            mlogout();
        }
        String[] cols = { "numberofsteps" };

        Cursor cur = db.query("Stepscounter", cols, null, null, null, null, null);
        if (cur.moveToFirst()) {
            do {

                fb.add(cur.getString(0));

            } while (cur.moveToNext());
        }
        cur.close();
        if (fb.size() != 0) {
            re = new ArrayAdapter<String>(ViewSteps.this, android.R.layout.simple_list_item_1, fb);
        }
        lv.setAdapter(re);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                StringBuffer result = new StringBuffer();
                result.append(" Your number of Steps ");
                result.append("\n Walked : ").append(fb.get(position).toString());
                t.setText(result);
                t.setTextColor(Color.BLACK);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.logout) {
            mlogout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void mlogout(){
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(ViewSteps.this,Login.class));

    }
}
