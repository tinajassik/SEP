package model;
import utilis.MyFileHandler;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class containing methods responsible for managing the Booking System
 * @author Andreea Asimine, Aleksandra Adamczak
 */
public class BookingModelManager implements Serializable
{
  private String fileName;

  /**
   * One-argument constructor.
   * @param fileName the name of the file that all data will be saved to
   */
  public BookingModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * Gets a BookingList containing all Booking objects saved to the file
   * @return a BookingList containing all Booking objects saved to the file
   */
  // Use the MyFileHandler class to retrieve a BookingList object with all Bookings
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();
    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++)
      {
        if (temp[i] instanceof BookingList)
        {
          allBookings = (BookingList) temp[i];
          break;
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allBookings;
  }

  /**
   * Gets a GuestList containing all Guest objects saved to the file.
   * @return a GuestList containing all Guest objects saved to the file
   */
  // Use the MyFileHandler class to retrieve a GuestList object with all Guests
  public GuestList getAllGuests()
  {
    GuestList allGuests = new GuestList();

    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++) {
        if (temp[i] instanceof GuestList) {
          for (int j = 0; j < ((GuestList) temp[i]).size(); j++)
            allGuests.addGuest(((GuestList) temp[i]).getGuest(j));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allGuests;
  }

  // Use the MyFileHandler class to retrieve a RoomList object with all Rooms

  /**
   * Gets a RoomList containing all Room objects saved to the file.
   * @return a RoomList containing all Room objects saved to the file
   */
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();

    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++) {
        if (temp[i] instanceof RoomList) {
          for (int j = 0; j < ((RoomList) temp[i]).size(); j++)
            allRooms.addRoom(((RoomList) temp[i]).getRoom(j));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allRooms;
  }

  // Method that returns a booking list with the expected arrivals

  /**
   * Gets a BookingList containing all Booking objects that have current date as their arrival date.
   * @return a BookingList containing all Booking objects that have current date as their arrival date
   */
  public BookingList getExpectedArrivals()
  {
    BookingList allBookings = getAllBookings();
    BookingList expectedArrivals = new BookingList();
    LocalDate currentDate = LocalDate.now();
    Date temp = new Date(currentDate.getDayOfMonth(),
        currentDate.getMonthValue(), currentDate.getYear());

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Booking booking = allBookings.getBooking(i);

      if(booking.getDateInterval().getArrivalDate().equals(temp) && !booking.isCheckIn())
      {
        expectedArrivals.addBooking(booking);
      }
    }
    return expectedArrivals;
  }

  /**
   * Gets a BookingList containing all Booking objects that have current date as their departure date.
   * @return a BookingList containing all Booking objects that have current date as their departure date
   */
  // Method that returns a booking list with the expected departures
  public BookingList getExpectedDepartures()
  {
    BookingList allBookings = getAllBookings();
    BookingList expectedDepartures = new BookingList();
    LocalDate currentDate = LocalDate.now();
    Date temp = new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(),currentDate.getYear());

    for (int i = 0; i < allBookings.size() ; i++)
  {
    Booking booking = allBookings.getBooking(i);

    if(booking.getDateInterval().getDepartureDate().equals(temp))
    {
      expectedDepartures.addBooking(booking);
    }
  }
    return expectedDepartures;
  }

  /**
   * Updates all information about Booking objects in BookingList saved in the file.
   * @param bookingList a new BookingList that will be saved to the file
   */
  public void updateBookings (BookingList bookingList) {

    RoomList allRooms = getAllRooms();
    GuestList guests = getAllGuests();

    ArrayList<Object> allData  = new ArrayList<>();
    allData.add(allRooms);
    allData.add(guests);
    allData.add(bookingList);

    try
    {
      MyFileHandler.writeArrayToBinaryFile(fileName, allData.toArray(new Object[allData.size()]));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
    try
    {
      updateXML();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }


  }

  /**
   * Updates BookingList, GuestList and RoomList saved in the file.
   * @param allData an ArrayList containing new BookingList, GuestList and RoomList which will be saved to the file
   */
  public void updateAllData(ArrayList<Object> allData)
  {
    try
    {
      MyFileHandler.writeArrayToBinaryFile(fileName, allData.toArray(new Object[allData.size()]));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
    try
    {
      updateXML();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }

  /**
   * Gets a RoomList containing rooms available in a given time period
   * @param dateInterval time period within which the availability of rooms will be checked
   * @return list of rooms which are available  in a given time period
   */
  //Method that returns a room list will all available rooms from a specific period
  public RoomList getAvailableRoomsForASpecificPeriod(DateInterval dateInterval)
  {
    BookingList allBookings = getAllBookings();
    RoomList availableRooms = getAllRooms();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Room room = allBookings.getBooking(i).getBookedRoom();

      if(!(allBookings.getBooking(i).getDateInterval().isAvailableDate(dateInterval)))
      {
        availableRooms.removeRoom(room);
      }

    }
    return availableRooms;
  }

  /**
   * Calculates price of the booking.
   * @param booking the booking of which price will be calculated
   * @return price of the booking
   */
  //Method that calculates and returns the price of the booking
  public double getPrice(Booking booking)
  {
    BookingList allBookings = getAllBookings();
    double price=0;

    for (int i = 0; i < allBookings.size(); i++)
    {
      if(allBookings.getBooking(i).equals(booking))
      {
        price = allBookings.getBooking(i).getDateInterval().getNumberOfNights()* allBookings.getBooking(i).getBookedRoom().getPrice();
      }
    }
    return price;
  }

  /**
   * Calculates price of the booking with a discount.
   * @param booking the booking of which price with a discount should be calculated
   * @param discount the discount which should be considered while calculating the price
   * @return price of the booking with a discount
   */
  //Method that returns the price with the discount if the guest is not happy about the facilities of the hotel
  public double priceWithDiscount(Booking booking, double discount)
  {
    BookingList allBookings = getAllBookings();

    double price = 0;

    for (int i = 0; i < allBookings.size(); i++)
    {
      if(allBookings.getBooking(i).equals(booking))
      {
        price = getPrice(booking);
        price = price - ((discount/100)*price);
      }
    }
    return price;
  }

  /**
   * Updates the XML file with information about bookings that is used on the Hotel's webpage
   * @throws FileNotFoundException
   */
  /* updateXML() method updates the XML file with information about all bookings that is used on the Hotel's webpage */
  public void updateXML() throws FileNotFoundException
  {
    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Ola\\WebstormProjects\\SEP1\\Web\\xml\\bookingList.xml");
    PrintWriter write = new PrintWriter(fileOut);
    write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
    write.println("<bookings>");
    for(int i=0; i < getAllBookings().size(); i++)
    {
      write.println("<booking>");
      write.println("<room>" + getAllBookings().getBooking(i).getBookedRoom().getRoomType() + "</room>");
      write.println("<arrival>" + getAllBookings().getBooking(i).getDateInterval().getArrivalDate() + "</arrival>");
      write.println("<departure>" + getAllBookings().getBooking(i).getDateInterval().getDepartureDate()+ "</departure>");
      write.println("</booking>");
    }
    write.println("</bookings>");
    write.close();
    System.out.println("File is created");
  }

  /**
   * Filters bookings by first and last name of person who has made the booking
   * @param firstName the first name by which the bookings will be filtered
   * @param lastName the last name by which the bookings will be filtered
   * @return the bookings which where made by a guest with the given first and last name
   */
  // TODO: Implement the method that filter all booking by first name and last name
    public BookingList filterBookingByName(String firstName, String lastName)
    {
        BookingList allBookings = getAllBookings();
        BookingList filteredBookings = new BookingList();

        for (int i = 0; i < allBookings.size(); i++)
        {
        if(allBookings.getBooking(i).getBookingGuest().getFirstName().equals(firstName) && allBookings.getBooking(i).getBookingGuest().getLastName().equals(lastName))
        {
            filteredBookings.addBooking(allBookings.getBooking(i));
        }
        }
        return filteredBookings;
    }




}
