package com.kazurayam.ks.reporting

import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class TestSuiteReportConverterTest {

	@Test
	void testMultiply() {
		String ts = "20230828_201457"
		Path reportFolder = Paths.get("./Include/fixtures/Reports/${ts}/TS2/${ts}")
		TestSuiteReportConverter instance = new TestSuiteReportConverter()
		instance.convertHtmlToPDF(reportFolder)
		//
		Path pdfFile = reportFolder.resolve("${ts}.pdf")
		assertTrue(Files.exists(pdfFile))
	}
}
