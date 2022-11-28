package com.cieep.a07_ejerciciologinpersistentegson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cieep.a07_ejerciciologinpersistentegson.modelos.ContactosMatricula;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private Button btnLogOut;
    private SharedPreferences sp;
    private SharedPreferences spContactos;

    private TextView lblEmail, lblPassword;
    private Button btnCargar;
    private Button btnGuardar;
    private TextView lblCantidad;

    private List<ContactosMatricula> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        spContactos = getSharedPreferences(Constantes.CONTACTOS, MODE_PRIVATE);
        sp = getSharedPreferences(Constantes.USUARIOS, MODE_PRIVATE);
        contactos = new ArrayList<>();
        if (!spContactos.contains(Constantes.DATOS)) {
            creaContactoMatricula();
        }

        btnLogOut = findViewById(R.id.btnLogOut);
        lblEmail = findViewById(R.id.lblEmailUser);
        lblPassword = findViewById(R.id.lblPasswordUser);
        btnCargar = findViewById(R.id.btnCargarDatos);
        btnGuardar = findViewById(R.id.btnGuardarDatos);
        lblCantidad = findViewById(R.id.lblCantidad);
        lblCantidad.setText("tenemos "+contactos.size()+" contactos");



        lblEmail.setText(sp.getString(Constantes.EMAIL, ""));
        lblPassword.setText(Constantes.decodifica(sp.getString(Constantes.PASSWORD, "")));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datosJSon = new Gson().toJson(contactos);
                Log.d("JSON", datosJSon);
                SharedPreferences.Editor editor = spContactos.edit();
                editor.putString(Constantes.DATOS, datosJSon);
                editor.apply();
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type tipo = new TypeToken< ArrayList<ContactosMatricula> >(){}.getType();
                ArrayList<ContactosMatricula> temp = new Gson().fromJson(spContactos.getString(Constantes.DATOS, "[]"), tipo);
                contactos.clear();
                contactos.addAll(temp);
                lblCantidad.setText("tenemos "+contactos.size()+" contactos");
            }
        });
    }

    private void creaContactoMatricula() {
        for (int i = 0; i < 10; i++) {
            contactos.add(new ContactosMatricula("Nombre "+i, "Ciclo "+i, "email "+ i, "telefono "+i));
        }
    }
}