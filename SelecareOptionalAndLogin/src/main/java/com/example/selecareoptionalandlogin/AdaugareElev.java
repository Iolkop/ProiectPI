package com.example.selecareoptionalandlogin;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class AdaugareElev {
    public TextField textfieldEmail;
    public TextField textfieldID;
    public TextField textfieldParola;
    public TextField textfieldNume;
    public TextField textfieldPrenume;

    DataBase db = new DataBase();
    public void confirm(ActionEvent actionEvent) throws IOException {
        String sql = "INSERT INTO `selectareoptional`.`conturi` (`idconturi`, `email`, `parola`, `optional`)" +
                " VALUES ('"+textfieldID.getText()+"','"+textfieldEmail.getText()+"','"+textfieldParola.getText()+"','"+""+"')";
        String sql1="INSERT INTO `selectareoptional`.`dateconturi` (`idconturi`, `Nume`, `Prenume`)"+" VALUES ('"+textfieldID.getText()+"','"+
                textfieldNume.getText()+"','"+textfieldPrenume.getText()+"')";
        System.out.println(sql);
        Statement stmt;
        int res;
        try{
//            System.out.println(this.id);
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeUpdate(sql);
            res = stmt.executeUpdate(sql1);
            backToElevi();


        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void backToElevi() throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("Conturi.fxml");
    }
}
