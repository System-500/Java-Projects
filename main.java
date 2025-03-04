import javax.swing.*;

public class main {
    public static void main(String[] args) {
      
        JFrame frame = new JFrame("My Application");

        appka app = new appka();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,150);
        frame.add(app.mainPanel);
        frame.setVisible(true);
    }
}
    