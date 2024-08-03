package validator;

import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import repository.OptredenRepository;
import domein.NieuwOptreden;
import domein.SubGenre;

public class NieuwOptredenValidation implements Validator {

    @Autowired
    private OptredenRepository optredenRepository;

    @Override
    public boolean supports(Class<?> klass) {
        return NieuwOptreden.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NieuwOptreden nieuwOptreden = (NieuwOptreden) target;

        String naam = nieuwOptreden.getNaam();
        LocalTime startuur = nieuwOptreden.getStartuur();
        List<SubGenre> subgenres = nieuwOptreden.getSubgenres();
        int festivalNummer1 = nieuwOptreden.getFestivalNummer1();
        int festivalNummer2 = nieuwOptreden.getFestivalNummer2();

        if (naam == null || naam.isBlank()) {
            errors.rejectValue("naam", "Validation.naamNull");
        } else if (!naam.matches("^[a-zA-Z ]+$")) {
            errors.rejectValue("naam", "Validation.naamInvalid");
        }

        if (startuur == null) {
            errors.rejectValue("startuur", "Validation.startuurNull", "Startuur mag niet leeg zijn");
        } else {
            if (startuur.getMinute() != 0 || startuur.getHour() < 10 || startuur.getHour() > 23) {
                errors.rejectValue("startuur", "Validation.startuurInvalid",
                        "Startuur moet tussen 10:00 en 23:00 liggen en de minuten moeten 00 zijn");
            } else if (optredenRepository.findByStartuur(startuur) != null) {
                errors.rejectValue("startuur", "Validation.startuurBezet",
                        "Het gekozen uur is al bezet door een ander optreden");
            }
        }

        if (subgenres != null) {
            if (subgenres.size() > 2) {
                errors.rejectValue("subgenres", "Validation.subgenresSize",
                        "Maximum 2 subgenres mogen ingevuld worden");
            } else if (subgenres.size() == 2 && subgenres.get(0).equals(subgenres.get(1))) {
                errors.rejectValue("subgenres", "Validation.subgenresDuplicate", "Twee dezelfde genres mogen niet");
            }
        }

        if (festivalNummer1 < 1000 || festivalNummer1 > 9999) {
            errors.rejectValue("festivalNummer1", "Validation.festivalNummer1Invalid",
                    "Festival-nummer1 moet uit 4 cijfers bestaan tussen 1000 en 9999");
        } else if (festivalNummer1 % 3 != 0) {
            errors.rejectValue("festivalNummer1", "Validation.festivalNummer1Multiple",
                    "Festival-nummer1 moet een veelvoud van 3 zijn");
        }

        if (festivalNummer2 < festivalNummer1 || festivalNummer2 > festivalNummer1 + 1000) {
            errors.rejectValue("festivalNummer2", "Validation.festivalNummer2Invalid",
                    "Festival-nummer2 moet groter of gelijk zijn aan festival-nummer1 en niet hoger dan festival-nummer1 + 1000");
        }
    }
}
