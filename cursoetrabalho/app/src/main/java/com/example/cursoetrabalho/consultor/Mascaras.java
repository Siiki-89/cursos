package com.example.cursoetrabalho.consultor;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Mascaras implements TextWatcher {

    private EditText editText;

    public Mascaras(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Nada a fazer aqui
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Nada a fazer aqui
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String input = editable.toString();

        if (input.length() == 2 && !input.contains(":")) {
            // Adicione dois pontos automaticamente quando o usuário digitar os primeiros dois dígitos
            input += ":";
            editText.setText(input);
            editText.setSelection(input.length());
        } else if (input.length() > 5) {
            // Limitar a entrada a 5 caracteres (hh:mm)
            editText.setText(input.substring(0, 5));
            editText.setSelection(5);
        }

        // Você também pode validar a entrada de tempo aqui, se necessário
    }
}