import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class MainApp extends JPanel {
    double sin60 = Math.sin(Math.PI / 3.0);  // Valor del seno de 60 grados
    int nivel_de_recursividad = 5;  // Puedes cambiar el nivel de recursividad

    public static void main(String[] args) {
        // Crear una ventana JFrame
        JFrame frame = new JFrame("Fractal Copo de Nieve - Curva de Koch");
        MainApp panel = new MainApp();
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Definimos las coordenadas del triángulo equilátero inicial
        double x1 = 300, y1 = 200;  // Vértice superior
        double x2 = 100, y2 = 500;  // Vértice inferior izquierdo
        double x3 = 500, y3 = 500;  // Vértice inferior derecho

        // Dibujamos los tres lados del triángulo usando la curva de Koch
        drawKochCurve(g, nivel_de_recursividad, x1, y1, x2, y2);
        drawKochCurve(g, nivel_de_recursividad, x2, y2, x3, y3);
        drawKochCurve(g, nivel_de_recursividad, x3, y3, x1, y1);
    }

    // Método recursivo para dibujar una curva de Koch
    private void drawKochCurve(Graphics g, int level, double x1, double y1, double x5, double y5) {
        if (level == 0) {
            // Si el nivel es 0, simplemente dibujamos una línea recta
            g.drawLine((int) x1, (int) y1, (int) x5, (int) y5);
        } else {
            // Dividimos la línea en tres partes
            double dx = (x5 - x1) / 3;
            double dy = (y5 - y1) / 3;

            // Puntos intermedios
            double x2 = x1 + dx;
            double y2 = y1 + dy;
            double x3 = (x1 + x5) / 2 - sin60 * (y5 - y1) / 3;
            double y3 = (y1 + y5) / 2 + sin60 * (x5 - x1) / 3;
            double x4 = x1 + 2 * dx;
            double y4 = y1 + 2 * dy;

            // Llamamos recursivamente a cada segmento de la curva de Koch
            drawKochCurve(g, level - 1, x1, y1, x2, y2);
            drawKochCurve(g, level - 1, x2, y2, x3, y3);
            drawKochCurve(g, level - 1, x3, y3, x4, y4);
            drawKochCurve(g, level - 1, x4, y4, x5, y5);
        }
    }
}
