package org.example;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import javax.xml.xpath.XPath;
import java.io.IOException;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        String link = "https://www.nfl.com/stats/player-stats/category/passing/2022/REG/all/passingyards/DESC";
        WebClient webClient = createNewWebClient();

        try {
            HtmlPage htmlPage = webClient.getPage(link);
            String xPathHead = "//*[@id=\"main-content\"]/section[3]/div/div/div/div/table/thead/tr";
            String xPathBody = "//*[@id=\"main-content\"]/section[3]/div/div/div/div/table/tbody";
            HtmlTableRow xpathheader = (HtmlTableRow) htmlPage.getByXPath(xPathHead).get(0);
            HtmlTableBody xpathList =(HtmlTableBody) htmlPage.getByXPath(xPathBody).get(0);
            List<HtmlTableCell> cellList = xpathheader.getCells();
            for(HtmlTableCell cell : cellList) {
                System.out.println(cell.asNormalizedText());
            }
        } catch (FailingHttpStatusCodeException | IOException e) {
            System.out.println(e.fillInStackTrace());
        }
        System.out.println("Succesfull");
    }

    private static WebClient createNewWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        return webClient;
    }

}
