import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ReactionTimer {

    public static void main(String[] args) {
        // Create a JFrame window (GUI for the application)
        JFrame frame = new JFrame("Reaction Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create a JLabel to display the instructions to the user
        JLabel instructionLabel = new JLabel("Click the button below as fast as you can!");
        // Create a JButton to start the benchmark program
        JButton startButton = new JButton("Start");
        // Set colour to go from red --> amber/orange --> green like a traffic light
        // Here, colour is initially set to red as it has not started yet
        startButton.setBackground(Color.red);

        // Add ActionListener to the startButton to start the program
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the button text is "Start" to run the rest of the program
                if (startButton.getText().equals("Start")) {
                    // Update instructionLabel and button text
                    instructionLabel.setText("Wait for the green button, then click it!");
                    startButton.setText("Wait...");
                    // Button is now orange (in waiting phase)
                    startButton.setBackground(Color.orange);
                    // Disable button can only click when it appears green
                    startButton.setEnabled(false);
                    // Create a Timer with a random delay to simulate a reaction time
                    // Random delay makes it unexpected so creates a more realistic reaction
                    Timer timer = new Timer(new Random().nextInt(5000) + 1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Update instructionLabel and button text, enable button
                            instructionLabel.setText("Click NOW!");
                            startButton.setText("Click!");
                            startButton.setBackground(Color.GREEN); // Set background color to green
                            startButton.setEnabled(true);
                            // Record the start time when the button is clicked
                            long startTime = System.currentTimeMillis();
                            // Add ActionListener to the button after it's clicked
                            startButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Calculate the reaction time and show the result in a message dialog
                                    long endTime = System.currentTimeMillis();
                                    long reactionTime = endTime - startTime;
                                    JOptionPane.showMessageDialog(frame, "Your reaction time is: " + reactionTime + " milliseconds", "Result", JOptionPane.INFORMATION_MESSAGE);
                                    // Reset button text and instructionLabel, remove this ActionListener
                                    // Restarts the game
                                    startButton.setText("Start");
                                    instructionLabel.setText("Click the button below as fast as you can!");
                                    startButton.removeActionListener(this);
                                }
                            });
                        }
                    });
                    // Set the timer to run only once
                    timer.setRepeats(false);
                    // Start the timer again
                    timer.start();
                }
            }
        });

        // Set the layout of the JFrame and add components 
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(instructionLabel);
        frame.add(startButton);
        frame.setVisible(true);
    }
}
