
$(function () {
    let vm = new Vue({
        el: "#app",
        data : {
            search: '',
            searchList: [],
            rightList: [],
            showError: false
        },
        mounted : async function() {
            let people = $('#communicate_people',window.parent.document).val();
            if (people && people.trim()) {
                let peoples = people.trim().split(",");
                let regex = /\((.+?)\)/g;
                let array = [];
                $.each(peoples, function (index, item) {
                    let name = item.substring(0, item.indexOf("(")).trim();
                    let id = Number.parseInt(item.match(regex).toString().replace("(", "").replace(")", ""));
                    array.push({name: name, id: id});
                });
                this.rightList = array;
            }
        },
        methods : {
            // 点击添加至右边
            add: function(item) {
                if (this.rightList.length >= 20) {
                    alert("最多可添加20个通讯员");
                    return;
                }
                let selectedected = this.rightList.filter(e => e.id === Number.parseInt(item.id));
                if (selectedected.length == 0) {
                    this.rightList.push(item);
                }
            },
            // 点击左边移除元素
            deleteRight: function(item) {
                let arr = this.rightList.filter(e => Number.parseInt(e.id) !== Number.parseInt(item.id));
                this.rightList = arr;
            },

            searchPerson: async function() {
                if (this.search && this.search.trim().length > 0) {
                    let res = await instance.get(`/find?name=${this.search.trim()}`);
                    this.searchList = res.status === 200 ? res.data : [] ;
                } else {
                    this.searchList = [];
                }
            },
            save: function() {
                if (this.rightList.length > 0) {
                    let show = '';
                    if (this.rightList.length > 3) {
                        show = Array.of(this.rightList[0], this.rightList[1], this.rightList[2]).map(e => `${e.name}(${e.id})`).join(",") + '......';
                    } else {
                        show = this.rightList.map(e => `${e.name}(${e.id})`).join(",");
                    }
                    let selected = this.rightList.map(e => `${e.name}(${e.id})`).join(",");
                    $('#communicate_people', window.parent.document).val(selected);
                    $('#communicate_people_show', window.parent.document).val(show);
                } else {
                    $('#communicate_people', window.parent.document).val("");
                    $('#communicate_people_show', window.parent.document).val("");
                }
                console.log( $('#communicate_people',window.parent.document).val());
                // parent.layer.close(parent.layer.getFrameIndex(window.name));
                // window.close();
            },
            showItem: function(item) {
                return `${item.name} (${item.id})`;
            }
        }
    });
});