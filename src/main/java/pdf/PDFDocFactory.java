package pdf;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Factory to generate PDF Document Instances
 * 
 * @author dev.sep.dv2.arc.frw.tlawrence
 * @version 1.0
 */
public abstract class PDFDocFactory {

  /**
   * Returns a new PDF Document instance based on the filename and html input
   * 
   * @param filename
   *          Filename for the PDF
   * @param html
   *          The HTML content of the PDF
   * @return new PDF Document instance
   * @throws BadFilenameException
   *           This exception is thrown when the filename passed is not valid
   *           for a PDF
   */
  public static PDFDoc getPDFDoc(String filename, String html) throws Exception {

    if (!isValidPDFFilename(filename)) {
      throw new Exception("Filename:" + filename +
          " is not valid for a PDFDoc. Must match regexp \"[\\\\w]+\\\\.pdf\"");
    }

    // return new HtmlPDFDoc(filename, html);
    return new HtmlPDFDoc(filename, html);

  }

  /**
   * Used to check if a PDF filename is valid
   * 
   * @param filename
   * @return
   */
  private static boolean isValidPDFFilename(String filename) {

    Pattern p = Pattern.compile("[\\w]+\\.pdf");
    Matcher m = p.matcher(filename);
    return m.matches();

  }

}
