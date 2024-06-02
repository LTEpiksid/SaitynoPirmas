package lt.viko.eif.s.dieliautas.Race.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasė, reprezentuojanti lenktynių duomenų bazę.
 */
@XmlRootElement
public class RaceDatabase {
    private List<Race> races = new ArrayList<>();
    private List<Racer> racers = new ArrayList<>();
    private List<RaceInfo> raceInfos = new ArrayList<>();
    private List<Status> statuses = new ArrayList<>();

    /**
     * Grąžina visų lenktynių sąrašą.
     * @return visų lenktynių sąrašas
     */
    @XmlElement
    public List<Race> getRaces() {
        return races;
    }

    /**
     * Nustato visų lenktynių sąrašą.
     * @param races visų lenktynių sąrašas
     */
    public void setRaces(List<Race> races) {
        this.races = races;
    }

    /**
     * Grąžina visų lenktynininkų sąrašą.
     * @return visų lenktynininkų sąrašas
     */
    @XmlElement
    public List<Racer> getRacers() {
        return racers;
    }

    /**
     * Nustato visų lenktynininkų sąrašą.
     * @param racers visų lenktynininkų sąrašas
     */
    public void setRacers(List<Racer> racers) {
        this.racers = racers;
    }

    /**
     * Grąžina visą lenktynių informaciją.
     * @return visos lenktynių informacijos sąrašas
     */
    @XmlElement
    public List<RaceInfo> getRaceInfos() {
        return raceInfos;
    }

    /**
     * Nustato visą lenktynių informaciją.
     * @param raceInfos visos lenktynių informacijos sąrašas
     */
    public void setRaceInfos(List<RaceInfo> raceInfos) {
        this.raceInfos = raceInfos;
    }

    /**
     * Grąžina visus statusus.
     * @return visų statusų sąrašas
     */
    @XmlElement
    public List<Status> getStatuses() {
        return statuses;
    }

    /**
     * Nustato visus statusus.
     * @param statuses visų statusų sąrašas
     */
    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
