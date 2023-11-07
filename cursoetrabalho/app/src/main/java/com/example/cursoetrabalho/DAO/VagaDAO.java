package com.example.cursoetrabalho.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cursoetrabalho.DTO.EmpresaDTO;
import com.example.cursoetrabalho.DTO.VagaDTO;
import com.example.cursoetrabalho.adapter.PostagemTrabalhoVerticalAdapter;
import com.example.cursoetrabalho.model.Trabalho;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VagaDAO {
    private Context mContext;
    private static final String HTTP = "http://";
    private static final String IP = "10.3.18.32";
    private static final String BASE_URL = HTTP + IP;
    private static final String INSERT_URL = BASE_URL + "/conexao/cadastroVaga.php";
    private static final String LIST_URL = BASE_URL + "/conexao/listarVaga.php";

    public interface OnCategoriaVagaListener {
        void OnCategoriaVagaListener(String cidade, String prazo, String cargo, String empresa, String descricao, String qtdVaga, String imgVaga, String vagaUrl);
    }

    public VagaDAO(Context context) {
        this.mContext = context;
    }
    public void inserirDado (VagaDTO vagaDTO) {
        try{
            String categoria = vagaDTO.getVagaCategoria() ;
            String nomeEmpresa = vagaDTO.getVagaNomeEmpresa();
            String vagaQtd = vagaDTO.getVagaQtd();
            String vagaCargo = vagaDTO.getVagaCargo();
            String vagaDataInicial = vagaDTO.getVagaDataInicial();
            String vagaDataFinal = vagaDTO.getVagaDataFinal();
            String vagaDesc = vagaDTO.getVagaDesc();
            String vagaImg = vagaDTO.getVagaIMG();
            String vagaUrl = vagaDTO.getVagaUrl();

            if (!categoria.isEmpty() && !nomeEmpresa.isEmpty() && !vagaQtd.isEmpty() &&
                    !vagaCargo.isEmpty() && !vagaDataInicial.isEmpty() && !vagaDataFinal.isEmpty() &&
                    !vagaDesc.isEmpty() && !vagaImg.isEmpty()&& !vagaUrl.isEmpty()) {
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
                        data.put("vagaCategoria", categoria);
                        data.put("vagaEmpresaNome", nomeEmpresa);
                        data.put("vagaQuantidade", vagaQtd);
                        data.put("vagaCargo", vagaCargo);
                        data.put("vagaDataVaga", vagaDataInicial);
                        data.put("vagaDataPrazo", vagaDataFinal);
                        data.put("vagaDescricao", vagaDesc);
                        data.put("vagaNomeIMG", vagaCargo.trim().replaceAll("\\s+", " "));
                        data.put("vagaImg", vagaImg);
                        data.put("vagaUrl", vagaUrl);
                        data.put("IP", IP);

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
    public PostagemTrabalhoVerticalAdapter imprimirDados(List<Trabalho> trabalhos, RecyclerView recyclerPostagem, String alternativaTrabalho, String categoriaTrabalho) {
        PostagemTrabalhoVerticalAdapter adapter1 = null;
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LIST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String cidade = jsonObject.getString("empresa_cidade") + "/" + jsonObject.getString("empresa_UF") + "/" + jsonObject.getString("empresa_endereco");
                                    trabalhos.add(new Trabalho(
                                            jsonObject.getString("vaga_cargo"),
                                            jsonObject.getString("empresa_nome"),
                                            cidade,
                                            jsonObject.getString("vaga_img")));
                                }
                                PostagemTrabalhoVerticalAdapter adapter1 = new PostagemTrabalhoVerticalAdapter(trabalhos, mContext);
                                recyclerPostagem.setAdapter(adapter1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.networkResponse == null) {
                                Log.d("listaTrabalho", "Sem conexão com a internet");
                            } else {
                                Log.d("listaTrabalho", error.toString().trim());
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("alternativaTrabalho", alternativaTrabalho);
                    params.put("categoriaTrabalho", categoriaTrabalho);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(stringRequest);
            return adapter1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void imprimirDadosTrabalho(String alternativaTrabalho, String nomeCargo, VagaDAO.OnCategoriaVagaListener listener) {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LIST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject vaga = jsonArray.getJSONObject(i);
                                    String cidade = vaga.getString("empresa_cidade") + "/" + vaga.getString("empresa_UF");
                                    String prazo = vaga.getString("vaga_data_vaga") + " até " + vaga.getString("vaga_data_prazo");
                                    String cargo = vaga.getString("vaga_cargo");
                                    String empresa = vaga.getString("empresa_nome");
                                    String descricao = vaga.getString("vaga_descricao");
                                    String qtdVaga = vaga.getString("vaga_quantidade");
                                    String vagaIMG = vaga.getString("vaga_img");
                                    String vagaUrl = vaga.getString("vaga_url");

                                    listener.OnCategoriaVagaListener(cidade, prazo, cargo, empresa, descricao, qtdVaga, vagaIMG, vagaUrl);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.networkResponse == null) {
                                Log.d("listaTrabalho", "Sem conexão com a internet");
                            } else {
                                Log.d("listaTrabalho", error.toString().trim());
                            }
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("alternativaTrabalho", alternativaTrabalho);
                    params.put("vagaTrabalho", nomeCargo);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
