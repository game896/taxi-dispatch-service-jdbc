package org.example.ui_handler;

import org.example.model.Vozilo;
import org.example.service.VoziloService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoziloUIHandler {

    private static final VoziloService voziloService = new VoziloService();

    public void handleVoziloMenu() {
        String answer;
        do {
            System.out.println("\nOdaberite opciju za rad nad vozilima:");
            System.out.println("1 - Prikaz svih");
            System.out.println("2 - Prikaz po registracionom broju");
            System.out.println("3 - Unos jednog vozila");
            System.out.println("4 - Unos više vozila");
            System.out.println("5 - Brisanje po registracionom broju");
            System.out.println("X - Izlazak iz rukovanja vozilima");

            answer = MainUIHandler.sc.nextLine();

            switch (answer) {
                case "1" -> showAll();
                case "2" -> showById();
                case "3" -> handleSingleInsert();
                case "4" -> handleMultipleInserts();
                case "5" -> handleDelete();
            }

        } while (!answer.equalsIgnoreCase("X"));
    }

    private void showAll() {
        try {
            for (Vozilo v : voziloService.getAll()) {
                printVozilo(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showById() {
        System.out.println("Registracioni broj vozila: ");
        String regBr = MainUIHandler.sc.nextLine();

        try {
            Vozilo v = voziloService.getById(regBr);
            if (v != null) {
                printVozilo(v);
            } else {
                System.out.println("Vozilo sa registracionim brojem " + regBr + " ne postoji.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleSingleInsert() {
        Vozilo v = readVoziloFromInput();

        try {
            boolean success = voziloService.save(v);
            System.out.println(success ? "Dodavanje uspesno." : "Dodavanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleMultipleInserts() {
        List<Vozilo> lista = new ArrayList<>();
        String answer;
        do {
            Vozilo v = readVoziloFromInput();
            lista.add(v);

            System.out.println("Prekinuti unos? X za potvrdu");
            answer = MainUIHandler.sc.nextLine();
        } while (!answer.equalsIgnoreCase("X"));

        try {
            int rowsSaved = voziloService.saveAll(lista);
            System.out.println("Uspesno dodato " + rowsSaved + " vozila.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate() {
        System.out.println("Registracioni broj vozila: ");
        String regBr = MainUIHandler.sc.nextLine();

        try {
            if (!voziloService.existsById(regBr)) {
                System.out.println("Vozilo sa unetim registracionim brojem ne postoji!");
                return;
            }

            Vozilo updated = readVoziloFromInput();
            updated.setRegBr(regBr); // primarni ključ ostaje isti

            boolean success = voziloService.save(updated);
            System.out.println(success ? "Vozilo uspesno izmenjeno." : "Izmena nije uspela.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete() {
        System.out.println("Registracioni broj vozila: ");
        String regBr = MainUIHandler.sc.nextLine();

        try {
            boolean success = voziloService.deleteById(regBr);
            System.out.println(success ? "Vozilo uspesno obrisano." : "Brisanje nije uspelo.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vozilo readVoziloFromInput() {
        System.out.println("Registracioni broj vozila: ");
        String regBr = MainUIHandler.sc.nextLine();

        return new Vozilo(regBr);
    }

    private void printVozilo(Vozilo v) {
        System.out.println("----------------------------------------------------");
        System.out.println("Registracioni broj: " + v.getRegBr());
    }
}
