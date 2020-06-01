package com.alex.Services;

import com.alex.Model.DataRowsHistoryPages;
import lombok.SneakyThrows;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvancedXMLHandlerDataRowsHistoryPages extends DefaultHandler {
    private List<DataRowsHistoryPages> dataRows = new ArrayList<DataRowsHistoryPages>();

    public List<DataRowsHistoryPages> getDataRows() {
        return dataRows;
    }

    @SneakyThrows
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("row")) {

            if (attributes.getQName(0).compareTo("BOARDID") == 0 && attributes.getQName(1).compareTo("TRADEDATE") == 0 && attributes.getQName(2).compareTo("SHORTNAME") == 0 && attributes.getQName(3).compareTo("SECID") == 0 &&
                    attributes.getQName(4).compareTo("NUMTRADES") == 0 && attributes.getQName(5).compareTo("VALUE") == 0 && attributes.getQName(6).compareTo("OPEN") == 0 && attributes.getQName(7).compareTo("LOW") == 0 &&
                    attributes.getQName(8).compareTo("HIGH") == 0 && attributes.getQName(9).compareTo("LEGALCLOSEPRICE") == 0 && attributes.getQName(10).compareTo("WAPRICE") == 0 && attributes.getQName(11).compareTo("CLOSE") == 0 &&
                    attributes.getQName(12).compareTo("VOLUME") == 0 && attributes.getQName(13).compareTo("MARKETPRICE2") == 0 && attributes.getQName(14).compareTo("MARKETPRICE3") == 0 && attributes.getQName(15).compareTo("ADMITTEDQUOTE") == 0 &&
                    attributes.getQName(16).compareTo("MP2VALTRD") == 0 && attributes.getQName(17).compareTo("MARKETPRICE3TRADESVALUE") == 0 && attributes.getQName(18).compareTo("ADMITTEDVALUE") == 0 && attributes.getQName(19).compareTo("WAVAL") == 0) {

                String BOARDID = attributes.getValue("BOARDID");
                Date TRADEDATE = new SimpleDateFormat("yyyy-MM-dd").parse(attributes.getValue("TRADEDATE"));
                String SHORTNAME = attributes.getValue("SHORTNAME");
                String SECID = attributes.getValue("SECID");
                double NUMTRADES = attributes.getValue("NUMTRADES").length() != 0 ? Double.parseDouble(attributes.getValue("NUMTRADES")) : 0;
                double VALUE = attributes.getValue("VALUE").length() != 0 ? Double.parseDouble(attributes.getValue("VALUE")) : 0;
                double OPEN = attributes.getValue("OPEN").length() != 0 ? Double.parseDouble(attributes.getValue("OPEN")) : 0;
                double LOW = attributes.getValue("LOW").length() != 0 ? Double.parseDouble(attributes.getValue("LOW")) : 0;
                double HIGH = attributes.getValue("HIGH").length() != 0 ? Double.parseDouble(attributes.getValue("HIGH")) : 0;
                double LEGALCLOSEPRICE = attributes.getValue("LEGALCLOSEPRICE").length() != 0 ? Double.parseDouble(attributes.getValue("LEGALCLOSEPRICE")) : 0;
                double WAPRICE = attributes.getValue("WAPRICE").length() != 0 ? Double.parseDouble(attributes.getValue("WAPRICE")) : 0;
                double CLOSE = attributes.getValue("CLOSE").length() != 0 ? Double.parseDouble(attributes.getValue("CLOSE")) : 0;
                double VOLUME = attributes.getValue("VOLUME").length() != 0 ? Double.parseDouble(attributes.getValue("VOLUME")) : 0;
                double MARKETPRICE2 = attributes.getValue("MARKETPRICE2").length() != 0 ? Double.parseDouble(attributes.getValue("MARKETPRICE2")) : 0;
                double MARKETPRICE3 = attributes.getValue("MARKETPRICE3").length() != 0 ? Double.parseDouble(attributes.getValue("MARKETPRICE3")) : 0;
                double ADMITTEDQUOTE = attributes.getValue("ADMITTEDQUOTE").length() != 0 ? Double.parseDouble(attributes.getValue("ADMITTEDQUOTE")) : 0;
                double MP2VALTRD = attributes.getValue("MP2VALTRD").length() != 0 ? Double.parseDouble(attributes.getValue("MP2VALTRD")) : 0;
                double MARKETPRICE3TRADESVALUE = attributes.getValue("MARKETPRICE3TRADESVALUE").length() != 0 ? Double.parseDouble(attributes.getValue("MARKETPRICE3TRADESVALUE")) : 0;
                double ADMITTEDVALUE = attributes.getValue("ADMITTEDVALUE").length() != 0 ? Double.parseDouble(attributes.getValue("ADMITTEDVALUE")) : 0;
                double WAVAL = attributes.getValue("WAVAL").length() != 0 ? Double.parseDouble(attributes.getValue("WAVAL")) : 0;

                dataRows.add(new DataRowsHistoryPages(BOARDID, TRADEDATE, SHORTNAME, SECID, NUMTRADES, VALUE, OPEN, LOW, HIGH
                        , LEGALCLOSEPRICE, WAPRICE, CLOSE, VOLUME, MARKETPRICE2, MARKETPRICE3, ADMITTEDQUOTE, MP2VALTRD,
                        MARKETPRICE3TRADESVALUE, ADMITTEDVALUE, WAVAL));
            }
        }
    }
}