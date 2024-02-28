package esprit.tn.controllers;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Reclamation extends Application {

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField toField;

    @FXML
    private Button sendButton;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("reclamation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Reclamation");
        stage.show();
    }

    @FXML
    public void sendEmail(ActionEvent event) {
        String to = toField.getText();
        String subject = subjectField.getText();
        String content = messageArea.getText();
        String username = "rezguiiheb06@gmail.com"; // changer
        String password = "clubafricain1234567890"; // changer
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("rezguiiheb06@gmail.com", "zjty vgzr kvzz jpba");
                    }
                });
        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rezguiiheb06@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject); // Set the subject of the email
            message.setText(content);// Set the content of the email

            // Enable debugging
            session.setDebug(true);

            // Send the message
            Transport.send(message);

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Votre réclamation a été soumise avec succès !\nNous allons examiner attentivement votre demande."); alert.showAndWait();

        } catch (MessagingException e) {
            // Save the failed email
            saveFailedEmail(to, subject, content);

            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to send email. Check your internet connection and try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void saveFailedEmail(String to, String subject, String content) {
        try (FileWriter writer = new FileWriter("failed_emails.txt", true)) {
            writer.write("To: " + to + "\n");
            writer.write("Subject: " + subject + "\n");
            writer.write("Content: " + content + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
