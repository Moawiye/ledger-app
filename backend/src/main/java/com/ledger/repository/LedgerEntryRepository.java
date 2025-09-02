package com.ledger.repository;

import com.ledger.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {
    
    // Find entries by date range
    List<LedgerEntry> findByDateBetweenOrderByDateAscDescriptionAsc(LocalDate startDate, LocalDate endDate);
    
    // Find all entries ordered by date, then description (like your original compareTo)
    List<LedgerEntry> findAllByOrderByDateAscDescriptionAscAmountAsc();
    
    // Find credits (positive amounts)
    List<LedgerEntry> findByAmountGreaterThanOrderByDateAscDescriptionAsc(int amount);
    
    // Find debits (negative amounts)
    List<LedgerEntry> findByAmountLessThanOrderByDateAscDescriptionAsc(int amount);
    
    // Calculate total balance
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM LedgerEntry e")
    Long calculateTotalBalance();
}
