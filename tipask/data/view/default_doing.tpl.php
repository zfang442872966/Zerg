<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); $adlist = $this->fromcache("adlist"); ?><div class="wrapper clearfix">
    <div class="content-left">
        <div class="my-answerbox mt10">
            <div class="title">
                <? if($type=='all') { ?>                全站最新动态
                <? } elseif($type=='atentto') { ?>                我关注的动态
                <? } elseif($type=='my') { ?>                我的动态
                <? } ?>            </div>
            <div id="qa-tabcard" style="width:690px;">
                <ul>
                    <? if($type=='atentto') { ?>                    <li class="on">关注的动态</li>
                    <li><a href="<?=SITE_URL?>?doing/default/my.html">我的动态</a></li>
                    <li><a href="<?=SITE_URL?>?doing/default/all.html">全站动态</a></li>
                    <? } elseif($type=='my') { ?>                    <li><a href="<?=SITE_URL?>?doing/default.html">关注的动态</a></li>
                    <li class="on">我的动态</li>
                    <li><a href="<?=SITE_URL?>?doing/default/all.html">全站动态</a></li>
                    <? } elseif($type=='all') { ?>                    <? if($this->user['uid']) { ?>                    <li><a href="<?=SITE_URL?>?doing/default.html">关注的动态</a></li>
                    <li><a href="<?=SITE_URL?>?doing/default/my.html">我的动态</a></li>
                    <? } ?>                    <li class='on'>全站动态</li>
                    <? } ?>                </ul>
            </div>
            <!--广告位1-->
            <? if((isset($adlist['doing']['left1']) && trim($adlist['doing']['left1']))) { ?>            <div style="margin-top:5px;"><?=$adlist['doing']['left1']?></div>
            <? } ?>            <ul class="q-tabmod mt10">
                <? if($recommandusers && $page==1) { ?>                <li><p class='suggest-header'>您可能感兴趣的人</p></li>
                
<? if(is_array($recommandusers)) { foreach($recommandusers as $recommanduser) { ?>
                <li class="suggest-item">
                    <div class="suggest-item-inner">
                        <span class="zg-right"><input type="button" onclick="attentto_user(<?=$recommanduser['uid']?>)" id="attenttouser_<?=$recommanduser['uid']?>" value="关注" class="button_attention"></span>
                        <a class="image-link" href="<?=SITE_URL?>?u-<?=$recommanduser['uid']?>.html" target="_blank"><img alt="<?=$recommanduser['username']?>" src="<?=$recommanduser['avatar']?>" /></a>
                        <div class="item-main">
                            <a class="item-link" href="<?=SITE_URL?>?u-<?=$recommanduser['uid']?>.html"><?=$recommanduser['username']?></a>
                            <p class="item-info-minor">
                                <? if($recommanduser['category']) { ?>擅长: 
                                
<? if(is_array($recommanduser['category'])) { foreach($recommanduser['category'] as $category) { ?>
                                <i class="expert-field"><a target="_blank" href="<?=SITE_URL?>?c-<?=$category['cid']?>.html"><?=$category['categoryname']?></a></i>
                                
<? } } ?>
                                <? } ?>                            </p>
                            <p class="item-info-major"><?=$recommanduser['followers']?>人关注 , <?=$recommanduser['answers']?>回答</p>
                        </div>
                    </div>
                </li>
                
<? } } ?>
                <? } ?>                
<? if(is_array($doinglist)) { foreach($doinglist as $doing) { ?>
                <li>
                    <div class="avatar">
                        <? if($doing['hidden'] && in_array($doing['action'],array(1,8))) { ?>                        <img src="<?=SITE_URL?>css/default/avatar.gif" alt="匿名" />
                        <? } else { ?>                        <a class="pic" target="_blank" title="<?=$doing['author']?>" href="<?=SITE_URL?>?u-<?=$doing['authorid']?>.html"><img src="<?=$doing['avatar']?>" alt="<?=$doing['author']?>" onmouseover="pop_user_on(this, '<?=$doing['authorid']?>', 'img');"  onmouseout="pop_user_out();" /></a>
                        <? } ?>                    </div>
                    <div class="msgcontent">
                        <div class="source"> 
                            <? if($doing['hidden'] && in_array($doing['action'],array(1,8))) { ?>                            匿名
                            <? } else { ?>                            <a title="<?=$doing['author']?>"  target="_blank" href="<?=SITE_URL?>?u-<?=$doing['authorid']?>.html"><?=$doing['author']?></a> 
                            <? } ?>                            <?=$doing['actiondesc']?><span class="time"><?=$doing['doing_time']?></span>
                        </div>
                        <div class="title"><a href="<?=SITE_URL?>?q-<?=$doing['questionid']?>.html" target="_blank"><?=$doing['title']?>?</a></div>
                        <div class="detail"><p><? echo cutstr($doing['content'],500) ?></p></div>
                        <div class="related">
                            <div class="pv"><?=$doing['attentions']?> 人关注 <span class="dot">•</span> <?=$doing['answers']?> 个回答 <span class="dot">•</span> <?=$doing['views']?> 次浏览 <span class="dot">•</span> 发表于 <?=$doing['question_time']?> </div>
                        </div>
                        <? if($doing['referid']) { ?>                        <div class="quote">
                            <div class="avatar">
                                <a href="<?=SITE_URL?>?u-<?=$doing['refer_authorid']?>.html"  target="_blank" class="pic"><img alt="自由的风" src="<?=$doing['refer_avatar']?>" onmouseover="pop_user_on(this, '<?=$doing['refer_authorid']?>', 'img');"  onmouseout="pop_user_out();" /></a>
                            </div>
                            <div class="detail"><p><? echo cutstr($doing['refer_content'],200) ?></p></div>
                        </div>
                        <? } ?>                    </div>
                    <div class="clr"></div>
                </li>
                
<? } } ?>
                <? if(!$doinglist && $type=='my') { ?>                <li><p>帮助他人，快乐自己！拿出你的热心，帮忙大家解决问题吧。 <a href='<?=SITE_URL?>?c-all/1.html'>这些问题需要您的帮助</a></p></li>
                <? } ?>            </ul>
        </div>	
        <div class="pages"><?=$departstr?></div>	
    </div>

    <div class="aside-right">
        <div class="modbox mt10">
            <div class="title">热门用户</div>
            <ul class="left-expert-list">
                <? $activeuserlist=$this->fromcache('activeuser'); ?>                
<? if(is_array($activeuserlist)) { foreach($activeuserlist as $index => $activeuser) { ?>
                <? $index++; ?>                <li>
                    <div class="pic"><a title="<?=$activeuser['username']?>" target="_blank" href="<?=SITE_URL?>?u-<?=$activeuser['uid']?>.html"><img width="50" height="50" alt="<?=$activeuser['username']?>" src="<?=$activeuser['avatar']?>"  onmouseover="pop_user_on(this, '<?=$activeuser['uid']?>', '');"  onmouseout="pop_user_out();"/></a></div>
                    <h3><a title="<?=$activeuser['username']?>" target="_blank" href="<?=SITE_URL?>?u-<?=$activeuser['uid']?>.html" onmouseover="pop_user_on(this, '<?=$activeuser['uid']?>', 'text');"  onmouseout="pop_user_out();"><?=$activeuser['username']?></a></h3>
                    <span><?=$activeuser['answers']?>回答</span>
                    <span><?=$activeuser['supports']?>赞同</span>
                    <p><a href="<?=SITE_URL?>?question/add/<?=$activeuser['uid']?>.html" class="invite">向TA求助</a></p>
                </li>
                
<? } } ?>
            </ul>
        </div>
        <!--广告位2-->
        <? if((isset($adlist['doing']['right1']) && trim($adlist['doing']['right1']))) { ?>        <div style="margin-top: 5px;"><?=$adlist['doing']['right1']?></div>
        <? } ?>        <div class="modbox mt10">
            <h3 class="title">热门标签</h3>
            <div class="hot-tags">
                <? $taglist=$this->fromcache('hosttaglist'); ?>                
<? if(is_array($taglist)) { foreach($taglist as $tag) { ?>
                <a target="_blank" title="<?=$tag['name']?>" href="<?=SITE_URL?>?question/search/tag:<?=$tag['name']?>.html"><?=$tag['name']?></a>
                
<? } } ?>
            </div>
        </div>
         <!--广告位3-->
        <? if((isset($adlist['doing']['right2']) && trim($adlist['doing']['right2']))) { ?>        <div style="margin-top: 5px;"><?=$adlist['doing']['right1']?></div>
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
