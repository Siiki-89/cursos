package com.example.cursoetrabalho.consultor;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraData implements TextWatcher {
    private EditText editText;
    private boolean isUpdating = false;

    public MascaraData(EditText editText) {
        this.editText = editText;
        this.editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isUpdating) {
            return;
        }

        String input = s.toString().replaceAll("[^\\d]", "");

        if (input.length() > 8) {
            input = input.substring(0, 8); // Limita a entrada a 8 caracteres
        }

        String formatted = formatData(input);

        isUpdating = true;
        editText.setText(formatted);
        editText.setSelection(formatted.length());
        isUpdating = false;
    }

    private String formatData(String input) {
        if (input.length() <= 2) {
            return input;
        } else if (input.length() <= 4) {
            return input.substring(0, 2) + "/" + input.substring(2);
        } else if (input.length() <= 8) {
            return input.substring(0, 2) + "/" + input.substring(2, 4) + "/" + input.substring(4);
        } else {
            // Reduz a data para "dd/mm/yyyy" se houver mais de 8 caracteres.
            return input.substring(0, 2) + "/" + input.substring(2, 4) + "/" + input.substring(4, 8);
        }
    }
}
