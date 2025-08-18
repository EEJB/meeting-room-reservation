/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package qcureservations_ver2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author narag
 */
public class Dashboard_MeetingRoomCheckIn extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard_MeetingRoomReservation
     */
    public Dashboard_MeetingRoomCheckIn() {
        initComponents();
        
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
        setImageToLabel(car_icon, "src/Image/INACTIVE COMPUTER ASSIGNMENT.png");
        setImageToLabel(cm_icon, "src/Image/INACTIVE COMPUTER MANAGEMENT.png");
        setImageToLabel(hul_icon, "src/Image/INACTIVE HISTORY.png");
        setImageToLabel(logout_icon, "src/Image/INACTIVE LOGOUT.png");
        
        //HEADER IMAGE
        setImageToLabel(user_image, "src/Image/USER PROFILE.png");
        setImageToLabel(headerr_icon, "src/Image/ACTIVE DASHBOARD.png");
        
        // START NEW CODE HERE
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

public class RoundedButton extends JButton {
    private int cornerRadius;
    private Color borderColor = null; // no border by default

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    public RoundedButton(String text) {
        this(text, 20);
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getModel().isArmed() ? getBackground().darker() : getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (borderColor != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            g2.dispose();
        }
        // else: no border is drawn
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        return shape.contains(x, y);
    }
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        Main_Panel = new javax.swing.JPanel();
        user_image = new javax.swing.JLabel();
        location = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        headerr_icon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        db_mrc_in_go_back = new javax.swing.JLabel();
        jPanel1 = new RoundedPanel(30);
        jPanel2 = new RoundedPanel(30);
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        db_mrc_in_chck_in_std_name = new javax.swing.JLabel();
        db_mrc_in_chck_in_std_no = new javax.swing.JLabel();
        db_mrc_in_chck_in_meeting_room = new javax.swing.JLabel();
        db_mrc_in_chck_out_date = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        db_mrc_in_chck_out_date1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        db_mrc_in_full_name = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        db_mrc_in_start_time = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        db_mrc_in_dscrptn = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        db_mrc_in_end_time = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        db_mrc_in_email_add = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        db_mrc_in_program = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        db_mrc_in_chckbx1 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx2 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx3 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx4 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx5 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx6 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx7 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx8 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx9 = new javax.swing.JCheckBox();
        db_mrc_in_chckbx10 = new javax.swing.JCheckBox();
        db_mrc_in_bttn_chck_in = new RoundedButton("",30);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QCU RESERVATIONS | Dashboard - Meeting Room Check-in");

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

        dashboard_indicator.setBackground(new java.awt.Color(11, 42, 122));

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

        dashboard_label.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        dashboard_label.setForeground(new java.awt.Color(11, 42, 122));
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

        car_indicator.setBackground(new java.awt.Color(219, 229, 255));

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

        car_label.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        car_label.setForeground(new java.awt.Color(52, 58, 70));
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

        Main_Panel.setBackground(Color.WHITE);
        Main_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Main_Panel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Main_Panel.setPreferredSize(new java.awt.Dimension(1542, 756));
        Main_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ACTIVE DASHBOARD.png"))); // NOI18N
        user_image.setPreferredSize(new java.awt.Dimension(30, 30));
        Main_Panel.add(user_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, -1, -1));

        location.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        location.setForeground(new java.awt.Color(52, 58, 70));
        location.setText("Dashboard / Meeting Room Check-in ");
        Main_Panel.add(location, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 340, 20));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(11, 42, 122));
        jLabel3.setText("Dashboard");
        Main_Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        headerr_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ACTIVE DASHBOARD.png"))); // NOI18N
        headerr_icon.setPreferredSize(new java.awt.Dimension(50, 50));
        Main_Panel.add(headerr_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 50));

        jLabel5.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 58, 70));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Hi, [Name]!  ");
        Main_Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 340, -1));

        db_mrc_in_go_back.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        db_mrc_in_go_back.setForeground(new java.awt.Color(11, 42, 122));
        db_mrc_in_go_back.setText("< Go Back");
        db_mrc_in_go_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Main_Panel.add(db_mrc_in_go_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jPanel1.setBackground(new java.awt.Color(248, 247, 253));

        jPanel2.setBackground(new java.awt.Color(217, 217, 217));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setText("Name :");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setText("Student No.");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setText("Meeting Room");

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel13.setText("Date:");

        db_mrc_in_chck_in_std_name.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chck_in_std_name.setText("Ralph Christian Mabato");

        db_mrc_in_chck_in_std_no.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chck_in_std_no.setText("22-1122");

        db_mrc_in_chck_in_meeting_room.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chck_in_meeting_room.setText("Room Number");

        db_mrc_in_chck_out_date.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chck_out_date.setText("11 - 21 - 2025");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel15.setText("Reservation Code");

        db_mrc_in_chck_out_date1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chck_out_date1.setText("0000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(db_mrc_in_chck_in_std_name, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(db_mrc_in_chck_in_std_no, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(db_mrc_in_chck_in_meeting_room, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(db_mrc_in_chck_out_date, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(db_mrc_in_chck_out_date1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(148, 148, 148))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(db_mrc_in_chck_out_date1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(db_mrc_in_chck_in_std_name)
                            .addComponent(db_mrc_in_chck_in_std_no)
                            .addComponent(db_mrc_in_chck_in_meeting_room)
                            .addComponent(db_mrc_in_chck_out_date))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel23.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel23.setText("General Information");

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel31.setText("Full Name:");

        db_mrc_in_full_name.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        db_mrc_in_full_name.setText("Ralph Christian Mabato");

        jLabel33.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel33.setText("Start Time:");

        db_mrc_in_start_time.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        db_mrc_in_start_time.setText("1:30 PM");

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel35.setText("End Time:");

        db_mrc_in_dscrptn.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        db_mrc_in_dscrptn.setText("Short Description");

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel37.setText("Purpose:");

        db_mrc_in_end_time.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        db_mrc_in_end_time.setText("2:30 PM");

        jLabel39.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel39.setText("Email Address : ");

        db_mrc_in_email_add.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        db_mrc_in_email_add.setText("ralphmabato@gmail.com");

        jLabel41.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel41.setText("Program:");

        db_mrc_in_program.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        db_mrc_in_program.setText("BSIT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(db_mrc_in_full_name)
                            .addComponent(jLabel33)
                            .addComponent(db_mrc_in_start_time))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(db_mrc_in_email_add)
                            .addComponent(jLabel35)
                            .addComponent(db_mrc_in_end_time))
                        .addGap(276, 276, 276)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(db_mrc_in_program)
                            .addComponent(jLabel41)
                            .addComponent(db_mrc_in_dscrptn)
                            .addComponent(jLabel37))
                        .addGap(53, 53, 53)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel39)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(db_mrc_in_full_name)
                    .addComponent(db_mrc_in_email_add)
                    .addComponent(db_mrc_in_program))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel35)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(db_mrc_in_start_time)
                    .addComponent(db_mrc_in_dscrptn)
                    .addComponent(db_mrc_in_end_time))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Main_Panel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 1060, 230));

        jLabel44.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel44.setText("Meeting Room Companion");
        Main_Panel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        db_mrc_in_chckbx1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx1.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, -1));

        db_mrc_in_chckbx2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx2.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, -1, -1));

        db_mrc_in_chckbx3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx3.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, -1, -1));

        db_mrc_in_chckbx4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx4.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, -1, -1));

        db_mrc_in_chckbx5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx5.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, -1, -1));

        db_mrc_in_chckbx6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx6.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, -1, -1));

        db_mrc_in_chckbx7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx7.setText("Name 1");
        db_mrc_in_chckbx7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                db_mrc_in_chckbx7ActionPerformed(evt);
            }
        });
        Main_Panel.add(db_mrc_in_chckbx7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, -1, -1));

        db_mrc_in_chckbx8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx8.setText("Name 1");
        db_mrc_in_chckbx8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                db_mrc_in_chckbx8ActionPerformed(evt);
            }
        });
        Main_Panel.add(db_mrc_in_chckbx8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, -1, -1));

        db_mrc_in_chckbx9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx9.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 440, -1, -1));

        db_mrc_in_chckbx10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        db_mrc_in_chckbx10.setText("Name 1");
        Main_Panel.add(db_mrc_in_chckbx10, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 490, -1, -1));

        db_mrc_in_bttn_chck_in.setBackground(new java.awt.Color(11, 42, 122));
        db_mrc_in_bttn_chck_in.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        db_mrc_in_bttn_chck_in.setForeground(new java.awt.Color(255, 255, 255));
        db_mrc_in_bttn_chck_in.setText("Check In");
        db_mrc_in_bttn_chck_in.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Main_Panel.add(db_mrc_in_bttn_chck_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 560, 150, 50));

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
            .addComponent(Main_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    }//GEN-LAST:event_dashboard_panelMouseEntered

    private void dashboard_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_panelMouseExited
        // TODO add your handling code here:
        setImageToLabel(dashboard_icon, "src/Image/INACTIVE DASHBOARD.png");
        animateColorTransition(dashboard_panel, dashboard_panel.getBackground(), new Color(219,229,255), 50);
        dashboard_label.setForeground(new Color(11,42,122));
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
        animateColorTransition(car_indicator, car_indicator.getBackground(), new Color(11,42,122), 50);
    }//GEN-LAST:event_CAR_PanelMouseEntered

    private void CAR_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CAR_PanelMouseExited
        // TODO add your handling code here:
        setImageToLabel(car_icon, "src/Image/INACTIVE COMPUTER ASSIGNMENT.png");
        animateColorTransition(CAR_Panel, CAR_Panel.getBackground(), new Color(219,229,255), 50);
        car_label.setForeground(new Color(52,58,70));
        animateColorTransition(car_indicator, car_indicator.getBackground(), new Color(219,229,255), 50);
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

    private void db_mrc_in_chckbx7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_db_mrc_in_chckbx7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_db_mrc_in_chckbx7ActionPerformed

    private void db_mrc_in_chckbx8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_db_mrc_in_chckbx8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_db_mrc_in_chckbx8ActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard_MeetingRoomCheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_MeetingRoomCheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_MeetingRoomCheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_MeetingRoomCheckIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_MeetingRoomCheckIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CAR_Panel;
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
    private javax.swing.JButton db_mrc_in_bttn_chck_in;
    private javax.swing.JLabel db_mrc_in_chck_in_meeting_room;
    private javax.swing.JLabel db_mrc_in_chck_in_std_name;
    private javax.swing.JLabel db_mrc_in_chck_in_std_no;
    private javax.swing.JLabel db_mrc_in_chck_out_date;
    private javax.swing.JLabel db_mrc_in_chck_out_date1;
    private javax.swing.JCheckBox db_mrc_in_chckbx1;
    private javax.swing.JCheckBox db_mrc_in_chckbx10;
    private javax.swing.JCheckBox db_mrc_in_chckbx2;
    private javax.swing.JCheckBox db_mrc_in_chckbx3;
    private javax.swing.JCheckBox db_mrc_in_chckbx4;
    private javax.swing.JCheckBox db_mrc_in_chckbx5;
    private javax.swing.JCheckBox db_mrc_in_chckbx6;
    private javax.swing.JCheckBox db_mrc_in_chckbx7;
    private javax.swing.JCheckBox db_mrc_in_chckbx8;
    private javax.swing.JCheckBox db_mrc_in_chckbx9;
    private javax.swing.JLabel db_mrc_in_dscrptn;
    private javax.swing.JLabel db_mrc_in_email_add;
    private javax.swing.JLabel db_mrc_in_end_time;
    private javax.swing.JLabel db_mrc_in_full_name;
    private javax.swing.JLabel db_mrc_in_go_back;
    private javax.swing.JLabel db_mrc_in_program;
    private javax.swing.JLabel db_mrc_in_start_time;
    private javax.swing.JLabel dr_icon;
    private javax.swing.JPanel dr_indicator;
    private javax.swing.JLabel dr_label;
    private javax.swing.JLabel headerr_icon;
    private javax.swing.JLabel hul_icon;
    private javax.swing.JPanel hul_indicator;
    private javax.swing.JLabel hul_label;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel location;
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
