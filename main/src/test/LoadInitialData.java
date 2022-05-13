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
    GuestList guests = new GuestList();
    Guest guest = new Guest("Kristina", "Jassova", "Sneznica Slovakia",
        "0987654", "Slovak", new Date(7, 8, 2001));
    guests.addGuest(guest);
    DateInterval dates = new DateInterval(new Date(1, 1, 2023),
        new Date(6, 1, 2023));

    String[] roomsArray = null;

    try
    {
      roomsArray = MyFileHandler.readArrayFromTextFile("main/rooms.txt");

      for (int i = 0; i < roomsArray.length; i++)
      {
        String tempRoom = roomsArray[i];
        String[] temp = tempRoom.split(",");
        String roomNumber = temp[0];
        String roomType = temp[1];
        Double price = Double.parseDouble(temp[2]);

        allRooms.addRoom(new Room(roomNumber, roomType, price));
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }

    for (int i = 1; i < 10; i++)
    {
      bookings.addBooking(new Booking(guests, allRooms.getRoom(1),
          new DateInterval(new Date(1, i, 2023), new Date(5, i, 2023))));
    }

    initialData.add(allRooms);
    initialData.add(bookings);
    initialData.add(guests);

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

