/*
 * Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.es.ui.integration.extension.test.publisher;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.es.ui.integration.extension.util.BaseUITestCase;
import org.wso2.es.ui.integration.extension.util.ESUtil;
import org.wso2.es.ui.integration.extension.util.ESWebDriver;

import static org.testng.Assert.assertTrue;


public class ESPublisherAssetCaramelPageTestCase extends BaseUITestCase {

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.init();
        driver = new ESWebDriver();
        baseUrl = getWebAppURL();
        ESUtil.login(driver, baseUrl, publisherApp, userInfo.getUserName(), userInfo.getPassword());
    }

    @Test(groups = "wso2.es", description = "")
    public void testESPublisherAssetCaramelPageTestCase() throws Exception {
        driver.get(baseUrl + "/publisher/asts/gadget/list");
        driver.findElement(By.cssSelector("button.btn.dropdown-toggle")).click();
        driver.findElement(By.linkText("Service")).click();
        driver.get(baseUrl + "/publisher/asts/servicex/new_caramel_page");
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Overview")));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Overview")));
        //assertTrue(isElementPresent(By.cssSelector("span.publisherTitle")));
//        driver.findElementPoll(By.linkText("Overview"),5);
        assertTrue(isElementPresent(By.linkText("Overview")));
        assertTrue(isElementPresent(By.linkText("Edit")));
        assertTrue(isElementPresent(By.linkText("Life Cycle")));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.get("/publisher/logout");
        driver.quit();
    }

}
