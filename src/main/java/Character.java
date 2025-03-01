/**
 * La classe <code>Character</code> est une classe abstraite qui représente un personnage dans le jeu.
 * Elle définit les attributs communs à tous les personnages (nom, points de vie, points d'expérience et coins)
 * et impose le contrat pour les actions de combat, de défense et d'attaque.
 * <p>
 * Cette classe sert de base aux classes <code>Player</code> et <code>Enemy</code>.
 * </p>
 *
 * @author VotreNom
 */
public abstract class Character {
    // Modes de combat (non utilisés directement dans cette version, mais réservés à une extension éventuelle)
    private final String BATTLE_MODE = "1";
    private final String ESCAPE_MODE = "2";
    private final String REST_MODE = "3";
    private final String PURCHASE_MODE = "4";

    private String name;
    private int health;
    private int maxHealth;
    private int experience;
    protected int coins; // Utilisé pour le système d'achats

    /**
     * Constructeur initialisant un personnage avec un nom, une santé maximale et un niveau d'expérience initial.
     *
     * @param name le nom du personnage
     * @param maxHealth le nombre de points de vie maximum du personnage
     * @param experience le niveau d'expérience initial du personnage
     */
    public Character(String name, int maxHealth, int experience) {
        this.name = name;
        this.health = this.maxHealth = maxHealth;
        this.experience = experience;
    }

    /**
     * Retourne le nom du personnage.
     *
     * @return le nom du personnage
     */
    public String getName() {
        return name;
    }

    /**
     * Met à jour le nom du personnage.
     *
     * @param name le nouveau nom du personnage
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le nombre de points de vie actuels du personnage.
     *
     * @return les points de vie actuels
     */
    public int getHealth() {
        return health;
    }

    /**
     * Définit le nombre de points de vie actuels du personnage.
     *
     * @param health le nouveau nombre de points de vie
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Réduit les points de vie du personnage d'un nombre de points donné.
     *
     * @param points le nombre de points à soustraire aux points de vie
     */
    public void decreaseHealth(int points) {
        this.health -= points;
    }

    /**
     * Augmente les points de vie du personnage d'un nombre de points donné.
     *
     * @param points le nombre de points à ajouter aux points de vie
     */
    public void increaseHealth(int points) {
        this.health += points;
    }

    /**
     * Retourne le nombre maximum de points de vie du personnage.
     *
     * @return le nombre maximum de points de vie
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Définit le nombre maximum de points de vie du personnage.
     *
     * @param maxHealth le nouveau nombre maximum de points de vie
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Retourne le niveau d'expérience actuel du personnage.
     *
     * @return le niveau d'expérience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Met à jour le niveau d'expérience du personnage.
     *
     * @param experience le nouveau niveau d'expérience
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Réduit l'expérience du personnage d'un nombre de points donné.
     *
     * @param points le nombre de points d'expérience à soustraire
     */
    public void decreaseExperience(int points) {
        this.experience -= points;
    }

    /**
     * Augmente l'expérience du personnage d'un nombre de points donné.
     *
     * @param points le nombre de points d'expérience à ajouter
     */
    public void increaseExperience(int points) {
        this.experience += points;
    }

    /**
     * Vérifie si le personnage est mort (c'est-à-dire si ses points de vie sont inférieurs ou égaux à zéro).
     *
     * @return <code>true</code> si le personnage est mort, <code>false</code> sinon
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Méthode abstraite qui définit l'attaque du personnage.
     * Chaque sous-classe doit fournir sa propre implémentation de l'attaque.
     *
     * @return la valeur de l'attaque
     */
    public abstract int attack();

    /**
     * Méthode abstraite qui définit la défense du personnage.
     * Chaque sous-classe doit fournir sa propre implémentation de la défense.
     *
     * @return la valeur de la défense
     */
    public abstract int defend();

    /**
     * Permet au personnage de se reposer et de récupérer des points de vie.
     * Cette méthode affiche un message et augmente les points de vie du personnage.
     *
     * @return <code>true</code> une fois le repos effectué
     */
    public boolean rest() {
        System.out.println(this.name + " se repose...");
        increaseHealth(maxHealth / 2);
        return true;
    }

    /**
     * Permet au personnage d'acheter un objet si les coins sont suffisants.
     * Le coût de l'objet est déduit des coins du personnage.
     *
     * @param item le nom de l'objet à acheter
     * @param cost le coût de l'objet en coins
     * @return <code>true</code> si l'achat est réussi, <code>false</code> sinon
     */
    public boolean purchase(String item, int cost) {
        if (this.coins >= cost) {
            this.coins -= cost;
            System.out.println(this.name + " a acheté " + item + " pour " + cost + " coins.");
            return true;
        } else {
            System.out.println("Coins insuffisants pour acheter " + item + ".");
            return false;
        }
    }

    /**
     * Retourne une chaîne de caractères contenant les statistiques du personnage.
     * Le format inclut le nom, les points de vie, l'expérience, les compétences, les équipements et la position.
     *
     * @return une chaîne formatée avec les informations du personnage
     */
    @Override
    public String toString() {
        String statsTemplate = """
                ****************************************
                ***** %s
                ****************************************
                * HP : %d / %d
                * XP : %d
                * Compétences : %s
                * Équipements : %s
                * Position: [X:%d, Y:%d]
                ****************************************
                """;
        return String.format(statsTemplate, this.name, this.health, this.maxHealth, this.experience,
                "à implémenter", "à implémenter", 0, 0);
    }
}
