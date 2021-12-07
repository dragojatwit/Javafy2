import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.util.Duration;


public class audioPlayer extends Application
{
	
	private Timer timer;

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try
		{
			
	        Media m = new Media(Paths. get(javafyClient.currentSong.getPath()).toUri().toString());
	        MediaPlayer player = new MediaPlayer(m);
	        MediaView mediaView = new MediaView(player);
	        
	        
	        
	        FileInputStream input = new FileInputStream("assets/button.jpg");
	        Image image = new Image(input);
	        ImageView imageView = new ImageView(image);
	        imageView.setFitHeight(50);
	        imageView.setFitWidth(50);
	        Button pause = new Button("",imageView);
	        
	        Pane root = new Pane(mediaView,pause);
	    
	        Scene scene = new Scene(root, 500, 500);
	        
	        primaryStage.setTitle("Media Player");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	
	        player.play();
	        System.out.println("started");

	        	//System.out.println("playing");
	        double seconds = m.getDuration().toSeconds();
	        timer = new Timer();
	        TimerTask task = new TimerTask()
	        		{
	        			public void run()
	        			{
	        				player.stop();
	        				player.play();
	        			}
	        		};
	        		
    		pause.setOnAction(value -> {
	        	Status status = player.getStatus();
	        	if(status == status.PLAYING)
	        	{
	        		player.pause();
	        		timer.cancel();
	        	}else
	        	{
	        		player.play();
	        		this.timer = new Timer();
	        		this.timer.schedule(task, (long)player.getTotalDuration().toSeconds() / 1000);
	        	}
	        	
	        });
	        
	        timer.schedule(task, (long) seconds / 1000);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
