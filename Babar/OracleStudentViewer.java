package Babar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OracleStudentViewer extends JFrame {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    private static final String USER = "SYSTEM";
    private static final String PASS = "student";

    private JTable table;
    private DefaultTableModel model;

    public OracleStudentViewer() {
        setTitle("Oracle Student Database Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Course", "Marks"}, 0);
        table = new JTable(model);

        JButton fetchBtn = new JButton("Fetch Students");
        fetchBtn.addActionListener(e -> fetchStudents());

        add(fetchBtn, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    private void fetchStudents() {
        model.setRowCount(0);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            String sql = "SELECT ID, NAME, COURSE, MARKS FROM STUDENTS";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("COURSE"),
                        rs.getInt("MARKS")
                });
            }

            JOptionPane.showMessageDialog(this, "Data Loaded Successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new OracleStudentViewer();
    }
}

