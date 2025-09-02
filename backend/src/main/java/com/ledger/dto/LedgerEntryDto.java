package com.ledger.dto;

import com.ledger.entity.LedgerEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LedgerEntryDto {
    
    private Long id;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Amount is required")
    private Integer amount;
    
    private Long runningBalance;
    
    // Default constructor
    public LedgerEntryDto() {}
    
    // Constructor from entity
    public LedgerEntryDto(LedgerEntry entry) {
        this.id = entry.getId();
        this.date = entry.getDate();
        this.description = entry.getDescription();
        this.amount = entry.getAmount();
    }
    
    // Constructor with running balance
    public LedgerEntryDto(LedgerEntry entry, Long runningBalance) {
        this(entry);
        this.runningBalance = runningBalance;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    
    public Long getRunningBalance() { return runningBalance; }
    public void setRunningBalance(Long runningBalance) { this.runningBalance = runningBalance; }
}
