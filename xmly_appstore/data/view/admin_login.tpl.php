<? if(!defined('IN_TIPASK')) exit('Access Denied'); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><? $user=$this->user; $setting=$this->setting; ?><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=<?=TIPASK_CHARSET?>">
<title>管理登录-<?=$setting['site_name']?></title>
<style type="text/css"> 
body{padding:0;margin:0px;font-size:12px;color:#333;font-family:Verdana; background:#fbfbfb;}
select{margin-left:1.5em;vertical-align:middle;border:1px solid #b4cceb;height:22px;font-size:12px;}
#main{width:600px;margin:auto;}
*{padding:0;margin:0}
input{font-size:12px;font-family:Verdana;}
#wrap{height:100px;}
#wrapc{height:315px; border:#c9dff5 1px solid; background:#edf4fa;}
#logo{background:url(<?=SITE_URL?>css/default/logo.png) center top no-repeat;height:64px;width:210px;}
.logo{padding:40px 0 0 175px;}
.login{margin:0 0 0 30px;padding-top:10px;}
.login th{height:33px;line-height:33px;list-style:none;text-align:right;font-weight:normal;width:200px;font-size:12px;}
.login td{text-align:left;font-size:12px;}
.input{font-size:12px;vertical-align:middle;height:17px;margin-top:2px;border:1px solid #fff;color:#666;}
.logo-icon{border: 1px solid; border-color: #8cb7e1 #e3f0fc #e3f0fc #8cb7e1;float:left;margin-left:1.5em;_margin-left:.7em;background:#fff;padding-right:.5em;}
.logo-icon div{background:#fff url(<?=SITE_URL?>css/admin/login-icon.gif);width:18px;float:left;margin-top:3px;margin-left:.5em;}
.logo-icon .pw{background-position:1px 1px;width:18px;height:20px;}
.logo-icon .pwpd{background-position:0 -49px;width:18px;height:20px;}
.logo-icon .yan{background-position:0 -99px;width:18px;height:20px;}
.logo-icon .daan{background-position:1px -148px;width:18px;height:20px;}
.logo-icon .pw2{width:9em;}
.logo-icon .pwpd2{width:9em;}
.logo-icon .yan2{width:6em;}
.getpwd{ line-height:25px; padding-left:5px;}
.getpwd a{ color:#666}
.bottom{text-align:center;margin:auto;padding-top:1em;color:#888;}
</style>
</head>
<body>
<div id="main">
<div id="wrap">&nbsp;</div>
  <div id="wrapc">
      <div class="logo">
      <div id="logo"></div>
    </div>
    <div class="login">

     <form   action="index.php?admin_main/login<?=$setting['seo_suffix']?>" method="post">
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <th>管理员帐号</th>
            <td><div class="logo-icon">
                <div class="pw"></div>
                <input class="input pw2" id="username" name="username" value="<?=$user['username']?>" type="text"/> 
              </div></td>
          </tr>
          <tr>
            <th>管理员密码</th>
            <td><div class="logo-icon">
                <div class="pwpd"></div>
                <input type="password" name="password" size="32" tabindex="0" class="input pwpd2">
              </div>
  </td>
          </tr> 
          <tr>
            <th></th>
            <td><input name="submit" type="image" src="<?=SITE_URL?>css/admin/login.gif" style="margin-left:1.5em;margin-top:.5em;" />
            </td>
          </tr>
        </table> 
</form>
    </div>
  </div>
</div>
<div style="clear:both;text-align:center;margin:5px auto;">Powered by <a href="http://www.tipask.com/" target="_blank">Tipask</a> <?=TIPASK_VERSION?>	&copy; 2009-2010  Tipask.</div>
</body>
</html>