/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qcureservations_ver2;

/**
 *
 * @author narag
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.table.TableCellRenderer;
class MultilineCellRenderer extends JTextArea implements TableCellRenderer {

    public MultilineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setFont(new Font("Roboto", Font.PLAIN, 12));
        setMargin(new Insets(5, 10, 5, 10)); // Padding
        setBorder(null); // Remove the visible border that causes the difference
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        setText(value == null ? "" : value.toString());

        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(row % 2 == 0 ? new Color(250, 250, 255) : Color.WHITE); // mimic your striped rows
        }

        // Match row height to content
        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
        int preferredHeight = getPreferredSize().height;
        if (table.getRowHeight(row) != preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }

        return this;
    }
}
