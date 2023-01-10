package com.inatel.quotation.management.repository;

import com.inatel.quotation.management.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {


    List<Quote> findByStockId(String id);




}
