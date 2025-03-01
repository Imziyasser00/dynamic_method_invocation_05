import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe <code>Menu</code> représente un menu interactif dans le jeu.
 * Elle gère l'affichage d'un ensemble d'options (menu items) et l'exécution de l'action associée
 * au choix de l'utilisateur.
 * <p>
 * Le menu est constitué d'un titre, d'une liste d'objets <code>MenuItem</code> et d'un choix par défaut.
 * Les MenuItem désactivés sont automatiquement retirés de la liste.
 * </p>
 *
 * @author VotreNom
 */
public class Menu {
    private String title;
    private ArrayList<MenuItem> items;
    private int defaultChoice;
    private int numberOfChoices = 0;

    /**
     * Constructeur de la classe <code>Menu</code>.
     * Il initialise le menu avec un titre, une liste d'options et un choix par défaut.
     * <p>
     * La liste des items est filtrée pour retirer les options désactivées et triée selon leur rang (choiceRank).
     * </p>
     *
     * @param title le titre du menu
     * @param items la liste des options du menu
     * @param defaultChoice le choix par défaut (utilisé en cas de besoin)
     */
    public Menu(String title, ArrayList<MenuItem> items, int defaultChoice) {
        this.title = title;
        this.items = items;
        this.defaultChoice = defaultChoice;
        // Supprime de la liste les MenuItem désactivés
        this.items.removeIf(item -> item.isDisabled());
        this.numberOfChoices = this.items.size();
        // TODO : Vérifier qu'aucun doublon de valeur de position n'est présent dans la liste
        // Trie la liste des items en fonction de leur rang (choiceRank)
        this.items.sort((item1, item2) -> item1.getchoiceRank() - item2.getchoiceRank());
    }

    /**
     * Constructeur alternatif de la classe <code>Menu</code> qui initialise le menu avec un choix par défaut de 1.
     *
     * @param title le titre du menu
     * @param items la liste des options du menu
     */
    public Menu(String title, ArrayList<MenuItem> items) {
        this(title, items, 1);
    }

    /**
     * Ajoute un nouvel item au menu et réorganise la liste selon le rang.
     *
     * @param item l'option à ajouter au menu
     */
    public void addMenuItem(MenuItem item) {
        this.items.add(item);
        // Trie la liste après ajout pour respecter l'ordre défini par le choix du rang
        this.items.sort((item1, item2) -> item1.getchoiceRank() - item2.getchoiceRank());
        this.numberOfChoices = this.items.size();
    }

    /**
     * Retourne le titre du menu.
     *
     * @return le titre du menu
     */
    public String getTitle() {
        return title;
    }

    /**
     * Met à jour le titre du menu.
     *
     * @param title le nouveau titre du menu
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retourne la liste des items du menu.
     *
     * @return la liste des options du menu
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Met à jour la liste des items du menu.
     *
     * @param items la nouvelle liste des options du menu
     */
    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    /**
     * Retourne le choix par défaut du menu.
     *
     * @return le choix par défaut
     */
    public int getDefaultChoice() {
        return defaultChoice;
    }

    /**
     * Met à jour le choix par défaut du menu.
     *
     * @param defaultChoice le nouveau choix par défaut
     */
    public void setDefaultChoice(int defaultChoice) {
        this.defaultChoice = defaultChoice;
    }

    /**
     * Exécute le menu en affichant les options et en récupérant le choix de l'utilisateur.
     * La méthode boucle tant que l'utilisateur ne saisit pas un choix valide.
     *
     * @param scanner le Scanner utilisé pour lire la saisie utilisateur
     * @return la chaîne de caractères correspondant à l'action associée au choix sélectionné
     */
    public String exec(Scanner scanner) {
        int choice = -1;
        do {
            System.out.print(this);
            if (choice > numberOfChoices) {
                System.out.println("Veuillez saisir une valeur comprise entre 1 et " + numberOfChoices + ".");
            }
            System.out.print(">>> ");
            try {
                choice = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                choice = -1;  // En cas d'erreur, on continue la boucle d'interaction
                System.out.println("Veuillez saisir quelque chose.");
            }
        } while (choice < 1 || choice > numberOfChoices);
        return items.get(choice - 1).getChoiceAction();
    }

    /**
     * Génère une représentation textuelle du menu.
     * Le menu affiche le titre et la liste des options numérotées.
     *
     * @return une chaîne de caractères représentant le menu
     */
    @Override
    public String toString() {
        StringBuilder menuItems = new StringBuilder();
        int index = 0;
        for (MenuItem item : items) {
            // Seules les options activées sont affichées
            menuItems.append(item.isEnabled() ? "[" + ++index + "] " + item.getChoiceLabel() + ".\n" : "");
        }

        String menuTemplate = """
                ****************************************
                ***** %S
                ****************************************
                %s
                ****************************************
                """;

        String menu = String.format(menuTemplate, this.title, menuItems.toString().trim());
        return menu;
    }
}
