<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); ?>
<div class="nav-line"><a class="first" href="<?=SITE_URL?>"><?=$setting['site_name']?></a> &gt; <span>财富商城</span></div>
<div class="wrapper clearfix">
    <div class="content-left">
        <div class="modbox">
            <ul class="gift-list clearfix">
                
<? if(is_array($giftlist)) { foreach($giftlist as $gift) { ?>
                <li>
                    <img width="138" height="138" src="<?=SITE_URL?><?=$gift['image']?>" alt="<?=$gift['title']?>" onclick="show_desc('<?=$gift['title']?>', <?=$gift['id']?>);"/>
                    <a class="name" title="<?=$gift['title']?>"  href="javascript:void(0);" onclick="show_desc('<?=$gift['title']?>', <?=$gift['id']?>);"><?=$gift['title']?></a>
                    <span>(售价：<?=$gift['credit']?>财富）</span>
                    <div style="display:none" id="<?=$gift['id']?>_desc"><?=$gift['description']?></div>
                    <span><input type="button" class="button_4" value="立即兑换" onclick="exchange(<?=$gift['id']?>, <?=$gift['credit']?>);"/></span>
                </li>                
                
<? } } ?>
            </ul>
        </div>
        <div class="pages"><?=$departstr?></div>
        <div class="tipmod mt10">
            <h3>温馨提示：</h3>
            <div class="credit_note">
                <p>为了保证您所兑换的礼品能够及时送到，请您仔细阅读下列内容：</p>
                <p>1.请您填写详细的联系地址：省、市、区、县、村、路（街道号）、单位，注明您的邮编，真实姓名还有联系方式。</p>
                <div class="credit_note">
                    <p>详细地址示例： </p>
                    <p>a.单位地址</p>
                    <p>XX省XX市XX区XX路XX号 XX办公楼XX写字楼XX房间号XX公司<br>
                    </p>
                    <p>b.学校地址(请您一定要注明所在年级和班级)</p>
                    <p>XX省XX市XX区XX路XX号XX学校 XX年级XX班级<br>
                    </p>
                    <p>c.家庭地址(请您注明所在小区的楼号及门牌号)</p>
                    <p>XX省XX市XX区XX路XX号XX小区XX楼XX单元XX门牌号</p>
                </div>
                <p>2.由于快递公司所到地区有限，如果您的所在地快递不能到达，请在备注中注明，我们会为您转发EMS。</p> 
                <p>3.如有任何问题，请及时<a href="mailto:<?=$setting['admin_email']?>" target="_blank">联系我们</a>.</p> 
            </div>
        </div>        
    </div>
    <div class="aside-right">
        <? if($user['uid']) { ?>        <div class="modbox mb10">
            <div class="userinfo-box">
                <div class="userinfo clearfix">
                    <a target="_blank" href="<?=SITE_URL?>?user/default.html" class="pic"><img width="50" height="50" src="<?=$user['avatar']?>"></a>
                    <h3><a target="_blank" href="<?=SITE_URL?>?user/default.html"><?=$user['username']?></a></h3>
                    <p>当前财富值:<img src="<?=SITE_URL?>css/default/gold.gif"><font color="#FF6600"><?=$user['credit2']?></font></p>
                    <p><a href="<?=SITE_URL?>?index/help.html#如何获得积分" target="_blank">如何获得财富?</a></p>
                </div>
            </div>
        </div>
        <? } ?>        <div class="modbox">
            <div class="title">礼品公告</div>
            <div class="rcontent clearfix"><?=$setting['gift_note']?></div>
        </div>
        <div class="modbox mt10">
            <h3 class="title">财富榜</h3>
            <ul class="right-list" id="alltop">
                <? $weekuserlist=$this->fromcache('alluserlist'); ?>                
<? if(is_array($weekuserlist)) { foreach($weekuserlist as $index => $alluser) { ?>
                <? $index++; ?>                <li>
                    <? if($index<4) { ?>                    <em class="n1"><?=$index?></em>
                    <? } else { ?>                    <em class="n2"><?=$index?></em>
                    <? } ?>                    <a href="<?=SITE_URL?>?u-<?=$alluser['uid']?>.html" target="_blank" onmouseover="pop_user_on(this, '<?=$alluser['uid']?>', 'text');"  onmouseout="pop_user_out();"><?=$alluser['username']?></a>
                    <span class="credit"><?=$alluser['credit2']?></span>
                </li>
                
<? } } ?>
            </ul>
        </div>
        <div class="modbox mt15">
            <div class="title">兑换动态</div>
            <div id="exchange_detail" class="exchange">
                <? if($loglist) { ?>                
<? if(is_array($loglist)) { foreach($loglist as $giftlog) { ?>
                <p><a href="<?=SITE_URL?>?u-<?=$giftlog['uid']?>.html" target="_blank"><?=$giftlog['username']?></a> 刚刚兑换了礼品"<?=$giftlog['giftname']?>"</p>
                
<? } } ?>
                <? } ?>            </div>
        </div>
    </div>
</div>
<div id="gift_desc" title="礼品详情" style="display: none;"></div>
<div id="exchangeform" title="兑换礼品" style="display: none">
    <form name="loginform"  action="<?=SITE_URL?>?gift/add.html" method="post">
        <input type="hidden" name="gid"  id="gid" value="" />
        <div class="input-bar">
            <h2>真实姓名</h2>
            <input type="text" class="normal-input" id="realname" name="realname" />
            <span class="input_warming">请务必填入真实姓名</span>
        </div>
        <div class="clr"></div>
        <div class="input-bar">
            <h2>电子邮箱</h2>
            <input type="text" class="normal-input" id="email" name="email" />
            <span class="input_warming">常用邮箱地址</span>
        </div>
        <div class="clr"></div>
        <div class="input-bar">
            <h2>联系电话</h2>
            <input type="text" class="normal-input" id="phone" name="phone"  />
            <span id="repasswordtip" class="input_warming">您的联系电话</span>
        </div>
        <div class="clr"></div>
        <div class="input-bar">
            <h2>邮寄地址</h2>
            <input type="text" class="normal-input" id="addr" name="addr"  onblur="check_email();"/>
            <span id="emailtip" class="input_warming">您的联系地址</span>
        </div>
        <div class="clr"></div>
        <div class="input-bar">
            <h2>邮政编码</h2>
            <input type="text" class="normal-input" id="postcode" name="postcode" />
        </div>
        <div class="clr"></div>
        <div class="input-bar">
            <h2>QQ</h2>
            <input type="text" class="normal-input" id="qq" name="qq" />
        </div>
        <div class="clr"></div>
        <div class="input-bar">
            <h2>备注</h2>
            <input type="text" class="normal-input" id="notes" name="notes" />
        </div>
        <div class="clr"></div>
        <div class="auto-login">
            <input type="submit" value="提&nbsp;交" class="normal-button" name="submit" />
        </div>
    </form>
    <div class="clr mt15"></div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        	if($("#exchange_detail").height()>250){
$("#exchange_detail").height(250);
$("#exchange_detail").css("overflow","hidden");
var scroll=new s('exchange_detail',2000,30);
scroll.bind();
scroll.start();
}
    });
            function s(zxdt, delay, speed){
                    this.rotator = $("#" + zxdt);
                    this.delay = delay || 1000;
                    this.speed = speed || 20;
                    this.tid = this.tid2 = this.firstp = null;
                    this.pause = false;
                    this.num = 0;
                    this.p_length = $("#exchange_detail p").length;
                    }
    s.prototype = {
    bind:function(){
    var o = this;
            this.rotator.hover(function(){o.end(); }, function(){o.start(); });
    },
            start:function(){
            this.pause = false;
                    if ($("#exchange_detail p").length == this.p_length){
            this.firstp = $("#exchange_detail p:first-child");
                    this.rotator.append(this.firstp.clone());
            }
            var o = this;
                    this.tid = setInterval(function(){o.rotation(); }, this.speed);
            },
            end:function(){
                    this.pause = true;
                    clearInterval(this.tid);
                    clearTimeout(this.tid2);
            },
            rotation:function(){
                    if (this.pause)return;
                    var o = this;
                    var firstp = $("#exchange_detail p:first-child");
                    this.num++;
                    this.rotator[0].scrollTop = this.num;
                    if (this.num == this.firstp[0].scrollHeight + 8){
                        clearInterval(this.tid);
                        this.firstp.remove();
                        this.num = 0;
                        this.rotator[0].scrollTop = 0;
                        this.tid2 = setTimeout(function m(){o.start(); }, this.delay);
                    }
            }
    }
    function show_desc(title, gid) {
    $("#gift_desc").attr("title", title + "详情");
            $("#gift_desc").html($("#" + gid + "_desc").html());
            $("#gift_desc").dialog({
    autoOpen: false,
            width: 600,
            modal: true,
            resizable: true
    });
            $("#gift_desc").dialog("open");
    }
    function exchange(id, credit) {
    var uid = "<?=$user['uid']?>";
            var usercredit = "<?=$user['credit2']?>";
            if (uid == 0) {
    login();
            return false;
    }
    if (credit > usercredit){
            alert("抱歉!您的财富值不够!");
            return false;
    }
    if(!confirm("确定兑换该礼品？完成兑换后会消耗您"+credit+"财富值!")){
        return false;
    }
    $("#gid").val(id);
            $("#exchangeform").dialog({
            autoOpen: false,
            width: 560,
            modal: false,
            resizable: false
    });
            $("#exchangeform").dialog("open");
    }
</script>
<? include template('footer'); ?>
