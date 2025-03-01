/**
 * La classe <code>MenuItem</code> représente une option dans un menu interactif.
 * Chaque objet <code>MenuItem</code> possède un rang (pour déterminer son ordre d'affichage),
 * un libellé, une action associée et un indicateur indiquant s'il est activé ou non.
 * Cette classe permet de gérer dynamiquement les options d'un menu.
 *
 * @author VotreNom
 */
public class MenuItem {
    private int choiceRank;
    private String choiceLabel;
    private String choiceAction;
    private boolean enabled;

    /**
     * Constructeur de la classe <code>MenuItem</code> permettant d'initialiser tous les attributs.
     *
     * @param choiceRank le rang (ordre) de l'option dans le menu
     * @param choiceLabel le libellé affiché pour cette option
     * @param choiceAction l'action (souvent une commande ou un identifiant) associée à cette option
     * @param enabled indique si l'option est activée (<code>true</code>) ou désactivée (<code>false</code>)
     */
    public MenuItem(int choiceRank, String choiceLabel, String choiceAction, boolean enabled) {
        this.choiceRank = choiceRank;
        this.choiceLabel = choiceLabel;
        this.choiceAction = choiceAction;
        this.enabled = enabled;
    }

    /**
     * Constructeur de la classe <code>MenuItem</code> qui initialise l'option en la marquant comme activée.
     *
     * @param choiceRank le rang (ordre) de l'option dans le menu
     * @param choiceLabel le libellé affiché pour cette option
     * @param choiceAction l'action associée à cette option
     */
    public MenuItem(int choiceRank, String choiceLabel, String choiceAction) {
        this(choiceRank, choiceLabel, choiceAction, true);
    }

    /**
     * Retourne le rang de l'option dans le menu.
     *
     * @return le rang de l'option
     */
    public int getchoiceRank() {
        return choiceRank;
    }

    /**
     * Met à jour le rang de l'option dans le menu.
     *
     * @param choiceRank le nouveau rang de l'option
     */
    public void setchoiceRank(int choiceRank) {
        this.choiceRank = choiceRank;
    }

    /**
     * Retourne le libellé de l'option.
     *
     * @return le libellé de l'option
     */
    public String getChoiceLabel() {
        return choiceLabel;
    }

    /**
     * Met à jour le libellé de l'option.
     *
     * @param choiceLabel le nouveau libellé de l'option
     */
    public void setChoiceLabel(String choiceLabel) {
        this.choiceLabel = choiceLabel;
    }

    /**
     * Retourne l'action associée à l'option.
     *
     * @return l'action associée
     */
    public String getChoiceAction() {
        return choiceAction;
    }

    /**
     * Met à jour l'action associée à l'option.
     *
     * @param choiceAction la nouvelle action associée à l'option
     */
    public void setChoiceAction(String choiceAction) {
        this.choiceAction = choiceAction;
    }

    /**
     * Vérifie si l'option est activée.
     *
     * @return <code>true</code> si l'option est activée, <code>false</code> sinon
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Vérifie si l'option est désactivée.
     *
     * @return <code>true</code> si l'option est désactivée, <code>false</code> sinon
     */
    public boolean isDisabled() {
        return !enabled;
    }
}
