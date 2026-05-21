package org.example.ui_handler;

import org.example.model.TarifnaGrupa;
import org.example.service.TarifnaGrupaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifnaGrupaUIHandler {

    private static final TarifnaGrupaService tarifnaGrupaService = new TarifnaGrupaService();

    public void handleTarifnaGrupaMenu() {
        String answer;
        do {
            System.out.println("\nOdaberite opciju za rad nad tarifnim grupama:");
            System.out.println("1 - Prikaz svih");
            System.out.println("2 - Prikaz po identifikatoru");
            System.out.println("3 - Unos jedne tarifne grupe");
            System.out.println("4 - Unos više tarifnih grupa");
            System.out.println("5 - Izmena po identifikatoru");
            System.out.println("6 - Brisanje po identifikatoru");
            System.out.println("X - Izlazak iz rukovanja tarifnim grupama");

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
            for (TarifnaGrupa tg : tarifnaGrupaService.getAll()) {
                printTarifnaGrupa(tg);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showById() {
        System.out.println("Naziv tarifne grupe: ");
        String id = MainUIHandler.sc.nextLine();

        try {
            TarifnaGrupa tg = tarifnaGrupaService.getById(id);
            if (tg != null) {
                printTarifnaGrupa(tg);
            } else {
                System.out.println("Tarifna grupa sa ID " + id + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleSingleInsert() {
        TarifnaGrupa tg = readTarifnaGrupaFromInput();

        try {
            boolean success = tarifnaGrupaService.save(tg);
            System.out.println(success ? "Dodavanje uspesno." : "Dodavanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleMultipleInserts() {
        List<TarifnaGrupa> lista = new ArrayList<>();
        String answer;
        do {
            TarifnaGrupa tg = readTarifnaGrupaFromInput();
            lista.add(tg);

            System.out.println("Prekinuti unos? X za potvrdu");
            answer = MainUIHandler.sc.nextLine();
        } while (!answer.equalsIgnoreCase("X"));

        try {
            int rowsSaved = tarifnaGrupaService.saveAll(lista);
            System.out.println("Uspesno dodato " + rowsSaved + " tarifnih grupa.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate() {
        System.out.println("Naziv tarifne grupe: ");
        String id = MainUIHandler.sc.nextLine();

        try {
            if (!tarifnaGrupaService.existsById(id)) {
                System.out.println("Tarifna grupa sa unetim ID ne postoji!");
                return;
            }

            TarifnaGrupa updated = readTarifnaGrupaFromInput();
            updated.setNazTg(id); // primarni ključ ostaje isti

            boolean success = tarifnaGrupaService.save(updated);
            System.out.println(success ? "Tarifna grupa uspesno izmenjena." : "Izmena nije uspela.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete() {
        System.out.println("Naziv tarifne grupe: ");
        String id = MainUIHandler.sc.nextLine();

        try {
            boolean success = tarifnaGrupaService.deleteById(id);
            System.out.println(success ? "Tarifna grupa uspesno obrisana." : "Brisanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TarifnaGrupa readTarifnaGrupaFromInput() {
        System.out.println("Naziv tarifne grupe: ");
        String naziv = MainUIHandler.sc.nextLine();

        System.out.println("Opis tarifne grupe: ");
        String opis = MainUIHandler.sc.nextLine();

        return new TarifnaGrupa(naziv, opis);
    }

    private void printTarifnaGrupa(TarifnaGrupa tg) {
        System.out.println("----------------------------------------------------");
        System.out.println("Naziv: " + tg.getNazTg());
        System.out.println("Opis: " + tg.getOpisTG());
    }
}
