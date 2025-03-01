import java.util.ArrayList;

/**
 * La classe <code>Story</code> représente l'histoire du jeu.
 * Elle gère la progression des niveaux et stocke l'ensemble des niveaux disponibles,
 * ainsi que le niveau supérieur (topLevel) qui introduit l'histoire générale.
 * <p>
 * La classe fournit également des méthodes statiques pour afficher les différentes parties narratives
 * (intro, outro et introductions/épilogues des niveaux).
 * </p>
 *
 * Note : Dans le second constructeur, il est supposé que la collection <code>levels</code> permet l'accès
 * aux premiers éléments via <code>getFirst()</code> et <code>removeFirst()</code>. Vous pouvez adapter ce code
 * en utilisant par exemple une <code>LinkedList</code> si nécessaire.
 *
 * @author VotreNom
 */
public class Story {
    /** Constante représentant le dernier niveau (niveau 4). */
    public static final int LAST_LEVEL = 4;

    /** Le niveau supérieur qui introduit l'histoire générale. */
    private Level topLevel;

    /** La liste des niveaux disponibles dans l'histoire. */
    private ArrayList<Level> levels;

    /** L'indice du niveau courant dans la liste des niveaux. */
    private int currentLevel = 0;

    /** Le nombre total de niveaux disponibles. */
    private int levelsNumber;

    /**
     * Constructeur de la classe <code>Story</code> avec un niveau supérieur défini.
     *
     * @param topLevel le niveau supérieur introduisant l'histoire
     * @param levels la liste des niveaux suivants
     */
    public Story(Level topLevel, ArrayList<Level> levels) {
        this.topLevel = topLevel;
        this.levels = levels;
        this.levelsNumber = levels.size();
    }

    /**
     * Constructeur de la classe <code>Story</code> sans niveau supérieur explicite.
     * Le premier élément de la liste est extrait pour servir de niveau supérieur, et le reste constitue la suite des niveaux.
     *
     * @param levels la liste des niveaux, dont le premier sera utilisé comme niveau supérieur
     */
    public Story(ArrayList<Level> levels) {
        // Ici, on suppose que la liste possède les méthodes getFirst() et removeFirst().
        // Si vous utilisez une ArrayList, pensez à adapter en utilisant get(0) et remove(0).
        this.topLevel = levels.get(0); // Remplacer getFirst() par get(0) si nécessaire
        levels.remove(0);             // Remplacer removeFirst() par remove(0)
        this.levels = levels;
        this.levelsNumber = levels.size();
    }

    /**
     * Retourne le niveau supérieur de l'histoire.
     *
     * @return le niveau supérieur
     */
    public Level getTopLevel() {
        return topLevel;
    }

    /**
     * Met à jour le niveau supérieur de l'histoire.
     *
     * @param topLevel le nouveau niveau supérieur
     */
    public void setTopLevel(Level topLevel) {
        this.topLevel = topLevel;
    }

    /**
     * Retourne la liste des niveaux de l'histoire.
     *
     * @return la liste des niveaux
     */
    public ArrayList<Level> getLevels() {
        return levels;
    }

    /**
     * Met à jour la liste des niveaux de l'histoire.
     *
     * @param levels la nouvelle liste des niveaux
     */
    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    /**
     * Retourne le nombre total de niveaux disponibles dans l'histoire.
     *
     * @return le nombre de niveaux
     */
    public int getLevelsNumber() {
        return this.levelsNumber;
    }

    /**
     * Retourne l'indice du niveau courant.
     *
     * @return le numéro du niveau courant
     */
    public int getCurrentLevelNumber() {
        return this.currentLevel;
    }

    /**
     * Retourne le niveau courant.
     *
     * @return le niveau courant de l'histoire
     */
    public Level getCurrentLevel() {
        return this.levels.get(this.currentLevel);
    }

    /**
     * Passe au niveau suivant.
     * Incrémente l'indice du niveau courant et retourne <code>true</code> si le changement a été effectué.
     *
     * @return <code>true</code> si le niveau suivant est accessible, <code>false</code> sinon
     */
    public boolean nextLevel() {
        return (this.currentLevel++ < this.levelsNumber);
    }

    /**
     * Vérifie si le niveau courant est le dernier niveau de l'histoire.
     *
     * @return <code>true</code> si le niveau courant est le dernier, <code>false</code> sinon
     */
    public boolean isLastLevel() {
        return this.currentLevel == this.levelsNumber;
    }

    /**
     * Affiche l'introduction générale de l'histoire.
     * Cette méthode efface l'écran, affiche un en-tête, un texte narratif et attend une saisie de l'utilisateur.
     */
    public static void printIntro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Histoire");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'outro générale de l'histoire (victoire).
     * Efface l'écran, affiche un en-tête et un texte narratif de conclusion.
     */
    public static void printOutro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Victoire");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
    }

    /**
     * Affiche l'introduction du premier niveau.
     * Efface l'écran, affiche le titre et un texte narratif d'introduction pour le niveau 1,
     * puis attend une saisie de l'utilisateur.
     */
    public static void printFirstLevelIntro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 1 - Prologue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'épilogue du premier niveau.
     * Efface l'écran, affiche le titre et un texte narratif de conclusion pour le niveau 1,
     * puis attend une saisie de l'utilisateur.
     */
    public static void printFirstLevelOutro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 1 - Épilogue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'introduction du deuxième niveau.
     */
    public static void printSecondLevelIntro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 2 - Prologue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'épilogue du deuxième niveau.
     */
    public static void printSecondLevelOutro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 2 - Épilogue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'introduction du troisième niveau.
     */
    public static void printThirdLevelIntro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 3 - Prologue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'épilogue du troisième niveau.
     */
    public static void printThirdLevelOutro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 3 - Épilogue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'introduction du quatrième niveau.
     */
    public static void printFourthLevelIntro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 4 - Prologue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }

    /**
     * Affiche l'épilogue du quatrième niveau.
     */
    public static void printFourthLevelOutro() {
        GameLogic.cleanOutput(true);
        GameLogic.printLine(50, null);
        System.out.println("Niveau 4 - Épilogue");
        GameLogic.printLine(50, null);
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        System.out.println("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
        System.out.println("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur ?");
        GameLogic.waitForInput();
    }
}
