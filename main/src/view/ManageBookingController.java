package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.BookingList;
import model.BookingModelManager;

import java.io.IOException;

public class ManageBookingController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;
  @FXML private ListView listView;
  @FXML private Button buttonCheckIn;
  @FXML private Button buttonCheckOut;
  @FXML private Button buttonManageBooking;


  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
  }

  public void reset() {
    updateBookings();
  }

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {

    if (e.getSource() == buttonBack)
    {
      viewHandler.openView("MainView");
    }

    if (e.getSource() == buttonCheckIn)
    {
      viewHandler.openView("CheckIn");
    }
  }

  // maybe idk yet if it works
  public void updateBookings() {
    listView.getItems().clear();
    BookingList bookings = modelManager.getAllBookings();
    for (int i = 0; i < bookings.size(); i++)
    {
      listView.getItems().add(bookings.getBooking(i));
    }
  }
}


