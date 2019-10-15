import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FleschScene extends Scene{

	private BorderPane myRoot;
	private Document myDocument;
	private String text;
	
	public FleschScene(Parent root) {
		super(root);
		myRoot = (BorderPane) root;
		myRoot.setStyle("-fx-background-color: Lavender");
		myDocument = new Document(text);
		
		BorderPane titleBox = new BorderPane();
		titleBox.setPrefHeight(100);
		myRoot.setTop(titleBox);

		Text title = new Text("Flesch Score Calculator");
		title.setFont(new Font(30));
		titleBox.setCenter(title);
		
		TextArea input = new TextArea();
		input.setWrapText(true);
		input.setPrefSize(450, 450);
	
		VBox contents = new VBox();
		HBox h = new HBox();
		h.getChildren().add(input);
		Button fleschIndex = new Button("Flesch Index");
		h.getChildren().add(fleschIndex);
		h.setSpacing(30);
		h.setAlignment(Pos.CENTER);
		contents.getChildren().add(h);
		
		TextField displayScore = new TextField();
		HBox score = new HBox();
		Text t = new Text("Flesch Index");
		Button clear = new Button("Clear");
		score.getChildren().add(displayScore);
		score.getChildren().add(t);
		score.getChildren().add(clear);
		score.setSpacing(20);
		
		score.setAlignment(Pos.CENTER);
		contents.getChildren().add(score);
		contents.setSpacing(30);
		
		myRoot.setCenter(contents);
		
		
		fleschIndex.setOnAction(e -> {
			String i = input.getText();
			myDocument.setText(i);
			double d = myDocument.getFleschScore();
			displayScore.setText(d + "");
		});
		
		clear.setOnAction(e -> {
			input.setText("");
		});
		
		
	}

}
