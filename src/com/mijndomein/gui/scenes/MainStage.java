package com.mijndomein.gui.scenes;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainStage extends Application {
	
	public static double sceneWidth = 400;
	public static double sceneHeight = 600;
	private static Stage stage;
	
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	this.stage = primaryStage;
    	stage.getIcons().add(new Image("file:src/img/favicon.png"));
    	primaryStage.setTitle("Huiscentrale App");
        primaryStage.setScene(new DevicesScene().getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage() {
    	return stage;
    }
    
}