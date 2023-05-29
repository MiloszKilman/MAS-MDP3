import java.io.Serializable;
import java.util.Optional;

public class Addresses implements Serializable {
    private String city;
    private String Street;
    private int numberStreet;
    //opcjonalny
    private int apartmentNumber;
    private String postCode;



    public Addresses(String city, String street, int numberStreet, String postCode) {
        this.city = city;
        Street = street;
        this.numberStreet = numberStreet;
        this.postCode = postCode;
    }
//
//    public Addresses(String city, String street, int numberStreet, @Nullable int apartmentNumber, String postCode) {
//        this.city = city;
//        Street = street;
//        this.numberStreet = numberStreet;
//        this.apartmentNumber = apartmentNumber;
//        this.postCode = postCode;
//    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "city='" + city + '\'' +
                ", Street='" + Street + '\'' +
                ", numberStreet=" + numberStreet +
                ", apartmentNumber=" + (apartmentNumber == 0 ? " (brak numeru mieszkania) " : apartmentNumber)  +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
