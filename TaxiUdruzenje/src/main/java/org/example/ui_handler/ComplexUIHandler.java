package org.example.ui_handler;

import org.example.dto.SimpleQueryDto;
import org.example.dto.HardQueryOneDto;
import org.example.dto.HardQueryTwoDto;
import org.example.service.ComplexService;

import java.sql.SQLException;
import java.util.List;

public class ComplexUIHandler {

    private static final ComplexService complexService = new ComplexService();

    public void handleComplexMenu() {
        String answer;
        do {
            System.out.println("\nOdaberite opciju za rad sa vozačima i tarifnim grupama:");
            System.out.println("1 - Prikaz ukupnog broja vožnji koje je svaki vozač obavio i sume zarade koju je ostvario, sa detaljnim podacima o imenu i prezimenu vozača.");
            System.out.println("2 - Prikaz tarifnih grupa sa brojem vožnji i prihodima većim od 1000, sortirano po prihodu opadajuće.");
            System.out.println("3 - Prikaz vozača po tarifama sa brojem vožnji i prosečnom cenom vožnje, filtrirano po cenama većim od 200. Sortirano po broju vožnji i prosečnoj ceni.");
            System.out.println("D - Brisanje klijenta po matičnom broju, samim tim i sve njegove narucenje voznje");
            System.out.println("X - Izlazak iz rukovanja podacima");

            answer = MainUIHandler.sc.nextLine();

            switch (answer.toUpperCase()) {
                case "1" -> showVozaci();
                case "2" -> showTarifneGrupe();
                case "3" -> showVozaciPoTarifama();
                case "D" -> deleteKlijent();
            }

        } while (!answer.equalsIgnoreCase("X"));
    }

    // Metoda za prikaz vozača (jednostavan query)
    private void showVozaci() {
        try {
            List<SimpleQueryDto> lista = complexService.getVozaciSaBrojemVoznjiIUkupnomZaradom();
            if (lista.isEmpty()) {
                System.out.println("Nema vozača ili podaci nisu dostupni.");
            } else {
                for (SimpleQueryDto dto : lista) {
                    printVozac(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printVozac(SimpleQueryDto dto) {
        System.out.println("----------------------------------------------------");
        System.out.println("Matični broj: " + dto.getMbrZ());
        System.out.println("Ime: " + dto.getImeZ());
        System.out.println("Prezime: " + dto.getPreZ());
        System.out.println("Broj vožnji: " + dto.getBrojVoznji());
        System.out.println("Ukupna zarada: " + dto.getUkupnaZarada());
    }

    // Metoda za prikaz tarifnih grupa (hard query 1)
    private void showTarifneGrupe() {
        try {
            List<HardQueryOneDto> lista = complexService.getTarifneGrupeSaPrihodom();
            if (lista.isEmpty()) {
                System.out.println("Nema tarifnih grupa sa prihodom većim od 1000 ili podaci nisu dostupni.");
            } else {
                for (HardQueryOneDto dto : lista) {
                    printTarifnaGrupa(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printTarifnaGrupa(HardQueryOneDto dto) {
        System.out.println("----------------------------------------------------");
        System.out.println("Naziv tarifne grupe: " + dto.getNazivTg());
        System.out.println("Broj vožnji: " + dto.getBrojVoznji());
        System.out.println("Prihod: " + dto.getPrihod());
    }

    // Metoda za prikaz vozača po tarifama (hard query 2)
    private void showVozaciPoTarifama() {
        try {
            List<HardQueryTwoDto> lista = complexService.getHardQueryTwo();
            if (lista.isEmpty()) {
                System.out.println("Nema vozača ili vožnji koje zadovoljavaju kriterijum.");
            } else {
                for (HardQueryTwoDto dto : lista) {
                    printVozacPoTarifi(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printVozacPoTarifi(HardQueryTwoDto dto) {
        System.out.println("----------------------------------------------------");
        System.out.println("Matični broj vozača: " + dto.getVozacId());
        System.out.println("Ime i prezime: " + dto.getImePrezime());
        System.out.println("Tarifa: " + dto.getTarifa());
        System.out.println("Broj vožnji po tarifnoj grupi: " + dto.getBrojVoznji());
        System.out.println("Prosečna cena vožnje: " + dto.getProsecnaCena());
    }

    // NOVA METODA za brisanje klijenta
    private void deleteKlijent() {
        System.out.println("Unesite matični broj klijenta koji želite da obrišete:");
        try {
            int mbrKl = Integer.parseInt(MainUIHandler.sc.nextLine());
            boolean success = complexService.delKlijentTransactional(mbrKl);
            if (success) {
                System.out.println("Klijent sa matičnim brojem " + mbrKl + " je uspešno obrisan.");
            } else {
                System.out.println("Brisanje klijenta nije uspelo. Proverite da li klijent postoji ili da li postoje zavisnosti.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Neispravan unos. Molimo unesite validan broj.");
        } catch (SQLException e) {
            System.out.println("Došlo je do greške prilikom brisanja klijenta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
