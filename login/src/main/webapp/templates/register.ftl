<#assign projectName="http://localhost:8080"/>
<#assign imageService="http://localhost:8010"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>测试Bootstrap</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/login.css" />
</head>

<body>
<!--导航栏-->
<div>
    <nav class="navbar navbar-default" style="margin-bottom: 0px;">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">YiJiuPi</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${projectName}/rest/user/home/1">首页 <span class="sr-only">(current)</span></a></li>
                    <li><a href="${projectName}/user/index.do">登陆</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

</div>

<!--内容栏-->
<div style="height: 400px;margin-top: 0px;">
    <div class="row" >
        <!--内容左侧菜单-->
        <div class="col-md-3" >
            <div class="row">
                <div class="col-md-3" style="background-color: #46B8DA;height: 400px;">左侧操作</div>
                <div class="col-md-9" style="background-color: #EC971F;height: 400px;">左侧内容</div>
            </div>
        </div>
        <!--内容中间栏-->
        <div class="col-md-6" style="background-color: lightgrey; height: 400px;" >
            <form action="${projectName}/user/register.do" id="registering" method="post">
                <table style="background-color: #C0C0C0;width: 70%;margin-left: 15%;margin-top: 8%;padding:10px ;">

                    <tr>
                        <td colspan="3">
                            <marquee scrollamount="10" bgcolor="aquamarine">${message }</marquee>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">
                            <font style="text-align: center;padding-left: 35%;"  color="#122B40">欢迎注册</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="tr2">
                            <label>图像:</label>
                        </td>
                        <td colspan="2" class="tr1">
                            <img id='imgSize1ImgSrc'src='' height="100" width="100" />
                            <#--onchange='submitImgSize1Upload()-->
                            <input	type='file' id='imgSize1File' name='imgSize1File' class="file" />
                            <input type='hidden'id='imgSize1' name='userPhoto' value='' reg="^.+$" tip="亲！您忘记上传图片了。" />
                        </td>
                    </tr>
                    <tr>
                        <td class="tr2">
                            <label>用户名:</label>
                        </td>
                        <td colspan="2" class="tr1">
                            <input type="text" name="userName" id="userName" value="" placeholder="请填入用户名" />
                        </td>
                    </tr>

                    <tr>
                        <td class="tr2">
                            <label>密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
                        </td>
                        <td colspan="1" class="tr1">
                            <input type="hidden" name="userPassword" id="userPassword" value="">
                            <input type="password" name="password" id="password" value="" >
                        </td>
                    </tr>

                    <tr>
                        <td class="tr2">确认密码:</td>
                        <td colspan="2" class="tr1">
                            <input type="password" name="passwordRepeater" id="passwordRepeater" value="" />
                        </td>
                    </tr>

                    <tr>
                        <td class="tr2">性&nbsp;&nbsp;&nbsp;&nbsp;别:</td>
                        <td colspan="2" class="tr1">
                            <input type="radio" name="userSex" id="sex1" value="男" checked="checked"/>男
                            <input type="radio" name="userSex" id="sex2" value="女" />女
                        </td>
                    </tr>

                    <tr>
                        <td class="tr2">工&nbsp;&nbsp;&nbsp;&nbsp;资:</td>
                        <td>
                            <input type="number" name="userSalary" id=" " value="" />
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="submit" name="register" id="register" value="注册" style="margin-left: 35%;"/>
                            <input type="reset" name="reseting" id="reseting" value="重置" />
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <!--内容右边 -->
        <div class="col-md-3" style="background-color: #888888;height: 400px;">.col-md-3</div>
    </div>

</div>

<!--尾部栏-->
<div style="background-color: burlywood;">
    <div class="row">
        <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
        <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
        <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
    </div>

</div>

</body>

</html>