import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Allows viewing of application
 */
public class View
{
	private Stage myStage;
	private FleschScene myFleschScene;

	public View()
	{
		myFleschScene = new FleschScene(new BorderPane());
	}

	public Stage getStage()
	{
		return myStage;
	}

	public void setStage(Stage stage)
	{
		myStage = stage;
		myStage.setTitle("Flesch Score Readability");


		myStage.setWidth(1300);
		myStage.setHeight(700);
		myStage.setScene(myFleschScene);

		stage.show();
	}
}
