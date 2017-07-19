jQuery(function(){
/*返回顶部与找客服*/
var jq=jQuery;
jq('#roll_top').hide();
jq(window).scroll(function () {
if (jq(window).scrollTop() > 0) {
jq('#roll_top').fadeIn(0);//当滑动栏向下滑动时，按钮渐现的时间
} else {
jq('#roll_top').fadeOut(0);//当页面回到顶部第一屏时，按钮渐隐的时间
}
});
jq('#roll_top').click(function () {
jq('html,body').animate({
scrollTop : '0px'
}, 0);//返回顶部所用的时间 
});
});