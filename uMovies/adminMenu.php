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

<form name="form" action="correctPassword.php" method="post">
    Password    <input type="password"  name="passwordField"/>
        <input type="submit"    value="Login"   onclick="return isEmpty();" />
</form>

<p>
<?php
    $password = $_POST['passwordField'];
    error_reporting(E_ALL ^ E_WARNING);
    $moviesdb = new mysqli('localhost','uMoviesRoot',$password,'uMovies');

    $connected = $moviesdb->connect_errno;
					
    $moviesdb->close();

    if (!$connected) {
        $_SESSION["password"] = $password;
        header('Location: correctPassword.php');
    }
    else {
        header('Location: incorrectPassword.php');
    }
?>
</p>

<p><copyright>Roberto A. Flores. All Rights Reserved &copy; 2016</copyright></p>
</div>

</body>
</html>
