
public abstract class Person {
protected String name;
protected String surname;
protected String pesel;
protected String telephone;
protected String email;
protected Adress adress;
public Person(String name, String surname, String pesel, String telephone, String email, String street, String city, String postalCode,String country) {
	super();
	this.name = name;
	this.surname = surname;
	this.pesel = pesel;
	this.telephone = telephone;
	this.email = email;
	this.adress = new Adress(street,city,postalCode,country);
}
public String getName() {
	return name;
}
public String getSurname() {
	return surname;
}
public String getPesel() {
	return pesel;
}
public String getTelephone() {
	return telephone;
}
public String getEmail() {
	return email;
}
public Adress getAdress() {
	return adress;
}






	
}
