
public class Adress {
private String street;
private String city;
private String postalCode;
private String country;

public Adress(String street, String city, String postalCode, String country) {
	super();
	this.street = street;
	this.city = city;
	this.postalCode = postalCode;
	this.country = country;
}

public void setStreet(String street) {
	this.street = street;
}

public void setCity(String city) {
	this.city = city;
}

public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}

public void setCountry(String country) {
	this.country = country;
}

public String getStreet() {
	return street;
}

public String getCity() {
	return city;
}

public String getPostalCode() {
	return postalCode;
}

public String getCountry() {
	return country;
}





}
