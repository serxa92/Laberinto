import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class LaberintoGUI extends JFrame {
    private final JuegoLaberinto juego = new JuegoLaberinto();
    private final Random random = new Random();

    private JLabel roomLabel;
    private JLabel energyLabel;

    public LaberintoGUI() {
        setTitle("Juego del Laberinto (GUI)");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con estado
        JPanel topPanel = new JPanel();
        roomLabel = new JLabel("Estás en la sala 1");
        energyLabel = new JLabel("Energía: 100");
        topPanel.add(roomLabel);
        topPanel.add(energyLabel);
        add(topPanel, BorderLayout.NORTH);

        // Panel inferior con botones
        JPanel buttonPanel = new JPanel();
        JButton moveBtn = new JButton("Moverse");
        JButton inspectBtn = new JButton("Inspeccionar");
        JButton exitBtn = new JButton("Salir");

        buttonPanel.add(moveBtn);
        buttonPanel.add(inspectBtn);
        buttonPanel.add(exitBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Eventos
        moveBtn.addActionListener(e -> mover());
        inspectBtn.addActionListener(e -> inspeccionar());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void mover() {
        int[] disponibles = juego.getSalasDisponibles();
        String opciones = "";
        for (int s : disponibles) {
            opciones += s + " ";
        }
        String input = JOptionPane.showInputDialog(this,
                "¿A qué sala quieres moverte? Salas disponibles: " + opciones);
        try {
            int sala = Integer.parseInt(input);
            if (juego.puedeMoverseA(sala)) {
                juego.moverASala(sala);
                actualizarPantalla();
                if (juego.haGanado()) {
                    JOptionPane.showMessageDialog(this, "¡Has ganado!");
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No puedes moverte a esa sala.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        }
    }

    private void inspeccionar() {
        int resultado = juego.inspeccionarSala(random);
        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "¡Tesoro encontrado! +10 energía.");
        } else if (resultado < 0) {
            JOptionPane.showMessageDialog(this, "¡Trampa! -15 energía.");
        } else {
            JOptionPane.showMessageDialog(this, "No has encontrado nada.");
        }
        actualizarPantalla();
        if (juego.haPerdido()) {
            JOptionPane.showMessageDialog(this, "¡Has perdido por falta de energía!");
            System.exit(0);
        }
    }

    private void actualizarPantalla() {
        roomLabel.setText("Estás en la sala " + (juego.getRoomPosition() + 1));
        energyLabel.setText("Energía: " + juego.getEnergyPoints());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LaberintoGUI gui = new LaberintoGUI();
            gui.setVisible(true);
        });
    }
}
