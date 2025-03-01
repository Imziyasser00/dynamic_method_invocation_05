/**
 * La classe <code>Enemy</code> représente un ennemi dans le jeu RPG.
 * Un ennemi est généré en fonction de l'expérience du joueur pour adapter sa difficulté.
 * Cette classe hérite de la classe abstraite <code>Character</code> et implémente ses méthodes d'attaque et de défense.
 *
 * @author VotreNom
 */
public class Enemy extends Character {
    private int playerExperience;

    /**
     * Constructeur de la classe <code>Enemy</code>.
     * Les points de vie et l'expérience de l'ennemi sont générés de façon aléatoire
     * en fonction de l'expérience du joueur pour calibrer la difficulté.
     *
     * @param name le nom de l'ennemi
     * @param playerExperience l'expérience du joueur, servant à ajuster les caractéristiques de l'ennemi
     */
    public Enemy(String name, int playerExperience) {
        // Appel au constructeur de la classe mère pour initialiser le nom, les points de vie et l'expérience
        super(name, (int) (Math.random() * playerExperience + playerExperience / 3.0 + 5),
                (int) (Math.random() * (playerExperience / 4.0 + 2) + 1));
        this.playerExperience = playerExperience;
    }

    /**
     * Retourne l'expérience du joueur utilisée pour calibrer cet ennemi.
     *
     * @return l'expérience du joueur associée à cet ennemi
     */
    public int getPlayerExperience() {
        return playerExperience;
    }

    /**
     * Met à jour l'expérience du joueur utilisée pour calibrer cet ennemi.
     * Si la valeur fournie est négative, l'expérience est fixée à zéro.
     *
     * @param playerExperience la nouvelle valeur de l'expérience du joueur
     */
    public void setPlayerExperience(int playerExperience) {
        if (playerExperience < 0) {
            this.playerExperience = 0;
        } else {
            this.playerExperience = playerExperience;
        }
    }

    /**
     * Calcule et retourne la valeur d'attaque de l'ennemi.
     * La valeur est déterminée aléatoirement en fonction de l'expérience du joueur et de l'expérience de l'ennemi.
     *
     * @return la valeur d'attaque de l'ennemi
     */
    @Override
    public int attack() {
        return (int) (Math.random() * (playerExperience / 4.0 + 1) + this.getExperience() / 4.0 + 3);
    }

    /**
     * Calcule et retourne la valeur de défense de l'ennemi.
     * La valeur est déterminée aléatoirement en fonction de l'expérience du joueur et de l'expérience de l'ennemi.
     *
     * @return la valeur de défense de l'ennemi
     */
    @Override
    public int defend() {
        return (int) (Math.random() * (playerExperience / 4.0 + 1) + this.getExperience() / 4.0 + 3);
    }
}
