package com.example.cursoetrabalho.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.cursoetrabalho.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class form_entrar extends AppCompatActivity {
    private TextInputLayout textInputLayoutCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_entrar);

        textInputLayoutCpf = findViewById(R.id.textInputLayoutCpf);
        TextInputEditText editTextCpf = findViewById(R.id.editTextCpf);
        //MASCARA PARA O CPF
        editTextCpf.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                String input = editable.toString();
                String cpfWithMask = applyCpfMask(input);

                if (!cpfWithMask.equals(current)) {
                    isUpdating = true;
                    editTextCpf.setText(cpfWithMask);
                    editTextCpf.setSelection(cpfWithMask.length());
                    current = cpfWithMask;
                }
            }
        });
    }
    //ABRIR FORMPRINCIPAL
    public void abrirPrincipal(View view){
        Intent intent = new Intent(getApplicationContext(), form_principal.class);
        startActivity(intent);
        finish();
    }

    private String applyCpfMask(String cpf) {
        // Remove caracteres não numéricos
        String numbersOnly = cpf.replaceAll("[^0-9]", "");

        // Aplica a máscara de CPF: 12345678901 -> 123.456.789-01
        StringBuilder maskedCpf = new StringBuilder();
        for (int i = 0; i < numbersOnly.length(); i++) {
            maskedCpf.append(numbersOnly.charAt(i));
            if (i == 2 || i == 5) {
                maskedCpf.append(".");
            } else if (i == 8) {
                maskedCpf.append("-");
            }
        }
        return maskedCpf.toString();
    }
}