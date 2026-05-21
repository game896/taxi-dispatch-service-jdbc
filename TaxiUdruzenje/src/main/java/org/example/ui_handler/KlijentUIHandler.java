package org.example.ui_handler;

import org.example.model.Klijent;
import org.example.service.KlijentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlijentUIHandler {

    private static final KlijentService klijentService = new KlijentService();

    public void handleKlijentMenu() {
        String answer;
        do {
            System.out.println("\nOdaberite opciju za rad nad klijentima:");
            System.out.println("1 - Prikaz svih");
            System.out.println("2 - Prikaz po identifikatoru");
            System.out.println("3 - Unos jednog klijenta");
            System.out.println("4 - Unos više klijenata");
            System.out.println("5 - Izmena po identifikatoru");
            System.out.println("6 - Brisanje po identifikatoru");
            System.out.println("X - Izlazak iz rukovanja klijentima");

            answer = MainUIHandler.sc.nextLine();

            switch (answer) {
                case "1" -> showAll();
                case "2" -> showById();
                case "3" -> handleSingleInsert();
                case "4" -> handleMultipleInserts();
                case "5" -> handleUpdate();
                case "6" -> handleDelete();
            }

        } while (!answer.equalsIgnoreCase("X"));
    }

    private void showAll() {
        try {
            for (Klijent k : klijentService.getAll()) {
                printKlijent(k);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showById() {
        System.out.println("ID klijenta: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            Klijent k = klijentService.getById(id);
            if (k != null) {
                printKlijent(k);
            } else {
                System.out.println("Klijent sa ID " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleSingleInsert() {
        Klijent k = readKlijentFromInput();

        try {
            boolean success = klijentService.save(k);
            System.out.println(success ? "Dodavanje uspesno." : "Dodavanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleMultipleInserts() {
        List<Klijent> lista = new ArrayList<>();
        String answer;
        do {
            Klijent k = readKlijentFromInput();
            lista.add(k);

            System.out.println("Prekinuti unos? X za potvrdu");
            answer = MainUIHandler.sc.nextLine();
        } while (!answer.equalsIgnoreCase("X"));

        try {
            int rowsSaved = klijentService.saveAll(lista);
            System.out.println("Uspesno dodato " + rowsSaved + " klijenata.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate() {
        System.out.println("ID klijenta: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            if (!klijentService.existsById(id)) {
                System.out.println("Klijent sa unetim ID ne postoji!");
                return;
            }

            Klijent updated = readKlijentFromInput();
            updated.setMbrKl(id);

            boolean success = klijentService.save(updated);
            System.out.println(success ? "Klijent uspesno izmenjen." : "Izmena nije uspela.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete() {
        System.out.println("ID klijenta: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            boolean success = klijentService.deleteById(id);
            System.out.println(success ? "Klijent uspesno obrisan." : "Brisanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Klijent readKlijentFromInput() {
        System.out.println("ID klijenta: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        System.out.println("Email klijenta: ");
        String email = MainUIHandler.sc.nextLine();

        System.out.println("Broj telefona klijenta: ");
        String brtel = MainUIHandler.sc.nextLine();

        System.out.println("Ime klijenta: ");
        String ime = MainUIHandler.sc.nextLine();

        System.out.println("Prezime klijenta: ");
        String prz = MainUIHandler.sc.nextLine();

        System.out.println("Dispecer ID: ");
        int dispId = Integer.parseInt(MainUIHandler.sc.nextLine());

        return new Klijent(id, email, brtel, ime, prz, dispId);
    }

    private void printKlijent(Klijent k) {
        System.out.println("----------------------------------------------------");
        System.out.println("ID: " + k.getMbrKl());
        System.out.println("Email: " + k.getEmailKl());
        System.out.println("Broj telefona: " + k.getBrtelKl());
        System.out.println("Ime: " + k.getImeKl());
        System.out.println("Prezime: " + k.getPrzKl());
        System.out.println("Dispecer ID: " + k.getDispecerMbrZ());
    }
}
