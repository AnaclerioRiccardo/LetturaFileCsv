package it.csv.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import it.csv.model.Book;
import it.csv.repository.BookRepository;

@Component
public class DataLoad implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public void run(String... args) throws Exception {
		//List<List<String>> records = new ArrayList<List<String>>();
		try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {
		    String[] values = null;
		    csvReader.readNext();	//nel csv ho la prima riga con scritto titoo ed autore io non voglio avermeli scritti sul db
		    while ((values = csvReader.readNext()) != null) {
		        //records.add(Arrays.asList(values));
		    	bookRepo.save(new Book(values[0], values[1]));
		    }
		}
		System.out.println("test");
	}

}
