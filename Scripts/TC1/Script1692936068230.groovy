import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

void judge(boolean expected, Map result) {
	if (result.kwReturn == expected) {
		if (result.timeTaken <= result.timeout) {
			KeywordUtil.logInfo("WebUI.waitForElement returnd ${result.kwReturn} whereas expected=${expected}; timeTaken(${result.timeTaken}) <= timeout(${result.timeout})")
		} else {
			KeywordUtil.markFailed("WebUI.waitForElement should have returned near ${result.distance} seconds, but actually it took ${result.timeTaken} seconds")
		}
	} else {
		KeywordUtil.markFailed("WebUI.waitForElement returned ${result.kwReturn} whereas expected=${expected}")
	}
}

println "Katalon Studio version: ${RunConfiguration.getAppVersion()}"

WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)

Map result

result = WebUI.callTestCase(findTestCase("subproc"), ["distance": 10, "timeout": 15])
judge(true, result)

result = WebUI.callTestCase(findTestCase("subproc"), ["distance": 10, "timeout": 5])
judge(false, result)

WebUI.closeBrowser()
