package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.BookingModelManager;
import model.Date;
import model.DateInterval;
import model.RoomList;

import java.time.LocalDate;

public class RoomsController
{
  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;
  @FXML private TextArea allRoomsArea;
  @FXML private TextArea availableRoomsArea;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    RoomList rooms = modelManager.getAllRooms();
    String str = "";

    for (int i = 0; i < rooms.size(); i++) {
      str += rooms.getRoom(i).toString() + "\n --------------\n " ;
    }
    allRoomsArea.setText(str);
    reset();

    allRoomsArea.setEditable(false);
    availableRoomsArea.setEditable(false);

  }

  public void reset() {
    updateAvailableRoomsArea();

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

  // method to get all available rooms for one night (updates every day)
  public void updateAvailableRoomsArea() {
    Date arrivalDate;
    Date departureDate;

    arrivalDate = new Date(LocalDate.now().getDayOfMonth(), LocalDate.now()
        .getMonthValue(), LocalDate.now().getYear());
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    departureDate = new Date(tomorrow.getDayOfMonth(),tomorrow.getMonthValue(),tomorrow.getYear());

    DateInterval dateInterval = new DateInterval(arrivalDate, departureDate);
    RoomList availableRooms = modelManager.getAvailableRoomsForASpecificPeriod(dateInterval);

    String str = "";

    for (int i = 0; i < availableRooms.size(); i++) {
      str += availableRooms.getRoom(i).toString() + "\n --------------\n " ;
    }
    availableRoomsArea.setText(str);
  }


}
