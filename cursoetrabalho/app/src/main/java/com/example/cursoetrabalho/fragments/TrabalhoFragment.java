package com.example.cursoetrabalho.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cursoetrabalho.DAO.VagaDAO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.activity.form_add_curso;
import com.example.cursoetrabalho.activity.form_add_empresa;
import com.example.cursoetrabalho.activity.form_add_vaga;
import com.example.cursoetrabalho.activity.form_categoria_trabalho;
import com.example.cursoetrabalho.activity.form_entrar;
import com.example.cursoetrabalho.adapter.PostagemTrabalhoVerticalAdapter;
import com.example.cursoetrabalho.model.Postagem;
import com.example.cursoetrabalho.model.Trabalho;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoFragment extends Fragment {
    RecyclerView recyclerView;
    private int isAdm = 1;
    private List<Trabalho> trabalhos = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trabalho, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerTrabalho);
        VagaDAO vagaDAO = new VagaDAO(getContext());
        prepararRecyclerPostagem(recyclerView);

        PostagemTrabalhoVerticalAdapter adapter = vagaDAO.imprimirDados(trabalhos, recyclerView, getContext(), "1", "");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.botton_top_trabalho, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sair) {
            abrirEntrar();
        } else if (id == R.id.addcurso){
            abrirCurso();
        } else if (id == R.id.addempresa){
            abirEmpresa();
        } else if (id == R.id.addvaga){
            abrirVaga();
        } else if (id == R.id.categoria){
            abrirCategorias();
        }

        return super.onOptionsItemSelected(item);
    }
    public void abrirCategorias(){
        Intent intent = new Intent(getContext(), form_categoria_trabalho.class);
        startActivity(intent);
    }
    public void abrirEntrar(){
        Intent intent = new Intent(getContext(), form_entrar.class);
        startActivity(intent);
    }
    public void abrirVaga(){
        Intent intent = new Intent(getContext(), form_add_vaga.class);
        startActivity(intent);
    }
    public void abirEmpresa(){
        Intent intent = new Intent(getContext(), form_add_empresa.class);
        startActivity(intent);
    }
    public void abrirCurso(){
        Intent intent = new Intent(getContext(), form_add_curso.class);
        startActivity(intent);
    }

    public void prepararRecyclerPostagem(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
    }
}