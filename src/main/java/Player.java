/**
 * La classe <code>Player</code> représente le joueur dans notre jeu RPG.
 * Elle hérite de la classe abstraite <code>Character</code> et définit des attributs spécifiques,
 * tels que les compétences d'attaque et de défense, ainsi que les ressources du joueur (coins, élixirs, repos).
 * <p>
 * Les compétences sont gérées sous forme de tableaux, et le joueur peut améliorer ses compétences via la méthode <code>chooseSkills()</code>.
 * </p>
 *
 * @author VotreNom
 */
public class Player extends Character {
    /** Nombre maximum de points de vie pour le joueur. */
    public static final int MAX_HEALTH = 100;
    /** Expérience initiale du joueur. */
    public static final int EXPERIENCE = 0;
    /** Nombre de compétences initiales pour la défense. */
    public static final int SKILLS_NUMBER = 1;
    /** Valeur initiale des pièces du joueur. */
    public static final int COINS_INIT_VAL = 5;
    /** Valeur initiale des élixirs du joueur. */
    public static final int ELIXIRS_INIT_VAL = 0;
    /** Nombre initial de repos disponibles pour le joueur. */
    public static final int RESTS_INIT_VAL = 1;

    /** Tableau des compétences d'attaque disponibles. */
    public String[] attackSkills = {"Strength", "Power", "Might", "Godlike Strength"};
    /** Tableau des compétences de défense disponibles. */
    public String[] defenseSkills = {"Heavy Bones", "Stoneskin", "Scale Armor", "Holy Aura"};
    /** Nombre de compétences d'attaque actuellement acquises. */
    public int attackSkillsNumber;
    /** Nombre de compétences de défense actuellement acquises. */
    public int defenseSkillsNumber;
    /** Nombre d'élixirs possédés par le joueur. */
    public int elixirs;
    /** Nombre de pièces possédées par le joueur. */
    public int coins;
    /** Nombre de fois où le joueur peut se reposer. */
    public int rests;

    /**
     * Constructeur de la classe <code>Player</code>.
     * Initialise le joueur avec un nom, des points de vie maximaux et une expérience initiale,
     * puis définit les ressources de départ (pièces, élixirs, repos) et les compétences de base.
     *
     * @param name le nom du joueur
     */
    public Player(String name) {
        // Appel au constructeur de la classe mère pour initialiser le nom, les points de vie et l'expérience
        super(name, MAX_HEALTH, EXPERIENCE);
        this.attackSkillsNumber = 0;
        this.defenseSkillsNumber = SKILLS_NUMBER;
        // Initialisation des ressources du joueur
        this.coins = COINS_INIT_VAL;
        this.elixirs = ELIXIRS_INIT_VAL;
        this.rests = RESTS_INIT_VAL;
    }

    /**
     * Calcule et retourne la valeur d'attaque du joueur.
     * La valeur d'attaque est déterminée aléatoirement en fonction de l'expérience du joueur
     * et du nombre de compétences d'attaque acquises, ainsi que d'un bonus basé sur les compétences de défense.
     *
     * @return la valeur d'attaque du joueur
     */
    @Override
    public int attack() {
        return (int) (Math.random() * ((double) this.getExperience() / 4 + attackSkillsNumber * 3 + 3)
                + (double) this.getExperience() / 10 + attackSkillsNumber * 2 + defenseSkillsNumber + 1);
    }

    /**
     * Calcule et retourne la valeur de défense du joueur.
     * La valeur de défense est déterminée aléatoirement en fonction de l'expérience du joueur
     * et du nombre de compétences de défense acquises, avec un bonus prenant également en compte les compétences d'attaque.
     *
     * @return la valeur de défense du joueur
     */
    @Override
    public int defend() {
        return (int) (Math.random() * ((double) this.getExperience() / 4 + defenseSkillsNumber * 3 + 3)
                + (double) this.getExperience() / 10 + defenseSkillsNumber * 2 + attackSkillsNumber + 1);
    }

    /**
     * Permet au joueur de choisir une compétence à améliorer.
     * Affiche un menu proposant soit une compétence d'attaque, soit une compétence de défense,
     * puis incrémente le nombre de compétences correspondantes en fonction du choix effectué.
     */
    public void chooseSkills() {
        GameLogic.cleanOutput(true);
        GameLogic.mainMessage("Veuillez choisir une compétence :", null, true);
        System.out.println("[1] " + this.attackSkills[this.attackSkillsNumber]);
        System.out.println("[2] " + this.defenseSkills[this.defenseSkillsNumber]);
        int choice = GameLogic.readChoiceInt(">>> ", 2);
        GameLogic.cleanOutput(true);
        switch (choice) {
            case 1:
                GameLogic.mainMessage("Vous avez acquis la compétence « " + this.attackSkills[this.attackSkillsNumber] + " ».", null, true);
                this.attackSkillsNumber++;
                break;
            case 2:
                GameLogic.mainMessage("Vous avez acquis la compétence « " + this.defenseSkills[this.defenseSkillsNumber] + " ».", null, true);
                this.defenseSkillsNumber++;
                break;
            default:
                break;
        }
        GameLogic.waitForInput();
    }
}
