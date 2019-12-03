var ZTree=function () {
    /**
     *
     * @param url 请求地址url
     * @param callback 回调函数
     * @param nowId  每次访问请求地址携带的参数----目前是当前对象的id(可不用)
     */
    var initZTree=function (url,callback,nowId) {
        var setting = {
            async: {
                enable: true,
                url:url,
                //第一次以后请求提交的属性 name别名为n(第一次请求不携带参数)
                autoParam:["id", "name=n", "level=lv"],
                otherParam:{"nowId":nowId}
            },
            //回调
            callback: {
                //点击事件后 获取值并赋值
                onClick: callback
            }
        };
        $(document).ready(function(){
            $.fn.zTree.init($("#tree"), setting);
        });
    };
    return{
        init:function (url,callback,nowId) {
            initZTree(url,callback,nowId);
        }
    }
}();