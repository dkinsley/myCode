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

            <h3>Uploading Data File</h3>
			<! button for choose file and upload >
            <form name="form"   action="uploadFile.php" method="post">
                Password    <input type="file"  name="sourceFile"/>
                            <input type="submit"    value="Upload"   onclick="return isEmpty();" />

                <script type="text/javascript">
                    function isEmpty()  {
                        var result;
                        var fileName;
						var confirmation;
						
						result = false;
						filename = document.forms['form'].sourceFile.value;
                        if(fileName === "") {
                            alert("The file name cannot be empty.");
                            result = false;
                        }
                        else {
                            
							confirmation = confirm('Uploading data file: ' + fileName);
                            result = confirmation;
                        }
                        return result;
                    }
                </script>
            </form>

            <h3>Deleting Information</h3>
            <form   action="deleteDatabaseTables.php">
                <input  type="submit"   value="Delete All"  onclick="return confirmDelete();"/>
                <script type="text/javascript">
                    function confirmDelete() {
                        var result = confirm('All data will be deleted. Proceed?');
                        return result;
                    }
                </script>
            </form>

            <p><copyright>Roberto A. Flores. All Rights Reserved &copy; 2016</copyright></p>
        </div>

    </body>
</html>
