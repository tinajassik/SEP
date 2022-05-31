package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

private int count;


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

  public void reset() {
    count = 0;
    displayInitialData();
  }

  public Region getRoot()
  {
    return root;
  }


  public Booking getSelectedBookingNew() {
    return viewHandler.getManageBookingController().getSelectedBookingNew();
  }

  public boolean checkDates(DateInterval dates)
  {
    return dates.compareDatesContinuity();
  }

  public void displayInitialData() {
    Booking booking = getSelectedBookingNew();
    if (booking != null) {
      int day = booking.getDateInterval().getArrivalDate().getDay();
      int month = booking.getDateInterval().getArrivalDate().getMonth();
      int year = booking.getDateInterval().getArrivalDate().getYear();
      if(LocalDate.now().getDayOfMonth() != day || LocalDate.now().getMonthValue() != month || LocalDate.now().getYear() != year)
      {
        arrivalDate.setValue(LocalDate.now());
      }
      roomNumberField.setText(booking.getBookedRoom().getRoomNumber());
//      arrivalDate.setValue(LocalDate.of(booking.getDateInterval().getArrivalDate().getYear(), booking.getDateInterval().getArrivalDate().getMonth(),
//          booking.getDateInterval().getArrivalDate().getDay()));
      departureDate.setValue(LocalDate.of(booking.getDateInterval().getDepartureDate().getYear(), booking.getDateInterval().getDepartureDate().getMonth(),
          booking.getDateInterval().getDepartureDate().getDay()));

      if (count == 0) {
        firstNameField.setText(booking.getBookingGuest().getFirstName());
        lastNameField.setText(booking.getBookingGuest().getLastName());
        nationalityField.setText(booking.getBookingGuest().getNationality());
        addressField.setText(booking.getBookingGuest().getAddress());
        phoneNumberField.setText(booking.getBookingGuest().getPhoneNumber());
//        booking.getDateInterval().setArrivalDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        Date birthday = booking.getBookingGuest().getBirthday();
        birthdayDate.setValue(LocalDate.of(birthday.getYear(), birthday.getMonth(),birthday.getDay()));
        count++;
      }


    }
  }


  public void checkInGuests(ActionEvent e){

    ArrayList<Object> allData = new ArrayList<>();

    // get all Data before manipulating with the file

    GuestList checkedGuests = modelManager.getAllGuests();
    BookingList allBookings = modelManager.getAllBookings();
    RoomList allRooms = modelManager.getAllRooms();
    int start = checkedGuests.size();


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

        System.out.println(checkedGuests);

        firstNameField.clear();
        lastNameField.clear();
        nationalityField.clear();
        addressField.clear();
        phoneNumberField.clear();
        birthdayDate.setValue(null);
      }
     if (e.getSource() == buttonCompleteCheckIn) {
       Booking booking = getSelectedBookingNew();
       System.out.println(booking);
      allBookings.deleteBooking(booking);
       booking.checkedIn();
       LocalDate arrival  = arrivalDate.getValue();
       booking.getDateInterval().setArrivalDate(new Date(arrival.getDayOfMonth(), arrival.getMonthValue(),arrival.getYear()));
       System.out.println(checkedGuests.size());

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
    //update data.bin to add all new guests.txt;
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
