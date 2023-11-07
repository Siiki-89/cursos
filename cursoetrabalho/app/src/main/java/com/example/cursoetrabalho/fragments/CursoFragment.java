package com.example.cursoetrabalho.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cursoetrabalho.DAO.CursoDAO;
import com.example.cursoetrabalho.DTO.CursoDTO;
import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.activity.form_categoria_curso;
import com.example.cursoetrabalho.adapter.*;
import com.example.cursoetrabalho.model.Categoria;
import com.example.cursoetrabalho.model.Postagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.cursoetrabalho.adapter.CategoriaAdapter;
import com.example.cursoetrabalho.adapter.PostagemCursoHorizontalAdapter;

public class CursoFragment extends Fragment {
    private RecyclerView recyclerPostagem;
    private RecyclerView recyclerPostagem1;
    private RecyclerView recyclerPostagem2;
    private RecyclerView recyclerPostagem3;
    private RecyclerView recyclerCategoria;
    private TextView txtAleatorio1;
    private TextView txtAleatorio2;
    private TextView txtAleatorio3;
    private TextView txtVerTudo;

    private List<Postagem> postagens = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private CursoDAO cursoDAO;
    private CursoDTO cursoDTO;
    private int contador = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_curso, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (contador == 0) {
            prepararCategorias();
            contador++;
        }

        cursoDAO = new CursoDAO(getContext());

        recyclerPostagem = view.findViewById(R.id.recyclerPostagem);
        recyclerPostagem1 = view.findViewById(R.id.recyclerPostagem1);
        recyclerPostagem2 = view.findViewById(R.id.recyclerPostagem2);
        recyclerPostagem3 = view.findViewById(R.id.recyclerPostagem3);
        recyclerCategoria = view.findViewById(R.id.recyclerCategoria);

        txtAleatorio1 = view.findViewById(R.id.txtAleatorio1);
        txtAleatorio2 = view.findViewById(R.id.txtAleatorio2);
        txtAleatorio3 = view.findViewById(R.id.txtAleatorio3);
        txtVerTudo = view.findViewById(R.id.txtVerTudo);

        configurarRecyclerPostagem(recyclerPostagem);
        configurarRecyclerPostagem(recyclerPostagem1);
        configurarRecyclerPostagem(recyclerPostagem2);
        configurarRecyclerPostagem(recyclerPostagem3);
        configurarRecyclerCategoria(recyclerCategoria);

        carregarDados(); // Função para carregar os dados nas recycler views

        txtVerTudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCursoCat();
            }
        });
    }

    private void configurarRecyclerPostagem(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
    }

    private void configurarRecyclerCategoria(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
    }

    private void prepararCategorias() {
        cursoDTO = new CursoDTO();
        Categoria categoria;
        for (String categoriaNome : cursoDTO.categorias) {
            categoria = new Categoria(categoriaNome);
            categorias.add(categoria);
        }
    }

    private void abrirCursoCat() {
        Intent intent = new Intent(getContext(), form_categoria_curso.class);
        startActivity(intent);
    }

    private void carregarDados() {
        // EM ALTA
        PostagemCursoHorizontalAdapter adapterAlta = cursoDAO.imprimirDado(postagens, recyclerPostagem, "1", "");
        recyclerPostagem.setAdapter(adapterAlta);
        postagens.clear();

        // NÚMERO ALEATÓRIO
        List<Integer> indicesAleatorios = new ArrayList<>();
        Random random = new Random();

        while (indicesAleatorios.size() < 3) {
            int indiceAleatorio = random.nextInt(cursoDTO.categorias.length);

            if (!indicesAleatorios.contains(indiceAleatorio)) {
                indicesAleatorios.add(indiceAleatorio);
            }
        }
        // CATEGORIA
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter( categorias, getContext());
        recyclerCategoria.setAdapter(categoriaAdapter);

        // ALEATÓRIO 1
        PostagemCursoHorizontalAdapter adapterAleatorio1 = cursoDAO.imprimirDado(postagens, recyclerPostagem1, "2", cursoDTO.categorias[indicesAleatorios.get(0)]);
        recyclerPostagem1.setAdapter(adapterAleatorio1);
        txtAleatorio1.setText(cursoDTO.categorias[indicesAleatorios.get(0)]);
        postagens.clear();

        // ALEATÓRIO 2
        PostagemCursoHorizontalAdapter adapterAleatorio2 = cursoDAO.imprimirDado(postagens, recyclerPostagem2, "2", cursoDTO.categorias[indicesAleatorios.get(1)]);
        recyclerPostagem2.setAdapter(adapterAleatorio2);
        txtAleatorio2.setText(cursoDTO.categorias[indicesAleatorios.get(1)]);
        postagens.clear();

        // ALEATÓRIO 3
        PostagemCursoHorizontalAdapter adapterAleatorio3 = cursoDAO.imprimirDado(postagens, recyclerPostagem3, "2", cursoDTO.categorias[indicesAleatorios.get(2)]);
        recyclerPostagem3.setAdapter(adapterAleatorio3);
        txtAleatorio3.setText(cursoDTO.categorias[indicesAleatorios.get(2)]);
    }
}
