import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

assert distance != null
assert timeout != null

TestObject makeTestObject(String id, String selector) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("css", ConditionType.EQUALS, selector)
	return tObj
}

TestObject tObj = makeTestObject("demo", "#demo")

String ksVersion = RunConfiguration.getAppVersion()
println "Katalon Studio version: ${ksVersion}"

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path outDir = projectDir.resolve("screenshots")
Files.createDirectories(outDir)
Path html = projectDir.resolve("page.html")
StringBuilder sb = new StringBuilder()
sb.append(html.toFile().toURI().toURL().toExternalForm())
sb.append("?ksVersion=${ksVersion}")
sb.append('&')
sb.append("distance=${distance}")
String urlString = sb.toString()

WebUI.navigateToUrl(urlString)
WebUI.verifyElementPresent(tObj, timeout, FailureHandling.STOP_ON_FAILURE)
				   
boolean result = WebUI.waitForElementNotPresent(tObj, timeout)
println "result=${result}"

Path outfile = outDir.resolve(RunConfiguration.getAppVersion() + "_d" + distance + "_t" + timeout + ".png")
WebUI.takeScreenshot(outfile.toString())

return result