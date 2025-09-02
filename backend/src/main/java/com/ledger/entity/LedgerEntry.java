package com.ledger.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "entry_date", nullable = false)
    private LocalDate date;
    
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    
    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount; // Store as cents to avoid floating point issues
    
    // Default constructor for JPA
    protected LedgerEntry() {}
    
    // Constructor (similar to your original)
    public LedgerEntry(LocalDate date, String description, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        
        this.date = date;
        this.description = description.trim();
        this.amount = amount;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LedgerEntry)) return false;
        LedgerEntry that = (LedgerEntry) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("LedgerEntry{id=%d, date=%s, description='%s', amount=%d}", 
            id, date, description, amount);
    }
}
