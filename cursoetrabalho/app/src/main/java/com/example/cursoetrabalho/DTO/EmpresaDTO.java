package com.example.cursoetrabalho.DTO;

public class EmpresaDTO {
    private String empresaCnpj;
    private String empresaNome;
    private String empresaCep;
    private String empresaUf;
    private String empresaCidade;
    private String empresaEndereco;
    private String empresaEmail;
    private String empresaTelefone;
    public EmpresaDTO() {
    }
    public EmpresaDTO(String empresaCnpj, String empresaNome, String empresaCep, String empresaUf, String empresaCidade, String empresaEndereco, String empresaEmail, String empresaTelefone) {

        this.empresaCnpj = empresaCnpj.trim();
        this.empresaNome = empresaNome.trim();
        this.empresaCep = empresaCep.trim();
        this.empresaUf = empresaUf.trim();
        this.empresaCidade = empresaCidade.trim();
        this.empresaEndereco = empresaEndereco.trim();
        this.empresaEmail = empresaEmail.trim();
        this.empresaTelefone = empresaTelefone.trim();
    }

    public String getEmpresaCnpj() {
        return empresaCnpj;
    }

    public void setEmpresaCnpj(String empresaCnpj) {
        this.empresaCnpj = empresaCnpj;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getEmpresaCep() {
        return empresaCep;
    }

    public void setEmpresaCep(String empresaCep) {
        this.empresaCep = empresaCep;
    }

    public String getEmpresaUf() {
        return empresaUf;
    }

    public void setEmpresaUf(String empresaUf) {
        this.empresaUf = empresaUf;
    }

    public String getEmpresaCidade() {
        return empresaCidade;
    }

    public void setEmpresaCidade(String empresaCidade) {
        this.empresaCidade = empresaCidade;
    }

    public String getEmpresaEndereco() {
        return empresaEndereco;
    }

    public void setEmpresaEndereco(String empresaEndereco) {
        this.empresaEndereco = empresaEndereco;
    }

    public String getEmpresaEmail() {
        return empresaEmail;
    }

    public void setEmpresaEmail(String empresaEmail) {
        this.empresaEmail = empresaEmail;
    }

    public String getEmpresaTelefone() {
        return empresaTelefone;
    }

    public void setEmpresaTelefone(String empresaTelefone) {
        this.empresaTelefone = empresaTelefone;
    }
}
