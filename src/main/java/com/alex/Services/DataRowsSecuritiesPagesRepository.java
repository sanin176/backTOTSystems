package com.alex.Services;

import com.alex.Model.DataRowsSecuritiesPages;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DataRowsSecuritiesPagesRepository extends CrudRepository<DataRowsSecuritiesPages, Integer> {
    @Modifying
    @Transactional
    @Query("SELECT d FROM DataRowsSecuritiesPages d WHERE d.secid = :secid")
    List<DataRowsSecuritiesPages> findByIdSecid(@Param("secid") String secid);

    @Query("SELECT DISTINCT dS FROM DataRowsSecuritiesPages dS INNER JOIN dS.dopTableDataRowsHistoryPages")
    List<DataRowsSecuritiesPages> findAllHS();
}
