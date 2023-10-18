package com.example.cursoetrabalho.model;

import android.widget.ImageView;

public class Postagem {

    private String txtNomeCurso, txtFornecedor, txtQtdVisualizacao, txtQtdGostei, txtIsPresencial;
    private String imgCurso;

    public Postagem(String txtNomeCurso, String txtFornecedor, String txtQtdVisualizacao, String txtQtdGostei, String imgCurso) {

        this.txtNomeCurso = txtNomeCurso;
        this.txtFornecedor = txtFornecedor;
        this.txtQtdVisualizacao = txtQtdVisualizacao;
        this.txtQtdGostei = txtQtdGostei;
        this.imgCurso = imgCurso;

    }

    public Postagem(String txtNomeCurso, String txtFornecedor, String txtQtdVisualizacao, String txtQtdGostei, String txtIsPresencial, String imgCurso) {
        this.txtNomeCurso = txtNomeCurso;
        this.txtFornecedor = txtFornecedor;
        this.txtQtdVisualizacao = txtQtdVisualizacao;
        this.txtQtdGostei = txtQtdGostei;
        this.txtIsPresencial = txtIsPresencial;
        this.imgCurso = imgCurso;
    }

    public Postagem() {
    }

    public String getTxtIsPresencial() {
        return txtIsPresencial;
    }

    public void setTxtIsPresencial(String txtIsPresencial) {
        this.txtIsPresencial = txtIsPresencial;
    }

    public String getTxtNomeCurso() {
        return txtNomeCurso;
    }

    public void setTxtNomeCurso(String txtNomeCurso) {
        this.txtNomeCurso = txtNomeCurso;
    }

    public String getTxtFornecedor() {
        return txtFornecedor;
    }

    public void setTxtFornecedor(String txtFornecedor) {
        this.txtFornecedor = txtFornecedor;
    }

    public String getTxtQtdVisualizacao() {
        return txtQtdVisualizacao;
    }

    public void setTxtQtdVisualizacao(String txtQtdVisualizacao) {
        this.txtQtdVisualizacao = txtQtdVisualizacao;
    }

    public String getTxtQtdGostei() {
        return txtQtdGostei;
    }

    public void setTxtQtdGostei(String txtQtdGostei) {
        this.txtQtdGostei = txtQtdGostei;
    }

    public String getImgCurso() {
        return imgCurso;
    }

    public void setImgCurso(String imgCurso) {
        this.imgCurso = imgCurso;
    }



}
