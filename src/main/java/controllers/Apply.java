package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class Apply {

    @FXML
    private Button Back;


    @FXML
    private Button Postuler;
    @FXML
    private TextField lettre;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    void back(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/offreEmploi.fxml"));
        try {
            Parent root = loader1.load();
            OffreEmploi controller = loader1.getController();
            lettre.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void Postuler(ActionEvent event) {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);



            // Add the nom and prenom TextField values to the PDF document
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 650);
                contentStream.showText("NAME: " + nom.getText() + " " + prenom.getText()+ "         " +"lettre de motivation: " + lettre.getText());

                contentStream.endText();
            }

            // Save the PDF document
            FileOutputStream outputStream = new FileOutputStream("application.pdf");
            document.save(outputStream);
            document.close();

            // Send the PDF file as an email attachment
            String to = "mohamedaziz.chaabi@esprit.tn"; // Replace with the recipient's email address
            String from = "2036ZIZOU@gmail.com"; // Replace with your email address
            String subject = "Job Application";
            String message = "Please find attached my job application.";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server host
            props.put("mail.smtp.port", "587"); // Replace with your SMTP server port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2036ZIZOU@gmail.com", "qobt qznq cnkw nhst"); // Replace with your SMTP server username and password
                }
            });

            Message messageObj = new MimeMessage(session);
            messageObj.setFrom(new InternetAddress(from));
            messageObj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            messageObj.setSubject(subject);
            messageObj.setText(message);

            File pdfFile = new File("application.pdf"); // Replace with the path to your PDF file
            messageObj.setDataHandler(new DataHandler(new FileDataSource(pdfFile)));
            messageObj.setFileName(pdfFile.getName());

            Transport.send(messageObj);

            System.out.println("Email sent successfully.");

        } catch (IOException | MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

}
