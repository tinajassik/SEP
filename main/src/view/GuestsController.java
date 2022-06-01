package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.*;

public class GuestsController
{
  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;
  @FXML private ListView guestsListView;
  @FXML private TextField textField1;
  @FXML private TextField textField2;
  @FXML private Pane pane;
  @FXML private TextArea textArea1;
  @FXML private TextArea textArea2;
  @FXML private TextArea text;
  @FXML private Button searchButton;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager,
      Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
  }

  public void reset()
  {
    updateGuests();
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
      text.clear();
      textArea1.clear();
      textArea2.clear();
      textField1.clear();
      textField2.clear();
      text.clear();

    }
  }

  public void updateGuests()
  {
    guestsListView.getItems().clear();

    GuestList guests = modelManager.getAllGuests();
    for (int i = 0; i < guests.size(); i++)
    {
      guestsListView.getItems().add(
          guests.getGuest(i).getFirstName() + " " + guests.getGuest(i).getLastName());

    }
  }

  public void searchGuest(ActionEvent e)
  {
    BookingList bookings = modelManager.getAllBookings();
    GuestList guests = modelManager.getAllGuests();
    String s = "";
    System.out.println(guests);
    System.out.println(bookings);

    boolean found = false;

    if (e.getSource() == searchButton)
    {
      String firstName = textField1.getText();
      String lastName = textField2.getText();

      for (int i = 0; i < guests.size(); i++)
      {
        if (guests.getGuest(i).getFirstName().equals(firstName) && guests.getGuest(i).getLastName().equals(lastName))
        {
          textArea1.setText(guests.getGuest(i).toString() + " \n");

          found = true;

        }

      }

      if (!found) {
          Alert alert = new Alert(Alert.AlertType.WARNING,
                  "Guest not found.");
          alert.setTitle("Missing guest");
          alert.setHeaderText(null);
          alert.showAndWait();

      }

      BookingList bookingList = new BookingList();
      Booking temp = null;
      s = "";
      boolean bookingGuest = true; // to determine if the guest who is being searched is the one who created the booking or no

      for (int i = 0; i < bookings.size(); i++) {
        GuestList guestsForThisBooking = bookings.getBooking(i).getGuests();

        for (int j = 1; j < guestsForThisBooking.size(); j++ ) {

          if (guestsForThisBooking.getGuest(j).getFirstName().equals(firstName) && guestsForThisBooking.getGuest(j).getLastName().equals(lastName)) {
            temp = bookings.getBooking(i);
            bookingGuest = false;
          }
        }
      }



      for (int j = 0; j < bookings.getBookingsByFullName(firstName, lastName).size(); j++)
      {

        bookingList.addBooking(
            (Booking) bookings.getBookingsByFullName(firstName, lastName).get(j));
      }



      if(bookingGuest) {
        for (int a = 0; a < bookingList.size(); a++)
        {

          s += "Booking number: " + (a + 1) + "\n";
          s += bookingList.getBooking(a) + "\n";
        }

      } else s +=  temp;


    }
   textArea2.setText(s);
}
    public void seeMoreInfo()
    {
      GuestList guests = modelManager.getAllGuests();
      String guest = (String) guestsListView.getSelectionModel().getSelectedItem();
      for (int i = 0; i < guests.size(); i++)
      {
        String s =guests.getGuest(i).getFirstName() + " " + guests.getGuest(i).getLastName();
        if(s.equals(guestsListView.getSelectionModel().getSelectedItem()))
          text.setText(guests.getGuest(i).toString());
      }
    }
}
