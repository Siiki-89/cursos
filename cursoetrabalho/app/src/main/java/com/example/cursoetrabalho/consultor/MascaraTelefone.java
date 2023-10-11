package com.example.cursoetrabalho.consultor;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraTelefone implements TextWatcher {
    private EditText editText;
    private boolean isUpdating = false;

    public MascaraTelefone(EditText editText) {
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

        if (input.length() > 11) {
            input = input.substring(0, 11); // Limit to 11 characters
        }

        String formatted = formatPhoneNumber(input);

        isUpdating = true;
        editText.setText(formatted);
        editText.setSelection(formatted.length());
        isUpdating = false;
    }

    private String formatPhoneNumber(String input) {
        if (input.length() <= 2) {
            return input;
        } else if (input.length() <= 7) {
            return "(" + input.substring(0, 2) + ")" + input.substring(2);
        } else if (input.length() <= 11) {
            return "(" + input.substring(0, 2) + ")" + input.substring(2, 7) + "-" + input.substring(7);
        } else {
            // Trim to "(00)00000-0000" format if more than 11 characters.
            return "(" + input.substring(0, 2) + ")" + input.substring(2, 7) + "-" + input.substring(7, 11);
        }
    }
}

