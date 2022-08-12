package com.assignment.dbclm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.assignment.dbclm.entities.NACEDao;
import com.assignment.dbclm.repositories.NACERepository;
import com.assignment.dbclm.services.NACEService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@SpringBootTest
class DbclmApplicationTests {
	@Autowired
	private NACEService naceService;

	@MockBean
	private NACERepository naceRepository;

	NACEDao naceDao;
	
	@Mock
	CSVReader csvReader;


	@BeforeEach
	void setUp() {
		naceDao = new NACEDao(110l, 2, "test", "test", "test", "test", "test", "test", "test", "test");
	}

	@Test
	void getNaceDetailsUnitWithCorrectOrder() {
		when(naceRepository.findByOrder(110L)).thenReturn(
				Optional.of(naceDao));
		assertEquals(110l, naceService.getNaceDetails(110l).get().getOrder());
	}

	@Test
	void getNaceDetailsUnitWithInCorrectOrder() {
		when(naceRepository.findByOrder(110L)).thenReturn(
				Optional.of(naceDao));
		assertNotEquals(111l, naceService.getNaceDetails(110l).get().getOrder());
	}

	
	@Test
	void putNaceDetailsUnitTestWithIncorrectFileData() throws CsvValidationException, IOException {
		String[] naceDetail = { "110", "2", "test1", "test", "test", "test", "test", "test", "test", "test" };
		when(csvReader.readNext()).thenReturn(naceDetail);
		when(naceRepository.save(naceDao)).thenReturn(naceDao);
		assertNotEquals("test1", naceDao.getCode());
	}

	@Test
	void putNaceDetailsUnitTestWithCorrectFileData() throws CsvValidationException, IOException {
		String[] naceDetail = { "110", "2", "test", "test", "test", "test", "test", "test", "test", "test" };
		when(csvReader.readNext()).thenReturn(naceDetail);
		when(naceRepository.save(naceDao)).thenReturn(naceDao);
		assertEquals("test", naceDao.getCode());
	}


}
