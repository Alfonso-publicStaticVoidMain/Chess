package graphic_resources;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Alfonso Gallego
 */
public class Buttons {

    /**
     * Standard template for a JButton, to be followed by the reset, save and
     * load buttons.
     * @param label String to be displayed inside the button.
     * @return A JButton that:
     * <ul>
     * <li>Is opaque.</li>
     * <li>Does not have a border.</li>
     * <li>Its font is Arial, bold and size 16.</li>
     * <li>Its text and ActionCommand is the label parameter.</li>
     * </ul>
     */
    public static JButton standardButton(String label) {
        JButton result = new JButton();
        result.setOpaque(true);
        result.setBorderPainted(false);
        result.setFont(new Font("Arial", Font.BOLD, 16));
        result.setText(label);
        result.setActionCommand(label);
        return result;
    }

    public static JButton boardButton() {
        JButton button = new JButton();
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(80, 80));
        return button;
    }
    
}
