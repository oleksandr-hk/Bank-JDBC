package com.solvd.bank.xml;

import com.solvd.bank.entity.branch_with_fixed_relationship.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {


   public   static List<Bank> parseXML() throws XMLStreamException, FileNotFoundException {

        List<Bank> banks = new ArrayList<>();
        Bank bank = null;
        Customer customer = new Customer();
        List<Customer> customers = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {

            String fileName = "src/main/resources/bank.xml";

            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while(xmlEventReader.hasNext()) {

                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement = xmlEvent.asStartElement();

                    if(startElement.getName().getLocalPart().equals("Bank")){

                        bank = new Bank();
                        Address address = new Address();
                        City city = new City();
                        Country country = new Country();


                        bank.setAddress(address);
                        bank.getAddress().setCity(city);
                        bank.getAddress().getCity().setCountry(country);


                    }
                    //set the other varibles from xml elements
                    else if(startElement.getName().getLocalPart().equals("id")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.setId((long) Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("address")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.setName(xmlEvent.asCharacters().getData());
                    }else if(startElement.getName().getLocalPart().equals("license")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.setLicense((long) Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("idCity")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.getAddress().setId((long) Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                    else if(startElement.getName().getLocalPart().equals("nameCity")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.getAddress().getCity().setName(xmlEvent.asCharacters().getData());
                    }
                    else if(startElement.getName().getLocalPart().equals("idCountry")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.getAddress().getCity().getCountry().setId((long) Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("nameCountry")){
                        xmlEvent = xmlEventReader.nextEvent();
                        bank.getAddress().getCity().getCountry().setName(xmlEvent.asCharacters().getData());
                    }else if(startElement.getName().getLocalPart().equals("idCustomer")){
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setId((long) Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("name")){
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setName((xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("surname")){
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setSurname((xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("phone")){
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setPhone((xmlEvent.asCharacters().getData()));
                    }else if(startElement.getName().getLocalPart().equals("email")){
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setEmail((xmlEvent.asCharacters().getData()));
                    }

                //if Employee end element is reached, add employee object to list
                if(xmlEvent.isEndElement()){
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("Bank")){
                        banks.add(bank);
                        bank.getCustomers().add(customer);
                    }
                }
            }}

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return banks;
    }
}
