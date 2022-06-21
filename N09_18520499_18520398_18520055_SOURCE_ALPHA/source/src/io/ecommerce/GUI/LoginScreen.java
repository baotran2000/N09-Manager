package io.ecommerce.GUI;

import io.ecommerce.BUS.EmployeeBUS;
import io.ecommerce.DTO.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JPanel loginPanel;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JButton exitButton;

    private EmployeeBUS _employeeBUS = new EmployeeBUS();

    private void _validateCredentials() {
        if (emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Email và mật khẩu không được để trống.",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Employee employee = _employeeBUS.getEmployeeByCredentials(
                emailTextField.getText().trim(),
                passwordTextField.getText().trim()
        );

        if (employee != null) {
            ProductScreen productScreen = new ProductScreen(employee);
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập không thành công. Kiểm tra email và mật khẩu.",
                    "!!!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public LoginScreen() {
        setTitle("Đăng nhập");
        setContentPane(loginPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 300));
        pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _validateCredentials();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        LoginScreen loginScreen = new LoginScreen();
    }
}