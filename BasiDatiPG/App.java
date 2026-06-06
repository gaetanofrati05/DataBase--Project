package BasiDatiPG;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner; 
public class App{
  public static void main(String [] args){
	  int scelta=0; 
	  Query query= new Query();
	  Scanner sc= new Scanner(System.in);
	  try (Connection conn = DBConnection.getConnection()){ System.out.println("CONNESSIONE RIUSCITA");
	  do{
		  System.out.println();
		  System.out.println("1. Registrazione di una nuova prenotazione per un alloggio;");
		  System.out.println("2. Prenotazione di una esperienza;");
		  System.out.println("3. Aggiunta di un nuovo alloggio;");
		  System.out.println("4. Aggiunta di un servizio per uno specifico alloggio;");
		  System.out.println("5. Modifica del numero di locali di un appartamento;");
		  System.out.println("6. Stampa di tutte le prenotazioni registrate, compreso l’importo totale;");
		  System.out.println("7. Stampa i dati dei professionisti che lavorano presso una specifica agenzia;");
		  System.out.println("8. Stampa di tutti gli utenti, compreso il numero di prenotazioni effettuate;");
		  System.out.println("9. Stampa del numero di alloggi gestiti da ogni host;");
		  System.out.println("10.Ricerca degli alloggi disponibili in un determinato range di date che possano accogliere almeno n ospiti;");
		  System.out.println("11.Stampa di tutti gli utenti che hanno effettuato prenotazioni per un totale di almeno 30 giorni;");
		  System.out.println("12.Stampa del rating medio di ciascun host per ognuno dei 4 rating delle recensioni (Disponibilità, Qualità, Precisione, Pulizia);");
		  System.out.println("13.Stampa di una classifica degli host ordinata in base al numero totale di recensioni ricevute;");
		  System.out.println("14.Stampa Nome e Descrizione degli alloggi che hanno ospitato (in totale) almeno 30 ospiti;");
		  System.out.println("15.Stampa di tutti gli utenti che non hanno prenotato alcuna esperienza;");
		  System.out.println("Quale operazione vuoi fare?(Premi -1 per uscire dal programma)");
		  scelta= sc.nextInt();
		  sc.nextLine();
		  switch(scelta){
			  case -1:
			  {
				   System.out.println("Uscita dal programma...");
                    break;
			  }
			  case 1:
        		{
					query.registrazionePrenotazione(conn,sc);
        			break;
        		}
        		case 2:
        		{
        			query.prenotazioneEsperienza(conn,sc);
        			break;
        		}
        		case 3:
        		{
        			query.aggiungiAlloggio(conn,sc);
        			break;
        		}
        		case 4:
        		{
        			query.aggiuntaServizioperAlloggio(conn,sc);
        			break;
        		}
        		case 5:
        		{
                    query.modificaLocali(conn,sc);
        			break;
        		}
        		case 6:
        		{
        			query.stampaPrenotazioni(conn);
        			break;
        		}
        		case 7:
        		{
        			query.stampaHostprof(conn);
        			break;
        		}
        		case 8:
        		{
        			query.stampaUtentinumeroPrenotazioni(conn);
        			break;
        		}
        		case 9:
        		{
        			query.numeroAlloggigestitiDaHost(conn);
        			break;
        		}
        		case 10:
        		{
        			query.trovaAlloggio(conn, sc);
        			break;
        		}
        		case 11:
        		{
        			query.stampaUtentiprenotazioni(conn);
        			break;
        		}
        		case 12:
        		{
        			query.stampaRatingmedio(conn);
        			break;
        		}
        		case 13:
        		{
        			query.classificaHost(conn);
        			break;
        		}
        		case 14:
        		{
        			query.stampaAlloggiospitano30persone(conn);
        			break;
        		}
        		case 15:
        		{
        			query.utentiChenonHannoEsperienze(conn);
        			break;
        		}
				default:
				System.out.println("Scelta non valida!");
		  }
	  }while(scelta!=-1);	  
  } catch (SQLException e) {
        System.out.println("ERRORE DI CONNESSIONE");
        e.printStackTrace();
    }
	sc.close();
  }
}