
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

 SAX parser use callback function  to notify client object of the XML document structure.
 You should extend DefaultHandler and override the method when parsin the XML document
 ***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    SmartDoorbell doorbell;
    SmartDoorlock doorlock;
    SmartSpeaker speaker;
    SmartLighting light;
    SmartThermostat thermostat;
    Accessory accessory;
    HashMap<String, Object> products;
    static HashMap<String, SmartDoorbell> doorbells;
    static HashMap<String, SmartDoorlock> doorlocks;
    static HashMap<String, SmartSpeaker> speakers;
    static HashMap<String, SmartLighting> lights;
    static HashMap<String, SmartThermostat> thermostats;

    static HashMap<String, Accessory> accessories;
    int totalSize;
    String smartXmlFileName;
    HashMap<String, String> accessoryHashMap;
    String elementValueRead;
    String currentElement = "";

    public SaxParserDataStore() {
    }

    public SaxParserDataStore(String smartXmlFileName) {
        this.smartXmlFileName = smartXmlFileName;
        doorbells = new HashMap<String, SmartDoorbell>();
        doorlocks = new HashMap<String, SmartDoorlock>();
        speakers = new HashMap<String, SmartSpeaker>();
        lights = new HashMap<String, SmartLighting>();
        thermostats = new HashMap<String, SmartThermostat>();
        accessories = new HashMap<String, Accessory>();
        accessoryHashMap = new HashMap<String, String>();
        parseDocument();
    }

    //parse the xml using sax parser to get the data
    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(smartXmlFileName, this);

        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


////////////////////////////////////////////////////////////

    /*************

     There are a number of methods to override in SAX handler  when parsing your XML document :

     Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document.
     Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.
     Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


     There are few other methods that you could use for notification for different purposes, check the API at the following URL:

     https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html
     ***************/

////////////////////////////////////////////////////////////

    // when xml start element is parsed store the id into respective hashmap for doorbell,games etc
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("doorbell")) {
            currentElement = "doorbell";
            doorbell = new SmartDoorbell();
            doorbell.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("doorlock")) {
            currentElement = "doorlock";
            doorlock = new SmartDoorlock();
            doorlock.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("light")) {
            currentElement = "light";
            light = new SmartLighting();
            light.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("speaker")) {
            currentElement = "speaker";
            speaker = new SmartSpeaker();
            speaker.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("thermostat")) {
            currentElement = "thermostat";
            thermostat = new SmartThermostat();
            thermostat.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") && !currentElement.equals("doorbell")) {
            currentElement = "accessory";
            accessory = new Accessory();
            accessory.setId(attributes.getValue("id"));
        }
    }

    // when xml end element is parsed store the data into respective hashmap for doorbell,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {

        if (element.equals("doorbell")) {
            doorbells.put(doorbell.getId(), doorbell);
            return;
        }

        if (element.equals("doorlock")) {
            doorlocks.put(doorlock.getId(), doorlock);
            return;
        }
        if (element.equals("light")) {
            lights.put(light.getId(), light);
            return;
        }
        if (element.equals("speaker")) {
            speakers.put(speaker.getId(), speaker);
            return;
        }
        if (element.equals("thermostat")) {
            thermostats.put(thermostat.getId(), thermostat);
            return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
            accessories.put(accessory.getId(), accessory);
            return;
        }
        if (element.equals("accessory") && currentElement.equals("doorbell")) {
            accessoryHashMap.put(elementValueRead, elementValueRead);
        }
        if (element.equalsIgnoreCase("accessories") && currentElement.equals("doorbell")) {
            doorbell.setAccessories(accessoryHashMap);
            accessoryHashMap = new HashMap<String, String>();
            return;
        }
        if (element.equalsIgnoreCase("image")) {
            if (currentElement.equals("doorbell"))
                doorbell.setImage(elementValueRead);
            if (currentElement.equals("light"))
                light.setImage(elementValueRead);
            if (currentElement.equals("doorlock"))
                doorlock.setImage(elementValueRead);
            if (currentElement.equals("speaker"))
                speaker.setImage(elementValueRead);
            if (currentElement.equals("thermostat"))
                thermostat.setImage(elementValueRead);
            if (currentElement.equals("accessory"))
                accessory.setImage(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("discount")) {
            if (currentElement.equals("doorbell"))
                doorbell.setDiscount(Double.parseDouble(elementValueRead));
            if (currentElement.equals("light"))
                light.setDiscount(Double.parseDouble(elementValueRead));
            if (currentElement.equals("doorlock"))
                doorlock.setDiscount(Double.parseDouble(elementValueRead));
            if (currentElement.equals("speaker"))
                speaker.setDiscount(Double.parseDouble(elementValueRead));
            if (currentElement.equals("thermostat"))
                thermostat.setDiscount(Double.parseDouble(elementValueRead));
            if (currentElement.equals("accessory"))
                accessory.setDiscount(Double.parseDouble(elementValueRead));
            return;
        }

        if (element.equalsIgnoreCase("condition")) {
            if (currentElement.equals("doorbell"))
                doorbell.setCondition(elementValueRead);
            if (currentElement.equals("light"))
				light.setCondition(elementValueRead);
            if (currentElement.equals("doorlock"))
                doorlock.setCondition(elementValueRead);
			if (currentElement.equals("speaker"))
				speaker.setCondition(elementValueRead);
			if (currentElement.equals("thermostat"))
				thermostat.setCondition(elementValueRead);
            if (currentElement.equals("accessory"))
                accessory.setCondition(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("manufacturer")) {
            if (currentElement.equals("doorbell"))
                doorbell.setRetailer(elementValueRead);
            if (currentElement.equals("light"))
                light.setRetailer(elementValueRead);
            if (currentElement.equals("doorlock"))
                doorlock.setRetailer(elementValueRead);
			if (currentElement.equals("speaker"))
				speaker.setRetailer(elementValueRead);
			if (currentElement.equals("thermostat"))
				thermostat.setRetailer(elementValueRead);
            if (currentElement.equals("accessory"))
                accessory.setRetailer(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("name")) {
            if (currentElement.equals("doorbell"))
                doorbell.setName(elementValueRead);
            if (currentElement.equals("light"))
				light.setName(elementValueRead);
            if (currentElement.equals("doorlock"))
                doorlock.setName(elementValueRead);
			if (currentElement.equals("speaker"))
				speaker.setName(elementValueRead);
			if (currentElement.equals("thermostat"))
				thermostat.setName(elementValueRead);
            if (currentElement.equals("accessory"))
                accessory.setName(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("price")) {
            if (currentElement.equals("doorbell"))
                doorbell.setPrice(Double.parseDouble(elementValueRead));
            if (currentElement.equals("light"))
				light.setPrice(Double.parseDouble(elementValueRead));
            if (currentElement.equals("doorlock"))
                doorlock.setPrice(Double.parseDouble(elementValueRead));
			if (currentElement.equals("speaker"))
				speaker.setPrice(Double.parseDouble(elementValueRead));
			if (currentElement.equals("thermostat"))
				thermostat.setPrice(Double.parseDouble(elementValueRead));
            if (currentElement.equals("accessory"))
                accessory.setPrice(Double.parseDouble(elementValueRead));
            return;
        }

        if (element.equalsIgnoreCase("description")) {
            if (currentElement.equals("doorbell"))
                doorbell.setDescription(elementValueRead);
            if (currentElement.equals("light"))
                light.setDescription(elementValueRead);
            if (currentElement.equals("doorlock"))
                doorlock.setDescription(elementValueRead);
            if (currentElement.equals("speaker"))
                speaker.setDescription(elementValueRead);
            if (currentElement.equals("thermostat"))
                thermostat.setDescription(elementValueRead);
            if (currentElement.equals("accessory"))
                accessory.setDescription(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("quantity")) {
            if (currentElement.equals("doorbell"))
                doorbell.setQuantity(Integer.parseInt(elementValueRead));
            if (currentElement.equals("light"))
                light.setQuantity(Integer.parseInt(elementValueRead));
            if (currentElement.equals("doorlock"))
                doorlock.setQuantity(Integer.parseInt(elementValueRead));
            if (currentElement.equals("speaker"))
                speaker.setQuantity(Integer.parseInt(elementValueRead));
            if (currentElement.equals("thermostat"))
                thermostat.setQuantity(Integer.parseInt(elementValueRead));
            if (currentElement.equals("accessory"))
                accessory.setQuantity(Integer.parseInt(elementValueRead));
            return;
        }
    }

    //get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////

    //call the constructor to parse the xml and get product details
    public static void addHashmap() {
        String TOMCAT_HOME = System.getProperty("catalina.home");
        new SaxParserDataStore(TOMCAT_HOME + "/webapps/Assignment_3_Chandru_Pramoda/ProductCatalog.xml");
    }
}