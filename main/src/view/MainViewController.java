package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.BookingList;
import model.BookingModelManager;

/**
* A class controlling the Main View window.
*
* @author Kristina Jassova
* @version 1.0
*/
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


  /**
  * Initializes the MainView window.
  *
  * @param viewHandler  the view handler for Check In window
  * @param modelManager object of the BookingModelManager class
  * @param root         object of the Region class
  */
  public void init(ViewHandler viewHandler, BookingModelManager modelManager,
      Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    displayArrivalsAndDepartures();
  }

  /**
  * Resets the MainView window.
  */
  public void reset() {
    displayArrivalsAndDepartures();
  }
  
  /**
  * Gets the root object of the Region.
  *
  * @return the root object
  */
  public Region getRoot()
  {
    return root;
  }
  
  /**
  * Displays arrival and departure dates.
  */
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
  }
}
