package main;

import javax.swing.*;
import inputs.MouseInputs;
import utils.DBUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {

    // Variables to manage registration state
    public boolean isCurrentPageRegister = false;

    // UI components for registration form
    public JTextField FullName;
    public JTextField Email;
    public JPasswordField Password;
    public JTextField DOB;
    public JTextField PN;

    private final JLabel RegisterCredentialsIncorrect;

    // Constructor for the RegisterPanel
    public RegisterPanel(Window window) {
        MouseInputs mouseInputs = new MouseInputs(window);

        // Initialize UI components
        this.FullName = new JTextField();
        this.Email = new JTextField();
        this.Password = new JPasswordField();
        this.DOB = new JTextField();
        this.PN = new JTextField();
        JButton registerBtn = new JButton("Register");
        JLabel argumentsNotFilled = new JLabel("You haven't filled everything or \n the arguments already exist");

        JLabel fullName = new JLabel("Full Name");
        JLabel email = new JLabel("Email");
        JLabel password = new JLabel("Password");
        JLabel dob = new JLabel("Date of Birth(yyyy/mm/dd)");
        JLabel pn = new JLabel("Phone Number");
        JButton backBtnRegister = new JButton("Back");
        this.RegisterCredentialsIncorrect = new JLabel("Your register credentials are incorrect");

        // Add mouse listener for user inputs
        addMouseListener(mouseInputs);

        // Set focus to this panel
        requestFocus();

        // Set layout to null for custom component placement
        setLayout(null);

        // Configure visibility of credentials error label
        this.RegisterCredentialsIncorrect.setVisible(false);

        // Configure Back button for registration
        backBtnRegister.setBounds(10, 10, 80, 50);
        backBtnRegister.setBackground(new Color(81, 200, 120));
        backBtnRegister.setForeground(Color.BLACK);

        // Configure Email input
        this.Email.setBackground(new Color(239, 239, 239));
        this.Email.setBorder(null);
        this.Email.setBounds(500, 160, 300, 30);
        this.Email.setForeground(Color.BLACK);

        email.setFont(new Font("Arial", Font.BOLD, 16));
        email.setBounds(500, 140, 300, 15);
        email.setForeground(Color.BLACK);

        // Configure Full Name input
        this.FullName.setBackground(new Color(239, 239, 239));
        this.FullName.setBorder(null);
        this.FullName.setBounds(500, 160 + 80, 300, 30);
        this.FullName.setForeground(Color.BLACK);

        fullName.setFont(new Font("Arial", Font.BOLD, 16));
        fullName.setBounds(500, 140 + 80, 300, 15);
        fullName.setForeground(Color.BLACK);

        // Configure Password input
        this.Password.setBackground(new Color(239, 239, 239));
        this.Password.setBorder(null);
        this.Password.setBounds(500, 160 + 160, 300, 30);
        this.Password.setForeground(Color.BLACK);

        password.setFont(new Font("Arial", Font.BOLD, 16));
        password.setBounds(500, 140 + 160, 300, 15);
        password.setForeground(Color.BLACK);

        // Configure Date of Birth input
        this.DOB.setBackground(new Color(239, 239, 239));
        this.DOB.setBorder(null);
        this.DOB.setBounds(500, 160 + 240, 300, 30);
        this.DOB.setForeground(Color.BLACK);

        dob.setFont(new Font("Arial", Font.BOLD, 16));
        dob.setBounds(500, 140 + 240, 300, 15);
        dob.setForeground(Color.BLACK);

        // Configure Phone Number input
        this.PN.setBackground(new Color(239, 239, 239));
        this.PN.setBorder(null);
        this.PN.setBounds(500, 160 + 320, 300, 30);
        this.PN.setForeground(Color.BLACK);

        pn.setFont(new Font("Arial", Font.BOLD, 16));
        pn.setBounds(500, 140 + 320, 300, 15);
        pn.setForeground(Color.BLACK);

        // Configure Register button
        registerBtn.setBounds(580, 160 + 400, 100, 60);
        registerBtn.setBackground(new Color(81, 200, 120));
        registerBtn.setForeground(Color.BLACK);

        argumentsNotFilled.setBounds(500, 100, 100, 30);
        argumentsNotFilled.setForeground(Color.RED);

        // Configure credentials error label
        this.RegisterCredentialsIncorrect.setBackground(new Color(188, 84, 73));
        this.RegisterCredentialsIncorrect.setForeground(Color.RED);
        this.RegisterCredentialsIncorrect.setBounds(500, 100, 300, 10);

        // Add action listener for the Register button
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Attempt to register the user
                    if (DBUtils.RequestRegister(Email.getText(), Password.getText(), FullName.getText(), DOB.getText(), PN.getText())) {
                        // If successful, navigate to the login page
                        window.getWindowFrame().removeRegisterPage();
                        if (!window.getWindowFrame().loginPage.isVisible()) {
                            window.getWindowFrame().loginPage.setVisible(true);
                            RegisterCredentialsIncorrect.setVisible(false);
                        } else window.getWindowFrame().addLoginPage();

                    } else {
                        // If registration fails, display an error message
                        System.out.println("Register Failed!");
                        RegisterCredentialsIncorrect.setVisible(true);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Add action listener for the Back button
        backBtnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the welcome page
                window.getWindowFrame().registerPage.setVisible(false);
                window.getWindowFrame().welcomePage.setVisible(true);
            }
        });

        // Add components to the panel
        add(this.RegisterCredentialsIncorrect);
        add(this.FullName);
        add(this.Email);
        add(this.Password);
        add(this.DOB);
        add(this.PN);
        add(fullName);
        add(email);
        add(password);
        add(dob);
        add(pn);
        add(registerBtn);
        add(backBtnRegister);
        setSize(1280, 720);
        setBackground(new Color(255, 255, 255));
    }
}
