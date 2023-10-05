package com.example.cursoetrabalho.DTO;

import androidx.annotation.NonNull;

public class CursoDTO {
    public String[] categorias = { "Programação", "Marketing Digital", "Informática",
            "Desenvolvimento Web", "Inglês como Segunda Língua (ESL)", "Design Gráfico", "Machine Learning",
            "Fotografia", "Finanças Pessoais", "Gerenciamento de Projetos", "Ciência de Dados",
            "Redes de Computadores", "Marketing de Mídias Sociais",
            "Introdução à Inteligência Artificial (IA)", "Psicologia"};

    public String cursoCategoria;
    public String cursoNome;
    public String cursoFornecedor;
    public String cursoQtdHoras;
    public String cursoDescricao;
    public String cursoPresencial;
    public String cursoUrl;
    public String cursoQtdGostei;
    public String cursoQtdVisualizacao;

    public CursoDTO() {
    }

    public CursoDTO(String cursoCategoria, String cursoNome, String cursoFornecedor, String cursoQtdHoras, String cursoDescricao, String cursoPresencial, String cursoUrl, String cursoQtdGostei, String cursoQtdVisualizacao) {
        this.cursoCategoria = cursoCategoria;
        this.cursoNome = cursoNome;
        this.cursoFornecedor = cursoFornecedor;
        this.cursoQtdHoras = cursoQtdHoras;
        this.cursoDescricao = cursoDescricao;
        this.cursoPresencial = cursoPresencial;
        this.cursoUrl = cursoUrl;
        this.cursoQtdGostei = cursoQtdGostei;
        this.cursoQtdVisualizacao = cursoQtdVisualizacao;
    }

    public CursoDTO(String cursoCategoria, String cursoNome, String cursoFornecedor, String cursoQtdHoras, String cursoDescricao, String cursoPresencial, String cursoUrl) {
        this.cursoCategoria = cursoCategoria;
        this.cursoNome = cursoNome;
        this.cursoFornecedor = cursoFornecedor;
        this.cursoQtdHoras = cursoQtdHoras;
        this.cursoDescricao = cursoDescricao;
        this.cursoPresencial = cursoPresencial;
        this.cursoUrl = cursoUrl;
    }

    public String getCursoCategoria() {
        return cursoCategoria;
    }

    public void setCursoCategoria(String cursoCategoria) {
        this.cursoCategoria = cursoCategoria;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public String getCursoFornecedor() {
        return cursoFornecedor;
    }

    public void setCursoFornecedor(String cursoFornecedor) {
        this.cursoFornecedor = cursoFornecedor;
    }

    public String getCursoQtdHoras() {
        return cursoQtdHoras;
    }

    public void setCursoQtdHoras(String cursoQtdHoras) {
        this.cursoQtdHoras = cursoQtdHoras;
    }

    public String getCursoDescricao() {
        return cursoDescricao;
    }

    public void setCursoDescricao(String cursoDescricao) {
        this.cursoDescricao = cursoDescricao;
    }

    public String getCursoPresencial() {
        return cursoPresencial;
    }

    public void setCursoPresencial(String cursoPresencial) {
        this.cursoPresencial = cursoPresencial;
    }

    public String getCursoUrl() {
        return cursoUrl;
    }

    public void setCursoUrl(String cursoUrl) {
        this.cursoUrl = cursoUrl;
    }

    public String getCursoQtdGostei() {
        return cursoQtdGostei;
    }

    public void setCursoQtdGostei(String cursoQtdGostei) {
        this.cursoQtdGostei = cursoQtdGostei;
    }

    public String getCursoQtdVisualizacao() {
        return cursoQtdVisualizacao;
    }

    public void setCursoQtdVisualizacao(String cursoQtdVisualizacao) {
        this.cursoQtdVisualizacao = cursoQtdVisualizacao;
    }
}
