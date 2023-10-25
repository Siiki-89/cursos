package com.example.cursoetrabalho.activity;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.DTO.CursoDTO;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.consultor.MascaraHrs;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class form_add_curso extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText cursoNome, cursoFornecedor, cursoUrl, cursoQtdHoras;
    private Bitmap bitmap;
    private static final int PICK_IMAGE_REQUEST = 1;
    String cursoCategoria;
    EditText cursoDescricao;
    Button btnEnviar, btnLogo;
    RadioGroup cursoModalidadeButton;
    public CursoDTO cursoDTORegistro;
    Spinner spinnerCategoriaCurso;
    CursoDAO cursoDAO;
    ImageView imgCurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_curso);
        CursoDTO cursoDTO = new CursoDTO();
        cursoDAO = new CursoDAO(this);

        Spinner spinCat = findViewById(R.id.spinnerCategoria);
        spinCat.setOnItemSelectedListener(this);

        ArrayAdapter adCategoria = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cursoDTO.categorias);

        adCategoria.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spinCat.setAdapter(adCategoria);

        spinnerCategoriaCurso = findViewById(R.id.spinnerCategoria);
        cursoNome= findViewById(R.id.txtNomeCursoCampo);
        cursoFornecedor = findViewById(R.id.txtFornecedorCampo);
        cursoUrl = findViewById(R.id.txtUrlFornecedorCampo);
        cursoQtdHoras = findViewById(R.id.txtQtdHorasCampo);
        cursoDescricao = findViewById(R.id.txtDescCurso);
        cursoModalidadeButton = findViewById(R.id.RGroup);
        imgCurso = findViewById(R.id.imgCurso4);

        btnEnviar = findViewById(R.id.btnFinalizar);
        btnLogo = findViewById(R.id.btnLogo);

        new MascaraHrs(this, cursoQtdHoras);

        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cursoModalidade = "";
                try {
                    cursoModalidade = retornarBotao();
                } catch (Exception e){

                }
                String img = imageToString(bitmap);

                cursoCategoria = spinnerCategoriaCurso.getSelectedItem().toString();
                cursoDTORegistro = new CursoDTO (cursoCategoria, cursoNome.getText().toString().trim(), cursoFornecedor.getText().toString().trim()
                , cursoQtdHoras.getText().toString().trim(), cursoDescricao.getText().toString().trim()
                , cursoModalidade.trim(), cursoUrl.getText().toString().trim(), img.trim());

                cursoDAO.inserirDado(cursoDTORegistro);

            }
        });
    }
    public String retornarBotao(){
        int idSelecionado = cursoModalidadeButton.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(idSelecionado);
        String cursoModalidade = radioButton.getText().toString();
        return cursoModalidade;
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
                imgCurso.setImageBitmap(bitmap);
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