package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Booking;
import model.BookingModelManager;
import model.Date;
import utilis.MyFileHandler;
import java.time.LocalDate;

import java.io.IOException;

public class CheckOutController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;
  private MyFileHandler fileHandler;
  ;

  @FXML private TextField fullName;
  @FXML private TextField arrivalDate;
  @FXML private DatePicker departureDate;
  @FXML private Button buttonBack;
  @FXML private Button buttonPrice;
  @FXML private TextField price;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    reset();
    displayInitialInfo();
  }

  public void reset() {

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
    if (e.getSource() == buttonPrice)
    {

      int day = departureDate.getValue().getDayOfMonth();
      int month = departureDate.getValue().getMonthValue();
      int year = departureDate.getValue().getYear();
      Date departure = displayInitialInfo().getDateInterval().getDepartureDate();
      Booking booking = displayInitialInfo();

      booking.getBookedRoom().getPrice();
      if(departure.getDay() == day && departure.getMonth() == month && departure.getYear() == year)
      {
        price.setText(modelManager.getPrice(booking).toString());
        System.out.println(modelManager.getPrice(booking).toString());
      }
      else
      {
        booking.getDateInterval().getDepartureDate().setDay(day);
        booking.getDateInterval().getDepartureDate().setMonth(month);
        booking.getDateInterval().getDepartureDate().setYear(year);
        price.setText(modelManager.getPrice(booking).toString());
      }
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
      fullName.setText(booking.getBookingGuest().getFirstName() + booking.getBookingGuest().getLastName());
      arrivalDate.setText(booking.getDateInterval().getArrivalDate().toString());
      departureDate.setValue(LocalDate.now());
    }
     return booking;
  }

}
