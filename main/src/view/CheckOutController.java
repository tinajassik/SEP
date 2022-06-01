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

/**
 * A class
 */
public class CheckOutController
{
  private BookingModelManager modelManager;
  private Region root;
  private ViewHandler viewHandler;
  private MyFileHandler fileHandler;

  @FXML private Text fullName;
  @FXML private Text arrivalDate;
  @FXML private Text departureDate;
  @FXML private Text roomPrice;
  @FXML private Button buttonBack;
  @FXML private Button normalPrice;
  @FXML private Button discountPrice;
  @FXML private Button addFee;
  @FXML private Button checkOut;
  @FXML private TextField fee;
  @FXML private TextField price;
  @FXML private TextField discountText;
  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    displayInitialInfo();

  }
  public Booking getSelectedBookingNew() {
    return viewHandler.getManageBookingController().getSelectedBookingNew();
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

    if (e.getSource() == normalPrice)
    {
      Booking booking = getSelectedBookingNew();
      int day = booking.getDateInterval().getDepartureDate().getDay();
      int month = booking.getDateInterval().getDepartureDate().getMonth();
      int year = booking.getDateInterval().getDepartureDate().getYear();
      if(LocalDate.now().getDayOfMonth() != day || LocalDate.now().getMonthValue() != month || LocalDate.now().getYear() != year)
      {
        BookingList allBookings = modelManager.getAllBookings();
        allBookings.deleteBooking(booking);
        booking.getDateInterval().setDepartureDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
        allBookings.addBooking(booking);
        modelManager.updateBookings(allBookings);
      }

      price.setText(Double.toString(modelManager.getPrice(booking)));
    }

    if (e.getSource() == discountPrice)
    {
      if(discountText.getText().equals(""))
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,"You have not entered the discount");
        alert.showAndWait();
      }
      else
      {
        Booking booking = displayInitialInfo();
        int day = booking.getDateInterval().getDepartureDate().getDay();
        int month = booking.getDateInterval().getDepartureDate().getMonth();
        int year = booking.getDateInterval().getDepartureDate().getYear();
        double discount = Double.parseDouble(discountText.getText());
        if(LocalDate.now().getDayOfMonth() != day || LocalDate.now().getMonthValue() != month || LocalDate.now().getYear() != year)
        {

          BookingList allBookings = modelManager.getAllBookings();
          allBookings.deleteBooking(booking);
          booking.getDateInterval().setDepartureDate(new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear()));
          allBookings.addBooking(booking);

          modelManager.updateBookings(allBookings);
        }
         price.setText(Double.toString(modelManager.priceWithDiscount(booking,discount)));
      }
    }

    if (e.getSource() == addFee)
    {
      if(fee.getText().equals(""))
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,"You have not entered the fee");
        alert.showAndWait();
      }
      else
      {
        double feeValue = Double.parseDouble(fee.getText());

        if(!(price.getText().isEmpty()))
        {
          double priceDouble = Double.parseDouble(price.getText()) + feeValue;
          price.setText(Double.toString(priceDouble));
        }
      }
    }
    if (e.getSource() == checkOut)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm the check out");
      alert.setTitle(null);
      alert.showAndWait();
      if (alert.getResult() == ButtonType.OK)
      {
        Booking booking = displayInitialInfo();
        BookingList allBookings = modelManager.getAllBookings();
        GuestList allGuests = modelManager.getAllGuests();

        for(int i = 0; i < booking.getGuests().size(); i++)
        {
          allGuests.removeGuest(booking.getGuests().getGuest(i));
        }
        allBookings.deleteBooking(booking);
        ArrayList<Object> allData = new ArrayList<>();
        allData.add(allBookings);
        allData.add(allGuests);
        allData.add(modelManager.getAllRooms());
        modelManager.updateAllData(allData);
      }
     }
    }


  public Booking displayInitialInfo() {
    Booking booking = viewHandler.getManageBookingController().getSelectedBookingNew();
    if (booking != null)
    {
      fullName.setText(booking.getBookingGuest().getFirstName() + " " + booking.getBookingGuest().getLastName());
      arrivalDate.setText(booking.getDateInterval().getArrivalDate().toString());
      departureDate.setText(LocalDate.now().toString());
      roomPrice.setText(Double.toString(booking.getBookedRoom().getPrice()));
      fee.clear();
      discountText.clear();
      price.clear();
      price.setEditable(false);

    }

    return booking;
  }

}
