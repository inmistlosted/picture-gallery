<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Адміністратор</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/bootstrap.min.css" />
                <link rel="shortcut icon"  href="${pageContext.request.contextPath}/res/shop_assets/images/gallery-icon.png" sizes="16x16" type="image/png">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/font-awesome/4.5.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/fonts.googleapis.com.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/manage_assets/css/ace-ie.min.css" />

		<script src="${pageContext.request.contextPath}/res/manage_assets/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/res/manage_assets/js/html5shiv.min.js"></script>
		<script src="${pageContext.request.contextPath}/res/manage_assets/js/respond.min.js"></script>
	</head>
	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="${pageContext.request.contextPath}/manage/" class="navbar-brand">
						<small>
							<i class="fa fa-painting"></i>
							Адміністрування галереї
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${pageContext.request.contextPath}/res/manage_assets/images/avatars/avatar3.png" alt="Jason's Photo" />
								<span class="user-info">
									<small>Доброго дня,</small>
									Адміністратор
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								

								<li>
									<a href="${pageContext.request.contextPath}/index.jsp">
										<i class="ace-icon fa fa-power-off"></i>
										Вийти
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div style="display: flex">
		<div class="main-container ace-save-state" id="main-container" style="float: left">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar responsive ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>

				<ul class="nav nav-list">
					<li class="">
						<a href="${pageContext.request.contextPath}/manage/index.jsp">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> Панель приладів </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/manage/addCategory.jsp">
							<i class="menu-icon fa fa-plus"></i>
							<span class="menu-text">Додати категорію </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listCategories">
							<i class="menu-icon fa fa-table"></i>
							<span class="menu-text">Переглянути категорії</span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/manage/addArtist.jsp">
							<i class="menu-icon fa fa-plus"></i>
							<span class="menu-text">Додати художника </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listArtists">
							<i class="menu-icon fa fa-table"></i>
							<span class="menu-text" style="font-size: 12px">Переглянути художників</span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/manage/addWay.jsp">
							<i class="menu-icon fa fa-plus"></i>
							<span class="menu-text">Додати напрям </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listWays">
							<i class="menu-icon fa fa-table"></i>
							<span class="menu-text">Переглянути напрями</span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addPaintingUI">
							<i class="menu-icon fa fa-plus"></i>
							<span class="menu-text">Додати картину </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listPaintings">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text">Переглянути картини </span>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
		</div>