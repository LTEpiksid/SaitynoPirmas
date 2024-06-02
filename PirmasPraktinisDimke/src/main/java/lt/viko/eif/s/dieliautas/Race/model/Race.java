package lt.viko.eif.s.dieliautas.Race.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Klasė, atitinkanti lenktynių duomenis.
 */
@XmlRootElement
public class Race {
    private int id;
    private String name;
    private String location;
    private String date;
    private List<Racer> racers;

    /**
     * Grąžina lenktynių ID.
     * @return lenktynių ID
     */
    @XmlElement
    public int getId() {
        return id;
    }

    /**
     * Nustato lenktynių ID.
     * @param id lenktynių ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Grąžina lenktynių pavadinimą.
     * @return lenktynių pavadinimas
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * Nustato lenktynių pavadinimą.
     * @param name lenktynių pavadinimas
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Grąžina lenktynių vietą.
     * @return lenktynių vieta
     */
    @XmlElement
    public String getLocation() {
        return location;
    }

    /**
     * Nustato lenktynių vietą.
     * @param location lenktynių vieta
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Grąžina lenktynių datą.
     * @return lenktynių data
     */
    @XmlElement
    public String getDate() {
        return date;
    }

    /**
     * Nustato lenktynių datą.
     * @param date lenktynių data
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Grąžina sąrašą lenktynininkų, dalyvaujančių lenktynėse.
     * @return sąrašas lenktynininkų
     */
    @XmlElement
    public List<Racer> getRacers() {
        return racers;
    }

    /**
     * Nustato sąrašą lenktynininkų, dalyvaujančių lenktynėse.
     * @param racers sąrašas lenktynininkų
     */
    public void setRacers(List<Racer> racers) {
        this.racers = racers;
    }
}
