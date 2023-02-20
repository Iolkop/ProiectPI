package com.example.selecareoptionalandlogin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectareOptional implements Initializable{


    public List<Elev> elevi = new ArrayList<Elev>();
    @FXML
    private Label Prenume;

    @FXML
    private Label idElev;
    @FXML
    private Label numeELev;

    @FXML
    private Label optionalELev;

    @FXML
    private Button backElevi;

    @FXML
    private Button salveazaButton;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private String[] optional = {"Informatica","Biologie","Chimie"};


    private DataBase db = new DataBase();
    public String id;
    public int index = 0;

    @FXML
    private ListView<String> listViewElevi = new ListView<String>();
    private ObservableList<String> items = FXCollections.observableArrayList ();

    public SelectareOptional(){}

    public void getElevi() {
        String sql = "Select conturi.idconturi,email,Nume,Prenume,optional from conturi,dateconturi \n" +
                "where conturi.idconturi=dateconturi.idconturi";
        Statement stmt;
        ResultSet res;
        try{
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeQuery(sql);

            while(res.next()) {
                System.out.println("HELLO2");
                Elev elev = new Elev();
                elev.setOptional(res.getString("optional"));
                elev.setId(res.getBigDecimal("idconturi"));
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
        String sql = "Select conturi.idconturi,email,Nume,Prenume,optional from conturi,dateconturi \n" +
                "where conturi.idconturi=dateconturi.idconturi";
        Statement stmt;
        ResultSet res;
        myChoiceBox.getItems().addAll(optional);
        myChoiceBox.setOnAction(this::getOptional);
        try{
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeQuery(sql);

            while(res.next()) {
                System.out.println("HELLO33");
                Elev elev2 = new Elev();
                elev2.setOptional(res.getString("optional"));
                elev2.setId(res.getBigDecimal("idconturi"));
                elev2.setNume(res.getString("Nume"));
                elev2.setPrenume(res.getString("Prenume"));
                elevi.add(elev2);
                items.add(res.getString("idconturi") + " "
                        +res.getString("Prenume")+ " "+res.getString("Nume")+ " "
                        +res.getString("optional"));
            }
            System.out.println("TEST");
            System.out.println(items);
            Prenume.setText(elevi.get(index).getPrenume());
            numeELev.setText(elevi.get(index).getNume());
            this.id = String.valueOf(elevi.get(index).getId());
            listViewElevi.getItems().addAll(items);

            listViewElevi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    System.out.println(listViewElevi.getSelectionModel().getSelectedIndex());
                    index = listViewElevi.getSelectionModel().getSelectedIndex();
                    Elev elev2 = elevi.get(listViewElevi.getSelectionModel().getSelectedIndex());
                    numeELev.setText(elev2.getNume());
                    Prenume.setText(elev2.getPrenume());
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
    void Salveaza(ActionEvent event) throws IOException{
        String myoptional=myChoiceBox.getValue();
        String sql = "UPDATE `selectareoptional`.`conturi` SET `optional` = '"+myoptional+"' WHERE (`idconturi` = '"+this.id+"')";
                // "INSERT INTO `selectareoptional`.`conturi` (`optional`)"+" VALUES ('"+myoptional+"')";
        Statement stmt;
        //UPDATE `selectareoptional`.`conturi` SET `optional` = 'Informatica' WHERE (`idconturi` = '2');
        int res;
        try{
            Connection connection = db.connection();
            stmt = connection.createStatement();
            res = stmt.executeUpdate(sql);
            back();


        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void getOptional(ActionEvent event)
    {
        String myoptional=myChoiceBox.getValue();
        //optionalELev.setText(myoptional);
    }

}
