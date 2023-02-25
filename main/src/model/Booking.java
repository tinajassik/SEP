package model;

import java.io.Serializable;

/**
 * A class containing all information about a Booking object.
 *
 * @author Andreea Asimine, Aleksandra Adamczak
 * @version 1.2
 */
public class Booking implements Serializable {
    //creating fields/attributes for class Booking
    private GuestList guests;
    private Room room;
    private DateInterval dates;
    private boolean checkIn;
    private boolean lateCheckIn;
    private boolean extraBed;

    /**
     * Three-argument constructor.
     *
     * @param guests the guest list for a particular room
     * @param room   the booked room
     * @param dates  the dates of arrival and departure
     */
    public Booking(GuestList guests, Room room, DateInterval dates) {
        this.guests = guests;
        this.room = room;
        this.dates = dates;
        checkIn = false;
        extraBed = false;
        lateCheckIn = false;
    }

    /**
     * Returns the first guest of the booking.
     *
     * @return the first person from the ArrayList(the person that made the booking)
     */
    public Guest getBookingGuest() {
        return guests.getFirstGuest();
    }

    /**
     * Returns true if extra bed was desired or false if it is not.
     *
     * @return boolean true if extra bed is set or false it is not
     */
    public boolean hasExtraBed() {
        return extraBed;
    }

    /**
     * Returns all the guests from the booking.
     *
     * @return the list of guests.txt from the booking
     */
    public GuestList getGuests() {
        return guests;
    }

    /**
     * Returns arrival and departure dates.
     *
     * @return the date interval of the booking
     */
    public DateInterval getDateInterval() {
        return dates;
    }


    /**
     * Sets extra bed to true if extra bed is needed.
     */
    public void addExtraBed() {
        extraBed = true;
    }

    /**
     * Returns the room from the booking.
     *
     * @return the room from the booking
     */
    public Room getBookedRoom() {
        return room;
    }


    /**
     * Sets the room to the booking.
     *
     * @param room which is being set as the room in the booking
     */
    public void setBookedRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns if the guests have a checked in or not.
     *
     * @return boolean true or false
     */
    public boolean isCheckIn() {
        return this.checkIn;
    }

    /**
     * Returns true if the guests will check in after 6 p.m.
     *
     * @return boolean true or false
     */
    public boolean isLateCheckIn() {
        return this.lateCheckIn;
    }

    /**
     * Sets extra bed option to the false if the guest does not need extra bed.
     */
    public void removeExtraBed() {
        extraBed = false;
    }

    /**
     * method is run when the guest checks in
     */
    public void checkedIn() {
        checkIn = true;
    }

    /**
     * Sets lateCheckIn to true.
     */
    public void willCheckInLate() {
        this.lateCheckIn = true;
    }

    /**
     * Sets lateCheckIn to false.
     */
    public void willNotCheckInLate() {
        lateCheckIn = false;
    }

    /**
     * Returns a String representation of the Booking
     *
     * @return a String containing information about Booking Objects
     */
    public String toString() {
        String status = "";
        String bed = "";
        String late = "";
        if (checkIn) status = "Checked In";
        else status = "NOT Checked In";

        if (extraBed) bed = " Yes";
        else bed = " No";

        if (lateCheckIn) late = "Will Check-In after 18:00";


        return "Guest: " + getBookingGuest().getFirstName() + " " + getBookingGuest().getLastName() +
                "\n" + getBookedRoom() + "\n" + getDateInterval() + "\n" + "Status: " + status + "\n"
                + "Extra bed: " + bed + "\n" + late;
    }

    /**
     * Compares object to the booking object.
     *
     * @param obj the object that is being compared
     * @return if the compared objects are the instance of Booking class and containing same information then returns true and if not it returns false
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Booking)) //check if obj is the same type
        {
            return false;
        }
        Booking other = (Booking) obj; //cast obj to this type

        //check if the fields are the same
        return guests.equals(other.guests) && room.equals(other.room)
                && dates.equals(other.dates);
    }
}
