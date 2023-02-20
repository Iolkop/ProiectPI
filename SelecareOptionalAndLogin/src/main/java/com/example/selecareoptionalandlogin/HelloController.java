package com.example.selecareoptionalandlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class HelloController {

    DataBase database = new DataBase();

    @FXML
    private ImageView image;
    @FXML
    private TextField email;

    @FXML
    private PasswordField parola;


    @FXML
    public void getData(ActionEvent actionEvent) throws IOException, SQLException {
        String uname = email.getText();
        String pass = parola.getText();
        Connection connection = database.connection();
        HelloApplication main = new HelloApplication();
        String sql = "SELECT * FROM `conturi` WHERE email='"+email.getText()+"' AND parola='"+parola.getText()+"'";
        System.out.println(sql);
        Statement stmt;
        ResultSet res;
        if (uname.equals("") && pass.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Username or Password Blank");
        }
        else try{
            stmt = connection.prepareStatement("Select * from conturi where email=? and parola=?");
            ((PreparedStatement) stmt).setString(1,uname);
            ((PreparedStatement) stmt).setString(2,pass);
            res = stmt.executeQuery(sql);
            System.out.println(res);
                if(uname.equals("admin") && pass.equals("1"))
                {
                    JOptionPane.showMessageDialog(null,"Login Admin");
                    System.out.println("admin");
                    main.changeScene("Conturi.fxml");
                }
               else if(res.next())
                {
                    JOptionPane.showMessageDialog(null,"Login");
                    System.out.println("elev");
                    main.changeScene("SelectareOptional.fxml");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Register Failed");
                    email.setText("");
                    parola.setText("");
                    email.requestFocus();
                }
        }
        catch (Exception e){
            System.out.println(e);}
    }
}

