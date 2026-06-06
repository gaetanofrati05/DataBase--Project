package BasiDatiPG;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Query{
public void registrazionePrenotazione(Connection conn, Scanner sc){
		try {
		System.out.println("Inserisci l'id della prenotazione:");
		int idPren= sc.nextInt();
		sc.nextLine(); //consuma il \n
		System.out.println("Inserisci il codice fiscale dell'utente:");
		String cfUtente= sc.nextLine();
		System.out.println("Inserisci l'id dell'alloggio");
		int idAlloggio= sc.nextInt();
		sc.nextLine();
		System.out.println("Inserisci la data di inizio(YYYY-MM-DD)");
		String dataInizio= sc.nextLine();
		System.out.println("Inserisci la data di fine (YYYY-MM-DD)");
		String dataFine= sc.nextLine();
		System.out.println("Inserisci il numero di ospiti");
		int numOspiti= sc.nextInt();
		sc.nextLine();
		System.out.println("Inserisci il costo totale della prenotazione");
		double costoTotale= sc.nextDouble();
		sc.nextLine();
		System.out.println("Inserisci lo sconto della prenotazione");
		sc.nextLine();
		double sconto= sc.nextDouble();
		sc.nextLine();
		String sql="""
        INSERT INTO PRENOTAZIONE
        (Idprenotazione, Sconto_applicabile, Data_inizio, Data_fine,
         Numero_ospiti, Costo_totale, CF_utente, Idalloggio)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setInt(1, idPren);
            ps.setDouble(2, sconto);
            ps.setDate(3, java.sql.Date.valueOf(dataInizio));
            ps.setDate(4, java.sql.Date.valueOf(dataFine));
            ps.setInt(5, numOspiti);
            ps.setDouble(6, costoTotale);
            ps.setString(7, cfUtente);
            ps.setInt(8, idAlloggio);
            ps.executeUpdate();
            System.out.println("Prenotazione registrata correttamente!");
		} catch(SQLException e){
			System.out.println("Errore durante l'inserimento della prenotazione");
            e.printStackTrace();
		}
	}
public void prenotazioneEsperienza(Connection conn, Scanner sc){
		try{
			System.out.println("Inserisci il codice fiscale");
			String cfUtente= sc.nextLine();
			System.out.println("Inserisci l'id della esperienza");
			int idEsperienza= sc.nextInt();
			sc.nextLine();
			String sql="""
			INSERT INTO ACQUISTO
			(CF_utente, Idesperienza)
			VALUES(?,?)
			""";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, cfUtente);
			ps.setInt(2, idEsperienza);
			ps.executeUpdate();
			System.out.println("Acquisto fatto correttamente!");
		}catch(SQLException e){
			System.out.println("Errore durante l'acquisto");
            e.printStackTrace();
		}
	}
public void aggiungiAlloggio(Connection conn, Scanner sc) {
    try {
        System.out.println("Inserisci l'id dell'alloggio:");
        int idAlloggio = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Inserisci il nome dell'alloggio:");
        String nomeAlloggio = sc.nextLine();
        System.out.println("Inserisci il tipo di alloggio (Camera singola, Appartamento, Villa):");
        String tipoAlloggio = sc.nextLine();
        boolean presenzaBagnoPrivato = false;
        boolean presenzaGiardino = false; 
        String genereSpecifico = null;
        int numeroPiani = 0;
        int numeroLocali = 0;
        switch (tipoAlloggio.toLowerCase()) {
            case "camera singola":
                System.out.println("Presenza bagno privato (true/false):");
                presenzaBagnoPrivato = sc.nextBoolean();
                sc.nextLine();
                System.out.println("Genere specifico (M/F/B) o lascia vuoto:");
                String genereInput = sc.nextLine();
				if (!genereInput.isEmpty()) {
            genereSpecifico = genereInput;
                  }
                break;
            case "appartamento":
                System.out.println("Inserisci numero locali:");
                numeroLocali = sc.nextInt();
                sc.nextLine();
                break;
            case "villa":
                System.out.println("Inserisci numero piani:");
                numeroPiani = sc.nextInt();
                sc.nextLine();
                System.out.println("Presenza giardino (true/false):");
                presenzaGiardino = sc.nextBoolean();
                sc.nextLine();
                break;
            default:
                System.out.println("Tipo di alloggio non valido!");
                return; 
        }
        System.out.println("Costo per notte:");
        double costoNotte = sc.nextDouble();
        sc.nextLine();
        System.out.println("Numero massimo di ospiti:");
        int maxOspiti = sc.nextInt();
        sc.nextLine();
        System.out.println("Descrizione:");
        String descrizione = sc.nextLine();
        System.out.println("Via:");
        String via = sc.nextLine();
        System.out.println("Civico:");
        int civico = sc.nextInt();
        sc.nextLine();
		System.out.println("Permesso animali (true/false)");
		boolean permessoAnimali= sc.nextBoolean();
		sc.nextLine();
		System.out.println("Privilegi utenza(true/false)");
		boolean privilegiUtenza= sc.nextBoolean();
		sc.nextLine();
		System.out.println("Metri quadri");
		 int metriQuadri = sc.nextInt();
		 sc.nextLine();
        System.out.println("Link foto:");
        String linkFoto = sc.nextLine();
        System.out.println("ID città:");
        int idCitta = sc.nextInt();
        sc.nextLine();
        System.out.println("CF Host:");
        String cfHost = sc.nextLine();
        String sql = """
            INSERT INTO ALLOGGIO
            (Idalloggio, Nome, Tipo_alloggio, Numero_piani, Metri_quadri, Presenza_bagno_privato,
             Numero_locali, Permesso_animali, Privilegi_utenza, Presenza_giardino, Genere_specifico,
             Costo_per_notte, Max_ospiti, Descrizione_testuale, Via, Civico, Link_fotografie,
             Idcitta, CF_host)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idAlloggio);
        ps.setString(2, nomeAlloggio);
        ps.setString(3, tipoAlloggio);
        ps.setInt(4, numeroPiani); 
        ps.setInt(5, metriQuadri); 
        ps.setBoolean(6, presenzaBagnoPrivato);
        ps.setInt(7, numeroLocali); 
        ps.setBoolean(8, permessoAnimali);
        ps.setBoolean(9, privilegiUtenza);
        ps.setBoolean(10, presenzaGiardino); 
        ps.setString(11, genereSpecifico); 
        ps.setDouble(12, costoNotte);
        ps.setInt(13, maxOspiti);
        ps.setString(14, descrizione);
        ps.setString(15, via);
        ps.setInt(16, civico);
        ps.setString(17, linkFoto);
        ps.setInt(18, idCitta);
        ps.setString(19, cfHost);
        ps.executeUpdate();
        System.out.println("Alloggio aggiunto correttamente!");
    } catch (SQLException e) {
        System.out.println("Errore durante l'aggiunta dell'alloggio");
        e.printStackTrace();
    }
 }

public void aggiuntaServizioperAlloggio(Connection conn, Scanner sc){
		try{
			System.out.println("Inserisci l'id del servizio");
			int idServizio= sc.nextInt();
			sc.nextLine();
			System.out.println("Inserisci l'id del alloggio");
			int idAlloggio= sc.nextInt();
			sc.nextLine();
			String sql= """
			INSERT INTO PRESTAZIONE
			(Idalloggio, Idservizio)
			VALUES (?,?)
			""";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,idAlloggio);
			ps.setInt(2, idServizio);
			ps.executeUpdate();
			System.out.println("Servizio aggiunto correttamente!");
		}catch (SQLException e) {
        System.out.println("Errore durante l'aggiunta del servizio");
        e.printStackTrace();
    }
}
public void modificaLocali(Connection conn, Scanner sc){
		try{
			System.out.println("Inserisci l'id dell'alloggio");
			int idAlloggio= sc.nextInt();
			sc.nextLine();
			System.out.println("Inserisci il nuovo numero di locali");
			int nuoviLocali= sc.nextInt();
			sc.nextLine();
			String sql= """
			UPDATE ALLOGGIO 
			SET Numero_locali= ?
			WHERE Idalloggio= ?
			AND Tipo_alloggio="Appartamento"
			""";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, nuoviLocali);
            ps.setInt(2, idAlloggio);
			int riga= ps.executeUpdate();
			if(riga>0){
			System.out.println("Numero di locali aggiornato correttamente!");
			}else {
            System.out.println("Aggiornamento non effettuato: l'alloggio non esiste o non è un appartamento.");
		}
		}catch (SQLException e) {
        System.out.println("Errore durante la modifica del numero di locali");
        e.printStackTrace();
    }
  }
public void stampaPrenotazioni (Connection conn){
		String sql= """
		SELECT Idprenotazione, CF_utente, Idalloggio, Data_inizio, Data_fine, Numero_ospiti, Sconto_applicabile, Costo_totale 
		FROM PRENOTAZIONE
        """;
		try{
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println("Prenotazioni presenti");
		 while (rs.next()) {
           System.out.println("----------------------------");
           System.out.println("ID Prenotazione: " + rs.getInt("Idprenotazione"));
           System.out.println("CF Utente: " + rs.getString("CF_utente"));
           System.out.println("ID Alloggio: " + rs.getInt("Idalloggio"));
           System.out.println("Data Inizio: " + rs.getDate("Data_inizio"));
           System.out.println("Data Fine: " + rs.getDate("Data_fine"));
           System.out.println("Numero Ospiti: " + rs.getInt("Numero_ospiti"));
           System.out.println("Sconto Applicabile: " + rs.getDouble("Sconto_applicabile"));
           System.out.println("Costo Totale: " + rs.getDouble("Costo_totale"));
        } 
	}catch (SQLException e) {
        System.out.println("Errore durante la stampa delle prenotazioni");
        e.printStackTrace();
    }
}
public void stampaHostprof(Connection conn){
		try {
		String sql= """
		SELECT CF_host, Email, Tasso_risposta, Livello_esperienza, Tipo_host, Idagenzia
		FROM HOST
		WHERE(Tipo_host='Professionista')
		""";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println("Host professionisti presenti");
		while(rs.next()){
			 System.out.println("----------------------------");
			 System.out.println("CF Host: " + rs.getString("CF_host"));
             System.out.println("Email: " + rs.getString("Email"));
             System.out.println("Tasso risposta: " + rs.getInt("Tasso_risposta"));
             System.out.println("Livello esperienza: " + rs.getInt("Livello_esperienza"));
             System.out.println("Tipo host: " + rs.getString("Tipo_host"));
             System.out.println("ID Agenzia: " + rs.getInt("Idagenzia"));
		}
	}catch (SQLException e) {
        System.out.println("Errore durante la stampa degli host professionisti");
        e.printStackTrace();
    }
}
public void stampaUtentinumeroPrenotazioni(Connection conn){
		try{
			String sql= """
			SELECT u.CF_utente, u.Nome_utente, u.Cognome_utente, u.Email, u.Bio, u.Nazionalita, u.Idnumero_utente, COUNT(p.Idprenotazione) AS numeroPrenotazioni
			FROM UTENTE_REGISTRATO u
			LEFT JOIN PRENOTAZIONE p
			ON u.CF_utente=p.CF_utente
			GROUP BY u.CF_utente, u.Nome_utente, u.Cognome_utente, u.Email, u.Bio, u.Nazionalita, u.Idnumero_utente
			""";
			PreparedStatement ps = conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    System.out.println("Utenti con numero prenotazioni effettuate");
		while(rs.next()){
			System.out.println("----------------------------");
			System.out.println("CF Utente: " + rs.getString("CF_utente"));
            System.out.println("Nome: " + rs.getString("Nome_utente"));
            System.out.println("Cognome: " + rs.getString("Cognome_utente"));
            System.out.println("Email: " + rs.getString("Email"));
            System.out.println("Nazionalità: " + rs.getString("Nazionalita"));
            System.out.println("Numero prenotazioni: " + rs.getInt("numeroPrenotazioni"));
         }
		} catch (SQLException e) {
        System.out.println("Errore durante la stampa degli utenti");
        e.printStackTrace();
    }
}
public void numeroAlloggigestitiDaHost(Connection conn){
	  String sql= """
	  SELECT h.CF_host, h.Email, h.Tipo_host,COUNT(a.Idalloggio) AS numeroAlloggi
      FROM `HOST` h
      JOIN ALLOGGIO a
      ON h.CF_host = a.CF_host
      GROUP BY h.CF_host,h.Email,h.Tipo_host
		""";
		try{
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println("Numero di alloggi gestiti da ogni host:");

        while (rs.next()) {
            System.out.println("----------------------------");
            System.out.println("CF Host: " + rs.getString("CF_host"));
            System.out.println("Email: " + rs.getString("Email"));
            System.out.println("Tipo host: " + rs.getString("Tipo_host"));
            System.out.println("Numero alloggi gestiti: " + rs.getInt("numeroAlloggi"));
        }
    }catch (SQLException e) {
        System.out.println("Errore durante la stampa del numero di alloggi per host");
        e.printStackTrace();
    } 
 }
public void trovaAlloggio(Connection conn, Scanner sc){
		try{
		System.out.println("Inserisci la prima data(YYYY-MM-DD)");
		String dataInizio= sc.nextLine();
		System.out.println("Inserisci la seconda data(YYYY-MM-DD)");
		String dataFine= sc.nextLine();
		System.out.println("Inserisci il numero di ospiti (n)");
		int n= sc.nextInt();
		String sql= """
		SELECT a.Idalloggio, a.Nome, a.Tipo_alloggio, a.Max_ospiti, a.Costo_per_notte
		FROM ALLOGGIO a
		WHERE a.Max_ospiti>=?
		 AND NOT EXISTS (
                  SELECT 1
                  FROM PRENOTAZIONE p
                  WHERE p.Idalloggio = a.Idalloggio
                    AND NOT (
                        p.Data_fine < ?
                        OR p.Data_inizio > ?
                    )
              )
			  """;
		PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, n);
        ps.setDate(2, java.sql.Date.valueOf(dataInizio));
        ps.setDate(3, java.sql.Date.valueOf(dataFine));
        ResultSet rs = ps.executeQuery();
        System.out.println("Alloggi disponibili:");
        boolean trovato = false;
        while (rs.next()) {
            trovato = true;
            System.out.println("----------------------------");
            System.out.println("ID Alloggio: " + rs.getInt("Idalloggio"));
            System.out.println("Nome: " + rs.getString("Nome"));
            System.out.println("Tipo: " + rs.getString("Tipo_alloggio"));
            System.out.println("Max ospiti: " + rs.getInt("Max_ospiti"));
            System.out.println("Costo per notte: €" + rs.getDouble("Costo_per_notte"));
        }

        if (!trovato) {
            System.out.println("Nessun alloggio disponibile per i criteri selezionati.");
        }
	}catch (SQLException e) {
        System.out.println("Errore durante la ricerca degli alloggi disponibili");
        e.printStackTrace();
    }
  } 

public void stampaUtentiprenotazioni(Connection conn){
		String sql="""
		SELECT u.Nome_utente, u.Cognome_utente, u.CF_utente, SUM(DATEDIFF(p.Data_fine, p.Data_inizio)) AS numeroGiorni
		FROM UTENTE_REGISTRATO u
		JOIN PRENOTAZIONE p
		ON u.CF_utente = p.CF_utente
		GROUP BY u.Nome_utente, u.Cognome_utente, u.CF_utente 
		HAVING SUM(DATEDIFF(p.Data_fine, p.Data_inizio)) >=30;
		""";
		try{
		PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
        System.out.println("Utenti con almeno 30 giorni di prenotazioni:");
        boolean trovato = false;
        while (rs.next()) {
            trovato = true;
            System.out.println("----------------------------");
            System.out.println("CF Utente: " + rs.getString("CF_utente"));
            System.out.println("Nome: " + rs.getString("Nome_utente"));
            System.out.println("Cognome: " + rs.getString("Cognome_utente"));
            System.out.println("Numero totale giorni prenotati: " + rs.getInt("numeroGiorni"));
        }
        if (!trovato) {
            System.out.println("Nessun utente con almeno 30 giorni di prenotazioni.");
        }
   } catch (SQLException e) {
        System.out.println("Errore durante la stampa degli utenti con almeno 30 giorni di prenotazioni");
        e.printStackTrace();
    }
}
public void stampaRatingmedio(Connection conn){
		String sql= """
		SELECT h.CF_host, h.Email, 
		AVG(r.Disponibilita_host) AS mediaDisponibilita,
		AVG(r.Qualita_alloggio) AS mediaQualita,
		AVG(r.Precisione_annuncio) AS mediaPrecisione,
        AVG(r.Pulizia) AS mediaPulizia
		FROM `HOST` h
		LEFT JOIN ALLOGGIO a 
		ON h.CF_host=a.CF_host
		LEFT JOIN PRENOTAZIONE p
		ON a.Idalloggio=p.Idalloggio
		LEFT JOIN RECENSIONE r
		ON p.Idprenotazione= r.Idprenotazione
		GROUP BY h.CF_host, h.Email
		""";
		try{
		 PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
		 System.out.println("Rating medio di ciascun host:");
        boolean trovato = false;
        while (rs.next()) {
            trovato = true;
            System.out.println("----------------------------");
            System.out.println("CF Host: " + rs.getString("CF_host"));
            System.out.println("Email: " + rs.getString("Email"));
            System.out.println("Media disponibilità: " + rs.getDouble("mediaDisponibilita"));
            System.out.println("Media qualità: " + rs.getDouble("mediaQualita"));
            System.out.println("Media precisione: " + rs.getDouble("mediaPrecisione"));
            System.out.println("Media pulizia: " + rs.getDouble("mediaPulizia"));
        }
        if (!trovato) {
            System.out.println("Nessun host con recensioni presenti.");
        }
    } catch (SQLException e) {
        System.out.println("Errore durante la stampa del rating medio degli host");
        e.printStackTrace();
    }
}
public void classificaHost(Connection conn){
		String sql="""
		SELECT h.CF_host,h.Email, COUNT(r.Idprenotazione) AS numeroRecensioni
		FROM `HOST`h
		JOIN ALLOGGIO a
		ON h.CF_host=a.CF_host
		JOIN PRENOTAZIONE p
		ON a.Idalloggio=p.Idalloggio
		JOIN RECENSIONE r 
		ON p.Idprenotazione=r.Idprenotazione
		GROUP BY h.CF_host,h.Email
		ORDER BY numeroRecensioni DESC;
		""";
		 try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        System.out.println("Classifica host per numero di recensioni ricevute:");
        int posizione = 1;
        while (rs.next()) {
            System.out.println("----------------------------");
            System.out.println("Posizione: " + posizione++);
            System.out.println("CF Host: " + rs.getString("CF_host"));
            System.out.println("Email: " + rs.getString("Email"));
            System.out.println("Numero recensioni: " + rs.getInt("numeroRecensioni"));
        }
    } catch (SQLException e) {
        System.out.println("Errore durante la classifica degli host");
        e.printStackTrace();
    }
}
public void stampaAlloggiospitano30persone(Connection conn){
		String sql="""
		SELECT a.Nome, a.Descrizione_testuale, SUM(p.Numero_ospiti) AS totaleOspiti
		FROM ALLOGGIO a
		JOIN PRENOTAZIONE p
		ON a.Idalloggio=p.Idalloggio
		GROUP BY a.Nome, a.Descrizione_testuale
		HAVING SUM(p.Numero_ospiti)>=30;
		""";
		try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        System.out.println("Alloggi che hanno ospitato almeno 30 ospiti:");
        boolean trovato = false;
        while (rs.next()) {
            trovato = true;
            System.out.println("----------------------------");
            System.out.println("Nome alloggio: " + rs.getString("Nome"));
            System.out.println("Descrizione: " + rs.getString("Descrizione_testuale"));
            System.out.println("Totale ospiti ospitati: " + rs.getInt("totaleOspiti"));
        }
        if (!trovato) {
            System.out.println("Nessun alloggio ha ospitato almeno 30 ospiti.");
        }
    } catch (SQLException e) {
        System.out.println("Errore durante la stampa degli alloggi");
        e.printStackTrace();
    }
}
public void utentiChenonHannoEsperienze(Connection conn){
		String sql="""
		SELECT u.Nome_utente, u.Cognome_utente, u.CF_utente, u.Email
		FROM UTENTE_REGISTRATO u 
		LEFT JOIN ACQUISTO a
		ON u.CF_utente=a.CF_utente
		WHERE a.Idesperienza IS NULL;
		""";
		try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        System.out.println("Utenti che non hanno acquistato alcuna esperienza:");
        boolean trovato = false;
        while (rs.next()) {
            trovato = true;
            System.out.println("----------------------------");
            System.out.println("CF Utente: " + rs.getString("CF_utente"));
            System.out.println("Nome: " + rs.getString("Nome_utente"));
            System.out.println("Cognome: " + rs.getString("Cognome_utente"));
            System.out.println("Email: " + rs.getString("Email"));
        }
        if (!trovato) {
            System.out.println("Nessun utente trovato.");
        }
    } catch (SQLException e) {
        System.out.println("Errore durante la stampa degli utenti senza esperienze");
        e.printStackTrace();
    }
  }
}

