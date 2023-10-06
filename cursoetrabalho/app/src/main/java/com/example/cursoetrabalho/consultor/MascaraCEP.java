package com.example.cursoetrabalho.consultor;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MascaraCEP implements TextWatcher {
    private EditText editText;
    private TextInputEditText empresaUF;
    private TextInputEditText empresaCidade;
    private TextInputEditText empresaEndereco;
    private boolean isUpdating;

    public MascaraCEP(EditText editText, TextInputEditText empresaUF, TextInputEditText empresaCidade, TextInputEditText empresaEndereco) {
        this.editText = editText;
        this.empresaUF = empresaUF;
        this.empresaCidade = empresaCidade;
        this.empresaEndereco = empresaEndereco;
        isUpdating = false;
        this.editText.addTextChangedListener(this);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        buscarCEP();
    }

    @Override
    public void afterTextChanged(Editable s) {

        if (isUpdating) {
            isUpdating = false;
            return;
        }

        String cep = s.toString();
        String cepWithoutHyphen = cep.replaceAll("-", "");

        if (cepWithoutHyphen.length() > 5) {
            cep = cepWithoutHyphen.substring(0, 5) + "-" + cepWithoutHyphen.substring(5);
            isUpdating = true;
            editText.setText(cep);
            editText.setSelection(cep.length());
        }
    }

    public void buscarCEP() {
        String cep = editText.getText().toString();
        if (!cep.isEmpty()) {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            new BuscarCEPTask().execute(url);
        }
    }

    private class BuscarCEPTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String logradouro = jsonObject.getString("logradouro");
                    String bairro = jsonObject.getString("bairro");
                    String cidade = jsonObject.getString("localidade");
                    String estado = jsonObject.getString("uf");
                    empresaUF.setText(estado);
                    empresaCidade.setText(cidade);
                    empresaEndereco.setText(bairro + " " + logradouro);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }
        }
    }
}
