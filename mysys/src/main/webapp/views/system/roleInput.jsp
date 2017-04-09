<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>
	var readonly = "${param.readonly}";
	$(function() {
		if (readonly) {
			$('input').attr("readonly", "readonly");
			$('#menuBtn').remove();
		}
		//Free.validateSubmitAndClose($("#inputForm"));
		$("#inputForm").validate({
			rules : {
				rolename : {
					required : true,
					remote : { //验证用户名是否存在
						type : "POST",
						url : "${ctx}/system/role/ajax_havaRole.do", //servlet
						data : {
							name : function() {
								return $("#rolename").val();
							},
							oldrolename : "${ob.rolename}"
						}
					}
				}
			},
			messages : {
				rolename : {
					required : "角色名不能为空",
					remote : "角色名已经存在"
				}
			},
			submitHandler : function(form) {
				$.ajax({
					type : "POST",
					url : $(form).attr("action"),
					data : $(form).serialize(),
					error : Free.ajaxError,
					success : function(data) {
						if (data == 'ok') {
							if (parent.g)
								parent.g.reload();
							parent.layer.msg('操作成功', {
								time : 1000
							});
							parent.layer.closeAll('iframe');
						} else {
							parent.layer.msg('操作失败', {
								time : 1000
							});
						}
					}
				});
				return false;
			}
		});
	});
	//====================tree 组件 start
	var setting = {
		view : {
			selectedMulti : false
		},
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false
		},
		check : {
			enable : false
		//是否显示checkbox
		},
		async : {
			enable : true,
			url : "${ctx}/system/department/ajax_tree.do"
		},
		data : {
			key : {
				name : "name"
			},
			simpleData : {
				enable : true,//true时下面的设置生效
				idKey : "id",//id
				pIdKey : "fid",//pid
			}
		},
		callback : {
			onAsyncSuccess : zTreeOnAsyncSuccess,//异步加载成功,
			onClick : onClick
		}
	};
	//异步加载成功后    展开所有节点
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		treeObj.expandAll(true);
	};

	function beforeClick(treeId, treeNode) {
		var check = (treeNode && !treeNode.isParent);
		if (!check)
			alert("只能选择城市...");
		return check;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
				.getSelectedNodes();
		$("#areaname").val(nodes[0].name);
		$("#areaid").val(nodes[0].id);
		hideMenu();
	}

	function showMenu() {
		var cityObj = $("#areaname");
		var cityOffset = $("#areaname").offset();
		$("#menuContent").css({
			left : cityOffset.left + "px",
			top : cityOffset.top + cityObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
				event.target).parents("#menuContent").length > 0)) {
			hideMenu();
		}
	}

	var treeObj = null;
	$(document).ready(function() {
		treeObj = $.fn.zTree.init($("#treeDemo"), setting);
	});

	//====================tree 组件 end
</script>

</head>
<style type="text/css">
.ztree li span.button.iconUser_ico_docu {
	margin-right: 2px;
	background: url(${ctx}/static/ztree/css/zTreeStyle/img/diy/ico_user.png)
		no-repeat scroll 0 0 transparent;
	vertical-align: top;
	*vertical-align: middle
}

ul.ztree {
	background: none repeat scroll 0 0 #f0f6e4;
	border: 1px solid #617775;
	height: 360px;
	margin-top: 10px;
	overflow-x: auto;
	overflow-y: scroll;
	width: 490px;
}

.ztree li span.button.ico_docu {
	margin-right: 2px;
	background-position: -110px -32px;
	vertical-align: top;
	float: left;
}
</style>
<body>
	<form id="inputForm" action="${ctx}/system/role/save.do">
		<input type="hidden" name="id" value="${ob.id}" /> 
		<div class="content">
			<div class="tit">角色信息</div>
			<div class="tit_down">
				<table width="100%" class="table2">
					<tr>
						<td height="40" class="fr">角色：</td>
						<td><input name="rolename" id="rolename" class="dfinput "
							value="${ob.rolename}" type="text" /></td>
					</tr>

					<tr>
						<td height="40" class="fr">描述：</td>
						<td><input name="description" class="dfinput "
							value="${ob.description}" type="text" /></td>
					</tr>
				</table>
				<div style="width: 200px; margin: 0 auto">
					<c:if test="${empty param.readonly}">
						<button class="btn1">保存</button>
					</c:if>
				</div>
			</div>
		</div>
	</form>
</body>
</html>