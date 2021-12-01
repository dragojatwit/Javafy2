import java.io.FileInputStream;
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
	String currentSong;
	
	public void changeSong(String currentSong)
	{
		this.currentSong = currentSong;
	}

	public void play()
	{
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try
		{
	        MediaPlayer player = new MediaPlayer(new Media(currentSong));
	        MediaView mediaView = new MediaView(player);
	
	        FileInputStream input = new FileInputStream("assets/button.jpg");
	        Image image = new Image(input);
	        ImageView imageView = new ImageView(image);
	        Button pause = new Button("dsafsdf",imageView);
	        
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
	        Scene scene = new Scene(pause, 1000, 1000);
	        
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
