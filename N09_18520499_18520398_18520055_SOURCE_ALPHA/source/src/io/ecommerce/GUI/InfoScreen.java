package io.ecommerce.GUI;

import io.ecommerce.DTO.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoScreen extends JDialog {
    private JPanel infoPanel;
    private JTextField idTextField;
    private JTextField fullnameTextField;
    private JTextField emailTextField;
    private JLabel idLabel;
    private JLabel fullnameLabel;
    private JLabel emailLabel;
    private JTextField birthdateTextField;
    private JLabel birthdateLabel;
    private JTextField genderTextField;
    private JLabel genderLabel;
    private JButton logoutButton;

    public InfoScreen(JFrame parent, Employee employee) {
        setTitle("Thông tin");
        setContentPane(infoPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(400, 300));
        setModal(true);
        setLocationRelativeTo(parent);
        pack();

        idTextField.setText(employee.getEmployeeId());
        fullnameTextField.setText(employee.getFullName());
        emailTextField.setText(employee.getEmail());
        birthdateTextField.setText(employee.getBirthdate().toString());
        genderTextField.setText(employee.getGender().toString());

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.dispose();

                LoginScreen loginScreen = new LoginScreen();
            }
        });

        setVisible(true);
    }
}