package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.DAO.VagaDAO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.adapter.PostagemCursoVerticalAdapter;
import com.example.cursoetrabalho.adapter.PostagemTrabalhoVerticalAdapter;
import com.example.cursoetrabalho.model.Trabalho;

import java.util.ArrayList;
import java.util.List;

public class form_categoria_trabalho_especifica extends AppCompatActivity {

    private TextView txtCategoria;
    private RecyclerView recyclerPostagem;
    private List<Trabalho> trabalhos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria_trabalho_especifica);

        Intent intent = getIntent();
        String categoriaTrabalho = intent.getExtras().getString("categoriaTrabalho");

        txtCategoria = findViewById(R.id.txtCategoria);
        recyclerPostagem = findViewById(R.id.recyclerView);

        txtCategoria.setText(categoriaTrabalho);
        VagaDAO vagaDAO = new VagaDAO(this);
        prepararRecyclerPostagem(recyclerPostagem);

        PostagemTrabalhoVerticalAdapter adapter = vagaDAO.imprimirDados(trabalhos, recyclerPostagem, "1", "");
        recyclerPostagem.setAdapter(adapter);
    }
    public void prepararRecyclerPostagem(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerPostagem.setHasFixedSize(true);
    }
}