/*
 * Copyright (c) 2014, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.es.ui.integration.test.store;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.es.ui.integration.util.BaseUITestCase;
import org.wso2.es.ui.integration.util.ESUtil;
import org.wso2.es.ui.integration.util.ESWebDriver;
import static org.testng.Assert.*;

/**
 * Test appearance and behaviour of Gadget page
 */
public class ESStoreGadgetPageTestCase extends BaseUITestCase {
    private StringBuffer verificationErrors = new StringBuffer();
    private String firstAsset;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.init();
        currentUserName = userInfo.getUserName();
        currentUserPwd = userInfo.getPassword();
        driver = new ESWebDriver();
        baseUrl = getWebAppURL();
        ESUtil.login(driver, baseUrl, storeApp, currentUserName, currentUserPwd);
    }

    @Test(groups = "wso2.es.store", description = "Test Gadgets Page")
    public void testGadgetPage() throws Exception {
        //test appearance of gadget page
        driver.get(baseUrl + "/store/asts/gadget/list");
        try {
            assertEquals("Gadget", driver.findElement(By.xpath
                    ("//div[@id='container-search']/div/div/div/div/a/li")).getText(),
                    "Gadget Menu missing");
            assertEquals("Line Plus Bar Chart", driver.findElement(By.
                    xpath("//a[contains(text(),'Line Plus Bar Chart')]")).getText(),
                    "Gadgets missing");
            firstAsset = driver.findElement(By.cssSelector("h4")).getText();
            assertEquals("Recently Added", driver.findElement(By.xpath
                    ("//div[@id='container-assets']/div/div[2]/div[1]/div/h4")).getText(),
                    "Recently Added section missing");
            assertEquals("Line Plus Bar Chart", driver.findElement(By.
                    xpath("//a[contains(.,'Line Plus Bar Chart')]")).getText(),
                    "Recently added Gadgets missing");
            assertEquals("Tags", driver.findElement(By.xpath
                    ("//div[@id='container-assets']/div/div[2]/div[2]/div/h4")).getText(),
                    "Tags section missing");
            assertTrue(isElementPresent(By.linkText("charts")), "Tags missing (charts tag)");
            assertEquals("All Categories", driver.findElement(By.
                    cssSelector("div.breadcrumb-head")).getText()
                    , "Category drop down missing");
            assertTrue(isElementPresent(By.cssSelector("i.icon-star")), "Popularity sort missing");
            assertTrue(isElementPresent(By.cssSelector("i.icon-sort-alphabetical")),
                    "Alphabetical sort missing");
            assertTrue(isElementPresent(By.cssSelector("i.icon-calendar")), "Recent sort missing");
            assertTrue(isElementPresent(By.id("search")), "Search tray missing");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        verificationErrors = failOnError(verificationErrors);
    }

    @Test(groups = "wso2.es.store", description = "Test Gadgets Page Links",
            dependsOnMethods = "testGadgetPage")
    public void testLinksFromPage() throws Exception {
        //test links
        driver.get(baseUrl + "/store/asts/gadget/list");
        try {
            assertEquals(firstAsset, driver.findElement(By.cssSelector("h4")).getText(),
                    "Cannot view selected Gadget's page through Gadget list");
            driver.findElement(By.xpath("//div[@id='container-search']/div/div/div/div/a/li"))
                    .click();
            driver.findElement(By.xpath("//a[contains(text(),'Line Chart')]")).click();
            assertEquals("Line Chart", driver.findElement(By.cssSelector("h3")).getText(),
                    "Cannot view selected Gadget's page through Recently added list");

            //TODO disabled - bug
//            driver.findElement(By.xpath("//div[@id='container-search']/div/div/div/div/a/li"))
//                    .click();
//            driver.findElement(By.linkText("pie")).click();
//            assertEquals(1, driver.findElements(By.cssSelector("div.span3.asset")).size(),
//                    "Tags not working");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        verificationErrors = failOnError(verificationErrors);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.get(baseUrl + "/store/logout");
        driver.quit();
    }

}
