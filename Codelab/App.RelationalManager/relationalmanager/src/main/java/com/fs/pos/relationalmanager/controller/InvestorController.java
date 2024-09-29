package com.fs.pos.relationalmanager.controller;

import com.fs.pos.relationalmanager.entities.Investor;
import com.fs.pos.relationalmanager.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investors")
@CrossOrigin(origins = "*")
public class InvestorController {
    @Autowired
    private InvestorService investorService;

    // Create a new investor
    @PostMapping
    public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor) {
        Investor createdInvestor = investorService.createInvestor(investor);
        return ResponseEntity.ok(createdInvestor);
    }

    // Get an investor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Investor> getInvestorById(@PathVariable Long id) {
        Optional<Investor> investor = investorService.getInvestorById(id);
        return investor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all investors
    @GetMapping
    public ResponseEntity<List<Investor>> getAllInvestors() {
        List<Investor> investors = investorService.getAllInvestors();
        return ResponseEntity.ok(investors);
    }

    // Update an investor
    @PutMapping("/{id}")
    public ResponseEntity<Investor> updateInvestor(@PathVariable Long id, @RequestBody Investor investorDetails) {
        Investor updatedInvestor = investorService.updateInvestor(id, investorDetails);
        return ResponseEntity.ok(updatedInvestor);
    }

    // Delete an investor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestor(@PathVariable Long id) {
        investorService.deleteInvestor(id);
        return ResponseEntity.noContent().build();
    }

    // Get all investors by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Investor>> getInvestorsByUserId(@PathVariable Long userId) {
        List<Investor> investors = investorService.getInvestorsByUserId(userId);
        return ResponseEntity.ok(investors);
    }
}