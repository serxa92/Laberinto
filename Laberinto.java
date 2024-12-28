
            // Procesar la opción ingresada
           import java.util.Scanner;

            public class Laberinto {

                public static boolean IS_GAME_OVER = false;

                public static int gamePoints = 100;

                public static short roomPosition = 0;

                public static int[][] rooms = new int[4][2];

                public static short[] randomEvents= new short [3];

                public static void main(String[] args) {

                    // Para acceder a la posicion n de un array, abrimos [] y ponemos el índice n
                    rooms[0] = new int[]{2, 3};
                    rooms[1] = new int[]{1, 4};
                    rooms[2] = new int[]{};
                    rooms[3] = new int[]{1, 2};

                    Scanner sc = new Scanner(System.in);

                    while (!IS_GAME_OVER) {
                        System.out.println("Estás en la sala " + (roomPosition + 1));
                        System.out.println("Que quieres hacer?\n" +
                                "Opción 1: Moverte de sala\n" +
                                "Opción 2: Inspeccionar la sala\n" +
                                "Opción 3: Salir del juego.");

                        short option = 0; // Inicializamos la opción
                        boolean opcionvalida = false;

                        // Validar entrada del usuario
                        do {
                            if (sc.hasNextShort()) {
                                option = sc.nextShort();
                                if (option >= 1 && option <= 3) {
                                    opcionvalida = true;
                                } else {
                                    System.out.println("Opción fuera de rango. Debe ser 1, 2 o 3.");
                                }
                            } else {
                                System.out.println("Entrada no válida. Por favor, introduce un número.");
                                sc.next();
                            }
                        } while (!opcionvalida);

                        // Procesamos la opción ingresada

                        switch (option){

                            case 1 ->{
                                System.out.println("¿A que sala quieres moverte?");

                                short room = sc.nextShort();

                                // Dentro del array rooms, accedo a la posicion actual y evalúo las posibilidades de conexión
                                // en los indices [0] y [1]

                                if (rooms[roomPosition][0] ==room || rooms[roomPosition][1]==room){

                                    if (room==3){
                                        System.out.println("¡Has llegado al final!");
                                        IS_GAME_OVER = true;
                                    }else {
                                        System.out.println("Puedes acceder a esta sala");
                                    }

                                    roomPosition = (short) (room - 1);

                                }else{
                                    System.out.println("No puedes acceder a esta sala");
                                }
                                break;


                            }

                            case 2 ->{




                                break;
                            }

                            case 3 ->{

                                System.out.println("Has salido del juego");
                                IS_GAME_OVER = true;


                                break;
                            }}
                    }

                    sc.close();
                }
            }








