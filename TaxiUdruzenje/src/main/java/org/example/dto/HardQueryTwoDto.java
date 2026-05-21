package org.example.dto;

import java.math.BigDecimal;

public class HardQueryTwoDto {

    private Integer vozacId;
    private String imePrezime;
    private String tarifa;
    private Integer brojVoznji;
    private BigDecimal prosecnaCena;

    public HardQueryTwoDto(Integer vozacId, String imePrezime, String tarifa, Integer brojVoznji, BigDecimal prosecnaCena) {
        this.vozacId = vozacId;
        this.imePrezime = imePrezime;
        this.tarifa = tarifa;
        this.brojVoznji = brojVoznji;
        this.prosecnaCena = prosecnaCena;
    }

    public Integer getVozacId() {
        return vozacId;
    }

    public void setVozacId(Integer vozacId) {
        this.vozacId = vozacId;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public Integer getBrojVoznji() {
        return brojVoznji;
    }

    public void setBrojVoznji(Integer brojVoznji) {
        this.brojVoznji = brojVoznji;
    }

    public BigDecimal getProsecnaCena() {
        return prosecnaCena;
    }

    public void setProsecnaCena(BigDecimal prosecnaCena) {
        this.prosecnaCena = prosecnaCena;
    }
}
