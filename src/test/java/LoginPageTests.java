import com.microsoft.playwright.*;
import org.example.TraceExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TraceExtension.class)
public class LoginPageTests {

    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        // Stop tracing and export it into a zip archive.
        String currentTestName = TraceExtension.testName;
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/trace-" + currentTestName + ".zip")));
        context.close();
    }

    void enterCreds(String login, String password) {
        page.navigate("http://localhost:8000/login.html");
        page.locator("input[id=\"username\"]").fill(login);
        page.locator("input[id=\"password\"]").fill(password);
    }

    @Test
    void shouldEnterCorrectCredentials() {
        enterCreds("to@mail.com", "toto");
        page.locator("button").click();
        assertEquals("Login successful", page.locator("div[id=\"message\"]").textContent());
    }

    @Test
    void shouldEnterEmptyCredentials() {
        enterCreds("", ""); // or skip this step
        page.locator("button").click();
        assertEquals("Username and password are required", page.locator("div[id=\"message\"]").textContent());
    }

    @Test
    void shouldEnterIncorrectCredentials() {
        enterCreds("incorrect@mail.com", "no"); // or skip this step
        page.locator("button").click();
        assertEquals("Invalid username or password", page.locator("div[id=\"message\"]").textContent());
    }

}
