package com.ledger.service;

import com.ledger.entity.LedgerEntry;
import com.ledger.repository.LedgerEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LedgerService {
    
    private final LedgerEntryRepository repository;
    
    @Autowired
    public LedgerService(LedgerEntryRepository repository) {
        this.repository = repository;
    }
    
    // Get all entries (sorted like your original Ledger class)
    @Transactional(readOnly = true)
    public List<LedgerEntry> getAllEntries() {
        return repository.findAllByOrderByDateAscDescriptionAscAmountAsc();
    }
    
    // Add new entry
    public LedgerEntry addEntry(LocalDate date, String description, int amount) {
        LedgerEntry entry = new LedgerEntry(date, description, amount);
        return repository.save(entry);
    }
    
    // Delete entry by ID
    public boolean deleteEntry(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Get entry by ID
    @Transactional(readOnly = true)
    public Optional<LedgerEntry> getEntryById(Long id) {
        return repository.findById(id);
    }
    
    // Get credits only (like your original getCredits method)
    @Transactional(readOnly = true)
    public List<LedgerEntry> getCredits() {
        return repository.findByAmountGreaterThanOrderByDateAscDescriptionAsc(0);
    }
    
    // Get debits only (like your original getDebits method)
    @Transactional(readOnly = true)
    public List<LedgerEntry> getDebits() {
        return repository.findByAmountLessThanOrderByDateAscDescriptionAsc(0);
    }
    
    // Get entries by date range (like your original getDateRange method)
    @Transactional(readOnly = true)
    public List<LedgerEntry> getEntriesByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findByDateBetweenOrderByDateAscDescriptionAsc(startDate, endDate);
    }
    
    // Calculate current balance (like your original getBalance method)
    @Transactional(readOnly = true)
    public long getCurrentBalance() {
        Long balance = repository.calculateTotalBalance();
        return balance != null ? balance : 0L;
    }
    
    // Get running balances for all entries
    @Transactional(readOnly = true)
    public List<LedgerEntryWithBalance> getAllEntriesWithRunningBalance() {
        List<LedgerEntry> entries = getAllEntries();
        List<LedgerEntryWithBalance> result = new java.util.ArrayList<>();
        
        long runningBalance = 0;
        for (LedgerEntry entry : entries) {
            runningBalance += entry.getAmount();
            result.add(new LedgerEntryWithBalance(entry, runningBalance));
        }
        
        return result;
    }
    
    // Inner class to represent entry with running balance
    public static class LedgerEntryWithBalance {
        private final LedgerEntry entry;
        private final long balance;
        
        public LedgerEntryWithBalance(LedgerEntry entry, long balance) {
            this.entry = entry;
            this.balance = balance;
        }
        
        public LedgerEntry getEntry() { return entry; }
        public long getBalance() { return balance; }
    }
}
