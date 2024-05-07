package com.progresssoft.controller;

import com.progresssoft.dto.DealRequestDTO;
import com.progresssoft.model.Deal;
import com.progresssoft.service.DealServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/deals")
@Validated
@Tag(name = "Deals", description = "Endpoints for managing deals")
public class DealsController {
    private final DealServiceImpl dealService;

    public DealsController(DealServiceImpl dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    @Operation(
            summary = "Get All Deals",
            description = "Retrieves all deals stored in the system"
    )
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = dealService.getAllDeals();
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/{dealUniqueId}")
    @Operation(
            summary = "Get Deal by ID",
            description = "Retrieves a single deal by its unique ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Deal found",
            content = @Content(schema = @Schema(implementation = Deal.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Deal not found"
    )
    public ResponseEntity<Deal> getDealById(@PathVariable Long dealUniqueId) {
            Deal deal = dealService.getDealById(dealUniqueId);
            return ResponseEntity.ok(deal);
    }

    @PostMapping("/import")
    @Operation(
            summary = "Import Deal",
            description = "Imports a new deal into the system"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Deal imported successfully",
            content = @Content(schema = @Schema(implementation = Deal.class))
    )
    public ResponseEntity<Deal> importDeal(@Valid @RequestBody DealRequestDTO deal) {
        Deal savedDeal = dealService.importDeal(deal);
        return new ResponseEntity<>(savedDeal, HttpStatus.CREATED);
    }

}
