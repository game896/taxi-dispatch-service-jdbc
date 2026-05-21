package org.example.ui_handler;

import java.sql.SQLException;
import java.util.Scanner;

public class MainUIHandler {
    public static Scanner sc = new Scanner(System.in);

    private final KlijentUIHandler klijentUIHandler = new KlijentUIHandler();
    private final ZaposleniUIHandler zaposleniUIHandler = new ZaposleniUIHandler();
    private final TarifnaGrupaUIHandler tarifnaGrupaUIHandler = new TarifnaGrupaUIHandler();
    private final VoziloUIHandler voziloUIHandler = new VoziloUIHandler();
    private final ComplexUIHandler complexUIHandler = new ComplexUIHandler();

    public void handleMainMenu() {

        String answer;
        do {
            System.out.println("\nOdaberite opciju:");
            System.out.println("1 - Rukovanje klijentima");
            System.out.println("2 - Rukovanje zaposlenih");
            System.out.println("3 - Rukovanje tarifnim grupama");
            System.out.println("4 - Rukovanje vozilima");
            System.out.println("5 - Kompleksni upiti");
            System.out.println("X - Izlazak iz programa");

            answer = sc.nextLine();

            switch (answer) {
                case "1":
                    klijentUIHandler.handleKlijentMenu();
                    break;
                case "2":
                    zaposleniUIHandler.handleZaposleniMenu();
                    break;
                case "3":
                    tarifnaGrupaUIHandler.handleTarifnaGrupaMenu();
                    break;
                case "4":
                    voziloUIHandler.handleVoziloMenu();
                    break;
                case "5":
                    complexUIHandler.handleComplexMenu();
                    break;
            }

        } while (!answer.equalsIgnoreCase("X"));

        sc.close();
    }
}
