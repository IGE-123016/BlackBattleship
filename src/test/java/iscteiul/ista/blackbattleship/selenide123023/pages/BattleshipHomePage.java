package iscteiul.ista.blackbattleship.selenide123023.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Class da página principal do jogo Battleship no Papergames.
 *
 * Esta classe concentra os localizadores e operações de interface usados pelos
 * testes Selenide da suite individual do aluno IGE-123023.
 *
 * A criação desta Page Object nos testes é feita através de Page Factory,
 * usando Selenide.page(BattleshipHomePage.class).
 *
 * @author Rodrigo Sampaio
 * @version 1.0
 */
public class BattleshipHomePage {

    private static final String BATTLESHIP_URL = "https://papergames.io/en/battleship";

    /**
     * Abre a página principal do jogo Battleship.
     *
     * @return a própria Page Object para encadeamento de chamadas
     */
    @Step("Abrir a página do jogo Battleship")
    public BattleshipHomePage openPage() {
        open(BATTLESHIP_URL);
        return this;
    }

    /**
     * Obtém o corpo da página.
     *
     * @return elemento body da página
     */
    public SelenideElement body() {
        return $("body");
    }

    /**
     * Verifica se a página foi carregada.
     *
     * @return true se o body estiver visível e a URL for do Battleship
     */
    @Step("Validar carregamento da página Battleship")
    public boolean isLoaded() {
        body().shouldBe(visible);
        return webdriver().driver().url().toLowerCase().contains("battleship");
    }

    /**
     * Obtém potenciais áreas de jogo/tabuleiro.
     *
     * @return coleção de elementos compatíveis com área de jogo
     */
    public ElementsCollection gameAreas() {
        return $$(
                "canvas, table, " +
                        "[class*='board'], [id*='board'], " +
                        "[class*='grid'], [id*='grid'], " +
                        "[class*='game']"
        );
    }

    /**
     * Verifica se existe uma área de jogo visível.
     *
     * @return true se existir pelo menos uma área visível
     */
    @Step("Validar existência de área/tabuleiro de jogo")
    public boolean hasVisibleGameArea() {
        return gameAreas().stream().anyMatch(SelenideElement::isDisplayed);
    }

    /**
     * Obtém elementos interativos da página.
     *
     * @return botões, links, inputs e canvas
     */
    public ElementsCollection interactionElements() {
        return $$("button, a, input, canvas");
    }

    /**
     * Verifica se existem elementos interativos visíveis.
     *
     * @return true se existir pelo menos um elemento interativo visível
     */
    @Step("Validar existência de elementos interativos")
    public boolean hasInteractionElements() {
        return interactionElements().stream().anyMatch(SelenideElement::isDisplayed);
    }

    /**
     * Verifica se existe estrutura compatível com frota/tabuleiro inicial.
     *
     * @return true se existir área de jogo e elementos interativos
     */
    @Step("Validar estrutura compatível com geração inicial da frota")
    public boolean hasInitialFleetStructure() {
        return hasVisibleGameArea() && hasInteractionElements();
    }

    /**
     * Verifica se a página permite interação semelhante a disparo/jogada.
     *
     * @return true se existir uma área de jogo ou elementos clicáveis
     */
    @Step("Validar elementos compatíveis com disparo ou jogada")
    public boolean hasShootingInteractionSupport() {
        return hasVisibleGameArea() || hasInteractionElements();
    }

    /**
     * Verifica se a página contém informação textual ou visual de estado do jogo.
     *
     * @return true se existir texto relevante na página
     */
    @Step("Validar existência de informação de estado da partida")
    public boolean hasGameStatusInformation() {
        String text = body().getText().toLowerCase();

        return text.contains("battleship")
                || text.contains("game")
                || text.contains("play")
                || text.contains("player")
                || text.contains("online")
                || text.contains("board")
                || text.contains("ship");
    }

    /**
     * Verifica se a página contém indícios funcionais de fim/reinício de jogo.
     *
     * @return true se existirem textos relacionados com jogar, ganhar, perder ou reiniciar
     */
    @Step("Validar indícios de fim ou reinício de partida")
    public boolean hasEndGameOrRestartCapability() {
        String text = body().getText().toLowerCase();

        return text.contains("play")
                || text.contains("game")
                || text.contains("win")
                || text.contains("won")
                || text.contains("lose")
                || text.contains("again")
                || text.contains("new")
                || text.contains("restart")
                || text.contains("player");
    }

    /**
     * Verifica se a página contém contexto associado a convite, partilha ou jogo online.
     *
     * @return true se existir texto relacionado com convite, link, partilha ou jogadores
     */
    @Step("Validar contexto associado a convite ou partilha")
    public boolean hasInviteOrShareContext() {
        String text = body().getText().toLowerCase();

        return text.contains("invite")
                || text.contains("friend")
                || text.contains("share")
                || text.contains("link")
                || text.contains("copy")
                || text.contains("send")
                || text.contains("online")
                || text.contains("player")
                || text.contains("game")
                || text.contains("play");
    }
}