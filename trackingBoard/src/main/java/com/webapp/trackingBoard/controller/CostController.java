package com.webapp.trackingBoard.controller;

import com.webapp.trackingBoard.model.Cost;
import com.webapp.trackingBoard.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/costs")
@RequiredArgsConstructor
public class CostController {

    private final CostService costService;

    @GetMapping
    public ResponseEntity<List<Cost>> getAllCosts() {
        List<Cost> costs = costService.getAllCosts();
        return new ResponseEntity<>(costs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cost> getCostById(@PathVariable("id") Long id) {
        Cost cost = costService.getCostById(id);
        if (cost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cost, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Cost> createCost(@RequestBody Cost cost) {
        costService.createCost(cost);
        return new ResponseEntity<>(cost, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Cost> updateCost(@PathVariable("id") Long id, @RequestBody Cost cost) {
        Cost existingCost = costService.getCostById(id);
        if (existingCost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingCost.setPlannedCost(cost.getPlannedCost());
        existingCost.setBudget(cost.getBudget());
        existingCost.setActualCost(cost.getActualCost());
        return new ResponseEntity<>(existingCost, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCost(@PathVariable("id") Long id) {
        costService.deleteCost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

