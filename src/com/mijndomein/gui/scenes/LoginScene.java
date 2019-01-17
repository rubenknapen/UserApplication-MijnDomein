package com.mijndomein.gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene {
	
	Group root = new Group(); 
	Scene scene = new Scene(root, new MainStage().sceneWidth, new MainStage().sceneHeight);
	VBox vbox = new VBox();
	Button loginButton = new Button("Inloggen");
	public Stage primaryStage;
	
	public LoginScene() {
		buildScene();
	}
	
	private void buildScene() {
		Text title = new Text("Voer uw gebruikerscode en wachtwoord in");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    
	    HBox userCodeBox = new HBox();    
	    userCodeBox.setAlignment(Pos.CENTER_LEFT);
	    Text userCodeBoxTitle = new Text("Gebruikerscode: ");
	    TextField userCodeField = new TextField();
	    userCodeField.setMaxWidth(150);
	    userCodeField.setPrefHeight(40);  
	    userCodeBox.getChildren().addAll(userCodeBoxTitle, userCodeField);
	    
	    HBox userPWBox = new HBox();
	    userPWBox.setAlignment(Pos.CENTER_LEFT);
	    Text userPWBoxTitle = new Text("Wachtwoord: ");
	    TextField userPWField = new TextField();
	    userPWField.setMaxWidth(150);
	    userPWField.setPrefHeight(40);  
	    userPWBox.getChildren().addAll(userPWBoxTitle, userPWField);
	    
	    loginButton.setPadding(new Insets (10, 20, 10, 20));
	    loginButton.setOnAction(e -> MainStage.getStage().setScene(new DashboardScene().getScene()));
	    
	    vbox.getChildren().addAll(title, userCodeBox, userPWBox, loginButton);
	    //vbox.setStyle("-fx-background-color: #cf1020");
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(10);
	    vbox.prefWidthProperty().bind(scene.widthProperty());
	    vbox.prefHeightProperty().bind(scene.heightProperty());
		root.getChildren().add(vbox);	
	}
	
	private void validateLogin() {
		
	}
	
	public Scene getScene() {
		return scene;
	}
	
}
