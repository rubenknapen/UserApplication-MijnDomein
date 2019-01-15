import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DashboardScene {
	
	Group root = new Group(); 
	public Scene scene = new Scene(root, new MainStage().sceneWidth, new MainStage().sceneHeight);
	BorderPane dashboardBorderPane = new BorderPane();

	public DashboardScene() {
		buildScene();
	}
	
	private void buildScene() {
		addTitleBar();
		addDashboardTiles();
		addDashboardLinks();
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
		Text title = new Text("Dashboard");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	    title.setFill(Color.web("#FFF"));      
	    
		Button logOutButton = new Button();
		logOutButton.setStyle("-fx-background-image: url('/img/logout.png'); -fx-focus-color: transparent;-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
		logOutButton.setPrefSize(24, 24);	 
		logOutButton.setOnAction(e -> MainStage.getStage().setScene(new LoginScene().getScene()));
		titleBox.add(title, 1, 0);
		titleBox.add(logOutButton, 2, 0);
				
		dashboardBorderPane.setTop(titleBox);
	}
	
	private void addDashboardTiles() {
		
		HBox tilesContainer = new HBox();
		tilesContainer.setPadding(new Insets(10));
		tilesContainer.setSpacing(10);
		
		// Temperatuur tile
		DashboardTile temperatuurTile = new DashboardTile("Temperatuur", 24.7, "C");
		
		// Luchtvochtigheid tile
		DashboardTile luchtvochtigheidTile = new DashboardTile("Luchtvochtigheid", 78, "%");
			
		Button testButton = new Button("Test new value");
		testButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	//System.out.println("test");
		    	temperatuurTile.setValue(40);
		    	System.out.println(temperatuurTile.value);
		    }
		});
				
		tilesContainer.getChildren().addAll(temperatuurTile.tile, luchtvochtigheidTile.tile);
		dashboardBorderPane.setCenter(tilesContainer);
		//dashboardBorderPane.setBottom(testButton);
	}
	
	private void addDashboardLinks() {
		VBox linkHolder = new VBox();
		linkHolder.setPadding(new Insets(10));		
		Button devicesButton = new Button("Bekijk apparaten");
		devicesButton.setOnAction(e -> MainStage.getStage().setScene(new DevicesScene().getScene()));
		linkHolder.getChildren().add(devicesButton);
		dashboardBorderPane.setBottom(linkHolder);
	}

	public Scene getScene() {
		return scene;
	}
	
}
