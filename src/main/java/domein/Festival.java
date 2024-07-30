package domein;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "festivals")

public class Festival implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String naam;

  @Column(nullable = false)
  private LocalDate datum;

  @Column(nullable = false)
  private LocalTime aanvangsuur;

  @ManyToOne
  @JoinColumn(name = "regio_id", nullable = false)
  private Regio regio;

  @ManyToOne
  @JoinColumn(name = "genre_id", nullable = false)
  private Genre genre;

  @Column(nullable = false)
  private Integer capaciteit;

  @Column(nullable = false)
  private BigDecimal prijs;

  @Column(nullable = false)

  private int festivalNummer1;

  @Column(nullable = false)
  private int festivalNummer2;

}
