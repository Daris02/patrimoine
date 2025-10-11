package school.hei.patrimoine.cas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import school.hei.patrimoine.cas.example.PatrimoineBakoAu8Avril25;
import school.hei.patrimoine.modele.Patrimoine;

@Slf4j
class PatrimoineDeBakoTest {

  private final PatrimoineBakoAu8Avril25 patrimoineBakoAu8Avril25 =
      new PatrimoineBakoAu8Avril25();

  private Patrimoine patrimoineDeBako31Dec2025() {
    return patrimoineBakoAu8Avril25.patrimoineDeBakoA31Decembre25();
  }

  @Test
  void bako_patrimoine_au_31_decembre_2025() {
    var patrimoineDeBakoAu31Dec2025 = patrimoineDeBako31Dec2025();

    assertEquals(ariary(8_599_479), patrimoineDeBakoAu31Dec2025.getValeurComptable());
  }
}
