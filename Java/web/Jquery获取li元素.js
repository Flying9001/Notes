jquery 获取<ul> 点击的是那个<li>
<ul class="anserdh" id="topmenu">
   <li class="qhbg"><a href="" >积分榜</a></li>
   <li><a href="">回答榜 </a></li>
   <li><a href="" >提问榜</a></li>
   <li><a href="" >满意榜</a></li>
</ul>
点击那个就把在那个<li>的追加class="qhbg"样式
比如：点击了回答榜 变成
 <ul class="anserdh" id="topmenu">
   <li ><a href="" >积分榜</a></li>
   <li class="qhbg"><a href="">回答榜 </a></li>
   <li><a href="" >提问榜</a></li>
   <li><a href="" >满意榜</a></li>
</ul>

$(function(){

$('.anserdh li a').click(function(){
    $('.anserdh li').removeClass('qhbg');
    $(this).parent().addClass('qhbg');

  })

})