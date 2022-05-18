package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.BookingList;
import model.BookingModelManager;
import model.GuestList;
import model.RoomList;

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

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();

  }

  public void reset() {
    updateGuests();
  }

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack) {
      viewHandler.openView("MainView");
      textArea1.clear();
      textArea2.clear();
      textField1.clear();
      textField2.clear();
    }
  }

  // maybe idk yet if it works
  public void updateGuests() {
    guestsListView.getItems().clear();
    GuestList guests = modelManager.getAllGuests();
    for (int i = 0; i < guests.size(); i++)
    {
      guestsListView.getItems().add(guests.getGuest(i).getFirstName() + " " + guests.getGuest(i).getLastName()) ;
     // if(guestsListView.getItems().)text.setText(guests.getGuest(i).toString());
    }
  }

  public void searchGuest(ActionEvent e)
  {

    BookingList bookings = modelManager.getAllBookings();
    GuestList guests = modelManager.getAllGuests();
    if (e.getSource() == searchButton)
    {
      String firstName = textField1.getText();
      String lastName = textField2.getText();

      for (int i = 0; i < guests.size(); i++)
      {
        if (guests.getGuest(i).getFirstName().equals(firstName)&&guests.getGuest(i).getLastName().equals(lastName))
        {
          textArea1.setText(guests.getGuest(i).toString() + " \n");


          for (int j = 0; j < bookings.getBookingsByFullName(firstName, lastName).size(); j++)
          {

            textArea2.setText(bookings.getBooking(i).toString());
          }
        }
      }
    }

  }
    public void seeMoreInfo()
    {
      GuestList guests = modelManager.getAllGuests();
      for (int i = 0; i < guests.size(); i++)
      {
        text.setText(guests.getGuest(i).toString());

      }
    }





}
