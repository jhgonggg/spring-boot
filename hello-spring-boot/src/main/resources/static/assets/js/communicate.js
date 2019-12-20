var communicateLayer;
var communicate = (function () {

    return {
        addCommunicate: function () {
            communicateLayer = layer.open({
                type: 2,
                title: "",
                shadeClose: true,
                shade: 0.8,
                shift: 2,
                area: ['65%', '90%'],
                end: function () {

                },
                content: "people.html"
            });
        }

        // addCommunicate: function () {
        //     window.open(
        //         '/people',
        //         'newwindow',
        //         'title = "",shadeClose = true,shade = 0.8,shift = 2,height=200,area = ["65%, "90%""]')
        // }

    };
})();