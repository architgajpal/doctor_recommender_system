<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <% if(session.getAttribute("name")!=null)
{
   response.sendRedirect("homepage.jsp");
}
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Doctor Recommender System</title>
		<meta name="description" content="Ideas for revealing content in a schematic box look." />
		<meta name="keywords" content="javascript, plugin, reveal, effect, demo, web development, web design, template" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="favicon.ico">
		<link href="https://fonts.googleapis.com/css?family=Inconsolata:400,700|Poppins:700" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/revealer.css" />
		<link rel="stylesheet" type="text/css" href="css/pater.css" />
		<script>document.documentElement.className = 'js';</script>
		
	</head>
	<body class="demo-form">
		<svg class="hidden">
			
			<symbol id="icon-cross" viewBox="0 0 10.2 10.2">
				<title>cross</title>
				<path d="M5.8,5.1l4.4,4.4l-0.7,0.7L5.1,5.8l-4.4,4.4L0,9.5l4.4-4.4L0,0.7L0.7,0l4.4,4.4L9.5,0l0.7,0.7L5.8,5.1z"/>
			</symbol>
		</svg>
		<main>
			
			<header class="codrops-header">
				
				<h1 class="codrops-header__title">Doctor Recommender System</h1>
				
			</header>
			<section class="content flexy">
				<div class="box box--border box--small">
					
					<form name="loginForm" method="post" action="Login" >
					<h3 >For Existing Users</h3>
					
			
					
					<div >
						<label class="form__label">Email</label>
						<input class="form__input" type="email" name="email">
					</div>
					<div >
						<label class="form__label">Password</label>
						<input class="form__input" type="password" name="password">
					</div>
					
					<div >
						
						<input class="btn btn--default" type="submit" value="Login" />
											
						
						
					
					</div>
				</form>
					<a href="guest.jsp" class="btn btn--default">Continue as Guest User</a>
									</div>
			</section>
									
			<!-- Related demos -->
			
		</main>
		<script src="js/anime.min.js"></script>
		<script src="js/main.js"></script>
				<script>
		(function() {
			var modalEl = document.querySelector('.modal'),
				revealer = new RevealFx(modalEl),
				deleteCtrl = modalEl.querySelector('.btn--default'),
				closeCtrl = modalEl.querySelector('.btn--modal-close');

			document.querySelector('.btn--modal-open').addEventListener('click', function() {
				modalEl.classList.add('modal--open');
				revealer.reveal({
					bgcolor: '#004346',
					direction: 'tb',
					duration: 400, 
					easing: 'easeOutCirc',
					onCover: function(contentEl, revealerEl) {
						contentEl.style.opacity = 1;
					},
					onComplete: function() {
						closeCtrl.addEventListener('click', closeModal);
						deleteCtrl.addEventListener('click', closeModal);
					}
				});
			});

			function closeModal(ev) {
				closeCtrl.removeEventListener('click', closeModal);
				deleteCtrl.removeEventListener('click', closeModal);
				modalEl.classList.remove('modal--open');
				revealer.reveal({
					bgcolor: ev.target.classList.contains('btn--modal-close') ? '#004346' : '#40f180',
					direction: 'bt',
					duration: ev.target.classList.contains('btn--modal-close') ? 200 : 400, 
					easing: 'easeOutCirc',
					onCover: function(contentEl, revealerEl) {
						contentEl.style.opacity = 0;
					},
					onComplete: function() {
						modalEl.classList.remove('modal--open');
					}
				});
			}
		})();
		</script>
		<script>
		(function() {
			var formEl = document.querySelector('section.form'),
				revealer = new RevealFx(formEl),
				closeCtrl = formEl.querySelector('.form__close');

			document.querySelector('.btn--form-open').addEventListener('click', function() {
				revealer.reveal({
					bgcolor: '#8f40f1',
					direction: 'bt',
					duration: 600,
					onCover: function(contentEl, revealerEl) {
						formEl.classList.add('form--open');
						contentEl.style.opacity = 1;
					},
					onComplete: function() {
						closeCtrl.addEventListener('click', closeForm);
					}
				});
			});

			function closeForm() {
				closeCtrl.removeEventListener('click', closeForm);
				formEl.classList.remove('form--open');
				revealer.reveal({
					bgcolor: '#8f40f1',
					direction: 'tb',
					duration: 600, 
					onCover: function(contentEl, revealerEl) {
						formEl.classList.remove('form--open');
						contentEl.style.opacity = 0;
					}
				});
			}

			formEl.addEventListener('submit', function(ev) {ev.preventDefault();});
		})();
		</script>
	</body>
</html>