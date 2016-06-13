package Game.Prespecified;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import DataStructures.BinaryTree;
import DataStructures.BinaryTreeNode;
import DataStructures.LinkedList;
import DataStructures.LinkedListNode;
import XmlReader.SaladToppingsReader;

/**
 * PrespecifiedGUI creates 20 question game with prespecified answer
 * @author phyu22m
 *
 */
public class PrespecifiedGUI extends JPanel{
	//creates reader object that reads the xml file
	SaladToppingsReader saladToppings = new SaladToppingsReader("SaladToppings.xml");
	//storing the binary tree that was created from the reader
	BinaryTree guessingGame = saladToppings.buildGameTree();
	BinaryTreeNode gameNode;
	JLabel questionLabel = new JLabel();
	JLabel menuLabel = new JLabel();
	//declaring global variables
	JPanel southPanel;
	JPanel centerPanel;
	JButton yesButton;
	JButton noButton;
	JPanel listPanel;
	JPanel menuPanel;
	JPanel restartPanel;
	LinkedList listLL;

	/**
	 * constructor
	 */
	public PrespecifiedGUI(){
		//inherit all the methods
		super();
		initPanel();
	}

	/**
	 * main method that creates the program
	 * @param args
	 */
	public static void main(String[] args){
		//initialize a new JFrame 
		JFrame gameFrame = new JFrame("Moe's Salad Toppings 20 Questions");
		gameFrame.add(new PrespecifiedGUI() );
		//set the size of the frame
		gameFrame.setSize(600,700);
		gameFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gameFrame.setVisible( true );

	}

	/**
	 * add all the panels to the frame
	 */
	public void initPanel(){
		//setting a border layout
		setLayout(new BorderLayout());
		add(northPanel(),BorderLayout.NORTH);
		validate();
		add(centerPanel(),BorderLayout.CENTER);
		add(southPanel(),BorderLayout.SOUTH);
	}

	/**
	 * creates northPanel that contains image
	 * @return northPanel
	 */
	private JPanel northPanel(){
		//initialize JPanel called northPanel
		JPanel northPanel = new JPanel();
		//initialize JLabel called saladLabel
		JLabel saladLabel = new JLabel();
		//adding image to saladLabel
		saladLabel.setIcon(new ImageIcon("salad.jpg"));
		northPanel.add(saladLabel);
		validate();
		return northPanel;
	}

	/**
	 * creates JPanel that belongs to the center of the frame
	 * centerPanel contains instruction of the game, the list and start game button
	 * @return centerPanel
	 */
	private JPanel centerPanel(){
		//initialize
		centerPanel = new JPanel(new BorderLayout());
		//adding Panel according to their place
		centerPanel.add(menuPanel(),BorderLayout.NORTH);
		centerPanel.add(createlistPanel(),BorderLayout.CENTER);
		centerPanel.add(startButton(),BorderLayout.SOUTH);
		return centerPanel;
	}

	/**
	 * JPanel with instruction
	 * @return
	 */
	private JPanel menuPanel(){
		//initialize
		menuPanel = new JPanel();
		//initialize myFont and set it to a desired font
		Font myFont = new Font("Century Gothic",Font.PLAIN, 20);
		//setting the font of the menuLabel to myFont
		menuLabel.setFont(myFont);
		menuLabel.setText("<html>Welcome to Twenty Questions!<br>Pick one thing from the list below and we will try to guess it!</html>");
		menuPanel.add(menuLabel);
		return menuPanel;
	}
	
	

	/**
	 * @return panel with a list of choices 
	 */
	private JPanel createlistPanel(){
		//initialize a new panel with gridlayout
		listPanel = new JPanel(new GridLayout(0,4));
		//edited the preorderTraversal in the Default Binary Tree so that it will only print out the leaves
		//stored the list of leaves in listLL
		listLL = guessingGame.preorderTraversal();
		//setting node as the first leaf
		LinkedListNode<String> node = listLL.getFirstNode();
		while(node!=null){
			//set node's data to a new JLabel and add it to the listPanel 
			listPanel.add(new JLabel(node.getData()));
			//update the node to the next node
			node = node.getNext();
		}
		return listPanel;
	}
	
	/**
	 * JButton that starts the game 
	 * @return
	 */
	private JButton startButton(){
		//initialize 
		final JButton startButton = new JButton("Start Game");
		startButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//start showing the questions
						southPanel.setVisible(true);
						//make startButton go away
						startButton.setVisible(false);
					}

				});
		return startButton;
	}


	/**
	 * restart JPanel contains restart Button and when the button is clicked, restarts the game
	 * @return JPanel
	 */
	public JPanel restartPanel()
	{	
		//initialize
		restartPanel = new JPanel();
		//initialize button called restartButton
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						//when restartButton is clicked, remove everything on the frame
						removeAll();	
						//add everything again
						initPanel();						revalidate();
						repaint();
					}

				});
		
		//adding restartButton to the restartPanel
		restartPanel.add(restartButton);
		return restartPanel;
	}

	/**
	 * JPanel that belongs to the south of the panel
	 * @return southPanel that contains the list of questions and buttons for user to answer
	 */
	private JPanel southPanel(){
		//initialize
		southPanel = new JPanel(new BorderLayout());
		southPanel.add(questionPanel(),BorderLayout.NORTH);
		southPanel.add(buttonPanel(),BorderLayout.SOUTH);
		southPanel.setVisible(false);
		return southPanel;
	}

	
	/**
	 * changes the font of the questionLabel
	 */
	public void setFont(){
		//initialize
		Font myFont = new Font("Century Gothic",Font.BOLD, 20);
		//setting questionLabel to the desired font
		questionLabel.setFont(myFont);
	}
	
	/**
	 * display the question 
	 * @return JPanel that displays the question 
	 */
	private JPanel questionPanel(){
		//initialize
		JPanel questionPanel = new JPanel(new BorderLayout());
		//setting the gameNode to be the root of the guessing game binary tree
		gameNode = guessingGame.getRoot();
		setFont();
		//setting the questionLabel to root of the tree
		questionLabel.setText(gameNode.getData().toString());
		//centering the questionLabel
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionPanel.add(questionLabel,BorderLayout.CENTER);
		return questionPanel;

	}
	
	/**
	 * contains yes and no Button
	 * @return JPanel that contains buttons
	 */
	private JPanel buttonPanel(){
		//initialize
		JPanel buttonPanel = new JPanel(new GridLayout(1,1));
		buttonPanel.add(yesButton());
		buttonPanel.add(noButton());
		return buttonPanel;
	}

	/**
	 * JButton that displays different questions as the user clicked
	 * @return JButton
	 */
	private JButton yesButton(){
		yesButton = new JButton("Yes");
		yesButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(!gameNode.isLeaf()){
							//set the gameNode to be the left child of the gameNode
							gameNode = gameNode.getLeftChild();
							//checking the updated gameNode
							if(!gameNode.isLeaf()){
								setFont();
								questionLabel.setText(gameNode.getData().toString());
							}
							else{//gameNode is a leaf
								setFont();
								//asking the user if they guess the leaf
								questionLabel.setText("Did you guess " + gameNode.getData().toString() + "?");
							}
						}
						//when gameNode is a leaf and the user clicked yes 
						else if(gameNode.isLeaf()){
							setFont();
							//the computer guessed the user's answer
							questionLabel.setText("We guessed it right!");
							//making the button not clickable
							yesButton.setEnabled(false);
							noButton.setEnabled(false);
							centerPanel.setVisible(false);
							//add restartPanel for the user to play the game again
							add(restartPanel(),BorderLayout.CENTER);
						}

					}

				});
		return yesButton;
	}


	/**
	 * JButton that displays different questions as the user clicked
	 * @return JButton
	 */
	private JButton noButton(){
		noButton = new JButton("No");
		noButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(!gameNode.isLeaf()){
							//set the gameNode to be the right child of the gameNode
							gameNode = gameNode.getRightChild();
							//checking the updated gameNode
							if(!gameNode.isLeaf()){
								setFont();
								questionLabel.setText(gameNode.getData().toString());
							}
							else{
								//gameNode is a leaf
								setFont();
								//asking the user if they guess the leaf
								questionLabel.setText("Did you guess " + gameNode.getData().toString() + "?");
							}
						}
						//when gameNode is a leaf and the user clicked no 
						else if(gameNode.isLeaf()){
							setFont();
							//default message if the user was thinking something else
							questionLabel.setText("It should have been " + gameNode.getData().toString() + " though :(");
							//making the button not clickable
							yesButton.setEnabled(false);
							noButton.setEnabled(false);
							centerPanel.setVisible(false);
							//add restartPanel for the user to play the game again
							add(restartPanel(),BorderLayout.CENTER);
						}
					}

				});
		return noButton;
	}

}
