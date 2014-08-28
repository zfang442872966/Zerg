<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); $adlist = $this->fromcache("adlist"); ?><div class="wrapper clearfix">
    <div class="content-left">
        <!--广告位1-->
        <? if((isset($adlist['common']['left1']) && trim($adlist['common']['left1']))) { ?>        <div style="margin-top:10px;"><?=$adlist['common']['left1']?></div>
        <? } ?>        <div class="topics">
            
<? if(is_array($topiclist)) { foreach($topiclist as $topic) { ?>
            <div class="topic_content">
                <div class="topic_l"><img width="228" height="170" src="<?=SITE_URL?><?=$topic['image']?>"></div>
                <div class="topic_r">
                    <h3><?=$topic['title']?></h3>
                    <p><?=$topic['describtion']?></p>
                    <ul class="list">
                        
<? if(is_array($topic['questionlist'])) { foreach($topic['questionlist'] as $question) { ?>
                        <li><a title="<?=$question['title']?>" target="_blank" href="<?=SITE_URL?>?q-<?=$question['id']?>.html"><?=$question['title']?></a> </li>
                        
<? } } ?>
                    </ul>
                </div>
                <div class='clr'></div>
            </div>
            
<? } } ?>
        </div>	
        <div class="pages"><?=$departstr?></div>	
    </div>
    <div class="aside-right">
        <div class="modbox mt10">
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
        <!--广告位2-->
        <? if((isset($adlist['common']['right1']) && trim($adlist['common']['right1']))) { ?>        <div style="margin-top:5px;"><?=$adlist['common']['right1']?></div>
        <? } ?>    </div>
</div>
<? include template('footer'); ?>
