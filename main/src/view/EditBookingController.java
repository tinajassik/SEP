package view;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import utilis.MyFileHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditBookingController
{
  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;
  @FXML private RadioButton lateCheckInYES;
  @FXML private RadioButton lateCheckInNO;
  @FXML private RadioButton extraBedYES;
  @FXML private RadioButton extraBedNO;
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField nationalityField;
  @FXML private TextField addressField;
  @FXML private DatePicker arrivalDate;
  @FXML private DatePicker departureDate;
  @FXML private DatePicker birthdayDate;
  @FXML private TextField phoneNumberField;
  @FXML private TextField roomNumberField;
  @FXML private Button buttonSave;
  @FXML private GridPane main;
  @FXML private Button removeBooking;
  @FXML private ChoiceBox<Room> choiceBox;
  @FXML private Button buttonApply;

  ToggleGroup checkInGroup = new ToggleGroup();
  ToggleGroup extraBedGroup = new ToggleGroup();

  public void init(ViewHandler viewHandler, BookingModelManager modelManager,
      Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
    lateCheckInYES.setToggleGroup(checkInGroup);
    lateCheckInNO.setToggleGroup(checkInGroup);
    extraBedNO.setToggleGroup(extraBedGroup);
    extraBedYES.setToggleGroup(extraBedGroup);

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

  public void reset()
  {
    displayInitialData();
  }

  public Region getRoot()
  {
    return root;
  }

  public Booking getSelectedBooking()
  {
    return  viewHandler.getManageBookingController().getSelectedBookingNew();
  }

  public void displayInitialData()
  {
    Booking booking = getSelectedBooking();
    if (booking != null)
    {
      roomNumberField.setText(booking.getBookedRoom().getRoomNumber());
      arrivalDate.setValue(
          LocalDate.of(booking.getDateInterval().getArrivalDate().getYear(),
              booking.getDateInterval().getArrivalDate().getMonth(),
              booking.getDateInterval().getArrivalDate().getDay()));
      departureDate.setValue(LocalDate.of(booking.getDateInterval().getDepartureDate().getYear(),
          booking.getDateInterval().getDepartureDate().getMonth(),
          booking.getDateInterval().getDepartureDate().getDay()));
      firstNameField.setText(booking.getBookingGuest().getFirstName());
      lastNameField.setText(booking.getBookingGuest().getLastName());
      nationalityField.setText(booking.getBookingGuest().getNationality());
      addressField.setText(booking.getBookingGuest().getAddress());
      phoneNumberField.setText(booking.getBookingGuest().getPhoneNumber());
      birthdayDate.setValue(LocalDate.of(booking.getBookingGuest().getBirthday().getYear(),
          booking.getBookingGuest().getBirthday().getMonth(),
          booking.getBookingGuest().getBirthday().getDay()));
            if (booking.isLateCheckIn()) {lateCheckInYES.setSelected(true);}
            else {lateCheckInNO.setSelected(true);}
            if (booking.hasExtraBed()) {extraBedYES.setSelected(true);}
            else {extraBedNO.setSelected(true);}
          getAvailableRooms(booking.getDateInterval());
            choiceBox.getItems().add(booking.getBookedRoom());
          choiceBox.getSelectionModel().select(booking.getBookedRoom());
    }
  }

  public boolean checkDates(DateInterval dates)
  {
    return dates.compareDatesContinuity();
  }

  public void getAvailableRooms(DateInterval dates)
  {
    choiceBox.getItems().clear();
    RoomList available = modelManager.getAvailableRoomsForASpecificPeriod(
        dates);
    for (int i = 0; i < available.size(); i++)
    {
      choiceBox.getItems().add(available.getRoom(i));
    }
    if (available.size() > 0)
    {
      choiceBox.setValue(available.getRoom(0));
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack)
    {
      viewHandler.openView("SearchBooking");
    }
    if (e.getSource() == buttonSave && !(isFieldEmpty()))
    {

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Information will be changed.");
      alert.setTitle("Edit Booking Confirmation");
      alert.setHeaderText(null);
      alert.showAndWait();

      // only if the OK button is pressed, the booking changed
      if (alert.getResult() == ButtonType.OK)
      {
        makeChanges();
      }
    }
    if (e.getSource() == buttonSave && isFieldEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "You have not filled in all the necessary information. Try again please.");
      alert.setTitle("Missing information");
      alert.setHeaderText(null);
      alert.showAndWait();
    }


    if (e.getSource() == buttonApply)
    {
      LocalDate arrival = this.arrivalDate.getValue();
      int day = arrival.getDayOfMonth();
      int month = arrival.getMonthValue();
      int year = arrival.getYear();
      Date arrivalDate = new Date(day, month, year);
      LocalDate departure = this.departureDate.getValue();
      int day1 = departure.getDayOfMonth();
      int month1 = departure.getMonthValue();
      int year1 = departure.getYear();
      Date departureDate = new Date(day1, month1, year1);
      DateInterval datesToBeBooked = new DateInterval(arrivalDate,
          departureDate);

      if (!(checkDates(datesToBeBooked)))
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Arrival and departure dates are not valid!");
        alert.setTitle("Dates Error");
        alert.setHeaderText(null);
        alert.showAndWait();
        this.arrivalDate.setValue(null);
        this.departureDate.setValue(null);

      }
      else
        getAvailableRooms(datesToBeBooked);

    }

  }

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

    if (!extraBedNO.isSelected() && !extraBedYES.isSelected())
    {
      empty = true;
    }
    if (!lateCheckInNO.isSelected() && !lateCheckInYES.isSelected())
    {
      empty = true;
    }
    if (choiceBox.getSelectionModel().isEmpty())
    {
      empty = true;
    }

    return empty;
  }
  public void makeChanges()
  {
    Booking booking = getSelectedBooking(); // 1 for variable declaration, method getSelectedBooking() has a time complexity of O(1) ---> 2

    BookingList bookingList = modelManager.getAllBookings(); // 1 for variable declaration, method getAllBookings() has a time complexity of O(n) ---> 1 + n

    for (int i = 0; i < bookingList.size(); i++) // 1 for declaration, 1 comparison, 1 for size(), 1 for incrementation
      // the for loop runs n times (n = size of the bookingList) ---> 4n
    {
      if (booking.equals(bookingList.getBooking(i)))
      //equals() takes 1 and getBooking() takes 1 as well because it is using a get(index) method of ArrayList which is a constant time operation ---> 2
      {
        if (firstNameField.getText() != null) //getText() takes 1 and 1 for "!=" ---> 2
          bookingList.getBooking(i).getBookingGuest().setFirstName(firstNameField.getText());
        //getBooking() takes 1, getBookingGuest() takes 1, setFirstName(), getText method takes O(1) as well ---> 4
        if (lastNameField.getText() != null) //getText() takes 1 and 1 for "!=" ---> 2
          bookingList.getBooking(i).getBookingGuest().setLastName(lastNameField.getText());
        //4 constant methods, 3 get methods and 1 set method --->4
        if (nationalityField.getText() != null) //getText() takes 1 and 1 for "!=" ---> 2
          bookingList.getBooking(i).getBookingGuest().setNationality(nationalityField.getText());
        //4 constant methods, 3 get methods and 1 set method --->4

        LocalDate arrival = arrivalDate.getValue(); // 1 for variable declaration, getValue() method is a constant time operation, so takes O(1) ---> 2
        LocalDate departure = departureDate.getValue(); // 1 for variable declaration, getValue() method is a constant time operation, so takes O(1) ---> 2
        LocalDate bday = birthdayDate.getValue(); // 1 for variable declaration, getValue() method is a constant time operation, so takes O(1) ---> 2

        bookingList.getBooking(i).getDateInterval().setArrivalDate(
            new Date(arrival.getDayOfMonth(), arrival.getMonthValue(), arrival.getYear()));
        //getBooking() takes 1, getDateInterval takes 1 and also setArrivaldate() takes 1 ---> 3
        //getDayOfMonth(), getMonthValue(), getYear() are constant operations - get methods - that take O(1) ---> 3
        bookingList.getBooking(i).getDateInterval().setDepartureDate(
            new Date(departure.getDayOfMonth(), departure.getMonthValue(),
                departure.getYear()));
        // 5 get methods that take O(1), 1 set method that takes 1 ---> 6
        bookingList.getBooking(i).getBookingGuest().setBirthday(
            new Date(bday.getDayOfMonth(), bday.getMonthValue(), bday.getYear()));
        // 5 get methods that take O(1), 1 set method that takes 1 ---> 6

        bookingList.getBooking(i).setBookedRoom(choiceBox.getSelectionModel().getSelectedItem());
        //4 constant methods that take O(1) - 3 get methods and 1 set method ---> 4

        if (addressField.getText() != null) //getText() takes 1 and 1 for "!=" ---> 2
          bookingList.getBooking(i).getBookingGuest().setAddress(addressField.getText());
        //4 constant methods that take O(1) - 3 get methods and one set method ---> 4
        if (phoneNumberField.getText() != null) //getText() takes 1 and 1 for "!=" ---> 2
          bookingList.getBooking(i).getBookingGuest().setPhoneNumber(phoneNumberField.getText());
        //4 constant methods that take O(1) - 3 get methods and one set method ---> 4
        if (extraBedNO.isSelected() || extraBedYES.isSelected())
        //isSelected() takes O(1), so 2*1 and "||" takes 1 --->3
        {
          if (extraBedNO.isSelected())
            bookingList.getBooking(i).removeExtraBed(); // 3 constant methods that take O(1) ---> 3

          else
            bookingList.getBooking(i).addExtraBed(); // 2 constant methods that take O(1) ---> 2
        }
        if (lateCheckInNO.isSelected() || lateCheckInYES.isSelected())
        //isSelected() takes O(1), so 2*1 and "||" takes 1 --->3
        {
          if (lateCheckInNO.isSelected())
            bookingList.getBooking(i).willNotCheckInLate();
            // 3 constant methods that take O(1) ---> 3
          else
            bookingList.getBooking(i).willCheckInLate();
          // 2 constant methods that take O(1) ---> 2
        }
      }
    }

    modelManager.updateBookings(bookingList); //method that takes O(n)
  }

  // there is no base case since the method is not recursive
  // for loop runs O(n) times because of the incrementation in each iteration(n++)
  // T(n) = 2+1+n+4n*(2+2+4+2+4+2+4+2+2+2+3+3+6+6+4+2+4+2+4+3+3+3+3)+1 ---> ignoring constants
  // and coefficients we get
  // T(n) = O(n)
  // the method was chosen because it includes calls of other many methods
  // multiple manipulations with ArrayList objects



    public void deleteBooking(ActionEvent e)
    {
      Booking booking = getSelectedBooking();
      BookingList bookingList = modelManager.getAllBookings();
      if (e.getSource() == removeBooking)
      {
        for (int i = 0; i < bookingList.size(); i++)
        {
          if (booking.equals(bookingList.getBooking(i)) && !(booking.isCheckIn()))
          {
            bookingList.deleteBooking(booking);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "The booking will be deleted.");
            alert.setTitle("Cancel selected booking");
            alert.setHeaderText(null);
            alert.showAndWait();
            modelManager.updateBookings(bookingList);
            clearAllFields();
          }
          else if (booking.equals(bookingList.getBooking(i)) && booking.isCheckIn())
          {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "The booking can't be deleted."
                    + "The guests are already checked in.");
            alert.setTitle("Cancel selected booking");
            alert.setHeaderText(null);
            alert.showAndWait();
            modelManager.updateBookings(bookingList);
          }
        }

      }
    }
  public void clearAllFields() {

    for (int i = 0; i < main.getChildren().size(); i++)
    {
      if (main.getChildren().get(i) instanceof TextField)
      {
        ((TextField) main.getChildren().get(i)).clear();
      }
      else if (main.getChildren().get(i) instanceof DatePicker)
      {
        ((DatePicker) main.getChildren().get(i)).setValue(null);
      }
    }

    extraBedYES.setSelected(false);
    extraBedNO.setSelected(false);
    lateCheckInNO.setSelected(false);
    lateCheckInYES.setSelected(false);

  }
}
