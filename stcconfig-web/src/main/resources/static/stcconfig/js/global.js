/*
layer刷新子页面，
window.location.reload();
layer刷新父页面
window.parent.location.reload();
*/
/**
 * 自动将form表单封装成json对象
 */
function serializeForm(formId) {
    var o = {};
    var a = $("#" + formId).serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
// AJAX
// function getData() {
//     let data = {};
//     $.ajax({
//         url: "/config/getConfigList",
//         data: JSON.stringify({anything: 123}),
//         type: "post",
//         dataType: "json",
//         async: false,
//         headers: {'Content-Type': 'application/json;charset=utf-8'}, //接口json格式
//         success: function (result) {
//             data = result;
//         }
//     });
//     return data;
// }