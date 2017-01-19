package com.manoj.fitnessdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity{

    //Dec
    EditText username, password;
    DBHelper bdh;
    SQLiteDatabase db;
    Button login,register, twitter;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        session = new Session(this);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnlRegister);
        twitter = (Button) findViewById(R.id.btntwitterlogin);

        username = (EditText) findViewById(R.id.luname);
        password = (EditText) findViewById(R.id.lpwd);

        bdh = new DBHelper(this);
        db = bdh.getWritableDatabase();

        if(session.loggedIn()){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] cols = { "pname", "ppwd" };
                System.out.println(cols);
                if (!TextUtils.isEmpty(username.getText().toString())
                        && !TextUtils.isEmpty(password.getText().toString())) {

                    String[] selected = { username.getText().toString(), password.getText().toString() };

                    System.out.println("USEREDIT" +username.getText().toString()+ "PASSEDIT" +password.getText().toString());

                    Cursor cur = db.query("User", cols, "pname=? and ppwd=?", selected, null, null, null);


                    if (cur.moveToFirst()) {
                        do {
                            session.setLoggedIn(true);
                            Intent i = new Intent(Login.this, MainActivity.class);
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();

                        } while (cur.moveToNext());
                    } else {
                        Toast.makeText(Login.this, "your are not a valid user", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Field should not be empty", Toast.LENGTH_LONG).show();

                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                finish();
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

}
