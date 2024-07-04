package school.hei.patrimoine.modele;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.util.Set;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatrimoineZetyTest {

    @Test
    void patrimoine_zety_evaluation() {
        var zety = new Personne("Zety");
        var au03jul2024 = LocalDate.of(2024, JULY, 3);

        var ordi = new Materiel(
                "Ordinateur",
                au03jul2024,
                1_200_000,
                au03jul2024.minusDays(1),
                -0.10);
        var vetments = new Materiel(
                "Vetments",
                au03jul2024,
                1_500_000,
                au03jul2024.minusDays(30),
                -0.50);

        var argentEspeces = new Argent("Especes", au03jul2024, 800_000);
        var debutAnneeScolaire = LocalDate.of(2023, NOVEMBER, 1);
        var finAnneeScolaire =  LocalDate.of(2024, AUGUST, 30);
        var fraisDeScolarite = new FluxArgent(
                "Frais de scolarité pour 2023-2024",
                argentEspeces, debutAnneeScolaire, finAnneeScolaire, -200_000,
                27);

        var compteBancaire = new Argent("Compte bancaire", au03jul2024, 100_000);
        var fraisDeTenue = new FluxArgent(
                "Frais de tenue du compte bancaire",
                compteBancaire, au03jul2024, LocalDate.of(2054, JULY, 1), -20_000,
                25);

        var patrimoineZetyAu3jul2024 = new Patrimoine(
                "patrimoineZetyAu3jul2024",
                zety,
                au03jul2024,
                Set.of(new GroupePossession("Le groupe", au03jul2024, Set.of(ordi, vetments, argentEspeces, compteBancaire))));

        var au17Sept2024 = LocalDate.of(2024, SEPTEMBER, 17);
        assertTrue(patrimoineZetyAu3jul2024.projectionFuture(au17Sept2024).getValeurComptable() < 3_600_000);
        assertEquals(2_978_848, patrimoineZetyAu3jul2024.projectionFuture(au17Sept2024).getValeurComptable());
    }

    @Test
    void patrimoine_zety_avec_dette() {
        var zety = new Personne("Zety");
        var au03jul2024 = LocalDate.of(2024, JULY, 3);

        var ordi = new Materiel(
                "Ordinateur",
                au03jul2024,
                1_200_000,
                au03jul2024.minusDays(1),
                -0.10);
        var vetments = new Materiel(
                "Vetments",
                au03jul2024,
                1_500_000,
                au03jul2024.minusDays(30),
                -0.50);

        var argentEspeces = new Argent("Especes", au03jul2024, 800_000);
        var debutAnneeScolaire = LocalDate.of(2023, NOVEMBER, 1);
        var finAnneeScolaire =  LocalDate.of(2024, AUGUST, 30);
        var fraisDeScolarite = new FluxArgent(
                "Frais de scolarité pour 2023-2024",
                argentEspeces, debutAnneeScolaire, finAnneeScolaire, -200_000,
                27);

        var compteBancaire = new Argent("Compte bancaire", au03jul2024, 100_000);
        var fraisDeTenue = new FluxArgent(
                "Frais de tenue du compte bancaire",
                compteBancaire, au03jul2024, LocalDate.of(2054, JULY, 1), -20_000,
                25);

        var dateDEmprunt = LocalDate.of(2024, SEPTEMBER, 18);
        var dateDeRendu =  LocalDate.of(2025, SEPTEMBER, 18);
        var dette = new Dette(
                "Dette du frais de scolarite 2024-2025",
                dateDEmprunt,
                -10_000_000);
        var dettePour2024_2025 = new FluxArgent(
                "Dette du frais de scolarite 2024-2025",
                compteBancaire, dateDEmprunt, dateDeRendu, 1_000_000,
                18);

        var patrimoineZetyAu3jul2024 = new Patrimoine(
                "patrimoineZetyAu3jul2024",
                zety,
                au03jul2024,
                Set.of(new GroupePossession("Le groupe", au03jul2024, Set.of(ordi, vetments, argentEspeces, compteBancaire, dette))));

        var au17Sept2024 = LocalDate.of(2024, SEPTEMBER, 17);
        var au18Sept2024 = LocalDate.of(2024, SEPTEMBER, 18);
        assertEquals(9_002_384,
                patrimoineZetyAu3jul2024.projectionFuture(au17Sept2024).getValeurComptable()-
                patrimoineZetyAu3jul2024.projectionFuture(au18Sept2024).getValeurComptable());
    }

    @Test
    void patrimoine_zety_avec_don() {
        var zety = new Personne("Zety");
        var au03jul2024 = LocalDate.of(2024, JULY, 3);

        var argentEspeces = new Argent("Especes", au03jul2024, 800_000);
        var debutAnneeScolaire = LocalDate.of(2023, NOVEMBER, 1);
        var finAnneeScolaire =  LocalDate.of(2024, AUGUST, 30);
        var fraisDeScolarite = new FluxArgent(
                "Frais de scolarité pour 2023-2024",
                argentEspeces, debutAnneeScolaire, finAnneeScolaire, -200_000,
                27);

        var debutAnnee2024 = LocalDate.of(2024, JANUARY, 1);
        var donDesParents = new FluxArgent(
                "Transfert de don des parents",
                argentEspeces, debutAnnee2024, LocalDate.of(2054, JANUARY, 1), 100_000,
                15);

        var au01Oct2024 = LocalDate.of(2024, OCTOBER, 1);
        var au13Fev2025 = LocalDate.of(2025, FEBRUARY, 13);
        var trainDevieZety = new FluxArgent(
                "Train de vie de Zety",
                argentEspeces, au01Oct2024, au13Fev2025, -250_000,
                1);

        assertEquals(LocalDate.of(2025, 1, 1), argentEspeces.finFinancements(0));
    }

    @Test
    void patrimoine_zety_en_allemagne() {

        var zety = new Personne("Zety");
        var au03jul2024 = LocalDate.of(2024, JULY, 3);

        var ordi = new Materiel(
                "Ordinateur",
                au03jul2024,
                1_200_000,
                au03jul2024.minusDays(1),
                -0.10);
        var vetments = new Materiel(
                "Vetments",
                au03jul2024,
                1_500_000,
                au03jul2024.minusDays(30),
                -0.50);

        var argentEspeces = new Argent("Especes", au03jul2024, 800_000);
        var debutAnneeScolaire = LocalDate.of(2023, NOVEMBER, 1);
        var finAnneeScolaire =  LocalDate.of(2024, AUGUST, 30);
        var fraisDeScolarite = new FluxArgent(
                "Frais de scolarité pour 2023-2024",
                argentEspeces, debutAnneeScolaire, finAnneeScolaire, -200_000,
                27);

        var compteBancaire = new Argent("Compte bancaire", au03jul2024, 100_000);
        var fraisDeTenue = new FluxArgent(
                "Frais de tenue du compte bancaire",
                compteBancaire, au03jul2024, LocalDate.of(2054, JULY, 1), -20_000,
                25);

        var au21Sept2024 = LocalDate.of(2024, SEPTEMBER, 21);
        var fraisDeScolariteEnUneFois = new FluxArgent(
                "Frais de scolarite 2024-2025",
                compteBancaire, au21Sept2024, au21Sept2024, -2_500_000,
                21);

        var debutAnnee2024 = LocalDate.of(2024, JANUARY, 1);
        var donDesParents = new FluxArgent(
                "Transfert de don des parents",
                argentEspeces, debutAnnee2024, LocalDate.of(2054, JANUARY, 1), 100_000,
                15);

        var au01Oct2024 = LocalDate.of(2024, OCTOBER, 1);
        var au13Fev2025 = LocalDate.of(2025, FEBRUARY, 13);
        var trainDevieZety = new FluxArgent(
                "Train de vie de Zety",
                argentEspeces, au01Oct2024, au13Fev2025, -250_000,
                1);

        var dateDEmprunt = LocalDate.of(2024, SEPTEMBER, 18);
        var dateDeRendu =  LocalDate.of(2025, SEPTEMBER, 18);
        var dette = new Dette(
                "Dette du frais de scolarite 2024-2025",
                dateDEmprunt,
                -10_000_000);
        var dettePour2024_2025 = new FluxArgent(
                "Dette du frais de scolarite 2024-2025",
                compteBancaire, dateDEmprunt, dateDeRendu, -1_000_000,
                18);

        var patrimoineZetyAu3jul2024 = new Patrimoine(
                "patrimoineZetyAu3jul2024",
                zety,
                au03jul2024,
                Set.of(new GroupePossession("Le groupe", au03jul2024, Set.of(ordi, vetments, argentEspeces, compteBancaire))));

        var au14Fev2025 = LocalDate.of(2025, FEBRUARY, 14);
        assertEquals(5528686, -patrimoineZetyAu3jul2024.projectionFuture(au14Fev2025).getValeurComptable());
    }

    @Test
    void patrimoine_zety_en_allemagne_avec_dette() {

        var zety = new Personne("Zety");
        var au03jul2024 = LocalDate.of(2024, JULY, 3);

        var ordi = new Materiel(
                "Ordinateur",
                au03jul2024,
                1_200_000,
                au03jul2024.minusDays(1),
                -0.10);
        var vetments = new Materiel(
                "Vetments",
                au03jul2024,
                1_500_000,
                au03jul2024.minusDays(30),
                -0.50);

        var argentEspeces = new Argent("Especes", au03jul2024, 800_000);
        var debutAnneeScolaire = LocalDate.of(2023, NOVEMBER, 1);
        var finAnneeScolaire =  LocalDate.of(2024, AUGUST, 30);
        var fraisDeScolarite = new FluxArgent(
                "Frais de scolarité pour 2023-2024",
                argentEspeces, debutAnneeScolaire, finAnneeScolaire, -200_000,
                27);

        var compteBancaire = new Argent("Compte bancaire", au03jul2024, 100_000);
        var fraisDeTenue = new FluxArgent(
                "Frais de tenue du compte bancaire",
                compteBancaire, au03jul2024, LocalDate.of(2054, JULY, 1), -20_000,
                25);

        var au21Sept2024 = LocalDate.of(2024, SEPTEMBER, 21);
        var fraisDeScolariteEnUneFois = new FluxArgent(
                "Frais de scolarite 2024-2025",
                compteBancaire, au21Sept2024, au21Sept2024, -2_500_000,
                21);

        var debutAnnee2024 = LocalDate.of(2024, JANUARY, 1);
        var donDesParents = new FluxArgent(
                "Transfert de don des parents",
                argentEspeces, debutAnnee2024, LocalDate.of(2054, JANUARY, 1), 100_000,
                15);

        var au01Oct2024 = LocalDate.of(2024, OCTOBER, 1);
        var au13Fev2025 = LocalDate.of(2025, FEBRUARY, 13);
        var trainDevieZety = new FluxArgent(
                "Train de vie de Zety",
                argentEspeces, au01Oct2024, au13Fev2025, -250_000,
                1);

        var dateDEmprunt = LocalDate.of(2024, SEPTEMBER, 18);
        var dateDeRendu =  LocalDate.of(2025, SEPTEMBER, 18);
        var dette = new Dette(
                "Dette du frais de scolarite 2024-2025",
                dateDEmprunt,
                -10_000_000);
        var dettePour2024_2025 = new FluxArgent(
                "Dette du frais de scolarite 2024-2025",
                compteBancaire, dateDEmprunt, dateDeRendu, -1_000_000,
                18);

        var au15Fev2025 = LocalDate.of(2025, FEBRUARY, 15);
        var detteDeutscheBank = new Dette(
                "Dette au DeutscheBank 2025",
                au15Fev2025,
                new DeviseValue(-7000, Devise.EURO).getValeurAriary());

        var patrimoineZetyAu3jul2024 = new Patrimoine(
                "patrimoineZetyAu3jul2024",
                zety,
                au03jul2024,
                Set.of(new GroupePossession("Le groupe", au03jul2024, Set.of(ordi, vetments, argentEspeces, compteBancaire, dette, detteDeutscheBank))));

        assertEquals(49178070, -patrimoineZetyAu3jul2024.projectionFuture(au15Fev2025).getValeurComptable());
    }
}
