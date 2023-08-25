import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.setViewPortSize(400, 400)

WebUI.callTestCase(findTestCase("subproc"), ["distance": 5, "timeout": 8])
WebUI.callTestCase(findTestCase("subproc"), ["distance": 30, "timeout": 8])

WebUI.closeBrowser()
