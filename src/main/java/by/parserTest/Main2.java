package by.parserTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main2 {
    public static void main(String[] args) {
        String url = "https://www.nasdaq.com/market-activity/stocks/aapl/earnings";

        //System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get(url);
        } catch (TimeoutException exception) {
            System.out.println("Lost Internet Connection");
        }

        Document page = Jsoup.parse(webDriver.getPageSource());
        webDriver.close();

        Element earningsSurpAmoun = page.select("tbody[class=earnings-surprise__table-body]").first();

        Element earningsForecast = page.select("div[class=earnings-forecast__section earnings-forecast__section--quarterly]").first();
        Element earningsForecastTable = earningsForecast.select("tbody[class=earnings-forecast__table-body]").first();

        for (int i = 0; i < 2; i++) {
            Element earningsForecastElement = earningsForecastTable.select("tr[class=earnings-forecast__row earnings-forecast__row--body]").get(i);

            System.out.println("Some " + earningsForecastElement.select("th[class=earnings-forecast__cell]").text());
            System.out.println("Some second " + earningsForecastElement.select("td[class=earnings-forecast__cell]").first().text());
            System.out.println();
            System.out.println();
        }

        for (int i = 0; i < 4; i++) {
            Element earningsSurpAmounElement = earningsSurpAmoun.select("tr[class=earnings-surprise__row earnings-surprise__row--body]").get(i);

            System.out.println("Month: " + earningsSurpAmounElement.select("th[class=earnings-surprise__table-cell]").text());

            System.out.println("Date: " + earningsSurpAmounElement.select("td[class=earnings-surprise__table-cell]").get(0).text());
            System.out.println("First: " + earningsSurpAmounElement.select("td[class=earnings-surprise__table-cell]").get(1).text());
            System.out.println("Second: " + earningsSurpAmounElement.select("td[class=earnings-surprise__table-cell]").get(2).text());
            System.out.println();
            System.out.println();
        }
    }
}