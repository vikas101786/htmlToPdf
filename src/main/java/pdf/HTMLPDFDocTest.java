package pdf;


import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class HTMLPDFDocTest {
	
	
	  private static final String EN_HTML_CODE = "<html><head></head><body>hello</body></html>";

	  private static final String KR_HTML_CODE = "<html><head></head><body>í•­ê³µì‚¬ í™•ì•½ ë²ˆí˜¸</body></html>";

	  private static final String JA_HTML_CODE = "<html><head></head><body>æ—¥æœ¬ãƒ†ãƒ¬ãƒ“</body></html>";

	  private static final String CN_HTML_CODE = "<html><head></head><body>ç”µè„‘</body></html>";
	  
	  private static final String AB_HTML_CODE = "<html><head></head><body>كيف حالك</body></html>";

	  private static final String TMP_FILE_NAME = "target/tmpPdf.ser";
	  
	  
	  public void testEnglishPDFShort() throws Exception {
		    PDFDoc pdf = PDFDocFactory.getPDFDoc("test_gb.pdf", EN_HTML_CODE);
		    pdf.setHtmlWidth(150);
		    pdf.setPageSize(new Dimension(200, 200));
		    pdf.enableImgSplit(false);
		    save(pdf);
		  }
	
	  private void save(PDFDoc pdf) throws FileNotFoundException, IOException {
		    FileOutputStream fos = new FileOutputStream("target/" + pdf.getFilename());
		    fos.write(pdf.getPDFBytes());
		    fos.close();
		  }
	  
	  public void testJapanesePDF() throws Exception {
		    PDFDoc pdf = PDFDocFactory.getPDFDoc("test_ja.pdf", JA_HTML_CODE);
		    pdf.setEncoding("shift_jis");
		    save(pdf);
	  }
	  
	  // PDF generation for Arabic is not working
	  public void testArabicPDF() throws Exception {
		     //  PDFDoc pdf = PDFDocFactory.getPDFDoc("test_ab.pdf", AB_HTML_CODE); 
		    PDFDoc pdf = PDFDocFactory.getPDFDoc("test_ab.pdf", new String(AB_HTML_CODE.getBytes()));
		    // Tried UTF-8 , cp1256 encoding
		    pdf.setEncoding("ISO_8859_1");
		    save(pdf);
	  }
	  
	  public void testJapanesePDFFull() throws Exception {
		    PDFDoc pdf = PDFDocFactory.getPDFDoc("test_ja_full.pdf", readContent("ja_full.html", "UTF-8"));
		    pdf.setEncoding("shift_jis");
		    save(pdf);
		  }
	  
	  private String readContent(String fileName, String charsetName) throws IOException {
		    return IOUtils.toString(getClass().getResourceAsStream(fileName), charsetName);
		  }
	  
	  public void testKoreanPDF() throws Exception {
		    PDFDoc pdf = PDFDocFactory.getPDFDoc("test_kr.pdf", KR_HTML_CODE);
		    pdf.setEncoding("euc_kr");
		    save(pdf);
		  }
	  
	  public void testChinesePDF() throws Exception {
		    PDFDoc pdf = PDFDocFactory.getPDFDoc("test_cn.pdf", CN_HTML_CODE);
		    pdf.setEncoding("gb2312");
		    save(pdf);
		  }
	  
	  public static void main(String[] args) throws Exception {
		HTMLPDFDocTest html = new HTMLPDFDocTest();
		html.testEnglishPDFShort();
		html.testJapanesePDF();
		html.testArabicPDF();
		html.testChinesePDF();
		html.testKoreanPDF();
	}
}
