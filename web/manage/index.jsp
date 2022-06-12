<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/commons/header.jsp"%>
<div class="main-content"  style="width: 100%">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="${pageContext.request.contextPath}/manage/">Головна</a>
				</li>

				<li class="active">Панель приладів</li>
			</ul>

			<div class="nav-search" id="nav-search">
				<form class="form-search">
					<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
						<i class="ace-icon fa fa-search nav-search-icon"></i>
					</span>
				</form>
			</div>
		</div>

		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listPaintings" class="btn btn-default btn-app radius-4" style="width: 10%">
								<i class="ace-icon fa fa-table bigger-230"></i>
								Список картин
							</a>
							<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listWays" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-table bigger-230"></i>
								Список напрямів
							</a>
							<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listArtists" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-table bigger-230"></i>
								Список художників
							</a>
							<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listCategories" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-table bigger-230"></i>
								Список категорій
							</a>
							<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addPaintingUI" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-plus bigger-230"></i>
								Додати картину
							</a>
							<a href="${pageContext.request.contextPath}/manage/addWay.jsp" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-plus bigger-230"></i>
								Додати напрям
							</a>
							<a href="${pageContext.request.contextPath}/manage/addArtist.jsp" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-plus bigger-230"></i>
								Додати художника
							</a>
							<a href="${pageContext.request.contextPath}/manage/addCategory.jsp" class="btn btn-default btn-app radius-4" style="width: 12%">
								<i class="ace-icon fa fa-plus bigger-230"></i>
								Додати категорію
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@ include file="/manage/commons/footer.jsp"%>