package org.example.ui_handler;

import org.example.model.Zaposleni;
import org.example.service.ZaposleniService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZaposleniUIHandler {

    private static final ZaposleniService zaposleniService = new ZaposleniService();

    public void handleZaposleniMenu() {
        String answer;
        do {
            System.out.println("\nOdaberite opciju za rad nad zaposlenima:");
            System.out.println("1 - Prikaz svih");
            System.out.println("2 - Prikaz po identifikatoru");
            System.out.println("3 - Unos jednog zaposlenog");
            System.out.println("4 - Unos više zaposlenih");
            System.out.println("5 - Izmena po identifikatoru");
            System.out.println("6 - Brisanje po identifikatoru");
            System.out.println("X - Izlazak iz rukovanja zaposlenima");

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
            for (Zaposleni z : zaposleniService.getAll()) {
                printZaposleni(z);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showById() {
        System.out.println("ID zaposlenog: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            Zaposleni z = zaposleniService.getById(id);
            if (z != null) {
                printZaposleni(z);
            } else {
                System.out.println("Zaposleni sa ID " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleSingleInsert() {
        Zaposleni z = readZaposleniFromInput();

        try {
            boolean success = zaposleniService.save(z);
            System.out.println(success ? "Dodavanje uspešno." : "Dodavanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleMultipleInserts() {
        List<Zaposleni> lista = new ArrayList<>();
        String answer;
        do {
            Zaposleni z = readZaposleniFromInput();
            lista.add(z);

            System.out.println("Prekinuti unos? X za potvrdu");
            answer = MainUIHandler.sc.nextLine();
        } while (!answer.equalsIgnoreCase("X"));

        try {
            int rowsSaved = zaposleniService.saveAll(lista);
            System.out.println("Uspešno dodato " + rowsSaved + " zaposlenih.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate() {
        System.out.println("ID zaposlenog: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            if (!zaposleniService.existsById(id)) {
                System.out.println("Zaposleni sa unetim ID ne postoji!");
                return;
            }

            Zaposleni updated = readZaposleniFromInput();
            updated.setMbrZ(id);

            boolean success = zaposleniService.save(updated);
            System.out.println(success ? "Zaposleni uspešno izmenjen." : "Izmena nije uspela.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete() {
        System.out.println("ID zaposlenog: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        try {
            boolean success = zaposleniService.deleteById(id);
            System.out.println(success ? "Zaposleni uspešno obrisan." : "Brisanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Zaposleni readZaposleniFromInput() {
        System.out.println("ID zaposlenog: ");
        int id = Integer.parseInt(MainUIHandler.sc.nextLine());

        System.out.println("Ime zaposlenog: ");
        String ime = MainUIHandler.sc.nextLine();

        System.out.println("Prezime zaposlenog: ");
        String prezime = MainUIHandler.sc.nextLine();

        System.out.println("Plata zaposlenog: ");
        double plata = Double.parseDouble(MainUIHandler.sc.nextLine());

        System.out.println("Kategorija zaposlenog (V ili B): ");
        String kat = MainUIHandler.sc.nextLine();

        return new Zaposleni(id, ime, prezime, plata, kat);
    }

    private void printZaposleni(Zaposleni z) {
        System.out.println("----------------------------------------------------");
        System.out.println("ID: " + z.getMbrZ());
        System.out.println("Ime: " + z.getImeZ());
        System.out.println("Prezime: " + z.getPreZ());
        System.out.println("Plata: " + z.getPltZ());
        System.out.println("Kategorija: " + z.getKatZ());
    }
}
