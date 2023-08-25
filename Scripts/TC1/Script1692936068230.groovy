import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)

WebUI.callTestCase(findTestCase("subproc"), ["distance": 10, "timeout": 20])
WebUI.callTestCase(findTestCase("subproc"), ["distance": 10, "timeout": 5])

WebUI.closeBrowser()
