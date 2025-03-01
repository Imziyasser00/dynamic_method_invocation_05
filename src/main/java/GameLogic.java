import java.io.IOException;
import java.util.Scanner;

/**
 * La classe <code>GameLogic</code> regroupe l'ensemble de la logique de notre jeu RPG.
 * Toutes les méthodes et attributs sont déclarés statiques, car je n'ai pas besoin d'instancier cette classe.
 *
 * @author VotreNom
 */
public class GameLogic {
    private static final int DEFAULT_SEPARATOR_LENGTH = 50;
    static Scanner scanner = new Scanner(System.in);
    static Player player;
    private static String[] enemies = {"Sorcier", "Bandit", "Bandit", "Sorcier", "Guerrier"};
    private static boolean isGameRunning;
    private static int placeNumber = 0;
    private static String[] places = {"Place 1", "Place 2", "Place 3", "Place 4"};
    static int level = 1;

    /**
     * Lit la saisie utilisateur dans la console et retourne un entier correspondant au choix.
     * La méthode force l'utilisateur à saisir une valeur entre 1 et <code>numberOfChoices</code>.
     *
     * @param prompt le message à afficher pour inviter l'utilisateur à saisir
     * @param numberOfChoices le nombre maximum de choix possibles
     * @return l'entier choisi par l'utilisateur
     */
    public static int readChoiceInt(String prompt, int numberOfChoices) {
        int input = -1;
        do {
            if (input > numberOfChoices) {
                System.out.println("Veuillez saisir une valeur comprise entre 1 et " + numberOfChoices + " !");
            }
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;  // En cas d'erreur, on continue la boucle
                System.out.println("Veuillez saisir quelque chose !");
            }
        } while (input < 1 || input > numberOfChoices);
        return input;
    }

    /**
     * Efface le contenu de la console.
     * Si <code>rough</code> est vrai, la méthode effectue simplement 100 retours à la ligne,
     * sinon elle tente d'exécuter une commande spécifique au système d'exploitation.
     *
     * @param rough détermine le mode d'effacement (rapide ou spécifique au système)
     */
    public static void cleanOutput(boolean rough) {
        if (rough) {
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
        } else {
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec(new String[]{"clear"});
            } catch (IOException | InterruptedException e) {
                System.out.println("Une erreur inattendue est survenue : " + e);
            }
        }
    }

    /**
     * Affiche une ligne de séparation dans la console.
     *
     * @param length la longueur de la ligne
     * @param symbol le symbole utilisé pour construire la ligne (si null, '-' est utilisé)
     */
    public static void printLine(int length, String symbol) {
        symbol = (symbol == null) ? "-" : symbol;
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

    /**
     * Affiche un message dans la console précédé et suivi d'une ligne de séparation (si demandé).
     *
     * @param title le titre ou message à afficher
     * @param lineLength la longueur de la ligne de séparation (si null, la valeur par défaut est utilisée)
     * @param withSeparator indique s'il faut afficher une ligne de séparation avant et après le message
     */
    public static void mainMessage(String title, Integer lineLength, boolean withSeparator) {
        lineLength = (lineLength == null) ? DEFAULT_SEPARATOR_LENGTH : lineLength;
        if (withSeparator) {
            printLine(lineLength, null);
            System.out.println(title);
            printLine(lineLength, null);
        } else {
            System.out.println(title);
        }
    }

    /**
     * Attend que l'utilisateur saisisse une donnée pour poursuivre l'exécution du programme.
     */
    public static void waitForInput() {
        System.out.println("Appuyez sur n'importe quel caractère puis « Entrée » pour continuer...");
        scanner.next();
    }

    /**
     * Initialise et démarre le jeu.
     * Cette méthode demande à l'utilisateur de choisir un nom de joueur, présente l'introduction de l'histoire,
     * instancie le joueur et démarre la boucle principale du jeu.
     */
    public static void startGame() {
        boolean isNameDefined = false;
        String name;
        cleanOutput(true);
        mainMessage("Web Empire", null, true);
        waitForInput();

        do {
            cleanOutput(true);
            mainMessage("Veuillez choisir votre nom de joueur", null, true);
            System.out.print(">>> ");
            name = scanner.next();
            cleanOutput(true);
            mainMessage("Le nom choisi est « " + name + " ».\nÊtes-vous sûr de votre choix ?", null, true);
            System.out.println("[1] Oui.");
            System.out.println("[2] Non, je souhaite modifier mon nom de joueur.");
            int choice = readChoiceInt(">>> ", 2);
            isNameDefined = (choice == 1);
        } while (!isNameDefined);

        // Présentation de l'histoire
        Story.printIntro();

        // Instanciation d'un joueur
        player = new Player(name);

        // Début de l'acte 1
        Story.printFirstLevelIntro();

        // Lancement de la boucle principale d'interaction du jeu
        isGameRunning = true;
        startMainGameLoop();
    }

    /**
     * Gère la progression vers le niveau suivant en fonction de l'expérience du joueur.
     * Met à jour les ennemis et affiche les transitions d'histoire correspondantes.
     */
    public static void goToNextLevel() {
        if (player.getExperience() >= 10 && level == 1) {
            level++;
            placeNumber++;
            Story.printFirstLevelOutro();
            player.chooseSkills();
            // Mise à jour des ennemis pour le niveau suivant
            enemies = new String[]{"Bandit", "Mercenaire", "Sorcier", "Bandit", "Guerrier"};
            Story.printSecondLevelIntro();
        } else if (player.getExperience() >= 50 && level == 2) {
            level++;
            placeNumber++;
            Story.printSecondLevelOutro();
            player.chooseSkills();
            enemies = new String[]{"Bandit", "Mercenaire", "Sorcier", "Bandit", "Guerrier"};
            Story.printThirdLevelIntro();
            player.setHealth(player.getMaxHealth());
        } else if (player.getExperience() >= 100 && level == 3) {
            level++;
            placeNumber++;
            Story.printThirdLevelOutro();
            player.chooseSkills();
            enemies = new String[]{"Bandit", "Mercenaire", "Sorcier", "Bandit", "Guerrier"};
            Story.printFourthLevelIntro();
            finalFight();
        }
    }

    /**
     * Choisit dynamiquement l'événement suivant (combat, repos ou achat) en fonction de l'état du joueur.
     *
     * @return une chaîne de caractères indiquant l'événement choisi ("Combat", "Repos" ou "Achat")
     */
    private static String chooseRandomEvent() {
        // Poids de base pour chaque événement
        int weightFight = 50;
        int weightRest = 30;
        int weightPurchase = 20;

        // Augmentation des chances de repos si la santé est faible
        if (player.getHealth() < player.getMaxHealth() * 0.5) {
            weightRest += 20;
        }
        // Augmentation des chances d'achat si le joueur a peu de pièces
        if (player.coins < 5) {
            weightPurchase += 20;
        }
        int totalWeight = weightFight + weightRest + weightPurchase;
        int randomVal = (int) (Math.random() * totalWeight);

        if (randomVal < weightFight) {
            return "Combat";
        } else if (randomVal < weightFight + weightRest) {
            return "Repos";
        } else {
            return "Achat";
        }
    }

    /**
     * Continue la partie en passant au niveau suivant et en déclenchant un événement aléatoire (combat, repos ou achat).
     */
    public static void continueGame() {
        goToNextLevel();
        if (level != Story.LAST_LEVEL) {
            String event = chooseRandomEvent();
            switch (event) {
                case "Combat":
                    startFight();
                    break;
                case "Repos":
                    rest();
                    break;
                case "Achat":
                    purchase();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Affiche les informations du personnage (points de vie, expérience, argent, élixirs, compétences, etc.).
     */
    public static void showCharacterInfo() {
        cleanOutput(true);
        mainMessage("Vos informations - " + player.getName(), null, true);
        System.out.println("HP : " + player.getHealth() + " / " + player.getMaxHealth());
        printLine(50, null);
        System.out.println("XP : " + player.getExperience());
        printLine(50, null);
        System.out.println("Argent : " + player.coins);
        printLine(50, null);
        System.out.println("Nombre d'élixirs : " + player.elixirs);
        printLine(50, null);

        if (player.attackSkillsNumber > 0) {
            System.out.println("Attaque : " + player.attackSkills[player.attackSkillsNumber - 1]);
            printLine(50, null);
        }
        if (player.defenseSkillsNumber > 0) {
            System.out.println("Défense : " + player.defenseSkills[player.defenseSkillsNumber - 1]);
            printLine(50, null);
        }
        waitForInput();
    }

    /**
     * Gère le système d'achat d'un élixir par le joueur.
     * Vérifie que le joueur dispose des pièces nécessaires, déduit le montant et augmente le nombre d'élixirs.
     */
    public static void purchase() {
        cleanOutput(true);
        int randomPrice = (int) (Math.random() * (10 + player.elixirs * 3) + 10 + player.elixirs);
        mainMessage("Vous rencontrez un marchand qui propose un élixir pour " + randomPrice + " coins.", null, false);
        printLine(50, null);
        System.out.println("Acceptez-vous sa proposition ?");
        System.out.println("[1] Oui.");
        System.out.println("[2] Non, c'est trop cher.");
        int choice = readChoiceInt(">>> ", 2);
        if (choice == 1) {
            cleanOutput(true);
            if (player.coins >= randomPrice) {
                mainMessage("Vous avez acheté l'élixir du marchand.", null, false);
                player.elixirs++;
                player.coins -= randomPrice;
            } else {
                mainMessage("Il vous manque " + (randomPrice - player.coins) + " coins pour acheter l'élixir.", null, false);
            }
            waitForInput();
        }
    }

    /**
     * Permet au joueur de se reposer et de récupérer des points de vie.
     * Vérifie que le joueur dispose d'au moins un repos disponible et que sa santé n'est pas déjà maximale.
     */
    public static void rest() {
        cleanOutput(true);
        if (player.rests >= 1) {
            mainMessage("Vous pouvez encore vous reposer " + player.rests + " fois. Voulez-vous reprendre des forces ?", null, false);
            System.out.println("[1] Oui.");
            System.out.println("[2] Non, je me sens en forme.");
            int choice = readChoiceInt(">>> ", 2);
            if (choice == 1) {
                cleanOutput(true);
                if (player.getHealth() < player.getMaxHealth()) {
                    int restHealthEffect = (int) (Math.random() * (player.getExperience() / 4.0 + 1) + 10);
                    player.setHealth(Math.min(player.getHealth() + restHealthEffect, player.getMaxHealth()));
                    System.out.println("Vous avez récupéré des forces. Votre santé est maintenant de " + player.getHealth() + " points.");
                    player.rests--;
                } else {
                    System.out.println("Vous êtes déjà en pleine forme.");
                }
                waitForInput();
            }
        }
    }

    /**
     * Démarre un combat en sélectionnant un ennemi aléatoire et en appelant la méthode de gestion du combat.
     */
    public static void startFight() {
        cleanOutput(true);
        mainMessage("Vous venez de rencontrer un ennemi...", null, false);
        waitForInput();
        String enemyName = enemies[(int) (Math.random() * enemies.length)];
        Enemy enemy = new Enemy(enemyName, player.getExperience());
        fight(enemy);
    }

    /**
     * Gère le déroulement d'un combat entre le joueur et un ennemi.
     * Propose au joueur de combattre, d'utiliser un élixir ou de fuir, et met à jour les points de vie et l'expérience en conséquence.
     *
     * @param enemy l'ennemi contre lequel le joueur combat
     */
    public static void fight(Enemy enemy) {
        boolean isFightOngoing = true;
        while (isFightOngoing) {
            cleanOutput(true);
            mainMessage("Ennemi [" + enemy.getName() + "]\nHP: " + enemy.getHealth() + " | XP: " + enemy.getExperience(), null, false);
            mainMessage("Vous [" + player.getName() + "]\nHP: " + player.getHealth() + " | XP: " + player.getExperience(), null, false);
            System.out.println("Choisissez une action :");
            printLine(50, null);
            System.out.println("[1] Combattre.");
            System.out.println("[2] Utiliser un élixir.");
            System.out.println("[3] Prendre la fuite.");
            int choice = readChoiceInt(">>> ", 3);
            int dealtDamage, takenDamage;
            switch (choice) {
                case 1: // Combat
                    dealtDamage = player.attack() - enemy.defend();
                    takenDamage = enemy.attack() - player.defend();
                    if (takenDamage < 0) {
                        dealtDamage -= takenDamage / 2;
                        takenDamage = 0;
                    }
                    if (dealtDamage < 0) {
                        dealtDamage = 0;
                    }
                    player.setHealth(player.getHealth() - takenDamage);
                    enemy.setHealth(enemy.getHealth() - dealtDamage);
                    cleanOutput(true);
                    mainMessage("Bilan du combat", null, false);
                    System.out.println("Vous avez infligé " + dealtDamage + " points de dégâts à " + enemy.getName() + ".");
                    printLine(50, null);
                    System.out.println(enemy.getName() + " vous a infligé " + takenDamage + " points de dégâts.");
                    waitForInput();
                    if (player.getHealth() <= 0) {
                        gameOver();
                        isFightOngoing = false;
                    } else if (enemy.getHealth() <= 0) {
                        cleanOutput(true);
                        mainMessage("Vous avez vaincu " + enemy.getName() + ".", null, false);
                        player.setExperience(player.getExperience() + enemy.getExperience());
                        mainMessage("Vous gagnez " + enemy.getExperience() + " points d'expérience.", null, false);
                        // Récompense aléatoire
                        if (Math.random() * 5 + 1 <= 2.25) {
                            player.rests++;
                            System.out.println("Vous avez gagné une opportunité de repos supplémentaire.");
                        }
                        int rewardCoins = (int) (Math.random() * enemy.getExperience());
                        if (rewardCoins > 0) {
                            player.coins += rewardCoins;
                            System.out.println("Vous avez dérobé " + rewardCoins + " coins à " + enemy.getName() + ".");
                        }
                        waitForInput();
                        isFightOngoing = false;
                    }
                    break;
                case 2: // Utilisation d'un élixir
                    cleanOutput(true);
                    if (player.elixirs > 0 && player.getHealth() < player.getMaxHealth()) {
                        mainMessage("Voulez-vous utiliser un élixir ? (" + player.elixirs + " restant(s))", null, false);
                        System.out.println("[1] Oui.");
                        System.out.println("[2] Non.");
                        int choiceElixir = readChoiceInt(">>> ", 2);
                        if (choiceElixir == 1) {
                            player.setHealth(player.getMaxHealth());
                            mainMessage("Grâce à l'élixir, vos points de vie sont entièrement restaurés.", null, false);
                            waitForInput();
                        } else {
                            showCharacterInfo();
                        }
                    } else {
                        mainMessage("Vous n'avez pas d'élixir ou vous n'en avez pas besoin.", null, false);
                        waitForInput();
                    }
                    break;
                case 3: // Fuite
                    cleanOutput(true);
                    if (level != Story.LAST_LEVEL) {
                        if (Math.random() * 10 + 1 <= 3.5) {
                            mainMessage("Vous parvenez à fuir.", null, false);
                            waitForInput();
                            isFightOngoing = false;
                        } else {
                            mainMessage(enemy.getName() + " vous rattrape!", null, false);
                            takenDamage = enemy.attack();
                            mainMessage("Vous perdez " + takenDamage + " points de vie.", null, false);
                            player.setHealth(player.getHealth() - takenDamage);
                            waitForInput();
                            if (player.getHealth() <= 0) {
                                gameOver();
                                isFightOngoing = false;
                            }
                        }
                    } else {
                        mainMessage("Vous ne pouvez fuir le combat final.", null, false);
                        waitForInput();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Démarre le combat final contre le boss et termine le jeu.
     */
    public static void finalFight() {
        Enemy finalEnemy = new Enemy("Grand Maître", 300);
        fight(finalEnemy);
        Story.printOutro();
        isGameRunning = false;
    }

    /**
     * Affiche le menu principal du jeu avec les différentes options disponibles.
     */
    public static void printMainMenu() {
        cleanOutput(true);
        mainMessage(places[placeNumber], null, true);
        System.out.println("Veuillez choisir une action :");
        printLine(50, null);
        System.out.println("[1] Poursuivre le jeu.");
        System.out.println("[2] Voir vos informations.");
        System.out.println("[3] Quitter le jeu.");
    }

    /**
     * Affiche le message de fin de jeu lorsque le joueur meurt et arrête l'exécution de la boucle principale.
     */
    public static void gameOver() {
        cleanOutput(true);
        mainMessage("Vous venez de mourir...", null, false);
        mainMessage("Vous avez gagné " + player.getExperience() + " points d'expérience.", null, false);
        System.out.println("Essayez encore une fois !");
        isGameRunning = false;
    }

    /**
     * Démarre la boucle principale du jeu qui affiche le menu et gère les actions du joueur.
     */
    public static void startMainGameLoop() {
        while (isGameRunning) {
            printMainMenu();
            int choice = readChoiceInt(">>> ", 3);
            switch (choice) {
                case 1:
                    continueGame();
                    break;
                case 2:
                    showCharacterInfo();
                    break;
                case 3:
                    isGameRunning = false;
                    break;
                default:
                    break;
            }
        }
    }
}
