package com.example.cursoetrabalho.model;

public class Trabalho {
    String txtCargo, txtEmpresa, txtRegiao;

    public Trabalho() {
    }

    public Trabalho(String txtCargo, String txtEmpresa, String txtRegiao) {
        this.txtCargo = txtCargo;
        this.txtEmpresa = txtEmpresa;
        this.txtRegiao = txtRegiao;
    }

    public String getTxtCargo() {
        return txtCargo;
    }

    public void setTxtCargo(String txtCargo) {
        this.txtCargo = txtCargo;
    }

    public String getTxtEmpresa() {
        return txtEmpresa;
    }

    public void setTxtEmpresa(String txtEmpresa) {
        this.txtEmpresa = txtEmpresa;
    }

    public String getTxtRegiao() {
        return txtRegiao;
    }

    public void setTxtRegiao(String txtRegiao) {
        this.txtRegiao = txtRegiao;
    }

}
