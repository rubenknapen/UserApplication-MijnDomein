import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DashboardTile {

	VBox tile = new VBox();
	public double value;
	private String title;
	private String textValue;
	private Text tileValue;
	private String valueSymbol;
	
	public DashboardTile(String title, double value, String valueSymbol) {
		
		this.title = title;
		this.value = value;
		this.valueSymbol = valueSymbol;
		
		tile.setMinWidth(MainStage.sceneWidth / 2 - 15);
		tile.setMinHeight(MainStage.sceneWidth / 3 - 15);
		tile.setAlignment(Pos.CENTER);
		tile.setPadding(new Insets(10));
		tile.setStyle("-fx-background-color: #FFEA00; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0.5, 4);");
		
		Text tileTitle = new Text(title);
		tileTitle.setFill(Color.web("#000"));
		tileTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		
		String textValue = String.valueOf(value);		
		tileValue = new Text(textValue + " " + valueSymbol);
		
		
		tileValue.setFill(Color.web("#000"));
		tileValue.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		tile.getChildren().addAll(tileTitle, tileValue);
	}
	
	public void setValue(double value) {
		this.value = value;
		String textValue = String.valueOf(value);
		tileValue.setText(textValue);
	}
	
}
