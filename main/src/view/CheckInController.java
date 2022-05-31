package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.converter.LocalDateStringConverter;
import model.*;
import utilis.MyFileHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class controlling the Check-In window.
 *
 * @author Kristina Jassova
 * @version 1.4
 */
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

  private int count;

  /**
   * Initializes the CheckIn window.
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
    reset();
  }

  /**
   * Resets the CheckIn window.
   */
  public void reset()
  {
    count = 0;
    displayInitialData();
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
   * Gets selected item from the listView.
   *
   * @return the Booking object
   */
  public Booking getSelectedBookingNew()
  {
    return viewHandler.getManageBookingController().getSelectedBookingNew();
  }

  /**
   * Displays necessary data to the CheckIn window
   */
  public void displayInitialData()
  {
    Booking booking = getSelectedBookingNew();
    if (booking != null)
    {
      int day = booking.getDateInterval().getArrivalDate().getDay();
      int month = booking.getDateInterval().getArrivalDate().getMonth();
      int year = booking.getDateInterval().getArrivalDate().getYear();
      if (LocalDate.now().getDayOfMonth() != day
          || LocalDate.now().getMonthValue() != month
          || LocalDate.now().getYear() != year)
      {
        //        BookingList allBookings = modelManager.getAllBookings();
        //        allBookings.deleteBooking(booking);
        arrivalDate.setValue(LocalDate.now());
        //        booking.getDateInterval().setArrivalDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        //        allBookings.addBooking(booking);
        //        modelManager.updateBookings(allBookings);
      }
      roomNumberField.setText(booking.getBookedRoom().getRoomNumber());
      //      arrivalDate.setValue(LocalDate.of(booking.getDateInterval().getArrivalDate().getYear(), booking.getDateInterval().getArrivalDate().getMonth(),
      //          booking.getDateInterval().getArrivalDate().getDay()));
      departureDate.setValue(
          LocalDate.of(booking.getDateInterval().getDepartureDate().getYear(),
              booking.getDateInterval().getDepartureDate().getMonth(),
              booking.getDateInterval().getDepartureDate().getDay()));

      if (count == 0)
      {
        firstNameField.setText(booking.getBookingGuest().getFirstName());
        lastNameField.setText(booking.getBookingGuest().getLastName());
        nationalityField.setText(booking.getBookingGuest().getNationality());
        addressField.setText(booking.getBookingGuest().getAddress());
        phoneNumberField.setText(booking.getBookingGuest().getPhoneNumber());
        //        booking.getDateInterval().setArrivalDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        Date birthday = booking.getBookingGuest().getBirthday();
        birthdayDate.setValue(
            LocalDate.of(birthday.getYear(), birthday.getMonth(),
                birthday.getDay()));
        count++;
      }

    }
  }

  /**
   * Checks in the guests.
   *
   * @param e constructs an ActionEvent object
   */
  public void checkInGuests(ActionEvent e)
  {

    ArrayList<Object> allData = new ArrayList<>();

    // get all Data before manipulating with the file

    GuestList checkedGuests = modelManager.getAllGuests();
    BookingList allBookings = modelManager.getAllBookings();
    RoomList allRooms = modelManager.getAllRooms();
    int start = checkedGuests.size();

    if (e.getSource() == buttonCheckInGuest)
    {

      String firstName = firstNameField.getText();
      String lastname = lastNameField.getText();
      String nationality = nationalityField.getText();
      String address = addressField.getText();
      String phoneNumber = phoneNumberField.getText();
      LocalDate birthday = birthdayDate.getValue();
      int day = birthday.getDayOfMonth();
      int month = birthday.getMonthValue();
      int year = birthday.getYear();
      Date birthdayGuest = new Date(day, month, year);
      Guest guest = new Guest(firstName, lastname, address, phoneNumber,
          nationality, birthdayGuest);
      checkedGuests.addGuest(guest);
      firstNameField.clear();
      lastNameField.clear();
      nationalityField.clear();
      addressField.clear();
      phoneNumberField.clear();
      birthdayDate.setValue(null);
    }
    if (e.getSource() == buttonCompleteCheckIn)
    {
      Booking booking = getSelectedBookingNew();
      allBookings.deleteBooking(booking);
      booking.checkedIn();

      for (int i = start - 1; i < checkedGuests.size(); i++)
      {
        booking.getGuests().addGuest(checkedGuests.getGuest(i));
      }

      allBookings.addBooking(booking);

      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "All guests were successfully checked in.");
      alert.setTitle("Check-In complete");
      alert.setHeaderText(null);
      alert.showAndWait();

    }
    //update data.bin to add all new guests.txt;
    allData.add(
        checkedGuests); // now checkedGuests contains all the guests checkedIn before as well as the new ones
    allData.add(allBookings);
    allData.add(allRooms);
    modelManager.updateAllData(allData);
  }

  /**
   * A semantic event which indicates that a component-defined action occurred, generated by a component.
   * @param e constructs an ActionEvent object
   */
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack)
    {
      viewHandler.openView("SearchBooking");
    }

  }
}
