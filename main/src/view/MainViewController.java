package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.BookingList;
import model.BookingModelManager;

public class MainViewController
{

  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML Button buttonNewBooking;
  @FXML Button buttonManageBooking;
  @FXML Button buttonRooms;
  @FXML Button buttonGuests;
  @FXML TextArea textAreaArrivals;
  @FXML TextArea textAreaDepartures;


//  @FXML MenuItem exitMenuItem;
//  @FXML MenuItem aboutMenuItem;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    displayArrivalsAndDepartures();

  }

  public void reset() {
    displayArrivalsAndDepartures();
  }

  public Region getRoot()
  {
    return root;
  }

  public void displayArrivalsAndDepartures() {
    BookingList arrivals = modelManager.getExpectedArrivals();
    BookingList departures = modelManager.getExpectedDepartures();
    String arr = "";
    String dep= "";

    for (int i = 0; i < arrivals.size(); i++) {
      arr += arrivals.getBooking(i).getBookingGuest().getLastName() + " "
          + arrivals.getBooking(i).getBookingGuest().getFirstName() + ", Room Number: "
          + arrivals.getBooking(i).getBookedRoom().getRoomNumber() + "\n";
    }
    for (int i = 0; i < departures.size(); i++) {
      dep += departures.getBooking(i).getBookingGuest().getLastName() + " "
          + departures.getBooking(i).getBookingGuest().getFirstName() + ", Room Number: "
          + departures.getBooking(i).getBookedRoom().getRoomNumber() + "\n";
    }
    textAreaArrivals.setText(arr);
    textAreaDepartures.setText(dep);
  }

  public void handleActions(ActionEvent e)
  {

    if (e.getSource() == buttonNewBooking)
    {
      viewHandler.openView("CreateBooking");
    }
    else if(e.getSource() == buttonManageBooking) {
      viewHandler.openView("SearchBooking");
    }
    else if (e.getSource() == buttonRooms) {
      viewHandler.openView("Rooms");
    }
    else if (e.getSource() == buttonGuests) {
      viewHandler.openView("Guests");
    }


    //    else if (e.getSource() == exitMenuItem)
    //    {
    //      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
    //          "Do you really want to exit the program?",
    //          ButtonType.YES, ButtonType.NO);
    //      alert.setTitle("Exit");
    //      alert.setHeaderText(null);
    //
    //      alert.showAndWait();
    //
    //      if (alert.getResult() == ButtonType.YES)
    //      {
    //        System.exit(0);
    //      }
    //    }
    //    else if (e.getSource() == aboutMenuItem)
    //    {
    //      Alert alert = new Alert(Alert.AlertType.INFORMATION);
    //      alert.setHeaderText(null);
    //      alert.setTitle("About");
    //      alert.setContentText("This is just a little program that demonstrates some of the GUI features in Java");
    //      alert.showAndWait();
    //    }
    //  }

  }



}
