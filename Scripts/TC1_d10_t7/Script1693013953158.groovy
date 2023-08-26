import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kazurayam.ks.waitforelementnotpresent.KeywordDriver
import com.kazurayam.ks.waitforelementnotpresent.ResultEvaluator

/**
 * TC1_d10_t7
 */

Map result = new KeywordDriver().drive(distance = 10, timeout = 7)
new ResultEvaluator().evaluate(expected = false, result = result)
