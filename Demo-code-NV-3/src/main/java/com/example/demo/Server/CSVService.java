package com.example.demo.Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.CSVHelper.CSVHelper;
import com.example.demo.Repository.PersonnelRepository;
import com.example.demo.model.Personnel;

@Service
public class CSVService {

	@Autowired
	  PersonnelRepository personnelrepository;

	  public void save(MultipartFile file) throws Throwable {
	    try {
	      List<Personnel> personnels = CSVHelper.csvToPersonnels(file.getInputStream());
	      personnelrepository.saveAll(personnels);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }

	  public ByteArrayInputStream load() {
	    List<Personnel> personnel = personnelrepository.findAll();

	    ByteArrayInputStream in = CSVHelper.personnelsToCSV(personnel);
	    return in;
	  }

	  public List<Personnel> getAllTutorials() {
	    return personnelrepository.findAll();
	  }
}
