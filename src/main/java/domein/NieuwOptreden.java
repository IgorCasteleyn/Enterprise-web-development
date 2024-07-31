package domein;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import io.micrometer.common.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NieuwOptreden implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "Naam mag niet leeg zijn")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Naam mag enkel uit letters en spaties bestaan")
  private String naam;

  @NotNull(message = "Startuur mag niet leeg zijn")
  private LocalTime startuur;

  @Size(max = 2, message = "Maximum 2 subgenres mogen ingevuld worden")
  private List<SubGenre> subgenres;

  @Range(min = 1000, max = 9999, message = "Festival-nummer1 moet uit 4 cijfers bestaan tussen 1000 en 9999")
  private int festivalNummer1;

  @Min(value = 0, message = "Festival-nummer2 moet groter of gelijk zijn aan festival-nummer1")
  private int festivalNummer2;

  @Nullable
  private List<Integer> subgenreIds;
}
