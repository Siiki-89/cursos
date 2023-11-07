package com.example.cursoetrabalho.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    private VagaDTO vagaDTODados;
    private Bitmap bitmap;
    private static final int PICK_IMAGE_REQUEST = 1;
    private VagaDAO vagaDAO;
    private ImageView vagaImg;
    private Spinner spinnerCategoriaVaga;
    private TextInputEditText nomeEmpresa, vagaCargo, vagaDataInicial, vagaDataFinal, vagaQtd, vagaUrl;
    private EditText vagaDesc;
    private String categoria;
    private Button btnInsrir, btnVagaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_vaga);

        iniciarVariaveis();
        iniciarMascaras();
        configBotao();
    }

    private void iniciarVariaveis() {
        VagaDTO vagaDTO = new VagaDTO();

        Spinner spinCat = findViewById(R.id.spinnerCategoriaVaga);
        spinCat.setOnItemSelectedListener(this);

        ArrayAdapter adCategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vagaDTO.categoriasEmprego);

        adCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
    }

    private void iniciarMascaras() {
        new MascaraData(vagaDataInicial);
        new MascaraData(vagaDataFinal);
    }

    private void configBotao() {
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

    private void openGallery() {
        // Cria uma intenção para selecionar uma imagem da galeria
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // Inicia a atividade da galeria e aguarda o resultado
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private String imageToString(Bitmap bitmap) {
        if (bitmap == null) {
            return ""; // Retorna uma string vazia se o bitmap for nulo
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Comprime o bitmap em formato JPEG
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        byte[] imageBytes = outputStream.toByteArray();

        // Codifica os bytes da imagem em uma string Base64
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o resultado é da solicitação de seleção de imagem
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            try {
                // Converte a URI da imagem em um objeto Bitmap
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Exibe a imagem no ImageView
                vagaImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // Ação quando um item é selecionado no Spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Ação quando nenhum item é selecionado no Spinner
    }
}
