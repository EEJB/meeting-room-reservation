/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package qcureservations_ver2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author narag
 */
public class ComputerAssignmentRecords extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard_MeetingRoomReservation
     */
    public ComputerAssignmentRecords() {
        initComponents();
        loadReservationTable();
        
        //DO NOT CHANGE THESE
        setExtendedState(MAXIMIZED_BOTH);
        
        setImageToLabel(QCU_logo, "src/Image/QCU LOGO 300X300.png");
        
        HeaderName.setText("<html>"
        + "<span style='color:rgb(11,42,122); font-family:Roboto; font-weight:bold;'>QC</span>"
        + "<span style='color:rgb(239,58,93); font-family:Roboto; font-weight:bold;'>U</span> "
        + "<span style='color:rgb(52,58,70); font-family:Roboto; font-weight:bold;'>Reservations</span>"
        + "</html>");
        
        //NAVIGATION ICON - DO NOT CHANGE
        setImageToLabel(dashboard_icon, "src/Image/INACTIVE DASHBOARD.png");
        setImageToLabel(dr_icon, "src/Image/INACTIVE DAILY RECORD.png");
        setImageToLabel(rr_icon, "src/Image/INACTIVE RESERVATION.png");
        setImageToLabel(cio_icon, "src/Image/INACTIVE CHECK IN_OUT.png");
        setImageToLabel(mrm_icon, "src/Image/INACTIVE MEETING ROOM.png");
        setImageToLabel(car_icon, "src/Image/ACTIVE COMPUTER ASSIGNMENT.png");
        setImageToLabel(cm_icon, "src/Image/INACTIVE COMPUTER MANAGEMENT.png");
        setImageToLabel(hul_icon, "src/Image/INACTIVE HISTORY.png");
        setImageToLabel(logout_icon, "src/Image/INACTIVE LOGOUT.png");
        
        //HEADER IMAGE
        setImageToLabel(user_image, "src/Image/USER PROFILE.png");
        setImageToLabel(headerr_icon, "src/Image/ACTIVE COMPUTER ASSIGNMENT.png");
        
         //Scrollbar
                // Customize the vertical scrollbar
        JScrollBar verticalBar = ScrollBar.getVerticalScrollBar();
        verticalBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100); // thumb
                this.trackColor = new Color(230, 230, 230); // track
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(80, 80, 80)); // thumb color
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.dispose();
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        // Optional: adjust width of scrollbar
        verticalBar.setPreferredSize(new Dimension(10, Integer.MAX_VALUE));

        
        
        // START NEW CODE HERE
        
        CustomTableStyler.applyStyle(CAR_Table);
        
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setForeground(Color.WHITE); // text color

                } else {
                    c.setForeground(Color.BLACK); // default text color
                    c.setBackground(Color.WHITE); // default row background
                }

                return c;
            }
        };

        // Apply to all columns
        for (int i = 0; i < CAR_Table.getColumnCount(); i++) {
            CAR_Table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        
        
        // Assuming your JDateChooser is named dateChooser
        datePick.getDateEditor().setEnabled(false);
    }

    //DO NOT CHANGE THIS - use this for resizing image to match the size of the label
    private void setImageToLabel(JLabel label, String imagePath)
    {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedImg));
    }
    
    //DO NOT CHANGE - NAVIGATION ANIMATION
    private Color interpolateColor(Color start, Color end, float fraction) 
    {
        int red = (int) (start.getRed() + (end.getRed() - start.getRed()) * fraction);
        int green = (int) (start.getGreen() + (end.getGreen() - start.getGreen()) * fraction);
        int blue = (int) (start.getBlue() + (end.getBlue() - start.getBlue()) * fraction);
        return new Color(red, green, blue);
    }

    //DO NOT CHANGE - NAVIGATION ANIMATION
    private void animateColorTransition(JComponent component, Color startColor, Color endColor, int duration) {
        int frames = 20; // Number of frames in the animation
        int delay = duration / frames; // Time between frames
        final int[] currentFrame = {0};

        Timer timer = new Timer(delay, null);
        timer.addActionListener(e -> 
        {
            float fraction = (float) currentFrame[0] / (float) frames;
            component.setBackground(interpolateColor(startColor, endColor, fraction));
            currentFrame[0]++;
            if (currentFrame[0] > frames) 
            {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
    
    public class RoundedPanel extends JPanel {
    private int cornerRadius;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // make background transparent for smooth edges
    }

    public RoundedPanel() {
        this(25); // default radius
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
    }
}
    

public class RoundedTextField extends JTextField {
    private int cornerRadius;
    private boolean showBorder = true;
    private Color borderColor = Color.decode("#d9d9d9");

    public RoundedTextField(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
        setDisabledTextColor(Color.decode("#000000"));
    }

    public RoundedTextField() {
        this(25); // default radius
    }

    // Text padding
    @Override
    public Insets getInsets() {
        return new Insets(8, 12, 8, 12); // top, left, bottom, right
    }

    // Allow toggling the border
    public void setShowBorder(boolean show) {
        this.showBorder = show;
        repaint();
    }

    // Allow changing the border color
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        if (!isEnabled()) {
            g2.setColor(new Color(230, 230, 230));
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (showBorder) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
        }
    }
}

public class CustomTableStyler {

    public static void applyStyle(JTable table) {
        // === Header Customization ===
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 60)); // Header height

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setOpaque(true);
                label.setBackground(Color.decode("#e8e9ef"));
                label.setForeground(Color.BLACK);
                label.setFont(new Font("Roboto", Font.BOLD, 16));
                label.setHorizontalAlignment(CENTER);

                // Simulate 5px grid lines with white borders
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 5, Color.white));
                return label;
            }
        });

        // === Row Style ===
        table.setRowHeight(50); // Default row height

        // Custom cell renderer to simulate thick 5px grid lines
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Roboto", Font.PLAIN, 14));
                label.setForeground(Color.BLACK);
                label.setOpaque(true);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        label.setBackground(Color.decode("#f8f7fd"));
                    } else {
                        label.setBackground(Color.decode("#e8e9ef"));
                        
                    }
                }

                // Simulate 5px grid lines with white borders
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 5, Color.WHITE));
                return label;
            }
        });

        // === Optional: clean up native grid and borders ===
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0)); // Remove native spacing

        table.setBorder(BorderFactory.createEmptyBorder());

        // ScrollPane border (optional)
        if (table.getParent() instanceof JViewport) {
            Container parent = table.getParent().getParent();
            if (parent instanceof JScrollPane) {
                ((JScrollPane) parent).setBorder(BorderFactory.createEmptyBorder());
            }
        }

        // Apply row height manually to all rows
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setRowHeight(i, 50); // Or your preferred height
        }
    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_Panel = new javax.swing.JPanel();
        user_image = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        headerr_icon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new RoundedPanel(30);
        jPanel2 = new RoundedPanel(30);
        db_mrco_name = new javax.swing.JLabel();
        db_mrco_std_no = new javax.swing.JLabel();
        db_mrco_mtng_rm = new javax.swing.JLabel();
        db_mrco_date = new javax.swing.JLabel();
        CAR_std_name = new javax.swing.JLabel();
        CAR_std_no = new javax.swing.JLabel();
        CAR_meeting_room = new javax.swing.JLabel();
        CAR_date = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        CAR_full_name = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        CAR_start_time = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        CAR_purpose = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        CAR_end_time = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        CAR_eml_add = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        CAR_prgrm = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        CAR_remarks = new javax.swing.JLabel();
        CAR_processed_by = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        CAR_end_time1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CAR_search = new RoundedTextField(20);
        ScrollBar = new javax.swing.JScrollPane();
        CAR_Table = new javax.swing.JTable();
        datePick = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        Menu_Panel = new javax.swing.JPanel();
        QCU_logo = new javax.swing.JLabel();
        HeaderName = new javax.swing.JLabel();
        dashboard_panel = new javax.swing.JPanel();
        dashboard_indicator = new javax.swing.JPanel();
        dashboard_icon = new javax.swing.JLabel();
        dashboard_label = new javax.swing.JLabel();
        dashboard_panel3 = new javax.swing.JPanel();
        dashboard_indicator3 = new javax.swing.JPanel();
        logo_img5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dailyRecord_Panel = new javax.swing.JPanel();
        dr_indicator = new javax.swing.JPanel();
        dr_icon = new javax.swing.JLabel();
        dr_label = new javax.swing.JLabel();
        dashboard_panel1 = new javax.swing.JPanel();
        dashboard_indicator1 = new javax.swing.JPanel();
        logo_img3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CheckInOutPanel = new javax.swing.JPanel();
        cio_indicator = new javax.swing.JPanel();
        cio_icon = new javax.swing.JLabel();
        cio_label = new javax.swing.JLabel();
        dashboard_panel5 = new javax.swing.JPanel();
        dashboard_indicator5 = new javax.swing.JPanel();
        logo_img7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ReservationRecord_Panel = new javax.swing.JPanel();
        rr_indicator = new javax.swing.JPanel();
        rr_icon = new javax.swing.JLabel();
        rr_label = new javax.swing.JLabel();
        dashboard_panel7 = new javax.swing.JPanel();
        dashboard_indicator7 = new javax.swing.JPanel();
        logo_img8 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CAR_Panel = new javax.swing.JPanel();
        car_indicator = new javax.swing.JPanel();
        car_icon = new javax.swing.JLabel();
        car_label = new javax.swing.JLabel();
        dashboard_panel9 = new javax.swing.JPanel();
        dashboard_indicator9 = new javax.swing.JPanel();
        logo_img10 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        HUL_Panel = new javax.swing.JPanel();
        hul_indicator = new javax.swing.JPanel();
        hul_icon = new javax.swing.JLabel();
        hul_label = new javax.swing.JLabel();
        dashboard_panel11 = new javax.swing.JPanel();
        dashboard_indicator11 = new javax.swing.JPanel();
        logo_img12 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        MRM_Panel = new javax.swing.JPanel();
        mrm_indicator = new javax.swing.JPanel();
        mrm_icon = new javax.swing.JLabel();
        mrm_label = new javax.swing.JLabel();
        dashboard_panel13 = new javax.swing.JPanel();
        dashboard_indicator13 = new javax.swing.JPanel();
        logo_img14 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Logout_Panel = new javax.swing.JPanel();
        logout_icon = new javax.swing.JLabel();
        logout_label = new javax.swing.JLabel();
        dashboard_panel15 = new javax.swing.JPanel();
        dashboard_indicator15 = new javax.swing.JPanel();
        logo_img16 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dashboard_panel16 = new javax.swing.JPanel();
        dashboard_indicator16 = new javax.swing.JPanel();
        logo_img17 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dashboard_panel17 = new javax.swing.JPanel();
        dashboard_indicator17 = new javax.swing.JPanel();
        logo_img18 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dashboard_panel18 = new javax.swing.JPanel();
        dashboard_indicator18 = new javax.swing.JPanel();
        logo_img19 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        dashboard_panel19 = new javax.swing.JPanel();
        dashboard_indicator19 = new javax.swing.JPanel();
        logo_img20 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        dashboard_panel20 = new javax.swing.JPanel();
        dashboard_indicator20 = new javax.swing.JPanel();
        logo_img21 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dashboard_panel21 = new javax.swing.JPanel();
        dashboard_indicator21 = new javax.swing.JPanel();
        logo_img22 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        CM_Panel = new javax.swing.JPanel();
        cm_indicator = new javax.swing.JPanel();
        cm_icon = new javax.swing.JLabel();
        cm_label = new javax.swing.JLabel();
        dashboard_panel22 = new javax.swing.JPanel();
        dashboard_indicator23 = new javax.swing.JPanel();
        logo_img24 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        dashboard_panel23 = new javax.swing.JPanel();
        dashboard_indicator24 = new javax.swing.JPanel();
        logo_img25 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        dashboard_panel24 = new javax.swing.JPanel();
        dashboard_indicator25 = new javax.swing.JPanel();
        logo_img26 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        dashboard_panel25 = new javax.swing.JPanel();
        dashboard_indicator26 = new javax.swing.JPanel();
        logo_img27 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        dashboard_panel26 = new javax.swing.JPanel();
        dashboard_indicator27 = new javax.swing.JPanel();
        logo_img28 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        dashboard_panel27 = new javax.swing.JPanel();
        dashboard_indicator28 = new javax.swing.JPanel();
        logo_img29 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        dashboard_panel28 = new javax.swing.JPanel();
        dashboard_indicator29 = new javax.swing.JPanel();
        logo_img30 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QCU RESERVATIONS | Computer Assignment Records");

        Main_Panel.setBackground(Color.WHITE);
        Main_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Main_Panel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Main_Panel.setPreferredSize(new java.awt.Dimension(1542, 756));
        Main_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ACTIVE DASHBOARD.png"))); // NOI18N
        user_image.setPreferredSize(new java.awt.Dimension(30, 30));
        Main_Panel.add(user_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(11, 42, 122));
        jLabel3.setText("Computer Assignment Record");
        Main_Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        headerr_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ACTIVE DASHBOARD.png"))); // NOI18N
        headerr_icon.setPreferredSize(new java.awt.Dimension(50, 50));
        Main_Panel.add(headerr_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 50));

        jLabel5.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 58, 70));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Hi, [Name]!  ");
        Main_Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 340, -1));

        jPanel1.setBackground(new java.awt.Color(248, 247, 253));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(217, 217, 217));

        db_mrco_name.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        db_mrco_name.setText("Name :");

        db_mrco_std_no.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        db_mrco_std_no.setText("Student No.");

        db_mrco_mtng_rm.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        db_mrco_mtng_rm.setText("PC Number");

        db_mrco_date.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        db_mrco_date.setText("Date:");

        CAR_std_name.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CAR_std_name.setText(" ");

        CAR_std_no.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CAR_std_no.setText(" ");

        CAR_meeting_room.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CAR_meeting_room.setText(" ");

        CAR_date.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        CAR_date.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(db_mrco_name)
                    .addComponent(CAR_std_name, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CAR_std_no, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(db_mrco_std_no))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(CAR_meeting_room, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(db_mrco_mtng_rm)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CAR_date, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(db_mrco_date))
                .addGap(226, 226, 226))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(db_mrco_name)
                    .addComponent(db_mrco_std_no)
                    .addComponent(db_mrco_mtng_rm)
                    .addComponent(db_mrco_date))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CAR_std_name)
                    .addComponent(CAR_std_no)
                    .addComponent(CAR_meeting_room)
                    .addComponent(CAR_date))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 17, 1010, -1));

        jLabel23.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel23.setText("General Information");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 99, -1, -1));

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel31.setText("Contact Number:");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 134, -1, -1));

        CAR_full_name.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_full_name.setText(" ");
        jPanel1.add(CAR_full_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 155, -1, -1));

        jLabel33.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel33.setText("Start Time:");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 184, -1, -1));

        CAR_start_time.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_start_time.setText(" ");
        jPanel1.add(CAR_start_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 205, -1, -1));

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel35.setText("End Time:");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 184, -1, -1));

        CAR_purpose.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_purpose.setText(" ");
        jPanel1.add(CAR_purpose, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 259, 257, -1));

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel37.setText("Purpose:");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 238, -1, -1));

        CAR_end_time.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_end_time.setText(" ");
        jPanel1.add(CAR_end_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 205, -1, -1));

        jLabel39.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel39.setText("Email Address : ");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 134, -1, -1));

        CAR_eml_add.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_eml_add.setText(" ");
        jPanel1.add(CAR_eml_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 155, 265, -1));

        jLabel41.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel41.setText("Program:");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 134, -1, -1));

        CAR_prgrm.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_prgrm.setText(" ");
        jPanel1.add(CAR_prgrm, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 155, 93, -1));

        jLabel36.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel36.setText("Time Range");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 184, -1, -1));

        jLabel34.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel34.setText("Remarks:");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 238, -1, -1));

        CAR_remarks.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_remarks.setText(" ");
        jPanel1.add(CAR_remarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 259, -1, -1));

        CAR_processed_by.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jPanel1.add(CAR_processed_by, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 259, -1, -1));

        jLabel38.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel38.setText("Processed By:");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 238, -1, -1));

        CAR_end_time1.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        CAR_end_time1.setText(" ");
        jPanel1.add(CAR_end_time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 200, -1, -1));

        Main_Panel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 1060, 300));
        Main_Panel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 1060, 20));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setText("Date:");
        Main_Panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setText("Search:");
        Main_Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        CAR_search.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        CAR_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CAR_searchKeyReleased(evt);
            }
        });
        Main_Panel.add(CAR_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 580, 50));

        CAR_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Student Name", "Assigned PC", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CAR_Table.setMaximumSize(new java.awt.Dimension(225, 250));
        CAR_Table.setMinimumSize(new java.awt.Dimension(225, 250));
        CAR_Table.setPreferredSize(new java.awt.Dimension(225, 250));
        CAR_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CAR_TableMouseClicked(evt);
            }
        });
        ScrollBar.setViewportView(CAR_Table);

        Main_Panel.add(ScrollBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 1060, 190));

        datePick.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePickPropertyChange(evt);
            }
        });
        Main_Panel.add(datePick, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 240, 50));

        jButton1.setLabel("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Main_Panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 480, 100, 50));

        Menu_Panel.setBackground(new java.awt.Color(219, 229, 255));
        Menu_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        QCU_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        QCU_logo.setPreferredSize(new java.awt.Dimension(80, 80));
        Menu_Panel.add(QCU_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        HeaderName.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        HeaderName.setForeground(new java.awt.Color(11, 42, 122));
        HeaderName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HeaderName.setText("QCU Reservations");
        Menu_Panel.add(HeaderName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 250, -1));

        dashboard_panel.setBackground(new java.awt.Color(219, 229, 255));
        dashboard_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboard_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboard_panelMouseExited(evt);
            }
        });
        dashboard_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout dashboard_indicatorLayout = new javax.swing.GroupLayout(dashboard_indicator);
        dashboard_indicator.setLayout(dashboard_indicatorLayout);
        dashboard_indicatorLayout.setHorizontalGroup(
            dashboard_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicatorLayout.setVerticalGroup(
            dashboard_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel.add(dashboard_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        dashboard_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        dashboard_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel.add(dashboard_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        dashboard_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        dashboard_label.setForeground(new java.awt.Color(52, 58, 70));
        dashboard_label.setText("Dashboard");
        dashboard_panel.add(dashboard_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator3Layout = new javax.swing.GroupLayout(dashboard_indicator3);
        dashboard_indicator3.setLayout(dashboard_indicator3Layout);
        dashboard_indicator3Layout.setHorizontalGroup(
            dashboard_indicator3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator3Layout.setVerticalGroup(
            dashboard_indicator3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel3.add(dashboard_indicator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img5.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel3.add(logo_img5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel4.setText("Dashboard");
        dashboard_panel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel.add(dashboard_panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(dashboard_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dailyRecord_Panel.setBackground(new java.awt.Color(219, 229, 255));
        dailyRecord_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dailyRecord_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dailyRecord_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dailyRecord_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dailyRecord_PanelMouseExited(evt);
            }
        });
        dailyRecord_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dr_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout dr_indicatorLayout = new javax.swing.GroupLayout(dr_indicator);
        dr_indicator.setLayout(dr_indicatorLayout);
        dr_indicatorLayout.setHorizontalGroup(
            dr_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dr_indicatorLayout.setVerticalGroup(
            dr_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dailyRecord_Panel.add(dr_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        dr_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        dr_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        dailyRecord_Panel.add(dr_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        dr_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        dr_label.setForeground(new java.awt.Color(52, 58, 70));
        dr_label.setText("Daily Record");
        dailyRecord_Panel.add(dr_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator1Layout = new javax.swing.GroupLayout(dashboard_indicator1);
        dashboard_indicator1.setLayout(dashboard_indicator1Layout);
        dashboard_indicator1Layout.setHorizontalGroup(
            dashboard_indicator1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator1Layout.setVerticalGroup(
            dashboard_indicator1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel1.add(dashboard_indicator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img3.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel1.add(logo_img3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel2.setText("Dashboard");
        dashboard_panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dailyRecord_Panel.add(dashboard_panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(dailyRecord_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 360, 50));

        CheckInOutPanel.setBackground(new java.awt.Color(219, 229, 255));
        CheckInOutPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckInOutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckInOutPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CheckInOutPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CheckInOutPanelMouseExited(evt);
            }
        });
        CheckInOutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cio_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout cio_indicatorLayout = new javax.swing.GroupLayout(cio_indicator);
        cio_indicator.setLayout(cio_indicatorLayout);
        cio_indicatorLayout.setHorizontalGroup(
            cio_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cio_indicatorLayout.setVerticalGroup(
            cio_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        CheckInOutPanel.add(cio_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        cio_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        cio_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        CheckInOutPanel.add(cio_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        cio_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        cio_label.setForeground(new java.awt.Color(52, 58, 70));
        cio_label.setText("Check-in & Check-out Record");
        CheckInOutPanel.add(cio_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator5Layout = new javax.swing.GroupLayout(dashboard_indicator5);
        dashboard_indicator5.setLayout(dashboard_indicator5Layout);
        dashboard_indicator5Layout.setHorizontalGroup(
            dashboard_indicator5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator5Layout.setVerticalGroup(
            dashboard_indicator5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel5.add(dashboard_indicator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img7.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel5.add(logo_img7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel6.setText("Dashboard");
        dashboard_panel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        CheckInOutPanel.add(dashboard_panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(CheckInOutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, 50));

        ReservationRecord_Panel.setBackground(new java.awt.Color(219, 229, 255));
        ReservationRecord_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReservationRecord_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReservationRecord_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReservationRecord_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReservationRecord_PanelMouseExited(evt);
            }
        });
        ReservationRecord_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rr_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout rr_indicatorLayout = new javax.swing.GroupLayout(rr_indicator);
        rr_indicator.setLayout(rr_indicatorLayout);
        rr_indicatorLayout.setHorizontalGroup(
            rr_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rr_indicatorLayout.setVerticalGroup(
            rr_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        ReservationRecord_Panel.add(rr_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        rr_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        rr_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        ReservationRecord_Panel.add(rr_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        rr_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        rr_label.setForeground(new java.awt.Color(52, 58, 70));
        rr_label.setText("Reservation Record");
        ReservationRecord_Panel.add(rr_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator7Layout = new javax.swing.GroupLayout(dashboard_indicator7);
        dashboard_indicator7.setLayout(dashboard_indicator7Layout);
        dashboard_indicator7Layout.setHorizontalGroup(
            dashboard_indicator7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator7Layout.setVerticalGroup(
            dashboard_indicator7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel7.add(dashboard_indicator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img8.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel7.add(logo_img8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel8.setText("Dashboard");
        dashboard_panel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        ReservationRecord_Panel.add(dashboard_panel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(ReservationRecord_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, 50));

        CAR_Panel.setBackground(new java.awt.Color(219, 229, 255));
        CAR_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CAR_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CAR_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CAR_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CAR_PanelMouseExited(evt);
            }
        });
        CAR_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        car_indicator.setBackground(new java.awt.Color(11, 42, 122));
        car_indicator.setForeground(new java.awt.Color(11, 42, 122));

        javax.swing.GroupLayout car_indicatorLayout = new javax.swing.GroupLayout(car_indicator);
        car_indicator.setLayout(car_indicatorLayout);
        car_indicatorLayout.setHorizontalGroup(
            car_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        car_indicatorLayout.setVerticalGroup(
            car_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        CAR_Panel.add(car_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        car_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        car_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        CAR_Panel.add(car_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        car_label.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        car_label.setForeground(new java.awt.Color(11, 42, 122));
        car_label.setText("Computer Assignment Record");
        CAR_Panel.add(car_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator9Layout = new javax.swing.GroupLayout(dashboard_indicator9);
        dashboard_indicator9.setLayout(dashboard_indicator9Layout);
        dashboard_indicator9Layout.setHorizontalGroup(
            dashboard_indicator9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator9Layout.setVerticalGroup(
            dashboard_indicator9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel9.add(dashboard_indicator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img10.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel9.add(logo_img10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setText("Dashboard");
        dashboard_panel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        CAR_Panel.add(dashboard_panel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(CAR_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, -1, 50));

        HUL_Panel.setBackground(new java.awt.Color(219, 229, 255));
        HUL_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HUL_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HUL_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HUL_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HUL_PanelMouseExited(evt);
            }
        });
        HUL_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hul_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout hul_indicatorLayout = new javax.swing.GroupLayout(hul_indicator);
        hul_indicator.setLayout(hul_indicatorLayout);
        hul_indicatorLayout.setHorizontalGroup(
            hul_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        hul_indicatorLayout.setVerticalGroup(
            hul_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        HUL_Panel.add(hul_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        hul_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        hul_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        HUL_Panel.add(hul_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        hul_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        hul_label.setForeground(new java.awt.Color(52, 58, 70));
        hul_label.setText("History and Usage Logs");
        HUL_Panel.add(hul_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator11Layout = new javax.swing.GroupLayout(dashboard_indicator11);
        dashboard_indicator11.setLayout(dashboard_indicator11Layout);
        dashboard_indicator11Layout.setHorizontalGroup(
            dashboard_indicator11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator11Layout.setVerticalGroup(
            dashboard_indicator11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel11.add(dashboard_indicator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img12.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel11.add(logo_img12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel12.setText("Dashboard");
        dashboard_panel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        HUL_Panel.add(dashboard_panel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(HUL_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, -1, 50));

        MRM_Panel.setBackground(new java.awt.Color(219, 229, 255));
        MRM_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MRM_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MRM_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MRM_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MRM_PanelMouseExited(evt);
            }
        });
        MRM_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mrm_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout mrm_indicatorLayout = new javax.swing.GroupLayout(mrm_indicator);
        mrm_indicator.setLayout(mrm_indicatorLayout);
        mrm_indicatorLayout.setHorizontalGroup(
            mrm_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mrm_indicatorLayout.setVerticalGroup(
            mrm_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        MRM_Panel.add(mrm_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        mrm_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        mrm_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        MRM_Panel.add(mrm_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        mrm_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        mrm_label.setForeground(new java.awt.Color(52, 58, 70));
        mrm_label.setText("Meeting Room Management");
        MRM_Panel.add(mrm_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator13Layout = new javax.swing.GroupLayout(dashboard_indicator13);
        dashboard_indicator13.setLayout(dashboard_indicator13Layout);
        dashboard_indicator13Layout.setHorizontalGroup(
            dashboard_indicator13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator13Layout.setVerticalGroup(
            dashboard_indicator13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel13.add(dashboard_indicator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img14.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel13.add(logo_img14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel14.setText("Dashboard");
        dashboard_panel13.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        MRM_Panel.add(dashboard_panel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Menu_Panel.add(MRM_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, -1, 50));

        Logout_Panel.setBackground(new java.awt.Color(219, 229, 255));
        Logout_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logout_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Logout_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Logout_PanelMouseExited(evt);
            }
        });
        Logout_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logout_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        Logout_Panel.add(logout_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        logout_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        logout_label.setForeground(new java.awt.Color(52, 58, 70));
        logout_label.setText("Logout");
        Logout_Panel.add(logout_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator15Layout = new javax.swing.GroupLayout(dashboard_indicator15);
        dashboard_indicator15.setLayout(dashboard_indicator15Layout);
        dashboard_indicator15Layout.setHorizontalGroup(
            dashboard_indicator15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator15Layout.setVerticalGroup(
            dashboard_indicator15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel15.add(dashboard_indicator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img16.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel15.add(logo_img16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel16.setText("Dashboard");
        dashboard_panel15.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        Logout_Panel.add(dashboard_panel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dashboard_panel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator16Layout = new javax.swing.GroupLayout(dashboard_indicator16);
        dashboard_indicator16.setLayout(dashboard_indicator16Layout);
        dashboard_indicator16Layout.setHorizontalGroup(
            dashboard_indicator16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator16Layout.setVerticalGroup(
            dashboard_indicator16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel16.add(dashboard_indicator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img17.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel16.add(logo_img17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel17.setText("Daily Report");
        dashboard_panel16.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator17Layout = new javax.swing.GroupLayout(dashboard_indicator17);
        dashboard_indicator17.setLayout(dashboard_indicator17Layout);
        dashboard_indicator17Layout.setHorizontalGroup(
            dashboard_indicator17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator17Layout.setVerticalGroup(
            dashboard_indicator17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel17.add(dashboard_indicator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img18.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel17.add(logo_img18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel18.setText("Dashboard");
        dashboard_panel17.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel16.add(dashboard_panel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        Logout_Panel.add(dashboard_panel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        dashboard_panel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator18Layout = new javax.swing.GroupLayout(dashboard_indicator18);
        dashboard_indicator18.setLayout(dashboard_indicator18Layout);
        dashboard_indicator18Layout.setHorizontalGroup(
            dashboard_indicator18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator18Layout.setVerticalGroup(
            dashboard_indicator18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel18.add(dashboard_indicator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img19.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel18.add(logo_img19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel19.setText("Daily Report");
        dashboard_panel18.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator19Layout = new javax.swing.GroupLayout(dashboard_indicator19);
        dashboard_indicator19.setLayout(dashboard_indicator19Layout);
        dashboard_indicator19Layout.setHorizontalGroup(
            dashboard_indicator19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator19Layout.setVerticalGroup(
            dashboard_indicator19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel19.add(dashboard_indicator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img20.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel19.add(logo_img20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel20.setText("Dashboard");
        dashboard_panel19.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel18.add(dashboard_panel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dashboard_panel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator20Layout = new javax.swing.GroupLayout(dashboard_indicator20);
        dashboard_indicator20.setLayout(dashboard_indicator20Layout);
        dashboard_indicator20Layout.setHorizontalGroup(
            dashboard_indicator20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator20Layout.setVerticalGroup(
            dashboard_indicator20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel20.add(dashboard_indicator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img21.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel20.add(logo_img21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel21.setText("Daily Report");
        dashboard_panel20.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator21Layout = new javax.swing.GroupLayout(dashboard_indicator21);
        dashboard_indicator21.setLayout(dashboard_indicator21Layout);
        dashboard_indicator21Layout.setHorizontalGroup(
            dashboard_indicator21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator21Layout.setVerticalGroup(
            dashboard_indicator21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel21.add(dashboard_indicator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img22.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel21.add(logo_img22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel22.setText("Dashboard");
        dashboard_panel21.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel20.add(dashboard_panel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dashboard_panel18.add(dashboard_panel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        Logout_Panel.add(dashboard_panel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        Menu_Panel.add(Logout_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, -1, 50));

        CM_Panel.setBackground(new java.awt.Color(219, 229, 255));
        CM_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CM_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CM_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CM_PanelMouseExited(evt);
            }
        });
        CM_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cm_indicator.setBackground(new java.awt.Color(219, 229, 255));

        javax.swing.GroupLayout cm_indicatorLayout = new javax.swing.GroupLayout(cm_indicator);
        cm_indicator.setLayout(cm_indicatorLayout);
        cm_indicatorLayout.setHorizontalGroup(
            cm_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cm_indicatorLayout.setVerticalGroup(
            cm_indicatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        CM_Panel.add(cm_indicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        cm_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        cm_icon.setPreferredSize(new java.awt.Dimension(35, 35));
        CM_Panel.add(cm_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        cm_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        cm_label.setForeground(new java.awt.Color(52, 58, 70));
        cm_label.setText("Computer Management");
        CM_Panel.add(cm_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator23Layout = new javax.swing.GroupLayout(dashboard_indicator23);
        dashboard_indicator23.setLayout(dashboard_indicator23Layout);
        dashboard_indicator23Layout.setHorizontalGroup(
            dashboard_indicator23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator23Layout.setVerticalGroup(
            dashboard_indicator23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel22.add(dashboard_indicator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img24.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel22.add(logo_img24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel24.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel24.setText("Dashboard");
        dashboard_panel22.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        CM_Panel.add(dashboard_panel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dashboard_panel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator24Layout = new javax.swing.GroupLayout(dashboard_indicator24);
        dashboard_indicator24.setLayout(dashboard_indicator24Layout);
        dashboard_indicator24Layout.setHorizontalGroup(
            dashboard_indicator24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator24Layout.setVerticalGroup(
            dashboard_indicator24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel23.add(dashboard_indicator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img25.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel23.add(logo_img25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel25.setText("Daily Report");
        dashboard_panel23.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator25Layout = new javax.swing.GroupLayout(dashboard_indicator25);
        dashboard_indicator25.setLayout(dashboard_indicator25Layout);
        dashboard_indicator25Layout.setHorizontalGroup(
            dashboard_indicator25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator25Layout.setVerticalGroup(
            dashboard_indicator25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel24.add(dashboard_indicator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img26.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel24.add(logo_img26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel26.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel26.setText("Dashboard");
        dashboard_panel24.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel23.add(dashboard_panel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        CM_Panel.add(dashboard_panel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        dashboard_panel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator26Layout = new javax.swing.GroupLayout(dashboard_indicator26);
        dashboard_indicator26.setLayout(dashboard_indicator26Layout);
        dashboard_indicator26Layout.setHorizontalGroup(
            dashboard_indicator26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator26Layout.setVerticalGroup(
            dashboard_indicator26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel25.add(dashboard_indicator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img27.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel25.add(logo_img27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel27.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel27.setText("Daily Report");
        dashboard_panel25.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator27Layout = new javax.swing.GroupLayout(dashboard_indicator27);
        dashboard_indicator27.setLayout(dashboard_indicator27Layout);
        dashboard_indicator27Layout.setHorizontalGroup(
            dashboard_indicator27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator27Layout.setVerticalGroup(
            dashboard_indicator27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel26.add(dashboard_indicator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img28.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel26.add(logo_img28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel28.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel28.setText("Dashboard");
        dashboard_panel26.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel25.add(dashboard_panel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dashboard_panel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator28Layout = new javax.swing.GroupLayout(dashboard_indicator28);
        dashboard_indicator28.setLayout(dashboard_indicator28Layout);
        dashboard_indicator28Layout.setHorizontalGroup(
            dashboard_indicator28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator28Layout.setVerticalGroup(
            dashboard_indicator28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel27.add(dashboard_indicator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img29.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel27.add(logo_img29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel29.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel29.setText("Daily Report");
        dashboard_panel27.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout dashboard_indicator29Layout = new javax.swing.GroupLayout(dashboard_indicator29);
        dashboard_indicator29.setLayout(dashboard_indicator29Layout);
        dashboard_indicator29Layout.setHorizontalGroup(
            dashboard_indicator29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dashboard_indicator29Layout.setVerticalGroup(
            dashboard_indicator29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        dashboard_panel28.add(dashboard_indicator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 50));

        logo_img30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/QCU LOGO 300X300.png"))); // NOI18N
        logo_img30.setPreferredSize(new java.awt.Dimension(35, 35));
        dashboard_panel28.add(logo_img30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 7, -1, -1));

        jLabel30.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel30.setText("Dashboard");
        dashboard_panel28.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, -1, 280, 50));

        dashboard_panel27.add(dashboard_panel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 360, 50));

        dashboard_panel25.add(dashboard_panel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        CM_Panel.add(dashboard_panel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        Menu_Panel.add(CM_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Menu_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Main_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Menu_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Main_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dashboard_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_panelMouseClicked
        // TODO add your handling code here:
        Dashboard_Main dm = new Dashboard_Main();
        this.setVisible(false);  // Hide the current page
        dm.setVisible(true);
    }//GEN-LAST:event_dashboard_panelMouseClicked

    private void dashboard_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_panelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(dashboard_icon, "src/Image/HOVER DASHBOARD.png");
        animateColorTransition(dashboard_panel, dashboard_panel.getBackground(), new Color(11,42,122), 50);
        dashboard_label.setForeground(new Color(255,255,255));
        animateColorTransition(dashboard_indicator, dashboard_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_dashboard_panelMouseEntered

    private void dashboard_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_panelMouseExited
        // TODO add your handling code here:
        setImageToLabel(dashboard_icon, "src/Image/INACTIVE DASHBOARD.png");
        animateColorTransition(dashboard_panel, dashboard_panel.getBackground(), new Color(219,229,255), 50);
        dashboard_label.setForeground(new Color(52,58,70));
        animateColorTransition(dashboard_indicator, dashboard_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_dashboard_panelMouseExited

    private void dailyRecord_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyRecord_PanelMouseClicked
        // TODO add your handling code here:
        DailyRecord_MeetingRoomReservation dr = new DailyRecord_MeetingRoomReservation();
        this.setVisible(false);  // Hide the current page
        dr.setVisible(true);
    }//GEN-LAST:event_dailyRecord_PanelMouseClicked

    private void dailyRecord_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyRecord_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(dr_icon, "src/Image/HOVER DAILY RECORD.png");
        animateColorTransition(dailyRecord_Panel, dailyRecord_Panel.getBackground(), new Color(11,42,122), 50);
        dr_label.setForeground(new Color(255,255,255));
        animateColorTransition(dr_indicator, dr_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_dailyRecord_PanelMouseEntered

    private void dailyRecord_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dailyRecord_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(dr_icon, "src/Image/INACTIVE DAILY RECORD.png");
        animateColorTransition(dailyRecord_Panel, dailyRecord_Panel.getBackground(), new Color(219,229,255), 50);
        dr_label.setForeground(new Color(52,58,70));
        animateColorTransition(dr_indicator, dr_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_dailyRecord_PanelMouseExited

    private void CheckInOutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckInOutPanelMouseClicked
        // TODO add your handling code here:
        CheckInOutRecords cio = new CheckInOutRecords();
        this.setVisible(false);  // Hide the current page
        cio.setVisible(true);
    }//GEN-LAST:event_CheckInOutPanelMouseClicked

    private void CheckInOutPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckInOutPanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(cio_icon, "src/Image/HOVER CHECK IN_OUT.png");
        animateColorTransition(CheckInOutPanel, CheckInOutPanel.getBackground(), new Color(11,42,122), 50);
        cio_label.setForeground(new Color(255,255,255));
        animateColorTransition(cio_indicator, cio_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_CheckInOutPanelMouseEntered

    private void CheckInOutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckInOutPanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(cio_icon, "src/Image/INACTIVE CHECK IN_OUT.png");
        animateColorTransition(CheckInOutPanel, CheckInOutPanel.getBackground(), new Color(219,229,255), 50);
        cio_label.setForeground(new Color(52,58,70));
        animateColorTransition(cio_indicator, cio_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_CheckInOutPanelMouseExited

    private void ReservationRecord_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationRecord_PanelMouseClicked
        // TODO add your handling code here:
        ReservationRecords rr = new ReservationRecords();
        this.setVisible(false);  // Hide the current page
        rr.setVisible(true);
    }//GEN-LAST:event_ReservationRecord_PanelMouseClicked

    private void ReservationRecord_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationRecord_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(rr_icon, "src/Image/HOVER RESERVATION.png");
        animateColorTransition(ReservationRecord_Panel, ReservationRecord_Panel.getBackground(), new Color(11,42,122), 50);
        rr_label.setForeground(new Color(255,255,255));
        animateColorTransition(rr_indicator, rr_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_ReservationRecord_PanelMouseEntered

    private void ReservationRecord_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationRecord_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(rr_icon, "src/Image/INACTIVE RESERVATION.png");
        animateColorTransition(ReservationRecord_Panel, ReservationRecord_Panel.getBackground(), new Color(219,229,255), 50);
        rr_label.setForeground(new Color(52,58,70));
        animateColorTransition(rr_indicator, rr_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_ReservationRecord_PanelMouseExited

    private void CAR_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CAR_PanelMouseClicked
        // TODO add your handling code here:
        ComputerAssignmentRecords car = new ComputerAssignmentRecords();
        this.setVisible(false);  // Hide the current page
        car.setVisible(true);
    }//GEN-LAST:event_CAR_PanelMouseClicked

    private void CAR_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CAR_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(car_icon, "src/Image/HOVER COMPUTER ASSIGNMENT.png");
        animateColorTransition(CAR_Panel, CAR_Panel.getBackground(), new Color(11,42,122), 50);
        car_label.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_CAR_PanelMouseEntered

    private void CAR_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CAR_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(car_icon, "src/Image/ACTIVE COMPUTER ASSIGNMENT.png");
        animateColorTransition(CAR_Panel, CAR_Panel.getBackground(), new Color(219,229,255), 50);
        car_label.setForeground(new Color(11,42,122));
    }//GEN-LAST:event_CAR_PanelMouseExited

    private void HUL_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HUL_PanelMouseClicked
        // TODO add your handling code here:
        HistoryAndUsageLogs_MR hul = new HistoryAndUsageLogs_MR();
        this.setVisible(false);  // Hide the current page
        hul.setVisible(true);
    }//GEN-LAST:event_HUL_PanelMouseClicked

    private void HUL_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HUL_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(hul_icon, "src/Image/HOVER HISTORY.png");
        animateColorTransition(HUL_Panel, HUL_Panel.getBackground(), new Color(11,42,122), 50);
        hul_label.setForeground(new Color(255,255,255));
        animateColorTransition(hul_indicator, hul_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_HUL_PanelMouseEntered

    private void HUL_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HUL_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(hul_icon, "src/Image/INACTIVE HISTORY.png");
        animateColorTransition(HUL_Panel, HUL_Panel.getBackground(), new Color(219,229,255), 50);
        hul_label.setForeground(new Color(52,58,70));
        animateColorTransition(hul_indicator, hul_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_HUL_PanelMouseExited

    private void MRM_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MRM_PanelMouseClicked
        // TODO add your handling code here:
        MeetingRoomManagement mrm = new MeetingRoomManagement();
        this.setVisible(false);  // Hide the current page
        mrm.setVisible(true);
    }//GEN-LAST:event_MRM_PanelMouseClicked

    private void MRM_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MRM_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(mrm_icon, "src/Image/HOVER MEETING ROOM.png");
        animateColorTransition(MRM_Panel, MRM_Panel.getBackground(), new Color(11,42,122), 50);
        mrm_label.setForeground(new Color(255,255,255));
        animateColorTransition(mrm_indicator, mrm_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_MRM_PanelMouseEntered

    private void MRM_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MRM_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(mrm_icon, "src/Image/INACTIVE MEETING ROOM.png");
        animateColorTransition(MRM_Panel, MRM_Panel.getBackground(), new Color(219,229,255), 50);
        mrm_label.setForeground(new Color(52,58,70));
        animateColorTransition(mrm_indicator, mrm_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_MRM_PanelMouseExited

    private void Logout_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logout_PanelMouseClicked
        // TODO add your handling code here:
        LogIn login = new LogIn();
        this.setVisible(false);  // Hide the current page
        login.setVisible(true);
    }//GEN-LAST:event_Logout_PanelMouseClicked

    private void Logout_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logout_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(logout_icon, "src/Image/HOVER LOGOUT.png");
        animateColorTransition(Logout_Panel, Logout_Panel.getBackground(), new Color(11,42,122), 50);
        logout_label.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_Logout_PanelMouseEntered

    private void Logout_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logout_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(logout_icon, "src/Image/INACTIVE LOGOUT.png");
        animateColorTransition(Logout_Panel, Logout_Panel.getBackground(), new Color(219,229,255), 50);
        logout_label.setForeground(new Color(52,58,70));
    }//GEN-LAST:event_Logout_PanelMouseExited

    private void CM_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM_PanelMouseClicked
        // TODO add your handling code here:
        ComputerManagement cm = new ComputerManagement();
        this.setVisible(false);  // Hide the current page
        cm.setVisible(true);
    }//GEN-LAST:event_CM_PanelMouseClicked

    private void CM_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM_PanelMouseEntered
        // TODO add your handling code here:
        setImageToLabel(cm_icon, "src/Image/HOVER COMPUTER MANAGEMENT.png");
        animateColorTransition(CM_Panel, CM_Panel.getBackground(), new Color(11,42,122), 50);
        cm_label.setForeground(new Color(255,255,255));
        animateColorTransition(cm_indicator, dr_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_CM_PanelMouseEntered

    private void CM_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(cm_icon, "src/Image/INACTIVE COMPUTER MANAGEMENT.png");
        animateColorTransition(CM_Panel, CM_Panel.getBackground(), new Color(219,229,255), 50);
        cm_label.setForeground(new Color(52,58,70));
        animateColorTransition(cm_indicator, cm_indicator.getBackground(), new Color(219,229,255), 50);
    }//GEN-LAST:event_CM_PanelMouseExited

    private void CAR_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAR_searchKeyReleased
        applyReservationFilter();
    }//GEN-LAST:event_CAR_searchKeyReleased

    private void datePickPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickPropertyChange
        if ("date".equals(evt.getPropertyName())) {
            applyReservationFilter(); // Reuse the same method
        }
    }//GEN-LAST:event_datePickPropertyChange

    private void CAR_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CAR_TableMouseClicked
        int selectedRow = CAR_Table.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) CAR_Table.getValueAt(selectedRow, 0); // ID is column 0
            showReservationDetails(reservationId);
        }
    }//GEN-LAST:event_CAR_TableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        datePick.setDate(null);
        
        CAR_search.setText("");
        
        loadReservationTable();
        
        CAR_std_name.setText("");
        CAR_std_no.setText("");
        CAR_meeting_room.setText("");
        CAR_date.setText("");
        CAR_full_name.setText("");
        CAR_eml_add.setText("");
        CAR_prgrm.setText("");
        CAR_start_time.setText("");
        CAR_end_time.setText("");
        CAR_end_time1.setText("");
        CAR_remarks.setText("");
        CAR_purpose.setText("");
        CAR_processed_by.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComputerAssignmentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComputerAssignmentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComputerAssignmentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComputerAssignmentRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComputerAssignmentRecords().setVisible(true);
            }
        });
    }
    
    public void loadReservationTable() {
        Connection conn = dbConnection.dbCon.getConnection();
        DefaultTableModel model = (DefaultTableModel) CAR_Table.getModel();
        model.setRowCount(0); // clear table

        String query = """
            SELECT 
                desktopReservationID,
                CONCAT(s.LastName, ', ', s.FirstName, ' ', s.MiddleName, ' ', ISNULL(s.Suffix, '')) AS StudentName,
                dr.assignedPC,
                dr.TransactionDate
            FROM 
                DESKTOP_RESERVATION dr
            JOIN 
                STUDENT s ON dr.StudentID = s.studentID
        """;

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("StudentName");
                String pc = rs.getString("assignedPC");
                Date date = rs.getDate("TransactionDate");

                int id = rs.getInt("desktopReservationID");
                model.addRow(new Object[]{id, name, pc, date}); // 4 columns now

                // Hide the ID column (assume it's column 0)
                CAR_Table.getColumnModel().getColumn(0).setMinWidth(0);
                CAR_Table.getColumnModel().getColumn(0).setMaxWidth(0);
                CAR_Table.getColumnModel().getColumn(0).setWidth(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void filterReservationData(java.util.Date selectedDate, String searchTerm) {
        Connection conn = dbConnection.dbCon.getConnection();
        DefaultTableModel model = (DefaultTableModel) CAR_Table.getModel();
        model.setRowCount(0); // clear table

        StringBuilder query = new StringBuilder("""
            SELECT 
                desktopReservationID,
                CONCAT(s.LastName, ', ', s.FirstName, ' ', s.MiddleName, ' ', ISNULL(s.Suffix, '')) AS StudentName,
                dr.assignedPC,
                dr.TransactionDate
            FROM 
                DESKTOP_RESERVATION dr
            JOIN 
                STUDENT s ON dr.StudentID = s.studentID
            WHERE 1=1
        """);

        List<Object> parameters = new ArrayList<>();

        if (selectedDate != null) {
            query.append(" AND dr.TransactionDate = ?");
            parameters.add(new java.sql.Date(selectedDate.getTime()));
        }

        if (searchTerm != null && !searchTerm.isEmpty()) {
            query.append("""
                AND (
                    s.FirstName LIKE ?
                    OR s.LastName LIKE ?
                    OR s.MiddleName LIKE ?
                    OR dr.assignedPC LIKE ?
                )
            """);
            parameters.add("%" + searchTerm + "%"); // first name
            parameters.add("%" + searchTerm + "%"); // last name
            parameters.add("%" + searchTerm + "%"); // middle name
            parameters.add("%" + searchTerm + "%"); // assigned pc
        }

        try (PreparedStatement ps = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("StudentName");
                String pc = rs.getString("assignedPC");
                Date date = rs.getDate("TransactionDate");

                int id = rs.getInt("desktopReservationID");
                model.addRow(new Object[]{id, name, pc, date}); // 4 columns now

                // Hide the ID column (assume it's column 0)
                CAR_Table.getColumnModel().getColumn(0).setMinWidth(0);
                CAR_Table.getColumnModel().getColumn(0).setMaxWidth(0);
                CAR_Table.getColumnModel().getColumn(0).setWidth(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void applyReservationFilter() {
        java.util.Date selectedDate = datePick.getDate();
        String keyword = CAR_search.getText().trim();
        filterReservationData(selectedDate, keyword);
    }
    
    private void showReservationDetails(int reservationId) {
        try {
            Connection conn = dbConnection.dbCon.getConnection();
            String query = """
                SELECT 
                    CONCAT(s.LastName, ', ', s.FirstName, ' ', s.MiddleName, ' ', ISNULL(s.Suffix, '')) AS StudentName,
                    s.StudentNumber,
                    s.ContactNumber,
                    s.Email,
                    s.CourseProgram,
                    dr.assignedPC,
                    dr.TransactionDate,
                    dr.StartTime,
                    dr.EndTime,
                    dr.TimeRange,
                    dr.Remarks,
                    dr.Purpose,
                    CONCAT(u.LastName, ', ', u.FirstName, ' ', u.MiddleName, ' ', ISNULL(u.Suffix, '')) AS ProcessedBy
                FROM 
                    DESKTOP_RESERVATION dr
                JOIN 
                    STUDENT s ON dr.StudentID = s.studentID
                LEFT JOIN 
                    USERS u ON dr.ProcessedBy = u.userID
                WHERE 
                    dr.desktopReservationID = ?
            """;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reservationId); // ✅ Correct param index and value

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CAR_std_name.setText(rs.getString("StudentName"));
                CAR_std_no.setText(rs.getString("StudentNumber"));
                CAR_meeting_room.setText(rs.getString("assignedPC"));
                CAR_date.setText(rs.getDate("TransactionDate").toString());
                CAR_full_name.setText(rs.getString("ContactNumber"));
                CAR_eml_add.setText(rs.getString("Email"));
                CAR_prgrm.setText(rs.getString("CourseProgram"));
                CAR_start_time.setText(rs.getString("StartTime"));
                CAR_end_time.setText(rs.getString("EndTime"));
                CAR_end_time1.setText(rs.getString("TimeRange"));
                CAR_remarks.setText(rs.getString("Remarks"));
                CAR_purpose.setText(rs.getString("Purpose"));
                CAR_processed_by.setText(rs.getString("ProcessedBy"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CAR_Panel;
    private javax.swing.JTable CAR_Table;
    private javax.swing.JLabel CAR_date;
    private javax.swing.JLabel CAR_eml_add;
    private javax.swing.JLabel CAR_end_time;
    private javax.swing.JLabel CAR_end_time1;
    private javax.swing.JLabel CAR_full_name;
    private javax.swing.JLabel CAR_meeting_room;
    private javax.swing.JLabel CAR_prgrm;
    private javax.swing.JLabel CAR_processed_by;
    private javax.swing.JLabel CAR_purpose;
    private javax.swing.JLabel CAR_remarks;
    private javax.swing.JTextField CAR_search;
    private javax.swing.JLabel CAR_start_time;
    private javax.swing.JLabel CAR_std_name;
    private javax.swing.JLabel CAR_std_no;
    private javax.swing.JPanel CM_Panel;
    private javax.swing.JPanel CheckInOutPanel;
    private javax.swing.JPanel HUL_Panel;
    private javax.swing.JLabel HeaderName;
    private javax.swing.JPanel Logout_Panel;
    private javax.swing.JPanel MRM_Panel;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JPanel Menu_Panel;
    private javax.swing.JLabel QCU_logo;
    private javax.swing.JPanel ReservationRecord_Panel;
    private javax.swing.JScrollPane ScrollBar;
    private javax.swing.JLabel car_icon;
    private javax.swing.JPanel car_indicator;
    private javax.swing.JLabel car_label;
    private javax.swing.JLabel cio_icon;
    private javax.swing.JPanel cio_indicator;
    private javax.swing.JLabel cio_label;
    private javax.swing.JLabel cm_icon;
    private javax.swing.JPanel cm_indicator;
    private javax.swing.JLabel cm_label;
    private javax.swing.JPanel dailyRecord_Panel;
    private javax.swing.JLabel dashboard_icon;
    private javax.swing.JPanel dashboard_indicator;
    private javax.swing.JPanel dashboard_indicator1;
    private javax.swing.JPanel dashboard_indicator11;
    private javax.swing.JPanel dashboard_indicator13;
    private javax.swing.JPanel dashboard_indicator15;
    private javax.swing.JPanel dashboard_indicator16;
    private javax.swing.JPanel dashboard_indicator17;
    private javax.swing.JPanel dashboard_indicator18;
    private javax.swing.JPanel dashboard_indicator19;
    private javax.swing.JPanel dashboard_indicator20;
    private javax.swing.JPanel dashboard_indicator21;
    private javax.swing.JPanel dashboard_indicator23;
    private javax.swing.JPanel dashboard_indicator24;
    private javax.swing.JPanel dashboard_indicator25;
    private javax.swing.JPanel dashboard_indicator26;
    private javax.swing.JPanel dashboard_indicator27;
    private javax.swing.JPanel dashboard_indicator28;
    private javax.swing.JPanel dashboard_indicator29;
    private javax.swing.JPanel dashboard_indicator3;
    private javax.swing.JPanel dashboard_indicator5;
    private javax.swing.JPanel dashboard_indicator7;
    private javax.swing.JPanel dashboard_indicator9;
    private javax.swing.JLabel dashboard_label;
    private javax.swing.JPanel dashboard_panel;
    private javax.swing.JPanel dashboard_panel1;
    private javax.swing.JPanel dashboard_panel11;
    private javax.swing.JPanel dashboard_panel13;
    private javax.swing.JPanel dashboard_panel15;
    private javax.swing.JPanel dashboard_panel16;
    private javax.swing.JPanel dashboard_panel17;
    private javax.swing.JPanel dashboard_panel18;
    private javax.swing.JPanel dashboard_panel19;
    private javax.swing.JPanel dashboard_panel20;
    private javax.swing.JPanel dashboard_panel21;
    private javax.swing.JPanel dashboard_panel22;
    private javax.swing.JPanel dashboard_panel23;
    private javax.swing.JPanel dashboard_panel24;
    private javax.swing.JPanel dashboard_panel25;
    private javax.swing.JPanel dashboard_panel26;
    private javax.swing.JPanel dashboard_panel27;
    private javax.swing.JPanel dashboard_panel28;
    private javax.swing.JPanel dashboard_panel3;
    private javax.swing.JPanel dashboard_panel5;
    private javax.swing.JPanel dashboard_panel7;
    private javax.swing.JPanel dashboard_panel9;
    private com.toedter.calendar.JDateChooser datePick;
    private javax.swing.JLabel db_mrco_date;
    private javax.swing.JLabel db_mrco_mtng_rm;
    private javax.swing.JLabel db_mrco_name;
    private javax.swing.JLabel db_mrco_std_no;
    private javax.swing.JLabel dr_icon;
    private javax.swing.JPanel dr_indicator;
    private javax.swing.JLabel dr_label;
    private javax.swing.JLabel headerr_icon;
    private javax.swing.JLabel hul_icon;
    private javax.swing.JPanel hul_indicator;
    private javax.swing.JLabel hul_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel logo_img10;
    private javax.swing.JLabel logo_img12;
    private javax.swing.JLabel logo_img14;
    private javax.swing.JLabel logo_img16;
    private javax.swing.JLabel logo_img17;
    private javax.swing.JLabel logo_img18;
    private javax.swing.JLabel logo_img19;
    private javax.swing.JLabel logo_img20;
    private javax.swing.JLabel logo_img21;
    private javax.swing.JLabel logo_img22;
    private javax.swing.JLabel logo_img24;
    private javax.swing.JLabel logo_img25;
    private javax.swing.JLabel logo_img26;
    private javax.swing.JLabel logo_img27;
    private javax.swing.JLabel logo_img28;
    private javax.swing.JLabel logo_img29;
    private javax.swing.JLabel logo_img3;
    private javax.swing.JLabel logo_img30;
    private javax.swing.JLabel logo_img5;
    private javax.swing.JLabel logo_img7;
    private javax.swing.JLabel logo_img8;
    private javax.swing.JLabel logout_icon;
    private javax.swing.JLabel logout_label;
    private javax.swing.JLabel mrm_icon;
    private javax.swing.JPanel mrm_indicator;
    private javax.swing.JLabel mrm_label;
    private javax.swing.JLabel rr_icon;
    private javax.swing.JPanel rr_indicator;
    private javax.swing.JLabel rr_label;
    private javax.swing.JLabel user_image;
    // End of variables declaration//GEN-END:variables
}