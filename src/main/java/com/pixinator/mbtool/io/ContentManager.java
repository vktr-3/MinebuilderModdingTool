package com.pixinator.mbtool.io;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ContentManager {
  // ############################################################
  // # VARIABLES
  // ############################################################

  private DirectoryType dirType;

  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  public ContentManager(DirectoryType dirType) {
    this.dirType = dirType;
  }

  // ############################################################
  // # METHODS
  // ############################################################

  public BufferedImage loadImage(String fileName) throws IOException {
    switch (this.dirType) {
      case PACKED:
        return ImageIO.read(this.getPackedStream(fileName));
      case FOLDER:
        return ImageIO.read(this.getFolderStream(fileName));
    }

    return null;
  }

  public Document loadXMLFile(String fileName) throws SAXException, IOException, ParserConfigurationException {
    Document dom;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    dom = db.parse(fileName);

    return dom;
  }

  private InputStream getPackedStream(String fileName) {
    return ContentManager.class.getResourceAsStream("/" + fileName);
  }

  private InputStream getFolderStream(String fileName) throws IOException {
    return new FileInputStream(new File(fileName));
  }

  // ############################################################
  // # ENUMS
  // ############################################################

  public enum DirectoryType {
    PACKED, FOLDER
  }

}
