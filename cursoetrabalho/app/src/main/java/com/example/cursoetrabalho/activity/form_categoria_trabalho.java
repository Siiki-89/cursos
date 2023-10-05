package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cursoetrabalho.DTO.CursoDTO;
import com.example.cursoetrabalho.DTO.VagaDTO;
import com.example.cursoetrabalho.R;

public class form_categoria_trabalho extends AppCompatActivity {

    private ListView listViewCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria_trabalho);
        listViewCat = findViewById(R.id.listViewCatTrabalho);

        VagaDTO vagaDTO = new VagaDTO();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vagaDTO.categoriasEmprego);
        listViewCat.setAdapter(adapter);
        listViewCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), form_categoria_trabalho_especifica.class);
                intent.putExtra("categoriaTrabalho", vagaDTO.categoriasEmprego[i]);
                startActivity(intent);
            }
        });
    }
}