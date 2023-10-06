package com.example.cursoetrabalho.consultor;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MascaraHrs implements TextWatcher {

    private EditText editText;
    private Context mContext;
    private boolean isUpdating = false;
    public MascaraHrs(Context mContext, EditText editText) {
        this.editText = editText;
        this.mContext = mContext;
        this.editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isUpdating) {
            isUpdating = false;
            return;
        }

        String hrs = editable.toString();
        String hrsSemCaracters = hrs.replaceAll("[^\\d]", "");

        if (hrsSemCaracters.length() > 3) {
            String horas = hrsSemCaracters.substring(0, 3);
            String minutos = hrsSemCaracters.substring(3);

            int minutosInt = Integer.parseInt(minutos);
            if (minutosInt > 60) {
                Toast.makeText(mContext, "Minutos não podem ser maiores que 60", Toast.LENGTH_SHORT).show();
                minutos = "60";
            }
            hrs = horas + ":" + minutos;
            isUpdating = true;
            editText.setText(hrs);
            editText.setSelection(hrs.length());
            isUpdating = false;
        }
    }
}