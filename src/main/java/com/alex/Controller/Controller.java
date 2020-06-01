package com.alex.Controller;

import com.alex.Model.DataRowsHistoryPages;
import com.alex.Model.DataRowsSecuritiesPages;
import com.alex.Model.DopTableDataRowsHistoryPages;
import com.alex.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {

    @Autowired
    private DataRowsSecuritiesPagesRepository dataRowsSecuritiesPagesRepository;

    @Autowired
    private DataRowsHistoryPagesRepository dataRowsHistoryPagesRepository;

    @Autowired
    private DopTableDataRowsHistoryPagesRepository dopTableDataRowsHistoryPagesRepository;

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = {"/uploadSecurities"}, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileSecurities(@RequestParam MultipartFile file) throws IOException, ParserConfigurationException, SAXException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AdvancedXMLHandlerDataRowsSecuritiesPages advancedXMLHandlerDataRows = new AdvancedXMLHandlerDataRowsSecuritiesPages(false);
        parser.parse(new File(file.getOriginalFilename()), advancedXMLHandlerDataRows);

        List<DataRowsSecuritiesPages> dataRows = advancedXMLHandlerDataRows.getDataRows();

        System.out.println("Длина строки" + dataRows.size());
        for (DataRowsSecuritiesPages d : dataRows
        ) {
            dataRowsSecuritiesPagesRepository.save(d);
        }

        convFile.delete();

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/uploadHistory"}, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFileHistory(@RequestParam MultipartFile file) throws IOException, ParserConfigurationException, SAXException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AdvancedXMLHandlerDataRowsHistoryPages advancedXMLHandlerDataRows = new AdvancedXMLHandlerDataRowsHistoryPages();
        parser.parse(new File(file.getOriginalFilename()), advancedXMLHandlerDataRows);

        List<DataRowsHistoryPages> dataRows = advancedXMLHandlerDataRows.getDataRows();

        System.out.println("Длина строки" + dataRows.size());

        for (DataRowsHistoryPages d : dataRows
        ) {
            if (dopRESTRequestHistory(d)) {
                moveHistory(d);
            } else
                System.out.println("Error");
        }

        convFile.delete();

        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = {"/createSecuritiesPapers"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createSecuritiesPage(@RequestBody DataRowsSecuritiesPages d) {
        if(d.getName().matches("[А-Яа-я0-9 ]+"))
            dataRowsSecuritiesPagesRepository.save(d);
        return ResponseEntity.ok("valid");
    }

    @RequestMapping(value = {"/securitiesPapers"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DataRowsSecuritiesPages> getAllSecuritiesPages() {
        return dataRowsSecuritiesPagesRepository.findAll();
    }

    @RequestMapping(value = {"/securitiesPaper/{id}"}, method = RequestMethod.GET)
    public @ResponseBody
    Optional<DataRowsSecuritiesPages> getDataRowsSecuritiesPagesByID(@PathVariable("id") int id) {
        return dataRowsSecuritiesPagesRepository.findById(id);
    }

    @RequestMapping(value = {"/deleteSecuritiesPaper/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDataRowsSecuritiesPagesByID(@PathVariable("id") int id) {
        dataRowsSecuritiesPagesRepository.deleteById(id);
    }

    @RequestMapping(value = {"/putSecuritiesPaper"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void putDataRowsSecuritiesPages(@RequestBody DataRowsSecuritiesPages d) {
        dataRowsSecuritiesPagesRepository.save(d);
    }






    @RequestMapping(value = {"/createHistoryPapers"}, method = RequestMethod.POST)
    public ResponseEntity<String> createHistoryPage(@RequestBody DataRowsHistoryPages d) throws ParserConfigurationException, SAXException, IOException {
        if (dopRESTRequestHistory(d))
            moveHistory(d);
        else return new ResponseEntity<>("You have problem with connection", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/historiesPapers"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<DopTableDataRowsHistoryPages> getAllHistoryPages() {
        return dopTableDataRowsHistoryPagesRepository.findAll();
    }

    @RequestMapping(value = {"/historyPaper/{id}"}, method = RequestMethod.GET)
    public @ResponseBody
    Optional<DopTableDataRowsHistoryPages> getDataRowsHistoryPagesByID(@PathVariable("id") int id) {
        return dopTableDataRowsHistoryPagesRepository.findById(id);
    }

    @RequestMapping(value = {"/deleteHistoryPaper/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDataRowsHistoryPagesByID(@PathVariable("id") int id) {
        dopTableDataRowsHistoryPagesRepository.deleteById(id);
    }

    @RequestMapping(value = {"/putHistoryPaper"}, method = RequestMethod.PUT)
    public ResponseEntity<String> putDataRowsHistoryPages(@RequestBody DataRowsHistoryPages d) throws ParserConfigurationException, SAXException, IOException {
        if (dopRESTRequestHistory(d))
            moveHistory(d);
        else return new ResponseEntity<>("You have problem with connection", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }


    @RequestMapping(value = {"/historysSecuritiesPapers"}, method = RequestMethod.GET)
    public @ResponseBody List<DataRowsSecuritiesPages> getAllHistoryAndSecuritiesPages() {
        return dataRowsSecuritiesPagesRepository.findAllHS();
    }



    public void moveHistory(DataRowsHistoryPages d) throws ParserConfigurationException, SAXException, IOException {
        List<DataRowsSecuritiesPages> dataRowsSecuritiesPages;

        if (dopRESTRequestHistory(d)) {

            dataRowsSecuritiesPages = dataRowsSecuritiesPagesRepository.findByIdSecid(d.getSECID());

            DopTableDataRowsHistoryPages dopTableDataRowsHistoryPages = new DopTableDataRowsHistoryPages(d.getBOARDID(),
                    d.getTRADEDATE(), d.getSHORTNAME(), d.getSECID(),
                    d.getNUMTRADES(), d.getVALUE(), d.getOPEN(), d.getLOW(), d.getHIGH()
                    , d.getLEGALCLOSEPRICE(), d.getWAPRICE(), d.getCLOSE(), d.getVOLUME(), d.getMARKETPRICE2(),
                    d.getMARKETPRICE3(), d.getADMITTEDQUOTE(), d.getMP2VALTRD(),
                    d.getMARKETPRICE3TRADESVALUE(), d.getADMITTEDVALUE(), d.getWAVAL(), dataRowsSecuritiesPages.get(0));

            dopTableDataRowsHistoryPagesRepository.save(dopTableDataRowsHistoryPages);
        }
    }


    public boolean dopRESTRequestHistory(DataRowsHistoryPages d) throws IOException, SAXException, ParserConfigurationException {
        String newSecid = d.getSECID();

        if (dataRowsSecuritiesPagesRepository.findByIdSecid(newSecid.toLowerCase()).size() == 0) {

            final String uri = "http://iss.moex.com/iss/securities.xml?q=" + newSecid;

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            try (FileWriter writer = new FileWriter(newSecid + ".xml", false)) {
                writer.write(result);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            File convFile = new File(newSecid + ".xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            AdvancedXMLHandlerDataRowsSecuritiesPages advancedXMLHandlerDataRows = new AdvancedXMLHandlerDataRowsSecuritiesPages(true, newSecid);
            parser.parse(convFile, advancedXMLHandlerDataRows);

            List<DataRowsSecuritiesPages> dataRows = advancedXMLHandlerDataRows.getDataRows();

            for (DataRowsSecuritiesPages dataRowsS : dataRows
            ) {
                dataRowsSecuritiesPagesRepository.save(dataRowsS);
            }

            convFile.delete();

            if (dataRows.size() == 0)
                return false;

            System.out.println("----------1------------");
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println("-----------------------");
            System.out.println("----------1------------");


        }

        return true;
    }
}
