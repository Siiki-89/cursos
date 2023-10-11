package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cursoetrabalho.DAO.EmpresaDAO;
import com.example.cursoetrabalho.DTO.EmpresaDTO;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.consultor.MascaraCEP;
import com.example.cursoetrabalho.consultor.MascaraCNPJ;
import com.example.cursoetrabalho.consultor.MascaraTelefone;
import com.google.android.material.textfield.TextInputEditText;

public class form_add_empresa extends AppCompatActivity {


    EmpresaDTO empresaDTO;
    EmpresaDAO empresaDAO;
    Button btnFinalizar;
    TextInputEditText empresaCNPJ, empresaNome, empresaCEP, empresaUF, empresaCidade, empresaEndereco, empresaEmail, empresaTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_empresa);


        empresaNome = findViewById(R.id.txtNomeEmpresaCampo);
        empresaCNPJ = findViewById(R.id.txtCNPJCampo);
        empresaCEP = findViewById(R.id.txtCEPCampo);
        empresaUF = findViewById(R.id.txtUFCampo);
        empresaCidade = findViewById(R.id.txtCidadeCampo);
        empresaEndereco = findViewById(R.id.txtEnderecoCampo);
        empresaEmail = findViewById(R.id.txtEmailCampo);
        empresaTelefone = findViewById(R.id.txtTelefoneCampo);

        new MascaraCNPJ(empresaCNPJ);
        new MascaraTelefone(empresaTelefone);
        new MascaraCEP(empresaCEP, empresaUF, empresaCidade, empresaEndereco);

        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empresaDAO = new EmpresaDAO(getApplicationContext());
                empresaDTO = new EmpresaDTO(empresaCNPJ.getText().toString().trim(), empresaNome.getText().toString().trim(),
                        empresaCEP.getText().toString().trim(), empresaUF.getText().toString().trim(),
                        empresaCidade.getText().toString().trim(), empresaEndereco.getText().toString().trim(),
                        empresaEmail.getText().toString().trim(), empresaTelefone.getText().toString().trim());
                empresaDAO.inserirDado(empresaDTO);
            }
        });

    }

}