import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel implements MouseListener {
      Stage stage = new Stage();
      public Canvas() {
        setPreferredSize(new Dimension(1064, 720)); // increased slightly for buttons
        this.addMouseListener(this);
      }

      @Override
      public void paint(Graphics g) {
        stage.paint(g, getMousePosition());
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        stage.mouseClicked(e.getPoint());
      }

      @Override
      public void mousePressed(MouseEvent e) {}

      @Override
      public void mouseReleased(MouseEvent e) {}

      @Override
      public void mouseEntered(MouseEvent e) {}

      @Override
      public void mouseExited(MouseEvent e) {}
    }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    public void run() {
      while(true) {
        Instant startTime = Instant.now();
        repaint();
        Instant endTime = Instant.now();
        long howLong = Duration.between(startTime, endTime).toMillis();
        try{
          Thread.sleep(16 - howLong); // aprox 60 fps
        } catch(InterruptedException e) {
          System.out.println("Thread was interrupted, this is probably fine!");
        } catch(IllegalArgumentException e) {
          System.out.println("Application can't keep up with the framerate...");
        }
      }
    }
}
