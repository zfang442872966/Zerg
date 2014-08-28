<? if(!defined('IN_TIPASK')) exit('Access Denied'); $setting=$this->setting;$user=$this->user; ?><!--相关已解决--><? if(isset($userinfo)) { ?><div class="usercard_in clearfix">
    <div class="usercard_c1 clearfix">
        <div class="usercard_c1_l"><a href="<?=SITE_URL?>?u-<?=$userinfo['uid']?>.html" class="avatar"><img src="<?=$userinfo['avatar']?>" alt="<?=$userinfo['username']?>"/></a></div>
        <div class="usercard_c1_r">
            <h2 class="tit"><a class="tx_user" href="<?=SITE_URL?>?u-<?=$userinfo['uid']?>.html" title="<?=$userinfo['username']?>"><? echo cutstr($userinfo['username'],24,''); ?></a><? if($userinfo['gender']!=2) { ?><i class="icon_<?=$userinfo['gender']?>"></i><? } if($userinfo['expert']) { ?><i class="icon_expert" title='专家'></i><? } ?> <span class="tx_id">(<?=$userinfo_group['grouptitle']?>)</span><? if($userinfo['islogin']) { ?><span class="online">在线</span><? } else { ?><span class="offline">离线</span><? } ?></h2>
            <? if($userinfo['introduction']) { ?>            <p class="info"><?=$userinfo['introduction']?></p>
            <? } ?>            <? if($userinfo['category']) { ?>            <p class="info">擅长:
                
<? if(is_array($userinfo['category'])) { foreach($userinfo['category'] as $category) { ?>
                <i class="expert-field"><a target="_blank" href="<?=SITE_URL?>?c-<?=$category['cid']?>.html"><?=$category['categoryname']?></a></i>
                
<? } } ?>
            </p>
            <? } ?>            <p class="stats"><?=$userinfo['answers']?>回答<span class="tx_line">|</span><?=$userinfo['supports']?>赞同<span class="tx_line">|</span><?=$userinfo['credit2']?>财富<span class="tx_line">|</span><?=$userinfo['followers']?>关注</p>
        </div>
    </div>
    <div class="usercard_c2 clearfix">
        <div class="usercard_c2_l">
            <span class="gb_foed gb_fo1">
                <? if($user['uid']!=$userinfo['uid']) { ?>                <? if($is_followed) { ?>                <span><input type="button" class="button_followed" value="取消关注" id="attenttouser_<?=$userinfo['uid']?>" onclick="attentto_user(<?=$userinfo['uid']?>)"/></span>
                <? } else { ?>                <span><input type="button" class="button_attention" value="关注" id="attenttouser_<?=$userinfo['uid']?>" onclick="attentto_user(<?=$userinfo['uid']?>)"/></span>
                <? } ?>                <span><input type="button" class="button_attention" value="提问"  onclick="javascript:document.location = '<?=SITE_URL?>?question/add/<?=$userinfo['uid']?>.html'"/></span>
                <span><input type="button" class="button_attention" value="私信"  onclick="javascript:document.location = '<?=SITE_URL?>?message/send/<?=$userinfo['uid']?>.html'"/></span>
                <? } ?>            </span>
        </div>
    </div>
    <? if($userinfo['signature']) { ?>    <div class="usercard_c3 clearfix"><p class="info"><?=$userinfo['signature']?></p></div> 
    <? } ?></div><? } ?>