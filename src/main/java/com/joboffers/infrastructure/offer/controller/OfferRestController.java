package com.joboffers.infrastructure.offer.controller;

import com.joboffers.domain.offer.OfferFacade;
import com.joboffers.domain.offer.dto.NewOfferDto;
import com.joboffers.domain.offer.dto.OfferDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/offers")
public class OfferRestController {

    private final OfferFacade offerFacade;

    @GetMapping
    public ResponseEntity<List<OfferDto>> findAllOffers(){
        List<OfferDto> allOffers = offerFacade.findAllOffers();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable String id){
        OfferDto offerById = offerFacade.findOfferById(id);
        return ResponseEntity.ok(offerById);
    }

    @PostMapping
    public ResponseEntity<OfferDto> addNewOffer(@RequestBody @Valid NewOfferDto newOfferDto){
        OfferDto addedOffer = offerFacade.addNewOffer(newOfferDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedOffer);

    }
}
