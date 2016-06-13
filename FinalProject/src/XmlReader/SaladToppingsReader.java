package XmlReader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DataStructures.BinaryTree;
import DataStructures.BinaryTreeNode;
import DataStructures.DefaultBinaryTree;
import DataStructures.DefaultBinaryTreeNode;


public class SaladToppingsReader {

	private File xmlFile;

	/**
	 * constructor
	 */
	public SaladToppingsReader(String filename){
		this.xmlFile = new File(filename);
	}

	/**
	 * buildGameTree
	 * @return GameTree based on XML file.
	 */
	public BinaryTree buildGameTree(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			return parseDecisionTree(document);
		}
		catch(SAXException sxe){
			Exception x = sxe;
			if(sxe.getException()!=null)
				x = sxe.getException();
			x.printStackTrace();
		}
		catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		return null;
	}

	/**
	 * Parses XML Document
	 * @return GameTree
	 */
	public BinaryTree parseDecisionTree(Document document){
		Element xmlRootElement = (Element)document.getDocumentElement();
		BinaryTree myGameTree = new DefaultBinaryTree();
		//assume root element is a question
		myGameTree.setRoot(parseQuestionNode(xmlRootElement));
		return myGameTree;
	}

	/**
	 * Parses a question element.
	 */
	private BinaryTreeNode parseQuestionNode(Element element){
		if(!element.getTagName().equals("question")){
			System.err.println("Error: expection question tag");
		}

		String questionText = element.getAttribute("text");
		BinaryTreeNode questionNode = new DefaultBinaryTreeNode();
		questionNode.setData(questionText);

		NodeList children = element.getChildNodes();
		for(int i = 0;i<children.getLength();i++){
			if(children.item(i) instanceof Element){
				Element childNode = (Element)children.item(i);
				String user_response = childNode.getAttribute("user_answer");
				if(user_response.equals("yes")){
					questionNode.setLeftChild(parseAnswerNode(childNode));
				}
				else if(user_response.equals("no")){
					questionNode.setRightChild(parseAnswerNode(childNode));
				}
				else{
					System.err.println("Error in XML file: not a valid answer");
				}
			}
		}
		return questionNode;

	}

	private BinaryTreeNode parseAnswerNode(Element element){
		if(!element.getTagName().equals("answer")){
			System.err.println("Error: expecting answer tag");
		}

		NodeList children = element.getChildNodes();
		for(int i = 0; i<children.getLength();i++){
			if(children.item(i) instanceof Element){
				Element child = (Element)children.item(i);

				//base case: if child is Thing element 
				if(child.getTagName().equals("thing")){
					BinaryTreeNode node = new DefaultBinaryTreeNode();
					node.setData(child.getAttribute("value"));
					return node;
				}
				else if(child.getTagName().equals("question")){
					return parseQuestionNode(child);
				}
				else{
					System.err.println("Error: child of answer should be a thing or a question");
				}
			}
		
		}
		return null;
	}
	
	/**
	 * main method: reads in the selected XML file, builds a tree, prints the tree.
	 */
	
	public static void main(String[]args){
		SaladToppingsReader tr = new SaladToppingsReader("SaladToppings.xml");
		BinaryTree guessingGame = tr.buildGameTree();
		System.out.println("Preorder: " + guessingGame.preorderString());
	}
}