package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.Region;
import javafx.util.converter.LocalDateStringConverter;
import model.*;
import utilis.MyFileHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class controlling the Check In window
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
  @FXML private GridPane main;
  @FXML private Button buttonBack;
  @FXML private Button buttonCheckInGuest;
  @FXML private Button buttonCompleteCheckIn;
  private int count;

  /**
   * Initializes the Check In window
   * @param viewHandler the view handler for Check In window
   * @param modelManager
   * @param root
   */
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();

    // disabling past days in DatePicker for arrival and departure taken from stackoverflow
    arrivalDate.setDayCellFactory(picker -> new DateCell() {
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();

        setDisable(empty || date.compareTo(today) < 0 );
      }
    });

    departureDate.setDayCellFactory(picker -> new DateCell() {
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();

        setDisable(empty || date.compareTo(today) < 0 );
      }
    });

  }

  /**
   * Resets the CheckIn window.
   */

  public void reset() {
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
   * Checks if one or more fields are not filled.
   *
   * @return the true if fields are epmty
   */
  public boolean isFieldEmpty()
  {

    boolean empty = false;

    for (int i = 0; i < main.getChildren().size(); i++)
    {

      if (main.getChildren().get(i) instanceof TextField)
      {
        if (((TextField) main.getChildren().get(i)).getText().isEmpty())
        {
          empty = true;
        }
      }
      else if (main.getChildren().get(i) instanceof DatePicker)
      {
        if (((DatePicker) main.getChildren().get(i)).getValue() == null)
        {
          empty = true;
        }
      }
    }

    return empty;
  }

  /**
   * Gets selected item from the listView.
   *
   * @return the Booking object
   */
  public Booking getSelectedBookingNew() {
    return viewHandler.getManageBookingController().getSelectedBookingNew();
  }

  /**
   * Checks whether the date interval given by the argument is valid
   *
   * @return true or false
   */
  public boolean checkDates(DateInterval dates)
  {
    return dates.compareDatesContinuity();
  }

  /**
   * Displays necessary data to the CheckIn window.
   */
  public void displayInitialData() {
    Booking booking = getSelectedBookingNew();
    if (booking != null) {
      arrivalDate.setValue(LocalDate.now());
      roomNumberField.setText(booking.getBookedRoom().getRoomNumber());
      departureDate.setValue(LocalDate.of(booking.getDateInterval().getDepartureDate().getYear(), booking.getDateInterval().getDepartureDate().getMonth(),
          booking.getDateInterval().getDepartureDate().getDay()));

      if (count == 0) {
        firstNameField.setText(booking.getBookingGuest().getFirstName());
        lastNameField.setText(booking.getBookingGuest().getLastName());
        nationalityField.setText(booking.getBookingGuest().getNationality());
        addressField.setText(booking.getBookingGuest().getAddress());
        phoneNumberField.setText(booking.getBookingGuest().getPhoneNumber());
        Date birthday = booking.getBookingGuest().getBirthday();
        birthdayDate.setValue(LocalDate.of(birthday.getYear(), birthday.getMonth(),birthday.getDay()));
        count++;
      }
    }
  }

  /**
   * Checks in the guests.
   *
   * @param e constructs an ActionEvent object
   */
  public void checkInGuests(ActionEvent e){

    ArrayList<Object> allData = new ArrayList<>();

    // get all Data before manipulating with the file

    GuestList checkedGuests = modelManager.getAllGuests();
    BookingList allBookings = modelManager.getAllBookings();
    RoomList allRooms = modelManager.getAllRooms();
    int start = checkedGuests.size();

      if(e.getSource() == buttonCheckInGuest && !(isFieldEmpty())) {


        String firstName = firstNameField.getText();
        String lastname = lastNameField.getText();
        String nationality = nationalityField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        LocalDate birthday = birthdayDate.getValue();


        int day = birthday.getDayOfMonth();
        int month = birthday.getMonthValue();
        int year = birthday.getYear();
        Date birthdayGuest = new Date(day,month,year);
        Guest guest = new Guest(firstName,lastname,address,phoneNumber,nationality,birthdayGuest);
        checkedGuests.addGuest(guest);
        System.out.println(checkedGuests);
        firstNameField.clear();
        lastNameField.clear();
        nationalityField.clear();
        addressField.clear();
        phoneNumberField.clear();
        birthdayDate.setValue(null);
      }

      else if(e.getSource() == buttonCheckInGuest && isFieldEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "You have not filled in all the necessary information. Try again please.");
        alert.setTitle("Missing information");
        alert.setHeaderText(null);
        alert.showAndWait();
      }

      if (e.getSource() == buttonCompleteCheckIn) {

        DateInterval bookedDates = new DateInterval(new Date(arrivalDate.getValue().getDayOfMonth(),arrivalDate.getValue().getMonthValue(),
                arrivalDate.getValue().getYear()), new Date(departureDate.getValue().getDayOfMonth(),departureDate.getValue().getMonthValue(),
                departureDate.getValue().getYear()));

        if (checkDates(bookedDates)) {

          Booking booking = getSelectedBookingNew();
          allBookings.deleteBooking(booking);
          booking.checkedIn();
          booking.getDateInterval().setArrivalDate(new Date(arrivalDate.getValue().getDayOfMonth(),arrivalDate.getValue().getMonthValue(),arrivalDate.getValue().getYear()));

          for (int i = start - 1; i < checkedGuests.size(); i++) {
            booking.getGuests().addGuest(checkedGuests.getGuest(i));
          }

          allBookings.addBooking(booking);

          Alert alert = new Alert(Alert.AlertType.INFORMATION,
                  "All guests were successfully checked in.");
          alert.setTitle("Check-In complete");
          alert.setHeaderText(null);
          alert.showAndWait();

        }
        else {

          Alert alert = new Alert(Alert.AlertType.WARNING,
                  "Arrival date is after departure date or they are equal.");
          alert.setTitle("Invalid Dates inserted");
          alert.setHeaderText(null);
          alert.showAndWait();

        }

      }
    //update data.bin to add all new guests.txt;
    allData.add(checkedGuests); // now checkedGuests contains all the guests checkedIn before as well as the new ones
    allData.add(allBookings);
    allData.add(allRooms);
    modelManager.updateAllData(allData);

  }


  /**
   * A semantic event which indicates that a component-defined action occurred, generated by a component.
   *
   * @param e constructs an ActionEvent object
   */
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack )
    {
      viewHandler.openView("SearchBooking");
    }

  }
}
