package com.example.cursoetrabalho.activity;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.DTO.CursoDTO;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.consultor.Mascaras;
import com.example.cursoetrabalho.model.Postagem;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class form_add_curso extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText cursoNome, cursoFornecedor, cursoUrl, cursoQtdHoras;
    String cursoCategoria;
    EditText cursoDescricao;
    Button btnEnviar;
    RadioGroup cursoModalidadeButton;
    public CursoDTO cursoDTORegistro;
    Spinner spinnerCategoriaCurso;
    CursoDAO cursoDAO;
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

        btnEnviar = findViewById(R.id.btnFinalizar);
        cursoQtdHoras.addTextChangedListener(new Mascaras(cursoQtdHoras));
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cursoModalidade = "";
                try {
                    cursoModalidade = retornarBotao();
                } catch (Exception e){

                }

                cursoCategoria = spinnerCategoriaCurso.getSelectedItem().toString();
                cursoDTORegistro = new CursoDTO (cursoCategoria, cursoNome.getText().toString().trim(), cursoFornecedor.getText().toString().trim()
                , cursoQtdHoras.getText().toString().trim(), cursoDescricao.getText().toString().trim()
                , cursoModalidade.trim(), cursoUrl.getText().toString().trim());

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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}