package com.assignment.dbclm.services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.dbclm.entities.NACEDao;
import com.assignment.dbclm.repositories.NACERepository;
import com.opencsv.CSVReader;

@Service
public class NACEService {
    @Autowired
    NACERepository naceRepository;

    public ResponseEntity<?> putNaceDetails(InputStream inputStream) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(inputStream, "UTF-8"));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                Long order = nextLine[0] != null ? Long.parseLong(nextLine[0]) : 0;
                Integer level = nextLine[1] != null ? Integer.parseInt(nextLine[1]) : 0;
                String code = nextLine[2] != null ? nextLine[2].replaceFirst("^0+(?!$)", "") : "";
                String parent = nextLine[3] != null ? nextLine[3].replaceFirst("^0+(?!$)", "") : "";
                String description = nextLine[4] != null ? nextLine[4] : "";
                String includes = nextLine[5] != null ? nextLine[5] : "";
                String alsoIncludes = nextLine[6] != null ? nextLine[6] : "";
                String rulings = nextLine[7] != null ? nextLine[7] : "";
                String excludes = nextLine[8] != null ? nextLine[8] : "";
                String refISICrev = nextLine[9] != null ? nextLine[9] : "";
                NACEDao nacedata = new NACEDao(order, level, code, parent, description, includes, alsoIncludes,
                        rulings, excludes, refISICrev);
                naceRepository.save(nacedata);
            }
            return ResponseEntity.status(HttpStatus.OK).body("successfully persisted data from csv");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public NACEDao addNaceDetails(NACEDao naceData) {
        naceData = naceRepository.save(naceData);
        return naceData;
    }

    public List<NACEDao> getAllNaceDetails() {
        return naceRepository.findAll();
    }

    public Optional<NACEDao> getNaceDetails(Long order) {
        return naceRepository.findByOrder(order);
    }
}
