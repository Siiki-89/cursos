package com.example.cursoetrabalho.consultor;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraCNPJ implements TextWatcher {
    private EditText editText;
    private boolean isUpdating = false;

    public MascaraCNPJ(EditText editText) {
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
        String formatted = formatCNPJ(input);

        isUpdating = true;
        editText.setText(formatted);
        editText.setSelection(formatted.length());
        isUpdating = false;
    }

    private String formatCNPJ(String input) {
        if (input.length() <= 2) {
            return input;
        } else if (input.length() <= 5) {
            return input.substring(0, 2) + "." + input.substring(2);
        } else if (input.length() <= 8) {
            return input.substring(0, 2) + "." + input.substring(2, 5) + "." + input.substring(5);
        } else if (input.length() <= 12) {
            return input.substring(0, 2) + "." + input.substring(2, 5) + "." + input.substring(5, 8) + "/" + input.substring(8);
        } else {
            return input.substring(0, 2) + "." + input.substring(2, 5) + "." + input.substring(5, 8) + "/" + input.substring(8, 12) + "-" + input.substring(12);
        }
    }
}