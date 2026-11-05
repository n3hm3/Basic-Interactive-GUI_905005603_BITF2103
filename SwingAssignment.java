import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class AppWindow extends JFrame {
    public AppWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 760);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 600));
    }
}

public class SwingAssignment {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel label;

    public SwingAssignment() {
        frame = new AppWindow("Java Swing Assignment");

        panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBackground(new Color(236, 242, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("Java Swing Assignment");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        titleLabel.setForeground(new Color(20, 40, 70));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 190, 210), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        label = new JLabel(
                "Welcome to Java Swing Individual Assignment by Joseph Nehme Kamara",
                SwingConstants.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(new Color(20, 40, 70));
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setIconTextGap(18);

        ImageIcon icon = loadIcon("icon.jpg");
        if (icon != null) {
            Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
            frame.setIconImage(icon.getImage());
        }

        label.setMaximumSize(new Dimension(650, 200));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(label);
        contentPanel.add(Box.createVerticalStrut(20));

        JPanel controls = new JPanel();
        controls.setOpaque(false);
        controls.setLayout(new BorderLayout(10, 10));

        JTextField textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(320, 42));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        JButton changeTextButton = new JButton("Change Text");
        changeTextButton.setBackground(new Color(72, 120, 214));
        changeTextButton.setForeground(Color.WHITE);
        changeTextButton.setFocusPainted(false);

        JButton changeBackgroundButton = new JButton("Change Background");
        changeBackgroundButton.setBackground(new Color(21, 90, 76));
        changeBackgroundButton.setForeground(Color.WHITE);
        changeBackgroundButton.setFocusPainted(false);

        buttonsPanel.add(changeTextButton);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        buttonsPanel.add(changeBackgroundButton);

        controls.add(textField, BorderLayout.CENTER);
        controls.add(buttonsPanel, BorderLayout.SOUTH);

        contentPanel.add(controls);
        panel.add(contentPanel, BorderLayout.CENTER);

        changeTextButton.addActionListener(event -> {
            String nextText = textField.getText().trim();
            if (!nextText.isEmpty()) {
                label.setText(nextText);
                label.revalidate();
                label.repaint();
            }
        });

        changeBackgroundButton.addActionListener(event -> {
            Color chosen = JColorChooser.showDialog(
                    frame, "Choose panel background", panel.getBackground());
            if (chosen != null) {
                panel.setBackground(chosen);
                panel.revalidate();
                panel.repaint();
            }
        });

        frame.add(panel);
    }

    private ImageIcon loadIcon(String fileName) {
        URL resource = SwingAssignment.class.getResource(fileName);
        if (resource != null) {
            return new ImageIcon(resource);
        }

        File file = new File(fileName);
        if (file.exists()) {
            return new ImageIcon(file.getAbsolutePath());
        }

        return null;
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingAssignment().show());
    }
}