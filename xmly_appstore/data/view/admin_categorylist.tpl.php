<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template(header,admin); ?>
<script type="text/javascript">
    $(document).ready(function() {
    $("#list").sortable({
        update: function(){
            var reorderid = "";
            var numValue = $("input[name='order[]']");
            for (var i = 0; i < numValue.length; i++){
            reorderid += $(numValue[i]).val() + ",";
            }
            $.post("index.php?admin_category/reorder<?=$setting['seo_suffix']?>", {order:reorderid});
        }
    });
 });
</script>
<div style="width:100%; height:15px;color:#000;margin:0px 0px 10px;">
    <div style="float:left;"><a href="index.php?admin_main/stat<?=$setting['seo_suffix']?>" target="main"><b>控制面板首页</b></a>&nbsp;&raquo;&nbsp;分类管理</div>
</div><? if(isset($message)) { $type=isset($type)?$type:'correctmsg';  ?><table cellspacing="1" cellpadding="4" width="100%" align="center" class="tableborder">
    <tr>
        <td class="<?=$type?>"><?=$message?></td>
    </tr>
</table><? } ?><table width="100%" cellspacing="1" cellpadding="4" align="center" class="tableborder">
    <tr class="header"><td>分类列表&nbsp;&nbsp;&nbsp;<input type="button" style="cursor:pointer" onclick="document.location.href = 'index.php?admin_category/add/new<?=$setting['seo_suffix']?>'" value="添加新分类" /></td></tr>
    <tr class="altbg1"><td>分类的排序可以通过鼠标拖动来改变，当鼠标在某一分类上面时，按住左键即可上下移动。</td></tr>
    <tr class="altbg2"><td>
            <a href="index.php?admin_category/default<?=$setting['seo_suffix']?>">根目录</a>
            <? if(isset($navlist)) { ?>            
<? if(is_array($navlist)) { foreach($navlist as $nav) { ?>
            &gt;&gt; <a href="index.php?admin_category/view/<?=$nav['id']?><?=$setting['seo_suffix']?>"><?=$nav['name']?></a>  
            
<? } } ?>
            <? } ?>            <? if(isset($category['name'])) { ?>&gt;&gt; <?=$category['name']?><? } ?></td></tr>
</table>
<form name="answerlist" onsubmit="return confirm('删除该分类会同时删除该分类下的子分类及其分类下的所有问题，确定继续?');" action="index.php?admin_category/remove<?=$setting['seo_suffix']?>" method="post">
    <table cellspacing="1" cellpadding="4" width="100%" align="center" class="tableborder">
        <tr class="header" align="center">
            <td width="10%"><input class="checkbox" value="chkall" id="chkall" onclick="checkall('cid[]')" type="checkbox" name="chkall"><label for="chkall">删除</label></td>
            <td width="60%">分类名称</td>
            <td  width="10%">查看子分类</td>
            <td  width="10%">添加子分类</td>
            <td  width="10%">编辑</td>
        </tr>
    </table>
    <input type="hidden" name="hiddencid" value="<?=$pid?>" />
    <ul id="list" style="cursor: pointer;width:100%;float: right;" >
        
<? if(is_array($categorylist)) { foreach($categorylist as $cate) { ?>
        <li style="list-style:none;">
            <table  cellspacing="1" cellpadding="4" width="100%" align="center" style="border: 0 none !important; border-collapse: separate !important;empty-cells: show;margin-bottom: 0px;"> 
                <tr align="center" class="smalltxt">
                    <td width="10%" class="altbg2"><input class="checkbox" type="checkbox" value="<?=$cate['id']?>" name="cid[]"></td>
                    <td width="60%" class="altbg2"><a href="index.php?admin_category/view/<?=$cate['id']?><?=$setting['seo_suffix']?>"><input name="order[]" type="hidden" value="<?=$cate['id']?>"/><strong><?=$cate['name']?></strong></a></td>
                    <td width="10%" class="altbg2" align="center"><img src="<?=SITE_URL?>css/admin/view.png" style="cursor:pointer" onclick="document.location.href = 'index.php?admin_category/view/<?=$cate['id']?><?=$setting['seo_suffix']?>'"></td>
                    <td width="10%" class="altbg2" align="center"><img src="<?=SITE_URL?>css/admin/add.png" style="cursor:pointer" onclick="document.location.href = 'index.php?admin_category/add/<?=$cate['id']?>/<?=$cate['pid']?><?=$setting['seo_suffix']?>'"></td>
                    <td width="10%" class="altbg2" align="center"><img src="<?=SITE_URL?>css/admin/edit.png" style="cursor:pointer" onclick="document.location.href = 'index.php?admin_category/edit/<?=$cate['id']?><?=$setting['seo_suffix']?>'"></td>
                </tr>
            </table>
        </li>
        
<? } } ?>
    </ul>
    <center><input class="button" tabindex="3" type="submit" value=" 提 交 " name="submit" /></center>
</form>
<br>
<? include template(footer,admin); ?>
