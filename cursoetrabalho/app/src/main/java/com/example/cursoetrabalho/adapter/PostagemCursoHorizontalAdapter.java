package com.example.cursoetrabalho.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.activity.form_view_curso;
import com.example.cursoetrabalho.model.Postagem;

import java.util.List;

public class PostagemCursoHorizontalAdapter extends RecyclerView.Adapter<PostagemCursoHorizontalAdapter.MyViewHolder>{
    private List<Postagem> postagens;
    Context context;


    public PostagemCursoHorizontalAdapter(Context context, List<Postagem> listPostagens) {
        this.postagens = listPostagens;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detalhe_postagem_curso_horizontal, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem postagem = postagens.get(position);
        holder.txtNomeCurso.setText(postagem.getTxtNomeCurso());
        holder.txtFornecedor.setText(postagem.getTxtFornecedor());
        holder.txtQtdVisualizacao.setText(postagem.getTxtQtdVisualizacao());
        holder.txtQtdGostei.setText(postagem.getTxtQtdGostei());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, form_view_curso.class);
                intent.putExtra("txtNomeCurso", postagem.getTxtNomeCurso());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtNomeCurso, txtFornecedor, txtQtdVisualizacao, txtQtdGostei;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeCurso = itemView.findViewById(R.id.txtNomeCurso);
            txtFornecedor = itemView.findViewById(R.id.txtFornecedor);
            txtQtdVisualizacao = itemView.findViewById(R.id.txtQtdVisualizacao);
            txtQtdGostei = itemView.findViewById(R.id.txtQtdGostei);
        }
    }

}
