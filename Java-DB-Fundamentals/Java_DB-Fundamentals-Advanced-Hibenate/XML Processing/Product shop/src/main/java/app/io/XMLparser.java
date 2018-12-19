package app.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XMLparser {

    public <T> T fromXMLToObject(Class<T> tClass, String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return (T) unmarshaller.unmarshal(reader);
    }

    public <T> void fromObjectToXML(Class<T> tClass, T wrapperDto, String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrapperDto, new File(filePath));
    }
}
