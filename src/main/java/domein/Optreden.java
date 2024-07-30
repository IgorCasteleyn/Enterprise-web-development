package domein;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "optredens")

public class Optreden implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String naam;

  @Column(nullable = false)
  private LocalTime startuur;

  @ManyToMany
  private List<SubGenre> subgenres;

  @Column(nullable = false)
  private int festivalNummer1;

  @Column(nullable = false)
  private int festivalNummer2;
}
