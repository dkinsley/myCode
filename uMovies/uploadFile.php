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

            <h2>Uploading Data File</h2>

            <p>
                <?php
					#array of variables passed to current script- superglobal 
                    $sourceFile = $_POST['sourceFile'];
					
					#also an array of variables available to current script, available over all webpages 
                    $password = $_SESSION["password"];
					error_reporting(E_ALL ^ E_WARNING);
					
					#opens connection to mySQL server
                    $moviesdb = new mysqli('localhost','uMoviesRoot',$password,'uMovies');
                    $file = fopen($sourceFile, 'r');
					
					#goes through file
                    while (!feof($file)) {
						#parses the file
                        $line = fgetcsv($file, 20639, "\t");
						#looks at first parse to determine type
                        $type = $line[0];
						#movie name = second parse in line
                        

						
                       
						#if its a movie, comes in here and adds inserts result into movie db 
                        if ($type == 'movie') {
                            $movieName = $line[1];
							$movieYear = $line[2];
                            $insertMovie = "INSERT INTO `movies` VALUES ('$movieName', '$movieYear')";
                            $result = $moviesdb->query( $insertMovie );
							
							$MovieSuccess = 0;
							$TotalMovies = 0;
							$FinalMovie = 0;
                            
							$FinalMovie = $movieName;
                            $TotalMovies += 1;
							$MovieSuccess += $result ?   1   :   0;

                        }
						#if director, enters here and inserts result into director database
                        elseif ($type == 'director') {
                            $directorName = $line[2];
                            $insertDirector = "INSERT INTO `directors` VALUES ('$directorName')";
                            $result = $moviesdb->query( $insertDirector );

							$DirectorSuccess = 0;
							$DirectedBySuccess = 0;
							$DirectedByTotal = 0;
							$TotalDirected = 0;
							$DirectorLast = 0;
                            
							$DirectorSuccess += $result ?   1   :   0;
                            $TotalDirected += 1;
                            $DirectorLast = $directorName;

                            $insertDirectedBy    =   "INSERT INTO `directed_by` VALUES ('$movieName', '$directorName')";
                            $result     =   $moviesdb->query( $insertDirectedBy );

                            $DirectedBySuccess += $result ?   1   :   0;
                            $DirectedByTotal += 1;
                        }
						#if actor or actress, comes here and inserts results into actor database
                        elseif ($type == 'actor' || $type == 'actress') {
                            $actorName =   $line[2];
                            $role =   $line[3];
                            $actorGender =   $type == 'actor' ? 'Male' : 'Female';
                            $insertActor =   "INSERT INTO `actors` VALUES ('$actorName', '$actorGender')";
                            $result =   $moviesdb->query( $insertActor );

							$ActorSuccess = 0;
							$ActorTotal = 0;
							$ActorLast = 0;
							$FinalRole = 0;
					
                            $ActorSuccess +=  $result ?   1   :   0;
                            $ActorTotal +=  1;
                            $ActorLast = $actorName;
                            $FinalRole = $role;

							$PerformedInSuccess = 0;
							$TotalPerformed = 0;
					
                            $insertPerformedIn = "INSERT INTO `performed_in` VALUES ('$actorName', '$movieName','$role')";
                            $result          =   $moviesdb->query( $insertPerformedIn );

                            $PerformedInSuccess  += $result ?   1   :   0;
                            $TotalPerformed         += 1;
                        }
                    }
                    $moviesdb->close();
                   
					$_SESSION["DirectorSuccesses"]    =   $DirectorSuccess;
                    $_SESSION["TotalDirected"]        =   $TotalDirected;
                    $_SESSION["DirectorLast"]         =   $DirectorLast;
                    $_SESSION["ActorSuccesses"]       =   $ActorSuccess;
                    $_SESSION["ActorTotal"]           =   $ActorTotal;
                    $_SESSION["ActorLast"]            =   $ActorLast;
                    $_SESSION["LastRole"]             =   $FinalRole;
					$_SESSION["MovieSuccesses"]       =   $MovieSuccess;
                    $_SESSION["TotalMovies"]          =   $TotalMovies;
                    $_SESSION["MovieLast"]            =   $FinalMovie;
					$_SESSION["DirectedBySuccesses"]  =   $DirectedBySuccess;
                    $_SESSION["DirectedByTotal"]      =   $DirectedByTotal;
                    $_SESSION["PerformedInSuccesses"] =   $PerformedInSuccess;
                    $_SESSION["TotalPerformed"]     =   $TotalPerformed;

                    header('Location: uploadResults.php');
                ?>
            </p>
            <h2>Deleting Information</h2>

            <p><copyright>Roberto A. Flores. All Rights Reserved &copy; 2016</copyright></p>
        </div>

    </body>
</html>
