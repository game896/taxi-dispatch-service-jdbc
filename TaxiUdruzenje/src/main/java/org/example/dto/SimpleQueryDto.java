package org.example.dto;

import java.math.BigDecimal;

public class SimpleQueryDto {

    private Integer mbrZ;
    private String imeZ;
    private String preZ;
    private Integer brojVoznji;
    private BigDecimal ukupnaZarada;

    public SimpleQueryDto(Integer mbrZ, String imeZ, String preZ, Integer brojVoznji, BigDecimal ukupnaZarada) {
        this.mbrZ = mbrZ;
        this.imeZ = imeZ;
        this.preZ = preZ;
        this.brojVoznji = brojVoznji;
        this.ukupnaZarada = ukupnaZarada;
    }

    public Integer getMbrZ() {
        return mbrZ;
    }

    public void setMbrZ(Integer mbrZ) {
        this.mbrZ = mbrZ;
    }

    public String getImeZ() {
        return imeZ;
    }

    public void setImeZ(String imeZ) {
        this.imeZ = imeZ;
    }

    public String getPreZ() {
        return preZ;
    }

    public void setPreZ(String preZ) {
        this.preZ = preZ;
    }

    public Integer getBrojVoznji() {
        return brojVoznji;
    }

    public void setBrojVoznji(Integer brojVoznji) {
        this.brojVoznji = brojVoznji;
    }

    public BigDecimal getUkupnaZarada() {
        return ukupnaZarada;
    }

    public void setUkupnaZarada(BigDecimal ukupnaZarada) {
        this.ukupnaZarada = ukupnaZarada;
    }
}
