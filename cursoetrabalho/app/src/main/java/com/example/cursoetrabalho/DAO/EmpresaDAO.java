package com.example.cursoetrabalho.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cursoetrabalho.DTO.EmpresaDTO;
import com.example.cursoetrabalho.adapter.PostagemCursoVerticalAdapter;
import com.example.cursoetrabalho.adapter.PostagemTrabalhoVerticalAdapter;
import com.example.cursoetrabalho.model.Postagem;
import com.example.cursoetrabalho.model.Trabalho;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaDAO {
    private Context mContext;
    private static final String BASE_URL = "http://192.168.1.4/";
    private static final String INSERT_URL = BASE_URL + "/conexao/cadastroEmpresa.php";

    public EmpresaDAO(Context context) {
        this.mContext = context;
    }
    public void inserirDado (EmpresaDTO empresaDTO) {
        try{
            String empresaCNPJ = empresaDTO.getEmpresaCnpj() ;
            String empresaNome = empresaDTO.getEmpresaNome();
            String empresaCEP = empresaDTO.getEmpresaCep();
            String empresaUF = empresaDTO.getEmpresaUf();
            String empresaCidade = empresaDTO.getEmpresaCidade();
            String empresaEndereco = empresaDTO.getEmpresaEndereco();
            String empresaEmail = empresaDTO.getEmpresaEmail();
            String empresaTelefone = empresaDTO.getEmpresaTelefone();

            if (!empresaCNPJ.isEmpty() && !empresaNome.isEmpty() && !empresaCEP.isEmpty() &&
                    !empresaUF.isEmpty() && !empresaCidade.isEmpty() && !empresaEndereco.isEmpty() && !empresaEmail.isEmpty() &&
                    !empresaTelefone.isEmpty()) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, INSERT_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("res", response);
                        if ("sucesso".equals(response.trim())) {
                            Toast.makeText(mContext, "Registro concluido!", Toast.LENGTH_SHORT).show();
                        } else if ("erro".equals(response.trim())) {
                            Toast.makeText(mContext, "Erro ao inserir registro:", Toast.LENGTH_SHORT).show();
                        }  else {
                            Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            Toast.makeText(mContext, "Sem conectividade com a internet", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                            data.put("empresaCNPJ", empresaCNPJ);
                            data.put("empresaNome", empresaNome);
                            data.put("empresaCEP", empresaCEP);
                            data.put("empresaUF", empresaUF);
                            data.put("empresaCidade", empresaCidade);
                            data.put("empresaEndereco", empresaEndereco);
                            data.put("empresaEmail", empresaEmail);
                            data.put("empresaTelefone", empresaTelefone);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(mContext, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
