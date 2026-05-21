package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class Vozac extends Zaposleni {

    private Integer bDzr;
    private String brVD;
    private LocalDate datIdVD;

    public Vozac() {
        super();
    }

    public Vozac(Integer mbrZ, String imeZ, String preZ, Double pltZ, String katZ,
                 Integer bDzr, String brVD, LocalDate datIdVD) {
        super(mbrZ, imeZ, preZ, pltZ, katZ);
        this.bDzr = bDzr;
        this.brVD = brVD;
        this.datIdVD = datIdVD;
    }

    public Integer getBDzr() {
        return bDzr;
    }

    public void setBDzr(Integer bDzr) {
        this.bDzr = bDzr;
    }

    public String getBrVD() {
        return brVD;
    }

    public void setBrVD(String brVD) {
        this.brVD = brVD;
    }

    public LocalDate getDatIdVD() {
        return datIdVD;
    }

    public void setDatIdVD(LocalDate datIdVD) {
        this.datIdVD = datIdVD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vozac)) return false;
        Vozac vozac = (Vozac) o;
        return Objects.equals(getMbrZ(), vozac.getMbrZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMbrZ());
    }
}

