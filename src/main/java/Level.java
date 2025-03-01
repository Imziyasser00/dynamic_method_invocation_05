/**
 * La classe <code>Level</code> représente un niveau dans le jeu.
 * Elle contient le titre du niveau, ainsi que les textes d'introduction et de conclusion qui décrivent le contexte et la fin du niveau.
 * Cette classe permet de stocker et de récupérer ces informations pour les afficher durant l'aventure.
 *
 * @author VotreNom
 */
public class Level {
    private String title;
    private String intro;
    private String outro;

    /**
     * Constructeur de la classe <code>Level</code>.
     *
     * @param name le titre du niveau
     * @param intro le texte d'introduction du niveau
     * @param outro le texte de conclusion du niveau
     */
    public Level(String name, String intro, String outro) {
        this.title = name;
        this.intro = intro;
        this.outro = outro;
    }

    /**
     * Retourne le titre du niveau.
     *
     * @return le titre du niveau
     */
    public String getTitle() {
        return title;
    }

    /**
     * Met à jour le titre du niveau.
     *
     * @param title le nouveau titre du niveau
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retourne le texte d'introduction du niveau.
     *
     * @return le texte d'introduction
     */
    public String getIntro() {
        return intro;
    }

    /**
     * Met à jour le texte d'introduction du niveau.
     *
     * @param intro le nouveau texte d'introduction
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * Retourne le texte de conclusion du niveau.
     *
     * @return le texte de conclusion
     */
    public String getOutro() {
        return outro;
    }

    /**
     * Met à jour le texte de conclusion du niveau.
     *
     * @param outro le nouveau texte de conclusion
     */
    public void setOutro(String outro) {
        this.outro = outro;
    }
}
