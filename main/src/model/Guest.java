package model;
import javafx.scene.control.DatePicker;

import java.io.Serializable;

/**
 * A class representing information about guest's first and last name, address phone number, nationality and birthday
 * @author Andreea Asimine
 * @version 1.1
 */
public class Guest implements Serializable
{
  //creating fields/attributes for class Guest
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String nationality;
  private Date birthday;

  //3 arguments constructor

  /**
   * Three- argument constructor
   * @param firstName the guest's first name
   * @param lastName the guest's last name
   * @param address the guest's address
   * @param phoneNumber the guest's phone number
   * @param nationality the guest's nationality
   * @param birthday the guest's birthday
   */
  public Guest(String firstName, String lastName, String address, String phoneNumber, String nationality, Date birthday)
  {
    this.firstName=firstName;
    this.lastName=lastName;
    this.address=address;
    this.phoneNumber=phoneNumber;
    this.nationality=nationality;
    this.birthday=birthday.copy();
  }

  //setter for firstName
  public void setFirstName(String firstName)
  {
    this.firstName=firstName;
  }

  //setter for lastName
  public void setLastName(String lastName)
  {
    this.lastName=lastName;
  }

  //setter for adress
  public void setAdress(String adress)
  {
    this.address=adress;
  }

  //setter for phoneNumber
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber=phoneNumber;
  }



  //setter for nationality
  public void setNationality(String nationality)
  {
    this.nationality=nationality;
  }

  //setter for birthday
  public void setBirthday(Date birthday)
  {
    this.birthday=birthday.copy();
  }

  //getter for firstName
  public String getFirstName()
  {
    return firstName;
  }

  //getter for lastName
  public String getLastName()
  {
    return  lastName;
  }

  //getter for adress
  public String getAdress()
  {
    return address;
  }

  //getter for phoneNumber
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  //getter for nationality
  public String getNationality()
  {
    return nationality;
  }

  //getter for birthday
  public Date getBirthday()
  {
    return birthday.copy();
  }

  //toString() method used to convert string objects into a string
  public String toString()
  {
    return "First name: " + firstName +
        "\n" + "Last name: " + lastName +
        "\n" + "Adress: " + address +
        "\n" + "Phone number: " + phoneNumber +
        "\n" + "Nationality: " + nationality +
        "\n" + "Birthday: " + birthday;
  }

  //equals() method for checking if obj is the same object as Guest
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Guest)) //check if obj is the same type
    {
      return false;
    }
    Guest other = (Guest) obj; //cast obj to this type

    //check if the fields are the same
    return firstName.equals(other.firstName) &&
        lastName.equals(other.lastName) &&
        address.equals(other.address) &&
        phoneNumber.equals(other.phoneNumber) &&
        nationality.equals(other.nationality) &&
        birthday.equals(other.birthday);
  }
}
