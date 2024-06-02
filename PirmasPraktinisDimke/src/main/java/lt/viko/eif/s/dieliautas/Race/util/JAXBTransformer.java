package lt.viko.eif.s.dieliautas.Race.util;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Klasė, skirta objektų konvertavimui į XML ir atgal į objektus naudojant JAXB.
 */
public class JAXBTransformer {

    /**
     * Konvertuoja objektą į XML formatą.
     *
     * @param object Objektas, kurį reikia konvertuoti į XML.
     * @param <T> Objekto tipas.
     * @return Objekto XML formato eilutė.
     * @throws JAXBException Jei konvertavimo metu įvyksta klaida.
     */
    public static <T> String transformToXML(T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    /**
     * Konvertuoja XML formatą į objektą.
     *
     * @param xml XML formato eilutė.
     * @param clazz Objekto klasė.
     * @param <T> Objekto tipas.
     * @return XML formatą atitinkantis objektas.
     * @throws JAXBException Jei konvertavimo metu įvyksta klaida.
     */
    public static <T> T transformToPOJO(String xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<T> root = unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), clazz);
        return root.getValue();
    }
}
