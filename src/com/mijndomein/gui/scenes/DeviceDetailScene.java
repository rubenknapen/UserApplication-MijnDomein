package com.mijndomein.gui.scenes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.mijndomein.api.objects.Cluster;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DeviceDetailScene {
	
	Group root = new Group(); 
	public Scene scene = new Scene(root, new MainStage().sceneWidth, new MainStage().sceneHeight);
	BorderPane dashboardBorderPane = new BorderPane();
	HBox deviceGroupContainer = new HBox();
	HBox SingleDevicesContainer = new HBox();
	private int deviceIndex;
	private ChoiceBox deviceTypeSelector;
	private int selectedClusterId;

	public DeviceDetailScene(int deviceIndex) {
		this.deviceIndex = deviceIndex;
		buildScene();
	}
	
	private void buildScene() {
		addTitleBar();
		buildDetailsBox();
		root.getChildren().add(dashboardBorderPane);	
	}
	
	private void addTitleBar() {
		GridPane titleBox = new GridPane();
		//titleBox.setGridLinesVisible(true);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setPadding(new Insets(10));
		
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHalignment(HPos.CENTER);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        col3.setHalignment(HPos.RIGHT);
        titleBox.getColumnConstraints().addAll(col1,col2,col3);
		
		titleBox.prefWidthProperty().bind(scene.widthProperty());
		titleBox.setPrefHeight(50);

		titleBox.setStyle("-fx-background-color: #0D47A1; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0.5, 4);");
		Text title = new Text(DevicesScene.deviceListArray[deviceIndex].getName());
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	    title.setFill(Color.web("#FFF"));      
	    
		Button backButton = new Button();
		backButton.setStyle("-fx-background-image: url('/img/arrow-left.png'); -fx-focus-color: transparent;-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
		backButton.setPrefSize(24, 24);	 
		backButton.setOnAction(e -> MainStage.getStage().setScene(new DevicesScene().getScene()));
		titleBox.add(backButton, 0, 0);
		titleBox.add(title, 1, 0);
				
		dashboardBorderPane.setTop(titleBox);
	}
	
	private void buildDetailsBox() {
		VBox detailsBoxContainer = new VBox();
		detailsBoxContainer.setPadding(new Insets(10));
		detailsBoxContainer.setSpacing(10);
		
		Text clusterLabel = new Text("Toevoegen aan groep:");
		clusterLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
		ChoiceBox deviceTypeSelector = new ChoiceBox();
		deviceTypeSelector.setPrefSize(MainStage.sceneWidth - 20, 40);
		
		for (Cluster cluster : DevicesScene.clusterListArray){
			deviceTypeSelector.getItems().add(cluster.getName());
		}
		deviceTypeSelector.getItems().add("-geen-");
		
		Button addToClusterButton = new Button("Toevoegen aan groep");
		addToClusterButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
		    		//selectedClusterId = deviceTypeSelector.getSelectionModel().getselectedindex();
		    		addToCluster();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		Button deleteDeviceButton = new Button("Apparaat verwijderen");
		deleteDeviceButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
		    		deleteDevice();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					MainStage.getStage().setScene(new DevicesScene().getScene());	
				}
		    }
		});
		
		detailsBoxContainer.getChildren().addAll(clusterLabel, deviceTypeSelector, addToClusterButton, deleteDeviceButton);
		dashboardBorderPane.setCenter(detailsBoxContainer);
	}
	
	private void addToCluster() {
		try {
		
			
		URL targetUrl = new URL("http://localhost:8080/MijnDomeinServer6/restservices/domotica/component/link/cluster/3/" + DevicesScene.deviceListArray[deviceIndex].getComponentID());

		HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Content-Type", "application/json");
		

		OutputStream outputStream = httpConnection.getOutputStream();
		outputStream.flush();

		if (httpConnection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ httpConnection.getResponseCode());
		}

		BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
				(httpConnection.getInputStream())));

		httpConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MainStage.getStage().setScene(new DevicesScene().getScene());	
		}
	}
	
	private void deleteDevice() {
		try {
			URL targetUrl = new URL("http://localhost:8080/MijnDomeinServer6/restservices/domotica/component/remove/" + DevicesScene.deviceListArray[deviceIndex].getComponentID());

			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("DELETE");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			
			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ httpConnection.getResponseCode());
			}

			httpConnection.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public Scene getScene() {
		return scene;
	}
	
}
