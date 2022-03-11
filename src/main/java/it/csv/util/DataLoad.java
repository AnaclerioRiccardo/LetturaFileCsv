package it.csv.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import it.csv.model.Autore;
import it.csv.model.Book;
import it.csv.model.CasaEditrice;
import it.csv.repository.AutoreRepository;
import it.csv.repository.BookRepository;
import it.csv.repository.CasaEditriceRepository;

@Component
public class DataLoad implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AutoreRepository autoreRepo;
	
	@Autowired
	private CasaEditriceRepository editoreRepo;

	/*
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
	}*/
	
	@Override
	public void run(String... args) throws Exception {
		initAutore();
		initBook();
		initCasaEditore();
		initRelazioniEditore();
	}
	
	private void initCasaEditore() throws Exception {
		try (CSVReader csvReader = new CSVReader(new FileReader("casaeditore.csv"));) {
		    String[] values = null;
		    csvReader.readNext();	//nel csv ho la prima riga con scritto titoo ed autore io non voglio avermeli scritti sul db
		    while ((values = csvReader.readNext()) != null) {
		    	editoreRepo.save(new CasaEditrice(values[0], values[1]));
		    }
		}
	}
	
	private void initAutore() throws Exception {
		try (CSVReader csvReader = new CSVReader(new FileReader("autore.csv"));) {
		    String[] values = null;
		    csvReader.readNext();	//nel csv ho la prima riga con scritto titoo ed autore io non voglio avermeli scritti sul db
		    while ((values = csvReader.readNext()) != null) {
		    	autoreRepo.save(new Autore(values[0], values[1]));
		    }
		}
	}
	
	private void initBook() throws Exception {
		try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {
		    String[] values = null;
		    csvReader.readNext();	//nel csv ho la prima riga con scritto titoo ed autore io non voglio avermeli scritti sul db
		    while ((values = csvReader.readNext()) != null) {
		    	bookRepo.save(new Book(values[0], values[1], values[2]));
		    }
		}
	}
	
	private void initRelazioniEditore() throws Exception {
		try (CSVReader csvReader = new CSVReader(new FileReader("editori.csv"));) {
		    String[] values = null;
		    csvReader.readNext();	//nel csv ho la prima riga con scritto titoo ed autore io non voglio avermeli scritti sul db
		    CasaEditrice casaEditrice;
		    while ((values = csvReader.readNext()) != null) {
		    	casaEditrice = editoreRepo.findById(Long.valueOf(values[0])).get();
		    	casaEditrice.getAutori().add(autoreRepo.findById(Long.valueOf(values[1])).get());
		    	casaEditrice.getBooks().add(bookRepo.findById(Long.valueOf(values[2])).get());
		    	editoreRepo.save(casaEditrice);
		    }
		}
	}

}
