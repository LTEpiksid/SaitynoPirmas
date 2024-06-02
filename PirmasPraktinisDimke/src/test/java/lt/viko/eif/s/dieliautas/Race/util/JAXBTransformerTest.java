package lt.viko.eif.s.dieliautas.Race.util;

import lt.viko.eif.s.dieliautas.Race.model.Race;
import lt.viko.eif.s.dieliautas.Race.model.Racer;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testinė klasė JAXBTransformer.
 * Šioje klasėje testuojami XML į POJO ir POJO į XML transformacijos metodai.
 */
public class JAXBTransformerTest {

    /**
     * Testuoja JAXBTransformer transformToXML metodą.
     * Patikrina, ar sėkmingai atliekama Race objekto transformacija į XML.
     */
    @Test
    public void testTransformToXML() throws JAXBException {
        Race race = new Race();
        race.setId(1);
        race.setName("Grand Prix");
        race.setLocation("Monaco");
        race.setDate("2023-05-28");

        List<Racer> racers = new ArrayList<>();
        Racer racer = new Racer();
        racer.setId(1);
        racer.setFirstName("John");
        racer.setLastName("Doe");
        racer.setPhoneNumber("1234567890");
        racer.setStatusId(1);
        racers.add(racer);
        race.setRacers(racers);

        String xml = JAXBTransformer.transformToXML(race);
        assertNotNull(xml);
        System.out.println(xml);
    }

    /**
     * Testuoja JAXBTransformer transformToPOJO metodą.
     * Patikrina, ar sėkmingai atliekama XML transformacija į Race objektą.
     */
    @Test
    public void testTransformToPOJO() throws JAXBException {
        String xml = "<race><id>1</id><name>Grand Prix</name><location>Monaco</location><date>2023-05-28</date><racers><racer><id>1</id><firstName>John</firstName><lastName>Doe</lastName><phoneNumber>1234567890</phoneNumber><statusId>1</statusId></racer></racers></race>";
        Race race = JAXBTransformer.transformToPOJO(xml, Race.class);
        assertNotNull(race);
        assertEquals(1, race.getId());
        assertEquals("Grand Prix", race.getName());
        assertEquals("Monaco", race.getLocation());
        assertEquals("2023-05-28", race.getDate());
        assertNotNull(race.getRacers());
        assertEquals(1, race.getRacers().size());
    }
}
