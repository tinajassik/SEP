package model;
import utilis.MyFileHandler;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Serializable;
import utilis.MyFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BookingModelManager implements Serializable
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
    DateInterval dates = new DateInterval(arrivalDate, departureDate);

    RoomList availableRooms = getAvailableRoomsForASpecificPeriod(dates);

    for (int i = 0; i < availableRooms.size(); i++)
    {
      if(availableRooms.getRoom(i).equals(room))
      {
        GuestList allGuests = new GuestList();

        Booking booking = new Booking(allGuests, room, dates);
        allGuests.addGuest(guest);
      }
    }
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

      booking.getBookedRoom().setAvailability(false);
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

  public RoomList getAvailableRoomsForASpecificPeriod(DateInterval dateInterval)
  {
    BookingList allBookings = getAllBookings();
    RoomList availableRooms = new RoomList();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Room room = allBookings.getBooking(i).getBookedRoom();

      if(allBookings.getBooking(i).getDateInterval().IsAvailableDate(dateInterval)) availableRooms.addRoom(room);

    }
    return availableRooms;
  }

  public RoomList getSpecificTypeRoomsForAPeriod(String roomType, DateInterval dateInterval)
  {
    RoomList availableRooms = getAvailableRoomsForASpecificPeriod(dateInterval);
    RoomList rooms = new RoomList();

    for (int i = 0; i < availableRooms.size(); i++)
    {
      if(availableRooms.getRoom(i).getRoomType().equals(roomType))rooms.addRoom(availableRooms.getRoom(i));
    }
    return rooms;
  }

  public Double getPrice(Booking booking)
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

  public Double priceWithDiscount(Booking booking)
  {
    BookingList allBookings = getAllBookings();

    double price = 0;

    for (int i = 0; i < allBookings.size(); i++)
    {
      if(allBookings.getBooking(i).equals(booking))
      {
        price = getPrice(booking);
        price = price - (0.1*price);
      }
    }
    return price;
  }


}
