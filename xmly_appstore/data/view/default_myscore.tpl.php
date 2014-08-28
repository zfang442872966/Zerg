<? if(!defined('IN_TIPASK')) exit('Access Denied'); include template('header'); ?>
<div class="wrapper mt10 clearfix">
    
<? include template('user_menu'); ?>
    <div class="user-content">
        <div class="my-answerbox">
            <div class="mod-status-info">
                <div class="hd">
                    <h3>我的资料</h3>
                </div>
                <div class="bd">
                    <div class="status-data clearfix">
                        <ul>
                            <li class="item"><em><?=$user['answers']?></em><span>回答</span></li>
                            <li class="item"><em><?=$user['questions']?></em><span>提问</span></li>
                            <li class="item"><em><?=$adoptpercent?>%</em>采纳率</li>
                            <li class="item"><em><?=$user['supports']?></em><span>赞同</span></li>
                            <li class="item"><em><a href="<?=SITE_URL?>?user/follower.html"><?=$user['followers']?></a></em>关注者</li>
                            <li class="item last"><em><a href="<?=SITE_URL?>?user/attention.html"><?=$user['attentions']?></a></em>已关注</li>
                            <? if(2==$user['grouptype']) { ?>                            <? $credit1percent=round(($user['credit1']/$user['creditshigher'])*100); ?>                            <li class="item last rate"><div id="progressbar"></div><p>升级到下一等级 <a href="<?=SITE_URL?>?user/level.html"> [<?=$nextgroup['grouptitle']?>]</a> 还需 <font color="red"><?=$higherneeds?></font> 经验值</p></li>
                            <script type="text/javascript">
                                $(document).ready(function() {
                                    $("#progressbar").progressbar({value: <?=$credit1percent?>});
                                });
                            </script>
                            <? } ?>                        </ul>
                    </div>
                </div>
            </div>
            <div class="mod-status-info">
                <div class="hd">
                    <h3>我的积分</h3>
                </div>
                <div class="bd">
                    <? if($setting['ucenter_open']) { ?>                    <form name="exchangeform"  action="<?=SITE_URL?>?user/exchange.html" method="post">
                        <div class="exchange">积分兑换:
                            <input type="text" value="2" size="5" onkeyup="init_exchange()" name="exchangeamount" class="credit_input" id="exchangeamount">
                            <select class="normal_select" onchange="init_exchange()" name="tocredits" id="tocredits">                        
                                
<? if(is_array($outextcredits)) { foreach($outextcredits as $index => $credits) { ?>
                                <option value="<?=$credits['creditsrc']?>|<?=$credits['creditdesc']?>" src="<?=$credits['creditsrc']?>" unit="<?=$credits['unit']?>" title="<?=$credits['title']?>" index="<?=$index?>" ratio="<?=$credits['ratio']?>"><?=$credits['title']?></option>
                                
<? } } ?>
                            </select>
                            &nbsp;
                            所需&nbsp;<input type="text" value="0" class="credit_input" disabled="disabled" size="5" name="needamount" id="needamount" />
                            <select class="normal_select" name="fromcredits" id="fromcredits"></select>
                            &nbsp;
                            <input type="hidden" name="outextindex" id="outextindex" value="0" />
                            <input type="submit" name="exchange" value="立即兑换" class="normal-button" />
                        </div>
                    </form>
                    <? } ?>                    <div class="status-data clearfix">
                        <h4>经验值</h4>
                        <ul>
                            <li class="item"><em><?=$user['credit1']?></em><span>总分</span></li>
                            <li class="item"><em><?=$detail1['other']?></em><span>日常操作</span></li>
                            <li class="item"><em><?=$detail1['reward']?></em>奖励得分</li>
                            <li class="item last"><em><?=$detail1['punish']?></em><span>违规处罚</span></li>
                        </ul>
                    </div>
                    <div class="status-data clearfix">
                        <!--<a href="<?=SITE_URL?>?user/recharge.html"  class="recharge">[ 立即充值 ]</a>-->
                        <h4>财富值</h4>
                        <ul>
                            <li class="item"><em><?=$user['credit2']?></em><span>总分</span></li>
                            <li class="item"><em><?=$detail2['reward']?></em><span>奖励得分</span></li>
                            <li class="item"><em><?=$detail2['punish']?></em>违规处罚</li>
                            <li class="item"><em><?=$detail2['adopt']?></em>回答被采纳</li>
                            <li class="item last"><em><?=$detail2['offer']?></em><span>悬赏付出</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function init_exchange() {
        var exchangeamount = parseFloat($('#exchangeamount').val());
        var creditsrc = $('#tocredits').find("option:selected").attr('src');
        var ratio = parseFloat($('#tocredits').find("option:selected").attr('ratio'));
        var outextindex = $('#tocredits').find("option:selected").attr('index');
        $('#outextindex').val(outextindex);
        $('#needamount').val(exchangeamount / ratio);
        $("#fromcredits").empty();
        if (1 == creditsrc) {
            $("#fromcredits").append('<option value="1" title="经验">经验值</option>');
        } else {
            $("#fromcredits").append('<option value="2" title="财富">财富值</option>');
        }
    }
    $(document).ready(function() {
        init_exchange();
    });
</script>
<? include template('footer'); ?>
