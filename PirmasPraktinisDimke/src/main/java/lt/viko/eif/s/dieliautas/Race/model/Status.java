package lt.viko.eif.s.dieliautas.Race.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasė, reprezentuojanti lenktynininko statusą.
 */
@XmlRootElement(name = "status")
public class Status {
    private int id;
    private String statusName;

    /**
     * Grąžina statuso ID.
     * @return statuso ID
     */
    @XmlElement
    public int getId() {
        return id;
    }

    /**
     * Nustato statuso ID.
     * @param id statuso ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Grąžina statuso pavadinimą.
     * @return statuso pavadinimas
     */
    @XmlElement
    public String getStatusName() {
        return statusName;
    }

    /**
     * Nustato statuso pavadinimą.
     * @param statusName statuso pavadinimas
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
