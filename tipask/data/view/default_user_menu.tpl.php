<? if(!defined('IN_TIPASK')) exit('Access Denied'); ?>
<div class="user-aside">
    <div class="usermenu">
        <div class="userbox clearfix">
            <a class="pic" title="<?=$user['username']?>" href="<?=SITE_URL?>?user/profile.html"><img width="60" height="60" src="<?=$user['avatar']?>" alt="<?=$user['username']?>"></a>
            <h3><?=$user['username']?></h3>
            <span><?=$user['grouptitle']?></span>
<!--            <span class="user-level">LV<?=$user['level']?></span>-->
        </div>
        <ul class="menu clearfix">
            <li><a href="<?=SITE_URL?>?user/answer/1.html">回答数</a><?=$user['answers']?></li>
            <li><a href="<?=SITE_URL?>?user/ask/1.html">提问数</a><?=$user['questions']?></li>
            <li><a href="#">赞同数</a><?=$user['supports']?></li>
        </ul>
        <ul class="user-nav">
            <li<? if(strstr($regular,"user/score")) { ?> class="on"<? } ?>><? if(strstr($regular,"user/score")) { ?>我的首页<? } else { ?><a href="<?=SITE_URL?>?user/score.html">我的首页</a><? } ?></li>
            <li<? if(in_array($regular,array('user/profile','user/uppass','user/mycategory','user/editimg'))) { ?> class="on"<? } ?>><? if(in_array($regular,array('user/profile','user/uppass','user/mycategory','user/editimg'))) { ?>个人信息<? } else { ?><a href="<?=SITE_URL?>?user/profile.html">个人信息</a><? } ?></li>
            <li<? if(strstr($regular,"user/recommend")) { ?> class="on"<? } ?>><? if(strstr($regular,"user/recommend")) { ?>为我推荐<? } else { ?><a href="<?=SITE_URL?>?user/recommend.html">为我推荐</a><? } ?></li>
            <li<? if(strstr($regular,"user/ask")) { ?> class="on"<? } ?>><? if(strstr($regular,"user/ask")) { ?>我的提问<? } else { ?><a href="<?=SITE_URL?>?user/ask/1.html">我的提问</a><? } ?></li>
            <li<? if(strstr($regular,"user/answer")) { ?> class="on"<? } ?>><? if(strstr($regular,"user/answer")) { ?>我的回答<? } else { ?><a href="<?=SITE_URL?>?user/answer/1.html">我的回答</a><? } ?></li>
<!--            <li<? if(strstr($regular,"user/attention")) { ?> class="on"<? } ?>><? if(strstr($regular,"user/attention")) { ?>最新动态<? } else { ?><a href="<?=SITE_URL?>?user/attention.html">最新动态</a><? } ?></li>-->
            <li<? if(strstr($regular,"message")) { ?> class="on"<? } ?>><? if(strstr($regular,"message")) { ?>我的消息<? } else { ?><a href="<?=SITE_URL?>?message/personal.html">我的消息</a><? } ?></li>
            <li<? if(strstr("user/follower,user/attention",$regular)) { ?> class="on"<? } ?>><? if(strstr("user/follower,user/attention",$regular)) { ?>我的关注<? } else { ?><a href="<?=SITE_URL?>?user/follower.html">我的关注</a><? } ?></li>
            <li<? if(strstr($regular,"favorite")) { ?> class="on"<? } ?>><? if(strstr($regular,"favorite")) { ?>我的收藏<? } else { ?><a href="<?=SITE_URL?>?favorite/default.html">我的收藏</a><? } ?></li>
            <li<? if(strstr($regular,"user/level")) { ?> class="on"<? } ?>><? if(strstr($regular,"user/level")) { ?>我的等级<? } else { ?><a href="<?=SITE_URL?>?user/level.html">我的等级</a><? } ?></li>
        </ul>
    </div>
</div>
