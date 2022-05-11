package model;
import utilis.MyFileHandler;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utilis.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BookingModelManager
{
  private String fileName;

  public BookingModelManager(String fileName)
  {
    this.fileName = fileName;
  }


  // Use the MyFileHandler class to retrieve a BookingList object with all Bookings
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();

    try
    {
      allBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileName);
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


  // Use the MyFileHandler class to retrieve a GuestList object with all Guests
  public GuestList getAllGuests()
  {
    GuestList allGuests = new GuestList();

    try
    {
      allGuests = (GuestList) MyFileHandler.readFromBinaryFile(fileName);
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
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();

    try
    {
      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(fileName);
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

  public ArrayList getExpectedArrivals()
  {
    BookingList allBookings = getAllBookings();
    ArrayList expectedArrivals = new ArrayList<>();
    LocalDate currentDate = LocalDate.now();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Booking booking = allBookings.getBooking(i);

      if(booking.getDateInterval().getArrivalDate().equals(currentDate))
      {
        expectedArrivals.add(booking);
      }
    }
    return expectedArrivals;
  }

  public ArrayList getExpectedDepartures()
  {
    BookingList allBookings = getAllBookings();
    ArrayList expectedDepartures = new ArrayList<>();
    LocalDate currentDate = LocalDate.now();

    for (int i = 0; i < allBookings.size() ; i++)
  {
    Booking booking = allBookings.getBooking(i);

    if(booking.getDateInterval().getDepartureDate().equals(currentDate))
    {
      expectedDepartures.add(booking);
    }
  }
    return expectedDepartures;
  }

  public void createBooking(Guest guest, Room room, Date arrivalDate, Date departureDate)
  {
    GuestList allGuests = new GuestList();
    DateInterval dates = new DateInterval(arrivalDate, departureDate);
    Booking booking = new Booking(allGuests, room, dates);
    allGuests.addGuest(guest);
  }

  public ArrayList searchBooking(String firstName, String lastName)
  {
    BookingList allBookings = getAllBookings();

    if(allBookings.getBookingsByFullName(firstName, lastName).size()!=0)

      return allBookings.getBookingsByFullName(firstName, lastName);

     else return null;
  }

  public void removeBooking(String firstName, String lastName)
  {
    BookingList allBookings = getAllBookings();

    for (int i = 0; i < allBookings.size(); i++)
    {
      Booking booking = allBookings.getBooking(i);

      if (booking.getBookingGuest().getFirstName().equals(firstName)
          && booking.getBookingGuest().getLastName().equals(lastName))
        allBookings.deleteBooking(booking);
    }
  }

  public void saveBooking(BookingList bookings)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, bookings);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public RoomList getAvailableRooms()
  {
    RoomList allRooms = getAllRooms();
    RoomList availableRooms = new RoomList();

    for (int i = 0; i < allRooms.size() ; i++)
    {
      Room room = allRooms.getRoom(i);

      if(room.isAvailable())availableRooms.addRoom(room);

    }
    return availableRooms;

  }

}
