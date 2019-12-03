//ICheck工具类  用了dataTables init方法必须重绘-----统一的全选与反选 批量删除
var ICheck=function () {
    //批量选择框对象 class属性 icheck all
    var allCheck=$('input[type="checkbox"].icheck.all');
    //单个选择框对象 class属性 icheck each
    var eachCheck;
    //初始化ICheck
    var initICheck=function () {
        //初始化ICheck样式
        $('input[type="checkbox"].icheck').iCheck({
            checkboxClass: 'icheckbox_minimal-purple'
        });
    };
    //全选 反选
    var check=function () {
        eachCheck=$('input[type="checkbox"].icheck.each');
        //全选被点击时执行
        allCheck.on('ifClicked', function(e){
            //如果全选被选中
            if (!e.target.checked){
                //让其他也全选中
                eachCheck.iCheck('check');
            }
            else {
                eachCheck.iCheck('uncheck');
            }
        });
        //子选择框点击事件 触发主选择框
        eachCheck.on('ifClicked', function(e){
            var length=1;
            if(e.target.checked){
                allCheck.iCheck('uncheck');
            }
            else{
                eachCheck.each(function () {
                    if(($(this).prop("checked"))){
                        length++;
                    }
                });
                if(eachCheck.length===length){
                    allCheck.iCheck('check');
                }
            }
        })
    };
    //删除操作 多个删除与单个删除   url/删除的ajax请求地址  id/单删除时的id
    var delCheck=function (url,id) {
        var _idArray; //保存id的数组
        var l; //数组的长度
        del(url,id);

        //私有函数中的私有函数 只能这个私有函数调用
        function del(url,id){
            //获取选中的id并保存到数组_idArray
            _idArray=new Array();
            eachCheck.each(function () {
                // 判断是否选中
                var delFlag = $(this).is(":checked");
                if (delFlag) {
                    _idArray.push($(this).attr("id"));
                }
            });
            //保存单选时传的id  不为空时先清除打钩的
            if(id!=null&&id!=""){
                _idArray=[];
                _idArray.push(id);
            }
            l=_idArray.length;
            //没有选择删除项
            if (l===0){
                $("#modal-message").html("至少删除一项");
            }
            else{
                $("#modal-message").html("确定删除么");
            }
            //显示模态框
            $("#basic").modal("show");
        }
        //模态框点击确定操作
        $(".modal-footer .btn.green").bind("click",function () {
            if(l===0){
                $("#basic").modal("hide");
            }
            //有id时提交请求
            else{
                $.ajax({
                    "url":url,
                    "data":{"ids":_idArray.toString()},
                    "type":"POST",
                    "dataType":"JSON",
                    "success":function (data) {
                        $("#modal-message").html(data.message);
                        $(".modal-content .modal-footer").html("");
                        $("#basic").modal("show");
                        setTimeout(function () {
                            window.location.reload();
                        },500);
                    }
                });
            }
        });
    };

    return{
        init:function () {
            initICheck();
            check();
        },
        delCheck:function (url,id) {
            delCheck(url,id);
        }
    }
}();

jQuery(document).ready(function () {
    ICheck.init();
});
