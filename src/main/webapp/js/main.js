$(function () {
    $('#menuTree').tree({
        url:'/util/findMenu',
        onClick:function(node) {
            addTab(node.text,node.url);
        }
    });
    function addTab(title, url){
        if(url){
            if ($('#dataTab').tabs('exists', title)){

                $('#dataTab').tabs('select', title);
            } else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                $('#dataTab').tabs('add',{
                    title:title,
                    content:content,
                    closable:true
                });
            }
        }
    };

})

