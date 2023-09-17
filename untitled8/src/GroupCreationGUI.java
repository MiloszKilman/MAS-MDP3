import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupCreationGUI extends JFrame {
    private JTextField groupNameTextField;
    private JRadioButton securityGroupRadioButton;
    private JRadioButton emailGroupRadioButton;
    private JCheckBox filePermissionCheckBox;
    private JTextField emailAddressTextField;
    private JButton createGroupButton;
    private JLabel summaryLabel;

    public GroupCreationGUI() {
        setTitle("Utwórz grupę");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        groupNameTextField = new JTextField(20);
        securityGroupRadioButton = new JRadioButton("Grupa zabezpieczeń");
        emailGroupRadioButton = new JRadioButton("Grupa mailowa");
        filePermissionCheckBox = new JCheckBox("HasFilePermission");
        emailAddressTextField = new JTextField(20);
        createGroupButton = new JButton("Utwórz grupę");
        summaryLabel = new JLabel();

        ButtonGroup groupTypeButtonGroup = new ButtonGroup();
        groupTypeButtonGroup.add(securityGroupRadioButton);
        groupTypeButtonGroup.add(emailGroupRadioButton);

        emailAddressTextField.setEnabled(false);

        securityGroupRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filePermissionCheckBox.setEnabled(securityGroupRadioButton.isSelected());
                emailAddressTextField.setEnabled(false);
                emailAddressTextField.setBackground(null);
            }
        });

        emailGroupRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emailAddressTextField.setEnabled(emailGroupRadioButton.isSelected());
                filePermissionCheckBox.setEnabled(false);
                groupNameTextField.setBackground(null);
            }
        });

        createGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String groupName = groupNameTextField.getText();

                if (securityGroupRadioButton.isSelected()) {
                    boolean filePermission = filePermissionCheckBox.isSelected();
                    Group group = new Group(groupName);
                    SecurityGroup securityGroup = new SecurityGroup(group, filePermission);
                    summaryLabel.setText("Utworzono grupę zabezpieczeń. File Permission: " + securityGroup.isFilePermission());
                } else if (emailGroupRadioButton.isSelected()) {
                    String emailAddress = emailAddressTextField.getText();
                    Group group = new Group(groupName);
                    MailGroup mailGroup = new MailGroup(group, emailAddress);
                    summaryLabel.setText("Utworzono grupę mailową. Email Address: " + mailGroup.getEmailAddress());
                }
            }
        });

        JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
        groupPanel.add(securityGroupRadioButton);
        groupPanel.add(filePermissionCheckBox);

        JPanel emailGroupPanel = new JPanel();
        emailGroupPanel.setLayout(new BoxLayout(emailGroupPanel, BoxLayout.Y_AXIS));
        emailGroupPanel.add(emailGroupRadioButton);
        emailGroupPanel.add(emailAddressTextField);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(10, 10));
        inputPanel.add(new JLabel("Nazwa grupy:"), BorderLayout.WEST);
        inputPanel.add(groupNameTextField, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        centerPanel.add(groupPanel);
        centerPanel.add(emailGroupPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createGroupButton);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        summaryPanel.add(summaryLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
        setSize(400, 300); // Ustawia rozmiar okna na 400x300 pikseli
        setLookAndFeel(); // Ustawia wygląd aplikacji na systemowy

        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
