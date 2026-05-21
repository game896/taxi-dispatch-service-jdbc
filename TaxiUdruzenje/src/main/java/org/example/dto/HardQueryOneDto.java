package org.example.dto;

import java.math.BigDecimal;

public class HardQueryOneDto {

    private String nazivTg;
    private Integer brojVoznji;
    private BigDecimal prihod;

    public HardQueryOneDto(String nazivTg, Integer brojVoznji, BigDecimal prihod) {
        this.nazivTg = nazivTg;
        this.brojVoznji = brojVoznji;
        this.prihod = prihod;
    }

    public String getNazivTg() {
        return nazivTg;
    }

    public void setNazivTg(String nazivTg) {
        this.nazivTg = nazivTg;
    }

    public Integer getBrojVoznji() {
        return brojVoznji;
    }

    public void setBrojVoznji(Integer brojVoznji) {
        this.brojVoznji = brojVoznji;
    }

    public BigDecimal getPrihod() {
        return prihod;
    }

    public void setPrihod(BigDecimal prihod) {
        this.prihod = prihod;
    }
}
