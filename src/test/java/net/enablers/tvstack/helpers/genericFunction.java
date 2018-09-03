package net.enablers.tvstack.helpers;

import cucumber.api.Scenario;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.List;

public class genericFunction extends PageObject
{
    public static LinkedHashMap<String, String> dictTestData = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, String> dictConfig = new LinkedHashMap<String, String>();
    public static Hashtable<String, String> dictOutput = new Hashtable<String, String>();
    public static WebDriver driver = null;

    public void WaitForPageLoad(int timeoutinsecond)
    {
        //@ Wait for browser Ready state.
        waitABit(100);
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        try
        {
            for(int i =0;i<timeoutinsecond;i++)
            {
                String browserState = String.valueOf(executor.executeScript("return document.readyState"));
                if(browserState.equals("complete"))
                {
                    break;
                }
                else
                {
                    waitABit(1000);
                }
            }

        }
        catch (Exception e)
        {}
        //@ Wait for Loader
        try
        {
            for (int i = 0; i < timeoutinsecond; i++)
            {
                WebElement elementajax = getDriver().findElement(By.xpath("//*[@aria-label='Loading']"));
                if(elementajax.isDisplayed())
                {
                    waitABit(1000);
                }
                else
                {
                    break;
                }
            }
        }
        catch (Exception e)
        { }

        //@ Wait for Spinner
        try
        {
            for (int i = 0; i < timeoutinsecond; i++)
            {
                WebElement elementajax = getDriver().findElement(By.xpath("//*[contains(@class,'Spinner')]"));
                if(elementajax.isDisplayed())
                {
                    waitABit(1000);
                }
                else
                {
                    break;
                }
            }
        }
        catch (Exception e)
        { }

    }

    public void fetchTestDataForScenario(String scenarioName,String moduleName)
    {
        dictTestData.clear();
        String WorkSheetPath  = System.getProperty("user.dir")+"\\"+"\\src\\test\\resources\\TestData\\MasterTestData.xlsx";
        try
        {
            String strQuery = "Select * from "+moduleName+" where SCENARIONAME='"+scenarioName+"'";
            Fillo fillo=new Fillo();
            Connection connection=fillo.getConnection(WorkSheetPath);
            Recordset recordset=connection.executeQuery(strQuery);
            ArrayList<String> colCount = recordset.getFieldNames();
            for(int i=0;i<recordset.getCount();i++)
            {
                recordset.next();
                for(int j=0;j<colCount.size();j++)
                {
                    String Keyname = colCount.get(j);
                    String ColValue =recordset.getField(Keyname).toString();
                    dictTestData.put(Keyname, ColValue);
                }
            }
            recordset.close();
            connection.close();
            return ;
        }
        catch (Exception e)
        {}
        return ;

    }

    public void fnReadConfigvalue()
    {
        if(dictConfig.isEmpty())
        {
            String WorkSheetPath  = System.getProperty("user.dir")+"\\"+"\\src\\test\\resources\\TestData\\Config.xlsx";
            try
            {
                Fillo fillo=new Fillo();
                Connection connection=fillo.getConnection(WorkSheetPath);
                String strQuery="SELECT * FROM Config";
                Recordset recordset=connection.executeQuery(strQuery);
                while(recordset.next())
                {
                    String configparam =recordset.getField("Parameter").toString();
                    String configparamvalue =recordset.getField("Parametervalue").toString();
                    dictConfig.put(configparam,configparamvalue);
                }

                 strQuery="SELECT * FROM ApplicationURL";
                 recordset=connection.executeQuery(strQuery);
                while(recordset.next())
                {
                    String configparam =recordset.getField("EnvironmentType").toString();
                    String configparamvalue =recordset.getField("ApplicationURL").toString();
                    dictConfig.put(configparam,configparamvalue);
                }
                recordset.close();
                connection.close();
            }
            catch (Exception e)
            {}
        }
    }

    public void fetchTestDatafromExcelandPassargument(Scenario scenario)
    {
        try
        {
            String scenarioName =scenario.getName();
            String rawFeatureName = scenario.getId();
            String featureName = rawFeatureName.substring(rawFeatureName.lastIndexOf("/") + 1, rawFeatureName.length()).split(":")[0];
            featureName = featureName.replace(".feature", "");
            fetchTestDataForScenario(scenarioName, featureName);
        }
        catch (Exception e)
        {}

    }

    public boolean fnOpenBrowser()
    {
        String Browser = dictConfig.get("BrowserType");
        boolean customdownload = true;
        boolean blnFlag = false;
        String ThirdPartyexe = System.getProperty("user.dir")+"\\"+"\\drivers\\";
        String DownLoadPath = dictConfig.get("DownLoadPath");
        try
        {
            switch (Browser.trim().toLowerCase())
            {
                case "firefox":
                case "ff":
                case "mozila":
                    FirefoxOptions ff = new FirefoxOptions();
                    FirefoxProfile ffprofile = new FirefoxProfile();
                    ffprofile.setAcceptUntrustedCertificates(true);
                    ffprofile.setPreference("--disable-extensions", true);
                    ffprofile.setPreference("disable-popup-blocking", true);
                    ffprofile.setPreference("credentials_enable_service", false);
                    ffprofile.setPreference("profile.password_manager_enabled", false);
                    if(customdownload)
                        ffprofile.setPreference("download.default_directory", DownLoadPath);
                    ff.addArguments("--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
                    ff.setProfile(ffprofile);
                    System.setProperty("webdriver.gecko.driver", ThirdPartyexe+"geckodriver.exe");
                    driver = new FirefoxDriver(ff);
                    break;
                case "chrome":
                case "googlechrome":
                    ChromeOptions chromeoptions = new ChromeOptions();
                    chromeoptions.addArguments(new String[] { "test-type" });
                    chromeoptions.addArguments("--disable-extensions");
                    chromeoptions.addArguments("--ignore-certificate-errors");
                    chromeoptions.setCapability("disable-popup-blocking", true);
                    chromeoptions.addArguments("disable-infobars");
                    chromeoptions.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
                    chromeoptions.setCapability("credentials_enable_service", false);
                    chromeoptions.setCapability("profile.password_manager_enabled", false);
                    if (customdownload)
                        chromeoptions.setCapability("download.default_directory", DownLoadPath);
                    chromeoptions.setAcceptInsecureCerts(true);
                    chromeoptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    System.setProperty("webdriver.chrome.driver", ThirdPartyexe+"chromedriver.exe");
                  //  driver = new ChromeDriver(chromeoptions);
                    break;
            }
        }
        catch (Exception e)
        {
        }
        blnFlag = fnActionOnBrowser("navigate", dictConfig.get(dictConfig.get("Environment")));
        if (!blnFlag)
        {
            return false;
        }

        return blnFlag;
    }

    public boolean fnActionOnBrowser(String browseraction, String Optionalfornavigateorfocusorwindowid)
    {

        boolean Flag = false;
        dictOutput.put("output","");
        try
        {

            switch (browseraction.toLowerCase())
            {
                case "windowid":
                    ArrayList<String> newTab = new ArrayList<String>( getDriver().getWindowHandles());
                    dictOutput.put("output",newTab.get(Integer.parseInt(Optionalfornavigateorfocusorwindowid)));
                    return true;
                case "back":
                    getDriver().navigate().back();
                    WaitForPageLoad(120);
                    return true;
                case "forward":
                    getDriver().navigate().forward();
                    WaitForPageLoad(120);
                    return true;
                case "refresh":
                    getDriver().navigate().refresh();
                    WaitForPageLoad(120);
                    return true;
                case "maximize":
                    getDriver().manage().window().maximize();
                    return true;
                case "geturl":
                    WaitForPageLoad(120);
                    dictOutput.put("output", getDriver().getCurrentUrl().toString());
                    return true;
                case "navigate":
                    getDriver().navigate().to(Optionalfornavigateorfocusorwindowid);
                    getDriver().manage().window().maximize();
                    return true;
                case "gettitle":
                    dictOutput.put("output", getDriver().getTitle());
                    return true;
                case "focus":
                    ArrayList<String> allTab = new ArrayList<String>( getDriver().getWindowHandles());
                    getDriver().switchTo().window(allTab.get(Integer.parseInt(Optionalfornavigateorfocusorwindowid)));
                    return true;
                case "close":
                    getDriver().close();
                    return true;
                case "dispose":
                    getDriver().quit();
                    return true;
                case "getpagesource":
                    dictOutput.put("output", getDriver().getPageSource().toString());
                    return true;
                case "count":
                    dictOutput.put("output",String.valueOf( getDriver().getWindowHandles().size()));
                    return true;
                case "newtab":
                    ArrayList<String> windowHandles = new ArrayList<String>( getDriver().getWindowHandles());
                    ((JavascriptExecutor) getDriver()).executeScript(String.format("window.open('{0}', '_blank');", Optionalfornavigateorfocusorwindowid));
                    ArrayList<String> newWindowHandles = new ArrayList<String>( getDriver().getWindowHandles());
                    getDriver().switchTo().window(newWindowHandles.get(newWindowHandles.size()-1));
                    return true;

            }

        }
        catch (Exception ee)
        {
        }
        return Flag;
    }
}
