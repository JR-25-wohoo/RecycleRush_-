import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RecycleRushGameUI {
    public static void main(String[] args) {
        // Create frame with pastel background (Soft Blue & Peach)
        JFrame frame = new JFrame("Recycle Rush");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a pastel background (Soft Blue & Peach gradient)
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(162, 223, 247), 400, 0, new Color(255, 179, 179));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        frame.setContentPane(panel);

        // Create label with soft color text
        JLabel label = new JLabel("Enter an item to predict its bin:");
        label.setForeground(new Color(88, 91, 112)); // Soft gray text
        label.setFont(new Font("Arial", Font.BOLD, 16));

        // Create input field with soft colors
        JTextField inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBackground(new Color(250, 250, 250)); // Off-white background for input field

        // Create predict button with pastel background
        JButton predictButton = new JButton("Predict");
        predictButton.setBackground(new Color(255, 179, 179)); // Pastel Peach button
        predictButton.setForeground(Color.WHITE);

        // Set up layout and add components
        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(inputField);
        frame.add(predictButton);

        // Action for Predict button
        predictButton.addActionListener(e -> {
            String item = inputField.getText();
            try {
                String command = "python predict.py \"" + item + "\"";
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                // Read the result from Python script
                String result = reader.readLine();
                JOptionPane.showMessageDialog(frame, 
                    result == null || result.trim().isEmpty() ? "Prediction could not be made." : "Predicted bin: " + result);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
