<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template(header,admin); ?>
<script type="text/javascript">
$(document).ready(function() {
$("#list").sortable({
update: function(){
var reorderid="";
var numValue=$("input[name='order[]']");
for(var i=0;i<numValue.length;i++){
reorderid+=$(numValue[i]).val()+",";
}
var hiddencid=$("input[name='hiddencid']").val();
$.post("index.php?admin_link/reorder<?=$setting['seo_suffix']?>",{order:reorderid,hiddencid:hiddencid});
}
});
});

function removelink(lid){
if(confirm('删除该链接，确定继续?')){
window.location="index.php?admin_link/remove/"+lid+"<?=$setting['seo_suffix']?>";;
}
}
</script>
<div style="width:100%; height:15px;color:#000;margin:0px 0px 10px;">
  <div style="float:left;"><a href="index.php?admin_main/stat<?=$setting['seo_suffix']?>" target="main"><b>控制面板首页</b></a>&nbsp;&raquo;&nbsp;友情链接管理</div>
</div><? if(isset($message)) { $type=isset($type)?$type:'correctmsg';  ?><table cellspacing="1" cellpadding="4" width="100%" align="center" class="tableborder">
<tr>
<td class="<?=$type?>"><?=$message?></td>
</tr>
</table><? } ?><table width="100%" cellspacing="1" cellpadding="4" align="center" class="tableborder">
<tbody><tr class="header"><td>友情链接列表&nbsp;&nbsp;&nbsp;<input type="button" style="cursor:pointer" onclick="document.location.href='index.php?admin_link/add<?=$setting['seo_suffix']?>'" value="添加友情链接" /></td></tr>
<tr class="altbg1"><td>链接的排序可以通过鼠标拖动来改变，当鼠标在某一链接上面时，按住左键即可上下移动。</td></tr>
</tbody></table>
<table cellspacing="1" cellpadding="4" width="100%" align="center" style="border: 0 none !important; border-collapse: separate !important;empty-cells: show;margin-bottom: 0px;">
<tr class="header" align="center">
<td width="20%">链接名称</td>
<td  width="25%">logo</td>
<td  width="30%">描述</td>
<td  width="15%">编辑</td>
<td  width="10%">删除</td>
</tr>
</table>
    <input type="hidden" name="hiddencid" value="<?=$pid?>" />
    <ul id="list" style="cursor: pointer;width:100%;float: right;" >
<? if(is_array($linklist)) { foreach($linklist as $link) { ?>
<li style="list-style:none;">
<table  id="table1" cellspacing="1" cellpadding="4" width="100%" align="center" style="border: 0 none !important; border-collapse: separate !important;empty-cells: show;margin-bottom: 0px;"> 
<tr align="center" class="smalltxt">
<td width="20%" class="altbg1"><input name="order[]" type="hidden" value="<?=$link['id']?>"/><a href="<?=$link['url']?>" target="_blank"><?=$link['name']?></a></td>
<td width="25%" class="altbg2" align="center"><? if($link['logo']) { ?><img src="<?=$link['logo']?>" height="30" width="60"/><? } else { ?>无<? } ?></td>
<td width="30%" class="altbg1" align="center"><?=$link['description']?></td>
<td width="15%" class="altbg2" align="center"><img src="<?=SITE_URL?>css/admin/edit.png" onclick="document.location.href='index.php?admin_link/edit/<?=$link['id']?><?=$setting['seo_suffix']?>'"></td>
<td width="10%" class="altbg1" align="center"><img src="<?=SITE_URL?>css/admin/remove.png" onclick="removelink(<?=$link['id']?>);"></td>
</tr>
</table>
</li>
<? } } ?>
</ul>
<br>
<? include template(footer,admin); ?>
