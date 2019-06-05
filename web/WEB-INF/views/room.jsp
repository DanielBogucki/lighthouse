<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="<c:url value="/resources/simple1/style.css" />" rel="stylesheet">
    <title>Main Page</title>
    <meta name="description" content="website description"/>
    <meta name="keywords" content="website keywords, website keywords"/>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252"/>
    <link rel="stylesheet" type="text/css" href="style/style.css"/>
</head>
<body>

<div id="main">
    <div id="header">
        <div id="logo">
            <div id="logo_text">
                <!-- class="logo_colour", allows you to change the colour of the text -->
                <h1><a href="index.html">light<span class="logo_colour">house</span></a></h1>
                <h2>Automated lightning system in your house.</h2>
            </div>
        </div>
        <div id="menubar">
            <ul id="menu">
                <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
                <li><a href="index">Home</a></li>
                <li><a href="bulbs/search">Search Bulbs</a></li>
                <li class="selected"><a href="/rooms">Rooms</a></li>
                <li><a href="about">About</a></li>
            </ul>
        </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <div class="sidebar">
            <!-- insert your sidebar items here -->

        </div>
        <div id="content">
            <!-- insert the page content here -->
            <h1>${room.name}</h1>
            <br/>
            <h2>Bulbs <button type="button">New</button></h2>
            <p>Yeelight Color Bulb</p>
            <br/>
            <h2>Sensors <button type="button">Set new</button></h2>
            <p>BH1750</p>
            <br/>
            <h2>Schedules  <button type="button">Add New</button></h2>
            <button type="button">x</button>  <a href="schedule/">Noc</a><br/>
            <button type="button">x</button>  <a href="schedule/">Poranek</a><br/>
            <button type="button">x</button>  <a href="schedule/">Praca</a><br/>
            <button type="button">x</button>  <a href="schedule/">Odpoczynek</a><br/>

        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        Copyright &copy; simplestyle_1 | <a href="http://validator.w3.org/check?uri=referer">HTML5</a> | <a
            href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> | <a
            href="http://www.html5webtemplates.co.uk">Website templates</a>
    </div>
</div>

</body>
</html>