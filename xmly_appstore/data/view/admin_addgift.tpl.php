<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template(header,admin); ?>
<script type="text/javascript">g_site_url='<?=SITE_URL?>';g_prefix='<?=$setting['seo_prefix']?>';g_suffix='<?=$setting['seo_suffix']?>';</script>
<script src="<?=SITE_URL?>js/editor/ueditor.config.js" type="text/javascript"></script> 
<script src="<?=SITE_URL?>js/editor/ueditor.all.js" type="text/javascript"></script> 
<div style="width:100%; height:15px;color:#000;margin:0px 0px 10px;">
    <div style="float:left;"><a href="index.php?admin_main/stat<?=$setting['seo_suffix']?>" target="main"><b>控制面板首页</b></a>&nbsp;&raquo;&nbsp;添加礼品</div>
</div><? if(isset($message)) { $type=isset($type)?$type:'correctmsg';  ?><table cellspacing="1" cellpadding="4" width="100%" align="center" class="tableborder">
    <tr>
        <td class="<?=$type?>"><?=$message?></td>
    </tr>
</table><? } ?><form <? if(isset($gift)) { ?>action="index.php?admin_gift/edit<?=$setting['seo_suffix']?>"<? } else { ?>action="index.php?admin_gift/add<?=$setting['seo_suffix']?>"<? } ?>  method="post" enctype="multipart/form-data">
    <table cellspacing="1" cellpadding="4" width="100%" align="center" class="tableborder">
        <tr class="header"><td colspan="2">参数设置</td></tr>
        <tr>
            <td class="altbg1" width="45%"><b>礼品图片:</b><br><span class="smalltxt">礼品图片请选jpg、gif、png格式</span></td>
            <td class="altbg2"><? if(isset($gift['image'])) { ?><img src="<?=SITE_URL?><?=$gift['image']?>" width="80" height="80"/>&nbsp;&nbsp;&nbsp;<? } ?><input type="file" size="30" name="imgurl" /></td>
        </tr>
        <tr>
            <td class="altbg1" width="45%"><b>礼品名称:</b><br><span class="smalltxt">礼品名称</span></td>
            <td class="altbg2"><input class="txt"  name="giftname" <? if(isset($gift['title'])) { ?> value="<?=$gift['title']?>" <? } ?> /></td>
        </tr>
        <tr>
            <td class="altbg1" width="45%"><b>需要的财富值:</b><br><span class="smalltxt">兑换该礼品所需要的财富值</span></td>
            <td class="altbg2"><input class="txt" <? if(isset($gift['credit'])) { ?> value="<?=$gift['credit']?>" <? } ?> name="giftprice" /></td>
        </tr>
        <tr>
            <td class="altbg1" width="45%"><b>礼品描述:</b><br><span class="smalltxt">对于该礼品的简短说明</span></td>
            <td class="altbg2">
                <script type="text/plain" id="mycontent" name="giftdesrc" style="width:610px;height:300px;"><?=$gift['description']?></script>
                <script type="text/javascript">UE.getEditor('mycontent');</script>
            </td>
        </tr>
    </table>
    <br />
    <? if(isset($gift['id'])) { ?>    <input type="hidden" value="<?=$gift['id']?>" name="id" />
    <input type="hidden" value="<?=$gift['image']?>" name="imgpath" />
    <? } ?>    <center><input type="submit" class="button" name="submit" value="提 交"></center><br>
</form>
<br />
<? include template(footer,admin); ?>
