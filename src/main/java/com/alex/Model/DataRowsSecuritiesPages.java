package com.alex.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
public class DataRowsSecuritiesPages implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "dataRowsIdSecurities")
    private int idN;
    private int id;
    @Column(name = "secidS")
    private String secid;
    private String shortname;
    private String regnumber;
    private String name;
    private String isin;
    private int is_traded;
    private int emitent_id;
    private String emitent_title;
    private String emitent_inn;
    private String emitent_okpo;
    private String gosreg;
    private String type;
    private String groupD;
    private String primary_boardid;
    private String marketprice_boardid;

    @OneToMany(mappedBy="dataRowsSecuritiesPagesSecids", fetch = FetchType.EAGER)
    private List<DopTableDataRowsHistoryPages> dopTableDataRowsHistoryPages;

    public DataRowsSecuritiesPages() {

    }

    public DataRowsSecuritiesPages(int id, String secid, String shortname, String regnumber, String name, String isin, int is_traded, int emitent_id, String emitent_title, String emitent_inn, String emitent_okpo, String gosreg, String type, String groupD, String primary_boardid, String marketprice_boardid) {
        this.id = id;
        this.secid = secid;
        this.shortname = shortname;
        this.regnumber = regnumber;
        this.name = name;
        this.isin = isin;
        this.is_traded = is_traded;
        this.emitent_id = emitent_id;
        this.emitent_title = emitent_title;
        this.emitent_inn = emitent_inn;
        this.emitent_okpo = emitent_okpo;
        this.gosreg = gosreg;
        this.type = type;
        this.groupD = groupD;
        this.primary_boardid = primary_boardid;
        this.marketprice_boardid = marketprice_boardid;
    }

}