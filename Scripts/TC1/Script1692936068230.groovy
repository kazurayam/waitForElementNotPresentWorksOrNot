import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * 
 * 
 */

println "Katalon Studio version: ${RunConfiguration.getAppVersion()}"

WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)

Map result1 = WebUI.callTestCase(findTestCase("driveKeyword"), ["distance": 10, "timeout": 15])
WebUI.callTestCase(findTestCase("evaluateResult"), ["expected": true, "result": result1])

Map result2 = WebUI.callTestCase(findTestCase("driveKeyword"), ["distance": 10, "timeout": 5])
WebUI.callTestCase(findTestCase("evaluateResult"), ["expected": false, "result": result2])

WebUI.closeBrowser()
