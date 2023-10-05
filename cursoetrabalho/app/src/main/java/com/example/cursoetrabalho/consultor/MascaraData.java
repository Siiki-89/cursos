package com.example.cursoetrabalho.consultor;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraData implements TextWatcher {

    private EditText editText;

    public MascaraData(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        try {
            String input = charSequence.toString();
            if (input.length() == 3 && !input.contains(":")) {
                editText.setText(input + ":");
                editText.setSelection(input.length());

            }
        } catch (Exception e){

        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}