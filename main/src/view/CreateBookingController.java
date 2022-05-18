package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateBookingController
{
  private BookingModelManager modelManager;
  private Region root;
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

  ToggleGroup checkInGroup = new ToggleGroup();
  ToggleGroup extraBedGroup = new ToggleGroup();

  public void init(ViewHandler viewHandler, BookingModelManager modelManager,
      Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    lateCheckInYES.setToggleGroup(checkInGroup);
    lateCheckInNO.setToggleGroup(checkInGroup);
    extraBedNO.setToggleGroup(extraBedGroup);
    extraBedYES.setToggleGroup(extraBedGroup);
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack)
    {
      viewHandler.openView("MainView");
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
          empty = true;
      }
      else if (main.getChildren().get(i) instanceof DatePicker)
      {
        if (((DatePicker) main.getChildren().get(i)).getValue() == null)
          empty = true;
      }
      else if (main.getChildren().get(i) instanceof RadioButton)
      {
        if ((!((RadioButton) main.getChildren().get(i)).isSelected()))
          empty = true;
      }
    }
    return empty;
  }

  public void createBooking(ActionEvent e)
  {

    String nationality = nationalityField.getText();
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String phoneNumber = phoneNumberField.getText();
    String address = addressField.getText();
    String roomNumber = roomNumberField.getText();
    ArrayList<Object> allData = new ArrayList<>();
    boolean lateCheckIn;
    boolean extraBed;

    if (e.getSource() == buttonSave && !(isFieldEmpty()))
    {

      // creating objects from the Date class using data retrieved from the DatePicker
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
      LocalDate birthday = birthdayDate.getValue();
      int day2 = birthday.getDayOfMonth();
      int month2 = birthday.getMonthValue();
      int year2 = birthday.getYear();
      Date birthdayGuest = new Date(day2, month2, year2);

      // creating all the necessary fields to create a new booking
      Guest newGuest = new Guest(firstName, lastName, address, phoneNumber,
          nationality, birthdayGuest);
      GuestList guests = new GuestList();
      guests.addGuest(newGuest);
      Room roomToBeBooked = new Room(roomNumber);
      DateInterval datesToBeBooked = new DateInterval(arrivalDate, departureDate);
      modelManager.getAllGuests().addGuest(newGuest);
      Booking newBooking = new Booking(guests, roomToBeBooked, datesToBeBooked);
      BookingList bookingList = modelManager.getAllBookings();

      RoomList availableRooms = modelManager.getAvailableRoomsForASpecificPeriod(
          datesToBeBooked);
      boolean canBeBooked = false;

      for (int i = 0; i < availableRooms.size(); i++)
      {
        if (roomToBeBooked.getRoomNumber().equals(availableRooms.getRoom(i).getRoomNumber()))
        {
          canBeBooked = true;
        }
      }

      if (canBeBooked)
      {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Please confirm that all the information inserted is correct and you wish to create the booking.");
        alert.setTitle("Booking Confirmation");
        alert.setHeaderText(null);
        alert.showAndWait();

        // only if the OK button is pressed, the booking is added to the file
        if (alert.getResult() == ButtonType.OK)
        {
          bookingList.addBooking(newBooking);
          allData.add(modelManager.getAllGuests());
          allData.add(modelManager.getAllRooms());
          allData.add(bookingList);
          modelManager.updateAllData(allData);
        }





        for (int j = 0; j < main.getChildren().size(); j++)
        {
          if (main.getChildren().get(j) instanceof TextField)
          {
            ((TextField) main.getChildren().get(j)).clear();
          }
          else if (main.getChildren().get(j) instanceof DatePicker)
          {
            ((DatePicker) main.getChildren().get(j)).setValue(null);
          }
          else if (main.getChildren().get(j) instanceof RadioButton)
          {
            ((RadioButton) main.getChildren().get(j)).setSelected(false);
          }
        }
      }

      else
      {
        this.arrivalDate.setValue(null);
        this.departureDate.setValue(null);
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "This Room is not available for the specified period");
        alert.setTitle("Room not available");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
    }
    else {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "You have not filled in all the necessary information. Try again please.");
      alert.setTitle("Missing information");
      alert.setHeaderText(null);
      alert.showAndWait();

    }
    }
  }




