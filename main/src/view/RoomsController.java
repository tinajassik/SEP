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

/**
 * A class controlling the Rooms window.
 *
 * @author Kristina Jassova
 * @version 1.0
 */
public class RoomsController
{
  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;
  @FXML private TextArea allRoomsArea;
  @FXML private TextArea availableRoomsArea;

  /**
   * Initializes the Rooms window.
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
    RoomList rooms = modelManager.getAllRooms();
    String str = "";

    for (int i = 0; i < rooms.size(); i++)
    {
      str += rooms.getRoom(i).toString() + "\n --------------\n ";
    }
    allRoomsArea.setText(str);
    reset();

    allRoomsArea.setEditable(false);
    availableRoomsArea.setEditable(false);

  }

  /**
   * Resets the Rooms window.
   */
  public void reset()
  {
    updateAvailableRoomsArea();

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
   * A semantic event which indicates that a component-defined action occurred, generated by a component.
   *
   * @param e constructs an ActionEvent object
   */
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack)
    {
      viewHandler.openView("MainView");
    }
  }

  /**
   * Gets all available rooms for one night (updates every day).
   */
  public void updateAvailableRoomsArea()
  {
    Date arrivalDate;
    Date departureDate;

    arrivalDate = new Date(LocalDate.now().getDayOfMonth(),
        LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    departureDate = new Date(tomorrow.getDayOfMonth(), tomorrow.getMonthValue(),
        tomorrow.getYear());

    DateInterval dateInterval = new DateInterval(arrivalDate, departureDate);
    RoomList availableRooms = modelManager.getAvailableRoomsForASpecificPeriod(
        dateInterval);

    String str = "";

    for (int i = 0; i < availableRooms.size(); i++)
    {
      str += availableRooms.getRoom(i).toString() + "\n --------------\n ";
    }
    availableRoomsArea.setText(str);
  }

}
