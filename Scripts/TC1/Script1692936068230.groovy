import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.configuration.RunConfiguration
import java.nio.file.Path
import java.nio.file.Paths
import com.kms.katalon.core.util.KeywordUtil

TestObject makeTestObject(String id, String selector) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("css", ConditionType.EQUALS, selector)
	return tObj
}
println "Katalon Studio version: ${RunConfiguration.getAppVersion()}"
Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path html = projectDir.resolve("page.html")
WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)
WebUI.navigateToUrl(html.toFile().toURI().toURL().toExternalForm() + "?ksVersion=${RunConfiguration.getAppVersion()}")
TestObject tObj = makeTestObject("demo", "#demo")
WebUI.verifyElementPresent(tObj, 10, FailureHandling.STOP_ON_FAILURE)

boolean result = WebUI.waitForElementNotPresent(tObj, 10, FailureHandling.STOP_ON_FAILURE)
println "result=${result}"

if (!result) {
	KeywordUtil.markFailed("element with id=\"demo\" still remains")
}

WebUI.takeScreenshot("screenshot-" + RunConfiguration.getAppVersion() + ".png")
WebUI.delay(3);
WebUI.closeBrowser()
