package daily2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OracleStudentViewer extends JFrame {

    // JDBC connectionc
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "SYSTEM";
    private static final String PASS = "student";

    private JTable table;
    private DefaultTableModel model;

    public OracleStudentViewer() {
        setTitle("Oracle JamiaIslamia Viewer");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table columns: must match Oracle uppercase columns
        model = new DefaultTableModel(new String[]{"ID", "Name", "Marks", "Class"}, 0);
        table = new JTable(model);

        // Buttons
        JButton fetchBtn = new JButton("Fetch Students");
        fetchBtn.addActionListener(e -> fetchStudents());

        JButton insertBtn = new JButton("Insert Student");
        insertBtn.addActionListener(e -> showInsertDialog());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> fetchStudents());

        JPanel topPanel = new JPanel();
        topPanel.add(fetchBtn);
        topPanel.add(insertBtn);
        topPanel.add(refreshBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);

        // Load table on startup
        fetchStudents();
    }

    // Fetch data from JAMIAISLAMIA table
    private void fetchStudents() {
        model.setRowCount(0); // Clear previous rows
        String sql = "SELECT ID, NAME, MARKS, CLASS FROM JAMIAISLAMIA";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("MARKS"),
                        rs.getString("CLASS")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Show dialog to insert a new student
    private void showInsertDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField marksField = new JTextField();
        JTextField classField = new JTextField();

        Object[] message = {
                "ID:", idField,
                "Name:", nameField,
                "Marks:", marksField,
                "Class:", classField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Insert New Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int marks = Integer.parseInt(marksField.getText());
                String cls = classField.getText();

                insertStudent(id, name, marks, cls);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID and Marks must be integers!");
            }
        }
    }

    // Insert student into JAMIAISLAMIA
    private void insertStudent(int id, String name, int marks, String cls) {
        String sql = "INSERT INTO JAMIAISLAMIA (ID, NAME, MARKS, CLASS) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks);
            ps.setString(4, cls);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student inserted successfully!");
                fetchStudents(); // Refresh table after insert
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Insert Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OracleStudentViewer::new);
    }
}
