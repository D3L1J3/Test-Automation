package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;

// Fill in the form - Using traceviewer
public class Example1 {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage(); // context instead of browser, otherwise report is empty
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            page.navigate("https://itera-qa.azurewebsites.net/home/automation");
            page.getByPlaceholder("Enter your name").click();
            page.getByPlaceholder("Enter your name").fill("Mike");
            page.getByPlaceholder("Enter your name").press("Tab");
            page.getByPlaceholder("Enter your mobile phone").fill("0735678206");
            page.getByPlaceholder("Enter your mobile phone").press("Tab");
            page.getByPlaceholder("Enter email").fill("test.testing@test.se");
            page.getByPlaceholder("Enter email").press("Tab");
            page.getByPlaceholder("Password").fill("testingtesting");
            page.getByLabel("Address").click();
            page.getByLabel("Address").fill("Timmermansgatan 24, Malmö");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("Trace.zip")));
        }
    }
}
