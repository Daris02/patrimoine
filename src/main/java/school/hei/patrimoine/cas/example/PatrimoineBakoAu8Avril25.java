package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

public class PatrimoineBakoAu8Avril25 implements Supplier<Patrimoine> {

    @Override
    public Patrimoine get() {
        return new BakoCas().patrimoine();
    }

    public Patrimoine patrimoineDeBakoA31Decembre25() {
        LocalDate AU_31_DECEMBRE_2025 = LocalDate.of(2025, DECEMBER, 18);
        return Patrimoine.of(
                "bako au 31 December 2025",
                MGA,
                AU_31_DECEMBRE_2025,
                new Personne("Bako"),
                possessionsAu8Avril25())
            .projectionFuture(AU_31_DECEMBRE_2025);
    }
    public Set<Possession> possessionsAu8Avril25() {
        LocalDate au8avril25 = LocalDate.of(2025, APRIL, 8);

        var compteBNI =
            new Compte("Compte BNI", au8avril25, ariary(1_000_000));
        var salaire =
            new FluxArgent(
                "Contrat de travail",
                compteBNI,
                au8avril25.minusDays(100),
                au8avril25.plusDays(100),
                2,
                ariary(2_125_000));
        var compteEpargne =
            new Compte("Compte épargne BMOI", au8avril25, ariary(625_000));
        var coffreFort =
            new Compte("Coffre fort maison", au8avril25, ariary(1_750_000));
        var virement1 =
            new FluxArgent(
                "Virement mensuel 1",
                compteBNI,
                au8avril25.minusDays(100),
                au8avril25.plusDays(100),
                3,
                ariary(-200_000));
        var virement2 =
            new FluxArgent(
                "Virement mensuel 2",
                compteEpargne,
                au8avril25.minusDays(100),
                au8avril25.plusDays(100),
                3,
                ariary(200_000));
        var colocation =
            new FluxArgent(
                "Colocation",
                compteBNI,
                au8avril25.minusDays(100),
                au8avril25.plusDays(100),
                26,
                ariary(-600_000));
        var trainDeVie =
            new FluxArgent(
                "Vie courante",
                compteBNI,
                au8avril25.minusDays(100),
                au8avril25.plusDays(100),
                1,
                ariary(-700_000));
        var ordi = new Materiel("Ordinateur Portable", au8avril25.minusDays(3), au8avril25, ariary(3_000_000), -0.12);

        return Set.of(
            compteBNI,
            salaire,
            compteEpargne,
            coffreFort,
            virement1,
            virement2,
            colocation,
            trainDeVie,
            ordi
        );
    }
            
}
