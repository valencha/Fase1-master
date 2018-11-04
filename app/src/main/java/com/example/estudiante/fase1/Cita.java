package com.example.estudiante.fase1;

public class Cita {
    String citaId;
    String citaTitulo;
    String importancia;

    public Cita (){

    }

    public Cita(String citaId, String citaTitulo, String importancia) {
        this.citaId = citaId;
        this.citaTitulo = citaTitulo;
        this.importancia = importancia;
    }

    public String getCitaId() {
        return citaId;
    }

    public String getCitaTitulo() {
        return citaTitulo;
    }

    public String getImportancia() {
        return importancia;
    }
}
