import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

void judge(int timeout, boolean result, boolean expected) {
	if (result == expected) {
		KeywordUtil.logInfo("WebUI.waitForElement with timeout=${timeout}, result=${result}, expected=${expected}")
	} else {
		KeywordUtil.markFailed("WebUI.waitForElement with timeout=${timeout}, result=${result}, expected=${expected}")
	}
}

WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)

boolean result

result = WebUI.callTestCase(findTestCase("subproc"), ["distance": 10, "timeout": 15])
judge(15, result, true)

result = WebUI.callTestCase(findTestCase("subproc"), ["distance": 10, "timeout": 5])
judge(5, result, false)

WebUI.closeBrowser()
