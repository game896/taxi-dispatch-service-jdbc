package org.example.model;

import java.util.Objects;

public class Vozilo {

    private String regBr;

    public Vozilo() {
        super();
    }

    public Vozilo(String regBr) {
        this.regBr = regBr;
    }

    public String getRegBr() {
        return regBr;
    }

    public void setRegBr(String regBr) {
        this.regBr = regBr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vozilo)) return false;
        Vozilo vozilo = (Vozilo) o;
        return Objects.equals(regBr, vozilo.regBr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regBr);
    }
}

