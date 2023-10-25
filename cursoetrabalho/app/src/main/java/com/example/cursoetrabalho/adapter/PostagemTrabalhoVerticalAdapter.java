package com.example.cursoetrabalho.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.activity.form_view_trabalho;
import com.example.cursoetrabalho.model.Trabalho;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostagemTrabalhoVerticalAdapter extends RecyclerView.Adapter <PostagemTrabalhoVerticalAdapter.MyViewHolder> {
    private List<Trabalho> trabalhos;
    Context context;

    public PostagemTrabalhoVerticalAdapter(List<Trabalho> trabalhos, Context context) {
        this.trabalhos = trabalhos;
        this.context = context;
    }

    @NonNull
    @Override
    public PostagemTrabalhoVerticalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detalhe_postagem_trabalho_vertical, parent, false);

        return new PostagemTrabalhoVerticalAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trabalho trabalho = trabalhos.get(position);
        holder.txtCargo.setText(trabalho.getTxtCargo());
        holder.txtEmpresa.setText(trabalho.getTxtEmpresa());
        holder.txtRegiao.setText(trabalho.getTxtRegiao());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, form_view_trabalho.class);
                intent.putExtra("txtCargo", trabalho.getTxtCargo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trabalhos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCargo, txtEmpresa, txtRegiao;
        ImageView imgTrabalho;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCargo = itemView.findViewById(R.id.txtCargo);
            txtEmpresa = itemView.findViewById(R.id.txtEmpresa3);
            txtRegiao = itemView.findViewById(R.id.txtRegiao);
            imgTrabalho = itemView.findViewById(R.id.imgTrabalho);
        }
    }
}
