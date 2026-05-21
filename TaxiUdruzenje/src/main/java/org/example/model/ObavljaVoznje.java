package org.example.model;

import java.util.Objects;

public class ObavljaVoznje {

    private Integer vozacMbrZ;
    private String tarifnaGrupaNazTg;

    public ObavljaVoznje() {
        super();
    }

    public ObavljaVoznje(Integer vozacMbrZ, String tarifnaGrupaNazTg) {
        this.vozacMbrZ = vozacMbrZ;
        this.tarifnaGrupaNazTg = tarifnaGrupaNazTg;
    }

    public Integer getVozacMbrZ() {
        return vozacMbrZ;
    }

    public void setVozacMbrZ(Integer vozacMbrZ) {
        this.vozacMbrZ = vozacMbrZ;
    }

    public String getTarifnaGrupaNazTg() {
        return tarifnaGrupaNazTg;
    }

    public void setTarifnaGrupaNazTg(String tarifnaGrupaNazTg) {
        this.tarifnaGrupaNazTg = tarifnaGrupaNazTg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObavljaVoznje)) return false;
        ObavljaVoznje that = (ObavljaVoznje) o;
        return Objects.equals(vozacMbrZ, that.vozacMbrZ) &&
                Objects.equals(tarifnaGrupaNazTg, that.tarifnaGrupaNazTg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vozacMbrZ, tarifnaGrupaNazTg);
    }
}

