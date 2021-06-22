package pl.kielce.tu.neo4j.ogm;

import java.util.Map;
import java.util.Scanner;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class TestNeo4J {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "1234").build();
	    SessionFactory sessionFactory = new SessionFactory(configuration, "pl.kielce.tu.neo4j.ogm");

		Session session = sessionFactory.openSession();
		
		session.purgeDatabase();
			/*
		BookService bookService = new BookService(session);
		
		Author author1 = new Author("Sienkiewicz");
		Book book1 = new Book("Potop");
		book1.addAuthor(author1);
		bookService.createOrUpdate(book1);
		
		Author author2 = new Author("Mickiewicz");
		Author author3 = new Author("Słowacki");
		Book book2 = new Book("Wiersze");
		book2.addAuthor(author2);
		book2.addAuthor(author3);
		bookService.createOrUpdate(book2);
		
		Reader reader1 = new Reader("Kowalski");
		reader1.addBook(book1);
		ReaderService readerService = new ReaderService(session);
		readerService.createOrUpdate(reader1);
		
		System.out.println("All books:");
		for(Book b : bookService.readAll())
			System.out.println(b);
	
		for(Map<String, Object> map : bookService.getBookRelationships())
			System.out.println(map);
*/

		SamochodService samochodService = new SamochodService(session);
		DiagnostaService diagnostaService = new DiagnostaService(session);
		/*
		Diagnosta diagnosta1 = new Diagnosta("Jan", "Kowalski");
		Diagnosta diagnosta2 = new Diagnosta("Janek", "Kowalczewski");
		Samochod samochod1 = new Samochod("BMW", 2012);
		samochod1.addDiagnosta(diagnosta1);
		samochod1.addDiagnosta(diagnosta2);
		samochodService.createOrUpdate(samochod1);


		diagnostaService.createOrUpdate(diagnosta1);
		diagnostaService.createOrUpdate(diagnosta2);
*/
		System.out.println("Samochody:");
		for(Samochod s : samochodService.readAll())
			System.out.println(s);

		for(Map<String, Object> map : samochodService.getSamochodRelationships())
			System.out.println(map);

		System.out.println("Diagnosci:");
		for(Diagnosta d : diagnostaService.readAll())
			System.out.println(d);

		Scanner scan = new Scanner(System.in);
		int wybor = -1;
		while (wybor!=0){
			System.out.println("Serwis samochodowy\nWybierz...\n1 aby dodac\n2 aby usunac\n3 aby zaktualizowac\n" +
					"4 aby wyswietlic\n5 wyszukiwanie samochodow po roczniku\n0 aby wyjsc");
			wybor = Integer.parseInt(scan.nextLine());
			if(wybor == 1){
				System.out.println("Wybierz...\n1 dla diagnosty\n2 dla samochodu");
				wybor = Integer.parseInt(scan.nextLine());
				if(wybor == 1){
					System.out.println("Wpisz imie diagnosty");
					String imie = scan.nextLine();
					System.out.println("Wpisz nazwisko diagnosty");
					String nazwisko = scan.nextLine();
					diagnostaService.createOrUpdate(new Diagnosta(imie, nazwisko));
				}
				else if(wybor == 2){
					System.out.println("Wpisz marke pojazdu");
					String marka = scan.nextLine();
					System.out.println("Wpisz rocznik pojazdu");
					int rocznik = Integer.parseInt(scan.nextLine());
					samochodService.createOrUpdate(new Samochod(marka, rocznik));
				}
			}
			else if(wybor == 2){
				System.out.println("Wybierz...\n1 dla diagnosty\n2 dla samochodu");
				wybor = Integer.parseInt(scan.nextLine());
				if(wybor == 1){
					System.out.println("Wpisz ID diagnosty do usuniecia");
					long id = Integer.parseInt(scan.nextLine());
					diagnostaService.delete(id);
				}
				else if(wybor == 2){
					System.out.println("Wpisz ID samochodu do usuniecia");
					long id = Integer.parseInt(scan.nextLine());
					samochodService.delete(id);
				}
			}
			else if(wybor == 3){
				System.out.println("Wybierz...\n1 dla diagnosty\n2 dla samochodu");
				wybor = Integer.parseInt(scan.nextLine());
				if(wybor == 1) {
					System.out.println("Wpisz ID diagnosty do zaktualizowania");
					long id = Integer.parseInt(scan.nextLine());
					System.out.println("Wpisz imie diagnosty");
					String imie = scan.nextLine();
					System.out.println("Wpisz nazwisko diagnosty");
					String nazwisko = scan.nextLine();
					Diagnosta diagnosta = diagnostaService.read(id);
					diagnosta.setImie(imie);
					diagnosta.setNazwisko(nazwisko);
					diagnostaService.createOrUpdate(diagnosta);
				}
				else if(wybor == 2) {
					System.out.println("Wpisz ID samochodu do zaktualizowania");
					long id = Integer.parseInt(scan.nextLine());
					System.out.println("Wpisz marke pojazdu");
					String marka = scan.nextLine();
					System.out.println("Wpisz rocznik pojazdu");
					int rocznik = Integer.parseInt(scan.nextLine());
					Samochod samochod = samochodService.read(id);
					samochod.setMarka(marka);
					samochod.setRocznik(rocznik);
					samochodService.createOrUpdate(samochod);
				}
			}
			else if(wybor==4){
				System.out.println("Wybierz...\n1 dla diagnostow\n2 dla samochodow\n3 dla diagnostow po imieniu\n" +
						"4 dla samochodow po marce");
				wybor = Integer.parseInt(scan.nextLine());
				if(wybor == 1){
					System.out.println("Diagnosci:");
					for(Diagnosta d : diagnostaService.readAll())
						System.out.println(d);
				}
				else if(wybor == 2){
					System.out.println("Samochody:");
					for(Samochod s : samochodService.readAll())
						System.out.println(s);

				}
				else if(wybor == 3){
					System.out.println("podaj imie");
					String imie = scan.nextLine();
					System.out.println("Diagnosci o podanym imieniu:");
					for(Diagnosta d : diagnostaService.readAll()) {
						if (d.getImie().equals(imie)) {
							System.out.println(d);
						}
					}
				}
				else if (wybor==4){
					System.out.println("Podaj marke");
					String marka = scan.nextLine();
					System.out.println("Samochody o podanej marce");
					for(Samochod s : samochodService.readAll()){
						if(s.getMarka().equals(marka)){
							System.out.println(s);
						}

					}

				}
			}
			else if(wybor == 5){
				System.out.println("Podaj rocznik");
				int rocznik = Integer.parseInt(scan.nextLine());
				System.out.println("Samochody nowsze od rocznika " + rocznik);
				for(Samochod s : samochodService.readAll()){
					if(s.getRocznik()>rocznik){
						System.out.println(s);
					}
				}

			}
			else if(wybor == 0){
				break;
			}

		}


		sessionFactory.close();
	}
}
