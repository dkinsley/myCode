
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testReflection() {
		Class<?>   cClass  = Robot.class;
		Field[]    cFields = cClass.getDeclaredFields();

		for (Field f : cFields) {
			if (!f.isSynthetic()) {
				if (!Modifier.isPrivate( f.getModifiers() )) { 
					fail( "Field \""+f.getName()+"\" should be private" );
				}
				if ( Modifier.isStatic ( f.getModifiers() ) && !Modifier.isFinal( f.getModifiers() )) {
					fail( "Field \""+f.getName()+"\" can't be static and not final" );
				}
			}
		}
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNullInputFile() throws IOException {
		File output = folder.newFile( "output.txt" );
		Robot.getMissionReport( null, output );
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNullOutputFile() throws IOException {
		File input  = folder.newFile( "input.txt" );
		Robot.getMissionReport( input, null );
	}
	@Test
	public void testDefaultConstructor() {
		new Robot();
	}
	@Test
	public void testEmptyFile() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner scan = new Scanner( output );
		assertFalse ( "Incorrect result", scan.hasNext() );
		scan.close();
	}
	@Test
	public void testOne() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		PrintWriter write  = new PrintWriter( input );
		write.println( "0 0" );
		write.close();

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner scan = new Scanner( output );
		assertFalse ( "Incorrect result", scan.hasNext() );
		scan.close();
	}

	@Test
	public void testTwo() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		PrintWriter write  = new PrintWriter( input );
		write.println( "3 3 1 1 1 -2 R 1 1 L 2" );
		write.println( "3 3 -1  0 L L 2 2" );
		write.println( "-1 1" ); // Program ends when one of the grid's rows|columns is < 1.
		write.close();

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner  scan   = new Scanner( output );
		String[] result = new String[] { 
				"destroyed @ time 5",
				"destroyed @ time 0",
		};
		for (String expected : result) {
			if (scan.hasNext()) {
				String actual = scan.nextLine();
				assertEquals( "Incorrect result", expected, actual );
			}
			else {
				fail( String.format( "Unexpected end of file: expected \"%s\"", expected ));
				break;
			}
		}
		assertFalse ( "File contains more data than expected", scan.hasNext() );
		scan.close();
	}
	
	@Test
	public void testThree() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		PrintWriter write  = new PrintWriter( input );
		write.println( "2 1 1 0 1 L L 2 R R 2" );
		write.println( "200 500 0 0 -100 -100 -100 -100 -100 -100" );
		write.println( "1 -1" ); // Program ends when one of the grid's rows|columns is < 1.
		write.close();

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner  scan   = new Scanner( output );
		String[] result = new String[] { 
				"destroyed @ time 4",
				"destroyed @ time 2"
		};
		for (String expected : result) {
			if (scan.hasNext()) {
				String actual = scan.nextLine();
				assertEquals( "Incorrect result", expected, actual );
			}
			else {
				fail( String.format( "Unexpected end of file: expected \"%s\"", expected ));
				break;
			}
		}
		assertFalse ( "File contains more data than expected", scan.hasNext() );
		scan.close();
	}
	
	@Test
	public void testFour() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		PrintWriter write  = new PrintWriter( input );
		write.println( "1 5  2  3 R R 2 L 1 L 1" );
		write.println( "4 5 1 1 -1 L -1 L -1 -2 R 2 L 3" );
		write.println( "9 2 5 1 R R R R L L L L" );
		write.println( "0 0" );
		write.close();

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner  scan   = new Scanner( output );
		String[] result = new String[] { 
				"destroyed @ time 0",
				"destroyed @ time 6",
				"5 1 @ time 8",
		};
		for (String expected : result) {
			if (scan.hasNext()) {
				String actual = scan.nextLine();
				assertEquals( "Incorrect result", expected, actual );
			}
			else {
				fail( String.format( "Unexpected end of file: expected \"%s\"", expected ));
				break;
			}
		}
		assertFalse ( "File contains more data than expected", scan.hasNext() );
		scan.close();
	}
	
	@Test
	public void testFive() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		PrintWriter write  = new PrintWriter( input );
		write.println( "1 1  1  2 -3 -2 -1 0" );
		write.println( "2 20 1 12 L R L R 1 R 6 1" );
		write.println( "8 8  0 0 0 -1 1 -1 -1 1 1 1" );
		write.println( "8 8  2 2 -1 -1 -1 L 1 2 3 4 5" );
		write.println( "0 0" );
		write.close();

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner  scan   = new Scanner( output );
		String[] result = new String[] { 
				"destroyed @ time 0",
				"0 19 @ time 8",
				"destroyed @ time 8",
				"destroyed @ time 6"
		};
		for (String expected : result) {
			if (scan.hasNext()) {
				String actual = scan.nextLine();
				assertEquals( "Incorrect result", expected, actual );
			}
			else {
				fail( String.format( "Unexpected end of file: expected \"%s\"", expected ));
				break;
			}
		}
		assertFalse ( "File contains more data than expected", scan.hasNext() );
		scan.close();
	}

	@Test
	public void testSix() throws IOException {
		// create file
		File input  = folder.newFile( "input.txt" );
		File output = folder.newFile( "output.txt" );

		PrintWriter write  = new PrintWriter( input );
		write.println( "2 4 0 3 L 2 L 1 R 1" );
		write.println( "1 5 2 3 R R 2 L 1 L 1" );
		write.println( "0 0 0 0 1 2" ); // end of deployments
		write.println( "3 3 1 1 R L 1" );
		write.close();

		// invoke program
		Robot.getMissionReport( input, output );

		// verify file results
		assertTrue  ( "Output file does not exist", output.exists() );
		Scanner  scan   = new Scanner( output );
		String[] result = new String[] { 
				"1 0 @ time 6",
				"destroyed @ time 0"
		};
		for (String expected : result) {
			if (scan.hasNext()) {
				String actual = scan.nextLine();
				assertEquals( "Incorrect result", expected, actual );
			}
			else {
				fail( String.format( "Unexpected end of file: expected \"%s\"", expected ));
				break;
			}
		}
		assertFalse ( "File contains more data than expected", scan.hasNext() );
		scan.close();
	}
}