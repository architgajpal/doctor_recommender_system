<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% if(session.getAttribute("name")==null)
{ out.print("<script>alert('Login then continue!')</script>");
   response.sendRedirect("homepage.jsp");
}
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Doctor Recommender System | Profile</title>
		<meta name="description" content="Ideas for revealing content in a schematic box look." />
		<meta name="keywords" content="javascript, plugin, reveal, effect, demo, web development, web design, template" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="favicon.ico">
		<link href="https://fonts.googleapis.com/css?family=Inconsolata:400,700|Poppins:700" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/revealer.css" />
		<script>document.documentElement.className = 'js';</script>
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
		<main>
			<header class="codrops-header">
				
				<h1 class="codrops-header__title">Welcome, Aniket</h1>
				
				<button class="btn btn--menu"><svg class="icon icon--menu"><use xlink:href="#icon-menu"></use></svg></button>
			</header>
			<section class="content flexy flexy--center">
				<p class="content__heading content__heading--center"><strong>view and edit user profile</strong></p>
			</section>
			<nav class="menu">
				<button class="btn btn--close"><svg class="icon icon--cross"><use xlink:href="#icon-cross"></use></svg></button>
				<ul class="menu__inner">
				<li class="menu__item"><a class="menu__link" href="homepage.jsp">Home</a></li>
					
					<li class="menu__item"><a class="menu__link" href="ratingpage.jsp">Ratings</a></li>
					<li class="menu__item"><a class="menu__link" href="Logout">Logout</a></li>
					
				</ul>
			</nav>
			<!-- Related demos -->
			
		</main>
		<script src="js/anime.min.js"></script>
		<script src="js/main.js"></script>
		<script>
		(function() {
			var navEl = document.querySelector('nav.menu'),
				revealer = new RevealFx(navEl),
				closeCtrl = navEl.querySelector('.btn--close');

			document.querySelector('.btn--menu').addEventListener('click', function() {
				revealer.reveal({
					bgcolor: '#7f40f1',
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
					bgcolor: '#7f40f1',
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
