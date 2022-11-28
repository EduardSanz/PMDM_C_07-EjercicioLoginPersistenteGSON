package com.cieep.a07_ejerciciologinpersistentegson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;

    private SharedPreferences sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVistas();

        sp = getSharedPreferences(Constantes.USUARIOS, MODE_PRIVATE);

        if (sp.contains(Constantes.EMAIL) && sp.contains(Constantes.PASSWORD)) {
            startActivity(new Intent(MainActivity.this, UserActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtEmail.getText().toString().equalsIgnoreCase("correo@server.com")
                && txtPassword.getText().toString().equals("123456")) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Constantes.EMAIL, txtEmail.getText().toString());
                    editor.putString(Constantes.PASSWORD, Constantes.codifica(txtPassword.getText().toString()));
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, UserActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializaVistas() {
        txtEmail = findViewById(R.id.txtEmailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnLogin = findViewById(R.id.btnLogin);
    }
}