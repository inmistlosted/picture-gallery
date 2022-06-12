<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/commons/header.jsp"%>
<div class="main-content" style="width: 100%">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="${pageContext.request.contextPath}/manage/">Головна</a>
                </li>

                <li>
                    <a href="#">Художник</a>
                </li>
                <li class="active">Редагувати художника</li>
            </ul>

            <div class="nav-search" id="nav-search">
                <form class="form-search">
                    <span class="input-icon">
                        <input type="text" placeholder="Найти ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                        <i class="ace-icon fa fa-search nav-search-icon"></i>
                    </span>
                </form>
            </div>
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Редагувати художника
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Підтвердити зміну художника
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/servlet/ManageServlet?op=editArtist" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Ім'я </label>
                            <input type="text" name="id" value="${a.id}" style="display: none"/>

                            <div class="col-sm-9">
                                <input type="text" id="name" placeholder="назва" class="col-xs-10 col-sm-5" name="name" value="${a.name}"/>
                                <span class="help-inline col-xs-12 col-sm-7">
                                    <span id="sname"></span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Країна </label>

                            <div class="col-sm-9">
                                <input type="text" name="country" class="col-xs-10 col-sm-5" rows="5" id="cat_des" value="${a.country}" />
                            </div>
                        </div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Зберегти
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Очистити
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/manage/commons/footer.jsp"%>