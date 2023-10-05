package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.adapter.PostagemCursoHorizontalAdapter;
import com.example.cursoetrabalho.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class form_view_curso extends AppCompatActivity {
    private TextView txtNomeCurso, txtQtdView3, txtQtdLike3, txtQtdHoras3, txtFornecedor3, txtDescricao3, txtUrl3, txtIsPresencial3;
    public List<Postagem> postagens = new ArrayList<>();
    private CursoDAO cursoDAO;
    private RecyclerView recyclerPostagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view_curso);

        txtNomeCurso = findViewById(R.id.txtNomeCurso3);
        txtIsPresencial3 = findViewById(R.id.txtIsPresencial3);
        txtQtdView3 = findViewById(R.id.txtQtdView3);
        txtQtdLike3= findViewById(R.id.txtQtdLike3);
        txtQtdHoras3= findViewById(R.id.txtQtdHoras3);
        txtFornecedor3= findViewById(R.id.txtFornecedor3);
        txtDescricao3= findViewById(R.id.txtDescricao3);
        txtUrl3= findViewById(R.id.txtUrl3);
        recyclerPostagem = findViewById(R.id.recyclerPostagem);

        Intent intent = getIntent();
        String nomeCurso = intent.getExtras().getString("txtNomeCurso");

        cursoDAO = new CursoDAO(this);

        cursoDAO.imprimirDados("3", nomeCurso,  new CursoDAO.OnCategoriaCursoListener() {
                    @Override
                    public void onCategoriaCursoObtida(String categoriaCurso,String cursoNome,String cursoFornecedor,String cursoQtdHoras,
                                                       String cursoDescricao,String cursoPresencial,String cursoUrl,String cursoQtdView,String cursoQtdLike) {
                        prepararRecyclerPostagem(recyclerPostagem);
                        PostagemCursoHorizontalAdapter adapterPostagem =cursoDAO.imprimirDado(postagens, recyclerPostagem, "2", categoriaCurso);
                        recyclerPostagem.setAdapter(adapterPostagem);

                        txtNomeCurso.setText(cursoNome);
                        txtFornecedor3.setText(cursoFornecedor);
                        txtQtdHoras3.setText(cursoQtdHoras);
                        txtDescricao3.setText(cursoDescricao);
                        txtIsPresencial3.setText(cursoPresencial);
                        txtUrl3.setText(cursoUrl);
                        txtQtdView3.setText(cursoQtdView);
                        txtQtdLike3.setText(cursoQtdLike);
                    }
                });
    }
    public void prepararRecyclerPostagem(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerPostagem.setHasFixedSize(true);
    }
}