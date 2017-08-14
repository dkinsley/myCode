

import java.awt.BorderLayout;  
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NutritionGatherer extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Google Searcher");
		
		BorderPane border = new BorderPane();
		HBox hbox = new HBox();
		border.setTop(hbox);
		
		TextField text = new TextField();
		hbox.getChildren().add(text);
		
		
		
		Scene scene = new Scene(border, 500,50);
		stage.setScene(scene);
		
		stage.sizeToScene();
		stage.show();
		
		
	}
	
//	private NutritionGatherer(){ 
//		super("Nutrition Gatherer");
//		addWindowListener(new WindowAdapter(){
//			public void windowClosing(WindowEvent e){
//				int result = JOptionPane.showConfirmDialog(NutritionGatherer.this, "Are you sure you want to exit?");
//				if(result == JOptionPane.YES_OPTION)
//					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//			}
//		});
//		
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setLayout(new BorderLayout());
//		setSize(1000,1000);
//		//setResizable(false);
//		
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		JMenu menu = new JMenu("File");
//		
//		JMenu help = new JMenu("Help");
//		
//		menuBar.add(menu);
//		menuBar.add(help);
//		
//		JMenuItem about = new JMenuItem("About...");
//		help.add(about);
//		about.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(NutritionGatherer.this, 
//						"<html><hr>Nutrition Gatherer<br>by David Kinsley<hr></html>",
//						"About...", JOptionPane.INFORMATION_MESSAGE);
//			}
//		});
//		
//		JMenuItem exit = new JMenuItem();
//		exit = menu.add("Exit");
//		exit.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(JOptionPane.showConfirmDialog(getParent(), "Do you want to exit?","Select an Option",JOptionPane.YES_NO_CANCEL_OPTION) 
//						== JOptionPane.YES_OPTION);
//					dispose();
//			}
//		});
//		
//		JPanel panel = new JPanel(new BorderLayout());
//		add(panel,BorderLayout.CENTER);
//		
//		JPanel textPanel = new JPanel(new FlowLayout());
//		panel.add(textPanel, BorderLayout.NORTH);
//		
//		JTextField searchField = new JTextField(50);
//		textPanel.add(searchField);
//		
//		JComboBox<String> combo = new JComboBox<String>();
//		combo.addItem("All");
//		combo.addItem("Image");
//		combo.addItem("Video");
//		combo.addItem("News");
//		textPanel.add(combo);
//		
//		JTextArea textArea = new JTextArea(10,10);
//		JScrollPane scroll = new JScrollPane(textArea);
//		panel.add(scroll);
//		textArea.setEnabled(false);
//		
//		
//		JButton search = new JButton("Search");
//		textPanel.add(search);
//		search.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				 // turn off htmlunit warnings
//			    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
//			    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
//			    
//				try (WebClient webClient = new WebClient()){
//					HtmlPage homePage = webClient.getPage("http://google.com");		//get the homepage
//					HtmlForm form = homePage.getForms().get(0);
//					
//					HtmlSubmitInput button = form.getInputByName("btnK");
//					HtmlTextInput textInput = form.getInputByName("q");
//					
//					if(!searchField.getText().equals("")){
//						textInput.setValueAttribute(searchField.getText());
//						button.click();
//					}
//					else
//						JOptionPane.showMessageDialog(NutritionGatherer.this, "Textbox cannot be null");
//					
//					//System.out.println("homePage Title " + searchField.getText());
//					
//					URL page2URL = form.click().getUrl();
//					HtmlPage page2 = webClient.getPage(page2URL);
//					System.out.println(page2URL);
//					
//					textArea.setEnabled(true);
//					
//					HtmlForm form2 = page2.getForms().get(0);
//					Document doc = Jsoup.connect(page2URL.toString()).get();
//					
//					switch(combo.getSelectedIndex()){
//					case 0:
//						
//						Elements links = doc.select("h3.r > a");
//						for(Element url : links){
//							String r = url + "\n";
//							textArea.append(r);
//						}
//					case 1:
//						Elements images = doc.select("div.rg_meta");
//						for(Element image : images){
//							String r = image + "\n";
//							textArea.append(r);
//						}
//					}
//					
//					
//					
//					//Document doc = Jsoup.connect(page2.);
//					
//					
//				} catch (FailingHttpStatusCodeException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (MalformedURLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		});
//		
//		
//		//pack();
//		
//		
//		
//	}
	public static void main(String[] args){
		Application.launch(args);
	}
	
	   

}
