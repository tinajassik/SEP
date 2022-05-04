package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingManagementGUI extends Application
{
  @FXML private PieChart availability;

  public void start(Stage window) throws IOException
  {
    window.setTitle("OverLook Hotel Booking Manager");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("mainview.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}
