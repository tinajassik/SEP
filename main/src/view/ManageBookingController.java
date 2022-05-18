package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Booking;
import model.BookingList;
import model.BookingModelManager;
import utilis.MyFileHandler;

import java.io.IOException;

public class ManageBookingController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;
  private MyFileHandler fileHandler;

  @FXML private Button buttonBack;
  @FXML private ListView listView;
  @FXML private Button buttonCheckIn;
  @FXML private Button buttonSearch;
  @FXML private Button buttonShowAll;
  @FXML private TextField fieldName;
  @FXML private Button buttonCheckOut;
  @FXML private Button buttonManageBooking;


  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
  }

  public void getSelectedBooking() {
    Booking selectedBooking = (Booking) listView.getSelectionModel().getSelectedItem();
    try {
      fileHandler.writeToBinaryFile("selectedBooking.bin",selectedBooking);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
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

    else if (e.getSource() == buttonCheckIn)
    {
      viewHandler.openView("CheckIn");
    }

    else if (e.getSource() == buttonSearch) {
      listView.getItems().clear();
      String fullName = fieldName.getText();
      String[] temp = fullName.split(" ");
      String firstName = temp[0];
      String lastName = temp[1];
      BookingList bookings = modelManager.filterBookingByName(firstName,lastName);
      for (int i = 0; i < bookings.size(); i++)
      {
        listView.getItems().add(bookings.getBooking(i));
      }
    }

    else if(e.getSource() == buttonShowAll) {
      fieldName.clear();
      updateBookings();
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


