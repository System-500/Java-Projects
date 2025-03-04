import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class appka {
    JPanel mainPanel;
    private JButton OKButton;
    private JButton clearButton;
    private JTextField textField1;
    private JButton saveToFileButton;
    private JButton readFromFileButton;
    private JButton drawACircleButton;
    private JLabel myLabel;
    private int licznik = 0;

    public appka() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

   
        clearButton = new JButton("Clear");
        myLabel = new JLabel(" ");
        textField1 = new JTextField(20);
        OKButton = new JButton("OK");

        drawACircleButton = new JButton("Draw Circle");
        saveToFileButton = new JButton("Save to File");
        readFromFileButton = new JButton("Read from File");

       
        mainPanel.add(textField1);
        mainPanel.add(myLabel);
        mainPanel.add(Box.createVerticalStrut(10));

    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(OKButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveToFileButton);
        buttonPanel.add(readFromFileButton);
        buttonPanel.add(drawACircleButton);

        mainPanel.add(buttonPanel);

        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                myLabel.setText(text);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                myLabel.setText(" ");
            }
        });

        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
                licznik++;
                try {
                    File file = new File("Text.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.write(licznik + ") ");
                    writer.write(text);
                    writer.newLine();
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Error saving to file.");
                }
            }
        });

        readFromFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = new File("Text.txt");
                if (file.exists()) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        StringBuilder content = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        myLabel.setText("<html>" + content.toString().replaceAll("\n", "<br/>") + "</html>");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "Error reading from file.");
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "File does not exist.");
                }
            }
        });

    
        drawACircleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame circleFrame = new JFrame("Draw Circle");
                circleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                circleFrame.setSize(300, 300);
                circleFrame.add(new CirclePanel());
                circleFrame.setVisible(true);
            }
        });
    }

  


    private class CirclePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillOval(50, 50, 100, 100);
        }
    }
}
