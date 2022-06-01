package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import model.BookingModelManager;

import java.io.IOException;

/**
 * A class responsible for starting up the window for graphical user interface.
 *
 * @author Kristina Jassova
 * @version 1.0
 */
public class BookingManagementGUI extends Application
{
  /**
   * Starts up the window for graphical user interface.
   *
   * @param window the window for graphical user interface
   * @throws IOException is the exception that indicates the failure in reading from the binary file
   */
  public void start(Stage window) throws IOException
  {
    BookingModelManager modelManager = new BookingModelManager("data.bin");
    ViewHandler viewHandler = new ViewHandler(modelManager);
    viewHandler.start(window);
  }
}

