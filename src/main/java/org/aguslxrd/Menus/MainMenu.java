package org.aguslxrd.Menus;

import org.aguslxrd.Modules.AuthLogs;
import org.aguslxrd.Modules.BackupModule;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);

    public void viewMenu() throws InterruptedException, IOException {
        int opcion;

        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Realizar copia de seguridad");
            System.out.println("2. Otra opción (agrega más opciones según sea necesario)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    realizarCopiaDeSeguridad();
                    break;
                case 2:
                    getAuthLogInfo();
                    break;
                case 0:
                    System.out.println("Closing");
                    break;
                default:
                    System.out.println("Invalid option");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private void realizarCopiaDeSeguridad() throws InterruptedException {
        System.out.println("Copying Files...");
        Thread.sleep(2000);
        BackupModule backupModule = new BackupModule("C:\\\\Users\\\\iAgus\\\\Downloads\\\\acopiar", "C:\\\\Users\\\\iAgus\\\\Downloads\\\\backupDir");
        backupModule.startBackupScheduler();
    }

    private void getAuthLogInfo() throws IOException {
        AuthLogs authLogs = new AuthLogs();
        getAuthLogInfo();
    }
}
