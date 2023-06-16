import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class UserGUI extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField hireDateField;
    private JTextField phoneNumberField;
    private JTextField cityField;
    private JTextField streetField;
    private JTextField numberStreetField;
    private JTextField apartmentNumberField;
    private JTextField postCodeField;
    private JComboBox departmentField;
    private JTextField domainField;
    private JLabel domainLabel;

    private JRadioButton externalUserRadioButton;
    private JRadioButton internalUserRadioButton;
    private JRadioButton externalGuestUserRadioButton;

    private JTextField managerField;
    private JTextField payrollDayField;
    private JButton createButton;

    private Color disabledFieldColor = new Color(200, 200, 200); // Domyślny kolor dla wyłączonych pól

    public UserGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Create New User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel basicPanel = createBasicPanel();
        JPanel hrInfoPanel = createHRInfoPanel();
        JPanel addressPanel = createAddressPanel();

        tabbedPane.addTab("Basic Info", basicPanel);
        tabbedPane.addTab("HR Info", hrInfoPanel);
        tabbedPane.addTab("Address", addressPanel);

        createButton = new JButton("Create User");
        createButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);

        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createBasicPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridx = 1;
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 25));
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Last Name:"), constraints);

        constraints.gridx = 1;
        lastNameField = new JTextField();
        lastNameField.setPreferredSize(new Dimension(200, 25));
        panel.add(lastNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Phone Number:"), constraints);

        constraints.gridx = 1;
        phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(200, 25));
        panel.add(phoneNumberField, constraints);

        return panel;
    }

    private JPanel createHRInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("User Type:"), constraints);

        ButtonGroup userTypeGroup = new ButtonGroup();

        constraints.gridx = 1;
        externalUserRadioButton = new JRadioButton("External User");
        externalUserRadioButton.addActionListener(this);
        panel.add(externalUserRadioButton, constraints);
        userTypeGroup.add(externalUserRadioButton);

        constraints.gridx = 1;
        constraints.gridy = 1;
        internalUserRadioButton = new JRadioButton("Internal User");
        internalUserRadioButton.addActionListener(this);
        panel.add(internalUserRadioButton, constraints);
        userTypeGroup.add(internalUserRadioButton);

        constraints.gridx = 1;
        constraints.gridy = 2;
        externalGuestUserRadioButton = new JRadioButton("External Guest User");
        externalGuestUserRadioButton.addActionListener(this);
        panel.add(externalGuestUserRadioButton, constraints);
        userTypeGroup.add(externalGuestUserRadioButton);


        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Hire Date (YYYY-MM-DD):"), constraints);

        constraints.gridx = 1;
        hireDateField = new JTextField();
        hireDateField.setEnabled(false);
        hireDateField.setBackground(disabledFieldColor);
        hireDateField.setPreferredSize(new Dimension(200, 25));
        panel.add(hireDateField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Manager:"), constraints);

        constraints.gridx = 1;
        managerField = new JTextField();
        managerField.setEnabled(false);
        managerField.setBackground(disabledFieldColor);
        managerField.setPreferredSize(new Dimension(200, 25));
        panel.add(managerField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Payroll Day (YYYY-MM-DD):"), constraints);

        constraints.gridx = 1;
        payrollDayField = new JTextField();
        payrollDayField.setEnabled(false);
        payrollDayField.setBackground(disabledFieldColor);
        payrollDayField.setPreferredSize(new Dimension(200, 25));
        panel.add(payrollDayField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(new JLabel("Department:"), constraints);

        constraints.gridx = 1;
        String[] departmentOptions = {"Sales", "Marketing", "Finance", "IT", "Human Resources"};
        departmentField = new JComboBox<>(departmentOptions);
        departmentField.setEnabled(false);
        departmentField.setBackground(disabledFieldColor);
        departmentField.setPreferredSize(new Dimension(200, 25));
        panel.add(departmentField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;

        panel.add(new JLabel("Domain Name:"), constraints);

        constraints.gridx = 1;
        domainField = new JTextField();
        domainField.setPreferredSize(new Dimension(200, 25));
        panel.add(domainField, constraints);
        constraints.gridx = 1;
        domainField = new JTextField();
        domainField.setEnabled(false);
        domainField.setBackground(disabledFieldColor);
        domainField.setPreferredSize(new Dimension(200, 25));
        panel.add(domainField, constraints);

        return panel;
    }

    private JPanel createAddressPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("City:"), constraints);

        constraints.gridx = 1;
        cityField = new JTextField();
        cityField.setPreferredSize(new Dimension(200, 25));
        cityField.setText("Warszawa");
        panel.add(cityField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Street:"), constraints);

        constraints.gridx = 1;
        streetField = new JTextField();
        streetField.setPreferredSize(new Dimension(200, 25));
        streetField.setText("Marszałkowska");
        panel.add(streetField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Street Number:"), constraints);

        constraints.gridx = 1;
        numberStreetField = new JTextField();
        numberStreetField.setPreferredSize(new Dimension(200, 25));
        numberStreetField.setText("1");
        panel.add(numberStreetField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Apartment Number (Optional):"), constraints);

        constraints.gridx = 1;
        apartmentNumberField = new JTextField();
        apartmentNumberField.setPreferredSize(new Dimension(200, 25));
        panel.add(apartmentNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Post Code:"), constraints);

        constraints.gridx = 1;
        postCodeField = new JTextField();
        postCodeField.setPreferredSize(new Dimension(200, 25));
        postCodeField.setText("11-100");
        panel.add(postCodeField, constraints);

        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            LocalDate hireDate = LocalDate.parse(hireDateField.getText());
            int phoneNumber = Integer.parseInt(phoneNumberField.getText());
            String city = cityField.getText();
            String street = streetField.getText();
            int streetNumber = Integer.parseInt(numberStreetField.getText());
            int apartmentNumber = 0;
            String postCode = postCodeField.getText();

            // Check if the apartment number is provided
            if (!apartmentNumberField.getText().isEmpty()) {
                apartmentNumber = Integer.parseInt(apartmentNumberField.getText());
            }

            Addresses addresses = new Addresses(city, street, streetNumber, postCode);
            String[] department = {departmentField.toString()};

            User user;
            if (externalUserRadioButton.isSelected()) {
                LocalDate payrollDay = LocalDate.parse(payrollDayField.getText());
                user = new ExternalUser(name, lastName, hireDate, phoneNumber, addresses, department, payrollDay);
            }
            else if (externalUserRadioButton.isSelected()) {
                LocalDate payrollDay = LocalDate.parse(payrollDayField.getText());
                String domainName = domainField.getText();
                user = new ExternalGuestUser(name, lastName, hireDate, phoneNumber, addresses, department, payrollDay, domainName);
            }
            else {
                String managerName = managerField.getText();
                // Create a dummy manager user object for now
                User manager = new User(managerName, "", LocalDate.now(), 0, null, new String[]{});
                user = new InternalUser(name, lastName, hireDate, phoneNumber, addresses, department, manager);
            }


            // Do something with the created user object
            System.out.println("New user created: " + user.getName());

            // Show summary dialog
            showSummaryDialog(user);

            // Close the GUI window after creating the user
            dispose();
        }else if (e.getSource() == externalUserRadioButton) {
            // Enable External User fields, disable Internal User fields
            managerField.setEnabled(false);
            managerField.setBackground(disabledFieldColor);
            payrollDayField.setEnabled(true);
            payrollDayField.setBackground(Color.WHITE);
            hireDateField.setEnabled(true);
            hireDateField.setBackground(Color.WHITE);
            departmentField.setEnabled(true);  // Dodaj tę linię
            departmentField.setBackground(Color.WHITE);  // Dodaj tę linię
        } else if (e.getSource() == internalUserRadioButton) {
            // Enable Internal User fields, disable External User fields
            managerField.setEnabled(true);
            managerField.setBackground(Color.WHITE);
            payrollDayField.setEnabled(false);
            payrollDayField.setBackground(disabledFieldColor);
            hireDateField.setEnabled(true);
            hireDateField.setBackground(Color.WHITE);
            departmentField.setEnabled(true);  // Dodaj tę linię
            departmentField.setBackground(Color.WHITE);  // Dodaj tę linię
        }else if (e.getSource() == externalGuestUserRadioButton) {
            // Enable External Guest User fields, disable External User and Internal User fields
            hireDateField.setEnabled(true);
            hireDateField.setBackground(Color.WHITE);
            managerField.setEnabled(false);
            managerField.setBackground(disabledFieldColor);
            payrollDayField.setEnabled(false);
            payrollDayField.setBackground(disabledFieldColor);
            departmentField.setEnabled(true);  // Dodaj tę linię
            departmentField.setBackground(Color.WHITE);  // Dodaj tę linięX
            domainField.setEnabled(true);
            domainField.setBackground(Color.WHITE);
        }



    }

    private void showSummaryDialog(User user) {
        StringBuilder summary = new StringBuilder();
        summary.append("User Summary:\n");
        summary.append("Name: ").append(user.getName()).append("\n");
        summary.append("Last Name: ").append(user.getLastName()).append("\n");
        summary.append("Hire Date: ").append(user.getHireDate()).append("\n");


        if (user instanceof ExternalUser) {
            ExternalUser externalUser = (ExternalUser) user;
            summary.append("User Type: External User\n");
            summary.append("Payroll Day: ").append(externalUser.getPayrollDayInMonth()).append("\n");
        } else if (user instanceof InternalUser) {
            InternalUser internalUser = (InternalUser) user;
            summary.append("User Type: Internal User\n");
            summary.append("Manager: ").append(internalUser.getManager().getName()).append("\n");
        }
        else if (user instanceof ExternalGuestUser) {
            ExternalGuestUser externalGuestUser = (ExternalGuestUser) user;
            summary.append("User Type: External Guest User\n");
            summary.append("Domain Name: ").append(externalGuestUser.getDomainName()).append("\n");
        }


        JOptionPane.showMessageDialog(this, summary.toString(), "User Creation Summary", JOptionPane.INFORMATION_MESSAGE);

    }

}
