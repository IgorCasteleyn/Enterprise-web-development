package perform;

import org.springframework.web.reactive.function.client.WebClient;
import domein.Festival;
import reactor.core.publisher.Mono;

public class PerformRest {
  private final String SERVER_URI = "http://localhost:8080/rest";
  private WebClient webClient = WebClient.create();

  public PerformRest() throws Exception {
    try {
      System.out.println("\n------- GET ARTIESTEN BY PER FESTIVAL -------");
      getArtiestenPerFestival(1);

      System.out.println("\n------- GET FESTIVALS BY GENRE  ------- ");
      getFestivalsByGenres(1);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void getArtiestenPerFestival(int festivalId) {
    webClient.get().uri(SERVER_URI + "/artiest/festival/" + festivalId)
        .retrieve()
        .bodyToFlux(String.class)
        .flatMap(artiest -> {
          System.out.println(artiest + "\n");

          return Mono.empty();
        })
        .blockLast();
  }

  private void getFestivalsByGenres(int genreId) {
    webClient.get().uri(SERVER_URI + "/festivals/genre/" + genreId)
        .retrieve()
        .bodyToFlux(Festival.class)
        .flatMap(Festival -> {
          printFestivalData(Festival);
          return Mono.empty();
        })
        .blockLast();
  }

  private void printFestivalData(Festival Festival) {
    System.out.println("Festival: " + Festival.getNaam() + " - " + Festival.getRegio() + " - " + Festival.getDatum()
        + " - " + Festival.getPrijs());
  }

  public static void main(String[] args) throws Exception {
    new PerformRest();
  }
}