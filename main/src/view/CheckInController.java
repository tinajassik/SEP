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
    reset();
  }

  public void reset() {
    displayInitialData();
  }

  public Region getRoot()
  {
    return root;
  }

  public Booking getSelectedBooking() {

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

    return booking;

  }
  public void displayInitialData() {
    Booking booking = getSelectedBooking();
    if (booking != null) {
      roomNumberField.setText(booking.getBookedRoom().getRoomNumber());
      arrivalDate.setValue(LocalDate.of(booking.getDateInterval().getArrivalDate().getYear(), booking.getDateInterval().getArrivalDate().getMonth(),
          booking.getDateInterval().getArrivalDate().getDay()));
      departureDate.setValue(LocalDate.of(booking.getDateInterval().getDepartureDate().getYear(), booking.getDateInterval().getDepartureDate().getMonth(),
          booking.getDateInterval().getDepartureDate().getDay()));
    }
  }


  public void checkInGuests(ActionEvent e){

    ArrayList<Object> allData = new ArrayList<>();

    // get all Data before manipulating with the file
    GuestList checkedGuests = modelManager.getAllGuests();
    BookingList allBookings = modelManager.getAllBookings();
    RoomList allRooms = modelManager.getAllRooms();


      if(e.getSource() == buttonCheckInGuest) {
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

        firstNameField.clear();
        lastNameField.clear();
        nationalityField.clear();
        addressField.clear();
        phoneNumberField.clear();
        birthdayDate.setValue(null);
      }
     if (e.getSource() == buttonCompleteCheckIn) {
       // checkIn the person who created the booking automatically without typing again the info
       Booking booking = getSelectedBooking();
       checkedGuests.addGuest(booking.getBookingGuest());

       // change the status of the booking after completing the checkIn of all guests
       Booking updated = allBookings.getBooking(booking);
       updated.checkedIn();
       allBookings.deleteBooking(booking);
       allBookings.addBooking(updated);

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "All guests were successfully checked in.");
        alert.setTitle("Check-In complete");
        alert.setHeaderText(null);
        alert.showAndWait();
      }

    //update data.bin to add all new guests;
    allData.add(checkedGuests); // now checkedGuests contains all the guests checkedIn before as well as the new ones
    allData.add(allBookings);
    allData.add(allRooms);
    modelManager.updateAllData(allData);



    }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack )
    {
      viewHandler.openView("SearchBooking");
    }

  }
}
