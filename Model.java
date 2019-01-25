import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Model extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene primaryScene = new Scene(root, 360, 640);
        
        // primaryStage.setResizable(false);
        // primaryStage.initStyle(StageStyle.TRANSPARENT);

        // root.setOnMousePressed(new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {
        //         xOffset = event.getSceneX();
        //         yOffset = event.getSceneY();
        //     }
        // });
        // root.setOnMouseDragged(new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {
        //         primaryStage.setX(event.getScreenX() - xOffset);
        //         primaryStage.setY(event.getScreenY() - yOffset);
        //         primaryStage.setOpacity(0.8);
                
        //     }
        // });
        // root.setOnMouseReleased(new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {
        //         primaryStage.setOpacity(1);
        //     }
        // });
        
        primaryStage.setTitle("App.Skidaddle");
        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }

}