package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.adapter.PostagemCursoHorizontalAdapter;
import com.example.cursoetrabalho.model.Postagem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class form_view_curso extends AppCompatActivity {
    private ImageView imgCurso;
    private TextView txtNomeCurso, txtQtdView3, txtQtdLike3, txtQtdHoras3, txtFornecedor3, txtDescricao3, txtUrl3, txtIsPresencial3;
    private RecyclerView recyclerPostagem;

    private List<Postagem> postagens = new ArrayList();
    private CursoDAO cursoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view_curso);

        initializeViews();

        Intent intent = getIntent();
        String nomeCurso = intent.getStringExtra("txtNomeCurso");

        cursoDAO = new CursoDAO(this);
        cursoDAO.imprimirDados("3", nomeCurso, new CursoDAO.OnCategoriaCursoListener() {
            @Override
            public void onCategoriaCursoObtida(String categoriaCurso, String cursoNome, String cursoFornecedor, String cursoQtdHoras,
                                               String cursoDescricao, String cursoPresencial, String cursoUrl, String cursoQtdView, String cursoQtdLike, String cursoImg) {
                prepararRecyclerView();
                PostagemCursoHorizontalAdapter adapterPostagem =cursoDAO.imprimirDado(postagens, recyclerPostagem, "2", categoriaCurso);
                recyclerPostagem.setAdapter(adapterPostagem);

                loadCourseData(cursoNome, cursoFornecedor, cursoQtdHoras, cursoDescricao, cursoPresencial, cursoUrl, cursoQtdView, cursoQtdLike, cursoImg);
            }
        });
    }

    private void initializeViews() {
        imgCurso = findViewById(R.id.imageView4);
        txtNomeCurso = findViewById(R.id.txtNomeCurso3);
        txtIsPresencial3 = findViewById(R.id.txtIsPresencial3);
        txtQtdView3 = findViewById(R.id.txtQtdView3);
        txtQtdLike3 = findViewById(R.id.txtQtdLike3);
        txtQtdHoras3 = findViewById(R.id.txtQtdHoras3);
        txtFornecedor3 = findViewById(R.id.txtFornecedor3);
        txtDescricao3 = findViewById(R.id.txtDescricao3);
        txtUrl3 = findViewById(R.id.txtUrl3);
        recyclerPostagem = findViewById(R.id.recyclerPostagem);
    }

    private void prepararRecyclerView() {
        recyclerPostagem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerPostagem.setHasFixedSize(true);

    }

    private void loadCourseData(String nomeCurso, String cursoFornecedor, String cursoQtdHoras, String cursoDescricao, String cursoPresencial,
                                String cursoUrl, String cursoQtdView, String cursoQtdLike, String cursoImg) {
        txtNomeCurso.setText(nomeCurso);
        txtFornecedor3.setText(cursoFornecedor);
        txtQtdHoras3.setText(cursoQtdHoras);
        txtDescricao3.setText(cursoDescricao);
        txtIsPresencial3.setText(cursoPresencial);
        txtUrl3.setText(cursoUrl);
        txtQtdView3.setText(cursoQtdView);
        txtQtdLike3.setText(cursoQtdLike);

        try {
            Picasso.get().load(cursoImg).into(imgCurso);
        } catch (Exception e) {
        }
    }
}