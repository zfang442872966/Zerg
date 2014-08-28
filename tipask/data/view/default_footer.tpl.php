<? if(!defined('IN_TIPASK')) exit('Access Denied'); ?>
<div class="wrapper">
    <div class="footer">
        <div class="bottom">
            <a href="<?=SITE_URL?>" target="_blank"><?=$setting['site_name']?></a><span class="span-line">|</span>
            <a href="mailto:<?=$setting['admin_email']?>" target="_blank">联系我们</a><span class="span-line">|</span>
            <a href="<?=SITE_URL?>?index/help.html" target="_blank">使用帮助</a>
            <? if($setting['site_icp']) { ?>            <span class="span-line">|</span><a href="http://www.miibeian.gov.cn" target="_blank"><?=$setting['site_icp']?></a>
            <? } ?>            <? if($setting['site_statcode']) { ?>            <?=$setting['site_statcode']?>
            <? } ?>        </div>
        <p>Powered by <a rel="nofollow" target="_blank" href="http://www.tipask.com/">Tipask v<?=TIPASK_VERSION?></a> &copy;2009-2014 <a rel="nofollow" target="_blank" href="http://www.tipask.com">tipask.com</a>，Processed in <?=$runtime?> second(s), <?=$querynum?> queries.</p>
    </div>
</div>
<a id="scrollUp" href="#top" title="" style="position: fixed; z-index: 2147483647; display: block;"></a>
<div class="usercard" id="usercard"><div class="usercard_in clearfix"><div class="loading"><img src='<?=SITE_URL?>css/default/loading.gif' align='absmiddle' />&nbsp;加载中...</div></div></div>
<script src="<?=SITE_URL?>js/jquery-ui/jquery-ui.js" type="text/javascript"></script>
<script src="<?=SITE_URL?>js/jquery.scrollup.js" type="text/javascript"></script>
<script src="<?=SITE_URL?>js/common.js" type="text/javascript"></script>
<script type="text/javascript">
                $.scrollUp({
                scrollName: 'scrollUp', // Element ID
                topDistance: '260', // Distance from top before showing element (px)
                topSpeed: 300, // Speed back to top (ms)
                animation: 'fade', // Fade, slide, none
                animationInSpeed: 200, // Animation in speed (ms)
                animationOutSpeed: 200, // Animation out speed (ms)
                scrollText: '', // Text for element
                activeOverlay: false  // Set CSS color to display scrollUp active point, e.g '#00FFFF'
            });
</script>
</body>
</html>