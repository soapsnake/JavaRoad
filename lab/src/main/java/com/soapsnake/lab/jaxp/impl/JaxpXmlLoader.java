package com.soapsnake.lab.jaxp.impl;

import com.soapsnake.lab.jaxp.XmlLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class JaxpXmlLoader implements XmlLoader {

    public static void main(String[] args) {
        JaxpXmlLoader loader = new JaxpXmlLoader();
        String path = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
        loader.loadFromXml(path + "/test.xml");
    }

    @Override
    public Object loadFromXml(String fileName) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);

            System.out.println("getElementById: " + document.getElementById("flower"));
            System.out.println("getElementsByTagName: " + document.getElementsByTagName("flower"));

            NodeList nodeList = document.getElementsByTagName("flower");

            Node root = document.getChildNodes().item(0);
            parseChildNode(root);

//			for (int i = 0; i < nodeList.getLength(); i++) {
//				Node node = nodeList.item(i);
//				System.out.println("item = " + node);
//				System.out.println(node.getChildNodes().item(0).getTextContent());
//				System.out.println(node.getBaseURI());
//				System.out.println(node.getAttributes());
//				System.out.println(node.getFirstChild().getLocalName());
//				System.out.println(node.getLastChild().getLocalName());
//				System.out.println(node.getNamespaceURI());
//			}
//
//			System.out.println("nodename = " + document.getNodeName());
//			System.out.println("nodetype = " + document.getNodeType());
//			System.out.println("nodevalue = " + document.getNodeValue());
//


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * <flowerhouse>
     * <flower type = "xxController">
     * <flowerName>kevin</flowerName>
     * <age>18</age>
     * <douzi>good</douzi>
     * </flower>
     *
     * <flower type = "xxService">
     * <flowerName>mengyao</flowerName>
     * <age>28</age>
     * <douzi>bad</douzi>
     * </flower>
     * </flowerhouse>
     */
    private void parseChildNode(Node root) {
        //打印节点名称
        if (!root.getNodeName().equals("#text")) {
            System.out.println("nodeName = " + root.getNodeName() + " && " + "nodeContent=" + root.getTextContent() + " && " + " nodeValue= " + root.getNodeValue());
            if (null != root.getAttributes()) {
                for (int i = 0; i < root.getAttributes().getLength(); i++) {
                    System.out.println("attr.name = " + root.getAttributes().item(i).getNodeName());   //type
                    System.out.println("attr.value = " + root.getAttributes().item(i).getNodeValue());   //xxController
                }
            }
            NodeList cList = root.getChildNodes();
            for (int i = 0; i < cList.getLength(); i++) {
                Node cNode = cList.item(i);
                parseChildNode(cNode);
            }
        }
    }
}
