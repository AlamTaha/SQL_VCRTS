import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class VCRTS {

    static Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/vcrts";
    static String username = "root";
    static String password = "SplashSQL.01";

    public static void main(String[] args) {

        // Server GUI
        JFrame serverFrame = new JFrame("VCC Server");
        serverFrame.setSize(500, 500);
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setVisible(true);

        JLabel serverTitleLabel = new JLabel("Welcome to the VC Controller Server Dashboard!");
        JLabel serverStatusLabel = new JLabel("Database Status: ");

        JPanel serverPanel = new JPanel();
        serverPanel.add(serverTitleLabel);
        serverPanel.add(serverStatusLabel);
        serverFrame.add(serverPanel);
        System.out.println("Server GUI created");

        // Client GUI
        JFrame clientFrame = new JFrame("VCRTS User");
        clientFrame.setSize(500, 500);
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setVisible(true);

        JLabel clientTitleLabel = new JLabel("Welcome to the VCRTS Client Dashboard!");
        JLabel clientStatusLabel = new JLabel("Submission Status: ");
        JLabel clientSubtitleLabel = new JLabel("To begin, please select an option:");
        JButton vehicleRegButton = new JButton("Register New Vehicle");
        JButton taskSubButton = new JButton("Submit New Task");

        JPanel clientPanel = new JPanel();
        clientPanel.add(clientTitleLabel);
        clientPanel.add(clientStatusLabel);
        clientPanel.add(clientSubtitleLabel);
        clientPanel.add(vehicleRegButton);
        clientPanel.add(taskSubButton);

        clientFrame.add(clientPanel);
        System.out.println("Client GUI created");

        // Vehicle Registration Functionality
        vehicleRegButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame regFrame = new JFrame("Vehicle Registation");
                regFrame.setSize(300, 300);
                regFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                regFrame.setVisible(true);

                JLabel ownerLabel = new JLabel("Enter 4-Digit Owner ID: ");
                JTextField ownerText = new JTextField(20);
                JLabel vinLabel = new JLabel("Enter 4-Digit Vehicle ID: ");
                JTextField vinText = new JTextField(20);
                JLabel resLabel = new JLabel("Enter Residency Time in days: ");
                JTextField resText = new JTextField(20);
                JButton backButton = new JButton("back");
                JButton saveButton = new JButton("save");

                JPanel regPanel = new JPanel();
                regPanel.add(ownerLabel);
                regPanel.add(ownerText);
                regPanel.add(vinLabel);
                regPanel.add(vinText);
                regPanel.add(resLabel);
                regPanel.add(resText);
                regPanel.add(backButton);
                regPanel.add(saveButton);

                regFrame.add(regPanel);

                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        regFrame.setVisible(false);
                    }
                });

                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("New Vehicle Registration Detected");

                        JFrame saveCheck = new JFrame("Save Vehicle Check");
                        saveCheck.setSize(400, 250);
                        saveCheck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        saveCheck.setVisible(true);

                        JLabel statusLabel = new JLabel("New vehicle detected. Save to database?");
                        JButton yesButton = new JButton("YES");
                        JButton noButton = new JButton("NO");

                        JPanel savePanel = new JPanel();
                        savePanel.add(statusLabel);
                        savePanel.add(yesButton);
                        savePanel.add(noButton);

                        saveCheck.add(savePanel);

                        yesButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {

                                int savedOwnerID = Integer.parseInt(ownerText.getText());
                                int savedVin = Integer.parseInt(vinText.getText());
                                int savedRes = Integer.parseInt(resText.getText());
                                        
                                try {
                                    connection = DriverManager.getConnection(url, username, password);
                                    String sql = "INSERT INTO vehicleowner" + "(ownerid , vehicleid , residencytime)" + "VALUES (" + savedOwnerID + ", " + savedVin + ", " + savedRes + ")";
                                    System.out.println("Generating statement: " + sql);
                                    Statement statement = connection.createStatement();
                                    int row = statement.executeUpdate(sql);
                                    if (row > 0) {
                                        System.out.println("Data was inserted");
                                        serverStatusLabel.setText("Database Status: New vehicle registered");
                                        clientStatusLabel.setText("Submission Status: New vehicle registered");
                                    }
                                    connection.close();
                                } catch (SQLException e1) {
                                    e1.getMessage();
                                }

                                saveCheck.setVisible(false);

                                ownerText.setText("");
                                vinText.setText("");
                                resText.setText("");        

                            }
                        });

                        noButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Request denied");
                                serverStatusLabel.setText("Database Status: New vehicle rejected");
                                clientStatusLabel.setText("Submission Status: New vehicle registration denied");


                                saveCheck.setVisible(false);

                                ownerText.setText("");
                                vinText.setText("");
                                resText.setText("");
        
                            }
                        });
                    }
                });
            }
        });

        // Task Submission Functionality
        taskSubButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame subFrame = new JFrame("Task Submission");
                subFrame.setSize(300, 300);
                subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                subFrame.setVisible(true);

                JLabel clientLabel = new JLabel("Enter 4-Digit Client ID: ");
                JTextField clientText = new JTextField(20);
                JLabel durationLabel = new JLabel("Enter Job Duration in days: ");
                JTextField durationText = new JTextField(20);
                JLabel deadlineLabel = new JLabel("Enter Job Deadline Date: ");
                JTextField deadlineText = new JTextField(20);
                JButton backButton = new JButton("back");
                JButton saveButton = new JButton("save");

                JPanel subPanel = new JPanel();
                subPanel.add(clientLabel);
                subPanel.add(clientText);
                subPanel.add(durationLabel);
                subPanel.add(durationText);
                subPanel.add(deadlineLabel);
                subPanel.add(deadlineText);
                subPanel.add(backButton);
                subPanel.add(saveButton);

                subFrame.add(subPanel);

                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        subFrame.setVisible(false);
                    }
                });

                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("New Task Submission Detected");

                        JFrame saveCheck = new JFrame("Save Task Check");
                        saveCheck.setSize(400, 250);
                        saveCheck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        saveCheck.setVisible(true);

                        JLabel statusLabel = new JLabel("New task detected. Save to database?");
                        JButton yesButton = new JButton("YES");
                        JButton noButton = new JButton("NO");

                        JPanel savePanel = new JPanel();
                        savePanel.add(statusLabel);
                        savePanel.add(yesButton);
                        savePanel.add(noButton);

                        saveCheck.add(savePanel);

                        yesButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {

                                int savedClientID = Integer.parseInt(clientText.getText());
                                int savedDuration = Integer.parseInt(durationText.getText());
                                String savedDeadline = deadlineText.getText();
                                                
                                try {
                                    connection = DriverManager.getConnection(url, username, password);
                                    String sql = "INSERT INTO taskowner" + "(clientid , jobduration , jobdeadline)" +  "VALUES (" + savedClientID + ", " + savedDuration + ", '" + savedDeadline + "')";
                                    System.out.println("Generating statement: " + sql);
                                    Statement statement = connection.createStatement();
                                    int row = statement.executeUpdate(sql);
                                    if (row > 0)
                                        System.out.println("Data was inserted");
                                        serverStatusLabel.setText("Database Status: New task submitted");
                                        clientStatusLabel.setText("Submission Status: New task submitted");
        
                                    connection.close();
                                } catch (SQLException e1) {
                                    e1.getMessage();
                                }
        
                                saveCheck.setVisible(false);

                                clientText.setText("");
                                durationText.setText("");
                                deadlineText.setText("");
        
                            }
                        });

                        noButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Request denied");
                                serverStatusLabel.setText("Database Status: New task rejected");
                                clientStatusLabel.setText("Submission Status: New task submission denied");


                                saveCheck.setVisible(false);

                                clientText.setText("");
                                durationText.setText("");
                                deadlineText.setText("");
                
                            }
                        });
                    }
                });
            }
        });
    }
}