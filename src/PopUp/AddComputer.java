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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class AddComputer extends javax.swing.JPanel {

    /**
     * Creates new form Message1
     */
    
    private AddComputerListener addComputerListener;
    
    public AddComputer() {
        initComponents();
        // Wait until the component has a size before setting the image
        
        condition.setLightWeightPopupEnabled(false);
        status1.setLightWeightPopupEnabled(false);
        
       
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
        PcNumber = new RoundedTextField(20)
        ;
        jLabel7 = new javax.swing.JLabel();
        condition = new javax.swing.JComboBox<>();
        status1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();

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
        jLabel4.setText("PC Number");

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

        PcNumber.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        PcNumber.setToolTipText("");
        PcNumber.setBorder(null
        );
        PcNumber.setEnabled(true);
        PcNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PcNumberActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Condition");

        condition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good Condition", "Bad Condition" }));

        status1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available" }));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Status");

        errorLabel.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(status1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PcNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PcNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addGap(4, 4, 4)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(condition, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        if (addComputerListener != null) {
            addComputerListener.onCancel();
        }
    }//GEN-LAST:event_cancelActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (addComputerListener != null) {
            String pcNumber = PcNumber.getText().trim();
            String availabilityValue = status1.getSelectedItem().toString(); // availability JComboBox
            String conditionValue = condition.getSelectedItem().toString();  // condition JComboBox

            errorLabel.setText("");
            
            if (pcNumber.isEmpty()) {
                errorLabel.setText("Please fill in the PC Number.");
                return;
            }

            // Fire the onAdd back to CM_bttn_add_deviceActionPerformed
            addComputerListener.onAdd(pcNumber, availabilityValue, conditionValue);
        }
    }//GEN-LAST:event_addActionPerformed

    private void PcNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PcNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PcNumberActionPerformed

    public interface AddComputerListener {
        void onAdd(String pcNumber, String availability, String condition);
        void onCancel();
    }

    public void setAddComputerListener(AddComputerListener listener) {
        this.addComputerListener = listener;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PcNumber;
    private javax.swing.JButton add;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox<String> condition;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox<String> status1;
    // End of variables declaration//GEN-END:variables
}
