<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); $adlist = $this->fromcache("adlist"); $statistics=$this->fromcache('statistics'); $onlineusernum=$this->fromcache('onlineusernum'); ?><div class="nav-line">
    <div class="count_info">
        <span class="icon_count"/></span><span>已解决问题：<?=$statistics['solves']?></span><span>待解决问题：<?=$statistics['nosolves']?></span><span>当前在线：<? if($this->user['grouptype']==1) { ?><a href="<?=SITE_URL?>?index/online.html"><?=$onlineusernum?></a><? } else { ?><?=$onlineusernum?><? } ?>人</span>
    </div>
<div id="scoll-note">
    <ul>
        <? $notelist=$this->fromcache('notelist'); ?>        
<? if(is_array($notelist)) { foreach($notelist as $nindex => $note) { ?>
        <? $nindex++ ?>        <? if($nindex<=3) { ?>        <li><a target="_blank" title="<?=$note['title']?>" <? if($note['url']) { ?>href="<?=$note['url']?>"<? } else { ?>href="<?=SITE_URL?>?note/view/<?=$note['id']?>.html"<? } ?>><? echo cutstr($note['title'],40,''); ?></a><span>( <? echo cutstr($note['format_time'],10,'') ?> )</span></li>
        <? } ?>        
<? } } ?>
    </ul>
</div>
</div>
<div class="wrapper clearfix">
    <div class="aside">
        <div class="modbox mt10">
            <h3 class="title">问题分类</h3>
            <div class="category-nav">
                <? $categorylist=$this->fromcache('categorylist'); ?>                
<? if(is_array($categorylist)) { foreach($categorylist as $category1) { ?>
                <div <? if(!$category1['sublist']) { ?>class="categorybox"<? } else { ?>class="categorybox curr_menu"<? } ?>>
                    <div class="cate-item">
                        <h3><a target="_blank" title="<?=$category1['name']?>" href="<?=SITE_URL?>?c-<?=$category1['id']?>.html"><?=$category1['name']?></a></h3>
                        <p>
                            
<? if(is_array($category1['sublist'])) { foreach($category1['sublist'] as $index => $category2) { ?>
                            <? if($index<3) { ?>                            <a href="<?=SITE_URL?>?c-<?=$category2['id']?>.html"><?=$category2['name']?></a>
                            <? } ?>                            
<? } } ?>
                        </p>
                    </div>
                    <ul class="subcate clearfix">
                        
<? if(is_array($category1['sublist'])) { foreach($category1['sublist'] as $index => $category2) { ?>
                        <li><a href="<?=SITE_URL?>?c-<?=$category2['id']?>.html"><?=$category2['name']?></a></li>
                        
<? } } ?>
                    </ul>
                </div>
                
<? } } ?>
 
            </div>
        </div>
        <div class="modbox mt10">
            <h3 class="title">财富榜</h3>
            <div class="usertabbox">
                <a target="_blank" class="select" href="<?=SITE_URL?>?us-1.html" id="weektab">本周排行</a>
                <a target="_blank" class="not-selected" href="<?=SITE_URL?>?us-0.html" id="alltab">总排行</a>
            </div>
            <ul class="right-list" id="weektop">
                <? $weekuserlist=$this->fromcache('weekuserlist'); ?>                
<? if(is_array($weekuserlist)) { foreach($weekuserlist as $index => $weekuser) { ?>
                <? $index++; ?>                <li>
                    <? if($index<4) { ?>                    <em class="n1"><?=$index?></em>
                    <? } else { ?>                    <em class="n2"><?=$index?></em>
                    <? } ?>                    <a href="<?=SITE_URL?>?u-<?=$weekuser['uid']?>.html" target="_blank" onmouseover="pop_user_on(this, '<?=$weekuser['uid']?>', 'text');"  onmouseout="pop_user_out();"><?=$weekuser['username']?></a>
                    <span class="credit"><?=$weekuser['credit2']?></span>
                </li>
                
<? } } ?>
            </ul>
            <ul class="right-list" id="alltop" style="display: none;">
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
    </div>
    <div class="content">
        <div class="main">
            <? $topiclist=$this->fromcache('topiclist'); ?>            <? if($topiclist) { ?>            <div class="focusbox mt10">
                <div class="slidermod" >
                    <div class="slider-box clearfix">

                        
<? if(is_array($topiclist)) { foreach($topiclist as $index => $topic) { ?>
                        <div class="topic" id="<?=$topic['id']?>" <? if($index!=0) { ?>style="display:none"<? } ?>>
                             <img width="228" height="170" src="<?=SITE_URL?><?=$topic['image']?>"/>
                            <h4><? echo cutstr($topic['title'],14,''); ?></h4>
                            <p><? echo cutstr($topic['describtion'],60,''); ?></p>
                            <ul class="list">
                                
<? if(is_array($topic['questionlist'])) { foreach($topic['questionlist'] as $question) { ?>
                                <li><a  title="<?=$question['title']?>" target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html"><? echo cutstr($question['title'],26,''); ?></a> </li>
                                
<? } } ?>
 
                            </ul>
                        </div>
                        
<? } } ?>
                        <div class="clr"></div>
                        <ul class="pagination">
                            
<? if(is_array($topiclist)) { foreach($topiclist as $index => $topic) { ?>
                            <li <? if($index==0) { ?>class="spanhover"<? } ?> topicid="<?=$topic['id']?>"><span><? echo cutstr($topic['title'],14,''); ?></span></li>
                            
<? } } ?>
                        </ul>
                        <div class="more"><a href="<?=SITE_URL?>?topic/default.html" target="_blank">更多</a></div>
                    </div>
                </div>
            </div>
            <? } ?>            <!--广告位1-->
            <? if((isset($adlist['index']['middle1']) && trim($adlist['index']['middle1']))) { ?>            <div class="question-mod mt10"><?=$adlist['index']['middle1']?></div>
            <? } ?>            <!-- 待解决 -->
            <div class="question-mod mt10">
                <h3 class="title">待解决问题</h3>
                <p class="more"><a href="<?=SITE_URL?>?c-all/1.html" target="_blank">更多>></a></p>
                <? $nosolvelist=$this->fromcache('nosolvelist'); ?>                
<? if(is_array($nosolvelist)) { foreach($nosolvelist as $index => $question) { ?>
                <ul class="list">	    		
                    <li>
                        <span class="answer-number"><?=$question['answers']?>回答</span>
                        <a target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html" title="<?=$question['title']?>"><? echo cutstr($question['title'],50); ?></a>
                        <a target="_blank" class="type" href="<?=SITE_URL?>?c-<?=$question['cid']?>.html" title="<?=$question['category_name']?>">[<? echo cutstr($question['category_name'],10,''); ?>]</a>
                    </li>
                </ul>
                
<? } } ?>
            </div>
            <!--广告位2-->
            <? if((isset($adlist['index']['middle2']) && trim($adlist['index']['middle2']))) { ?>            <div class="question-mod mt10"><?=$adlist['index']['middle2']?></div>
            <? } ?>            <!-- 悬赏问题 -->
            <div class="question-mod mt10">
                <h3 class="title">高分悬赏问题</h3>
                <p class="more"><a href="<?=SITE_URL?>?c-all/4.html" target="_blank">更多>></a></p>
                <? $nosolvelist=$this->fromcache('rewardlist'); ?>                
<? if(is_array($nosolvelist)) { foreach($nosolvelist as $index => $question) { ?>
                <ul class="list">	    		
                    <li>
                        <span class="answer-number"><?=$question['answers']?>回答</span>
                        <span class="gold">                
                            <? if($question['price'] > 0) { ?>                            <img src="<?=SITE_URL?>css/default/gold.gif" /><?=$question['price']?>
                            <? } ?>                        </span>
                        <a target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html" title="<?=$question['title']?>"><? echo cutstr($question['title'],50); ?></a>
                        <a target="_blank" class="type" href="<?=SITE_URL?>?c-<?=$question['cid']?>.html" title="<?=$question['category_name']?>">[<? echo cutstr($question['category_name'],10,''); ?>]</a>
                    </li>
                </ul>
                
<? } } ?>
            </div>			
            <!--广告位3-->
            <? if((isset($adlist['index']['middle3']) && trim($adlist['index']['middle3']))) { ?>            <div class="question-mod mt10"><?=$adlist['index']['middle3']?></div>
            <? } ?>            <!-- 最新已解决-->
            <div class="question-mod mt10">
                <h3 class="title">最新已解决</h3>
                <p class="more"><a href="<?=SITE_URL?>?c-all/2.html" target="_blank">更多>></a></p>
                <? $solvelist=$this->fromcache('solvelist'); ?>                
<? if(is_array($solvelist)) { foreach($solvelist as $index => $question) { ?>
                <ul class="list">	    		
                    <li>
                        <span class="answer-number"><?=$question['answers']?>回答</span>
                        <a target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html" title="<?=$question['title']?>"><? echo cutstr($question['title'],50); ?></a>
                        <a target="_blank" class="type" href="<?=SITE_URL?>?c-<?=$question['cid']?>.html" title="<?=$question['category_name']?>">[<? echo cutstr($question['category_name'],10,''); ?>]</a>
                    </li>
                </ul>
                
<? } } ?>
            </div>
        </div>
        <div class="side">
            <? if($user['uid']) { ?>            <div class="modbox mt10">
                <div class="userinfo-box">
                    <div class="userinfo clearfix">
                        <a target="_blank" href="<?=SITE_URL?>?user/default.html" class="pic"><img width="50" height="50" src="<?=$user['avatar']?>"></a>
                        <h3><a target="_blank" href="<?=SITE_URL?>?user/default.html"><?=$user['username']?></a></h3>
                        <p><?=$user['grouptitle']?></p>
                        <p>财富:<?=$user['credit2']?><span>经验:<?=$user['credit1']?></span></p>
                    </div>
                </div>
            </div>
            <? } ?>            <div class="modbox mt10">
                <div class="title">推荐专家</div>
                <ul class="left-expert-list">
                    <? $expertlist=$this->fromcache('expertlist'); ?>                    
<? if(is_array($expertlist)) { foreach($expertlist as $expert) { ?>
                    <li>
                        <div class="pic"><a title="<?=$expert['name']?>" target="_blank" href="<?=SITE_URL?>?u-<?=$expert['uid']?>.html"><img width="50" height="50" alt="<?=$expert['username']?>" src="<?=$expert['avatar']?>"  onmouseover="pop_user_on(this, '<?=$expert['uid']?>', '');"  onmouseout="pop_user_out();"/></a></div>
                        <h3><a title="<?=$expert['name']?>" target="_blank" href="<?=SITE_URL?>?u-<?=$expert['uid']?>.html" onmouseover="pop_user_on(this, '<?=$expert['uid']?>', 'text');"  onmouseout="pop_user_out();"><?=$expert['username']?></a></h3>
                        <span><?=$expert['answers']?>回答</span>
                        <span><?=$expert['supports']?>赞同</span>
                        <input type="button" value="向TA求助" class="button_4" onclick="javascript:document.location = '<?=SITE_URL?>?question/add/<?=$expert['uid']?>.html'">
                    </li>
                    
<? } } ?>
                </ul>
            </div>

            <!--广告位4-->
            <? if((isset($adlist['index']['right1']) && trim($adlist['index']['right1']))) { ?>            <div class='mt10'><?=$adlist['index']['right1']?></div>
            <? } ?>            <div class="modbox mt10">
                <h3 class="title">热门标签</h3>
                <div class="hot-tags">
                    <? $taglist=$this->fromcache('hosttaglist'); ?>                    
<? if(is_array($taglist)) { foreach($taglist as $tag) { ?>
                    <a target="_blank" title="<?=$tag['name']?>" href="<?=SITE_URL?>?question/search/tag:<?=$tag['name']?>.html"><?=$tag['name']?></a>
                    
<? } } ?>
                </div>
            </div>
            <!-- 关注问题排行榜 -->
            <div class="modbox mt10">
                <div class="title">一周热点问题</div>
                <ul class="right-list">
                    <? $attentionlist=$this->fromcache('attentionlist'); ?>                    
<? if(is_array($attentionlist)) { foreach($attentionlist as $index => $question) { ?>
                    <? $index++; ?>                    <li>
                        <? if($index<4) { ?>                        <em class="n1"><?=$index?></em>
                        <? } else { ?>                        <em class="n2"><?=$index?></em>
                        <? } ?>                        <a  title="<?=$question['title']?>" target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html"><? echo cutstr($question['title'],40,''); ?></a>
                    </li>
                    
<? } } ?>
                </ul>
            </div>
        </div>
    </div>
</div><? if($linklist) { ?><div class="wrapper mt30 clearfix">
    <!-- 友情链接 -->
    <div class="links">
        <h3 class="title">友情链接</h3>
        <div class="friendlink">
            
<? if(is_array($linklist)) { foreach($linklist as $link) { ?>
            <a target="_blank" href="<?=$link['url']?>" title="<?=$link['description']?>">
                <? if($link['logo']) { ?>                <img src="<?=$link['logo']?>" alt="<?=$link['name']?>" />
                <? } else { ?>                <?=$link['name']?>
                <? } ?>            </a>
            
<? } } ?>
        </div>
    </div>
</div><? } ?><script type="text/javascript">
    var timer;
    $(document).ready(function() {
        $(".curr_menu").hover(function() {
            $(this).addClass("categorybox-hover");
        }, function() {
            $(this).removeClass("categorybox-hover");
        });
        //note scroll
        setInterval(function() {
            $("#scoll-note ul:first").animate({
                marginTop: "-29px"
            }, 400, function() {
                $(this).css({marginTop: "0px"}).find("li:first").appendTo(this);
            });
            $(".pagination ul:first").animate();
        }, 3000);
        //slider
        $(".pagination li").hover(function() {
            $(".pagination li[class='spanhover']").removeClass("spanhover");
            var topicid = $(this).attr("topicid");
            $(this).addClass("spanhover");
            timer = setTimeout(function() {
                $(".topic").hide();
                $("#" + topicid).show();
            }, 100);
        });
        $("#weektab").hover(function() {
            $(this).attr("class", "select");
            $("#alltab").attr("class", "not-selected");
            $("#weektop").show();
            $("#alltop").hide();
        });
        $("#alltab").hover(function() {
            $(this).attr("class", "select");
            $("#weektab").attr("class", "not-selected");
            $("#alltop").show();
            $("#weektop").hide();
        });
    });
</script>
<? include template('footer'); ?>
