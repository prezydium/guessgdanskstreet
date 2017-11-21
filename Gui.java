import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    static int chances = 10;
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel base;

    public Gui() {
        (this).setContentPane(base);
        //(this).add(textArea1, textField1);
        (this).setSize(800, 600);
        (this).setVisible(true);
        (this).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Proc proc1 = new Proc();
        proc1.chooseStreet();
        proc1.generateHiddenWord();
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("")) {
                } else {
                    proc1.setInput(textField1.getText().toUpperCase().charAt(0));
                    if (proc1.gameTurn()) {
                        textArea1.append("TAK\n");
                        textArea1.append(proc1.getOutput());
                        chances--;
                        textField1.setText("");
                    } else {
                        textArea1.append("nie\n");
                        textArea1.append(proc1.getOutput());
                        chances--;
                        textField1.setText("");
                    }
                    if (chances == 0) {
                        textArea1.append("Przegrałeś");
                        JOptionPane.showMessageDialog(null, "Przegrałeś\nTo była: " + proc1.randomStreet);
                        dispose();
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui window = new Gui();

            }
        });

    }

    public void appendToTextArea1(String s) {
        textArea1.append(s);
    }
}
