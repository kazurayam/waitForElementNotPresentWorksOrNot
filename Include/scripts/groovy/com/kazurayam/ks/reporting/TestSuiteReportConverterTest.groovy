package com.kazurayam.ks.reporting

import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class TestSuiteReportConverterTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	void test_convertHtmlToPDF() {
		String ts = "20230828_201457"
		Path reportFolder = Paths.get("./Include/fixtures/Reports/${ts}/TS2/${ts}")
		TestSuiteReportConverter instance = new TestSuiteReportConverter()
		instance.convertHtmlToPDF(reportFolder)
		println errContent.toString()
		println outContent.toString()
		//
		Path pdfFile = reportFolder.resolve("${ts}.pdf")
		assertTrue(Files.exists(pdfFile))
	}
}
