<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template(header,admin); ?>
<div id="append">
</div>
<div style="width:100%; height:15px;color:#000;margin:0px 0px 10px;">
    <div style="float:left;"><a href="index.php?admin_main/stat<?=$setting['seo_suffix']?>" target="main"><b>控制面板首页</b></a>&nbsp;&raquo;&nbsp;回答管理</div>
</div><? if(isset($message)) { $type=isset($type)?$type:'correctmsg';  ?><table cellspacing="1" cellpadding="4" width="100%" align="center" class="tableborder">
    <tr>
        <td class="<?=$type?>"><?=$message?></td>
    </tr>
</table><? } ?><form action="index.php?admin_question/searchanswer<?=$setting['seo_suffix']?>" method="post">
    <table width="100%" cellspacing="1" cellpadding="4" align="center" class="tableborder">
        <tbody>
            <tr class="header" ><td colspan="4">回答搜索</td></tr>
            <tr class="altbg1"><td colspan="4">可以通过如下搜索条件，检索问题回答</td></tr>
            <tr>
                <td width="200"  class="altbg2">标题:<input type="text" name="srchtitle" value="<?=$srchtitle?>"/></td>
                <td  width="200" class="altbg2">回答者:<input type="text" name="srchauthor" value="<?=$srchauthor?>"/></td>
                <td  width="250" class="altbg2">关键字:	<input type="text" name="keywords" size="30" maxlength="50" value="<?=$keywords?>"/></td>
                <td rowspan="2" class="altbg2"><input class="btn" type="submit" value="提 交"></td>
            </tr>
            <tr>
                <td colspan="3" rowspan="2" class="altbg2">回答时间<input type="text" id="timestart" name="srchdatestart" value="<?=$srchdatestart?>" />到&nbsp;<input class="txt" id="timeend" name="srchdateend" value="<?=$srchdateend?>"/></td>
            </tr>
        </tbody>
    </table>
</form>
[共 <font color="green"><?=$rownum?></font> 条回答]
<form name="answerlist" method="post">
    <table width="100%" border="0" cellpadding="4" cellspacing="1" class="tableborder">
        <tr class="header">
            <td width="7%"><input class="checkbox" value="chkall" id="chkall" onclick="checkall('aid[]')" type="checkbox" name="chkall"><label for="chkall">全选</label></td>
            <td  width="40%">回答内容</td>
            <td  width="10%">回答人</td>
            <td  width="13%">回答时间</td>
            <td  width="14%">IP</td>
            <td  width="10%">支持/反对</td>
            <td  width="6%">已采纳</td>
        </tr>
        <? if(isset($answerlist)) { ?> 
<? if(is_array($answerlist)) { foreach($answerlist as $answer) { ?>
        <tr>
            <? $content=htmlspecialchars($answer['content']); ?>            <td class="altbg2"><input class="checkbox" type="checkbox" value="<?=$answer['id']?>" name="aid[]"></td>
            <td class="altbg2" id="title_<?=$answer['id']?>">
                <a  href="index.php?question/view/<?=$answer['qid']?><?=$setting['seo_suffix']?>" target="_blank"><strong><?=$answer['title']?></strong></a>
                <div style= "WIDTH:550px;OVERFLOW-Y:auto;height:100px"> <?=$answer['content']?></div>
            </td>
            <td class="altbg2"><a href="index.php?user/space/<?=$answer['authorid']?><?=$setting['seo_suffix']?>" target="_blank"><?=$answer['author']?></a></td>
            <td class="altbg2"><?=$answer['time']?></td>
            <td class="altbg2"><?=$answer['ip']?></td>
            <td class="altbg2"><?=$answer['support']?>/<?=$answer['against']?></td>
            <td class="altbg2"><? if($answer['adopttime']) { ?><img title="已被采纳为最佳答案" src="<?=SITE_URL?>css/admin/icn_2.gif"><? } else { ?>否<? } ?></td>
        </tr>
        
<? } } ?>
 <? } ?>        <? if($departstr) { ?>        <tr class="smalltxt">
            <td class="altbg2" colspan="6"><div class="scott"><?=$departstr?></div></td>
        </tr>
        <? } ?>        <tr>
            <td colspan="6" class="altbg1"><input class="btn" type="button" name="delete" value="删除" onclick="deleteanswer();" /></td>
        </tr>
    </table>
</form>
<? include template(footer,admin); ?>
<script type="text/javascript">
    function deleteanswer(){
        if($("input[name='aid[]']:checked").length == 0){
            alert('你没有选择任何回答');
            return false;
        }
        if(confirm('确定删除回答?')==false){
            return false;
        }
        document.answerlist.action="index.php?admin_question/deleteanswer<?=$setting['seo_suffix']?>";
        document.answerlist.submit();
    }
</script>


