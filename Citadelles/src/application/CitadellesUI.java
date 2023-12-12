package application;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class CitadellesUI extends JFrame {
    public CitadellesUI() {
        setTitle("Citadelle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        String[] columnNames = {"Colonne 1", "Colonne 2", "Colonne 3"};
        String[][] data = {{"Cellule 1", "Cellule 2", "Cellule 3"}, {"Cellule 4", "Cellule 5", "Cellule 6"}};

        JTable table = new JTable(data, columnNames);
        panel.add(table);

        JButton button = new JButton("Cliquez ici");
        panel.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CitadellesUI().setVisible(true);
        });
    }
}