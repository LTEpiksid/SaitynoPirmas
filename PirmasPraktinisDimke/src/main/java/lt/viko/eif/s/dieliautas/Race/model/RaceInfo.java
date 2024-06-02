package lt.viko.eif.s.dieliautas.Race.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasė, reprezentuojanti lenktynių informaciją.
 */
@XmlRootElement
public class RaceInfo {
    private int id;
    private int raceId;
    private int racerId;
    private String finishTime;
    private int positionNumber;

    /**
     * Grąžina lenktynių informacijos ID.
     * @return lenktynių informacijos ID
     */
    @XmlElement
    public int getId() {
        return id;
    }

    /**
     * Nustato lenktynių informacijos ID.
     * @param id lenktynių informacijos ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Grąžina lenktynių ID.
     * @return lenktynių ID
     */
    @XmlElement
    public int getRaceId() {
        return raceId;
    }

    /**
     * Nustato lenktynių ID.
     * @param raceId lenktynių ID
     */
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    /**
     * Grąžina lenktynininko ID.
     * @return lenktynininko ID
     */
    @XmlElement
    public int getRacerId() {
        return racerId;
    }

    /**
     * Nustato lenktynininko ID.
     * @param racerId lenktynininko ID
     */
    public void setRacerId(int racerId) {
        this.racerId = racerId;
    }

    /**
     * Grąžina finišo laiką.
     * @return finišo laikas
     */
    @XmlElement
    public String getFinishTime() {
        return finishTime;
    }

    /**
     * Nustato finišo laiką.
     * @param finishTime finišo laikas
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * Grąžina pozicijos numerį.
     * @return pozicijos numeris
     */
    @XmlElement
    public int getPositionNumber() {
        return positionNumber;
    }

    /**
     * Nustato pozicijos numerį.
     * @param positionNumber pozicijos numeris
     */
    public void setPositionNumber(int positionNumber) {
        this.positionNumber = positionNumber;
    }
}
