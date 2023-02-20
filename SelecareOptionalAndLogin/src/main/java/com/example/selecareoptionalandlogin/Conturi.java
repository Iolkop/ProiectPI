package com.example.selecareoptionalandlogin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Conturi implements Initializable{

    public List<Elev> elevi = new ArrayList<Elev>();
    @FXML
    private Label Optional;

    @FXML
    private Label idElev;
    @FXML
    private Label numeELev;

    @FXML
    private Label prenumeELev;

    @FXML
    private Label emailELev;

    @FXML
    private Button adaugaElev;

    @FXML
    private Button backElevi;

    @FXML
    private Button stergeElev;

    private DataBase db = new DataBase();
    public String id;
    public int index = 0;

    @FXML
    private ListView<String> listViewElevi = new ListView<String>();
    private ObservableList<String> items = FXCollections.observableArrayList ();

    public Conturi(){}

    public void getElevi() {
        String sql = "Select * from conturi,dateconturi";
        Statement stmt;
        ResultSet res;
        try{
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeQuery(sql);

            while(res.next()) {
                System.out.println("HELLO");
                Elev elev = new Elev();
                elev.setEmail(res.getString("email"));
                elev.setOptional(res.getString("optional"));
                elev.setId(res.getBigDecimal("idconturi"));
                elev.setParola(res.getString("parola"));
                elev.setNume(res.getString("Nume"));
                elev.setPrenume(res.getString("Prenume"));
                elevi.add(elev);

                listViewElevi.setPrefWidth(100);
                listViewElevi.setPrefHeight(70);

                System.out.println("here");
                ObservableList<String> items = FXCollections.observableArrayList ();

                listViewElevi.getItems().addAll(items);
            }
        }catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql = "Select parola,conturi.idconturi,email,Nume,Prenume,optional from conturi,dateconturi where conturi.idconturi=dateconturi.idconturi";
        Statement stmt;
        ResultSet res;
        try{
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeQuery(sql);

            while(res.next()) {
                System.out.println("HELLO33");
                Elev elev2 = new Elev();
                elev2.setEmail(res.getString("email"));
                elev2.setOptional(res.getString("optional"));
                elev2.setId(res.getBigDecimal("idconturi"));
                elev2.setParola(res.getString("parola"));
                elev2.setNume(res.getString("Nume"));
                elev2.setPrenume(res.getString("Prenume"));
                elevi.add(elev2);
                items.add(res.getString("idconturi") + " "+res.getString("email")+ " "
                        +res.getString("Prenume")+ " "+res.getString("Nume")+ " "+res.getString("optional")+ " "+res.getString("parola"));
            }
            System.out.println("TEST");
            System.out.println(items);
            prenumeELev.setText(elevi.get(index).getPrenume());
            numeELev.setText(elevi.get(index).getNume());
            emailELev.setText(elevi.get(index).getEmail());
            Optional.setText(elevi.get(index).getOptional());
            this.id = String.valueOf(elevi.get(index).getId());
            listViewElevi.getItems().addAll(items);

            listViewElevi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    System.out.println(listViewElevi.getSelectionModel().getSelectedIndex());
                    index = listViewElevi.getSelectionModel().getSelectedIndex();
                    Elev elev2 = elevi.get(listViewElevi.getSelectionModel().getSelectedIndex());
                    numeELev.setText(elev2.getNume());
                    prenumeELev.setText(elev2.getPrenume());
                    emailELev.setText(elev2.getEmail());
                    Optional.setText(elev2.getOptional());
                    idElev.setText(elev2.getId().toString());
                    id = String.valueOf(elevi.get(index).getId());
                }
            });
        }catch (Exception e) {
            System.out.println("catch");
            System.out.println(e);
        }
    }
    public void printElevi() {
        System.out.println(elevi.size());
        for(Elev elev2 : elevi) {
            System.out.println(elev2.getEmail());
        }
    }
    @FXML
    void back()throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("hello-view.fxml");
    }

    @FXML
    void navToAdd(ActionEvent event) throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("AdaugareElev.fxml");
    }

    @FXML
    void stergere(ActionEvent event) throws SQLException {
        String sql = "DELETE FROM conturi WHERE idconturi="+this.id;
        String sql2 = "DELETE FROM dateconturi WHERE idconturi="+this.id;
        Statement stmt;
        int res;
        try{
            System.out.println(this.id);
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeUpdate(sql);
            res=stmt.executeUpdate(sql2);
            listViewElevi.getItems().remove(index);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
