package com.example.selecareoptionalandlogin;

import java.math.BigDecimal;

public class Elev
{
    private String email;
    private String optional;

    private String nume;

    private String parola;

    private String prenume;
    private BigDecimal id;


    public Elev(String email, String optional,String nume,String prenume,String parola) {
        this.email = email;
        this.optional = optional;
        this.id=id;
        this.nume=nume;
        this.prenume=prenume;
        this.parola=parola;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Elev() {
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }
}
