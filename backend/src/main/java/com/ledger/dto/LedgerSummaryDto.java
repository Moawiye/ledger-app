package com.ledger.dto;

import java.util.List;

public class LedgerSummaryDto {
    
    private List<LedgerEntryDto> entries;
    private long totalBalance;
    private int totalEntries;
    
    public LedgerSummaryDto(List<LedgerEntryDto> entries, long totalBalance) {
        this.entries = entries;
        this.totalBalance = totalBalance;
        this.totalEntries = entries.size();
    }
    
    // Getters and setters
    public List<LedgerEntryDto> getEntries() { return entries; }
    public void setEntries(List<LedgerEntryDto> entries) { this.entries = entries; }
    
    public long getTotalBalance() { return totalBalance; }
    public void setTotalBalance(long totalBalance) { this.totalBalance = totalBalance; }
    
    public int getTotalEntries() { return totalEntries; }
    public void setTotalEntries(int totalEntries) { this.totalEntries = totalEntries; }
}
