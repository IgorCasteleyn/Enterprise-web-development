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
            errors.rejectValue("startuur", "Validation.startuurNull");
        } else {
            if (startuur.getMinute() != 0 || startuur.getHour() < 10 || startuur.getHour() > 23) {
                errors.rejectValue("startuur", "Validation.startuurInvalid");
            } else if (optredenRepository.findByStartuur(startuur) != null) {
                errors.rejectValue("startuur", "Validation.startuurBezet");
            }
        }

        if (subgenres != null) {
            if (subgenres.size() > 2) {
                errors.rejectValue("subgenres", "Validation.subgenresSize");
            } else if (subgenres.size() == 2 && subgenres.get(0).equals(subgenres.get(1))) {
                errors.rejectValue("subgenres", "Validation.subgenresDuplicate");
            }
        }

        if (festivalNummer1 < 1000 || festivalNummer1 > 9999) {
            errors.rejectValue("festivalNummer1", "Validation.festivalNummer1Invalid");
        } else if (festivalNummer1 % 3 != 0) {
            errors.rejectValue("festivalNummer1", "Validation.festivalNummer1Multiple");
        }

        if (festivalNummer2 < festivalNummer1 || festivalNummer2 > festivalNummer1 + 1000) {
            errors.rejectValue("festivalNummer2", "Validation.festivalNummer2Invalid");
        }
    }
}
