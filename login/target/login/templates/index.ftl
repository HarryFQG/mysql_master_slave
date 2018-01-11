<#assign projectName="http://localhost:8080"/>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>测试Bootstrap</title>
		<script type="text/javascript" src="${projectName}/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${projectName}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${projectName}/js/login.js" ></script>
		<link rel="stylesheet" href="${projectName}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${projectName}/css/login.css" />
	</head>
	<script></script>

	<body>
		<!--导航栏-->
		<div class="header-login">
			<div id="menu-login-top">
				<img src="${projectName}/img/mpt8_2013071209535217.jpg" />
				<hr style="color: #000000;border-top: 2px  solid #888888 ; margin-top: 5px; margin-bottom: 5px; " />
			</div>
			<div id="menu-login">

				<ul class="nav nav-tabs">
					<li role="presentation" class="active">登陆</li>
					<li role="presentation"><a href="${projectName}/rest/user/home/1">主页</a></li>
					<li role="presentation"><a href="#"><a href="${projectName}/user/registerHtml.do">注册</a></a></li>
					<li role="presentation"><a href="#">菜单栏三</a></li>
				</ul>
			</div>
		</div>
		<!--内容栏-->
		<div class="content-login">
			<!--内容栏左边-->
			<div class="content-login-left">
				<fieldset id="content-login-fieldset">
					<legend style="margin-left: 10%;padding-left: 5%;width: 30%;font-family: '微软雅黑' ;font-size: 16px;">操作</legend>
					<div id="content-login-fieldset-menu">
						<div class="row">
							<div class="col-md-4">
								<ul class="list-group">
									<li class="list-group-item"><a>操作分类一</a> </li>
									<li class="list-group-item"><a>操作分类二</a></li>
									<li class="list-group-item"><a>操作分类三</a></li>
									<li class="list-group-item"><a>操作分类四</a></li>
									<li class="list-group-item"><a>操作分类五</a></li>
								</ul>
							</div>
							<div class="col-md-8">

							</div>

						</div>
					</div>
				</fieldset>
			</div>
			<!--内容栏中间-->
			<div class="content-login-center">

				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div id="content-login-center-2">
							<fieldset id="login-operation">
								<legend style="margin-top: 30%;width: 30%;font-family: '微软雅黑' ;font-size: 16px;">欢迎登录</legend>
								<form action="${projectName}/user/login.do" id="submit1" method="post">
									<table>
										<tr >
											<td colspan="3"><marquee scrollamount="10" bgcolor="aquamarine">${message }</marquee></td>
										</tr>
										<tr class="tr1">
											<td class="td1">
												<label>账&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
											</td>
											<td class="td2" colspan="2">
												<input type="text" name="userName" id="userName" placeholder="请输入用户名" />
											</td>
										</tr>
										<tr class="tr1">
											<td class="td1">
												<label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
											</td>
											<td class="td2" colspan="2">
												<input type="password" name="password" id="password" value="" placeholder="请输入密码" />
											</td>
	
										</tr>
										<tr class="tr1">
											<td class="td1"><label>验证码：</label></td>
											<td class="td2" style="width: 50px;"> 
												<input type="text" name="authCode" id="authCode" value="" placeholder="请输入验证码" style="width: 90px" /> </td>
											<td class="td2">
												<input type="hidden" id="codeHi" />
												<img src="${projectName}/user/validateCode.do" id="validateCode" />
											</td>
										</tr>
                                        <tr class="tr1">
                                            <td class="td1"><label>七天免登陆：</label></td>
                                            <td class="td2" colspan="2">
                                                <input type="radio" name="avoidLogin" id="avoidLogin" value="1"  />7天免登陆
                                            </td>
                                        </tr>
										<tr class="tr1">
											<td class="td1" colspan="2">
												<input type="submit"  value="登录" style="background-color: #449D44;border: 2px solid #ADADAD; border-radius: 10px;margin-left:20% ;width:60px ;height: 30px;" />
											</td>
											<td>
												<input type="button" value="算了" style="background-color: #BCE8F1;border: 2px solid #ADADAD; border-radius: 10px;width:60px ;height: 30px;" />
											</td>
										</tr>
									</table>

								</form>
							</fieldset>

						</div>

					</div>
					<div class="col-md-3">

					</div>

				</div>

			</div>
			<!--内容栏右边-->
			<div class="content-login-right">
				<div>
					<ul class="list-group">
						<li class="list-group-item">
							<span class="badge">14</span>页面创建时间:	${createHtmlTime}
						</li>
						<li class="list-group-item">
							<span class="badge">14</span> Cras justo odio
						</li>
						<li class="list-group-item">
							<span class="badge">14</span> Cras justo odio
						</li>
						
					</ul>

				</div>
			</div>
		</div>

		<!--尾部栏-->
		<div class="footer-login">
			<div style="align-content: center">
				<address style="margin-left: 40%;">&copy;北京易酒批电子商务有限公司</address>
			</div>
		</div>

	</body>

</html>