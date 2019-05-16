layui.extend({
	admin: '{/}../../static/js/admin'
});

layui.use(['table', 'jquery','form', 'admin'], function() {
	// var table = layui.table,
	// 	$ = layui.jquery,
	// 	form = layui.form,
	//

	//
	// table.render({
	// 	elem: '#articleList',
	// 	cellMinWidth: 80,
	// 	cols: [
	// 		[ {
	// 			field: 'id',title: 'ID',sort: true
	// 		}, {
	// 			field: 'xlsName',title: '问卷标题',templet: '#usernameTpl'
	// 		}, {
	// 			field: 'date',title: '上传时间',sort: true
	// 		}, {
	// 			field: 'operate',title: '操作',toolbar: '#operateTpl',unresize: true
	// 		}]
	// 	],
    //     url: '/questionnaire/list' ,//数据接口
	// 	event: true,
	// 	page: true
	// });
    layui.use('table', function(){
        var table = layui.table;
        $ = layui.$, form = layui.form,
        //第一个实例

        //搜索 ----------------------------------------------- Begin-----------------------------------------------------------
        $('#searchBtn').on('click',function(){
             var type = $(this).data('type');
             active[type] ? active[type].call(this) : '';
        });
        // 点击获取数据
        var  active = {
            getInfo: function () {
                var orderId=$('#searchValue').val();
                if (orderId) {
                    var index = layer.msg('查询中，请稍候...',{icon: 16,time:false,shade:0});
                    setTimeout(function(){
                        table.reload('articleList', { //表格的id
                            url:'/user-list',
                            where: {
                                name: orderId
                            }
                        });
                        layer.close(index);
                    },800);
                } else {
                    layer.msg("请输入用户名字");
                }
            },
        };
        //搜索 ----------------------------------------------- End-----------------------------------------------------------
        table.render({
            elem: '#articleList'
            ,height: 312
            ,url: '/user-list' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id',title: 'ID',sort: true}
                ,{field: 'name',title: '用户名',templet: '#usernameTpl'}
                ,{field: 'password',title: '密码',sort: true}
                ,{field: 'question',title: '密保问题',sort: true}
                ,{field: 'answer',title: '密保答案',sort: true}
                ,{field: 'operate',title: '操作',toolbar: '#operateTpl',unresize: true}
            ]]
        });

        //监听工具条
        table.on('tool(tableBtn)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'analysis'){ //查看
                console.log(data);
                window.location.href = "/edit-user/" + data.id;
                //do somehing
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
					$.post("/del-user/" + data.id,{},function (d) {
						if (d =="success") {
							layer.msg("删除成功");
						}else {
							layer.msg("系统异常");
						}
                    });

                });
            } else if(layEvent === 'edit'){ //编辑
                //do something

                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    ,title: 'xxx'
                });
            }
        });

    });
	/*
	 *数据表格中form表单元素是动态插入,所以需要更新渲染下
	 * http://www.layui.com/doc/modules/form.html#render
	 * */
	$(function(){
		form.render();
	});
	
	// var active = {
	// 	getCheckData: function() { //获取选中数据
	// 		var checkStatus = table.checkStatus('articleList'),
	// 			data = checkStatus.data;
	// 		//console.log(data);
	// 		//layer.alert(JSON.stringify(data));
	// 		if(data.length > 0) {
	// 			layer.confirm('确认要删除吗？' + JSON.stringify(data), function(index) {
	// 				layer.msg('删除成功', {
	// 					icon: 1
	// 				});
	// 				//找到所有被选中的，发异步进行删除
	// 				$(".layui-table-body .layui-form-checked").parents('tr').remove();
	// 			});
	// 		} else {
	// 			layer.msg("请先选择需要删除的文章！");
	// 		}
	//
	// 	}
		// ,
		// Recommend: function() {
		// 	var checkStatus = table.checkStatus('articleList'),
		// 		data = checkStatus.data;
		// 	if(data.length > 0) {
		// 		layer.msg("您点击了推荐操作");
		// 		for(var i = 0; i < data.length; i++) {
		// 			console.log("a:" + data[i].recommend);
		// 			data[i].recommend = "checked";
		// 			console.log("aa:" + data[i].recommend);
		// 			form.render();
		// 		}
		//
		// 	} else {
		// 		console.log("b");
		// 		layer.msg("请先选择");
		// 	}
		//
		// 	//$(".layui-table-body .layui-form-checked").parents('tr').children().children('input[name="zzz"]').attr("checked","checked");
		// },
		// Top: function() {
		// 	layer.msg("您点击了置顶操作");
		// },
		// Review: function() {
		// 	layer.msg("您点击了审核操作");
		// }

	// };

	// $('.demoTable .layui-btn').on('click', function() {
	// 	var type = $(this).data('type');
	// 	active[type] ? active[type].call(this) : '';
	// });
	//
	// /*用户-删除*/
	// window.member_del = function(obj, id) {
	// 	layer.confirm('确认要删除吗？', function(index) {
	// 		//发异步删除数据
	// 		$(obj).parents("tr").remove();
	// 		layer.msg('已删除!', {
	// 			icon: 1,
	// 			time: 1000
	// 		});
	// 	});
	// }

});

// function delAll(argument) {
// 	var data = tableCheck.getData();
// 	layer.confirm('确认要删除吗？' + data, function(index) {
// 		//捉到所有被选中的，发异步进行删除
// 		layer.msg('删除成功', {
// 			icon: 1
// 		});
// 		$(".layui-form-checked").not('.header').parents('tr').remove();
// 	});
// }