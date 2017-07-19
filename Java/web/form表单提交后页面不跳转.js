最简单的就是给 form 增加 onsubmit 参数，异步提交
比如调用 js 函数用题主所述的 jquery ajax 方法：
<script>
    function PostData() {
        $.ajax({
            type: "POST",
            url: "post.go",
            data : "",
            success: function(msg) {
            }
        });
        return false;
    }
</script>
<form onsubmit="return PostData()">
    <input type="text" value="">
    <input type="submit">
</form>




$('form').submit(function (event) {
      event.preventDefault();
      var form = $(this);

      if (!form.hasClass('fupload')) {
        //普通表单
        $.ajax({
          type: form.attr('method'),
          url: form.attr('action'),
          data: form.serialize()
        }).success(function () {
          //成功提交
        }).fail(function (jqXHR, textStatus, errorThrown) {
          //错误信息
        });
      }
      else {
        // mulitipart form,如文件上传类
        var formData = new FormData(this);
        $.ajax({
          type: form.attr('method'),
          url: form.attr('action'),
          data: formData,
          mimeType: "multipart/form-data",
          contentType: false,
          cache: false,
          processData: false
        }).success(function () {
          //成功提交
        }).fail(function (jqXHR, textStatus, errorThrown) {
          //错误信息
        });
      };
    });