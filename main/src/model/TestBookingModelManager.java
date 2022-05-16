package model;

public class TestBookingModelManager
{
  public static void main(String[] args)
  {
   BookingModelManager smm = new BookingModelManager("bookings.bin");

    // Get all bookings from the file and print them out
    BookingList list = smm.getAllBookings();
    System.out.println("All bookings:");
    System.out.println("------------------------------------------------------------");
    System.out.println(list);

    // Get all guests from the file and print them out
    GuestList list1 = smm.getAllGuests();
    System.out.println("All guests:");
    System.out.println("------------------------------------------------------------");
    System.out.println(list1);

    // Get all rooms from the file and print them out
    RoomList list2 = smm.getAllRooms();
    System.out.println("All rooms:");
    System.out.println("------------------------------------------------------------");
    System.out.println(list2);

    // Get all expected arrivals from the file and print them out
    BookingList list3 = smm.getExpectedArrivals();
    System.out.println("All expected arrivals:");
    System.out.println("------------------------------------------------------------");
    System.out.println(list3);

   // Get all bookings from the file and print them out
   BookingList list4 = smm.getExpectedDepartures();
   System.out.println("All expected departures:");
   System.out.println("------------------------------------------------------------");
   System.out.println(list4);

   //creating a new booking
   Date birthday = new Date(5,3,2002);
   Guest guest = new Guest("Tina", "Jasova", "Vesterbro", "01559456", "Romanian", birthday);
   Room room = new Room("2", "Double bedroom", 169);
   Date arrivalDate = new Date(12, 5, 2022);
   Date departureDate = new Date(20, 5, 2022);
   DateInterval dates = new DateInterval(arrivalDate, departureDate);
   System.out.println("New booking:");
   System.out.println("------------------------------------------------------------");
   System.out.println(smm.createBooking(guest, room, dates ));

   //creating a new booking
   Date birthday0 = new Date(5,3,2002);
   Guest guest0 = new Guest("Kevin", "Kluka", "Vesterbro", "01559456", "Romanian", birthday);
   Room room0 = new Room("2", "Double bedroom", 169);
   Date arrivalDate0 = new Date(2, 5, 2022);
   Date departureDate0 = new Date(12, 5, 2022);
   DateInterval dates0 = new DateInterval(arrivalDate, departureDate);
   smm.createBooking(guest0, room0, dates0 );
   System.out.println("New booking:");
   System.out.println("------------------------------------------------------------");
   System.out.println(smm.createBooking(guest0, room0, dates0 ));

   //creating a new booking
   Date birthday1 = new Date(5,3,2002);
   Guest guest1 = new Guest("Aleksandra", "Adamzak", "Vesterbro", "01559456", "Romanian", birthday);
   Room room1 = new Room("2", "Double bedroom", 169);
   Date arrivalDate1 = new Date(22, 5, 2022);
   Date departureDate1 = new Date(30, 5, 2022);
   DateInterval dates1 = new DateInterval(arrivalDate, departureDate);
   smm.createBooking(guest1, room1, dates1 );
   System.out.println("New booking:");
   System.out.println("------------------------------------------------------------");
   System.out.println(smm.createBooking(guest1, room1, dates1 ));

   for (int i = 0; i < smm.searchBooking("Andreea", "Asimine").size(); i++)
   {
    System.out.println(smm.searchBooking("Andreea", "Asimine").get(i));
    System.out.println(smm.getPrice((Booking) smm.searchBooking("Andreea", "Asimine").get(i)));
   }






  }
}
