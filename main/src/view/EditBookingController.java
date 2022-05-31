package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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

    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack)
    {
      viewHandler.openView("SearchBooking");
    }
    if (e.getSource() == buttonSave)
    {
      makeChanges();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Information will be changed.");
      alert.setTitle("Edit Booking Confirmation");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
  }

  public void makeChanges()
  {

    Booking booking = getSelectedBooking();
    BookingList bookingList = modelManager.getAllBookings();

    for (int i = 0; i < bookingList.size(); i++)
    {
      if (booking.equals(bookingList.getBooking(i)))
      {
        if (firstNameField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setFirstName(firstNameField.getText());
        if (lastNameField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setLastName(lastNameField.getText());
        if (nationalityField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setNationality(nationalityField.getText());

        LocalDate arrival = arrivalDate.getValue();
        LocalDate departure = departureDate.getValue();
        LocalDate bday = birthdayDate.getValue();

        bookingList.getBooking(i).getDateInterval().setArrivalDate(
            new Date(arrival.getDayOfMonth(), arrival.getMonthValue(), arrival.getYear()));
        bookingList.getBooking(i).getDateInterval().setDepartureDate(
            new Date(departure.getDayOfMonth(), departure.getMonthValue(),
                departure.getYear()));
        bookingList.getBooking(i).getBookingGuest().setBirthday(
            new Date(bday.getDayOfMonth(), bday.getMonthValue(), bday.getYear()));

        if (addressField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setAddress(addressField.getText());
        if (phoneNumberField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setPhoneNumber(phoneNumberField.getText());
        if (roomNumberField.getText() != null)
          bookingList.getBooking(i).getBookedRoom().setRoomNumber(roomNumberField.getText());

        if (extraBedNO.isSelected() || extraBedYES.isSelected())
        {
          if (extraBedNO.isSelected())
            bookingList.getBooking(i).removeExtraBed();
          else
            bookingList.getBooking(i).addExtraBed();
        }

        if (lateCheckInNO.isSelected() || lateCheckInYES.isSelected())
        {
          if (lateCheckInNO.isSelected())
            bookingList.getBooking(i).willNotCheckInLate();
          else
            bookingList.getBooking(i).willCheckInLate();
        }
      }
    }
    modelManager.updateBookings(bookingList);

  }
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
