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
    if (e.getSource() == searchButton)
    {
      String firstName = textField1.getText();
      String lastName = textField2.getText();

      for (int i = 0; i < guests.size(); i++)
      {
        if (guests.getGuest(i).getFirstName().equals(firstName) && guests.getGuest(i).getLastName().equals(lastName))
        {
          textArea1.setText(guests.getGuest(i).toString() + " \n");

        }
        else {
          Alert alert = new Alert(Alert.AlertType.WARNING,
              "Guest not found.");
         alert.setTitle("Missing guest");
         alert.setHeaderText(null);
         alert.showAndWait();
        }
      }
      System.out.println("1");
      BookingList bookingList = new BookingList();
      System.out.println("2");
      System.out.println(bookings.getBookingsByFullName(firstName, lastName).size());
      System.out.println("2.5");
      for (int j = 0; j < bookings.getBookingsByFullName(firstName, lastName).size(); j++)
      {
        System.out.println("3");
        bookingList.addBooking(
            (Booking) bookings.getBookingsByFullName(firstName, lastName).get(j));
      }
      s = "";
      for (int a = 0; a < bookingList.size(); a++)
      {
        System.out.println("2");
        s += "Booking number: " + (a + 1) + "\n";
        s += bookings.getBooking(a) + "\n";
      }

      System.out.println(s);
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
