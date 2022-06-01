package test;

import model.*;
import utilis.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LoadInitialData
{

  public static void main(String[] args)
  {
    ArrayList<Object> initialData= new ArrayList<>();
    RoomList allRooms = new RoomList();
    BookingList bookings = new BookingList();
    GuestList trial = new GuestList();
    ArrayList<DateInterval> dates = new ArrayList<>();
    ArrayList<GuestList> allGuests = new ArrayList<>();


    String[] roomsArray = null;
    String[] guestsArray = null;
    String[] dateIntervals = null;

    try
    {
      roomsArray = MyFileHandler.readArrayFromTextFile("main/rooms.txt");
      guestsArray = MyFileHandler.readArrayFromTextFile("main/guests.txt");
      dateIntervals = MyFileHandler.readArrayFromTextFile("main/dateintervals.txt");

      for (int i = 0; i < roomsArray.length; i++)
      {
        String tempRoom = roomsArray[i];
        String[] temp = tempRoom.split(",");
        String roomNumber = temp[0];
        String roomType = temp[1];
        Double price = Double.parseDouble(temp[2]);

        allRooms.addRoom(new Room(roomNumber, roomType, price));
      }

      for (int i = 0; i < guestsArray.length; i++) {
        String tempGuest = guestsArray[i];
        String[] temp = tempGuest.split(",");

        String firstName = temp[0];
        String lastName = temp[1];
        String address = temp[2];
        String nationality = temp[3];
        String phoneNumber = temp[4];
        int day = Integer.parseInt(temp[5]);
        int month = Integer.parseInt(temp[6]);
        int year = Integer.parseInt(temp[7]);
        Date birthday = new Date(day, month, year);
        GuestList guests = new GuestList();
        guests.addGuest(new Guest(firstName,lastName,address,phoneNumber,nationality,birthday));
        allGuests.add(guests);

      }

      for (int i = 0; i < dateIntervals.length; i++)
    {
      String tempDates= dateIntervals[i];
      String[] temp = tempDates.split(",");
      int day1 = Integer.parseInt(temp[0]);
      int month1 = Integer.parseInt(temp[1]);
      int year1 = Integer.parseInt(temp[2]);
      int day2 = Integer.parseInt(temp[3]);
      int month2 = Integer.parseInt(temp[4]);
      int year2 = Integer.parseInt(temp[5]);

      Date arrivalDate = new Date(day1, month1, year1);
      Date departureDate = new Date(day2, month2, year2);

      dates.add(new DateInterval(arrivalDate,departureDate));
    }

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }



    for (int i = 0; i < allGuests.size(); i++) {
      if (i < dates.size())
      bookings.addBooking(new Booking(allGuests.get(i),allRooms.getRoom(i),dates.get(i)));
    }

    initialData.add(allRooms);
    initialData.add(bookings);
    initialData.add(trial);


    try
    {
      MyFileHandler.writeArrayToBinaryFile("data.bin", initialData.toArray(new Object[0]));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }

    System.out.println("Done");
  }
}

