package com.zomerFestival.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domein.*;
import repository.*;

@Component
public class InitDataConfig implements CommandLineRunner {

        private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RegioRepository regioRepository;

        @Autowired
        private GenreRepository genreRepository;

        @Autowired
        private SubGenreRepository subGenreRepository;

        @Autowired
        private FestivalRepository festivalRepository;

        @Autowired
        private TicketRepository ticketRepository;

        @Override
        public void run(String... args) {

                // Gebruikers
                User user1 = new User(1, "user1", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_USER"));
                User user2 = new User(2, "user2", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_USER"));
                User user3 = new User(3, "user3", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_USER"));

                User admin = new User(4, "admin", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_ADMIN"));

                userRepository.saveAll(Arrays.asList(user1, user2, user3, admin));

                // Regio's
                Regio regio1 = Regio.builder().naam("Antwerpen")
                                .link("https://www.belgiekaart.com/wp-content/uploads/2020/12/Antwerpen.jpg").build();
                Regio regio2 = Regio.builder().naam("Luik")
                                .link("https://www.belgiekaart.com/wp-content/uploads/2020/12/Liege.jpg").build();
                Regio regio3 = Regio.builder().naam("Limburg")
                                .link("https://www.belgiekaart.com/wp-content/uploads/2020/12/Limburg.jpg").build();
                Regio regio4 = Regio.builder().naam("Oost-Vlaanderen")
                                .link("https://www.belgiekaart.com/wp-content/uploads/2020/12/Oost-Vlaanderen.jpg")
                                .build();
                Regio regio5 = Regio.builder().naam("Vlaams-Brabant")
                                .link("https://www.belgiekaart.com/wp-content/uploads/2020/12/Vlaams-Brabant.jpg")
                                .build();
                Regio regio6 = Regio.builder().naam("West-Vlaanderen")
                                .link("https://www.belgiekaart.com/wp-content/uploads/2020/12/West-Vlaanderen.jpg")
                                .build();

                regioRepository.saveAll(Arrays.asList(regio1, regio2, regio3, regio4, regio5, regio6));

                // Genres
                Genre pop = Genre.builder().naam("Pop").link(
                                "https://userscontent2.emaze.com/images/ae8de35b-b91d-4267-90ed-78868d1ca0c8/dbfd688c-9a00-4da3-baec-6fcd6032701c.jpg")
                                .build();
                Genre rock = Genre.builder().naam("Rock")
                                .link("https://i.pinimg.com/originals/0e/84/50/0e8450fa12dc7a7fb2148101bd836abd.jpg")
                                .build();
                Genre dance = Genre.builder().naam("Dance").link(
                                "https://musictech.com/wp-content/uploads/2019/10/tutorial-create-edm-free-header@1400x1050-650x488.jpg")
                                .build();
                Genre rap = Genre.builder().naam("Rap").link(
                                "https://getwallpapers.com/wallpaper/full/6/9/f/1128456-free-rap-wallpaper-3840x2160.jpg")
                                .build();
                Genre jazz = Genre.builder().naam("Jazz")
                                .link("https://media04.meinbezirk.at/article/2016/02/22/9/376639_XL.jpg?1462069121")
                                .build();

                genreRepository.saveAll(Arrays.asList(pop, rock, dance, rap, jazz));

                // SubGenres
                SubGenre popSub1 = SubGenre.builder().naam("Pop Rock").genre(pop).build();
                SubGenre popSub2 = SubGenre.builder().naam("Pop Ballad").genre(pop).build();
                SubGenre popSub3 = SubGenre.builder().naam("Pop Punk").genre(pop).build();

                SubGenre rockSub1 = SubGenre.builder().naam("Classic Rock").genre(rock).build();
                SubGenre rockSub2 = SubGenre.builder().naam("Indie Rock").genre(rock).build();
                SubGenre rockSub3 = SubGenre.builder().naam("Alternative Rock").genre(rock).build();

                SubGenre danceSub1 = SubGenre.builder().naam("House").genre(dance).build();
                SubGenre danceSub2 = SubGenre.builder().naam("Techno").genre(dance).build();
                SubGenre danceSub3 = SubGenre.builder().naam("Trance").genre(dance).build();

                SubGenre rapSub1 = SubGenre.builder().naam("Hip Hop").genre(rap).build();
                SubGenre rapSub2 = SubGenre.builder().naam("Trap").genre(rap).build();
                SubGenre rapSub3 = SubGenre.builder().naam("Gangsta Rap").genre(rap).build();

                SubGenre jazzSub1 = SubGenre.builder().naam("Smooth Jazz").genre(jazz).build();
                SubGenre jazzSub2 = SubGenre.builder().naam("Traditional Jazz").genre(jazz).build();
                SubGenre jazzSub3 = SubGenre.builder().naam("Fusion Jazz").genre(jazz).build();

                subGenreRepository.saveAll(Arrays.asList(popSub1, popSub2, popSub3, rockSub1, rockSub2, rockSub3,
                                danceSub1, danceSub2, danceSub3, rapSub1, rapSub2, rapSub3, jazzSub1, jazzSub2,
                                jazzSub3));

                // Festivals
                Festival festival1 = Festival.builder().naam("Festival 1")
                                .datum(LocalDate.of(2024, 7, 15))
                                .aanvangsuur(LocalTime.of(12, 0))
                                .regio(regio1)
                                .genre(pop)
                                .capaciteit(1000)
                                .prijs(new BigDecimal("25.00"))
                                .festivalNummer1(12345)
                                .festivalNummer2(67890)
                                .build();

                Festival festival2 = Festival.builder().naam("Festival 2")
                                .datum(LocalDate.of(2024, 7, 20))
                                .aanvangsuur(LocalTime.of(14, 0))
                                .regio(regio2)
                                .genre(rock)
                                .capaciteit(800)
                                .prijs(new BigDecimal("30.00"))
                                .festivalNummer1(54321)
                                .festivalNummer2(98765)
                                .build();

                Festival festival3 = Festival.builder().naam("Festival 3")
                                .datum(LocalDate.of(2024, 7, 25))
                                .aanvangsuur(LocalTime.of(11, 0))
                                .regio(regio3)
                                .genre(dance)
                                .capaciteit(1200)
                                .prijs(new BigDecimal("40.00"))
                                .festivalNummer1(24680)
                                .festivalNummer2(13579)
                                .build();

                Festival festival4 = Festival.builder().naam("Festival 4")
                                .datum(LocalDate.of(2024, 8, 1))
                                .aanvangsuur(LocalTime.of(13, 0))
                                .regio(regio4)
                                .genre(rap)
                                .capaciteit(900)
                                .prijs(new BigDecimal("35.00"))
                                .festivalNummer1(98765)
                                .festivalNummer2(54321)
                                .build();

                Festival festival5 = Festival.builder().naam("Festival 5")
                                .datum(LocalDate.of(2024, 8, 5))
                                .aanvangsuur(LocalTime.of(15, 0))
                                .regio(regio5)
                                .genre(jazz)
                                .capaciteit(700)
                                .prijs(new BigDecimal("45.00"))
                                .festivalNummer1(13579)
                                .festivalNummer2(24680)
                                .build();

                Festival festival6 = Festival.builder().naam("Festival 6")
                                .datum(LocalDate.of(2024, 8, 10))
                                .aanvangsuur(LocalTime.of(16, 0))
                                .regio(regio6)
                                .genre(pop)
                                .capaciteit(1100)
                                .prijs(new BigDecimal("30.00"))
                                .festivalNummer1(67890)
                                .festivalNummer2(12345)
                                .build();

                Festival festival7 = Festival.builder().naam("Festival 7")
                                .datum(LocalDate.of(2024, 8, 15))
                                .aanvangsuur(LocalTime.of(17, 0))
                                .regio(regio1)
                                .genre(rock)
                                .capaciteit(850)
                                .prijs(new BigDecimal("35.00"))
                                .festivalNummer1(54321)
                                .festivalNummer2(98765)
                                .build();

                Festival festival8 = Festival.builder().naam("Festival 8")
                                .datum(LocalDate.of(2024, 8, 20))
                                .aanvangsuur(LocalTime.of(18, 0))
                                .regio(regio2)
                                .genre(dance)
                                .capaciteit(1250)
                                .prijs(new BigDecimal("40.00"))
                                .festivalNummer1(24680)
                                .festivalNummer2(13579)
                                .build();

                Festival festival9 = Festival.builder().naam("Festival 9")
                                .datum(LocalDate.of(2024, 8, 25))
                                .aanvangsuur(LocalTime.of(19, 0))
                                .regio(regio3)
                                .genre(rap)
                                .capaciteit(950)
                                .prijs(new BigDecimal("45.00"))
                                .festivalNummer1(98765)
                                .festivalNummer2(54321)
                                .build();

                Festival festival10 = Festival.builder().naam("Festival 10")
                                .datum(LocalDate.of(2024, 9, 1))
                                .aanvangsuur(LocalTime.of(20, 0))
                                .regio(regio4)
                                .genre(jazz)
                                .capaciteit(750)
                                .prijs(new BigDecimal("50.00"))
                                .festivalNummer1(13579)
                                .festivalNummer2(24680)
                                .build();

                Festival festival11 = Festival.builder().naam("Festival 11")
                                .datum(LocalDate.of(2024, 9, 5))
                                .aanvangsuur(LocalTime.of(21, 0))
                                .regio(regio5)
                                .genre(pop)
                                .capaciteit(1150)
                                .prijs(new BigDecimal("55.00"))
                                .festivalNummer1(67890)
                                .festivalNummer2(12345)
                                .build();

                Festival festival12 = Festival.builder().naam("Festival 12")
                                .datum(LocalDate.of(2024, 9, 12))
                                .aanvangsuur(LocalTime.of(22, 0))
                                .regio(regio6)
                                .genre(rock)
                                .capaciteit(900)
                                .prijs(new BigDecimal("60.00"))
                                .festivalNummer1(54321)
                                .festivalNummer2(98765)
                                .build();

                Festival festival13 = Festival.builder().naam("Festival 13")
                                .datum(LocalDate.of(2024, 9, 10))
                                .aanvangsuur(LocalTime.of(13, 0))
                                .regio(regio1)
                                .genre(dance)
                                .capaciteit(1200)
                                .prijs(new BigDecimal("65.00"))
                                .festivalNummer1(24680)
                                .festivalNummer2(13579)
                                .build();

                Festival festival14 = Festival.builder().naam("Festival 14")
                                .datum(LocalDate.of(2024, 9, 15))
                                .aanvangsuur(LocalTime.of(14, 0))
                                .regio(regio2)
                                .genre(rap)
                                .capaciteit(1000)
                                .prijs(new BigDecimal("70.00"))
                                .festivalNummer1(98765)
                                .festivalNummer2(54321)
                                .build();

                Festival festival15 = Festival.builder().naam("Festival 15")
                                .datum(LocalDate.of(2024, 9, 20))
                                .aanvangsuur(LocalTime.of(15, 0))
                                .regio(regio3)
                                .genre(jazz)
                                .capaciteit(800)
                                .prijs(new BigDecimal("75.00"))
                                .festivalNummer1(13579)
                                .festivalNummer2(24680)
                                .build();

                Festival festival16 = Festival.builder().naam("Festival 16")
                                .datum(LocalDate.of(2024, 9, 25))
                                .aanvangsuur(LocalTime.of(16, 0))
                                .regio(regio4)
                                .genre(pop)
                                .capaciteit(1100)
                                .prijs(new BigDecimal("80.00"))
                                .festivalNummer1(67890)
                                .festivalNummer2(12345)
                                .build();

                Festival festival17 = Festival.builder().naam("Festival 17")
                                .datum(LocalDate.of(2024, 10, 1))
                                .aanvangsuur(LocalTime.of(17, 0))
                                .regio(regio5)
                                .genre(rock)
                                .capaciteit(850)
                                .prijs(new BigDecimal("85.00"))
                                .festivalNummer1(54321)
                                .festivalNummer2(98765)
                                .build();

                Festival festival18 = Festival.builder().naam("Festival 18")
                                .datum(LocalDate.of(2024, 10, 5))
                                .aanvangsuur(LocalTime.of(18, 0))
                                .regio(regio6)
                                .genre(dance)
                                .capaciteit(1250)
                                .prijs(new BigDecimal("90.00"))
                                .festivalNummer1(24680)
                                .festivalNummer2(13579)
                                .build();

                Festival festival19 = Festival.builder().naam("Festival 19")
                                .datum(LocalDate.of(2024, 10, 10))
                                .aanvangsuur(LocalTime.of(19, 0))
                                .regio(regio1)
                                .genre(rap)
                                .capaciteit(950)
                                .prijs(new BigDecimal("95.00"))
                                .festivalNummer1(98765)
                                .festivalNummer2(54321)
                                .build();

                Festival festival20 = Festival.builder().naam("Festival 20")
                                .datum(LocalDate.of(2024, 10, 15))
                                .aanvangsuur(LocalTime.of(20, 0))
                                .regio(regio2)
                                .genre(jazz)
                                .capaciteit(750)
                                .prijs(new BigDecimal("100.00"))
                                .festivalNummer1(13579)
                                .festivalNummer2(24680)
                                .build();

                festivalRepository.saveAll(Arrays.asList(
                                festival1, festival2, festival3, festival4, festival5,
                                festival6, festival7, festival8, festival9, festival10,
                                festival11, festival12, festival13, festival14, festival15,
                                festival16, festival17, festival18, festival19, festival20));

                // Tickets
                Ticket ticket1 = Ticket.builder()
                                .festival(festival1)
                                .user(user1)
                                .aantal(7)
                                .build();

                Ticket ticket2 = Ticket.builder()
                                .festival(festival2)
                                .user(user2).aantal(800)
                                .build();

                Ticket ticket3 = Ticket.builder()
                                .festival(festival3)
                                .user(user1)
                                .aantal(1)
                                .build();

                Ticket ticket4 = Ticket.builder()
                                .festival(festival4)
                                .user(user1)
                                .aantal(2)
                                .build();

                Ticket ticket5 = Ticket.builder()
                                .festival(festival5)
                                .user(user1)
                                .aantal(1)
                                .build();

                Ticket ticket6 = Ticket.builder()
                                .festival(festival6)
                                .user(user1)
                                .aantal(3)
                                .build();

                Ticket ticket7 = Ticket.builder()
                                .festival(festival7)
                                .user(user2)
                                .aantal(2)
                                .build();

                Ticket ticket8 = Ticket.builder()
                                .festival(festival8)
                                .user(user2)
                                .aantal(1)
                                .build();

                Ticket ticket9 = Ticket.builder()
                                .festival(festival9)
                                .user(user2)
                                .aantal(3)
                                .build();

                ticketRepository.saveAll(Arrays.asList(ticket1, ticket2, ticket3,
                                ticket4, ticket5, ticket6, ticket7, ticket8, ticket9));
        }
}
