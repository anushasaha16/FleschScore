import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Launches Java Application
 * @author anush
 *
 */
public class FleschApp extends Application{

	private View myView;

	public FleschApp()
	{
		myView = new View();
	}
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		myView.setStage(primaryStage);
	}
}
