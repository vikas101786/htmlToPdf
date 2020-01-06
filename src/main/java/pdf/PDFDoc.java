package pdf;

/*
 * Amadeus Confidential Information:
 * Unauthorized use and disclosure strictly forbidden.
 * @1998-2007 - Amadeus s.a.s - All Rights Reserved.
 */
/**
 * 
 */
import java.awt.Dimension;
import java.awt.Insets;

/**
 * Interface for creating a PDF Document
 * 
 * This class creates a PDF, allowing the definition of certain attributes to
 * the PDF. The PDF can then be returned as an Array of Bytes.
 * 
 * @author dev.sep.dv2.arc.frw.tlawrence
 * @version 1.0
 */
public interface PDFDoc {

  void enableImgSplit(boolean imgSplit);

  /**
   * Get on the filename for the PDF
   * 
   * @return PDF's filename
   */
  String getFilename();

  /**
   * Used to retrieve the <code>byte[]</code> representing the PDF
   * 
   * @return
   */
  byte[] getPDFBytes();

  void setEncoding(String encoding);

  void setFilename(String filename);

  void setHtmlWidth(int htmlWidth);

  void setPageInsets(Insets insets);

  void setPageSize(Dimension size);

}
