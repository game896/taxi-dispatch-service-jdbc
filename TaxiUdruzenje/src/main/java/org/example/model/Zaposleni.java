package org.example.model;

import java.util.Objects;

public class Zaposleni {

    private Integer mbrZ;
    private String imeZ;
    private String preZ;
    private Double pltZ;
    private String katZ;

    public Zaposleni() {
        super();
    }

    public Zaposleni(Integer mbrZ, String imeZ, String preZ, Double pltZ, String katZ) {
        this.mbrZ = mbrZ;
        this.imeZ = imeZ;
        this.preZ = preZ;
        this.pltZ = pltZ;
        this.katZ = katZ;
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

    public Double getPltZ() {
        return pltZ;
    }

    public void setPltZ(Double pltZ) {
        this.pltZ = pltZ;
    }

    public String getKatZ() {
        return katZ;
    }

    public void setKatZ(String katZ) {
        this.katZ = katZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zaposleni)) return false;
        Zaposleni that = (Zaposleni) o;
        return Objects.equals(mbrZ, that.mbrZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrZ);
    }
}
