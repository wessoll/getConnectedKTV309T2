package model;

/**
 * Klasse voor het aanmaken van een leerling gebruiker
 * @author wesley
 */
public class Student extends User {
    
    private Continent europe;
    
    /**
     * Constructor
     * @param firstName         voornaam leerling
     * @param lastName          achternaam leerling
     * @param userName          username leerling
     * @param password          wachtwoord leerling
     */
    public Student(String firstName, String lastName, String userName, String password){
        super(firstName, lastName, userName, password);
        europe = new Continent();
    }
}