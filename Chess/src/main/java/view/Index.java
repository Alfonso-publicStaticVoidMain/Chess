package view;

import controller.IndexController;
import graphic_resources.Buttons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alfonso Gallego
 */
public class Index extends JFrame {
    
    private final JPanel topPanel;
    private final JPanel buttonsPanel;
    private final JLabel title;
    private final JLabel subTitle;
    private final JButton[] buttons;
    private JButton newPieces;
    private final JButton exitButton;
    private static final String[] variantNames = {"Standard Chess", "Almost Chess", "Capablanca Chess", "Janus Chess", "Modern Chess", "Tutti Frutti Chess"};
    
    private IndexController controller;
    
    public Index() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        topPanel = new JPanel(new BorderLayout());

        title = new JLabel("Chess", SwingConstants.CENTER);
        title.setFont(new Font("Garamond", Font.BOLD, 48));

        subTitle = new JLabel("and many variants", SwingConstants.CENTER);
        subTitle.setFont(new Font("Garamond", Font.BOLD, 30));

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(subTitle, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        topPanel.add(Box.createRigidArea(new Dimension(0, 30)), BorderLayout.SOUTH);
        
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttons = new JButton[variantNames.length+2];
        final Dimension buttonSize = new Dimension(200, 50);
        for (int i = 0; i < variantNames.length; i++) {
            JButton button = Buttons.standardButton(variantNames[i]);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setPreferredSize(buttonSize);
            button.setBackground(Color.RED);
            buttons[i] = button;
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        newPieces = Buttons.standardButton("New Pieces");
        newPieces.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons[variantNames.length] = newPieces;
        buttonsPanel.add(newPieces);
        
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        exitButton = Buttons.standardButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons[variantNames.length+1] = exitButton;
        buttonsPanel.add(exitButton);
        
        add(buttonsPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static String[] getVariantNames() {
        return variantNames;
    }
    
    public void setController(IndexController controller) {
        this.controller = controller;
        for (JButton button : buttons) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
            button.addActionListener(controller);
        }
        
    }
}
