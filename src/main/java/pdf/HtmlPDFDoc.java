package pdf;


import java.awt.Dimension;
import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URL;
import java.util.Properties;

import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;


public class HtmlPDFDoc extends PD4Constants implements PDFDoc, Serializable {

  /**
   * Logger for HtmlPDFDoc class
   */
  //private static Logger s_logger = LoggerFactory.getLogger(HtmlPDFDoc.class);

  public static final boolean DEFAULT_IMG_SPLIT = false;

  public static final int DEFAULT_HTML_WIDTH = 750;

  public static Insets DEFAULT_PAGE_INSETS = new Insets(20, 50, 10, 10);

  public static Dimension DEFAULT_PAGE_SIZE = A4;

  /**
   * Used to set the font to be used in the PDF by creating a <code>BODY</code>
   * tag
   */
  private static final String BEING_BODY_FONT_TAG = "BODY, TD {font-family: ";

  /**
   * Used to set the font to be used in the PDF by creating a <code>BODY</code>
   * tag
   */
  private static final String END_BODY_FONT_TAG = "}";

  private static Properties ENC_FONTS_MAP = new Properties();

  static {
    ENC_FONTS_MAP.setProperty("windows-1256", "Courier new");
    ENC_FONTS_MAP.setProperty("big5", "SimSun");
    ENC_FONTS_MAP.setProperty("windows-1254", "Courier new");
    ENC_FONTS_MAP.setProperty("windows-874", "Courier new");
    ENC_FONTS_MAP.setProperty("windows-1251", "Courier new");
    ENC_FONTS_MAP.setProperty("windows-1250", "Courier new");
    ENC_FONTS_MAP.setProperty("euc_kr", "NanumGothic");
    ENC_FONTS_MAP.setProperty("gb2312", "SimSun");
    ENC_FONTS_MAP.setProperty("shift_jis", "SazanamiGothic");
    ENC_FONTS_MAP.setProperty("euc-jp", "SazanamiGothic");
    ENC_FONTS_MAP.setProperty("windows-1253", "Courier new");
  }

  private String m_filename;

  private final String m_html;

  private boolean imgSplit;

  private String encoding;

  private int htmlWidth;

  private Insets insets;

  private Dimension size;

  public HtmlPDFDoc(String filename, String html) throws Exception {
    m_filename = filename;
    m_html = html;

    setDefaultSettings();
  }

	private void setDefaultSettings() throws Exception {
		enableImgSplit(DEFAULT_IMG_SPLIT);
	    setHtmlWidth(DEFAULT_HTML_WIDTH);
	    setPageInsets(DEFAULT_PAGE_INSETS);
	    setPageSize(DEFAULT_PAGE_SIZE);
	
	    // Check if the Fonts can be loaded (for backward compatibility)
	    try {
	      PD4ML pdfDocument = new PD4ML();
	      pdfDocument.useTTF("java:pd4ml_fonts", true);
	    } catch (FileNotFoundException fnfEx) {
	      throw new Exception("Fonts can not be loaded for PDF conversion");
	    }
	}

	public void enableImgSplit(boolean imgSplit) {
		// TODO Auto-generated method stub
		this.imgSplit = imgSplit;
	}
	
	public String getFilename() {
		// TODO Auto-generated method stub
		return m_filename;
	}
	
	public byte[] getPDFBytes() {
		
			byte[] pdfBytes;
			PD4ML pdfDocument = new PD4ML();
			
			StringReader sr = new StringReader(m_html);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			try {
			  // Load pd4fonts.properties in pd4ml_fonts.jar
			  pdfDocument.useTTF("java:pd4ml_fonts", true);
			
			  pdfDocument.enableImgSplit(imgSplit);
			  pdfDocument.addStyle(getEncodingBodyFontTag(encoding), true);
			  pdfDocument.setHtmlWidth(htmlWidth);
			  pdfDocument.setPageInsets(insets);
			  pdfDocument.setPageSize(size);
			
			  pdfDocument.render(sr, baos);
			
			  pdfBytes = baos.toByteArray();
			
			} catch (IOException ioE) {
			
			  pdfBytes = null;
			
			} finally {
			
			  try {
			
			    baos.flush();
			    baos.close();
			
			  } catch (IOException ioE) {
			
			  }
			
			}
			
			return pdfBytes;
	}

	private String getEncodingBodyFontTag(String encoding2) {
	
	    StringBuffer tag = new StringBuffer(BEING_BODY_FONT_TAG);
	
	    if (encoding != null) {
	      String font = ENC_FONTS_MAP.getProperty(encoding.toLowerCase());
	      tag.append((font != null) ? font : "");
	    }
	    return tag + END_BODY_FONT_TAG;
	}

	public void setEncoding(String encoding) {
		 if (encoding != null && !encoding.equals("iso-8859-1")) {
		      this.encoding = encoding;
		    }
	}
	
	public void setFilename(String filename) {
		 this.m_filename = filename;	
	}
	
	public void setHtmlWidth(int htmlWidth) {
		// TODO Auto-generated method stub
		this.htmlWidth = htmlWidth;
	}
	
	public void setPageInsets(Insets insets) {
		// TODO Auto-generated method stub
		this.insets = insets;
	}
	
	public void setPageSize(Dimension size) {
		// TODO Auto-generated method stub
		this.size = size;
	}

  

}
