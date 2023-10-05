package com.example.cursoetrabalho.consultor;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraCNPJ implements TextWatcher {
    private static final String CNPJ_MASK = "##.###.###/####-##";
    private boolean isUpdating = false;
    private String oldText = "";

    private EditText editText;

    public MascaraCNPJ(EditText editText) {
        this.editText = editText;
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
            isUpdating = false;
            return;
        }

        String newText = s.toString();
        String unmaskedText = newText.replaceAll("[^\\d]", "");

        if (unmaskedText.length() > 14) {
            unmaskedText = unmaskedText.substring(0, 14);
        }

        int lengthDiff = unmaskedText.length() - oldText.length();

        if (lengthDiff > 0) {
            if (unmaskedText.length() == 2 || unmaskedText.length() == 5) {
                unmaskedText += ".";
            } else if (unmaskedText.length() == 8) {
                unmaskedText += "/";
            } else if (unmaskedText.length() == 12) {
                unmaskedText += "-";
            }
        }

        isUpdating = true;
        s.replace(0, s.length(), formatCNPJ(unmaskedText));
        oldText = unmaskedText;
    }

    private String formatCNPJ(String text) {
        StringBuilder maskedText = new StringBuilder();
        int index = 0;

        for (int i = 0; i < CNPJ_MASK.length(); i++) {
            char placeholder = CNPJ_MASK.charAt(i);

            if (placeholder == '#') {
                if (index < text.length()) {
                    maskedText.append(text.charAt(index));
                    index++;
                }
            } else {
                maskedText.append(placeholder);
            }
        }

        return maskedText.toString();
    }
}
