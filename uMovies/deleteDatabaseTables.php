<?php
    session_start();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"
            "http://www.w3.org/TR/REC-html40/strict.dtd">
<html>
<head>
<title>uMovies :: Movie</title>
<style type="text/css">
@import url(uMovies.css);
</style>
</head>
<body>

<div id="links">
<a href="./">Home<span> Access the database of movies, actors and directors. Free to all!</span></a>
<a href="admin.html">Administrator<span> Administrator access. Password required.</span></a>
</div>


<div id="content">
<h1>uMovies&trade;</h1>
<p>
Welcome to <em>uMovies</em>, your destination for information on <a href="movies.php" title="access movies information">movies</a>, <a href="actors.php" title="access actors information">actors</a> and <a href="directors.php" title="access directors information">directors</a>.
</p>
            <h2>Administrator Menu</h2>

            <h3>    Deleting Information    </h3>
            <h3>    All Data Deleted!       </h3>
			
			
            <?php
			#deletes everything- sets up connection with database and deletes everything on the website
                $password = $_SESSION["password"];
                $moviesdb = new mysqli('localhost','uMoviesRoot',$password,'uMovies');

              
				$MovieDelete       =   "DELETE from `movies`";
                $moviesdb->query($MovieDelete);
				
				$ActorDelete       =   "DELETE from `actors`" ;
                $moviesdb->query($ActorDelete);
				
				$DirectorDelete    =   "DELETE from `directors`";
                $moviesdb->query($DirectorDelete);
				
				$Directed_ByDelete   =   "DELETE from `directed_by`";
                $moviesdb->query($Directed_ByDelete);
				
				$Performed_InDelete  =   "DELETE from `performed_in`";
                $moviesdb->query($Performed_InDelete);

                $moviesdb->close();
            ?>
            <form   action="admin.html">
                <input  type="submit"   value="Back to Administrator Menu"/>
            </form>

            <p><copyright>Roberto A. Flores. All Rights Reserved &copy; 2016</copyright></p>
        </div>

    </body>
</html>
