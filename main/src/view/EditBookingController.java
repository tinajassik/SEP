package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
  private MyFileHandler fileHandler;

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
      addressField.setText(booking.getBookingGuest().getAdress());
      phoneNumberField.setText(booking.getBookingGuest().getPhoneNumber());
      birthdayDate.setValue(LocalDate.of(booking.getBookingGuest().getBirthday().getYear(),
          booking.getBookingGuest().getBirthday().getMonth(),
          booking.getBookingGuest().getBirthday().getDay()));
      //      String extraBedString = extraBedGroup.getSelectedToggle().get;
      //      String lateCheckInString = checkInGroup.selectedToggleProperty().getName();
      //      System.out.println(lateCheckInString);
      //      if (extraBedString.equals("Yes"))  extraBedYES.setSelected(true);
      //      else extraBedNO.setSelected(true);
      //      if (lateCheckInString.equals("Yes"))lateCheckInYES.setSelected(true);
      //      else lateCheckInNO.setSelected(false);
      //
      //      if (booking.isLateCheckIn()) lateCheckInYES.setSelected(true);
      //      else lateCheckInNO.setSelected(true);
      //      if (booking.getBookedRoom().isExtraBed())extraBedYES.setSelected(true);
      //      else extraBedNO.setSelected(false);

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
    ArrayList<Object> allData = new ArrayList<>();
    Booking booking = getSelectedBooking();
    RoomList allRooms = modelManager.getAllRooms();
    GuestList allGuests = modelManager.getAllGuests();

    BookingList bookingList = modelManager.getAllBookings();

    for (int i = 0; i < bookingList.size(); i++)
    {
      if (booking.equals(bookingList.getBooking(i)))
      {
       if (firstNameField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setFirstName(firstNameField.getText());
        System.out.println("1");
        if (lastNameField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setLastName(lastNameField.getText());
        if (nationalityField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setNationality(nationalityField.getText());
//        if(arrivalDate.getValue().getYear()!=null)booking.getDateInterval().setArrivalDate(arrivalDate.getValue().);
//        if(departureDate.getValue()!=null)booking.getDateInterval().setDepartureDate(departureDate.getValue());

        arrivalDate.setValue(
            LocalDate.of(booking.getDateInterval().getArrivalDate().getYear(),
                booking.getDateInterval().getArrivalDate().getMonth(),
                booking.getDateInterval().getArrivalDate().getDay()));
        departureDate.setValue(LocalDate.of(booking.getDateInterval().getDepartureDate().getYear(),
            booking.getDateInterval().getDepartureDate().getMonth(),
            booking.getDateInterval().getDepartureDate().getDay()));

//        bookingList.getBooking(i).getDateInterval().getArrivalDate().setDay(arrivalDate.);
//        bookingList.getBooking(i).getDateInterval().setDepartureDate(departureDate);
//        bookingList.getBooking(i).getBookingGuest().setBirthday(birthdayDate);

        if (addressField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setAdress(addressField.getText());
        if (phoneNumberField.getText() != null)
          bookingList.getBooking(i).getBookingGuest().setPhoneNumber(phoneNumberField.getText());
        // if(birthdayDate.getValue()!=null)booking.getBookingGuest().setBirthday(birthdayDate.getValue());
        if (roomNumberField.getText() != null)
          bookingList.getBooking(i).getBookedRoom().setRoomNumber(roomNumberField.getText());
      }
    }

    allData.add(allGuests);
    allData.add(allRooms);
    allData.add(bookingList);
    modelManager.updateAllData(allData);


  }
}
