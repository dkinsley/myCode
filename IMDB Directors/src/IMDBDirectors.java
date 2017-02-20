import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IMDBDirectors {

	private List<String> list = new ArrayList<String>();
	private List<String> temp = new ArrayList<String>();
	
	private String streamFirst = null;
	private String streamLast = null;
	
	private int z = 1;
	private int most = 0;
	private int count = 1;
	
	
	public static long countMovies(Path file){
		Stream<String> s = null;
		try {
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s.count();
	}

	public static long countDirectors(Path file){
		
		IMDBDirectors direct = new IMDBDirectors();
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> direct.list.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		direct.list.stream().forEach(i -> direct.list.toString().split("\t"));
		
		List<String> str = direct.list
				.stream()
				.map(i -> i.substring(0,direct.list.toString().indexOf("\t")+5))
				.collect(Collectors.toList());
		
		
		List<String> temp = new ArrayList<String>();
		if(direct.streamLast == null){
			direct.streamLast = str.get(0).split("\t")[0];
			direct.streamFirst = str.get(0).split("\t")[1];
			temp.add(direct.streamLast);
		}
		direct.list.stream().forEach(i -> {
			String[] arr = i.split("\t");
			if(direct.z != 1){
				if(!direct.streamLast.equals(arr[0])
						&& !direct.streamFirst.equals(arr[1]))
					temp.add(i);
				else if(direct.streamLast.equals(arr[0])
						&& !direct.streamFirst.equals(arr[1]))
					temp.add(i);
				else if(!direct.streamLast.equals(arr[0])
						&& direct.streamFirst.equals(arr[1]))
					temp.add(i);
				direct.streamLast = arr[0];
				direct.streamFirst = arr[1];
			}
			direct.z++;
		});
		return temp.size();
	}

	public static List<String> getMoviesOfDirector(Path file, String last, String first, SortBy sort){
		List<String> onlyDirectorList = new ArrayList<String>();
		List<String> totalList = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalList.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		totalList.stream().forEach(i -> {
			String[] arr = i.split("\t");
			if(arr[0].equals(last) && arr[1].equals(first)){
				onlyDirectorList.add(i);
			}
			
		});
		onlyDirectorList.stream().forEach(i -> temp.add(i));
		
		switch(sort){
		case YEAR:
			temp.stream().forEach(i -> {				
				Collections.sort(onlyDirectorList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						return Integer.parseInt(arr1[3]) - Integer.parseInt(arr2[3]);
					}
				});
			});
			break;
		case MOVIE:
			temp.stream().forEach(i -> {				
				Collections.sort(onlyDirectorList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						String aa = arr1[2];
						String bb = arr2[2];
						return aa.compareTo(bb);
					}
				});
			});
			break;
		case FIRSTNAME:
			temp.stream().forEach(i -> {				
				Collections.sort(onlyDirectorList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						String aa = arr1[1];
						String bb = arr2[1];
						return aa.compareTo(bb);
					}
				});
			});
			break;
		case LASTNAME:
			temp.stream().forEach(i -> {				
				Collections.sort(onlyDirectorList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						String aa = arr1[0];
						String bb = arr2[0];
						return aa.compareTo(bb);
					}
				});
			});
			break;
		}
		return onlyDirectorList;
	}

	public static List<String> getMoviesInYear(Path file, int year, SortBy sort){
		List<String> totalList = new ArrayList<String>();
		List<String> onlyMoviesList = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalList.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		totalList.stream().forEach(i -> {
			String[] arr = i.split("\t");
			int a = Integer.parseInt(arr[3]);
			if(a == year){
				onlyMoviesList.add(i);
			}
		});
		
		onlyMoviesList.stream().forEach(i -> temp.add(i));
		
		switch(sort){
		case YEAR:
			temp.stream().forEach(i -> {
				Collections.sort(onlyMoviesList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						return Integer.parseInt(arr1[3]) - Integer.parseInt(arr2[3]);
					}
				});
			});
			break;
		case MOVIE:
			temp.stream().forEach(i -> {
				Collections.sort(onlyMoviesList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						String aa = arr1[2];
						String bb = arr2[2];
						return aa.compareTo(bb);
					}
				});
			});
			break;
		case FIRSTNAME:
			temp.stream().forEach(i -> {
				Collections.sort(onlyMoviesList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						String aa = arr1[1];
						String bb = arr2[1];
						return aa.compareTo(bb);
					}
				});
			});
			break;
		case LASTNAME:
			temp.stream().forEach(i -> {
				Collections.sort(onlyMoviesList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						String aa = arr1[0];
						String bb = arr2[0];
						return aa.compareTo(bb);
					}
				});
			});
			break;
		}
		return onlyMoviesList;
	}

	public static List<String> getDirectorWithMostMovies(Path file){
		IMDBDirectors IMDB = new IMDBDirectors();
		List<String> totalMovies = new ArrayList<String>();
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalMovies.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		IMDB.z = 0;
		
		totalMovies.stream().forEach(i -> {
			String[] arr = i.split("\t");
			IMDB.temp.add(arr[0] + "\t" + arr[1]);
		});
		Collections.sort(IMDB.temp);
		List<String> temp2 = new ArrayList<String>();
		IMDB.temp.stream().forEach(i -> {
			String[] arr = i.split("\t");
			if(IMDB.z != 0){
				if(IMDB.streamFirst.equals(arr[1]) && IMDB.streamLast.equals(arr[0])){
					IMDB.count++;
				}
				else{
					if(IMDB.most < IMDB.count){
						IMDB.most = IMDB.count;
						String a = IMDB.streamLast + "\t" + IMDB.streamFirst +"\t"+ IMDB.most;
						if(temp2.size() == 0)
							temp2.add(a);
						else
							temp2.set(0,a);
					}
					else if(IMDB.most > IMDB.count){
				}
					IMDB.streamFirst = arr[1];
					IMDB.streamLast = arr[0];
					IMDB.count = 1;
				}
			}
			else{
				IMDB.z++;
				IMDB.streamFirst = arr[1];
				IMDB.streamLast = arr[0];
			}
		});
		return temp2;
	}

	public static List<String> getDirectorsOfMovie(Path file, String movie, int year, boolean sortByLastname){
		IMDBDirectors IMDB = new IMDBDirectors();
		List<String> totalList = new ArrayList<String>();
		List<String> moviesList = new ArrayList<String>();
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalList.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		totalList.stream().forEach(i -> {
			String[] arr = i.split("\t");
			int a = Integer.parseInt(arr[3]);
			if(sortByLastname){
				if(arr[2].equals(movie) && a == year)
					moviesList.add(arr[0] + "\t" + arr[1]);
			}
			else{
				if(arr[2].equals(movie) && a == year){
					moviesList.add(arr[1] + "\t" + arr[0]);
					Collections.sort(moviesList);
				}	
			}
		});	
		if(sortByLastname){
			return moviesList;
		}
		else{
			moviesList.stream().forEach(i -> {
				String[] arr = i.split("\t");
				IMDB.temp.add(arr[1] + "\t" + arr[0]);
			});
			return IMDB.temp;
		}			
	}

	public static List<String> getNumberOfDirectorsPerMovie(Path file, boolean sortByMovie){
		IMDBDirectors IMDB = new IMDBDirectors();
		List<String> totalList = new ArrayList<String>();
		List<String> moviesList = new ArrayList<String>();
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalList.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		totalList.stream().forEach(i -> {
			String[] arr = i.split("\t");
			String movie = arr[2];
			String year = arr[3];
			moviesList.add(movie + "\t" + year);
		});
		
		Map<String,Long> map = moviesList.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		List<String> mapMoviesList = new ArrayList<String>();
		List<String> temp2 = new ArrayList<String>();
		
		map.entrySet().stream().forEach(i -> {
			String[] arr = i.toString().split("=");
			if(sortByMovie){
				mapMoviesList.add(arr[0] + "\t" + arr[1]);
			}
			else{
				mapMoviesList.add(arr[1] + "\t" + arr[0]);
				Collections.sort(mapMoviesList);
			}
		});
		IMDB.z = 0;
		Collections.sort(mapMoviesList);
		mapMoviesList.stream().forEach(i -> temp2.add(i));
		mapMoviesList.clear();
		
		temp2.stream().forEach(i -> {
			String [] arr = i.split("\t");
			if(sortByMovie)
				mapMoviesList.add(arr[0] + "\t" + arr[2]);
			else
				mapMoviesList.add(arr[0] + "\t" + arr[1]);
		});
		mapMoviesList.stream().forEach(System.out::println);
		if(sortByMovie){
			Collections.sort(mapMoviesList,String.CASE_INSENSITIVE_ORDER);
			return mapMoviesList;
		}
		else{
			mapMoviesList.stream().forEach(i -> temp2.add(i));
			temp2.stream().forEach(i -> {
				Collections.sort(mapMoviesList,String.CASE_INSENSITIVE_ORDER);
				Collections.sort(mapMoviesList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String [] arr2 = b.split("\t");
						return Integer.parseInt(arr2[0]) - Integer.parseInt(arr1[0]);
					}
				});
			});
			temp2.clear();
			mapMoviesList.stream().forEach(i -> {
				String[] arr = i.split("\t");
				temp2.add(arr[1] + "\t" + arr[0]);
			});
			return temp2;
		}
	}

	public static List<String> getNumberOfMoviesPerYear(Path file, boolean sortByYear){
		List<String> totalList = new ArrayList<String>();
		List<String> yearList = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalList.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		totalList.stream().forEach(i -> {
			String[] arr = i.split("\t");
			String year = arr[3];
			yearList.add(year);
		});
		Collections.sort(yearList);
		
		Map<String,Long> map = yearList.stream()
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		yearList.clear();
		map.entrySet().stream().forEach(i -> {
			String[] arr = i.toString().split("=");
			if(sortByYear)
				yearList.add(arr[0] + "\t" + arr[1]);
			else
				yearList.add(arr[1] + "\t" + arr[0]);
		});
		Collections.sort(yearList);
		if(sortByYear)
			return yearList;
		else{
			yearList.stream().forEach(i -> temp.add(i));
			temp.stream().forEach(i -> {
				Collections.sort(yearList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String[] arr2 = b.split("\t");
						return Integer.parseInt(arr2[0]) - Integer.parseInt(arr1[0]);
					}
				});
			});
			temp.clear();
			yearList.stream().forEach(i -> {
				String[] arr = i.split("\t");
				temp.add(arr[1] + "\t" + arr[0]);
			});
			return temp;
		}
	}

	public static List<String> getDirectorsByNumberOfMovies(Path file, boolean sortByName){
		List<String> totalList = new ArrayList<String>();
		List<String> moviesList = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		Stream<String> s = null;
		try {			
			s = Files.lines(file,Charset.forName("ISO-8859-1"));
			s.forEach(i -> totalList.add(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		totalList.stream().forEach(i -> {
			String[] arr = i.split("\t");
			moviesList.add(arr[0] + "\t" + arr[1]);
		});
		Map<String,Long> map = moviesList.stream()
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		moviesList.clear();
		map.entrySet().stream().forEach(i -> {
			String[] arr = i.toString().split("=");
			if(sortByName)
				moviesList.add(arr[0] + "\t" + arr[1]);
			else
				moviesList.add(arr[1] + "\t" + arr[0]);
		});
		Collections.sort(moviesList,String.CASE_INSENSITIVE_ORDER);
		
		if(sortByName){
			return moviesList;
		}
		else{
			moviesList.stream().forEach(i -> temp.add(i));
			temp.stream().forEach(i -> {
				Collections.sort(moviesList, new Comparator<String>() {
					public int compare(String a, String b){
						String[] arr1 = a.split("\t");
						String[] arr2 = b.split("\t");
						return Integer.parseInt(arr2[0]) - Integer.parseInt(arr1[0]);
					}
				});
			});
			temp.clear();
			moviesList.stream().forEach(i -> {
				String[] arr = i.split("\t");
				temp.add(arr[1] + "\t" + arr[2] + "\t" + arr[0]);
			});
			return temp;
		}
	}
}
