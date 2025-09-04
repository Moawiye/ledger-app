package com.ledger.controller;

import com.ledger.dto.LedgerEntryDto;
import com.ledger.dto.LedgerSummaryDto;
import com.ledger.entity.LedgerEntry;
import com.ledger.service.LedgerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ledger")
@CrossOrigin(origins = {"http://localhost:5173", "https://ledger-app-six.vercel.app", "https://*.vercel.app"})
public class LedgerController {
    
    private final LedgerService ledgerService;
    
    @Autowired
    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }
    
    // Get all entries with running balances
    @GetMapping
    public ResponseEntity<LedgerSummaryDto> getAllEntries() {
        List<LedgerService.LedgerEntryWithBalance> entriesWithBalance = 
            ledgerService.getAllEntriesWithRunningBalance();
        
        List<LedgerEntryDto> dtos = entriesWithBalance.stream()
            .map(ewb -> new LedgerEntryDto(ewb.getEntry(), ewb.getBalance()))
            .collect(Collectors.toList());
        
        long totalBalance = ledgerService.getCurrentBalance();
        LedgerSummaryDto summary = new LedgerSummaryDto(dtos, totalBalance);
        
        return ResponseEntity.ok(summary);
    }
    
    // Add new entry
    @PostMapping
    public ResponseEntity<LedgerEntryDto> addEntry(@Valid @RequestBody LedgerEntryDto dto) {
        try {
            LedgerEntry entry = ledgerService.addEntry(dto.getDate(), dto.getDescription(), dto.getAmount());
            return ResponseEntity.status(HttpStatus.CREATED).body(new LedgerEntryDto(entry));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Delete entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        if (ledgerService.deleteEntry(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Get credits only
    @GetMapping("/credits")
    public ResponseEntity<List<LedgerEntryDto>> getCredits() {
        List<LedgerEntry> credits = ledgerService.getCredits();
        List<LedgerEntryDto> dtos = credits.stream()
            .map(LedgerEntryDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    // Get debits only
    @GetMapping("/debits")
    public ResponseEntity<List<LedgerEntryDto>> getDebits() {
        List<LedgerEntry> debits = ledgerService.getDebits();
        List<LedgerEntryDto> dtos = debits.stream()
            .map(LedgerEntryDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    // Get balance
    @GetMapping("/balance")
    public ResponseEntity<Long> getCurrentBalance() {
        return ResponseEntity.ok(ledgerService.getCurrentBalance());
    }
}
