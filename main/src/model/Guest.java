package model;

public class Guest
{
  //creating fields/attributes for class Guest
  private String firstName;
  private String lastName;
  private String adress;
  private String phoneNumber;
  private String nationality;
  private Date birthday;

  //3 arguments constructor
  public Guest(String firstName, String lastName, String adress, String phoneNumber, String nationality, Date birthday)
  {
    this.firstName=firstName;
    this.lastName=lastName;
    this.adress=adress;
    this.phoneNumber=phoneNumber;
    this.nationality=nationality;
    this.birthday=birthday;
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
    this.adress=adress;
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
    this.birthday=birthday;
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
    return adress;
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
    return birthday;
  }

  //toString() method used to convert string objects into a string
  public String toString()
  {
    return "First name: " + firstName +
        "/n" + "Last name: " + lastName +
        "/n" + "Adress: " + adress +
        "/n" + "Phone number: " + phoneNumber +
        "/n" + "Nationality: " + nationality +
        "/n" + "Birthday: " + birthday;
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
        adress.equals(other.adress) &&
        phoneNumber.equals(other.phoneNumber) &&
        nationality.equals(other.nationality) &&
        birthday.equals(other.birthday);
  }
}
