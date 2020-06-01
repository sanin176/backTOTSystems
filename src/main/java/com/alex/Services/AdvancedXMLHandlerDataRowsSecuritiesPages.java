package com.alex.Services;

import com.alex.Model.DataRowsSecuritiesPages;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AdvancedXMLHandlerDataRowsSecuritiesPages extends DefaultHandler {
    private List<DataRowsSecuritiesPages> dataRows = new ArrayList<DataRowsSecuritiesPages>();

    private boolean beginValue = false;
    private String valueStr = null;

    public AdvancedXMLHandlerDataRowsSecuritiesPages(boolean beginValue) {
        this.beginValue = beginValue;
    }

    public AdvancedXMLHandlerDataRowsSecuritiesPages(boolean beginValue, String valueStr) {
        this.beginValue = beginValue;
        this.valueStr = valueStr;
    }

    public List<DataRowsSecuritiesPages> getDataRows() {
        return dataRows;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("row")) {

            if(attributes.getQName(0).compareTo("id") == 0 && attributes.getQName(1).compareTo("secid") == 0 && attributes.getQName(2).compareTo("shortname") == 0 && attributes.getQName(3).compareTo("regnumber") == 0 &&
                    attributes.getQName(4).compareTo("name") == 0 && attributes.getQName(5).compareTo("isin") == 0 && attributes.getQName(6).compareTo("is_traded") == 0 &&  attributes.getQName(7).compareTo("emitent_id") == 0 &&
                    attributes.getQName(8).compareTo("emitent_title") == 0 && attributes.getQName(9).compareTo("emitent_inn") == 0 && attributes.getQName(10).compareTo("emitent_okpo") == 0 && attributes.getQName(11).compareTo("gosreg") == 0 &&
                    attributes.getQName(12).compareTo("type") == 0 && attributes.getQName(13).compareTo("group") == 0 && attributes.getQName(14).compareTo("primary_boardid") == 0 && attributes.getQName(15).compareTo("marketprice_boardid") == 0){

                String secid = attributes.getValue("secid");
                if(this.beginValue == false || (this.beginValue == true && secid.compareTo(this.valueStr) == 0)) {
                    int id = attributes.getValue("id").length() != 0 ? Integer.parseInt(attributes.getValue("id")) : 0;
                    String shortname = attributes.getValue("shortname");
                    String regnumber = attributes.getValue("regnumber");
                    String name = attributes.getValue("name");
                    String isin = attributes.getValue("isin");
                    int is_traded = attributes.getValue("is_traded").length() != 0 ? Integer.parseInt(attributes.getValue("is_traded")) : 0;
                    int emitent_id = attributes.getValue("emitent_id").length() != 0 ? Integer.parseInt(attributes.getValue("emitent_id")) : 0;
                    String emitent_title = attributes.getValue("emitent_title");
                    String emitent_inn = attributes.getValue("emitent_inn");
                    String emitent_okpo = attributes.getValue("emitent_okpo");
                    String gosreg = attributes.getValue("gosreg");
                    String type = attributes.getValue("type");
                    String groupD = attributes.getValue("group");
                    String primary_boardid = attributes.getValue("primary_boardid");
                    String marketprice_boardid = attributes.getValue("marketprice_boardid");
                    dataRows.add(new DataRowsSecuritiesPages(id, secid, shortname, regnumber, name, isin, is_traded, emitent_id, emitent_title
                            , emitent_inn, emitent_okpo, gosreg, type, groupD, primary_boardid, marketprice_boardid));
                }
            }
        }
    }
}