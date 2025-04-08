package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.util.Set;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class BakoCas extends Cas {
    private final Compte espece;
    private final Compte bniCompte;
    private final Compte bmoiCompte;

    public BakoCas() {
        super(
                LocalDate.of(2025, APRIL, 8),
                LocalDate.of(2025, DECEMBER, 31),
                new Personne("Bako"));
        espece = new Compte("Especes", LocalDate.of(2025, APRIL, 8), ariary(1_750_000));
        bniCompte = new Compte("BNI Compte", LocalDate.of(2025, APRIL, 8), ariary(2_000_000));
        bmoiCompte = new Compte("BMOI Compte", LocalDate.of(2025, APRIL, 8), ariary(625_000));
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected String nom() {
        return "Bako";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {
        var au08Avril2025 = LocalDate.of(2025, APRIL, 8);
        var au31Dec2025 = LocalDate.of(2025, DECEMBER, 31);
        var salaireNet =
                new FluxArgent(
                        "Salaire Net",
                        bniCompte,
                        au08Avril2025,
                        au08Avril2025,
                        2,
                        ariary(2_125_000));
        var epargneEntrant =
                new FluxArgent(
                        "Epargne Entrant",
                        bmoiCompte,
                        au08Avril2025,
                        au08Avril2025,
                        3,
                        ariary(200_000));
        var epargneSortant =
                new FluxArgent(
                        "Epargne Sortant",
                        bniCompte,
                        au08Avril2025,
                        au08Avril2025,
                        3,
                        ariary(-200_000));
        var loyer =
                new FluxArgent(
                        "Loyer",
                        bniCompte,
                        au08Avril2025,
                        au08Avril2025,
                        26,
                        ariary(-600_000));
        var trainDeVie =
                new FluxArgent(
                        "Vie Courant",
                        bniCompte,
                        au08Avril2025,
                        au08Avril2025,
                        1,
                        ariary(700_000));
        var pcPortable = new Materiel("Asus NoteBook", au08Avril2025.minusDays(10), au08Avril2025, ariary(3_000_000), -0.12);

        return Set.of(espece, bniCompte, bmoiCompte, salaireNet, epargneEntrant, epargneSortant, loyer, trainDeVie, pcPortable);
    }
}
