<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); $adlist = $this->fromcache("adlist"); ?><div class="wrapper clearfix">
    <div class="content-left">
        <!--广告位1-->
        <? if((isset($adlist['common']['left1']) && trim($adlist['common']['left1']))) { ?>        <div style="margin-top:10px;"><?=$adlist['common']['left1']?></div>
        <? } ?>        <div class="experts mt10">
            
<? if(is_array($expertlist)) { foreach($expertlist as $expert) { ?>
            <div class="expert">
                <a class="pic" target="_blank" title="<?=$expert['username']?>" href="<?=SITE_URL?>?u-<?=$expert['uid']?>.html"><img width="80" height="80" src="<?=$expert['avatar']?>" alt="<?=$expert['username']?>"></a>
                <a class="name" target="_blank" title="<?=$expert['username']?>" href="<?=SITE_URL?>?u-<?=$expert['uid']?>.html"><?=$expert['username']?></a><span>&nbsp;<? if($expert['introduction']) { ?>(<?=$expert['introduction']?>)<? } ?></span><input type="button" onclick="javascript:document.location = '<?=SITE_URL?>?question/add/<?=$expert['uid']?>.html'" class="button_4 flright" value="向TA求助"/>
                <div class="general">
                    <span>擅长领域：
                        
<? if(is_array($expert['category'])) { foreach($expert['category'] as $category) { ?>
                        <a target="_blank" href="<?=SITE_URL?>?c-<?=$category['cid']?>.html"><?=$category['categoryname']?></a>
                        
<? } } ?>
                    </span>
                    <span><em><a href="<?=SITE_URL?>?user/space_answer/<?=$expert['uid']?>.html"><?=$expert['answers']?></a></em>回答</span>
                    <span><em><?=$expert['supports']?></em>赞同</span>
                    <p>简介：<?=$expert['signature']?></p>
                    <? if($expert['bestanswer']) { ?>                    <h4>精选解答:</h4>
                    <ul class="clearfix">	    		
                        
<? if(is_array($expert['bestanswer'])) { foreach($expert['bestanswer'] as $question) { ?>
                        <li><a href="<?=SITE_URL?>?q-<?=$question['qid']?>.html" target='_blank' title="<?=$question['title']?>"><? echo cutstr($question['title'],40,''); ?></a></li>
                        
<? } } ?>
                    </ul>
                    <? } ?>                </div>
            </div>
            
<? } } ?>
        </div>	
        <div class="pages"><?=$departstr?></div>	
    </div>

    <div class="aside-right">
        <div class="modbox mt10">
            <div class="title">专家最新解答</div>
            <ul class="list">
                
<? if(is_array($questionlist)) { foreach($questionlist as $question) { ?>
                <li><a title="<?=$question['title']?>" target="_blank" href="<?=SITE_URL?>?q-<?=$question['qid']?>.html"><? echo cutstr($question['title'],30,''); ?></a></li>
                
<? } } ?>
            </ul>
        </div>
        <!--广告位2-->
        <? if((isset($adlist['common']['right1']) && trim($adlist['common']['right1']))) { ?>        <div style="margin-top:5px;"><?=$adlist['common']['right1']?></div>
        <? } ?>    </div>
</div>
<? include template('footer'); ?>
