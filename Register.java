package com.manoj.fitnessdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class Register extends AppCompatActivity {

    DBHelper bdh;
    SQLiteDatabase db;
    EditText name, pwd, rpwd, phno, mail;
    Button register, clear,backtologin;
    String usernam, email, phone, passwor, cpwd1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        register = (Button) findViewById(R.id.btnSIGN);
        name = (EditText) findViewById(R.id.susername);
        pwd = (EditText) findViewById(R.id.spwd);
        rpwd = (EditText) findViewById(R.id.scpwd);
        phno = (EditText) findViewById(R.id.sphone);
        mail = (EditText) findViewById(R.id.sEmail);
        clear = (Button) findViewById(R.id.btnCLEAR);
        backtologin = (Button)findViewById(R.id.btnbacktologin);
        bdh = new DBHelper(this);
        db = bdh.getWritableDatabase();

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                usernam = name.getText().toString().trim();
                email = mail.getText().toString().trim();
                phone = phno.getText().toString().trim();
                passwor = pwd.getText().toString().trim();
                cpwd1 = rpwd.getText().toString().trim();


                if (!passwor.equals(cpwd1)) {
                    rpwd.setError("Password not matching");
                } else if (usernam.equals("")) {
                    name.setError("Enter Valid Name");
                } else if (email.equals("")) {
                    mail.setError("Enter Email Id");
                } else if (phone.equals("")) {
                    phno.setError("Enter Phone Number");
                }

                else if (mail.getText().toString().contains("@") && mail.getText().toString().contains(".")) {
                        try {
                                ContentValues cv = new ContentValues();
                                cv.put("pname", usernam);
                                 cv.put("pmail", email);
                                  cv.put("pphno", phone);
                                      cv.put("ppwd", passwor);
                                          db.insert("User", null, cv);
                                                   System.out.println(cv);
                                                   Intent h = new Intent(Register.this, Login.class);
                                                          startActivity(h);
                                                             finish();
                                                         Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();

                                                            }catch (SQLException sqle){
                                         Toast.makeText(getApplicationContext(), "Error In INSERTING DATA", Toast.LENGTH_LONG).show();
                                                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Error In INSERTING DATA", Toast.LENGTH_LONG).show();
                        }
                } else {
                    mail.setError("Enter Valid Email ID");
                }
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                rpwd.setText("");
                pwd.setText("");
                name.setText("");
                mail.setText("");
                phno.setText("");
            }
        });

        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

    }
}
