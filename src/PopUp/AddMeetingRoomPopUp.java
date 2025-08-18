/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package PopUp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import qcureservations_ver2.Dashboard_ComputerAssignmentCheckOut;
import qcureservations_ver2.Dashboard_ComputerAssignmentCheckin;

/**
 *
 * @author narag
 */
public class AddMeetingRoomPopUp extends javax.swing.JPanel {

    /**
     * Creates new form Message1
     */
    
    private AddRoomListener addRoomListener;
    
    public AddMeetingRoomPopUp() {
        initComponents();
        // Wait until the component has a size before setting the image
        
        status.setLightWeightPopupEnabled(false);
        
       
    }


    //DO NOT CHANGE THIS - use this for resizing image to match the size of the label
    private void setImageToLabel(JLabel label, String imagePath)
    {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(resizedImg));
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        cancel = new RoundedButton("",15);
        jLabel4 = new javax.swing.JLabel();
        add = new RoundedButton("",15);
        RoomNumber = new RoundedTextField(20)
        ;
        Capacity = new RoundedTextField(20)
        ;
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        errorLabelRN = new javax.swing.JLabel();
        errorLabelCP = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(436, 460));
        setMinimumSize(new java.awt.Dimension(436, 460));
        setPreferredSize(new java.awt.Dimension(436, 460));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel5.setText("Add meeting room");

        cancel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cancel.setForeground(new java.awt.Color(11, 42, 122));
        cancel.setText("Cancel");
        cancel.setBorder(new LineBorder(new java.awt.Color(255,255,255), 2)); // Black border with thickness 2
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Room Number");

        add.setBackground(new java.awt.Color(11, 42, 122));
        add.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add");
        add.setBorder(null);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        RoomNumber.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        RoomNumber.setToolTipText("");
        RoomNumber.setBorder(null
        );
        RoomNumber.setEnabled(true);
        RoomNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomNumberActionPerformed(evt);
            }
        });

        Capacity.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Capacity.setToolTipText("");
        Capacity.setBorder(null
        );
        Capacity.setEnabled(true);
        Capacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapacityActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Capacity");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Status");

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Unavailable" }));
        status.setName(""); // NOI18N

        errorLabelRN.setForeground(new java.awt.Color(204, 0, 0));

        errorLabelCP.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(errorLabelRN, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(errorLabelCP, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Capacity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabelRN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(errorLabelCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        if (addRoomListener != null) {
            addRoomListener.onCancel();
        }
    }//GEN-LAST:event_cancelActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (addRoomListener != null) {
            String roomNumber = RoomNumber.getText().trim();
            String statusValue = status.getSelectedItem().toString(); // Use text field now
            int capacity = 0;

            // Reset error message first
            errorLabelRN.setText("");
            errorLabelCP.setText("");

            // Check if room number is empty
            if (roomNumber.isEmpty()) {
                errorLabelRN.setText("Please fill in the Room Number.");
                return;
            }

            // Check if capacity is a valid number
            try {
                capacity = Integer.parseInt(Capacity.getText().trim());
                if (capacity <= 0) {
                    errorLabelCP.setText("Capacity must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                errorLabelCP.setText("Please enter a valid number for Capacity.");
                return;
            }

            // If no error, proceed
            addRoomListener.onAdd(roomNumber, statusValue, capacity);
        }
    }//GEN-LAST:event_addActionPerformed

    private void RoomNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomNumberActionPerformed

    private void CapacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapacityActionPerformed
    
    public interface AddRoomListener {
        void onAdd(String roomNumber, String status, int capacity);
        void onCancel();
    }
    
    public void setAddRoomListener(AddRoomListener listener) {
        this.addRoomListener = listener;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Capacity;
    private javax.swing.JTextField RoomNumber;
    private javax.swing.JButton add;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel errorLabelCP;
    private javax.swing.JLabel errorLabelRN;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> status;
    // End of variables declaration//GEN-END:variables
}
