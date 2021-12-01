import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;


public class audioPlayer extends Application
{
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try
		{
			
	        Media m = new Media(Paths. get(javafyClient.getCurrentSong()).toUri().toString());
	        MediaPlayer player = new MediaPlayer(m);
	        MediaView mediaView = new MediaView(player);
	
	        FileInputStream input = new FileInputStream("assets/button.jpg");
	        Image image = new Image(input);
	        ImageView imageView = new ImageView(image);
	        imageView.setFitHeight(50);
	        imageView.setFitWidth(50);
	        Button pause = new Button("",imageView);
	        
	        pause.setOnAction(value -> {
	        	Status status = player.getStatus();
	        	if(status == status.PLAYING)
	        	{
	        		player.pause();
	        	}else
	        	{
	        		player.play();
	        	}
	        	
	        });
	        
	        Pane root = new Pane(mediaView,pause);
	    
	        Scene scene = new Scene(root, 500, 500);
	        
	        primaryStage.setTitle("Media Player");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	
	        player.play();
	        
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
