package model;
import utilis.MyFileHandler;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

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


//  public ArrayList<Object> getAllData() {
//
//  }
  // Use the MyFileHandler class to retrieve a BookingList object with all Bookings
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();

    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++) {
        if (temp[i] instanceof BookingList) {
          for (int j = 0; j < ((BookingList) temp[i]).size(); j++)
            allBookings.addBooking(((BookingList) temp[i]).getBooking(j));
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

  // method saveBooking rewrites all previously added data to the file with the addition of a new booking
  public void saveBooking(ArrayList<Object> allData)
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
  }

  public RoomList getAvailableRoomsForASpecificPeriod(DateInterval dateInterval)
  {
    BookingList allBookings = getAllBookings();
    RoomList availableRooms = new RoomList();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Room room = allBookings.getBooking(i).getBookedRoom();

      if(allBookings.getBooking(i).getDateInterval().isAvailableDate(dateInterval)) availableRooms.addRoom(room);

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

  public void updateXML(BookingList bookingList) throws FileNotFoundException
  {
    FileOutputStream fileOut = new FileOutputStream("bookingList.xml");
    PrintWriter write = new PrintWriter(fileOut);
    write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
    write.println("<bookings>");
    for(int i=0; i < bookingList.size(); i++)
    {
      write.println("<booking>");
      write.println("<room>" + bookingList.getBooking(i).getBookedRoom().getRoomType() + "</room>");
      write.println("<arrival>" + bookingList.getBooking(i).getDateInterval().getArrivalDate() + "</arrival>");
      write.println("<departure>" + bookingList.getBooking(i).getDateInterval().getDepartureDate()+ "</departure>");
      write.println("</booking>");
    }
    write.println("</bookings>");
    write.close();
    System.out.println("File is created");
  }


}
