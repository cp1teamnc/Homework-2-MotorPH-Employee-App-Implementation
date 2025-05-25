package GUI;

/**
 *
 * @author kayejoanneangelikaplaza
 */

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

public class SpecificEmployeeFrame extends JFrame {

    private JTextField empIdField; // input field for employee ID

    private JTextArea empDetailsArea; // rectangle spaceee to display employee details

    //date pickers/dropdown
    private JComboBox<String> fromDay, fromMonth, fromYear, toDay, toMonth, toYear;
   
    private JButton computeButton;//inside payslip rectangle sa right side

    
    //window
    public SpecificEmployeeFrame() {
        setTitle("Payslip Generator"); //sa panel title
        setSize(820, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

  
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 20));

        //area back btton n title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); //pwede makita bg if everr

        
        //same button
        JButton backButton = new JButton("\u2190");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 28));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setToolTipText("Back");
        backButton.addActionListener(e -> {
            new WelcomeFrame.ComputeOptionsFrame();
            dispose();
        });

        
        //title di ko macenter becz of arrow i think
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JLabel titleLabel = new JLabel("Payslip Generator");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(Color.decode("#000080"));
        titlePanel.add(titleLabel);

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        
        //split area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        //in line w txt field n confirm button
        JPanel empIdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel enterIdLabel = new JLabel("Employee ID:");
        empIdField = new JTextField();
        empIdField.setPreferredSize(new Dimension(100, 25));

        //try maglearn ng pag enter ng value with enter lang sa keyboard
        JButton verifyButton = new JButton("Confirm");
        verifyButton.setPreferredSize(new Dimension(60, 20)); //button size, font size
        verifyButton.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        empIdPanel.add(enterIdLabel);
        empIdPanel.add(empIdField);
        empIdPanel.add(verifyButton);

        contentPanel.add(empIdPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); //spacee

        // use null layout with explicit bounds for splitpanel
        JPanel splitPanel = new JPanel(null);
        splitPanel.setPreferredSize(new Dimension(300, 400)); //spacee

        
        //format ng emp details directly from csv, try formatting output from csv 4 emphasis
        empDetailsArea = new JTextArea();
        empDetailsArea.setEditable(false);
        empDetailsArea.setFont(new Font("Verdana", Font.PLAIN, 12));
        empDetailsArea.setLineWrap(true);
        empDetailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(empDetailsArea);
        scrollPane.setBounds(10, 30, 310, 310);

        
        //left rectangle emp deetss
        JPanel detailsPanel = new JPanel(new BorderLayout());
        JLabel detailsLabel = new JLabel("Details");
        detailsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        detailsLabel.setForeground(Color.decode("#002147"));
        detailsPanel.setBorder(BorderFactory.createTitledBorder(""));
        detailsPanel.setBounds(0, 0, 330, 350);
        detailsPanel.add(detailsLabel, BorderLayout.NORTH);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        //right rectangle --payslip, the csv integration stopped hereeezzzzzzzzzz how to remember
        JPanel payslipPanel = new JPanel();
        payslipPanel.setLayout(new BoxLayout(payslipPanel, BoxLayout.Y_AXIS));

        JLabel payslipLabel = new JLabel("Payslip");
        payslipLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        payslipLabel.setForeground(Color.decode("#002147"));
        payslipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  

        payslipPanel.add(payslipLabel);
        payslipPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // add vertical spacing

        JLabel coverageLabel = new JLabel("Select Pay Period:");
        coverageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        coverageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // center horizontally

         // panel for the whole date range
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4)); 

        datePanel.add(new JLabel("From:"));
        fromDay = new JComboBox<>(generateNumbers(1, 31));
        fromMonth = new JComboBox<>(new String[]{"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        fromYear = new JComboBox<>(generateNumbers(2024, 2025));
        datePanel.add(fromDay);
        datePanel.add(fromMonth);
        datePanel.add(fromYear);



        datePanel.add(new JLabel(" To:")); // Added some spaces before "To:" for better separation
        toDay = new JComboBox<>(generateNumbers(1, 31));
        toMonth = new JComboBox<>(new String[]{"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        toYear = new JComboBox<>(generateNumbers(2024, 2025));
        datePanel.add(toDay);
        datePanel.add(toMonth);
        datePanel.add(toYear);

        computeButton = new JButton("Generate Payslip"); //magpop up ang payslip breakdown

        payslipPanel.setBorder(BorderFactory.createTitledBorder(""));
        payslipPanel.setBounds(340, 0, 400, 350);
        payslipPanel.add(payslipLabel);
        payslipPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        payslipPanel.add(coverageLabel);
        payslipPanel.add(datePanel);
        payslipPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        payslipPanel.add(computeButton);

        splitPanel.add(detailsPanel);
        splitPanel.add(payslipPanel);

        contentPanel.add(splitPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        verifyButton.addActionListener(e -> verifyEmployee()); // "confirm"

        // disable muna compute button initially until employee is verified
        computeButton.setEnabled(false);

        // action listener for computeButton here
        computeButton.addActionListener(e -> generatePayslip());

        setVisible(true);//window visibility
    }
    
    // helper method to generate numbers as strings 4 day/year combo boxes
    private String[] generateNumbers(int start, int end) {
        String[] nums = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            nums[i - start] = String.valueOf(i);
        }
        return nums;
    }

   private void verifyEmployee() {
    try {
        String empId = empIdField.getText().trim();

        // blank input
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Employee ID field cannot be blank.", "Input Error", JOptionPane.WARNING_MESSAGE);
            empDetailsArea.setText("");
            computeButton.setEnabled(false);
            return;
        }

        // check for invalid characters (only digits allowed)
        if (!empId.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Employee ID must contain only numbers (no letters or symbols).", "Input Error", JOptionPane.WARNING_MESSAGE);
            empDetailsArea.setText("");
            computeButton.setEnabled(false);
            return;
        }

        // 5-digit check
        if (!Pattern.matches("\\d{5}", empId)) {
            JOptionPane.showMessageDialog(this, "Employee ID must be a 5-digit number.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            empDetailsArea.setText("");
            computeButton.setEnabled(false);
            return;
        }

        //ito nagpagalit saken
        InputStream is = getClass().getClassLoader().getResourceAsStream("EmployeeDetails.csv");
        if (is == null) {
            JOptionPane.showMessageDialog(this, "EmployeeDetails.csv not found.", "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // load & parse csv data
        List<String[]> employees = CsvLoader.loadCsv(is, true, ",");

        boolean found = false;
        StringBuilder details = new StringBuilder();

        //emp ID matching
        for (String[] emp : employees) {
            if (emp.length > 0 && emp[0].equals(empId)) {
                found = true;
                details.append("Employee ID: ").append(emp[0]).append("\n");
                details.append("Name: ").append(emp[2]).append(" ").append(emp[1]).append("\n");
                details.append("Birthday: ").append(emp[3]).append("\n");
                details.append("Address: ").append(emp[4]).append("\n");
                details.append("Phone Number: ").append(emp[5]).append("\n");
                details.append("SSS: ").append(emp[6]).append("\n");
                details.append("Philhealth #: ").append(emp[7]).append("\n");
                details.append("TIN: ").append(emp[8]).append("\n");
                details.append("Pag-ibig: ").append(emp[9]).append("\n");
                details.append("Status: ").append(emp[10]).append("\n");
                details.append("Position: ").append(emp[11]).append("\n");
                details.append("Immediate Supervisor: ").append(emp[12]).append("\n");
                details.append("Basic Salary: ₱").append(emp[13]).append("\n");
                details.append("Rice Subsidy: ₱").append(emp[14]).append("\n");
                details.append("Phone Allowance: ₱").append(emp[15]).append("\n");
                details.append("Clothing Allowance: ₱").append(emp[16]).append("\n");
                details.append("Gross Semi-monthly Rate: ₱").append(emp[17]).append("\n");
                details.append("Hourly Rate: ₱").append(emp[18]).append("\n");
                break;
            }
        }

        //if not found!
        if (!found) {
            empDetailsArea.setText("Employee ID " + empId + " not found.");
            computeButton.setEnabled(false);
        } else {
            empDetailsArea.setText(details.toString());
            computeButton.setEnabled(true);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        empDetailsArea.setText("");
        computeButton.setEnabled(false);
    }
}



    // Add this new method for computeButton action
    private void generatePayslip() {
        // placeholder for payslip generation logic
        // can use empIdField.getText() and date pickers to get range
        String empId = empIdField.getText().trim();

        String fromDate = fromDay.getSelectedItem() + " " + fromMonth.getSelectedItem() + " " + fromYear.getSelectedItem();
        String toDate = toDay.getSelectedItem() + " " + toMonth.getSelectedItem() + " " + toYear.getSelectedItem();

        JOptionPane.showMessageDialog(this,
                "Generate payslip for Employee ID: " + empId + "\nFrom: " + fromDate + "\nTo: " + toDate,
                "Payslip Generation",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpecificEmployeeFrame::new); //yes
    }
}
