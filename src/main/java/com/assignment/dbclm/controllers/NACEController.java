package com.assignment.dbclm.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.dbclm.entities.NACEDao;
import com.assignment.dbclm.services.NACEService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class NACEController {

    @Autowired
    NACEService naceService;

    @PostMapping(path = "putNaceDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Reads data from nace csv and persist into the database")
    public ResponseEntity<?> putNaceDetails(@RequestParam("file") MultipartFile file) {
        try {
            return naceService.putNaceDetails(file.getInputStream());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @PostMapping(path = "addNaceDetails")
    @Operation(summary = "Adds nace data")
    public ResponseEntity<?> addNaceDetails(@RequestBody NACEDao naceData) {
        try {
            naceData = naceService.addNaceDetails(naceData);
            return ResponseEntity.status(HttpStatus.OK).body(naceData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @GetMapping("getNaceDetails/{order}")
    @Operation(summary = "Gets nace details for the given order")
    public ResponseEntity<?> getNaceDetails(@PathVariable(name = "order") Long order) {
        try {
            Optional<NACEDao> naceData = naceService.getNaceDetails(order);
            if (naceData.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(naceData);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order " + order + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("getAllNaceDetails")
    @Operation(summary = "Gets all nace details present in database")
    public ResponseEntity<?> getAllNaceDetails() {
        try {
            List<NACEDao> naceList = naceService.getAllNaceDetails();
            return ResponseEntity.status(HttpStatus.OK).body(naceList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
