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

    private TextInputEditText cursoNome;
    private TextInputEditText cursoFornecedor;
    private TextInputEditText cursoUrl;
    private TextInputEditText cursoQtdHoras;
    private EditText cursoDescricao;
    private RadioGroup cursoModalidadeButton;
    private Spinner spinnerCategoriaCurso;
    private ImageView imgCurso;

    // Variável para a imagem selecionada
    private Bitmap bitmap;

    // Constante para solicitação da seleção de imagem
    private static final int PICK_IMAGE_REQUEST = 1;

    // Outras variáveis
    private String cursoCategoria;
    private Button btnEnviar;
    private Button btnLogo;
    public CursoDTO cursoDTORegistro;

    // Acesso ao banco de dados
    private CursoDAO cursoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_curso);

        carregarSpinner();
        iniciarVariaveis();
        salvarCurso();
        abrirGaleria();

        new MascaraHrs(this, cursoQtdHoras);

    }
    public void carregarSpinner(){
        Spinner spinCat = findViewById(R.id.spinnerCategoria);
        spinCat.setOnItemSelectedListener(this);

        CursoDTO cursoDTO = new CursoDTO();
        ArrayAdapter<String> adCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cursoDTO.categorias);
        adCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCat.setAdapter(adCategoria);
    }
    public void iniciarVariaveis(){
        cursoDAO = new CursoDAO(this);
        spinnerCategoriaCurso = findViewById(R.id.spinnerCategoria);
        cursoNome = findViewById(R.id.txtNomeCursoCampo);
        cursoFornecedor = findViewById(R.id.txtFornecedorCampo);
        cursoUrl = findViewById(R.id.txtUrlFornecedorCampo);
        cursoQtdHoras = findViewById(R.id.txtQtdHorasCampo);
        cursoDescricao = findViewById(R.id.txtDescCurso);
        cursoModalidadeButton = findViewById(R.id.RGroup);
        imgCurso = findViewById(R.id.imgCurso4);
        btnEnviar = findViewById(R.id.btnFinalizar);
        btnLogo = findViewById(R.id.btnLogo);
    }

    public String retornarBotao() {
        int idSelecionado = cursoModalidadeButton.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(idSelecionado);
        return radioButton.getText().toString();
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
                imgCurso.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void abrirGaleria(){
        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }
    public void salvarCurso(){
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cursoModalidade = retornarBotao();
                String img = imageToString(bitmap);

                cursoCategoria = spinnerCategoriaCurso.getSelectedItem().toString();
                cursoDTORegistro = new CursoDTO(cursoCategoria, cursoNome.getText().toString().trim(), cursoFornecedor.getText().toString().trim(),
                        cursoQtdHoras.getText().toString().trim(), cursoDescricao.getText().toString().trim(),
                        cursoModalidade.trim(), cursoUrl.getText().toString().trim(), img.trim());

                cursoDAO.inserirDado(cursoDTORegistro);
            }
        });
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // Ação quando um item é selecionado no Spinner
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Ação quando nenhum item é selecionado no Spinner
    }
}