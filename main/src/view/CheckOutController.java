package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import model.*;
import utilis.MyFileHandler;
import view.ViewHandler;

import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;

public class CheckOutController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;
  private MyFileHandler fileHandler;

  @FXML private Text fullName;
  @FXML private Text arrivalDate;
  @FXML private Text departureDate;
  @FXML private Button buttonBack;
  @FXML private Button normalPrice;
  @FXML private Button discountPrice;
  @FXML private TextField price;
  @FXML private TextField discountText;
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    displayInitialInfo();

  }

  public void reset() {
    displayInitialInfo();
  }

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack )
    {
      viewHandler.openView("SearchBooking");
    }
    Booking booking = displayInitialInfo();
    int day = booking.getDateInterval().getDepartureDate().getDay();
    int month = booking.getDateInterval().getDepartureDate().getMonth();
    int year = booking.getDateInterval().getDepartureDate().getYear();
    if (e.getSource() == normalPrice)
    {
      if(LocalDate.now().getDayOfMonth() != day || LocalDate.now().getMonthValue() != month || LocalDate.now().getYear() != year)
      {
        ArrayList<Object> allData = new ArrayList<>();
        RoomList allRooms = modelManager.getAllRooms();
        GuestList allGuests = modelManager.getAllGuests();
        BookingList allBookings = modelManager.getAllBookings();
        allBookings.deleteBooking(booking);
        booking.getDateInterval().setDepartureDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        allBookings.addBooking(booking);
        allData.add(allBookings);
        allData.add(allRooms);
        allData.add(allGuests);
        modelManager.updateAllData(allData);
      }
      price.setText(modelManager.getPrice(booking).toString());
    }

    if (e.getSource() == discountPrice)
    {
      double discount = Double.parseDouble(discountText.getText());
      if(LocalDate.now().getDayOfMonth() != day || LocalDate.now().getMonthValue() != month || LocalDate.now().getYear() != year)
      {
        ArrayList<Object> allData = new ArrayList<>();
        RoomList allRooms = modelManager.getAllRooms();
        GuestList allGuests = modelManager.getAllGuests();
        BookingList allBookings = modelManager.getAllBookings();
        allBookings.deleteBooking(booking);
        booking.getDateInterval().setDepartureDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        allBookings.addBooking(booking);
        allData.add(allBookings);
        allData.add(allRooms);
        allData.add(allGuests);
        modelManager.updateAllData(allData);
      }
      price.setText(modelManager.priceWithDiscount(booking,discount).toString());
    }
    }


  public Booking displayInitialInfo() {
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
    {
      fullName.setText(booking.getBookingGuest().getFirstName() + " " + booking.getBookingGuest().getLastName());
      arrivalDate.setText(booking.getDateInterval().getArrivalDate().toString());
      departureDate.setText(LocalDate.now().toString());
    }

    return booking;
  }

}
