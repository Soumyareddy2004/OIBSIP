import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ATMInterface extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField userIdField, pinField, amountField, transferField;
    private JTextArea transactionHistoryArea;
    private JButton loginButton, withdrawButton, depositButton, transferButton, historyButton, quitButton;
    private double balance = 1000.0;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private String userId = "admin";
    private String pin = "1234";

    public ATMInterface() {
        setTitle("ATM Interface");
        setSize(400, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("ATM System");
        label.setBounds(150, 10, 100, 30);
        add(label);

        // Login Section
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 50, 100, 30);
        add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(150, 50, 150, 30);
        add(userIdField);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(50, 100, 100, 30);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(150, 100, 150, 30);
        add(pinField);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        // ATM Operations
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 200, 120, 30);
        withdrawButton.addActionListener(this);
        withdrawButton.setEnabled(false);
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(200, 200, 120, 30);
        depositButton.addActionListener(this);
        depositButton.setEnabled(false);
        add(depositButton);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(50, 250, 120, 30);
        transferButton.addActionListener(this);
        transferButton.setEnabled(false);
        add(transferButton);

        historyButton = new JButton("Transaction History");
        historyButton.setBounds(200, 250, 150, 30);
        historyButton.addActionListener(this);
        historyButton.setEnabled(false);
        add(historyButton);

        quitButton = new JButton("Quit");
        quitButton.setBounds(150, 300, 100, 30);
        quitButton.addActionListener(this);
        quitButton.setEnabled(false);
        add(quitButton);

        amountField = new JTextField();
        amountField.setBounds(150, 350, 150, 30);
        add(amountField);

        transactionHistoryArea = new JTextArea();
        transactionHistoryArea.setBounds(50, 400, 300, 50);
        add(transactionHistoryArea);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (userIdField.getText().equals(userId) && pinField.getText().equals(pin)) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                enableButtons(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
            }
        } else if (e.getSource() == withdrawButton) {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrawn: $" + amount);
                JOptionPane.showMessageDialog(null, "Withdrawal Successful! Current balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance.");
            }
        } else if (e.getSource() == depositButton) {
            double amount = Double.parseDouble(amountField.getText());
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            JOptionPane.showMessageDialog(null, "Deposit Successful! Current balance: $" + balance);
        } else if (e.getSource() == transferButton) {
            double amount = Double.parseDouble(amountField.getText());
            String recipient = JOptionPane.showInputDialog("Enter recipient ID:");
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add("Transferred: $" + amount + " to " + recipient);
                JOptionPane.showMessageDialog(null, "Transfer Successful! Current balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance.");
            }
        } else if (e.getSource() == historyButton) {
            transactionHistoryArea.setText("Transaction History:\n");
            for (String transaction : transactionHistory) {
                transactionHistoryArea.append(transaction + "\n");
            }
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    private void enableButtons(boolean enabled) {
        withdrawButton.setEnabled(enabled);
        depositButton.setEnabled(enabled);
        transferButton.setEnabled(enabled);
        historyButton.setEnabled(enabled);
        quitButton.setEnabled(enabled);
    }

    public static void main(String[] args) {
        new ATMInterface().setVisible(true);
    }
}
