import java.util.Random;
import java.util.Scanner;

public class LaberintoConsola {
    public static void main(String[] args) {
        JuegoLaberinto juego = new JuegoLaberinto();
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("**** Bienvenido al juego del laberinto (Consola) ****");

        while (!juego.haGanado() && !juego.haPerdido()) {
            System.out.println("\nEstás en la sala " + (juego.getRoomPosition() + 1));
            System.out.println("Energía actual: " + juego.getEnergyPoints());
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Moverse");
            System.out.println("2. Inspeccionar sala");
            System.out.println("3. Salir");

            short opcion = getValidOption(sc);
            if (opcion == 1) {
                System.out.print("¿A qué sala quieres moverte? Salas disponibles: ");
                for (int s : juego.getSalasDisponibles()) {
                    System.out.print(s + " ");
                }
                System.out.print("\n> ");
                int destino = sc.nextInt();
                if (juego.puedeMoverseA(destino)) {
                    juego.moverASala(destino);
                } else {
                    System.out.println("No puedes moverte a esa sala.");
                }
            } else if (opcion == 2) {
                int resultado = juego.inspeccionarSala(random);
                if (resultado > 0) {
                    System.out.println("¡Tesoro encontrado! +10 de energía.");
                } else if (resultado < 0) {
                    System.out.println("¡Trampa! -15 de energía.");
                } else {
                    System.out.println("No has encontrado nada.");
                }
            } else {
                System.out.println("Has salido del juego.");
                return;
            }
        }

        if (juego.haGanado()) {
            System.out.println("🎉 ¡Has llegado a la sala final! ¡Has ganado!");
        } else {
            System.out.println("💀 Te has quedado sin energía. Has perdido.");
        }

        sc.close();
    }

    private static short getValidOption(Scanner sc) {
        short option = 0;
        boolean validOption = false;
        while (!validOption) {
            if (sc.hasNextShort()) {
                option = sc.nextShort();
                if (option >= 1 && option <= 3) {
                    validOption = true;
                } else {
                    System.out.println("Opción inválida. Elige 1, 2 o 3.");
                }
            } else {
                System.out.println("Entrada no válida.");
                sc.next(); // limpia buffer
            }
        }
        return option;
    }
}
