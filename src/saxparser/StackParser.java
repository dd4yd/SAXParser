/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

/**
 *
 * @author daviddean
 */
class StackParser {
    
    public static XMLNode root;
    public static Stack<XMLNode> stack;
    public static XMLNode current;
    
    public static XMLNode parse(File file) throws Exception {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
             DefaultHandler handler = new DefaultHandler() {
                 @Override
                 public void startDocument() throws SAXException {
                     root = null;
                     stack = new Stack<>();
                }
                 
                 @Override
                 public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                     XMLNode node = new XMLNode();
                     
                     node.name = qName;
                     System.out.println(qName);
                     
                     node.attributes = new HashMap();
                     int size = attributes.getLength();
                     
                     for (int i = 0; i < size; i++) {
                         node.attributes.put(attributes.getQName(i), attributes.getValue(i));
                     }
                     stack.push(node);
                     
                     if (current != null) {
                         if (current.children == null) {
                             current.children = new ArrayList();
                         }
                             current.children.add(node);
                         
                     }
                     current = node; 
                 } // end start element
                 
                 @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    current.content = new String(ch, start, length);
                }
                 @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    XMLNode popped = stack.pop();
                    if (popped != null) {
                        if (stack.empty()) {
                            root = popped;
                            current = null;
                        } else {
                            current = stack.peek();
                        }
                    }
                }
             };
             
             saxParser.parse(file.getAbsoluteFile(), handler);
             
        } catch (Exception e) {
            throw e;
        }
        
        return root;
    }
    
}
