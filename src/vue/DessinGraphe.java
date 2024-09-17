package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.Random;

import javax.swing.JComponent;

import Graphe.Arete;
import Graphe.Sommet;

public class DessinGraphe extends JComponent {
    private int nbPoints;
    private Arete[] aretes;
    private String typeGraphe;
    private static final long serialVersionUID = 1L;

    public DessinGraphe(int nb, Arete[] aretes, String type) {
        this.aretes = aretes;
        this.nbPoints = nb;
        this.typeGraphe = type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        Random r = new Random();

        // Définir une nouvelle police et couleur de fond
        gr.setFont(new Font("Arial", Font.BOLD, 14));
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());

        Sommet[] sommets = new Sommet[nbPoints];

        // Dessiner les sommets avec des couleurs aléatoires
        for (int i = 0; i < nbPoints; i++) {
            int x = r.nextInt(getWidth() - 50);
            int y = r.nextInt(getHeight() - 50);
            sommets[i] = new Sommet(x, y, i + 1);
            Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
            sommets[i].draw(gr, Color.BLUE);
        }

        // Dessiner les arêtes
        for (Arete arete : aretes) {
            Sommet depart = sommets[arete.getD_sommet_depart().getD_numero() - 1];
            Sommet arrivee = sommets[arete.getD_sommet_arrive().getD_numero() - 1];
            arete.getD_sommet_depart().moveTo(depart.getD_x(), depart.getD_y());
            arete.getD_sommet_arrive().moveTo(arrivee.getD_x(), arrivee.getD_y());
            Color edgeColor = (typeGraphe.equalsIgnoreCase("nov") || typeGraphe.equalsIgnoreCase("ov")) ? Color.BLACK : Color.GRAY;
            arete.draw(gr, edgeColor, typeGraphe.equalsIgnoreCase("nov") || typeGraphe.equalsIgnoreCase("ov"));
            if (typeGraphe.equalsIgnoreCase("ov") || typeGraphe.equalsIgnoreCase("onv")) {
                int mx = (depart.getD_x() + arrivee.getD_x()) / 2;
                int my = (depart.getD_y() + arrivee.getD_y()) / 2;
                gr.setColor(Color.RED);
                gr.drawString((depart.getD_x() < arrivee.getD_x()) ? ">" : "<", mx, my + 10);
            }
        }
    }
}
