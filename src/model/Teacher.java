package model;

/**
 * Klasse voor het aanmaken van een leraar gebruiker
 * @author wesley
 */
public class Teacher extends User {
    
    /**
     * Constructor
     * @param firstName         voornaam leraar
     * @param lastName          achternaam leraar
     * @param userName          username leraar
     * @param password          wachtwoord leraar
     */
    public Teacher(String firstName, String lastName, String userName, String password){
        super(firstName, lastName, userName, password);
    }
}