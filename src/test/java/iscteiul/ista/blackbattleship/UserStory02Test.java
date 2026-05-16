package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

/**
 * UserStory02Test - Testes JUnit para Regra de Disparo Consecutivo
 *
 * Testa ações básicas: disparar numa célula do tabuleiro adversário, verificar
 * aparecimento do resultado (hit/miss) e, se houver 'hit', tentar disparar de novo
 * (regra de tiro consecutivo).
 *
 * Autor: Rodrigo Sampaio (IGE-123023)
 */
public class UserStory02Test {
    private WebDriver driver;
    private UserStory02 us02;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://papergames.io/en/battleship");
        us02 = new UserStory02(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFireAndMaybeSecondShot() {
        assertTrue(us02.isGamePageLoaded(), "Página de jogo deverá estar carregada");

        // escolher uma célula inicial (0,0)
        String result = us02.fireAt(0,0);
        // aceitar qualquer resultado, mas garantir que a ação foi executada
        assertNotNull(result, "Resultado do disparo não deve ser nulo");

        // se for 'hit' (heurística sobre o texto), então verificar que é possível disparar novamente
        if (result.toLowerCase().contains("hit") || result.toLowerCase().contains("ganh") || result.toLowerCase().contains("acert")) {
            // tentar disparar na célula (0,1) e garantir que a UI aceita o clique
            String second = us02.fireAt(0,1);
            assertNotNull(second, "Segundo disparo não deve ser nulo após um hit");
        }
        // se não for hit, aceitar o comportamento como não aplicável para shot-consecutive
    }
}

