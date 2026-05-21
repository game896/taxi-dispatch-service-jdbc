package org.example.model;

import java.util.Objects;

public class NarucujeVoznju {

    private Integer klijentMbrKl;
    private String tarifnaGrupaNazTg;
    private Integer vozacMbrZ;
    private String ovNazTg;
    private Double cenVoz;
    private String pocDest;
    private String zavDest;

    public NarucujeVoznju() {
        super();
    }

    public NarucujeVoznju(Integer klijentMbrKl, String tarifnaGrupaNazTg,
                          Integer vozacMbrZ, String ovNazTg,
                          Double cenVoz, String pocDest, String zavDest) {
        this.klijentMbrKl = klijentMbrKl;
        this.tarifnaGrupaNazTg = tarifnaGrupaNazTg;
        this.vozacMbrZ = vozacMbrZ;
        this.ovNazTg = ovNazTg;
        this.cenVoz = cenVoz;
        this.pocDest = pocDest;
        this.zavDest = zavDest;
    }

    public Integer getKlijentMbrKl() {
        return klijentMbrKl;
    }

    public void setKlijentMbrKl(Integer klijentMbrKl) {
        this.klijentMbrKl = klijentMbrKl;
    }

    public String getTarifnaGrupaNazTg() {
        return tarifnaGrupaNazTg;
    }

    public void setTarifnaGrupaNazTg(String tarifnaGrupaNazTg) {
        this.tarifnaGrupaNazTg = tarifnaGrupaNazTg;
    }

    public Integer getVozacMbrZ() {
        return vozacMbrZ;
    }

    public void setVozacMbrZ(Integer vozacMbrZ) {
        this.vozacMbrZ = vozacMbrZ;
    }

    public String getOvNazTg() {
        return ovNazTg;
    }

    public void setOvNazTg(String ovNazTg) {
        this.ovNazTg = ovNazTg;
    }

    public Double getCenVoz() {
        return cenVoz;
    }

    public void setCenVoz(Double cenVoz) {
        this.cenVoz = cenVoz;
    }

    public String getPocDest() {
        return pocDest;
    }

    public void setPocDest(String pocDest) {
        this.pocDest = pocDest;
    }

    public String getZavDest() {
        return zavDest;
    }

    public void setZavDest(String zavDest) {
        this.zavDest = zavDest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NarucujeVoznju)) return false;
        NarucujeVoznju that = (NarucujeVoznju) o;
        return Objects.equals(klijentMbrKl, that.klijentMbrKl) &&
                Objects.equals(tarifnaGrupaNazTg, that.tarifnaGrupaNazTg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klijentMbrKl, tarifnaGrupaNazTg);
    }
}
