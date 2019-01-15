import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
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

public class GroupDetailsScene {
	
	Group root = new Group(); 
	public Scene scene = new Scene(root, new MainStage().sceneWidth, new MainStage().sceneHeight);
	BorderPane dashboardBorderPane = new BorderPane();
	HBox deviceGroupContainer = new HBox();
	GridPane toggleButtonsHolder = new GridPane();
	VBox singleDevicesContainer = new VBox();

	ListView<String> deviceList = new ListView<String>();
	ObservableList<String> deviceListItems =FXCollections.observableArrayList (
	    "Camera", "Eettafel verlichting", "Garagedeur", "TV", "Camera", "Eettafel verlichting", "Garagedeur", "TV", "Camera", "Eettafel verlichting", "Garagedeur", "TV");
	
	public GroupDetailsScene() {
		buildScene();
	}
	
	private void buildScene() {
		addTitleBar();
		addDeviceList();
		addToggleButtons();
		borderPaneCollector();
		initActions();
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
		Text title = new Text("Huiskamer - Groep");
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
	
	private void addToggleButtons() {
		//toggleButtonsHolder.setGridLinesVisible(true);
		toggleButtonsHolder.setAlignment(Pos.CENTER);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col1.setHalignment(HPos.LEFT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHalignment(HPos.RIGHT);
        toggleButtonsHolder.getColumnConstraints().addAll(col1,col2);
		
		ToggleButton tb1 = new ToggleButton("Lampen aan");
		ToggleButton tb2 = new ToggleButton("Ramen sluiten");
		ToggleButton tb3 = new ToggleButton("Deuren dicht");
		ToggleButton tb4 = new ToggleButton("Geluid aan");
		ToggleButton tb5 = new ToggleButton("Verwarming aan");
		ToggleButton tb6 = new ToggleButton("Test");
		tb1.setPrefSize(MainStage.sceneWidth/2 - 15, 70);
		tb2.setPrefSize(MainStage.sceneWidth/2 - 15, 70);
		tb3.setPrefSize(MainStage.sceneWidth/2 - 15, 70);
		tb4.setPrefSize(MainStage.sceneWidth/2 - 15, 70);
		tb5.setPrefSize(MainStage.sceneWidth/2 - 15, 70);
		tb6.setPrefSize(MainStage.sceneWidth/2 - 15, 70);
		
		toggleButtonsHolder.add(tb1, 0, 0);
		toggleButtonsHolder.setMargin(tb1, new Insets(0, 0, 10, 0));
		toggleButtonsHolder.add(tb2, 1, 0);
		toggleButtonsHolder.setMargin(tb2, new Insets(0, 0, 10, 0));
		toggleButtonsHolder.add(tb3, 0, 1);
		toggleButtonsHolder.setMargin(tb3, new Insets(0, 0, 10, 0));
		toggleButtonsHolder.add(tb4, 1, 1);
		toggleButtonsHolder.setMargin(tb4, new Insets(0, 0, 10, 0));
		toggleButtonsHolder.add(tb5, 0, 2);
		toggleButtonsHolder.setMargin(tb5, new Insets(0, 0, 10, 0));
		toggleButtonsHolder.add(tb6, 1, 2);
		toggleButtonsHolder.setMargin(tb6, new Insets(0, 0, 10, 0));
	}
	
	private void addDeviceList() {
		singleDevicesContainer.setSpacing(20);
		Text deviceGroupLabel = new Text("Apparaten in groep");
		deviceGroupLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		Separator separator = new Separator();
		Button deleteGroupButton = new Button("Groep verwijderen");
		
		deviceList.setItems(deviceListItems);
		deviceList.setPrefWidth(MainStage.sceneWidth - 20);
		deviceList.setPrefHeight(150);	
		singleDevicesContainer.getChildren().addAll(separator, deviceGroupLabel, deviceList, deleteGroupButton);
	}
	
	private void borderPaneCollector() {
		VBox centerPane = new VBox();
		centerPane.setPadding(new Insets(10));	
		centerPane.getChildren().addAll(toggleButtonsHolder, singleDevicesContainer);
		dashboardBorderPane.setCenter(centerPane);
	}
	
	public void initActions(){
		//Detecting mouse clicked	
		deviceList.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				MainStage.getStage().setScene(new DeviceDetailScene().getScene());
			}
		});
	}
		

	public Scene getScene() {
		return scene;
	}
	
}
