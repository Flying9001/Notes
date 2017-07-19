js文件代码：

<script type="text/javascript" src="/Public/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" >
$(function() {
$(".submit").click(function() {
var name = $("#name").val();
var username = $("#username").val();
var password = $("#password").val();
var gender = $("#gender").val();
var dataString = 'name='+ name + '&username=' + username + '&password=' + password + '&gender=' + gender;

if(name=='' || username=='' || password=='' || gender=='')
{
$('.success').fadeOut(200).hide();
$('.error').fadeOut(200).show();
}
else
{
$.ajax({
type: "POST",
url: "join.php",
data: dataString,
success: function(){
$('.success').fadeIn(200).show();
$('.error').fadeOut(200).hide();
}
});
}
return false;
});
});
</script>
 

html代码：

<form method="post" name="form">
<ul><li>
<input id="name" name="name" type="text" />
</li><li>
<input id="username" name="username" type="text" />
</li><li>
<input id="password" name="password" type="password" />
</li><li>
<select id="gender" name="gender"> 
<option value="">Gender</option>
<option value="1">Male</option>
<option value="2">Female</option>
</select>
</li></ul>
<div >
<input type="submit" value="Submit" class="submit"/>
<span class="error" style="display:none"> Please Enter Valid Data</span>
<span class="success" style="display:none"> Registration Successfully</span>
</div></form>
 

php代码：

<?php
if($_POST)
{
$name=$_POST['name'];
$username=$_POST['username'];
$password=$_POST['password'];
$gender=$_POST['gender'];
mysql_query("SQL insert statement.......");
}else { }

?>