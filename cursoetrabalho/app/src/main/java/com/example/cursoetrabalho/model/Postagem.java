package com.example.cursoetrabalho.model;

public class Postagem {

    private String txtNomeCurso, txtFornecedor, txtQtdVisualizacao, txtQtdGostei, txtIsPresencial;
    private int imgCurso;

    public Postagem(String txtNomeCurso, String txtFornecedor, String txtQtdVisualizacao, String txtQtdGostei) {

        this.txtNomeCurso = txtNomeCurso;
        this.txtFornecedor = txtFornecedor;
        this.txtQtdVisualizacao = txtQtdVisualizacao;
        this.txtQtdGostei = txtQtdGostei;

    }

    public Postagem(String txtNomeCurso, String txtFornecedor, String txtQtdVisualizacao, String txtQtdGostei, String txtIsPresencial) {
        this.txtNomeCurso = txtNomeCurso;
        this.txtFornecedor = txtFornecedor;
        this.txtQtdVisualizacao = txtQtdVisualizacao;
        this.txtQtdGostei = txtQtdGostei;
        this.txtIsPresencial = txtIsPresencial;
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

    public int getImgCurso() {
        return imgCurso;
    }

    public void setImgCurso(int imgCurso) {
        this.imgCurso = imgCurso;
    }



}
