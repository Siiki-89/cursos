package com.example.cursoetrabalho.DTO;

public class VagaDTO {

    public String[] categoriasEmprego = {"Tecnologia da Informação", "Saúde e Medicina", "Finanças e Contabilidade",
            "Marketing e Publicidade", "Educação e Ensino", "Engenharia", "Vendas e Atendimento ao Cliente",
            "Recursos Humanos", "Artes e Entretenimento", "Gestão de Projetos", "Consultoria", "Setor Alimentício",
            "Mídia e Comunicação", "Serviços Jurídicos", "Ciência e Pesquisa"};
    String vagaCategoria;
    String vagaNomeEmpresa;
    String vagaQtd;
    String vagaCargo;
    String vagaDataInicial;
    String vagaDataFinal;
    String vagaDesc;
    String vagaIMG;
    String vagaUrl;

    public VagaDTO() {
    }

    public VagaDTO(String vagaCategoria, String vagaNomeEmpresa, String vagaQtd, String vagaCargo, String vagaDataInicial, String vagaDataFinal, String vagaDesc, String vagaIMG, String vagaUrl) {
        this.vagaCategoria = vagaCategoria;
        this.vagaNomeEmpresa = vagaNomeEmpresa;
        this.vagaQtd = vagaQtd;
        this.vagaCargo = vagaCargo;
        this.vagaDataInicial = vagaDataInicial;
        this.vagaDataFinal = vagaDataFinal;
        this.vagaDesc = vagaDesc;
        this.vagaIMG = vagaIMG;
        this.vagaUrl = vagaUrl;
    }

    public String getVagaCategoria() {
        return vagaCategoria;
    }

    public void setVagaCategoria(String vagaCategoria) {
        this.vagaCategoria = vagaCategoria;
    }

    public String getVagaNomeEmpresa() {
        return vagaNomeEmpresa;
    }

    public void setVagaNomeEmpresa(String vagaNomeEmpresa) {
        this.vagaNomeEmpresa = vagaNomeEmpresa;
    }

    public String getVagaQtd() {
        return vagaQtd;
    }

    public void setVagaQtd(String vagaQtd) {
        this.vagaQtd = vagaQtd;
    }

    public String getVagaCargo() {
        return vagaCargo;
    }

    public void setVagaCargo(String vagaCargo) {
        this.vagaCargo = vagaCargo;
    }

    public String getVagaDataInicial() {
        return vagaDataInicial;
    }

    public void setVagaDataInicial(String vagaDataInicial) {
        this.vagaDataInicial = vagaDataInicial;
    }

    public String getVagaDataFinal() {
        return vagaDataFinal;
    }

    public void setVagaDataFinal(String vagaDataFinal) {
        this.vagaDataFinal = vagaDataFinal;
    }

    public String getVagaDesc() {
        return vagaDesc;
    }

    public void setVagaDesc(String vagaDesc) {
        this.vagaDesc = vagaDesc;
    }

    public String getVagaIMG() {
        return vagaIMG;
    }

    public void setVagaIMG(String vagaIMG) {
        this.vagaIMG = vagaIMG;
    }

    public String getVagaUrl() {
        return vagaUrl;
    }

    public void setVagaUrl(String vagaUrl) {
        this.vagaUrl = vagaUrl;
    }
}
