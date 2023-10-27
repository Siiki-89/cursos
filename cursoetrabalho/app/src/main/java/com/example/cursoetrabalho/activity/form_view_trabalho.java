package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cursoetrabalho.DAO.VagaDAO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.model.Trabalho;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class form_view_trabalho extends AppCompatActivity {
    private TextView txtCargo, txtEmpresa, txtData, txtQtdVaga, txtDescricao, txtRegiao, txtVagaUrl;
    private VagaDAO vagaDAO;
    private ImageView imgVagaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view_trabalho);

        Intent intent = getIntent();
        String nomeCurso = intent.getExtras().getString("txtCargo");

        txtCargo = findViewById(R.id.txtVagaTrabalho4);
        txtEmpresa = findViewById(R.id.txtEmpresa3);
        txtData = findViewById(R.id.txtPrazo3);
        txtQtdVaga = findViewById(R.id.txtQtdVaga3);
        txtDescricao = findViewById(R.id.txtDescricao4);
        txtRegiao = findViewById(R.id.txtLocalizacao3);
        imgVagaView = findViewById(R.id.imgVagaView);
        txtVagaUrl = findViewById(R.id.txtVagaUrl);

        final String[] VagaURL = new String[1];

        vagaDAO = new VagaDAO(this);
        vagaDAO.imprimirDadosTrabalho("3", nomeCurso, new VagaDAO.OnCategoriaVagaListener() {
            @Override
            public void OnCategoriaVagaListener(String cidade, String prazo, String cargo, String empresa, String descricao, String qtdVaga, String imgVaga, String vagaUrl) {
                txtCargo.setText(cargo);
                txtEmpresa.setText(empresa);
                txtData.setText(prazo);
                txtQtdVaga.setText(qtdVaga + " vagas disponíveis!");
                txtDescricao.setText(descricao);
                txtRegiao.setText(cidade);
                try {
                    Picasso.get().load(imgVaga).into(imgVagaView);
                } catch (Exception e){
                }
                VagaURL[0] = vagaUrl;
            }
        });
        txtVagaUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(VagaURL[0]));

                startActivity(intent);
            }
        });
    }
}