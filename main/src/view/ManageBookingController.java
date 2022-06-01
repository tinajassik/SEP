package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
  @FXML private Button buttonEditBooking;
  @FXML private Button buttonCheckOut;
  @FXML private Button buttonManageBooking;


  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
  }



  public Booking getSelectedBookingNew() {
    return (Booking) listView.getSelectionModel().getSelectedItem();
  }

  public void reset() {

    updateBookings();
    fieldName.clear();
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
      if (listView.getSelectionModel().isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "You have not selected any booking.");
        alert.setTitle("Missing information");
        alert.setHeaderText(null);
        alert.showAndWait();
      }

      else
        viewHandler.openView("CheckIn");
    }

    else if (e.getSource() == buttonSearch)
    {
      String fullName = fieldName.getText();
      if (!(fullName.equals("")))
      {
        listView.getItems().clear();
        String[] temp = fullName.split(" ");

        // in case the receptionist inputs only one string, index out of bounds will occur, therefore:
        try
        {
          String firstName = temp[0];
          String lastName = temp[1];
          BookingList bookings = modelManager.filterBookingByName(firstName,
              lastName);
          for (int i = 0; i < bookings.size(); i++)
          {
            listView.getItems().add(bookings.getBooking(i));
          }

          if (listView.getItems().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Name was not found. Please try again.");
            alert.setTitle("Guest not found");
            alert.setHeaderText(null);
            alert.showAndWait();
          }
        }
        catch (IndexOutOfBoundsException exception)
        {
          Alert alert = new Alert(Alert.AlertType.WARNING,
              "Invalid name. Try again.");
          alert.setTitle("Invalid name detected");
          alert.setHeaderText(null);
          alert.showAndWait();
        }
      }
    }

    else if (e.getSource() == buttonShowAll)
    {
      fieldName.clear();
      updateBookings();
    }

    else if (e.getSource() == buttonEditBooking)
    {
      if (listView.getSelectionModel().isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "You have not selected any booking.");
        alert.setTitle("Missing information");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
      else
        viewHandler.openView("EditBooking");

    }
    else if (e.getSource() == buttonCheckOut)
    {
      if (listView.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "You have not selected any booking.");
        alert.setTitle("Missing information");
        alert.setHeaderText(null);
        alert.showAndWait();

      } else {
        if (!((Booking)listView.getSelectionModel().getSelectedItem()).isCheckIn())
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setContentText(
                  "The guest you want to check out has not checked in");
          alert.showAndWait();
        }
        else
        {
          viewHandler.openView("CheckOut");
        }

      }

    }
  }

  public void updateBookings() {
    listView.getItems().clear();
    BookingList bookings = modelManager.getAllBookings();
    for (int i = 0; i < bookings.size(); i++)
    {
      listView.getItems().add(bookings.getBooking(i));
    }
  }
}


