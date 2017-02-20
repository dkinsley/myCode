import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class IMDBDirectorsTest {
	private static final String slash = System.getProperty("file.separator");
	private Path getFile() {
		Package pkg      = getClass().getPackage();
		String  path     = pkg == null ? "" : pkg.getName().replaceAll("\\.",slash);
		String  filename = String.format("bin%s%s%sdirectors.csv", slash, path, slash);
		File   file      = new File( filename ); 
		assertTrue( "File not found: "+ file.getAbsolutePath(), file.exists() );
		return Paths.get( filename );
	}

	@Test
	public void testHasPrivateAndNotStaticFields() {
		Class<?> type = IMDBDirectors.class;
		for (Field f : type.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				assertTrue ( "Field \""+f.getName()+"\" should be private", Modifier.isPrivate( f.getModifiers() ));
				assertFalse( "Field \""+f.getName()+"\" can't be static",   Modifier.isStatic ( f.getModifiers() ));
			}
		}
	}
	@Test
	public void testCountMovies() {
		long expected = 336;
		long actual   = IMDBDirectors.countMovies( getFile() );
		assertEquals( "Unexpected result", expected, actual );
	}
	// -------------------------------------------
	@Test
	public void testCountDirectors() {
		long expected = 190;
		long actual   = IMDBDirectors.countDirectors( getFile() );
		assertEquals( "Unexpected result", expected, actual );
	}
	// -------------------------------------------
	@Test
	public void testGetMoviesByDirectorSortedByMovie0() {
		String[]     expected = {
				"Lucas	George	Star Wars	1977",
				"Lucas	George	Star Wars: Episode I - The Phantom Menace	1999",
				"Lucas	George	Star Wars: Episode II - Attack of the Clones	2002",
				"Lucas	George	Star Wars: Episode III - Revenge of the Sith	2005"
		};
		List<String> actual   = IMDBDirectors.getMoviesOfDirector( getFile(), "Lucas", "George", SortBy.MOVIE );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesByDirectorSortedByMovie1() {
		String[]     expected = {
				"Howard	Ron	Angels & Demons	2009",
				"Howard	Ron	Apollo 13	1995",
				"Howard	Ron	Inferno	2016",
				"Howard	Ron	Splash	1984",
				"Howard	Ron	The Da Vinci Code	2006",
		};
		List<String> actual   = IMDBDirectors.getMoviesOfDirector( getFile(), "Howard", "Ron", SortBy.MOVIE );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesByDirectorSortedByYear0() {
		String[]     expected = {
				"Rodriguez	Robert	El mariachi	1992",
				"Rodriguez	Robert	Spy Kids	2001",
				"Rodriguez	Robert	Spy Kids 2: Island of Lost Dreams	2002",
				"Rodriguez	Robert	Once Upon a Time in Mexico	2003",
				"Rodriguez	Robert	Spy Kids 3-D: Game Over	2003",
				"Rodriguez	Robert	The Adventures of Sharkboy and Lavagirl 3-D	2005",
				"Rodriguez	Robert	Machete	2010",
				"Rodriguez	Robert	Spy Kids: All the Time in the World in 4D	2011",
				"Rodriguez	Robert	Machete Kills	2013",
		};
		List<String> actual   = IMDBDirectors.getMoviesOfDirector( getFile(), "Rodriguez", "Robert", SortBy.YEAR );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesByDirectorSortedByYear1() {
		String[]     expected = {
				"Spielberg	Steven	Jaws	1975",
				"Spielberg	Steven	Close Encounters of the Third Kind	1977",
				"Spielberg	Steven	1941	1979",
				"Spielberg	Steven	Raiders of the Lost Ark	1981",
				"Spielberg	Steven	E.T. the Extra-Terrestrial	1982",
				"Spielberg	Steven	Indiana Jones and the Temple of Doom	1984",
				"Spielberg	Steven	The Color Purple	1985",
				"Spielberg	Steven	Empire of the Sun	1987",
				"Spielberg	Steven	Always	1989",
				"Spielberg	Steven	Indiana Jones and the Last Crusade	1989",
				"Spielberg	Steven	Jurassic Park	1993",
				"Spielberg	Steven	Schindler's List	1993",
				"Spielberg	Steven	Amistad	1997",
				"Spielberg	Steven	The Lost World: Jurassic Park	1997",
				"Spielberg	Steven	Saving Private Ryan	1998",
				"Spielberg	Steven	Artificial Intelligence: AI	2001",
				"Spielberg	Steven	Catch Me If You Can	2002",
				"Spielberg	Steven	Minority Report	2002",
				"Spielberg	Steven	Munich	2005",
				"Spielberg	Steven	War of the Worlds	2005",
				"Spielberg	Steven	Indiana Jones and the Kingdom of the Crystal Skull	2008",
				"Spielberg	Steven	War Horse	2011",
				"Spielberg	Steven	Lincoln	2012",
				"Spielberg	Steven	Bridge of Spies	2015",				
		};
		List<String> actual   = IMDBDirectors.getMoviesOfDirector( getFile(), "Spielberg", "Steven", SortBy.YEAR );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	// -------------------------------------------
	@Test
	public void testGetMoviesInYearSortedByMovie0() {
		String[]     expected = {
				"Israel	Neal	Bachelor Party	1984",
				"Reitman	Ivan	Ghostbusters	1984",
				"Spielberg	Steven	Indiana Jones and the Temple of Doom	1984",	
				"Howard	Ron	Splash	1984",
		};
		List<String> actual   = IMDBDirectors.getMoviesInYear( getFile(), 1984, SortBy.MOVIE );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesInYearSortedByMovie1() {
		String[]     expected = {
				"Howard	Ron	Angels & Demons	2009",
				"Cameron	James	Avatar	2009",
				"Yates	David	Harry Potter and the Half-Blood Prince	2009",
				"Tarantino	Quentin	Inglourious Basterds	2009",
				"Eastwood	Clint	Invictus	2009",
				"Levy	Shawn	Night at the Museum: Battle of the Smithsonian	2009",
				"Abrams	J.J.	Star Trek	2009",
				"Heslov	Grant	The Men Who Stare at Goats	2009",
				"Fletcher	Anne	The Proposal	2009",
				"Reitman	Jason	Up in the Air	2009",
				"Fleischer	Ruben	Zombieland	2009",
		};
		List<String> actual   = IMDBDirectors.getMoviesInYear( getFile(), 2009, SortBy.MOVIE );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesInYearSortedByDirectorFirstname0() {
		String[]     expected = {
				"Fletcher	Anne	The Proposal	2009",
				"Eastwood	Clint	Invictus	2009",
				"Yates	David	Harry Potter and the Half-Blood Prince	2009",
				"Heslov	Grant	The Men Who Stare at Goats	2009",
				"Abrams	J.J.	Star Trek	2009",
				"Cameron	James	Avatar	2009",
				"Reitman	Jason	Up in the Air	2009",
				"Tarantino	Quentin	Inglourious Basterds	2009",
				"Howard	Ron	Angels & Demons	2009",
				"Fleischer	Ruben	Zombieland	2009",
				"Levy	Shawn	Night at the Museum: Battle of the Smithsonian	2009",
		};
		List<String> actual   = IMDBDirectors.getMoviesInYear( getFile(), 2009, SortBy.FIRSTNAME );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesInYearSortedByDirectorFirstname1() {
		String[]     expected = {
				"Harris	Blake	The Little Mermaid	2017",
				"Bouchard	Chris	The Little Mermaid	2017",	
				"Johnson	Rian	Star Wars: Episode VIII	2017",
		};
		List<String> actual   = IMDBDirectors.getMoviesInYear( getFile(), 2017, SortBy.FIRSTNAME );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesInYearSortedByDirectorLastname0() {
		String[]     expected = {
				"Bekmambetov	Timur	Ben-Hur	2016",
				"Blakeson	J	The 5th Wave	2016",
				"Bobin	James	Alice Through the Looking Glass	2016",
				"Chu	Jon M.	Now You See Me 2	2016",
				"Coen	Ethan	Hail, Caesar!	2016",
				"Coen	Joel	Hail, Caesar!	2016",
				"Eastwood	Clint	Sully	2016",
				"Feig	Paul	Ghostbusters	2016",
				"Foster	Jodie	Money Monster	2016",
				"Howard	Ron	Inferno	2016",
				"Mazer	Dan	Dirty Grandpa	2016",
				"Najafi	Babak	London Has Fallen	2016",
				"Russo	Anthony	Captain America: Civil War	2016",
				"Russo	Joe	Captain America: Civil War	2016",
				"Singer	Bryan	X-Men: Apocalypse	2016",
				"Snyder	Zack	Batman v Superman: Dawn of Justice	2016",
		};
		List<String> actual   = IMDBDirectors.getMoviesInYear( getFile(), 2016, SortBy.LASTNAME );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetMoviesInYearSortedByDirectorLastname1() {
		String[]     expected = {
				"Abrams	J.J.	Star Trek Into Darkness	2013",
				"Aja	Alexandre	Horns	2013",
				"Anderson	Brad	The Call	2013",
				"Blomkamp	Neill	Elysium	2013",
				"Cuarón	Alfonso	Gravity	2013",
				"Forster	Marc	World War Z	2013",
				"Fuqua	Antoine	Olympus Has Fallen	2013",
				"Greengrass	Paul	Captain Phillips	2013",
				"Hancock	John Lee	Saving Mr. Banks	2013",
				"Helgeland	Brian	42	2013",
				"Hood	Gavin	Ender's Game	2013",
				"Jackson	Peter	The Hobbit: The Desolation of Smaug	2013",
				"Lawrence	Francis	The Hunger Games: Catching Fire	2013",
				"Leterrier	Louis	Now You See Me	2013",
				"McQueen	Steve	12 Years a Slave	2013",
				"Peirce	Kimberly	Carrie	2013",
				"Rodriguez	Robert	Machete Kills	2013",
				"Russell	David O.	American Hustle	2013",
				"Stiller	Ben	The Secret Life of Walter Mitty	2013",
				"Turteltaub	Jon	Last Vegas	2013",
				"Wadlow	Jeff	Kick-Ass 2	2013",
		};
		List<String> actual   = IMDBDirectors.getMoviesInYear( getFile(), 2013, SortBy.LASTNAME );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	// -------------------------------------------
	@Test
	public void testGetDirectorWithMostMovies() {
		String[]     expected = { "Spielberg	Steven	24" };
		List<String> actual   = IMDBDirectors.getDirectorWithMostMovies( getFile() );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	// -------------------------------------------
	@Test
	public void testGetNumberOfDirectorsPerMovieSortedByMovie() {
		String[]     expected = {
				"12 Years a Slave	1",
				"15 Minutes	1",
				"1941	1",
				"28 Days	1",
				"42	1",
				"A Bronx Tale	1",
				"A League of Their Own	1",
				"A Million Ways to Die in the West	1",
				"A Time to Kill	1",
				"A Walk Among the Tombstones	1",
				"Air Force One	1",
				"Alice Through the Looking Glass	1",
				"Alien	1",
				"Alien: Resurrection	1",
				"Aliens	1",
				"Along Came a Spider	1",
				"Always	1",
				"American Beauty	1",
				"American Hustle	1",
				"American Sniper	1",
				"Amistad	1",
				"Analyze That	1",
				"Analyze This	1",
				"Angels & Demons	1",
				"Apocalypse Now	1",
				"Apollo 13	1",
				"Artificial Intelligence: AI	1",
				"Avatar	1",
				"Avengers: Age of Ultron	1",
				"Awakenings	1",
				"Babel	1",
				"Bachelor Party	1",
				"Batman & Robin	1",
				"Batman Begins	1",
				"Batman v Superman: Dawn of Justice	1",
				"Battleship	1",
				"Being John Malkovich	1",
				"Ben-Hur	1",
				"Beowulf	1",
				"Big	1",
				"Black Hawk Down	1",
				"Blade Runner	1",
				"Bridge of Spies	1",
				"Brokeback Mountain	1",
				"Bruce Almighty	1",
				"Cape Fear	1",
				"Captain America: Civil War	2",
				"Captain America: The Winter Soldier	2",
				"Captain Phillips	1",
				"Carrie	1",
				"Cast Away	1",
				"Catch Me If You Can	1",
				"Chappie	1",
				"Charlie Wilson's War	1",
				"Cinderella	1",
				"Clear and Present Danger	1",
				"Close Encounters of the Third Kind	1",
				"Cloud Atlas	3",
				"Contact	1",
				"Cowboys & Aliens	1",
				"Crash	1",
				"Crash	1",
				"Deep Impact	1",
				"Diary of a Wimpy Kid	1",
				"Die Another Day	1",
				"Dirty Grandpa	1",
				"Divine Secrets of the Ya-Ya Sisterhood	1",
				"Driving Miss Daisy	1",
				"Dumb and Dumber To	2",
				"E.T. the Extra-Terrestrial	1",
				"El mariachi	1",
				"Elysium	1",
				"Empire of the Sun	1",
				"Ender's Game	1",
				"Evan Almighty	1",
				"Everest	1",
				"Extremely Loud & Incredibly Close	1",
				"Fargo	1",
				"Flightplan	1",
				"Forrest Gump	1",
				"Freaky Friday	1",
				"Fury	1",
				"Get Smart	1",
				"Ghostbusters	1",
				"Ghostbusters	1",
				"Ghostbusters II	1",
				"Glory	1",
				"Good Night, and Good Luck.	1",
				"Goodfellas	1",
				"Gorillas in the Mist: The Story of Dian Fossey	1",
				"Gravity	1",
				"Groundhog Day	1",
				"Hail, Caesar!	2",
				"Hard Rain	1",
				"Harry Potter and the Chamber of Secrets	1",
				"Harry Potter and the Deathly Hallows: Part 1	1",
				"Harry Potter and the Deathly Hallows: Part 2	1",
				"Harry Potter and the Escape from Gringotts	1",
				"Harry Potter and the Forbidden Journey	1",
				"Harry Potter and the Goblet of Fire	1",
				"Harry Potter and the Half-Blood Prince	1",
				"Harry Potter and the Order of the Phoenix	1",
				"Harry Potter and the Prisoner of Azkaban	1",
				"Harry Potter and the Sorcerer's Stone	1",
				"Holes	1",
				"Home Alone 3	1",
				"Horns	1",
				"Indiana Jones and the Kingdom of the Crystal Skull	1",
				"Indiana Jones and the Last Crusade	1",
				"Indiana Jones and the Temple of Doom	1",
				"Inferno	1",
				"Inglourious Basterds	1",
				"Interstellar	1",
				"Invictus	1",
				"Iron Man 2	1",
				"Jack Ryan: Shadow Recruit	1",
				"Jaws	1",
				"Jurassic Park	1",
				"K-19: The Widowmaker	1",
				"Kick-Ass	1",
				"Kick-Ass 2	1",
				"Lara Croft Tomb Raider: The Cradle of Life	1",
				"Lara Croft: Tomb Raider	1",
				"Last Vegas	1",
				"Lincoln	1",
				"Little Fockers	1",
				"London Has Fallen	1",
				"Lost in Translation	1",
				"Love Actually	1",
				"Lucy	1",
				"Machete	2",
				"Machete Kills	1",
				"Maleficent	1",
				"Meet the Fockers	1",
				"Meet the Parents	1",
				"Men of Honor	1",
				"Midnight Run	1",
				"Million Dollar Baby	1",
				"Minority Report	1",
				"Miss Congeniality	1",
				"Miss Congeniality 2: Armed and Fabulous	1",
				"Mission: Impossible III	1",
				"Money Monster	1",
				"Moneyball	1",
				"Moulin Rouge!	1",
				"Mr. & Mrs. Smith	1",
				"Mrs. Doubtfire	1",
				"Munich	1",
				"Muppets Most Wanted	1",
				"My Cousin Vinny	1",
				"Night at the Museum	1",
				"Night at the Museum: Battle of the Smithsonian	1",
				"Night at the Museum: Secret of the Tomb	1",
				"No Country for Old Men	2",
				"Now You See Me	1",
				"Now You See Me 2	1",
				"O Brother, Where Art Thou?	1",
				"Ocean's Eleven	1",
				"Ocean's Thirteen	1",
				"Ocean's Twelve	1",
				"Olympus Has Fallen	1",
				"Once Upon a Time in Mexico	1",
				"Outbreak	1",
				"Paris, je t'aime	22",
				"Patriot Games	1",
				"Philadelphia	1",
				"Pirates of the Caribbean: At World's End	1",
				"Pirates of the Caribbean: Dead Man's Chest	1",
				"Pirates of the Caribbean: The Curse of the Black Pearl	1",
				"Presumed Innocent	1",
				"Raiders of the Lost Ark	1",
				"Raising Arizona	1",
				"RED	1",
				"Return of the Killer Tomatoes!	1",
				"Robin Hood: Prince of Thieves	1",
				"Salt	1",
				"Saving Mr. Banks	1",
				"Saving Private Ryan	1",
				"Schindler's List	1",
				"Se7en	1",
				"See No Evil, Hear No Evil	1",
				"Silver Linings Playbook	1",
				"Sleepless in Seattle	1",
				"Solaris	1",
				"Speed	1",
				"Speed 2: Cruise Control	1",
				"Splash	1",
				"Spy Kids	1",
				"Spy Kids 2: Island of Lost Dreams	1",
				"Spy Kids 3-D: Game Over	1",
				"Spy Kids: All the Time in the World in 4D	1",
				"Star Trek	1",
				"Star Trek Into Darkness	1",
				"Star Wars	1",
				"Star Wars: Episode I - The Phantom Menace	1",
				"Star Wars: Episode II - Attack of the Clones	1",
				"Star Wars: Episode III - Revenge of the Sith	1",
				"Star Wars: Episode V - The Empire Strikes Back	1",
				"Star Wars: Episode VI - Return of the Jedi	1",
				"Star Wars: Episode VII - The Force Awakens	1",
				"Star Wars: Episode VIII	1",
				"Stay	1",
				"Sully	1",
				"Super 8	1",
				"Syriana	1",
				"Taken	1",
				"Taken 2	1",
				"Taken 3	1",
				"Taxi Driver	1",
				"Ted 2	1",
				"That Was Then... This Is Now	1",
				"The 5th Wave	1",
				"The Adventures of Sharkboy and Lavagirl 3-D	1",
				"The Amazing Spider-Man	1",
				"The Amazing Spider-Man 2	1",
				"The American	1",
				"The Amityville Horror	1",
				"The Avengers	1",
				"The Big Short	1",
				"The Bone Collector	1",
				"The Bonfire of the Vanities	1",
				"The Bucket List	1",
				"The Call	1",
				"The Color Purple	1",
				"The Contract	1",
				"The Curious Case of Benjamin Button	1",
				"The Da Vinci Code	1",
				"The Dark Knight	1",
				"The Dark Knight Rises	1",
				"The Deer Hunter	1",
				"The Descendants	1",
				"The Expendables 3	1",
				"The Firm	1",
				"The Fugitive	1",
				"The Ghost Writer	1",
				"The Godfather	1",
				"The Godfather: Part II	1",
				"The Godfather: Part III	1",
				"The Good German	1",
				"The Green Mile	1",
				"The Harvest	1",
				"The Hobbit: An Unexpected Journey	1",
				"The Hobbit: The Battle of the Five Armies	1",
				"The Hobbit: The Desolation of Smaug	1",
				"The Hunger Games	1",
				"The Hunger Games: Catching Fire	1",
				"The Hunger Games: Mockingjay - Part 1	1",
				"The Hunger Games: Mockingjay - Part 2	1",
				"The Ides of March	1",
				"The Intern	1",
				"The Island	1",
				"The Ladykillers	2",
				"The Last Boy Scout	1",
				"The Little Mermaid	2",
				"The Lord of the Rings: The Fellowship of the Ring	1",
				"The Lord of the Rings: The Return of the King	1",
				"The Lord of the Rings: The Two Towers	1",
				"The Lost World: Jurassic Park	1",
				"The Men Who Stare at Goats	1",
				"The Mission	1",
				"The Monuments Men	1",
				"The Net	1",
				"The Peacemaker	1",
				"The Perfect Storm	1",
				"The Polar Express	1",
				"The Power of One	1",
				"The Princess Diaries	1",
				"The Princess Diaries 2: Royal Engagement	1",
				"The Proposal	1",
				"The Royal Tenenbaums	1",
				"The Secret Life of Walter Mitty	1",
				"The Shawshank Redemption	1",
				"The Silence of the Lambs	1",
				"The Sum of All Fears	1",
				"The Talented Mr. Ripley	1",
				"The Thin Red Line	1",
				"The Untouchables	1",
				"The Vanishing	1",
				"The Woman in Black	1",
				"Thelma & Louise	1",
				"There's Something About Mary	2",
				"Three Kings	1",
				"Tomorrowland	1",
				"Tootsie	1",
				"Tropic Thunder	1",
				"True Grit	2",
				"Twelve Monkeys	1",
				"Unforgiven	1",
				"Unknown	1",
				"Up in the Air	1",
				"Wag the Dog	1",
				"War Horse	1",
				"War of the Worlds	1",
				"What About Bob?	1",
				"What Lies Beneath	1",
				"Witness	1",
				"Working Girl	1",
				"World War Z	1",
				"X-Men	1",
				"X-Men: Apocalypse	1",
				"X-Men: Days of Future Past	1",
				"X-Men: The Last Stand	1",
				"Zombieland	1",
		};
		List<String> actual   = IMDBDirectors.getNumberOfDirectorsPerMovie( getFile(), true );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetNumberOfDirectorsPerMovieSortedByNumberOfDirectors() {
		String[]     expected = {
				"Paris, je t'aime	22",
				"Cloud Atlas	3",
				"Captain America: Civil War	2",
				"Captain America: The Winter Soldier	2",
				"Dumb and Dumber To	2",
				"Hail, Caesar!	2",
				"Machete	2",
				"No Country for Old Men	2",
				"The Ladykillers	2",
				"The Little Mermaid	2",
				"There's Something About Mary	2",
				"True Grit	2",
				"12 Years a Slave	1",
				"15 Minutes	1",
				"1941	1",
				"28 Days	1",
				"42	1",
				"A Bronx Tale	1",
				"A League of Their Own	1",
				"A Million Ways to Die in the West	1",
				"A Time to Kill	1",
				"A Walk Among the Tombstones	1",
				"Air Force One	1",
				"Alice Through the Looking Glass	1",
				"Alien	1",
				"Alien: Resurrection	1",
				"Aliens	1",
				"Along Came a Spider	1",
				"Always	1",
				"American Beauty	1",
				"American Hustle	1",
				"American Sniper	1",
				"Amistad	1",
				"Analyze That	1",
				"Analyze This	1",
				"Angels & Demons	1",
				"Apocalypse Now	1",
				"Apollo 13	1",
				"Artificial Intelligence: AI	1",
				"Avatar	1",
				"Avengers: Age of Ultron	1",
				"Awakenings	1",
				"Babel	1",
				"Bachelor Party	1",
				"Batman & Robin	1",
				"Batman Begins	1",
				"Batman v Superman: Dawn of Justice	1",
				"Battleship	1",
				"Being John Malkovich	1",
				"Ben-Hur	1",
				"Beowulf	1",
				"Big	1",
				"Black Hawk Down	1",
				"Blade Runner	1",
				"Bridge of Spies	1",
				"Brokeback Mountain	1",
				"Bruce Almighty	1",
				"Cape Fear	1",
				"Captain Phillips	1",
				"Carrie	1",
				"Cast Away	1",
				"Catch Me If You Can	1",
				"Chappie	1",
				"Charlie Wilson's War	1",
				"Cinderella	1",
				"Clear and Present Danger	1",
				"Close Encounters of the Third Kind	1",
				"Contact	1",
				"Cowboys & Aliens	1",
				"Crash	1",
				"Crash	1",
				"Deep Impact	1",
				"Diary of a Wimpy Kid	1",
				"Die Another Day	1",
				"Dirty Grandpa	1",
				"Divine Secrets of the Ya-Ya Sisterhood	1",
				"Driving Miss Daisy	1",
				"E.T. the Extra-Terrestrial	1",
				"El mariachi	1",
				"Elysium	1",
				"Empire of the Sun	1",
				"Ender's Game	1",
				"Evan Almighty	1",
				"Everest	1",
				"Extremely Loud & Incredibly Close	1",
				"Fargo	1",
				"Flightplan	1",
				"Forrest Gump	1",
				"Freaky Friday	1",
				"Fury	1",
				"Get Smart	1",
				"Ghostbusters	1",
				"Ghostbusters	1",
				"Ghostbusters II	1",
				"Glory	1",
				"Good Night, and Good Luck.	1",
				"Goodfellas	1",
				"Gorillas in the Mist: The Story of Dian Fossey	1",
				"Gravity	1",
				"Groundhog Day	1",
				"Hard Rain	1",
				"Harry Potter and the Chamber of Secrets	1",
				"Harry Potter and the Deathly Hallows: Part 1	1",
				"Harry Potter and the Deathly Hallows: Part 2	1",
				"Harry Potter and the Escape from Gringotts	1",
				"Harry Potter and the Forbidden Journey	1",
				"Harry Potter and the Goblet of Fire	1",
				"Harry Potter and the Half-Blood Prince	1",
				"Harry Potter and the Order of the Phoenix	1",
				"Harry Potter and the Prisoner of Azkaban	1",
				"Harry Potter and the Sorcerer's Stone	1",
				"Holes	1",
				"Home Alone 3	1",
				"Horns	1",
				"Indiana Jones and the Kingdom of the Crystal Skull	1",
				"Indiana Jones and the Last Crusade	1",
				"Indiana Jones and the Temple of Doom	1",
				"Inferno	1",
				"Inglourious Basterds	1",
				"Interstellar	1",
				"Invictus	1",
				"Iron Man 2	1",
				"Jack Ryan: Shadow Recruit	1",
				"Jaws	1",
				"Jurassic Park	1",
				"K-19: The Widowmaker	1",
				"Kick-Ass	1",
				"Kick-Ass 2	1",
				"Lara Croft Tomb Raider: The Cradle of Life	1",
				"Lara Croft: Tomb Raider	1",
				"Last Vegas	1",
				"Lincoln	1",
				"Little Fockers	1",
				"London Has Fallen	1",
				"Lost in Translation	1",
				"Love Actually	1",
				"Lucy	1",
				"Machete Kills	1",
				"Maleficent	1",
				"Meet the Fockers	1",
				"Meet the Parents	1",
				"Men of Honor	1",
				"Midnight Run	1",
				"Million Dollar Baby	1",
				"Minority Report	1",
				"Miss Congeniality	1",
				"Miss Congeniality 2: Armed and Fabulous	1",
				"Mission: Impossible III	1",
				"Money Monster	1",
				"Moneyball	1",
				"Moulin Rouge!	1",
				"Mr. & Mrs. Smith	1",
				"Mrs. Doubtfire	1",
				"Munich	1",
				"Muppets Most Wanted	1",
				"My Cousin Vinny	1",
				"Night at the Museum	1",
				"Night at the Museum: Battle of the Smithsonian	1",
				"Night at the Museum: Secret of the Tomb	1",
				"Now You See Me	1",
				"Now You See Me 2	1",
				"O Brother, Where Art Thou?	1",
				"Ocean's Eleven	1",
				"Ocean's Thirteen	1",
				"Ocean's Twelve	1",
				"Olympus Has Fallen	1",
				"Once Upon a Time in Mexico	1",
				"Outbreak	1",
				"Patriot Games	1",
				"Philadelphia	1",
				"Pirates of the Caribbean: At World's End	1",
				"Pirates of the Caribbean: Dead Man's Chest	1",
				"Pirates of the Caribbean: The Curse of the Black Pearl	1",
				"Presumed Innocent	1",
				"Raiders of the Lost Ark	1",
				"Raising Arizona	1",
				"RED	1",
				"Return of the Killer Tomatoes!	1",
				"Robin Hood: Prince of Thieves	1",
				"Salt	1",
				"Saving Mr. Banks	1",
				"Saving Private Ryan	1",
				"Schindler's List	1",
				"Se7en	1",
				"See No Evil, Hear No Evil	1",
				"Silver Linings Playbook	1",
				"Sleepless in Seattle	1",
				"Solaris	1",
				"Speed	1",
				"Speed 2: Cruise Control	1",
				"Splash	1",
				"Spy Kids	1",
				"Spy Kids 2: Island of Lost Dreams	1",
				"Spy Kids 3-D: Game Over	1",
				"Spy Kids: All the Time in the World in 4D	1",
				"Star Trek	1",
				"Star Trek Into Darkness	1",
				"Star Wars	1",
				"Star Wars: Episode I - The Phantom Menace	1",
				"Star Wars: Episode II - Attack of the Clones	1",
				"Star Wars: Episode III - Revenge of the Sith	1",
				"Star Wars: Episode V - The Empire Strikes Back	1",
				"Star Wars: Episode VI - Return of the Jedi	1",
				"Star Wars: Episode VII - The Force Awakens	1",
				"Star Wars: Episode VIII	1",
				"Stay	1",
				"Sully	1",
				"Super 8	1",
				"Syriana	1",
				"Taken	1",
				"Taken 2	1",
				"Taken 3	1",
				"Taxi Driver	1",
				"Ted 2	1",
				"That Was Then... This Is Now	1",
				"The 5th Wave	1",
				"The Adventures of Sharkboy and Lavagirl 3-D	1",
				"The Amazing Spider-Man	1",
				"The Amazing Spider-Man 2	1",
				"The American	1",
				"The Amityville Horror	1",
				"The Avengers	1",
				"The Big Short	1",
				"The Bone Collector	1",
				"The Bonfire of the Vanities	1",
				"The Bucket List	1",
				"The Call	1",
				"The Color Purple	1",
				"The Contract	1",
				"The Curious Case of Benjamin Button	1",
				"The Da Vinci Code	1",
				"The Dark Knight	1",
				"The Dark Knight Rises	1",
				"The Deer Hunter	1",
				"The Descendants	1",
				"The Expendables 3	1",
				"The Firm	1",
				"The Fugitive	1",
				"The Ghost Writer	1",
				"The Godfather	1",
				"The Godfather: Part II	1",
				"The Godfather: Part III	1",
				"The Good German	1",
				"The Green Mile	1",
				"The Harvest	1",
				"The Hobbit: An Unexpected Journey	1",
				"The Hobbit: The Battle of the Five Armies	1",
				"The Hobbit: The Desolation of Smaug	1",
				"The Hunger Games	1",
				"The Hunger Games: Catching Fire	1",
				"The Hunger Games: Mockingjay - Part 1	1",
				"The Hunger Games: Mockingjay - Part 2	1",
				"The Ides of March	1",
				"The Intern	1",
				"The Island	1",
				"The Last Boy Scout	1",
				"The Lord of the Rings: The Fellowship of the Ring	1",
				"The Lord of the Rings: The Return of the King	1",
				"The Lord of the Rings: The Two Towers	1",
				"The Lost World: Jurassic Park	1",
				"The Men Who Stare at Goats	1",
				"The Mission	1",
				"The Monuments Men	1",
				"The Net	1",
				"The Peacemaker	1",
				"The Perfect Storm	1",
				"The Polar Express	1",
				"The Power of One	1",
				"The Princess Diaries	1",
				"The Princess Diaries 2: Royal Engagement	1",
				"The Proposal	1",
				"The Royal Tenenbaums	1",
				"The Secret Life of Walter Mitty	1",
				"The Shawshank Redemption	1",
				"The Silence of the Lambs	1",
				"The Sum of All Fears	1",
				"The Talented Mr. Ripley	1",
				"The Thin Red Line	1",
				"The Untouchables	1",
				"The Vanishing	1",
				"The Woman in Black	1",
				"Thelma & Louise	1",
				"Three Kings	1",
				"Tomorrowland	1",
				"Tootsie	1",
				"Tropic Thunder	1",
				"Twelve Monkeys	1",
				"Unforgiven	1",
				"Unknown	1",
				"Up in the Air	1",
				"Wag the Dog	1",
				"War Horse	1",
				"War of the Worlds	1",
				"What About Bob?	1",
				"What Lies Beneath	1",
				"Witness	1",
				"Working Girl	1",
				"World War Z	1",
				"X-Men	1",
				"X-Men: Apocalypse	1",
				"X-Men: Days of Future Past	1",
				"X-Men: The Last Stand	1",
				"Zombieland	1",
		};
		List<String> actual   = IMDBDirectors.getNumberOfDirectorsPerMovie( getFile(), false );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	// -------------------------------------------
	@Test
	public void testGetNumberOfMoviesPerYearSortedByYear() {
		String[] expected = {
				"1972	1",
				"1974	1",
				"1975	1",
				"1976	2",
				"1977	2",
				"1978	1",
				"1979	3",
				"1980	1",
				"1981	1",
				"1982	3",
				"1983	1",
				"1984	4",
				"1985	3",
				"1986	2",
				"1987	3",
				"1988	5",
				"1989	6",
				"1990	5",
				"1991	6",
				"1992	7",
				"1993	10",
				"1994	4",
				"1995	5",
				"1996	3",
				"1997	10",
				"1998	6",
				"1999	8",
				"2000	9",
				"2001	12",
				"2002	12",
				"2003	9",
				"2004	9",
				"2005	15",
				"2006	30",
				"2007	9",
				"2008	6",
				"2009	11",
				"2010	14",
				"2011	10",
				"2012	13",
				"2013	21",
				"2014	22",
				"2015	11",
				"2016	16",
				"2017	3",
		};
		List<String> actual   = IMDBDirectors.getNumberOfMoviesPerYear(getFile(), true );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetNumberOfMoviesPerYearSortedByNumberOfMovies() {
		String[] expected = {
				"2006	30",
				"2014	22",
				"2013	21",
				"2016	16",
				"2005	15",
				"2010	14",
				"2012	13",
				"2001	12",
				"2002	12",
				"2009	11",
				"2015	11",
				"1993	10",
				"1997	10",
				"2011	10",
				"2000	9",
				"2003	9",
				"2004	9",
				"2007	9",
				"1999	8",
				"1992	7",
				"1989	6",
				"1991	6",
				"1998	6",
				"2008	6",
				"1988	5",
				"1990	5",
				"1995	5",
				"1984	4",
				"1994	4",
				"1979	3",
				"1982	3",
				"1985	3",
				"1987	3",
				"1996	3",
				"2017	3",
				"1976	2",
				"1977	2",
				"1986	2",
				"1972	1",
				"1974	1",
				"1975	1",
				"1978	1",
				"1980	1",
				"1981	1",
				"1983	1",
		};
		List<String> actual   = IMDBDirectors.getNumberOfMoviesPerYear( getFile(), false );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	// -------------------------------------------
	@Test
	public void testGetDirectorsOfMovieSortedByFirstName() {
		String[] expected = {
				"Payne	Alexander",
				"Cuarón	Alfonso",
				"Podalydès	Bruno",
				"Doyle	Christopher",
				"Thomas	Daniela",
				"Benbihy	Emmanuel",
				"Coen	Ethan",
				"Auburtin	Frédéric",
				"Chadha	Gurinder",
				"Van Sant	Gus",
				"Depardieu	Gérard",
				"Coixet	Isabel",
				"Coen	Joel",
				"Suwa	Nobuhiro",
				"Schmitz	Oliver",
				"Assayas	Olivier",
				"LaGravenese	Richard",
				"Chomet	Sylvain",
				"Tykwer	Tom",
				"Natali	Vincenzo",
				"Salles	Walter",
				"Craven	Wes",				
		};
		List<String> actual   = IMDBDirectors.getDirectorsOfMovie( getFile(), "Paris, je t'aime", 2006, false );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetDirectorsOfMovieSortedByLastName() {
		String[] expected = {
				"Russo	Anthony",
				"Russo	Joe",
		};
		List<String> actual   = IMDBDirectors.getDirectorsOfMovie( getFile(), "Captain America: Civil War", 2016, true );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetDirectorsOfMovieWhenMovieDoesNotExist() {
		List<String> actual   = IMDBDirectors.getDirectorsOfMovie( getFile(), "Crash", 2000, true );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", 0, actual.size() );
	}
	@Test
	public void testGetDirectorsOfMovieWhenMovieNameExistsTwice() {
		String[] expected = {
				"Haggis	Paul",
		};
		List<String> actual   = IMDBDirectors.getDirectorsOfMovie( getFile(), "Crash", 2004, true );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	// -------------------------------------------
	@Test
	public void testGetDirectorsByNumberOfMoviesSortedByName() {
		String[] expected = {
				"Abrams	J.J.	5",
				"Aja	Alexandre	1",
				"Anderson	Brad	1",
				"Anderson	Wes	1",
				"Apted	Michael	1",
				"Assayas	Olivier	1",
				"Auburtin	Frédéric	1",
				"Avildsen	John G.	1",
				"Ayer	David	1",
				"Bay	Michael	1",
				"Bekmambetov	Timur	1",
				"Benbihy	Emmanuel	1",
				"Beresford	Bruce	2",
				"Berg	Peter	1",
				"Besson	Luc	1",
				"Bigelow	Kathryn	1",
				"Bird	Brad	1",
				"Blakeson	J	1",
				"Blomkamp	Neill	2",
				"Bobin	James	2",
				"Bouchard	Chris	1",
				"Branagh	Kenneth	2",
				"Brest	Martin	1",
				"Cain	Christopher	1",
				"Cameron	James	2",
				"Chadha	Gurinder	1",
				"Chomet	Sylvain	1",
				"Chu	Jon M.	1",
				"Cimino	Michael	1",
				"Clooney	George	3",
				"Coen	Ethan	5",
				"Coen	Joel	8",
				"Coixet	Isabel	1",
				"Collet-Serra	Jaume	1",
				"Columbus	Chris	3",
				"Coppola	Francis Ford	4",
				"Coppola	Sofia	1",
				"Corbijn	Anton	1",
				"Coup	Thierry	2",
				"Craven	Wes	1",
				"Cronenberg	David	1",
				"Cuarón	Alfonso	3",
				"Curtis	Richard	1",
				"Daldry	Stephen	1",
				"Darabont	Frank	2",
				"Davis	Andrew	2",
				"De Bello	John	1",
				"de Bont	Jan	3",
				"De Niro	Robert	1",
				"De Palma	Brian	2",
				"Demme	Jonathan	2",
				"Depardieu	Gérard	1",
				"Douglas	Andrew	1",
				"Doyle	Christopher	1",
				"Eastwood	Clint	5",
				"Ephron	Nora	1",
				"Farrelly	Bobby	2",
				"Farrelly	Peter	2",
				"Favreau	Jon	2",
				"Feig	Paul	1",
				"Fincher	David	2",
				"Fleischer	Ruben	1",
				"Fletcher	Anne	1",
				"Forster	Marc	2",
				"Foster	Jodie	1",
				"Frank	Scott	1",
				"Freudenthal	Thor	1",
				"Fuqua	Antoine	1",
				"Gaghan	Stephen	1",
				"Gilliam	Terry	1",
				"Gosnell	Raja	1",
				"Greengrass	Paul	1",
				"Haggis	Paul	1",
				"Hancock	John Lee	1",
				"Harris	Blake	1",
				"Helgeland	Brian	1",
				"Herzfeld	John	1",
				"Heslov	Grant	1",
				"Hiller	Arthur	1",
				"Hood	Gavin	1",
				"Howard	Ron	5",
				"Hughes	Patrick	1",
				"Israel	Neal	1",
				"Iñárritu	Alejandro G.	1",
				"Jackson	Peter	6",
				"Jeunet	Jean-Pierre	1",
				"Joffé	Roland	1",
				"Johnson	Rian	1",
				"Jonze	Spike	1",
				"Kershner	Irvin	1",
				"Khouri	Callie	1",
				"Kormákur	Baltasar	1",
				"LaGravenese	Richard	1",
				"Lawrence	Francis	3",
				"Leder	Mimi	2",
				"Lee	Ang	1",
				"Leterrier	Louis	1",
				"Levinson	Barry	1",
				"Levy	Shawn	3",
				"Liman	Doug	1",
				"Lucas	George	4",
				"Luhrmann	Baz	1",
				"Lynn	Jonathan	1",
				"MacFarlane	Seth	2",
				"Malick	Terrence	1",
				"Maniquis	Ethan	1",
				"Marconi	David	1",
				"Marquand	Richard	1",
				"Marshall	Garry	2",
				"Marshall	Penny	3",
				"Mazer	Dan	1",
				"McKay	Adam	1",
				"McQueen	Steve	1",
				"Megaton	Olivier	2",
				"Mendes	Sam	1",
				"Meyers	Nancy	1",
				"Miller	Bennett	1",
				"Minghella	Anthony	1",
				"Morel	Pierre	1",
				"Najafi	Babak	1",
				"Natali	Vincenzo	1",
				"Nelson	Gary	1",
				"Newell	Mike	1",
				"Nichols	Mike	2",
				"Nolan	Christopher	4",
				"Noyce	Phillip	4",
				"Oz	Frank	1",
				"Pakula	Alan J.	1",
				"Pasquin	John	1",
				"Payne	Alexander	2",
				"Peirce	Kimberly	1",
				"Petersen	Wolfgang	3",
				"Petrie	Donald	1",
				"Podalydès	Bruno	1",
				"Polanski	Roman	1",
				"Pollack	Sydney	2",
				"Ramis	Harold	3",
				"Ratner	Brett	1",
				"Reiner	Rob	1",
				"Reitman	Ivan	2",
				"Reitman	Jason	1",
				"Reynolds	Kevin	1",
				"Roach	Jay	2",
				"Robinson	Phil Alden	1",
				"Rodriguez	Robert	9",
				"Ross	Gary	1",
				"Russell	David O.	3",
				"Russo	Anthony	2",
				"Russo	Joe	2",
				"Salles	Walter	1",
				"Salomon	Mikael	1",
				"Schmitz	Oliver	1",
				"Schumacher	Joel	2",
				"Schwentke	Robert	2",
				"Scorsese	Martin	3",
				"Scott	Ridley	4",
				"Scott	Tony	1",
				"Segal	Peter	1",
				"Shadyac	Tom	2",
				"Singer	Bryan	3",
				"Sluizer	George	1",
				"Snyder	Zack	1",
				"Soderbergh	Steven	5",
				"Spielberg	Steven	24",
				"Stiller	Ben	2",
				"Stromberg	Robert	1",
				"Suwa	Nobuhiro	1",
				"Tamahori	Lee	2",
				"Tarantino	Quentin	1",
				"Thomas	Betty	1",
				"Thomas	Daniela	1",
				"Tillman Jr.	George	1",
				"Turteltaub	Jon	1",
				"Tykwer	Tom	2",
				"Van Sant	Gus	1",
				"Vaughn	Matthew	1",
				"Verbinski	Gore	3",
				"Wachowski	Lana	1",
				"Wachowski	Lilly	1",
				"Wadlow	Jeff	1",
				"Watkins	James	1",
				"Webb	Marc	2",
				"Weir	Peter	1",
				"Weitz	Paul	1",
				"West	Simon	1",
				"Whedon	Joss	2",
				"Winkler	Irwin	1",
				"Yates	David	4",
				"Zemeckis	Robert	6",
				"Zwick	Edward	1"
		};
		List<String> actual   = IMDBDirectors.getDirectorsByNumberOfMovies( getFile(), true );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
	@Test
	public void testGetDirectorsByNumberOfMoviesSortedByNumberOfMovies() {
		String[] expected = {
				"Spielberg	Steven	24",
				"Rodriguez	Robert	9",
				"Coen	Joel	8",
				"Jackson	Peter	6",
				"Zemeckis	Robert	6",
				"Abrams	J.J.	5",
				"Coen	Ethan	5",
				"Eastwood	Clint	5",
				"Howard	Ron	5",
				"Soderbergh	Steven	5",
				"Coppola	Francis Ford	4",
				"Lucas	George	4",
				"Nolan	Christopher	4",
				"Noyce	Phillip	4",
				"Scott	Ridley	4",
				"Yates	David	4",
				"Clooney	George	3",
				"Columbus	Chris	3",
				"Cuarón	Alfonso	3",
				"de Bont	Jan	3",
				"Lawrence	Francis	3",
				"Levy	Shawn	3",
				"Marshall	Penny	3",
				"Petersen	Wolfgang	3",
				"Ramis	Harold	3",
				"Russell	David O.	3",
				"Scorsese	Martin	3",
				"Singer	Bryan	3",
				"Verbinski	Gore	3",
				"Beresford	Bruce	2",
				"Blomkamp	Neill	2",
				"Bobin	James	2",
				"Branagh	Kenneth	2",
				"Cameron	James	2",
				"Coup	Thierry	2",
				"Darabont	Frank	2",
				"Davis	Andrew	2",
				"De Palma	Brian	2",
				"Demme	Jonathan	2",
				"Farrelly	Bobby	2",
				"Farrelly	Peter	2",
				"Favreau	Jon	2",
				"Fincher	David	2",
				"Forster	Marc	2",
				"Leder	Mimi	2",
				"MacFarlane	Seth	2",
				"Marshall	Garry	2",
				"Megaton	Olivier	2",
				"Nichols	Mike	2",
				"Payne	Alexander	2",
				"Pollack	Sydney	2",
				"Reitman	Ivan	2",
				"Roach	Jay	2",
				"Russo	Anthony	2",
				"Russo	Joe	2",
				"Schumacher	Joel	2",
				"Schwentke	Robert	2",
				"Shadyac	Tom	2",
				"Stiller	Ben	2",
				"Tamahori	Lee	2",
				"Tykwer	Tom	2",
				"Webb	Marc	2",
				"Whedon	Joss	2",
				"Aja	Alexandre	1",
				"Anderson	Brad	1",
				"Anderson	Wes	1",
				"Apted	Michael	1",
				"Assayas	Olivier	1",
				"Auburtin	Frédéric	1",
				"Avildsen	John G.	1",
				"Ayer	David	1",
				"Bay	Michael	1",
				"Bekmambetov	Timur	1",
				"Benbihy	Emmanuel	1",
				"Berg	Peter	1",
				"Besson	Luc	1",
				"Bigelow	Kathryn	1",
				"Bird	Brad	1",
				"Blakeson	J	1",
				"Bouchard	Chris	1",
				"Brest	Martin	1",
				"Cain	Christopher	1",
				"Chadha	Gurinder	1",
				"Chomet	Sylvain	1",
				"Chu	Jon M.	1",
				"Cimino	Michael	1",
				"Coixet	Isabel	1",
				"Collet-Serra	Jaume	1",
				"Coppola	Sofia	1",
				"Corbijn	Anton	1",
				"Craven	Wes	1",
				"Cronenberg	David	1",
				"Curtis	Richard	1",
				"Daldry	Stephen	1",
				"De Bello	John	1",
				"De Niro	Robert	1",
				"Depardieu	Gérard	1",
				"Douglas	Andrew	1",
				"Doyle	Christopher	1",
				"Ephron	Nora	1",
				"Feig	Paul	1",
				"Fleischer	Ruben	1",
				"Fletcher	Anne	1",
				"Foster	Jodie	1",
				"Frank	Scott	1",
				"Freudenthal	Thor	1",
				"Fuqua	Antoine	1",
				"Gaghan	Stephen	1",
				"Gilliam	Terry	1",
				"Gosnell	Raja	1",
				"Greengrass	Paul	1",
				"Haggis	Paul	1",
				"Hancock	John Lee	1",
				"Harris	Blake	1",
				"Helgeland	Brian	1",
				"Herzfeld	John	1",
				"Heslov	Grant	1",
				"Hiller	Arthur	1",
				"Hood	Gavin	1",
				"Hughes	Patrick	1",
				"Israel	Neal	1",
				"Iñárritu	Alejandro G.	1",
				"Jeunet	Jean-Pierre	1",
				"Joffé	Roland	1",
				"Johnson	Rian	1",
				"Jonze	Spike	1",
				"Kershner	Irvin	1",
				"Khouri	Callie	1",
				"Kormákur	Baltasar	1",
				"LaGravenese	Richard	1",
				"Lee	Ang	1",
				"Leterrier	Louis	1",
				"Levinson	Barry	1",
				"Liman	Doug	1",
				"Luhrmann	Baz	1",
				"Lynn	Jonathan	1",
				"Malick	Terrence	1",
				"Maniquis	Ethan	1",
				"Marconi	David	1",
				"Marquand	Richard	1",
				"Mazer	Dan	1",
				"McKay	Adam	1",
				"McQueen	Steve	1",
				"Mendes	Sam	1",
				"Meyers	Nancy	1",
				"Miller	Bennett	1",
				"Minghella	Anthony	1",
				"Morel	Pierre	1",
				"Najafi	Babak	1",
				"Natali	Vincenzo	1",
				"Nelson	Gary	1",
				"Newell	Mike	1",
				"Oz	Frank	1",
				"Pakula	Alan J.	1",
				"Pasquin	John	1",
				"Peirce	Kimberly	1",
				"Petrie	Donald	1",
				"Podalydès	Bruno	1",
				"Polanski	Roman	1",
				"Ratner	Brett	1",
				"Reiner	Rob	1",
				"Reitman	Jason	1",
				"Reynolds	Kevin	1",
				"Robinson	Phil Alden	1",
				"Ross	Gary	1",
				"Salles	Walter	1",
				"Salomon	Mikael	1",
				"Schmitz	Oliver	1",
				"Scott	Tony	1",
				"Segal	Peter	1",
				"Sluizer	George	1",
				"Snyder	Zack	1",
				"Stromberg	Robert	1",
				"Suwa	Nobuhiro	1",
				"Tarantino	Quentin	1",
				"Thomas	Betty	1",
				"Thomas	Daniela	1",
				"Tillman Jr.	George	1",
				"Turteltaub	Jon	1",
				"Van Sant	Gus	1",
				"Vaughn	Matthew	1",
				"Wachowski	Lana	1",
				"Wachowski	Lilly	1",
				"Wadlow	Jeff	1",
				"Watkins	James	1",
				"Weir	Peter	1",
				"Weitz	Paul	1",
				"West	Simon	1",
				"Winkler	Irwin	1",
				"Zwick	Edward	1"
		};
		List<String> actual   = IMDBDirectors.getDirectorsByNumberOfMovies( getFile(), false );
		assertNotNull( "Result was null", actual );
		assertEquals ( "Unexpected number of results", expected.length, actual.size() );
		for (int i = 0; i < expected.length; i++) { 
			String e = expected[i];
			String a = actual.get(i);
			assertEquals( "Unexpected result @ index "+i, e, a );
		}
	}
}