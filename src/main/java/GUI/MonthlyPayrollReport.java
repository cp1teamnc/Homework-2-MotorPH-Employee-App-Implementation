package GUI;

import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.io.*;
import java.util.List;

public class MonthlyPayrollReport extends JFrame {

    private JTextArea reportArea;

    public MonthlyPayrollReport() {
        setTitle("Monthly Payroll Report");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        add(scrollPane);

        loadPayrollData();

        setVisible(true);
    }

    private void loadPayrollData() {
        Path csvPath = Paths.get("src/main/resources/EmployeeDetails.csv");

        try {
            List<String> lines = Files.readAllLines(csvPath);
            StringBuilder sb = new StringBuilder();

            if (!lines.isEmpty()) {
                sb.append(lines.get(0)).append("\n"); // header 

                for (int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    sb.append(line).append("\n");
                }
            }

            reportArea.setText(sb.toString());

        } catch (IOException e) {
            reportArea.setText("Error loading payroll data:\n" + e.getMessage());
        }
    }
}
