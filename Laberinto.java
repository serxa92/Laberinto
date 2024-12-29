import java.util.Scanner;
import java.util.Random;

public class Laberinto {

    public static boolean IS_GAME_OVER = false;
    public static int energyPoints = 100;
    static Random random = new Random();
    public static short roomPosition = 0;
    public static int[][] rooms = {
            {2, 3},
            {1, 4},
            {},
            {1, 2}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("****Bienvenido al juego del laberinto ****\n");

        while (!IS_GAME_OVER) {
            System.out.println("Estás en la sala " + (roomPosition + 1));
            System.out.println("Que quieres hacer?\n" +
                    "1. Moverte de sala\n" +
                    "2. Inspeccionar la sala\n" +
                    "3. Salir del juego.");

            short option = getValidOption(sc);

            if (energyPoints <= 0) {
                System.out.println("¡Te has quedado sin energía! Has perdido el juego.");
                IS_GAME_OVER = true;
                continue;
            }

            switch (option) {
                case 1 -> moveRoom(sc);
                case 2 -> inspectRoom();
                case 3 -> {
                    System.out.println("Has decidido salir del juego. ¡Hasta la próxima!");
                    IS_GAME_OVER = true;
                }
            }
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
                    System.out.println("Opción fuera de rango. Debe ser 1, 2 o 3.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                sc.next();
            }
        }
        return option;
    }

    private static void moveRoom(Scanner sc) {
        System.out.println("¿A que sala quieres moverte?");
        short room = sc.nextShort();
        if (rooms[roomPosition][0] == room || rooms[roomPosition][1] == room) {
            if (room == 3) {
                System.out.println("¡Has llegado al final!");
                IS_GAME_OVER = true;
            }
            roomPosition = (short) (room - 1);
        } else {
            System.out.println("No puedes acceder a la sala " + room);
        }
    }

    private static void inspectRoom() {
        System.out.println("Has decidido inspeccionar la sala, veamos...");
        int points = getInspectionPoints();
        energyPoints += points;
    }

    public static int getInspectionPoints() {
        int points = 0;
        int randomEvent = random.nextInt(3);
        switch (randomEvent) {
            case 0 -> {
                System.out.println("¡Has encontrado un tesoro! ¡Has aumentado tu energía en 10 puntos!\n" +
                        "Tu energía actual es: " + (energyPoints + 10));
                points = 10;
            }
            case 1 -> {
                System.out.println("¡Has caído en una trampa, has reducido tu energía en 15 puntos!\n" +
                        "Tu energía actual es: " + (energyPoints - 15));
                points = -15;
            }
            case 2 -> System.out.println("No has encontrado nada...");
        }
        return points;
    }
}