//David Kinsley 100%

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Phonebook class that stores a name, phone number, and whether that
 * number is a work or home number to an arrayList.  On the phonebook window, there is an
 * add, delete, before and next button.   
 * @author dkins_000
 *
 */
public class PhoneBook extends JFrame{
	private JMenu program;
	private JMenu help;
	
	private JLabel name;
	private JLabel phoneNumber;
	private JLabel entries;
	private JLabel entryCounter;
	private JPanel panelFive;
	
	private JMenuItem exit;
	private JMenuItem about;
	
	private JTextField phoneTBox;
	private JTextField nameTBox;
	
	private JPanel panelTwo;
	private JPanel panelThree;
	private JPanel panelFour;
	private JPanel panelOne;
	
	private JCheckBox home;
	private JCheckBox work;
	
	private JButton before;
	private JButton next;
	private JButton add;
	private JButton delete;
	
	private List<String> nameArray = new ArrayList<String>();
	private List<String> phoneArray = new ArrayList<String>();
	private List<Character> checkBoxes = new ArrayList<Character>();
	
	private int currentEntry = 0;
	private int totalEntry = nameArray.size();
	
	/**
	 * phonebook class that houses the menus, buttons, textfields,
	 * checkboxes and labels that are on the frame which is layed out
	 * in a 5x1 grid
	 */
	private PhoneBook(){
		super("PhoneBook");
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(PhoneBook.this, "Want to Exit?");
				if(result == JOptionPane.YES_OPTION){
						setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					}
				else
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
						
			}
		});
		setLayout(new GridLayout(5,1));
		
		//makes the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		program = new JMenu("Program");
		program.setName("program");
		
		help = new JMenu("Help");
		help.setName("help");
		
		menuBar.add(program);
		menuBar.add(help);
		exit = program.add("Exit");
		about = help.add("About...");
		
		/**
		 * event for the exit option.  if selected, a dialogue pops up 
		 * asking the user if he/she wants to exit.  if yes is selected,
		 * the window is closed, if no or cancel are selected, nothing happens
		 */
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(getParent(), "Want to exit?",
						"Select an Option", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		/**
		 * event for the about option.  if selected, a window pops up displaying
		 * the name of the program and the author of the program
		 */
		about.addActionListener(new ActionListener() {
			private void displayAboutDialog() {
				JOptionPane.showMessageDialog( PhoneBook.this, 
						"<html><hr>PhoneBook<br>by David Kinsley<hr></html>",
						"About...", JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void actionPerformed(ActionEvent event) {
				displayAboutDialog();
			}
		});

		//panel 1
		panelOne = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,5));
		entries = new JLabel ("Entries:");
		entryCounter = new JLabel("0/0");
		panelOne.add(entries);
		panelOne.add(entryCounter);
		add(panelOne);
		
		//panel 2
		panelTwo = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		name = new JLabel("Name");
		nameTBox = new JTextField(40);
		nameTBox.setName("nameTBox");
		panelTwo.add(name);
		panelTwo.add(nameTBox);
		add(panelTwo);
		
		//panel 3
		panelThree = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		phoneNumber = new JLabel("Phone");
		phoneTBox = new JTextField(20);
		phoneTBox.setName("phoneTBox");
		panelThree.add(phoneNumber);
		panelThree.add(phoneTBox);
		add(panelThree);
		
		//panel 4
		panelFour = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
		home = new JCheckBox("Home");
		home.setName("home");
		
		work = new JCheckBox("Work");
		work.setName("work");
		
		panelFour.add(home);
		panelFour.add(work);
		add(panelFour);
		
		//panel 5
		panelFive = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
		before = new JButton("Before");
			before.setName("before");
		next = new JButton("Next");
			next.setName("next");
		add = new JButton("Add");
			add.setName("add");
		delete = new JButton("Delete");
			delete.setName("delete");
		before.setEnabled(false);
		next.setEnabled(false);
		delete.setEnabled(false);
		panelFive.add(before);
		panelFive.add(next);
		panelFive.add(add);
		panelFive.add(delete);
		add(panelFive);
		
		/**
		 * event for add button.  when this button is pressed, the current info
		 * in the name and phone text boxes are saved to an arraylist.  this button
		 * is never disabled
		 */
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				currentEntry++;
				totalEntry++;
				
				if(currentEntry == totalEntry){
					nameArray.add(nameTBox.getText());
					phoneArray.add(phoneTBox.getText());
					if(home.isSelected() && work.isSelected())
						checkBoxes.add('B');
					else if(!home.isSelected() && !work.isSelected())
						checkBoxes.add('N');
					else if(home.isSelected())
						checkBoxes.add('H');
					else if(work.isSelected())
						checkBoxes.add('W');
					
					delete.setEnabled(true);
				}
				
				
				else if(currentEntry != totalEntry){
					currentEntry = totalEntry;
					
					nameArray.add(nameTBox.getText());
					phoneArray.add(phoneTBox.getText());
					if(home.isSelected() && work.isSelected())
						checkBoxes.add('B');
					else if(!home.isSelected() && !work.isSelected())
						checkBoxes.add('N');
					else if(home.isSelected())
						checkBoxes.add('H');
					else if(work.isSelected())
						checkBoxes.add('W');
					
					next.setEnabled(false);					
				}
				
				
				if(currentEntry > 1)
					before.setEnabled(true);
				else
					before.setEnabled(false);
				entryCounter.setText(currentEntry + "/" + totalEntry);
				

				
			}
		});
		
		/**
		 * event for the before button.  when this button is pressed, the
		 * info from the previous entry is displayed.  only enabled if
		 * more than 2 entries.
		 */
		before.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					currentEntry--;
					
					nameTBox.setText(nameArray.get(currentEntry - 1));
					phoneTBox.setText(phoneArray.get(currentEntry - 1));
					if(checkBoxes.get(currentEntry-1) == 'H'){
						home.setSelected(true);
						work.setSelected(false);
					}
					else if(checkBoxes.get(currentEntry-1) == 'W'){
						home.setSelected(false);
						work.setSelected(true);
					}
					else if(checkBoxes.get(currentEntry-1) == 'B'){
						home.setSelected(true);
						work.setSelected(true);
					}
					else if(checkBoxes.get(currentEntry-1) == 'N'){
						home.setSelected(false);
						work.setSelected(false);
					}
					next.setEnabled(true);
					if(currentEntry == 1)
						before.setEnabled(false);
					entryCounter.setText(currentEntry + "/" + totalEntry);
			}
			
		});
		
		/**
		 * event for the next button.  when pressed, the info from the next 
		 * entry is displayed.  this button is disabled if currentEntry and
		 * totalEntry are equal.
		 */
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentEntry++;
				
				nameTBox.setText(nameArray.get(currentEntry - 1));
				phoneTBox.setText(phoneArray.get(currentEntry - 1));
				if(checkBoxes.get(currentEntry-1) == 'H'){
					home.setSelected(true);
					work.setSelected(false);
				}
				else if(checkBoxes.get(currentEntry-1) == 'W'){
					home.setSelected(false);
					work.setSelected(true);
				}
				else if(checkBoxes.get(currentEntry-1) == 'B'){
					home.setSelected(true);
					work.setSelected(true);
				}
				else if(checkBoxes.get(currentEntry-1) == 'N'){
					home.setSelected(false);
					work.setSelected(false);
				}
				if(currentEntry == totalEntry)
					next.setEnabled(false);
				before.setEnabled(true);
				entryCounter.setText(currentEntry + "/" + totalEntry);
			}
			
		});
		
		/**
		 * when this button is pressed and currententry = totalentry, the current entry 
		 * is deleted and the previous entry is displayed.  if currententry < totalentry, 
		 * then the info from currententry+1 is displayed.  if currententry && totalentry == 1,
		 * then all fields clear and the arraylists are emptied.  this button is disabled when 
		 * the window is first opened and becomes enabled when an entry has been submitted
		 */
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentEntry == 1 && totalEntry == 1){
					
					
					nameArray.clear();
					phoneArray.clear();
					checkBoxes.clear();
					
					currentEntry = 0;
					totalEntry = 0;
					
					nameTBox.setText("");
					phoneTBox.setText("");
					home.setSelected(false);
					work.setSelected(false);
					next.setEnabled(false);
					before.setEnabled(false);
					delete.setEnabled(false);
					entryCounter.setText(currentEntry + "/" + totalEntry);
				}
				else if(currentEntry == totalEntry){
					
					nameArray.remove(currentEntry-1);
					phoneArray.remove(currentEntry-1);
					checkBoxes.remove(currentEntry-1);
					
					currentEntry--;
					totalEntry--;
					
					nameTBox.setText(nameArray.get(currentEntry-1));
					phoneTBox.setText(phoneArray.get(currentEntry-1));
					if(checkBoxes.get(currentEntry-1) == 'H'){
						home.setSelected(true);
						work.setSelected(false);
					}
					else if(checkBoxes.get(currentEntry-1) == 'W'){
						home.setSelected(false);
						work.setSelected(true);
					}
					else if(checkBoxes.get(currentEntry-1) == 'B'){
						home.setSelected(true);
						work.setSelected(true);
					}
					else if(checkBoxes.get(currentEntry-1) == 'N'){
						home.setSelected(false);
						work.setSelected(false);
					}
					if(currentEntry == 1)
						before.setEnabled(false);
					entryCounter.setText(currentEntry + "/" + totalEntry);
				}
				else if(currentEntry != totalEntry){
					totalEntry--;
					
					nameTBox.setText(nameArray.get(currentEntry));
					phoneTBox.setText(phoneArray.get(currentEntry));
					if(checkBoxes.get(currentEntry) == 'H'){
						home.setSelected(true);
						work.setSelected(false);
					}
					else if(checkBoxes.get(currentEntry) == 'W'){
						home.setSelected(false);
						work.setSelected(true);
					}
					else if(checkBoxes.get(currentEntry) == 'B'){
						home.setSelected(true);
						work.setSelected(true);
					}
					else if(checkBoxes.get(currentEntry) == 'N'){
						home.setSelected(false);
						work.setSelected(false);
					}
					if(currentEntry == 1 && totalEntry == 1)
						next.setEnabled(false);
					else if(currentEntry == totalEntry)
						next.setEnabled(false);
					else
						next.setEnabled(true);
					entryCounter.setText(currentEntry + "/" + totalEntry);
					nameArray.remove(currentEntry-1);
					phoneArray.remove(currentEntry-1);
					checkBoxes.remove(currentEntry-1);
					
				}
			}
			 
		});
	}
	
	
	public static void main(String[] args) {
		PhoneBook frame = new PhoneBook();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(600,400);
		frame.setVisible(true);
	}

}