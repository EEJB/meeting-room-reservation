package PopUp;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GlassPaneOverlay extends JPanel {
    private final JPanel messagePanel;

    public GlassPaneOverlay(JPanel messagePanel) {
        this.messagePanel = messagePanel;
        setOpaque(false);  // Allow transparency
        setLayout(new GridBagLayout());  // Center the content

        // Add the message panel to the center
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(messagePanel, gbc);

        // Call the method to block mouse events
        setMouseEventHandling();
    }

    // Block mouse events to prevent interaction with the underlying components
    private void setMouseEventHandling() {
        setFocusable(true);
        setRequestFocusEnabled(true); // Allow focus on the GlassPane

        // Mouse events will be consumed, preventing clicks from passing through
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (!messagePanel.getBounds().contains(evt.getPoint())) {
                    evt.consume();
                }
            }
        });
        
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                if (!messagePanel.getBounds().contains(evt.getPoint())) {
                    evt.consume();
                }
            }
        });
    }

    public void start() {
        // Optional: Start any animation or effects you want to trigger
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Draw a semi-transparent black background (50% opacity)
        g2d.setColor(new Color(0, 0, 0, 128));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.dispose();
    }
}
