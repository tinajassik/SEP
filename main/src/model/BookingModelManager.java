package model;
import utilis.MyFileHandler;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.time.LocalDateTime;
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
        if (temp[i] instanceof GuestList)
          getAllGuests().addGuest((Guest) temp[i]);
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

  // Method that returns a booking list with the expected arrivals
  public BookingList getExpectedArrivals()
  {
    BookingList allBookings = getAllBookings();
    BookingList expectedArrivals = new BookingList();
    LocalDate currentDate = LocalDate.now();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Booking booking = allBookings.getBooking(i);

      if(booking.getDateInterval().getArrivalDate().equals(currentDate))
      {
        expectedArrivals.addBooking(booking);
      }
    }
    return expectedArrivals;
  }

  // Method that returns a booking list with the expected departures
  public BookingList getExpectedDepartures()
  {
    BookingList allBookings = getAllBookings();
    BookingList expectedDepartures = new BookingList();
    LocalDate currentDate = LocalDate.now();

    for (int i = 0; i < allBookings.size() ; i++)
  {
    Booking booking = allBookings.getBooking(i);

    if(booking.getDateInterval().getDepartureDate().equals(currentDate))
    {
      expectedDepartures.addBooking(booking);
    }
  }
    return expectedDepartures;
  }

  //Method that creates a new booking
  public Booking createBooking(Guest guest, Room room, DateInterval dates)
  {
    BookingList allBookings = getAllBookings();
    RoomList availableRooms = getAvailableRoomsForASpecificPeriod(dates);
    Booking booking = null;

    for (int i = 0; i < availableRooms.size(); i++)
    {
      if(availableRooms.getRoom(i).equals(room))
      {
        GuestList allGuests = new GuestList();

         booking = new Booking(allGuests, room, dates);
        allGuests.addGuest(guest);
        allBookings.addBooking(booking);
      }
    }
    return booking;
  }

  //Method that search a booking in the system
  public ArrayList searchBooking(String firstName, String lastName)
  {
    BookingList allBookings = getAllBookings();

    if(allBookings.getBookingsByFullName(firstName, lastName).size()!=0)

      return allBookings.getBookingsByFullName(firstName, lastName);

     else return null;
  }

  //Method that removes a booking from the system
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
      allBookings.deleteBooking(booking);
    }

  }

  // Use the MyFileHandler class to save all Bookings in the BookingList object
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

  //Method that returns a room list will all available rooms from a specific period
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

  //Method that returns a room list will all available rooms of just one type from a specific period
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

  //Method that calculates and returns the price of the booking
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

  //Method that returns the price with the discount if the guest is not happy about the facilities of the hotel
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

/* checkIn() adds new guests to the booking if necesarry and changes the booking status to checked in */
  public void checkIn(Booking booking, Guest newGuest)
  {
    booking.getGuests().addGuest(newGuest);
    booking.checkedIn();
  }

  /* deleteAfter6() method deletes booking if the guest have not checked in nor notified the hotel about a late check in */

  public void deleteAfter6()
  {
    if(LocalDateTime.now().getHour() >= 18)
    {
      for (int i = 0; i < getAllBookings().size();i++)
      {
        if(!(getAllBookings().getBooking(i).isCheckIn()) && !(getAllBookings().getBooking(i).isLateCheckIn()))
        {
          getAllBookings().deleteBooking(getAllBookings().getBooking(i));
        }
      }
    }
  }
/* lateCheckIn() method is called when a guest notifies the hotel that they will not check in until after 6 p.m. */
  public void lateCheckIn(Booking booking)
  {
    booking.willCheckInLate();
  }

/* checkOut() method is called during check out to calculate the price and delete the booking from the system*/
  public void checkOut(Booking booking)
  {
    getPrice(booking);
    getAllBookings().deleteBooking(booking);
  }






}
