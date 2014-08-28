<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); $adlist = $this->fromcache("adlist"); ?><div class="nav-line"><a class="first" href="<?=SITE_URL?>"><?=$setting['site_name']?></a> &gt; <span>活跃用户</span></div>
<div class="wrapper clearfix">
    <div class="content-left">
        <!--广告位1-->
        <? if((isset($adlist['common']['left1']) && trim($adlist['common']['left1']))) { ?>        <div><?=$adlist['common']['left1']?></div>
        <? } ?>        <div class="modbox">
            <ul class="active-list clearfix">
                
<? if(is_array($userlist)) { foreach($userlist as $activeuser) { ?>
                <li><a title="<?=$activeuser['username']?>" target="_blank" href="<?=SITE_URL?>?u-<?=$activeuser['uid']?>.html"><img width="80" height="80" src="<?=$activeuser['avatar']?>" onmouseout="pop_user_out();" onmouseover="pop_user_on(this, '<?=$activeuser['uid']?>', 'image_active');"></a><a class="name" title="<?=$activeuser['username']?>" target="_blank" href="<?=SITE_URL?>?u-<?=$activeuser['uid']?>.html"><?=$activeuser['username']?></a></li>
                
<? } } ?>
            </ul>
        </div>
        <div class="pages"><?=$departstr?></div>   
    </div>
    <div class="aside-right">
        <div class="modbox">
            <div class="title">回答榜</div>
            <ul style="display: block;" id="alltop" class="right-list">
                
<? if(is_array($answertop)) { foreach($answertop as $topuser) { ?>
                <? $index++; ?>                <li>
                    <? if($index<4) { ?>                    <em class="n1"><?=$index?></em>
                    <? } else { ?>                    <em class="n2"><?=$index?></em>
                    <? } ?>                    <a onmouseout="pop_user_out();" onmouseover="pop_user_on(this, '<?=$topuser['uid']?>', 'text');" target="_blank" href="<?=SITE_URL?>?u-<?=$topuser['uid']?>.html"><?=$topuser['username']?></a>
                    <span class="credit"><?=$topuser['answers']?></span>
                </li>
                
<? } } ?>
            </ul>
        </div>
        <!--广告位2-->
        <? if((isset($adlist['common']['right1']) && trim($adlist['common']['right1']))) { ?>        <div style="margin-top:5px;"><?=$adlist['common']['right1']?></div>
        <? } ?>        <div class="modbox mt10">
            <div class="title">一周热点问题</div>
            <ul class="right-list">
                <? $attentionlist=$this->fromcache('attentionlist'); ?>                
<? if(is_array($attentionlist)) { foreach($attentionlist as $index => $question) { ?>
                <? $index++; ?>                <li>
                    <? if($index<4) { ?>                    <em class="n1"><?=$index?></em>
                    <? } else { ?>                    <em class="n2"><?=$index?></em>
                    <? } ?>                    <a  title="<?=$question['title']?>" target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html"><? echo cutstr($question['title'],40,''); ?></a>
                </li>
                
<? } } ?>
            </ul>
        </div>
    </div>
</div>
<? include template('footer'); ?>
