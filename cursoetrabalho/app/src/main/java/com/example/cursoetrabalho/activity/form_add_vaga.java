package com.example.cursoetrabalho.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class form_add_vaga extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    VagaDTO vagaDTODados;
    private Bitmap bitmap;
    private static final int PICK_IMAGE_REQUEST = 1;
    VagaDAO vagaDAO;
    ImageView vagaImg;
    Spinner spinnerCategoriaVaga;
    TextInputEditText nomeEmpresa, vagaCargo, vagaDataInicial, vagaDataFinal, vagaQtd, vagaUrl;
    EditText vagaDesc;
    String categoria;
    Button btnInsrir,btnVagaImg;
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
        btnVagaImg = findViewById(R.id.btnVagaImg);

        spinnerCategoriaVaga = findViewById(R.id.spinnerCategoriaVaga);
        nomeEmpresa = findViewById(R.id.txtNomeEmpresaCampo);
        vagaQtd = findViewById(R.id.txtQtdVagaCampo);
        vagaCargo = findViewById(R.id.txtCargoCampo);
        vagaDataInicial = findViewById(R.id.txtDataInicialCampo);
        vagaDataFinal = findViewById(R.id.txtDataFinalCampo);
        vagaDesc = findViewById(R.id.txtDescVaga);
        vagaImg = findViewById(R.id.vagaImg);
        vagaUrl = findViewById(R.id.txtUrlVagaCampo);

        new MascaraData(vagaDataInicial);
        new MascaraData(vagaDataFinal);
        btnInsrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoria = spinnerCategoriaVaga.getSelectedItem().toString();

                String img = imageToString(bitmap);

                vagaDAO = new VagaDAO(getApplicationContext());
                vagaDTODados = new VagaDTO(categoria.toString().trim(), nomeEmpresa.getText().toString().trim(), vagaQtd.getText().toString().trim(),
                        vagaCargo.getText().toString().trim(), vagaDataInicial.getText().toString().trim(), vagaDataFinal.getText().toString().trim(),
                        vagaDesc.getText().toString().trim(), img.trim(), vagaUrl.getText().toString().trim());
                vagaDAO.inserirDado(vagaDTODados);

            }
        });
        btnVagaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String encondeImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encondeImage;
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                vagaImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}