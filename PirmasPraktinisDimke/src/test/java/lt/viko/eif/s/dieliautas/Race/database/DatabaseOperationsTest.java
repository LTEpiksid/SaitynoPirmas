package lt.viko.eif.s.dieliautas.Race.database;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testinė klasė DatabaseOperations.
 * Šioje klasėje testuojami metodai, susiję su duomenų bazių operacijomis.
 */
public class DatabaseOperationsTest {

    /**
     * Testuoja DatabaseOperations getConnection metodą.
     * Patikrina, ar sėkmingai užmezgamas ryšys su duomenų baze.
     */
    @Test
    public void testGetConnection() throws SQLException {
        assertNotNull(DatabaseOperations.getConnection());
    }

    /**
     * Testuoja DatabaseOperations viewAllRaces metodą.
     * Patikrina, ar grąžinamas rezultatų sąrašas nėra tuščias.
     */
    @Test
    public void testViewAllRaces() {
        String result = DatabaseOperations.viewAllRaces();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    /**
     * Testuoja DatabaseOperations viewAllRaceInfo metodą.
     * Patikrina, ar grąžinamas rezultatų sąrašas nėra tuščias.
     */
    @Test
    public void testViewAllRaceInfo() {
        String result = DatabaseOperations.viewAllRaceInfo();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    /**
     * Testuoja DatabaseOperations viewAllRacers metodą.
     * Patikrina, ar grąžinamas rezultatų sąrašas nėra tuščias.
     */
    @Test
    public void testViewAllRacers() {
        String result = DatabaseOperations.viewAllRacers();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    /**
     * Testuoja DatabaseOperations viewEntireDatabase metodą.
     * Patikrina, ar grąžinamas rezultatų sąrašas nėra tuščias.
     */
    @Test
    public void testViewEntireDatabase() {
        String result = DatabaseOperations.viewEntireDatabase();
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }
}
