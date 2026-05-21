package org.example.model;

import java.util.Objects;

public class Klijent {

    private Integer mbrKl;
    private String emailKl;
    private String brtelKl;
    private String imeKl;
    private String przKl;
    private Integer dispecerMbrZ;

    public Klijent() {
        super();
    }

    public Klijent(Integer mbrKl, String emailKl, String brtelKl,
                   String imeKl, String przKl, Integer dispecerMbrZ) {
        this.mbrKl = mbrKl;
        this.emailKl = emailKl;
        this.brtelKl = brtelKl;
        this.imeKl = imeKl;
        this.przKl = przKl;
        this.dispecerMbrZ = dispecerMbrZ;
    }

    public Integer getMbrKl() {
        return mbrKl;
    }

    public void setMbrKl(Integer mbrKl) {
        this.mbrKl = mbrKl;
    }

    public String getEmailKl() {
        return emailKl;
    }

    public void setEmailKl(String emailKl) {
        this.emailKl = emailKl;
    }

    public String getBrtelKl() {
        return brtelKl;
    }

    public void setBrtelKl(String brtelKl) {
        this.brtelKl = brtelKl;
    }

    public String getImeKl() {
        return imeKl;
    }

    public void setImeKl(String imeKl) {
        this.imeKl = imeKl;
    }

    public String getPrzKl() {
        return przKl;
    }

    public void setPrzKl(String przKl) {
        this.przKl = przKl;
    }

    public Integer getDispecerMbrZ() {
        return dispecerMbrZ;
    }

    public void setDispecerMbrZ(Integer dispecerMbrZ) {
        this.dispecerMbrZ = dispecerMbrZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klijent)) return false;
        Klijent klijent = (Klijent) o;
        return Objects.equals(mbrKl, klijent.mbrKl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrKl);
    }
}
