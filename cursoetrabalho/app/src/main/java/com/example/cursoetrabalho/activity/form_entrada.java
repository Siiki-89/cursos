package com.example.cursoetrabalho.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cursoetrabalho.R;

public class form_entrada extends AppCompatActivity {


    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_entrada);

        viewFlipper = findViewById(R.id.view_flipper);

        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();

    }
    public void abrirEntrar(View view){
        Intent intent = new Intent(getApplicationContext(), form_entrar.class);
        startActivity(intent);
        finish();
    }
    public void abrirPrincipal(View view){
        Intent intent = new Intent(getApplicationContext(), form_principal.class);
        startActivity(intent);
        finish();
    }

}