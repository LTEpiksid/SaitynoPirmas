package lt.viko.eif.s.dieliautas.Race.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasė, reprezentuojanti lenktynininką.
 */
@XmlRootElement
public class Racer {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int statusId;

    /**
     * Grąžina lenktynininko ID.
     * @return lenktynininko ID
     */
    @XmlElement
    public int getId() {
        return id;
    }

    /**
     * Nustato lenktynininko ID.
     * @param id lenktynininko ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Grąžina lenktynininko vardą.
     * @return lenktynininko vardas
     */
    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    /**
     * Nustato lenktynininko vardą.
     * @param firstName lenktynininko vardas
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Grąžina lenktynininko pavardę.
     * @return lenktynininko pavardė
     */
    @XmlElement
    public String getLastName() {
        return lastName;
    }

    /**
     * Nustato lenktynininko pavardę.
     * @param lastName lenktynininko pavardė
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Grąžina lenktynininko telefono numerį.
     * @return lenktynininko telefono numeris
     */
    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Nustato lenktynininko telefono numerį.
     * @param phoneNumber lenktynininko telefono numeris
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Grąžina lenktynininko statuso ID.
     * @return lenktynininko statuso ID
     */
    @XmlElement
    public int getStatusId() {
        return statusId;
    }

    /**
     * Nustato lenktynininko statuso ID.
     * @param statusId lenktynininko statuso ID
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
