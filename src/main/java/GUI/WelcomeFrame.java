package GUI;

/**
 *
 * @author kayejoanneangelikaplaza
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.FontUIResource; //to override default font

public class WelcomeFrame extends JFrame {

    // Custom JLabel for 3D lettering effect
    static class ThreeDLabel extends JLabel {
        private Color shadowColor = new Color(0, 0, 128, 150); // semi-transparent navy shadow
        private int shadowOffset = 3;

        public ThreeDLabel(String text, Font font, Color mainColor) {
            super(text);
            setFont(font);
            setForeground(mainColor);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            // shadow 
            g2.setColor(shadowColor);
            g2.setFont(getFont());
            g2.drawString(getText(), shadowOffset, getHeight() - shadowOffset);

            // main text on top
            g2.setColor(getForeground());
            g2.drawString(getText(), 0, getHeight() - shadowOffset - 3);

            g2.dispose();
        }
    }

    // changes font globally 4 all ui components
    //calls helper method setUIFont para ifollow ng entire ui ang font
    public static void setUIFont(FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    
    //main window (pre-log in muna, straightforward)
    public WelcomeFrame() {
        setTitle("MotorPH Payroll System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //default to center

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //vertical stack
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); //padding

        // MOTOR (navy blue) with 3D effect
        ThreeDLabel motorLabel = new ThreeDLabel("MOTOR", new Font("Verdana", Font.BOLD, 60), new Color(0x00, 0x00, 0x80)); // navy blue #000080
        motorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // PH (red) with 3D effect
        ThreeDLabel phLabel = new ThreeDLabel("PH", new Font("Verdana", Font.BOLD, 60), new Color(0xD3, 0x00, 0x00)); // red #D30000
        phLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // motor+ph
        JPanel motorPHPanel = new JPanel();
        motorPHPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // no horizontal or vertical gap
        motorPHPanel.setOpaque(false);
        motorPHPanel.add(motorLabel);
        motorPHPanel.add(phLabel);
        motorPHPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // payroll system subheading
        JLabel payrollLabel = new JLabel("Payroll System");
        payrollLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        payrollLabel.setForeground(Color.BLACK);
        payrollLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        //button para next step
        JButton continueButton = new JButton("Continue to Payroll");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setMaximumSize(new Dimension(200, 40));
        continueButton.addActionListener(e -> {
            new ComputeOptionsFrame();
            dispose();
        });
        

        panel.add(Box.createVerticalGlue()); // push content vertically centered (upp n d0wnnnnf)
        panel.add(motorPHPanel);
        panel.add(payrollLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(continueButton);
        panel.add(Box.createVerticalGlue()); 

        add(panel);
        setVisible(true);
    }

    // inner class for the compute options menu (select payslip or monthly report)
    public static class ComputeOptionsFrame extends JFrame {

    public ComputeOptionsFrame() {
        setTitle("Choose Payroll Options");
        setSize(500, 300); //window size KEEP SIMILAR SA IBA i think
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centering

        // para sa back button placementhe back button top left
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 30));


        JButton backButton = new JButton("\u2190"); // shet para sa back symbol lang 
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 30));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setToolTipText("Back");
        backButton.addActionListener(e -> {
            new WelcomeFrame();
            dispose();
        });

        
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        

        mainPanel.add(backPanel, BorderLayout.NORTH);

        // center panel to hold label n buttons, stacked tightly
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JLabel selectLabel = new JLabel("What do you want to do?");
        selectLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        selectLabel.setForeground(new Color(0x00, 0x00, 0x80)); // navy blue #000080
        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton specificEmpButton = new JButton("Generate Employee Payslip"); ///for single generation/specific
        JButton allEmpButton = new JButton("Generate Monthly Payroll Report"); // multiple

        specificEmpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        allEmpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        specificEmpButton.setMaximumSize(new Dimension(240, 40));
        allEmpButton.setMaximumSize(new Dimension(270, 40));

        specificEmpButton.addActionListener(e -> {
            new SpecificEmployeeFrame();
            dispose();
        });

        allEmpButton.addActionListener(e -> {
            new MonthlyPayrollReport();
            dispose();
        });

        centerPanel.add(Box.createRigidArea(new Dimension(0, 5))); // tiny space under arrow
        centerPanel.add(selectLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(specificEmpButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(allEmpButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}


    public static void main(String[] args) {
        setUIFont(new FontUIResource(new Font("Verdana", Font.PLAIN, 14)));
        SwingUtilities.invokeLater(WelcomeFrame::new);// para safe
    }
}
