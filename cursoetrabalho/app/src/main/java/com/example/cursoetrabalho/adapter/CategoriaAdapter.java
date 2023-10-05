package com.example.cursoetrabalho.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.activity.form_categoria_curso_especifica;
import com.example.cursoetrabalho.activity.form_view_curso;
import com.example.cursoetrabalho.model.Categoria;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.MyViewHolder> {
    private List<Categoria> categorias;
    private Context context;

    public CategoriaAdapter(List<Categoria> categorias, Context context) {
        this.categorias = categorias;
        this.context = context;
    }

    public CategoriaAdapter(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalhe_categoria, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.txtCategoria1.setText(categoria.getTxtCategoria1());
        holder.cardCategoriasCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, form_categoria_curso_especifica.class);
                Log.d("Sssss",categoria.getTxtCategoria1() );
                intent.putExtra("categoriaCurso", categoria.getTxtCategoria1());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button txtCategoria1, txtCategoria2, txtCategoria3, txtCategoria4;
        private CardView cardCategoriasCurso;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoria1 = itemView.findViewById(R.id.btnCategoria1);
            cardCategoriasCurso = itemView.findViewById(R.id.cardCategoriasCurso);
        }
    }
}