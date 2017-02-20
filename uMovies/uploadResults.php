<?php
    session_start();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"
            "http://www.w3.org/TR/REC-html40/strict.dtd">
<html>
    <head>
        <title>uMovies :: Movies</title>
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
                Welcome to <em>uMovies</em>, your destination for information on
                    <a href="movies.php"    title="access movies information">      movies</a>,
                    <a href="actors.php"    title="access actors information">      actors</a> and
                    <a href="directors.php" title="access directors information">   directors</a>.
            </p>

            <h2>Administrator Menu</h2>

            <h3>    Uploading Data File     </h3>

            <?php
				$ActorSuccess        =   $_SESSION["uploadActorSuccesses"];
                $TotalNumberActed               =   $_SESSION["uploadActorTotal"];
                $uploadActorFailureCount        =   $TotalNumberActed   -   $ActorSuccess;
                $FinalActor                =   $_SESSION["uploadActorLast"];
                $FinalRoleActed                 =   $_SESSION["uploadRoleLast"];
				
				$MovieSuccessCount        		=   $_SESSION["uploadMovieSuccesses"];
                $TotalNumberMovies               =   $_SESSION["uploadMovieTotal"];
                $MovieFailure        =   $TotalNumberMovies   -   $MovieSuccessCount;
                $FinalMovie                =   $_SESSION["uploadMovieLast"];
				
				$Directed_BySuccess   =   $_SESSION["uploadDirectedBySuccesses"];
                $TotalDirected_By          =   $_SESSION["uploadDirectedByTotal"];
                $Directed_ByFailure   =   $TotalDirected_By   -   $Directed_BySuccess;
                
				$DirectorSuccess     =   $_SESSION["uploadDirectorSuccesses"];
                $TotalDirected            =   $_SESSION["uploadDirectorTotal"];
                $DirectorFailed     =   $TotalDirected   -   $DirectorSuccess;
                $FinalDirected             =   $_SESSION["uploadDirectorLast"];
                
				$Performed_InSuccess  =   $_SESSION["uploadPerformedInSuccesses"];
                $TotalPerformed_In         =   $_SESSION["uploadPerformedInTotal"];
                $Perfomed_InFailure  =   $TotalPerformed_In   -   $Performed_InSuccess;

               

               

                echo "Added <strong>", $MovieSuccessCount, "</strong> movies out of ", $TotalNumberMovies, " movie records (", $MovieFailure," failures) [Last added: <i>",$FinalMovie,"</i>]<br \>";

                echo "Added <strong>", $ActorSuccess, "</strong> actors out of ", $TotalNumberActed, " actor records (", $uploadActorFailureCount," failures) [Last added: <i>",$FinalActor,"]</i><br \>";

                echo "Added <strong>", $DirectorSuccess, "</strong> directors out of ", $TotalDirected, " director records (", $DirectorFailed," failures) [Last added: <i>",$FinalDirected,"]</i><br \>";

                echo "Added <strong>", $Directed_BySuccess, "</strong> directions out of ", $TotalDirected_By, " movie/director records (", $Directed_ByFailure," failures) [Last added: <i>",$FinalMovie,"/",$FinalDirected,"]</i><br \>";

                echo "Added <strong>", $Performed_InSuccess, "</strong> performances out of ", $TotalPerformed_In, " actor/movie/role records (", $Perfomed_InFailure," failures) [Last added: <i>",$FinalActor,"/",$FinalMovie,"/", $FinalRoleActed,"]</i><br \><br \>";

            ?>
            <form   action="admin.html">
                <input  type="submit"   value="Back to Administrator Menu"/>
            </form>

            <p><copyright>Roberto A. Flores. All Rights Reserved &copy; 2016</copyright></p>
        </div>

    </body>
</html>
