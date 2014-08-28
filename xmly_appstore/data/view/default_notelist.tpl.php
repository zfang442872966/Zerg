<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); $adlist = $this->fromcache("adlist"); ?><div class="nav-line">
    <a class="first" href="<?=SITE_URL?>"><?=$setting['site_name']?></a>
    &gt; <span>公告列表</span>
</div>
<div class="wrapper clearfix">
    <div class="content-left">
        <!--广告位1-->
        <? if((isset($adlist['common']['left1']) && trim($adlist['common']['left1']))) { ?>        <div><?=$adlist['common']['left1']?></div>
        <? } ?>        <div id="mod-answer-list" class="mod-answer-list">
            <div class="bd">
                <div class="cls-qa-table">
                    <table>
                        <tbody>
                            <tr class=""><th class="s0">标题</th><th class="s1">评论/浏览</th><th class="s2">时间</th></tr>
                            
<? if(is_array($notelist)) { foreach($notelist as $index => $note) { ?>
                            <tr>
                                <td class="title">
                                    <div class="tit-full">
                                        <div class="wrap">
                                            <a <? if($note['url']) { ?> href="<?=$note['url']?>"  <? } else { ?>  href="<?=SITE_URL?>?note/view/<?=$note['id']?>.html" <? } ?> target="_blank" title="<?=$note['title']?>"><? echo cutstr($note['title'],50); ?></a>&nbsp;
                                        </div>
                                    </div>
                                </td>
                                <td><?=$note['comments']?>/<?=$note['views']?></td>
                                <td><?=$note['format_time']?></td>
                            </tr>
                            
<? } } ?>
                        </tbody>
                    </table>
                </div>
                <div class="pages"><?=$departstr?></div>
            </div>
        </div>
    </div>

    <div class="aside-right">
        <!-- 关注问题排行榜 -->
        <div class="modbox">
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
