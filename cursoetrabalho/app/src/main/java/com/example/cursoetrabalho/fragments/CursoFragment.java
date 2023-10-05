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

public class CursoFragment extends Fragment {

    public List<Postagem> postagens = new ArrayList<>();

    private List<Categoria> categorias = new ArrayList<>();
    private RecyclerView recyclerPostagem, recyclerPostagem1, recyclerCategoria, recyclerPostagem2, recyclerPostagem3;
    private int contador=0;
    private CursoDTO cursoDTO;
    private CursoDAO cursoDAO;
    private TextView txtAleatorio1, txtAleatorio2, txtAleatorio3, txtVerTudo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_curso, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(contador==0) {
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

        prepararRecyclerPostagem(recyclerPostagem);
        prepararRecyclerPostagem(recyclerPostagem1);
        prepararRecyclerPostagem(recyclerPostagem2);
        prepararRecyclerPostagem(recyclerPostagem3);
        prepararRecyclerCategoria(recyclerCategoria);


        //EM ALTA
        PostagemCursoHorizontalAdapter adapterAlta = cursoDAO.imprimirDado(postagens, recyclerPostagem, "1", "");
        recyclerPostagem.setAdapter(adapterAlta);
        postagens.clear();

        //NUMERO ALEATORIO
        List<Integer> indicesAleatorios = new ArrayList<>();
        Random random = new Random();

        while (indicesAleatorios.size() < 3) {
            int indiceAleatorio = random.nextInt(cursoDTO.categorias.length);

            if (!indicesAleatorios.contains(indiceAleatorio)) {
                indicesAleatorios.add(indiceAleatorio);
            }
        }

        // Os números aleatórios estão em 'numerosAleatorios'

        //ALEATORIO1

        PostagemCursoHorizontalAdapter adapterAleatorio1 =cursoDAO.imprimirDado(postagens, recyclerPostagem1, "2", cursoDTO.categorias[indicesAleatorios.get(0)]);
        recyclerPostagem1.setAdapter(adapterAleatorio1);
        txtAleatorio1.setText(cursoDTO.categorias[indicesAleatorios.get(0)]);
        postagens.clear();

        //ALEATORIO2

        PostagemCursoHorizontalAdapter adapterAleatorio2 =cursoDAO.imprimirDado(postagens, recyclerPostagem2, "2", cursoDTO.categorias[indicesAleatorios.get(1)]);
        recyclerPostagem2.setAdapter(adapterAleatorio2);
        txtAleatorio2.setText(cursoDTO.categorias[indicesAleatorios.get(1)]);
        postagens.clear();

        //ALEATORIO3

        PostagemCursoHorizontalAdapter adapterAleatorio3 =cursoDAO.imprimirDado(postagens, recyclerPostagem3, "2", cursoDTO.categorias[indicesAleatorios.get(2)]);
        recyclerPostagem3.setAdapter(adapterAleatorio3);
        txtAleatorio3.setText(cursoDTO.categorias[indicesAleatorios.get(2)]);

        //LISTAR CATEGORIA
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter( categorias, getContext());
        recyclerCategoria.setAdapter(categoriaAdapter);

        txtVerTudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCursoCat();
            }
        });

    }

    public void prepararRecyclerPostagem(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerPostagem.setHasFixedSize(true);
    }
    public void prepararRecyclerCategoria(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                2, GridLayoutManager.HORIZONTAL, false));
    }

    public void prepararCategorias(){
        cursoDTO = new CursoDTO();
        Categoria categoria;
        for (int i = 0; i < cursoDTO.categorias.length ; i ++) {
            categoria = new Categoria(cursoDTO.categorias[i]);
            this.categorias.add(categoria);
        }
    }
    public void abrirCursoCat(){
        Intent intent = new Intent(getContext(), form_categoria_curso.class);
        startActivity(intent);
    }


}