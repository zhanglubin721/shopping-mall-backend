# 商城前台后台一体（配合认证服务做登录效果演示使用）

#### 使用说明
后端controller接口返回商城首页
商城首页js发送请求到后端判断当前是否已登录（session中是否存有登录信息）
如果未登录，后端返回后商城首页js跳转至三方登录页面登录
登录成功重定向到商城首页
商城首页再次向后端发请求判断是否已登录（url中是否携带登陆成功后自动带有的code参数）
后端自动向认证服务器确认已登录，并在session中存储登录信息
后端返回登录成功信息，前端页面隐藏登录按钮，并显示欢迎 {用户名}