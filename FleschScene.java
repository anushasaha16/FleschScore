import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
/**
 * Scene that is set when the application is viewed
 * @author anush
 *
 */
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
	
		HBox contents = new HBox();
		VBox score = new VBox();
		contents.getChildren().add(input);
		Button fleschIndex = new Button("Calculate Flesch Index");
		Button clear = new Button("Clear");
		HBox buttons = new HBox();
		buttons.getChildren().add(fleschIndex);
		buttons.getChildren().add(clear);
		buttons.setSpacing(30);
		buttons.setAlignment(Pos.CENTER);
		score.getChildren().add(buttons);
		contents.getChildren().add(score);
		
		Text s = new Text("");
		s.setTextAlignment(TextAlignment.CENTER);
		s.setFont(Font.font("Helvetica", 30)); 
		Text t = new Text("");
		t.setTextAlignment(TextAlignment.CENTER);
		s.setFont(Font.font("Helvetica", 20)); 
		score.getChildren().add(s);
		score.getChildren().add(t);
		score.setSpacing(20);
		
		score.setAlignment(Pos.CENTER);
		contents.setSpacing(30);
		contents.setAlignment(Pos.CENTER);
		myRoot.setCenter(contents);
		
		/**
		 * Sets the fleschIndex button to display the flesch readability score of whatever text the user inputted and 
		 * information regarding the respective target audience once the button is pressed. 
		 * If the user did not input any text, they are prompted to do so.
		 */
		fleschIndex.setOnAction(e -> {
			String i = input.getText();
			myDocument.setText(i);
			double d = myDocument.getFleschScore();
			if(myDocument.getText().isEmpty()) {
				s.setText("Please enter text");
			}
			else {
			s.setText("Your flesch readability score is " + d);
			t.setText(myDocument.targetAudience(d));
			t.setWrappingWidth(400);
			}
			
		});
		
		/**
		 * Sets the clear button to empty the text box once pressed
		 */
		clear.setOnAction(e -> {
			input.setText("");
		});
		
		
	}

}
