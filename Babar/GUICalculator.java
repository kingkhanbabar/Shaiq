package Babar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICalculator extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0, num2 = 0;
    private String operator = "";
    private boolean newInput = true;

    public GUICalculator() {
        setTitle("Yellow Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        // ======= YELLOW THEME =======
        Color yellowBg = new Color(255, 255, 150);
        Color yellowButton = new Color(255, 255, 120);
        Color darkText = Color.BLACK;

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.YELLOW);
        display.setForeground(Color.BLACK);
        add(display, BorderLayout.NORTH);

        // Buttons Grid
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(yellowBg);

        for (String text : buttonLabels) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.addActionListener(this);

            // Yellow button style
            btn.setBackground(yellowButton);
            btn.setForeground(darkText);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        // Yellow frame background
        getContentPane().setBackground(yellowBg);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {  // If digit pressed
            if (newInput) {
                display.setText(input);
                newInput = false;
            } else {
                display.setText(display.getText() + input);
            }
        }
        else if (input.matches("[+\\-*/]")) { // Operator pressed
            num1 = Double.parseDouble(display.getText());
            operator = input;
            newInput = true;
        }
        else if (input.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = calculate(num1, num2, operator);
            display.setText(String.valueOf(result));
            newInput = true;
        }
        else if (input.equals("C")) {
            display.setText("");
            num1 = num2 = 0;
            operator = "";
            newInput = true;
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero!");
                    return 0;
                }
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}
