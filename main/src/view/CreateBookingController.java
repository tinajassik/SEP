package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import model.BookingModelManager;

public class CreateBookingController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset() {}

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack )
    {
      viewHandler.openView("MainView");
    }
  }
}
