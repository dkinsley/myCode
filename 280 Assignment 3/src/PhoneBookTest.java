//David Kinsley 100%
import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import org.junit.Test;

import edu.cnu.cs.gooey.Gooey;
import edu.cnu.cs.gooey.GooeyDialog;
import edu.cnu.cs.gooey.GooeyFrame;

public class PhoneBookTest {
	
	@Test
	public void testDefaultWindow() {
		Gooey.capture(new GooeyFrame() {

			@Override
			public void invoke() {
				PhoneBook.main(null);
			}

			@Override
			public void test(JFrame frame) {
				JTextField name = Gooey.getComponent(frame, JTextField.class, "nameTBox");
				JTextField phone = Gooey.getComponent(frame, JTextField.class, "phoneTBox");
				JCheckBox home = Gooey.getComponent(frame, JCheckBox.class, "home");
				JCheckBox work = Gooey.getComponent(frame, JCheckBox.class, "work");
				
				JButton before = Gooey.getComponent(frame, JButton.class, "before");
				JButton next = Gooey.getComponent(frame, JButton.class, "next");
				JButton add = Gooey.getComponent(frame, JButton.class, "add");
				JButton delete = Gooey.getComponent(frame, JButton.class, "delete");
				
				assertEquals(name.getText(), "");
				assertEquals(phone.getText(), "");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), false);
				assertEquals(before.isEnabled(), false);
				assertEquals(next.isEnabled(), false);
				assertEquals(add.isEnabled(), true);
				assertEquals(delete.isEnabled(), false);
			}
			
		});
	}
	
	@Test
	public void testMenuBar() {
		Gooey.capture(new GooeyFrame() {
			
			@Override
			public void invoke() {
				PhoneBook.main(null);
			}
			 
			@Override
			public void test(JFrame frame){
				JMenuBar menuBar = Gooey.getMenuBar(frame);
				JMenu program = Gooey.getMenu(menuBar, "Program");
				JMenu help = Gooey.getMenu(menuBar, "Help");
				JMenuItem exit = Gooey.getMenu(program, "Exit");
				JMenuItem about = Gooey.getMenu(help, "About...");

				List<JMenu> menu = Gooey.getMenus(menuBar);
				List<JMenuItem> items;

				assertEquals(2, menu.size());
				assertTrue(menu.contains(program));
				assertTrue(menu.contains(help));

				items = Gooey.getMenus(program);
				assertEquals(1, items.size());
				assertTrue(items.contains(exit));

				items = Gooey.getMenus(help);
				assertEquals(1, items.size());
				assertTrue(items.contains(about));

				frame.dispose();
			}
		});
	}
	
	@Test
	public void testExitDialog() {
		Gooey.capture(new GooeyFrame() { 
			
			@Override
			public void invoke(){
				PhoneBook.main(null);
			}
			
			@Override
			public void test(JFrame frame){
				JMenuBar menuBar = Gooey.getMenuBar(frame);
				JMenu program = Gooey.getMenu(menuBar, "Program");
				JMenuItem exit = Gooey.getMenu(program, "Exit");
				program.doClick();
				
				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						exit.doClick();
					}
					
					@Override
					public void test(JDialog dialog) {
						assertTrue(dialog.isShowing());
						assertEquals("Select an Option", dialog.getTitle());
						Gooey.getLabel(dialog, "Want to exit?");
						Gooey.getButton(dialog, "Yes");
						Gooey.getButton(dialog, "No");
						List<JButton> count = Gooey.getComponents(dialog,
								JButton.class);
						assertEquals(3, count.size());
						Gooey.getButton(dialog, "Yes").doClick();
					}
				});
			}
		});
	}
	
	@Test
	public void testAboutMenu(){
		Gooey.capture(new GooeyFrame() {
			
			@Override
			public void invoke() {
				PhoneBook.main(null);
			}
			
			@Override
			public void test(JFrame frame) {
				JMenuBar menuBar = Gooey.getMenuBar(frame);
				JMenu help = Gooey.getMenu(menuBar, "Help");
				JMenuItem about = Gooey.getMenu(help, "About...");
				help.doClick();
				
				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						about.doClick();
					}
					
					@Override
					public void test(JDialog dialog){
						assertTrue(dialog.isShowing());
						Gooey.getLabel(dialog,
								"<html><hr>PhoneBook<br>by David Kinsley<hr></html>");
						JButton ok = Gooey.getButton(dialog, "OK");
						List<JButton> count = Gooey.getComponents(dialog,
								JButton.class);
						assertEquals(1, count.size());
						ok.doClick();
					}
				});
				frame.dispose();
			}
		});
	}
	
	@Test
	public void testAdd() {
		Gooey.capture(new GooeyFrame() {
			
			@Override
			public void invoke() {
				PhoneBook.main(null);
			}
			@Override
			public void test(JFrame frame){
				JTextField name = Gooey.getComponent(frame, JTextField.class, "nameTBox");
				JTextField phone = Gooey.getComponent(frame, JTextField.class, "phoneTBox");
				JCheckBox home = Gooey.getComponent(frame, JCheckBox.class, "home");
				JCheckBox work = Gooey.getComponent(frame, JCheckBox.class, "work");

				JButton before = Gooey.getComponent(frame, JButton.class, "before");
				JButton next = Gooey.getComponent(frame, JButton.class, "next");
				JButton add = Gooey.getComponent(frame, JButton.class, "add");
				 
				name.setText("David");
				phone.setText("123456789");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				
				name.setText("Josh");
				phone.setText("987654321");
				home.setSelected(false);
				work.setSelected(true);
				
				add.doClick();
				
				name.setText("Sammy");
				phone.setText("10101010101");
				home.setSelected(true);
				work.setEnabled(true);
				
				add.doClick();
				before.doClick();
				assertEquals(name.getText(), "Josh");
				assertEquals(phone.getText(), "987654321");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), true);
				
				
				name.setText("Alex");
				phone.setText("999999999");
				home.setSelected(false);
				work.setSelected(false);
				
				add.doClick();
				
				name.setText("Vinny");
				phone.setText("1111111111");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				
				//vinny
				assertEquals(name.getText(), "Vinny");
				assertEquals(phone.getText(), "1111111111");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), false);
				
				//alex
				before.doClick();
				assertEquals(name.getText(), "Alex");
				assertEquals(phone.getText(), "999999999");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), false);
				
				//Sammy
				before.doClick();
				assertEquals(name.getText(), "Sammy");
				assertEquals(phone.getText(), "10101010101");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), true);
				
				//Josh
				before.doClick();
				
				
				before.doClick();
				assertEquals(name.getText(), "David");
				assertEquals(phone.getText(), "123456789");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), false);
			}
		});
	}
	@Test
	public void testBeforeandNextButtons(){
		Gooey.capture(new GooeyFrame() {
			
			@Override
			public void invoke() {
				PhoneBook.main(null);
			}
			
			@Override
			public void test(JFrame frame) {

				JTextField name = Gooey.getComponent(frame, JTextField.class, "nameTBox");
				JTextField phone = Gooey.getComponent(frame, JTextField.class, "phoneTBox");
				JCheckBox home = Gooey.getComponent(frame, JCheckBox.class, "home");
				JCheckBox work = Gooey.getComponent(frame, JCheckBox.class, "work");

				JButton before = Gooey.getComponent(frame, JButton.class, "before");
				JButton next = Gooey.getComponent(frame, JButton.class, "next");
				JButton add = Gooey.getComponent(frame, JButton.class, "add");
				
				name.setText("David");
				phone.setText("123456789");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				
				name.setText("Josh");
				phone.setText("987654321");
				home.setSelected(false);
				work.setSelected(true);
				
				add.doClick();
				
				name.setText("Sammy");
				phone.setText("10101010101");
				home.setSelected(true);
				work.setEnabled(true);
				
				add.doClick();
				
				name.setText("Alex");
				phone.setText("999999999");
				home.setSelected(false);
				work.setSelected(false);
				
				add.doClick();
				
				name.setText("Vinny");
				phone.setText("1111111111");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				
				before.doClick();
				before.doClick();
				before.doClick();
				before.doClick();
				
				assertEquals(name.getText(), "David");
				assertEquals(phone.getText(), "123456789");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), false);
				
				next.doClick();
				
				assertEquals(name.getText(), "Josh");
				assertEquals(phone.getText(), "987654321");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), true);
				
				next.doClick();
				
				assertEquals(name.getText(), "Sammy");
				assertEquals(phone.getText(), "10101010101");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), true);
				
				next.doClick();
				
				assertEquals(name.getText(), "Alex");
				assertEquals(phone.getText(), "999999999");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), false);
				
				next.doClick();
				
				assertEquals(name.getText(), "Vinny");
				assertEquals(phone.getText(), "1111111111");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), false);
			}
		});
	}
	@Test
	public void MoreComprehensiveAddTest() {
		Gooey.capture(new GooeyFrame() {
			
			@Override
			public void invoke() {
				PhoneBook.main(null);
			} 
			@Override
			public void test(JFrame frame){
				JTextField name = Gooey.getComponent(frame, JTextField.class, "nameTBox");
				JTextField phone = Gooey.getComponent(frame, JTextField.class, "phoneTBox");
				JCheckBox home = Gooey.getComponent(frame, JCheckBox.class, "home");
				JCheckBox work = Gooey.getComponent(frame, JCheckBox.class, "work");

				JButton before = Gooey.getComponent(frame, JButton.class, "before");
				JButton next = Gooey.getComponent(frame, JButton.class, "next");
				JButton add = Gooey.getComponent(frame, JButton.class, "add");
				  
				name.setText("David");
				phone.setText("123456789");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				
				name.setText("Josh");
				phone.setText("987654321");
				home.setSelected(false);
				work.setSelected(true);
				
				add.doClick();
				
				name.setText("Sammy");
				phone.setText("10101010101");
				home.setSelected(true);
				work.setEnabled(true);
				
				add.doClick();
				before.doClick();
				
				assertEquals(name.getText(), "Josh");
				assertEquals(phone.getText(), "987654321");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), true);
				
				
				name.setText("Alex");
				phone.setText("999999999");
				home.setSelected(false);
				work.setSelected(false);
				
				add.doClick();
				before.doClick();
				
				assertEquals(name.getText(), "Sammy");
				assertEquals(phone.getText(), "10101010101");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), true);
				
				name.setText("Vinny");
				phone.setText("1111111111");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				before.doClick();
				
				name.setText("Mom");
				phone.setText("4444444444");
				home.setSelected(false);
				work.setSelected(true);
				
				add.doClick();
				before.doClick();
				
				name.setText("Dad");
				phone.setText("5555555555");
				home.setSelected(true);
				work.setSelected(true);
				
				add.doClick();
			}
		});
	}
	
	@Test
	public void deleteTest() {
		Gooey.capture(new GooeyFrame() {
			
			@Override
			public void invoke() {
				PhoneBook.main(null);
			} 
			@Override
			public void test(JFrame frame){
				JTextField name = Gooey.getComponent(frame, JTextField.class, "nameTBox");
				JTextField phone = Gooey.getComponent(frame, JTextField.class, "phoneTBox");
				JCheckBox home = Gooey.getComponent(frame, JCheckBox.class, "home");
				JCheckBox work = Gooey.getComponent(frame, JCheckBox.class, "work");

				JButton before = Gooey.getComponent(frame, JButton.class, "before");
				JButton next = Gooey.getComponent(frame, JButton.class, "next");
				JButton add = Gooey.getComponent(frame, JButton.class, "add");
				JButton delete = Gooey.getComponent(frame, JButton.class, "delete");
				 
				name.setText("David");
				phone.setText("123456789");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				
				name.setText("Josh");
				phone.setText("987654321");
				home.setSelected(false);
				work.setSelected(true);
				
				add.doClick();
				
				
				name.setText("Sammy");
				phone.setText("10101010101");
				home.setSelected(true);
				work.setEnabled(true);
				
				add.doClick();
				//go back to josh
				before.doClick();
				//delete josh
				delete.doClick();
				
				assertEquals(name.getText(), "Sammy");
				assertEquals(phone.getText(), "10101010101");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), true);
				
				
				
				name.setText("Alex");
				phone.setText("999999999");
				home.setSelected(false);
				work.setSelected(false);
				
				//add alex
				add.doClick();
				//go back to sammy
				before.doClick();
				//delete sammy
				delete.doClick();
				
				assertEquals(name.getText(), "Alex");
				assertEquals(phone.getText(), "999999999");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), false);
				
				
				
				name.setText("Vinny");
				phone.setText("1111111111");
				home.setSelected(true);
				work.setSelected(false);
				
				add.doClick();
				before.doClick();
				delete.doClick();
				
				assertEquals(name.getText(), "Vinny");
				assertEquals(phone.getText(), "1111111111");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), false);
				
				name.setText("Mom");
				phone.setText("4444444444");
				home.setSelected(false);
				work.setSelected(true);
				
				add.doClick();
				before.doClick();
				delete.doClick();
				
				assertEquals(name.getText(), "Mom");
				assertEquals(phone.getText(), "4444444444");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), true);
				
				name.setText("Dad");
				phone.setText("5555555555");
				home.setSelected(true);
				work.setSelected(true);
				
				add.doClick();
				delete.doClick();
				
				assertEquals(name.getText(), "Mom");
				assertEquals(phone.getText(), "4444444444");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), true);
				
				delete.doClick();
				
				assertEquals(name.getText(), "David");
				assertEquals(phone.getText(), "123456789");
				assertEquals(home.isSelected(), true);
				assertEquals(work.isSelected(), false);
				
				delete.doClick();
				
				assertEquals(name.getText(), "");
				assertEquals(phone.getText(), "");
				assertEquals(home.isSelected(), false);
				assertEquals(work.isSelected(), false);
				assertEquals(before.isEnabled(), false);
				assertEquals(next.isEnabled(), false);
				assertEquals(add.isEnabled(), true);
				assertEquals(delete.isEnabled(), false);				
				
			}
		});
	}
	
	@Test
	public void MoreDeleteTests(){
			Gooey.capture(new GooeyFrame() {
				
				@Override
				public void invoke() {
					PhoneBook.main(null);
				} 
				@Override
				public void test(JFrame frame){
					JTextField name = Gooey.getComponent(frame, JTextField.class, "nameTBox");
					JTextField phone = Gooey.getComponent(frame, JTextField.class, "phoneTBox");
					JCheckBox home = Gooey.getComponent(frame, JCheckBox.class, "home");
					JCheckBox work = Gooey.getComponent(frame, JCheckBox.class, "work");

					JButton before = Gooey.getComponent(frame, JButton.class, "before");
					JButton next = Gooey.getComponent(frame, JButton.class, "next");
					JButton add = Gooey.getComponent(frame, JButton.class, "add");
					JButton delete = Gooey.getComponent(frame, JButton.class, "delete");
					 
					name.setText("David");
					phone.setText("123456789");
					home.setSelected(true);
					work.setSelected(false);
					
					add.doClick();
					
					name.setText("Josh");
					phone.setText("987654321");
					home.setSelected(false);
					work.setSelected(true);
					
					add.doClick();
					
					name.setText("Sammy");
					phone.setText("10101010101");
					home.setSelected(true);
					work.setEnabled(true);
				 	
					add.doClick();				
					
					name.setText("Alex");
					phone.setText("999999999");
					home.setSelected(false);
					work.setSelected(false);
					
					add.doClick();
					
					name.setText("Vinny");
					phone.setText("1111111111");
					home.setSelected(true);
					work.setSelected(false);
					
					add.doClick();
					
					name.setText("Mom");
					phone.setText("4444444444");
					home.setSelected(false);
					work.setSelected(true);
					
					add.doClick();
					
					name.setText("Dad");
					phone.setText("5555555555");
					home.setSelected(true);
					work.setSelected(true);
					
					add.doClick();
					
					delete.doClick();
					
					assertEquals(name.getText(), "Mom");
					assertEquals(phone.getText(), "4444444444");
					assertEquals(home.isSelected(), false);
					assertEquals(work.isSelected(), true);
					
					//delete mom
					delete.doClick();
					assertEquals(name.getText(), "Vinny");
					assertEquals(phone.getText(), "1111111111");
					assertEquals(home.isSelected(), true);
					assertEquals(work.isSelected(), false);
					
					//delete vinny
					delete.doClick();
					assertEquals(name.getText(), "Alex");
					assertEquals(phone.getText(), "999999999");
					assertEquals(home.isSelected(), false);
					assertEquals(work.isSelected(), false);
					
					//delete alex
					delete.doClick();
					assertEquals(name.getText(), "Sammy");
					assertEquals(phone.getText(), "10101010101");
					assertEquals(home.isSelected(), true);
					assertEquals(work.isSelected(), true);
					
					//delete sammy
					delete.doClick();
					assertEquals(name.getText(), "Josh");
					assertEquals(phone.getText(), "987654321");
					assertEquals(home.isSelected(), false);
					assertEquals(work.isSelected(), true);
					
					//delete josh
					delete.doClick();
					assertEquals(name.getText(), "David");
					assertEquals(phone.getText(), "123456789");
					assertEquals(home.isSelected(), true);
					assertEquals(work.isSelected(), false);
					
					//delete david
					delete.doClick();
					assertEquals(name.getText(), "");
					assertEquals(phone.getText(), "");
					assertEquals(home.isSelected(), false);
					assertEquals(work.isSelected(), false);
					assertEquals(before.isEnabled(), false);
					assertEquals(next.isEnabled(), false);
					assertEquals(add.isEnabled(), true);
					assertEquals(delete.isEnabled(), false);				
					
				}
			});
		} 
	
	@Test
	public void menuExitDialogTest() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(null);
			}

			@Override
			public void test(JFrame frame) {
				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						WindowEvent w = new WindowEvent(frame,
								WindowEvent.WINDOW_CLOSING);
						Toolkit.getDefaultToolkit().getSystemEventQueue()
								.postEvent(w);
					}

					@Override
					public void test(JDialog dialog) {
						Gooey.getButton(dialog, "Yes").doClick();
						assertFalse(dialog.isShowing());
					}
				});

			}
		});
	}
	
}