package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Booking;
import model.BookingModelManager;
import utilis.MyFileHandler;

import java.io.IOException;
import java.time.LocalDate;

public class CheckInController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;
  private MyFileHandler fileHandler;
  private ManageBookingController manageBookingController;
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField nationalityField;
  @FXML private TextField addressField;
  @FXML private DatePicker arrivalDate;
  @FXML private DatePicker departureDate;
  @FXML private DatePicker birthdayDate;
  @FXML private TextField phoneNumberField;
  @FXML private TextField roomNumberField;

  @FXML private Button buttonBack;
  @FXML private Button buttonCheckInGuest;
  @FXML private Button buttonCompleteCheckIn;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    roomNumberField = new TextField();
  displayInitialInfo();
  }

  public void reset() {}

  public Region getRoot()
  {
    return root;
  }

  public void displayInitialInfo() {
    Booking booking = null;
    try
    {
      booking = (Booking) fileHandler.readFromBinaryFile("selectedBooking.bin");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    if (booking != null)
    roomNumberField.setText(booking.getBookedRoom().getRoomNumber());
  }
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack )
    {
      viewHandler.openView("SearchBooking");
    }

  }
}
