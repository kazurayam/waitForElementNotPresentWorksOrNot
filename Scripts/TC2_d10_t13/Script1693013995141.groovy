import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kazurayam.ks.waitforelementnotpresent.KeywordDriver
import com.kazurayam.ks.waitforelementnotpresent.ResultEvaluator

/**
 * TC2_d10_t13
 */

Map result = new KeywordDriver().drive(distance = 10, timeout = 13)
new ResultEvaluator().evaluate(expected = true, result = result)
