package com.alex.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class DataRowsHistoryPages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dataRowsIdHistory")
    private int idN;
    private String BOARDID;
    private Date TRADEDATE;
    private String SHORTNAME;
    private String SECID;
    private double NUMTRADES;
    private double VALUE;
    private double OPEN;
    private double LOW;
    private double HIGH;
    private double LEGALCLOSEPRICE;
    private double WAPRICE;
    private double CLOSE;
    private double VOLUME;
    private double MARKETPRICE2;
    private double MARKETPRICE3;
    private double ADMITTEDQUOTE;
    private double MP2VALTRD;
    private double MARKETPRICE3TRADESVALUE;
    private double ADMITTEDVALUE;
    private double WAVAL;

    public DataRowsHistoryPages() {

    }

    public DataRowsHistoryPages(String BOARDID, Date TRADEDATE, String SHORTNAME, String SECID, double NUMTRADES, double VALUE, double OPEN, double LOW, double HIGH, double LEGALCLOSEPRICE, double WAPRICE, double CLOSE, double VOLUME, double MARKETPRICE2, double MARKETPRICE3, double ADMITTEDQUOTE, double MP2VALTRD, double MARKETPRICE3TRADESVALUE, double ADMITTEDVALUE, double WAVAL) {
        this.BOARDID = BOARDID;
        this.TRADEDATE = TRADEDATE;
        this.SHORTNAME = SHORTNAME;
        this.SECID = SECID;
        this.NUMTRADES = NUMTRADES;
        this.VALUE = VALUE;
        this.OPEN = OPEN;
        this.LOW = LOW;
        this.HIGH = HIGH;
        this.LEGALCLOSEPRICE = LEGALCLOSEPRICE;
        this.WAPRICE = WAPRICE;
        this.CLOSE = CLOSE;
        this.VOLUME = VOLUME;
        this.MARKETPRICE2 = MARKETPRICE2;
        this.MARKETPRICE3 = MARKETPRICE3;
        this.ADMITTEDQUOTE = ADMITTEDQUOTE;
        this.MP2VALTRD = MP2VALTRD;
        this.MARKETPRICE3TRADESVALUE = MARKETPRICE3TRADESVALUE;
        this.ADMITTEDVALUE = ADMITTEDVALUE;
        this.WAVAL = WAVAL;
    }
}