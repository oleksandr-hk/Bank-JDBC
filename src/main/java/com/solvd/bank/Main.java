package com.solvd.bank;

import com.solvd.bank.entity.branch_with_fixed_relationship.Bank;
import com.solvd.bank.xml.XMLReader;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {


    public static void main(String args[]){


        try {
            List<Bank> bankList = XMLReader.parseXML();
            System.out.println(bankList.size());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }






}
