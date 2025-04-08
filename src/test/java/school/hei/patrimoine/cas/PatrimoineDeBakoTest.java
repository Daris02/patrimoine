package school.hei.patrimoine.cas;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.BakoCas;
import school.hei.patrimoine.cas.example.PatrimoineZetyAu3Juillet2024;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class PatrimoineDeBakoTest {
    private final BakoCas bakoCas = new BakoCas();

    @Test
    void patrimoine_de_bako_au_31_decembre_2025() {
        var patrimoineDu08Avril2025 = bakoCas.patrimoine();
        var patrimoineDu31Decembre2025 = bakoCas.patrimoine().projectionFuture(LocalDate.of(2025, Month.DECEMBER, 31));

        assertNotNull(patrimoineDu31Decembre2025.getValeurComptable());
        assertNotEquals(patrimoineDu31Decembre2025.getValeurComptable(), patrimoineDu08Avril2025.getValeurComptable());
        assertTrue(Double.parseDouble(patrimoineDu31Decembre2025.getValeurComptable().ppMontant()) < Double.parseDouble(patrimoineDu08Avril2025.getValeurComptable().ppMontant()));
    }
}
