import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kazurayam.ks.waitforelementnotpresentworksornot.KeywordDriver
import com.kazurayam.ks.waitforelementnotpresentworksornot.ResultEvaluator

/**
 * 
 * 
 */

println "Katalon Studio version: ${RunConfiguration.getAppVersion()}"

WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)

Map result1 = new KeywordDriver().drive(distance = 10, timeout = 15)
new ResultEvaluator().evaluate(expected = true, result = result1)

Map result2 = new KeywordDriver().drive(distance = 10, timeout = 5)
new ResultEvaluator().evaluate(expected = false, result = result2)

WebUI.closeBrowser()
