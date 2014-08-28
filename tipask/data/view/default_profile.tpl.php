<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); ?>
<div class="wrapper mt10 clearfix">
    
<? include template('user_menu'); ?>
    <div class="user-content">
        <div class="my-answerbox">
            <div class="title">个人信息</div>
            <div id="qa-tabcard">
                <ul>
                    <li class="on">个人资料</li>
                    <li><a href="<?=SITE_URL?>?user/uppass.html">修改密码</a></li>
                    <li><a href="<?=SITE_URL?>?user/editimg.html">修改头像</a></li>
                    <li><a href="<?=SITE_URL?>?user/mycategory.html">我的设置</a></li>
                </ul>
            </div>
            <div class="loginform">
                <form method="POST" name="upinfoForm"  action="<?=SITE_URL?>?user/profile.html">
                    <div class="input-bar">
                        <h2>用户名:</h2>
                        <span class="text"><?=$user['username']?></span>
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>邮箱地址:</h2>
                        <input type="text" name="email" id="email" class="normal-input" <? if($user['email']!='null') { ?> value="<?=$user['email']?>"<? } else { ?>value=""<? } ?> />
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>消息设置:</h2>
                        <label><input class="normal_checkbox" type="checkbox" <? if(1 & $user['isnotify']) { ?>checked<? } ?> value="1" name="messagenotify" />站内消息&nbsp;&nbsp;</label>
                        <label><input class="normal_checkbox"  type="checkbox" <? if(2 & $user['isnotify']) { ?>checked<? } ?> value="2" name="mailnotify" />邮件通知</label>
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>性别:</h2>
                        <label><input type="radio" value="1" class="normal_radio" name="gender" <? if((1 == $user['gender'])) { ?> checked <? } ?> />男&nbsp;&nbsp;</label>
                        <label><input type="radio" value="0" class="normal_radio" name="gender" <? if((0 == $user['gender'])) { ?> checked <? } ?>/>女 &nbsp;&nbsp;</label>
                        <label><input type="radio" value="2" class="normal_radio" name="gender" <? if((2 == $user['gender'])) { ?> checked <? } ?> />保密</label>
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>生日:</h2>
                        <? $bdate=explode("-",$user['bday']); ?>                        <select id="birthyear" name="birthyear" onchange="showbirthday();" class="normal_select">
                            <? $curyear=date("Y"); ?>                            <? $yearlist = range(1911,$curyear); ?>                            
<? if(is_array($yearlist)) { foreach($yearlist as $year) { ?>
                            <option value="<?=$year?>" <? if($bdate['0']==$year) { ?>selected<? } ?> ><?=$year?></option>
                            
<? } } ?>
                        </select> 年&nbsp;&nbsp;
                        <select id="birthmonth" name="birthmonth" onchange="showbirthday();" class="normal_select">
                            <? $monthlist = range(1,12); ?>                            
<? if(is_array($monthlist)) { foreach($monthlist as $month) { ?>
                            <option value="<?=$month?>" <? if($bdate['1']==$month) { ?>selected<? } ?>><?=$month?></option>
                            
<? } } ?>
                        </select> 月&nbsp;&nbsp;
                        <select id="birthday" name="birthday" class="normal_select">
                            <? $dayhlist = range(1,31); ?>                            
<? if(is_array($dayhlist)) { foreach($dayhlist as $day) { ?>
                            <option  value="<?=$day?>" <? if($bdate['3']==$day) { ?>selected<? } ?>><?=$day?></option>
                            
<? } } ?>
                        </select>日
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>手机:</h2>
                        <input type="text"  name="phone" id="phone" class="normal-input" value="<?=$user['phone']?>">
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>QQ:</h2>
                        <input type="text"  name="qq" id="qq" class="normal-input" value="<?=$user['qq']?>"/>
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>MSN:</h2>
                        <input type="text"  name="msn" id="msn" class="normal-input" value="<?=$user['msn']?>"/>
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>身份介绍:</h2>
                        <input type="text"  name="introduction" id="introduction" class="normal-input" value="<?=$user['introduction']?>" />
                    </div>
                    <div class="clr"></div>
                    <div class="input-bar">
                        <h2>签名:</h2>
                        <textarea name="signature" id="signature" style="width:288px;height:89px;" class="normal_text" maxlength="200"><?=$user['signature']?></textarea>
                    </div>
                    <div class="clr"></div>
                    <div class="auto-login">
                        <input type="submit" name="submit" class="normal-button" value="保&nbsp;存" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<? include template('footer'); ?>
