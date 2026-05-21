package org.example.model;

import java.util.Objects;

public class TarifnaGrupa {

    private String nazTg;
    private String opisTG;

    public TarifnaGrupa() {
        super();
    }

    public TarifnaGrupa(String nazTg, String opisTG) {
        this.nazTg = nazTg;
        this.opisTG = opisTG;
    }

    public String getNazTg() {
        return nazTg;
    }

    public void setNazTg(String nazTg) {
        this.nazTg = nazTg;
    }

    public String getOpisTG() {
        return opisTG;
    }

    public void setOpisTG(String opisTG) {
        this.opisTG = opisTG;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TarifnaGrupa)) return false;
        TarifnaGrupa that = (TarifnaGrupa) o;
        return Objects.equals(nazTg, that.nazTg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazTg);
    }
}
