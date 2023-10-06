package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.DTO.CursoDTO;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.consultor.MascaraHrs;
import com.google.android.material.textfield.TextInputEditText;

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
        new MascaraHrs(this, cursoQtdHoras);
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