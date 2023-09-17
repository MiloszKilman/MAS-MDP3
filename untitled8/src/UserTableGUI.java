import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserTableGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTable groupTable;
    private DefaultTableModel groupTableModel;
    private List<User> selectedUsers;
    private DatabaseManager databaseManager;

    public UserTableGUI() {
        databaseManager = new DatabaseManager();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("User Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tworzenie kolumn dla wszystkich atrybutów User
        String[] columnNames = {"Name", "Last Name", "Hire Date", "Phone Number"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Zakładki
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Users", createUsersPanel(scrollPane));
        tabbedPane.addTab("Groups", createGroupsPanel());

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Ustawianie na pełną szerokość ekranu
        setLookAndFeel(); // Ustawianie wyglądu aplikacji na systemowy
        frame.setVisible(true);
    }

    private JPanel createUsersPanel(JScrollPane scrollPane) {
        JPanel usersPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Przycisk "Create new User"
        JButton createButton = new JButton("Create new User");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserGUI userGUI = new UserGUI();
            }
        });
        buttonPanel.add(createButton);

        // Przycisk "Refresh"
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadUserTableData();
            }
        });
        buttonPanel.add(refreshButton);

        // Przycisk "Remove User"
        JButton removeButton = new JButton("Remove User");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int modelRow = table.convertRowIndexToModel(selectedRows[i]);
                    String firstName = (String) tableModel.getValueAt(modelRow, 0);
                    String lastName = (String) tableModel.getValueAt(modelRow, 1);
                    tableModel.removeRow(modelRow);
                }
            }
        });
        buttonPanel.add(removeButton);

        usersPanel.add(buttonPanel, BorderLayout.NORTH);
        usersPanel.add(scrollPane, BorderLayout.CENTER);

        return usersPanel;
    }

    private JPanel createGroupsPanel() {
        JPanel groupsPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Przycisk "Create Group"
        JButton createGroupButton = new JButton("Create Group");
        createGroupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GroupCreationGUI groupCreationGUI = new GroupCreationGUI();
            }
        });
        buttonPanel.add(createGroupButton);

        // Przycisk "Refresh"
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGroupTableData();
            }
        });
        buttonPanel.add(refreshButton);

        // Przycisk "Add Users"
        JButton addUsersButton = new JButton("Add Users");
        addUsersButton.setEnabled(false); // Domyslnie wylaczony
        addUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = groupTable.getSelectedRow();
                if (selectedRow != -1) {
                    String groupName = (String) groupTableModel.getValueAt(selectedRow, 0);
                    Group selectedGroup = getGroupByName(groupName);
                    if (selectedGroup != null) {
                        showUserSelectionDialog(selectedGroup);
                    }
                }
            }
        });
        buttonPanel.add(addUsersButton);

        groupsPanel.add(buttonPanel, BorderLayout.NORTH);

        // Tabela grup
        String[] groupColumnNames = {"Group Name"};
        groupTableModel = new DefaultTableModel(groupColumnNames, 0);
        groupTable = new JTable(groupTableModel);
        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean isRowSelected = groupTable.getSelectedRow() != -1;
                addUsersButton.setEnabled(isRowSelected);
            }
        });
        JScrollPane scrollPane = new JScrollPane(groupTable);
        groupsPanel.add(scrollPane, BorderLayout.CENTER);

        return groupsPanel;
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showUserSelectionDialog(Group group) {
        JDialog dialog = new JDialog(frame, "Select Users", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLayout(new BorderLayout());

        DefaultListModel<User> userModel = new DefaultListModel<>();
        JList<User> userList = new JList<>(userModel);
        JScrollPane scrollPane = new JScrollPane(userList);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Przycisk "OK"
        JButton okButton = new JButton("Add to Group");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> selectedUsers = userList.getSelectedValuesList();
                group.setMembers(selectedUsers);
                dialog.dispose();
                showSummaryDialog(selectedUsers);
            }
        });
        buttonPanel.add(okButton);

        // Przycisk "Cancel"
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Wczytywanie dostępnych użytkowników
        List<User> users = databaseManager.getUsers();
        for (User user : users) {
            userModel.addElement(user);
        }

        dialog.setVisible(true);
    }
    private void showSummaryDialog(List<User> users) {
        StringBuilder summary = new StringBuilder();
        summary.append("Added users to group:\n");
        for (User user : users) {
            summary.append(user.getName()).append(" ").append(user.getLastName()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, summary.toString(), "Summary", JOptionPane.INFORMATION_MESSAGE);
    }


    private void loadUserTableData() {
        // Wczytywanie danych z tabeli "users" do tabeli
        tableModel.setRowCount(0); // Wyczyszczenie zawartości tabeli

        List<User> users = databaseManager.getUsers(); // Pobranie listy użytkowników z bazy danych

        for (User user : users) {
            tableModel.addRow(new Object[]{user.getName(), user.getLastName(), user.getHireDate(),
                    user.getPhoneNumber()});
        }

        // Odświeżenie widoku tabeli
        tableModel.fireTableDataChanged();
    }

    private void loadGroupTableData() {
        // Wczytywanie danych z bazy do tabeli
        groupTableModel.setRowCount(0); // Wyczyszczenie zawartości tabeli

        List<Group> groups = databaseManager.getGroups(); // Pobranie listy grup z bazy danych

        for (Group group : groups) {
            groupTableModel.addRow(new Object[]{group.getName()});
        }

        // Odświeżenie widoku tabeli
        groupTableModel.fireTableDataChanged();
    }

    private Group getGroupByName(String groupName) {
        List<Group> groups = databaseManager.getGroups();
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }

}
