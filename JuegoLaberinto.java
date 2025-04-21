import java.util.Random;

public class JuegoLaberinto {
    private int energyPoints = 100;
    private short roomPosition = 0;
    private final int[][] rooms = {
        {2, 3},
        {1, 4},
        {},
        {1, 2}
    };

    public int getEnergyPoints() {
        return energyPoints;
    }

    public short getRoomPosition() {
        return roomPosition;
    }

    public int[] getSalasDisponibles() {
        return rooms[roomPosition];
    }

    public boolean puedeMoverseA(int sala) {
        for (int r : rooms[roomPosition]) {
            if (r == sala) return true;
        }
        return false;
    }

    public void moverASala(int sala) {
        roomPosition = (short) (sala - 1);
    }

    public boolean haGanado() {
        return roomPosition == 2; // Sala 3
    }

    public boolean haPerdido() {
        return energyPoints <= 0;
    }

    public int inspeccionarSala(Random random) {
        int event = random.nextInt(3);
        int puntos = switch (event) {
            case 0 -> 10;
            case 1 -> -15;
            default -> 0;
        };
        energyPoints += puntos;
        return puntos;
    }
}
