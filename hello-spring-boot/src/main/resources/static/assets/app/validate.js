//函数对象---相当于一个类
var Validate=function () {
    //类中的私有函数
    var handlerInitValidation=function () {
        //添加validate没有的校验功能

        /* 验证手机号 11位   13或15开头
         */
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
        /*
        * 验证密码 只能为 字母数字 下划线 6-16位
        */
        $.validator.addMethod("pwd", function(value, element) {
            var pwd = /^[a-zA-Z0-9_]{6,16}$/;
            return this.optional(element) || pwd.test(value);
        }, "请输入6-16位字母数字或下划线");
    };
    /**
     * 提取表单验证 通过id确认校验哪张表单  rules 定义不能写在class的规则 如长度
     * @param formId
     */
    var handlerValidate = function (formId,rules) {
        $("#" + formId).validate({
            //自定义验证的name属性值的要求 也可写在前端输入项class内 *前端都必须要有name
            rules:rules,
            //自定义验证失败提示
            messages:{
                rePwd:{
                    equalTo:"两次密码不一致"
                },
                oldPwd:{
                    required:"输入项不能为空"
                }
            },
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function (error, element) {
                //element.parent().parent().attr("class", "form-group has-error");
                //找最近的form-group
                $(element).closest('.form-group').addClass('has-error');
                error.insertAfter(element);
            }
        });

    };
    //return返回的是对象 如 init
    return{
        //return里的是公有函数
        init: function () {
            handlerInitValidation();
        },
        /**
         * 表单验证
         * @param formId
         */
        validateForm: function (formId,rules) {
            handlerValidate(formId,rules);
        }
    }

}();

//导入js后自动执行
$(function () {
    Validate.init();
    Validate.validateForm("inputForm")
});