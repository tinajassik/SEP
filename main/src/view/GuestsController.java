package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    }
  }

  // maybe idk yet if it works
  public void updateGuests() {
    guestsListView.getItems().clear();
    GuestList guests = modelManager.getAllGuests();
    for (int i = 0; i < guests.size(); i++)
    {
      guestsListView.getItems().add(guests.getGuest(i).getFirstName() + " " + guests.getGuest(i).getLastName()) ;
    }
  }


}
