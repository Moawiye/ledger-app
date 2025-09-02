package com.ledger;

import com.ledger.entity.LedgerEntry;
import com.ledger.repository.LedgerEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final LedgerEntryRepository repository;

    @Autowired
    public DataLoader(LedgerEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only load data if no entries exist
        if (repository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // Sample ledger entries
        repository.save(new LedgerEntry(
            LocalDate.of(2024, 1, 15), 
            "Initial deposit", 
            100000 // $1000.00
        ));
        
        repository.save(new LedgerEntry(
            LocalDate.of(2024, 1, 16), 
            "Grocery shopping", 
            -8500 // -$85.00
        ));
        
        repository.save(new LedgerEntry(
            LocalDate.of(2024, 1, 17), 
            "Salary payment", 
            250000 // $2500.00
        ));
        
        repository.save(new LedgerEntry(
            LocalDate.of(2024, 1, 18), 
            "Gas station", 
            -4500 // -$45.00
        ));
        
        repository.save(new LedgerEntry(
            LocalDate.of(2024, 1, 19), 
            "Freelance work", 
            75000 // $750.00
        ));
        
        System.out.println("✅ Sample data loaded successfully!");
        System.out.println("📊 Total entries: " + repository.count());
        System.out.println("💰 Current balance: " + repository.calculateTotalBalance() + " cents");
    }
}
