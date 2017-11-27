import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    static int chances = 10;
    private JTextField textField1;
    private JTextArea textArea1;
    private JPanel base;
    private JTextField turnsLeft;
    private JButton znamOdpowiedźButton;
    private static Proc proc1 = new Proc();
    private static Streets street = new Streets();

    public Gui() {
        (this).setContentPane(base);
        //(this).add(textArea1, textField1);
        (this).setSize(800, 600);
        (this).setVisible(true);
        (this).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea1.append(proc1.hiddenWord);
        textArea1.append(("\nZgadnij wybierając po 1 literze jaka nazwa ulicy w Gdańsku została u góry ukryta\n"));
        textField1.grabFocus();
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("")) {
                } else {
                    proc1.setInput(textField1.getText().toUpperCase().charAt(0));
                    if (proc1.gameTurn()) {

                        textArea1.append(proc1.getOutput());
                        textArea1.append("       Trafiony!\n");
                        chances--;
                        textField1.setText("");
                        turnsLeft.setText("Pozostało prób: " + chances);
                    } else {

                        textArea1.append(proc1.getOutput());
                        textArea1.append("      Pudło!\n");
                        chances--;
                        textField1.setText("");
                        turnsLeft.setText("Pozostało prób: " + chances);
                    }
                    if (chances == 0) {
                        textArea1.append("Przegrałeś");
                        JOptionPane.showMessageDialog(null, "Przegrałeś\nTo była: " + proc1.randomStreet);
                        dispose();
                    }
                    if(proc1.hiddenWord.indexOf("*")== -1){
                        textArea1.append("Zwycięstwo!");
                        JOptionPane.showMessageDialog(null, "Zwycięstwo!\nUdało Ci się po: " +( 10 - chances) +
                                " próbach");
                    }
                    turnsLeft.setText("Pozostało prób: " + chances);
                }
            }
        });

        znamOdpowiedźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = JOptionPane.showInputDialog(null, "odp");
                if (proc1.instantAnswer(answer)){
                    JOptionPane.showMessageDialog(null, "Zwycięstwo!\nUdało Ci się po: " +( 10 - chances) +
                            " próbach");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Zła odpowiedź, tracisz 3 szanse!");
                    chances = chances - 3;
                    turnsLeft.setText("Pozostało prób: " + chances);
                    if (chances < 0) {
                        JOptionPane.showMessageDialog(null, "Przegrałeś\nTo była: " + proc1.randomStreet);
                        dispose();
                    }
                }

            }
        });
    }

    public static void main(String[] args) {

        proc1.setRandomStreet(street.chooseStreet());
        proc1.generateHiddenWord();
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
