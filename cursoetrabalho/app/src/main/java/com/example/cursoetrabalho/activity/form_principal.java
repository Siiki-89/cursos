package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cursoetrabalho.R;
import com.example.cursoetrabalho.databinding.ActivityFormPrincipalBinding;


public class form_principal extends AppCompatActivity {
    ActivityFormPrincipalBinding binding;
    NavHostFragment navHostFragment;
    NavController navController;
    private int isAdm = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iniciarNavegacao();

    }


    void iniciarNavegacao(){
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        if(isAdm==1){
            inflater.inflate(R.menu.botton_top_adm, menu);
        } else {
            inflater.inflate(R.menu.botton_top, menu);
        }
        return true;
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
        }

        return super.onOptionsItemSelected(item);
    }
    public void abrirEntrar(){
        Intent intent = new Intent(getApplicationContext(), form_entrar.class);
        startActivity(intent);
    }
    public void abrirVaga(){
        Intent intent = new Intent(getApplicationContext(), form_add_vaga.class);
        startActivity(intent);
    }
    public void abirEmpresa(){
        Intent intent = new Intent(getApplicationContext(), form_add_empresa.class);
        startActivity(intent);
    }
    public void abrirCurso(){
        Intent intent = new Intent(getApplicationContext(), form_add_curso.class);
        startActivity(intent);
    }

}