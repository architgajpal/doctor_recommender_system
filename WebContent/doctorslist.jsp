<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% if(session.getAttribute("name")==null)
{ out.print("<script>alert('Login then continue!')</script>");
   response.sendRedirect("homepage.jsp");
}
   %>
    <%@ page import="java.sql.Connection, java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Doctor Recommender System | Home</title>
		<meta name="description" content="Ideas for revealing content in a schematic box look." />
		<meta name="keywords" content="javascript, plugin, reveal, effect, demo, web development, web design, template" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="favicon.ico">
		<link href="https://fonts.googleapis.com/css?family=Inconsolata:400,700|Poppins:700" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/revealer.css" />
		<script>document.documentElement.className = 'js';</script>
		<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
		
	</head>
	<body class="demo-menu">
		<svg class="hidden">
			
			
			<symbol id="icon-menu" viewBox="0 0 17.6 9.9">
				<title>menu</title>
				<path d="M17.6,1H0V0h17.6V1z M17.6,4.3h-12v1h12V4.3z M17.6,8.9h-6.9v1h6.9V8.9z"/>
			</symbol>
			<symbol id="icon-cross" viewBox="0 0 10.2 10.2">
				<title>cross</title>
				<path d="M5.8,5.1l4.4,4.4l-0.7,0.7L5.1,5.8l-4.4,4.4L0,9.5l4.4-4.4L0,0.7L0.7,0l4.4,4.4L9.5,0l0.7,0.7L5.8,5.1z"/>
			</symbol>
		</svg>
		<script>
								function showruler(x,y)
								{
								
									alert('Hello');
										
										if(x<3)
											document.getElementById(y).style.color = "red";
										else if(x>2 && x<4)
											document.getElementById(y).style.color = "orange";
										else
											document.getElementById(y).style.color = "green";
										document.getElementById(y).innerHTML =x+"/5";
								
								
								
									
								}
								</script>
		<main>
			<header  class="codrops-header">
				
				<h1 class="codrops-header__title" id="greeting" style="color:#fff">Welcome <% out.print(session.getAttribute("name")); %></h1>
				
				<button class="btn btn--menu"><svg class="icon icon--menu"><use xlink:href="#icon-menu"></use></svg></button>
			</header>
			<section class="content flexy flexy--center">
				<p class="content__heading content__heading--center" style="color:#fff"><strong>Doctor Ratings</strong></p>
			<div class="panel	panel-primary"  style="border-color:#50A2A7;background-color:#F7F3E3; color:#2B2118">
	 <div class="panel-heading" style="border-color:#50A2A7;background-color:#50A2A7; color:#fff"><h4 style="text-align:center">Selected Doctors List</h4></div>
<div class="panel-body">	
	<div class="row">
	
	<div class="col-lg-12">
	<div class="table-responsive">
        <table class="table  table-hover">
    <thead>
      <tr>
        <th>Doc_id</th>
        <th>Name</th>
        <th>Mobile</th>
        <th>Fee</th>
        <th>City</th>
        <th>Speciality</th>
      
        <th>Current Rating</th>
        <th>Number of Ratings</th>
        <th>New Rating</th>
        
        
      </tr>
    </thead>
    <tbody>
	<% 

Connection conn=null;
Statement stmt=null;
ResultSet rs =null;
final String DB_URL = "jdbc:mysql://localhost/drs_major";
final String USER = "root";
final String PASS = "";
String search_city=(String) session.getAttribute("search_city");
String speciality_code= (String) session.getAttribute("specialist");
try {
Class.forName("com.mysql.jdbc.Driver");



	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	stmt = conn.createStatement();
      
       String sql;
       sql="select doc_id,doc_rating,speciality_code,rating_count,name,mobile,fee,city from doctor where (city='"+search_city+"'and speciality_code='"+speciality_code+"')";
      // out.println(sql);
       String doc_name=null;
		int doc_fee=0;
		String doc_city=null;
		String doc_mobile=null;
		int doc_id=0;
		String count="a";
		String doc_speciality=null;
	
		String doc_doc_rating=null;
		String doc_rating_count=null;
		//out.println(doc_speciality);
       rs=stmt.executeQuery(sql);
     
while ( rs.next() )
{	
	   doc_id=Integer.parseInt(rs.getString("doc_id"));
	 
		doc_name=rs.getString("name");
		doc_fee=Integer.parseInt(rs.getString("fee"));
		doc_city=rs.getString("city");
		
		doc_mobile=rs.getString("mobile");
		
		
		doc_doc_rating=rs.getString("doc_rating");
		doc_rating_count=rs.getString("rating_count");
		switch(Integer.parseInt(rs.getString("speciality_code")))
		{
		case 1:
			doc_speciality="Gynaecologist";
			break;
		case 2:
			doc_speciality="Cardiologist";
			break;
		case 3:
			doc_speciality="Pediatrician";
			break;
		case 4:
			doc_speciality="Urologist";
			break;
		case 5:
			doc_speciality="Dentist";
			break;
		case 6:
			doc_speciality="Dermatologist";
			break;
		case 7:
			doc_speciality="Gastroenterologist";
			break;
		case 8:
			doc_speciality="Neurologist";
			break;
		case 9:
			doc_speciality="Homeopath";
			break;
		case 10:
			doc_speciality="ENT";
			break;
		case 11:
			doc_speciality="Ayurveda";
			break;
		case 12:
			doc_speciality="orthopedist";
			break;
		case 13:
			doc_speciality="Physiotherapist";
			break;
		case 14:
			doc_speciality="Psychiatrist";
			break;
		case 15:
			doc_speciality="Dietician/Nutritionist";
			break;
		default:
			doc_speciality="error";
			break;
	
			
			
		}
		
		
		count=count+"a";
		count=count.trim();
		
	
%>
 
 
  
  
  	<tr>
  	<td ><% out.println(doc_id); %> </td>
  	<td ><% out.println(doc_name); %></td>
  	<td ><% out.println(doc_mobile); %></td>
  	<td ><% out.println(doc_fee); %></td>
  	<td ><% out.println(doc_city); %></td>
  	<td ><% out.println(doc_speciality); %></td>
 
  		
  				<td ><% out.println(doc_doc_rating); %></td>
  					<td ><% out.println(doc_rating_count); %></td>
  					<td>
  					<form method="post" action="updateservlet">
<label id=<% out.print(count); %>  style="color:orange"><% out.println(doc_doc_rating); %>/5 </label>
  <input type="range" name="new_rating" value=<% out.println(doc_doc_rating); %> onchange="showruler(this.value,'<% out.print(count); %>');" id="whr" class="form-group" name="whr" min="1" max="5">
  	 	
  	
  	<input type="hidden" value=<% out.println(doc_id); %> name="doc_id"  />
  	<input type="hidden" value=<% out.println(doc_rating_count); %> name="rating_count"  />
  	<input type="hidden" value=<% out.println(doc_doc_rating); %> name="old_rating"  />
  	<input class="btn btn--default" type="submit" Style="color:#fff; background-color:#50A2A7" value="Update" />
  	</form>
  	</td>
  
  	</tr>
  	<%
   
}
conn.close();
}
catch(Exception ex)
{
}

%>
      
	</tbody>
  </table>
	</div>
	
	</div>
	</div>
	</div>
	</div>
			</section>
			
			<nav class="menu">
				<button class="btn btn--close"><svg class="icon icon--cross"><use xlink:href="#icon-cross"></use></svg></button>
				<ul class="menu__inner">
				
					<li class="menu__item"><a class="menu__link" href="profilepage.jsp">Profile</a></li>
					<li class="menu__item"><a class="menu__link" href="ratingpage.jsp">Ratings</a></li>
					<li class="menu__item"><a class="menu__link" href="Logout">Logout</a></li>				
				</ul>
			</nav>
			<!-- Related demos -->
			
		</main>
		<script src="js/anime.min.js"></script>
		<script src="js/main.js"></script>
		<script>
								function showruler(x,y)
								{
								
									
										
										if(x<3)
											document.getElementById(y).style.color = "red";
										else if(x>2 && x<4)
											document.getElementById(y).style.color = "orange";
										else
											document.getElementById(y).style.color = "green";
										document.getElementById(y).innerHTML =x+"/5";
								
								
								
									
								}
								</script>
		<script>
		(function() {
			var navEl = document.querySelector('nav.menu'),
				revealer = new RevealFx(navEl),
				closeCtrl = navEl.querySelector('.btn--close');

			document.querySelector('.btn--menu').addEventListener('click', function() {
				revealer.reveal({
					bgcolor: '#004346',
					duration: 400, 
					easing: 'easeInOutCubic',
					onCover: function(contentEl, revealerEl) {
						navEl.classList.add('menu--open');
						contentEl.style.opacity = 1;
					},
					onComplete: function() {
						closeCtrl.addEventListener('click', closeMenu);
					}
				});
			});

			function closeMenu() {
				closeCtrl.removeEventListener('click', closeMenu);
				navEl.classList.remove('menu--open');
				revealer.reveal({
					bgcolor: '#004346',
					duration: 400, 
					easing: 'easeInOutCubic',
					onCover: function(contentEl, revealerEl) {
						navEl.classList.remove('menu--open');
						contentEl.style.opacity = 0;
					}
				});
			}
		})();
		</script>
	</body>
</html>
