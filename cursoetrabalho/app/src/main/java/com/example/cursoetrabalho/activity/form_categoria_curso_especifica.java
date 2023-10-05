package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.adapter.PostagemCursoVerticalAdapter;
import com.example.cursoetrabalho.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class form_categoria_curso_especifica extends AppCompatActivity {
    public List<Postagem> postagens = new ArrayList<>();
    private CursoDAO cursoDAO;
    private TextView txtCat;
    private RecyclerView recyclerPostagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria_curso_especifica);
        Intent intent = getIntent();
        String categoriaCurso = intent.getExtras().getString("categoriaCurso");

        recyclerPostagem = findViewById(R.id.recyclerView);
        txtCat = findViewById(R.id.txtCat);
        txtCat.setText(categoriaCurso);


        prepararRecyclerPostagem(recyclerPostagem);
        cursoDAO = new CursoDAO(this);

        PostagemCursoVerticalAdapter adapter = cursoDAO.imprimirDadoEspecifica(postagens, recyclerPostagem,  "2", categoriaCurso);
        recyclerPostagem.setAdapter(adapter);
    }
    public void prepararRecyclerPostagem(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerPostagem.setHasFixedSize(true);
    }
}