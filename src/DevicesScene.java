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

public class DevicesScene {
	
	Group root = new Group(); 
	public Scene scene = new Scene(root, new MainStage().sceneWidth, new MainStage().sceneHeight);
	BorderPane dashboardBorderPane = new BorderPane();
	HBox deviceGroupContainer = new HBox();
	HBox SingleDevicesContainer = new HBox();

	ListView<String> groupList = new ListView<>();
	ObservableList<String> groupListItems =FXCollections.observableArrayList (
	    "Huiskamer", "Slaapkamer", "Tuin", "Garage", "Huiskamer", "Slaapkamer", "Tuin", "Garage", "Huiskamer", "Slaapkamer", "Tuin", "Garage");
	
	ListView<String> deviceList = new ListView<String>();
	ObservableList<String> deviceListItems =FXCollections.observableArrayList (
	    "Camera", "Eettafel verlichting", "Garagedeur", "TV", "Camera", "Eettafel verlichting", "Garagedeur", "TV", "Camera", "Eettafel verlichting", "Garagedeur", "TV");
	
	
	public DevicesScene() {
		buildScene();
	}
	
	private void buildScene() {
		addTitleBar();
		addDeviceGroupHolder();
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
		Text title = new Text("Apparaten");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	    title.setFill(Color.web("#FFF"));      
	    
		Button backButton = new Button();
		backButton.setStyle("-fx-background-image: url('/img/arrow-left.png'); -fx-focus-color: transparent;-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
		backButton.setPrefSize(24, 24);	 
		backButton.setOnAction(e -> MainStage.getStage().setScene(new DashboardScene().getScene()));
		titleBox.add(backButton, 0, 0);
		titleBox.add(title, 1, 0);
				
		dashboardBorderPane.setTop(titleBox);
	}
	
	private void addDeviceGroupHolder() {
		VBox deviceGroupHolder = new VBox();
		deviceGroupHolder.setSpacing(20);
		
		Text groupName = new Text("Groepen");
		groupName.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		Button addGroupButton = new Button("Groep toevoegen");
		addGroupButton.setOnAction(e -> MainStage.getStage().setScene(new AddGroupScene().getScene()));
		
		Separator separator = new Separator();
		
		Text singleDeviceName = new Text("Apparaten");
		singleDeviceName.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		Button addDeviceButton = new Button("Apparaat toevoegen");
		addDeviceButton.setOnAction(e -> MainStage.getStage().setScene(new AddDeviceScene().getScene()));
		
		addGroupList();
		addDeviceList();
		deviceGroupHolder.getChildren().addAll(groupName, deviceGroupContainer, addGroupButton, separator, singleDeviceName, SingleDevicesContainer, addDeviceButton);
		dashboardBorderPane.setMargin(deviceGroupHolder, new Insets(10));
		dashboardBorderPane.setCenter(deviceGroupHolder);
	}
	
	private void addGroupList() {		
		groupList.setItems(groupListItems);
		groupList.setPrefWidth(MainStage.sceneWidth - 20);
		groupList.setPrefHeight(150);
		deviceGroupContainer.getChildren().addAll(groupList);
	}
	
	private void addDeviceList() {		
		deviceList.setItems(deviceListItems);
		deviceList.setPrefWidth(MainStage.sceneWidth - 20);
		deviceList.setPrefHeight(150);	
		SingleDevicesContainer.getChildren().addAll(deviceList);
	}
	
	public void initActions(){
		//Detecting mouse clicked
		groupList.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				MainStage.getStage().setScene(new GroupDetailsScene().getScene());
			}

		});
		
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
