package tech.pedrolima.DoacaoDePlantas.Dto;

import org.springframework.stereotype.Component;

public class ConfirmarDadosDTO {

    private String nomePlanta;
    private String numWhatsApp;
    private String email;
    private String endRetirada;
    private String obs;

    public String getNomePlanta() {
        return nomePlanta;
    }

    public void setNomePlanta(String nomePlanta) {
        this.nomePlanta = nomePlanta;
    }

    public String getNumWhatsApp() {
        return numWhatsApp;
    }

    public void setNumWhatsApp(String numWhatsApp) {
        this.numWhatsApp = numWhatsApp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndRetirada() {
        return endRetirada;
    }

    public void setEndRetirada(String endRetirada) {
        this.endRetirada = endRetirada;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
