package com.kazurayam.ks.reporting

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

import com.kazurayam.subprocessj.Subprocess
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class TestSuiteReportConverter {

	/**
	 * convert a Test Suite Execution Report in HTML to PDF
	 * using the wkhtmltopdf command
	 * 
	 * @param reportFolder
	 */
	public void convertHtmlToPDF(Path reportFolder) {
		Objects.requireNonNull(reportFolder)
		assert Files.exists(reportFolder)
		String prefix = reportFolder.getFileName().toString()
		String htmlFileName = prefix + ".html"
		Path htmlFile = reportFolder.resolve(htmlFileName)
		assert Files.exists(htmlFile)
		String pdfFileName = prefix + ".pdf"
		Path pdfFile = reportFolder.resolve(pdfFileName)
		if (Files.exists(pdfFile)) {
			Files.delete(pdfFile)
		}
		//
		Subprocess.CompletedProcess cp;
		cp = new Subprocess().cwd(reportFolder.toFile())
				.run(Arrays.asList("/usr/local/bin/wkhtmltopdf", "--debug-javascript",
				"--run-script", "\"console.log('starting')\"",
				"--run-script", "\"document.querySelector('h1').textContent='Hello, world'\"",
				"--run-script", "\"console.log('finished')\"",
				"--javascript-delay", "1000",
				htmlFileName, pdfFileName));
		assert 0 == cp.returncode()
		cp.stdout().each { line -> println line }
		cp.stderr().each { line -> println line }
	}
}
