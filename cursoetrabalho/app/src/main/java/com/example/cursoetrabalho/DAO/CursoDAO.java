package com.example.cursoetrabalho.DAO;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cursoetrabalho.DTO.CursoDTO;
import com.example.cursoetrabalho.adapter.PostagemCursoHorizontalAdapter;
import com.example.cursoetrabalho.adapter.PostagemCursoVerticalAdapter;
import com.example.cursoetrabalho.model.Postagem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CursoDAO {
    private Context mContext;
    private static final String HTTP = "http://";
    private static final String IP = "192.168.199.225";
    private static final String BASE_URL = HTTP + IP;
    private static final String INSERT_URL = BASE_URL + "/conexao/cadastroCurso.php";
    private static final String LIST_URL = BASE_URL + "/conexao/listarCurso.php";

    public CursoDAO() {
    }

    public CursoDAO(Context context) {
        this.mContext = context;
    }
    public interface OnCategoriaCursoListener {
        void onCategoriaCursoObtida(String categoriaCurso,String cursoNome,String cursoFornecedor,String cursoQtdHoras,
                                    String cursoDescricao,String cursoPresencial,String cursoUrl,String cursoQtdView, String cursoImg);
    }
    public void inserirDado (CursoDTO cursoDTO) {

        try{
            String cursoCategoria = cursoDTO.getCursoCategoria() ;
            String cursoNome = cursoDTO.getCursoNome();
            String cursoFornecedor = cursoDTO.getCursoFornecedor();
            String cursoQtdHoras = cursoDTO.getCursoQtdHoras();
            String cursoDescricao = cursoDTO.getCursoDescricao();
            String cursoPresencial = cursoDTO.getCursoPresencial();
            String cursoUrl = cursoDTO.getCursoUrl();
            String cursoImg = cursoDTO.getImgLogo();

            if (!cursoCategoria.isEmpty() && !cursoNome.isEmpty() && !cursoFornecedor.isEmpty() &&
                    !cursoQtdHoras.isEmpty() && !cursoDescricao.isEmpty() && !cursoUrl.isEmpty() && !cursoPresencial.isEmpty()) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, INSERT_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("res", response);
                        if ("sucesso".equals(response.trim())) {
                            Toast.makeText(mContext, "Registro concluido!", Toast.LENGTH_SHORT).show();
                        } else if ("erro".equals(response.trim())) {
                            Toast.makeText(mContext, "Erro ao inserir registro:", Toast.LENGTH_SHORT).show();
                            Log.d("Erro adicionar curso", response);
                        }  else {
                            Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                            Log.d("Erro adicionar curso", response);
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
                        data.put("cursoDescricao", cursoDescricao);
                        data.put("cursoCategoria", cursoCategoria);
                        data.put("cursoFornecedor", cursoFornecedor);
                        data.put("cursoNome", cursoNome);
                        data.put("cursoUrl", cursoUrl);
                        data.put("cursoPresencial", cursoPresencial);
                        data.put("cursoQtdHrs", cursoQtdHoras);
                        data.put("cursoImg", cursoImg);
                        data.put("cursoNomeIMG", cursoNome.trim().replaceAll("\\s+", " "));
                        data.put("URL" , IP);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(mContext, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){

        }
    }

    public PostagemCursoHorizontalAdapter imprimirDado(List<Postagem> postagens, RecyclerView recyclerPostagem, String alternativaCurso, String categoriaCurso){
        PostagemCursoHorizontalAdapter adapter = null;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                postagens.add(new Postagem(
                                        jsonObject.getString("curso_nome"),
                                        jsonObject.getString("curso_fornecedor"),
                                        jsonObject.getString("curso_visualizacao"),
                                        jsonObject.getString("curso_img")));
                            }
                            PostagemCursoHorizontalAdapter adapter = new PostagemCursoHorizontalAdapter(mContext ,postagens);
                            recyclerPostagem.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            Log.d("listaCursos", "sem conexao");

                        } else {
                            Log.d("listaCursos", "error.toString().trim()");
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Log.d("PostagemCat", "aqui foi");
                params.put("alternativaCurso", alternativaCurso);
                params.put("categoriaCurso", categoriaCurso);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
        return adapter;
    }
    public PostagemCursoVerticalAdapter imprimirDadoEspecifica(List<Postagem> postagens, RecyclerView recyclerPostagem, String alternativaCurso, String categoriaCurso){
        PostagemCursoVerticalAdapter adapter = null;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String presencial = jsonObject.getString("curso_presencial").equals("S") ? "Curso presencial" : "Não presencial";
                                postagens.add(new Postagem(
                                        jsonObject.getString("curso_nome"),
                                        jsonObject.getString("curso_fornecedor"),
                                        jsonObject.getString("curso_visualizacao"),
                                        presencial,
                                        jsonObject.getString("curso_img")));
                            }
                            PostagemCursoVerticalAdapter adapter = new PostagemCursoVerticalAdapter(mContext ,postagens);
                            recyclerPostagem.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            Log.d("listaCursos", "sem conexao");
                        } else {
                            Log.d("listaCursos", "error.toString().trim()");
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("alternativaCurso", alternativaCurso);
                params.put("categoriaCurso", categoriaCurso);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
        return adapter;
    }
    public void imprimirDados(String alternativaCurso, String nomeCurso, OnCategoriaCursoListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject curso = jsonArray.getJSONObject(i);

                                String cursoNome = curso.getString("curso_nome");
                                String cursoFornecedor = curso.getString("curso_fornecedor");
                                String cursoQtdHoras = curso.getString("curso_qtd_hrs");
                                String cursoDescricao = curso.getString("curso_descricao");
                                String cursoPresencial;
                                if(curso.getString("curso_presencial").equals("S")){
                                    cursoPresencial="Curso presencial";
                                } else {
                                    cursoPresencial="Não presencial";
                                }
                                String cursoUrl = curso.getString("curso_url");
                                String cursoQtdView = curso.getString("curso_visualizacao");
                                String categoriaCurso = curso.getString("curso_categoria");
                                String cursoImg = curso.getString("curso_img");

                                listener.onCategoriaCursoObtida(categoriaCurso, cursoNome, cursoFornecedor, cursoQtdHoras,
                                        cursoDescricao, cursoPresencial, cursoUrl, cursoQtdView, cursoImg);

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
                            Log.d("listaCursos", "sem conexao");
                        } else {
                            Log.d("listaCursos", error.toString().trim());
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("alternativaCurso", alternativaCurso);
                params.put("nomeCurso", nomeCurso);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
    }
}
