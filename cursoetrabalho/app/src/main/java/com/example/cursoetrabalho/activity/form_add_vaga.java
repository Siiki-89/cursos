package com.example.cursoetrabalho.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cursoetrabalho.DAO.VagaDAO;
import com.example.cursoetrabalho.DTO.VagaDTO;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.consultor.MascaraData;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class form_add_vaga extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    VagaDTO vagaDTODados;
    VagaDAO vagaDAO;
    Spinner spinnerCategoriaVaga;
    TextInputEditText nomeEmpresa, vagaCargo, vagaDataInicial, vagaDataFinal, vagaQtd;
    EditText vagaDesc;
    String categoria;
    Button btnInsrir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_vaga);

        VagaDTO vagaDTO = new VagaDTO();

        Spinner spinCat = findViewById(R.id.spinnerCategoriaVaga);
        spinCat.setOnItemSelectedListener(this);

        ArrayAdapter adCategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vagaDTO.categoriasEmprego);

        adCategoria.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spinCat.setAdapter(adCategoria);

        btnInsrir = findViewById(R.id.btnFinalizar);

        spinnerCategoriaVaga = findViewById(R.id.spinnerCategoriaVaga);
        nomeEmpresa = findViewById(R.id.txtNomeEmpresaCampo);
        vagaQtd = findViewById(R.id.txtQtdVagaCampo);
        vagaCargo = findViewById(R.id.txtCargoCampo);
        vagaDataInicial = findViewById(R.id.txtDataInicialCampo);
        vagaDataFinal = findViewById(R.id.txtDataFinalCampo);
        vagaDesc = findViewById(R.id.txtDescVaga);

        new MascaraData(vagaDataInicial);
        new MascaraData(vagaDataFinal);
        btnInsrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoria = spinnerCategoriaVaga.getSelectedItem().toString();

                vagaDAO = new VagaDAO(getApplicationContext());
                vagaDTODados = new VagaDTO(categoria.toString().trim(), nomeEmpresa.getText().toString().trim(), vagaQtd.getText().toString().trim(),
                        vagaCargo.getText().toString().trim(), vagaDataInicial.getText().toString().trim(), vagaDataFinal.getText().toString().trim(),
                        vagaDesc.getText().toString().trim());
                vagaDAO.inserirDado(vagaDTODados);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}