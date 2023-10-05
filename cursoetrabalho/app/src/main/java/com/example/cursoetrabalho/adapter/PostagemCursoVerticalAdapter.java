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

public class PostagemCursoVerticalAdapter extends RecyclerView.Adapter<PostagemCursoVerticalAdapter.MyViewHolder> {
    private List<Postagem> postagens;
    Context context;

    public PostagemCursoVerticalAdapter(Context context, List<Postagem> postagens) {
        this.postagens = postagens;
        this.context = context;
    }

    @NonNull
    @Override
    public PostagemCursoVerticalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detalhe_postagem_curso_vertical, parent, false);

        return new PostagemCursoVerticalAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem postagem = postagens.get(position);
        holder.cursoNome.setText(postagem.getTxtNomeCurso());
        holder.cursoFornecedor.setText(postagem.getTxtFornecedor());
        holder.cursoQtdView.setText(postagem.getTxtQtdVisualizacao());
        holder.cursoQtdLike.setText(postagem.getTxtQtdGostei());
        holder.cursoIsPresencial.setText(postagem.getTxtIsPresencial());

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
        private TextView cursoNome, cursoQtdView, cursoQtdLike, cursoFornecedor, cursoIsPresencial;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cursoNome = itemView.findViewById(R.id.txtNomeCurso4);
            cursoQtdView= itemView.findViewById(R.id.txtCursoQtdView4);
            cursoQtdLike= itemView.findViewById(R.id.txtCursoQtdLike4);
            cursoFornecedor= itemView.findViewById(R.id.txtCursoFonecedor4);
            cursoIsPresencial= itemView.findViewById(R.id.txtCursoIsPresencial4);
        }
    }

}