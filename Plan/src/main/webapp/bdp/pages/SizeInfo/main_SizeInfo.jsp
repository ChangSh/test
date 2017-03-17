<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../commons/statics.jsp"%>
<style type="text/css">
.bootstrap-table td {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  max-width: 200px;
}

#SizeInfo_view_form .col-sm-9 label {
  font-weight: normal;
}

#SizeInfo_form .col-sm-9 label {
  font-weight: normal;
}
</style>

<script type="text/javascript">
	$(function() {
		$('#SizeInfo_table').bootstrapTable('hideColumn', 'width');
		$('#SizeInfo_table').bootstrapTable('hideColumn', 'height');

	});
	function operateFormatter(value, row, index) {
		return [
				'&nbsp;<a class="check"  href="javascript:void(0)" title="查看">',
				'<i class="glyphicon glyphicon-link"></i>', '</a> &nbsp; ',
				'  <a class="edit" href="javascript:void(0)" title="编辑">',
				'<i class="glyphicon glyphicon-edit"></i>', '</a> &nbsp; ',
				' <a class="delete"  href="javascript:void(0)" title="删除">',
				'<i class="glyphicon glyphicon-remove-circle"></i>', '</a>' ]
				.join('');
	}

	window.operateEvents = {
		'click .edit' : function(e, value, row, index) {
			object_operate.show_modify_modal(row);
		},
		'click .check' : function(e, value, row, index) {
			object_operate.show_view_modal(row);
		},
		'click .delete' : function(e, value, row, index) {
			object_operate.del();
		}
	};
	global_variable.model = "SizeInfo";
	global_variable.model_cn = "用户管理";
	global_variable.sortName = "id";
	global_variable.columns = [ {
		field : 'state',
		checkbox : true
	}, {
		field : 'id',
		title : 'id',
		formatter : function(value, row, index) {
			return row.id;
		}
	}, {
		field : 'type',
		title : '尺寸类型',
		formatter : function(value, row, index) {
			if (row.type == 1) {
				return "自定义尺寸";
			} else if (row.type == 2) {
				return "常规尺寸";
			} else if (row.type == 3) {
				return "普通图片";
			} else {
				return "普通flash";
			}
		}
	}, {
		field : 'name',
		title : '尺寸名称',
		formatter : function(value, row, index) {
			return row.name;
		}
	}, {
		field : 'modalType',
		title : '模板类型',
		formatter : function(value, row, index) {
			if (row.modaltype == 1) {
				return "普通模板";
			} else if (row.modaltype == 2) {
				return "横幅模板";
			} else {
				return "全屏模板";
			}
		}
	}, {
		field : 'html',
		title : 'html',
		formatter : function(value, row, index) {
			return row.html.replace("script", "ss");
		}
	}, {
		field : 'width',
		title : '宽度',
		formatter : function(value, row, index) {
			return row.width;
		}
	}, {
		field : 'height',
		title : '高度',
		formatter : function(value, row, index) {
			return row.height;
		}
	}, {
		field : 'modalnum',
		title : '关联创意',
		formatter : function(value, row, index) {
			return row.modalnum + '个创意';
		}
	}, {
		field : 'operate',
		title : '操作',
		events : operateEvents,
		formatter : operateFormatter
	} ]
	function show() {
		var sizeType = $("#sizeType  option:selected").val();
		if (sizeType == 1) {
			$('#SizeInfo_table').bootstrapTable('showColumn', 'name');
			$('#SizeInfo_table').bootstrapTable('showColumn', 'modalType');
			$('#SizeInfo_table').bootstrapTable('showColumn', 'html');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'width');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'height');
			$('#SizeInfo_table').bootstrapTable('showColumn', 'modalnum');

		} else if (sizeType == 2) {
			$('#SizeInfo_table').bootstrapTable('showColumn', 'name');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'modalType');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'html');
			$('#SizeInfo_table').bootstrapTable('showColumn', 'width');
			$('#SizeInfo_table').bootstrapTable('showColumn', 'height');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'modalnum');
		} else {
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'name');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'modalType');
			$('#SizeInfo_table').bootstrapTable('showColumn', 'html');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'width');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'height');
			$('#SizeInfo_table').bootstrapTable('hideColumn', 'modalnum');
			$("#searchSizeInfo input[name='name']").parent().hide();

		}

	}
	global_variable.onLoadSuccess = function(result) {
	}
	global_variable.showExport = false;
	object_operate.del = function() {
		var rows = $("#" + global_variable.model + "_table").bootstrapTable(
				"getSelections");
		var ids = [];
		//for( var o in rows) {
		//ids.push(rows[o].id);
		//}
		for ( var o in rows) {
			var k = 0;//获取第一列
			for ( var i in rows[o]) {
				if (k == 0) {
					ids.push(rows[o][i]);
				}
				k++;
			}
		}

		if (rows.length > 0) {
			if (confirm("请确认是否批量删除" + global_variable.model_cn)) {
				$.ajax({
					async : false,
					type : 'POST',
					data : JSON.stringify(ids),
					url : "delete" + object_name + ".do",
					dataType : "json",
					contentType : "application/json",
					error : function(result) {//请求失败处理函数
						alert("请求失败");
					},
					success : function(result) { //请求成功后处理函数。 
						alert(result.msg);
						object_operate.success();
					}
				});
			}
		} else {
			alert("请先选择删除行");
		}
	}
	object_operate.show_add_modal = function() {
		$("#" + global_variable.model + "_form input[name='width']").parent()
				.parent().hide();
		$("#" + global_variable.model + "_form input[name='height']").parent()
				.parent().hide();
		$("#add_sizeType").removeAttr("disabled");
		$("#" + global_variable.model + "_submit").removeAttr('disabled');
		this.type = "add";
		object_form_operate.setTitle();
		/* $("#hiddenID").val(0);//设置默认值
		$("input[name='width']").val(0);
		$("input[name='height']").val(0); */
		object_form_operate.reset();
		$("#" + global_variable.model + "_Modal").modal("show");

		$("#" + global_variable.model + "_submit").unbind("click");
		$("#" + global_variable.model + "_submit").bind("click", function() {
			object_operate.add();
		});
	}

	object_operate.add = function() {
		var data = checkForm();
		if (data != undefined) {
			ajaxHander("add" + object_name + ".do", data, "POST");
		}
	}
	object_operate.show_modify_modal = function(obj) {

		$("#" + global_variable.model + "_submit").removeAttr('disabled');

		this.type = "modify";
		object_form_operate.setTitle();
		object_form_operate.setValue(obj);
		$("#" + global_variable.model + "_Modal").modal("show");
		$("#" + global_variable.model + "_submit").unbind("click");
		$("#" + global_variable.model + "_submit").bind("click", function() {
			object_operate.modify();
		});
		$("#" + global_variable.model + "_close").bind("click", function() {
		});
	}
	object_operate.modify = function() {
		var data = checkForm();
		if (data != undefined) {
			ajaxHander("modify" + object_name + ".do", data, "POST");
		}
	}
	object_form_operate.setValue = function(obj) {
		var k = 0;//获取第一列
		for ( var i in obj) {
			if (k == 0) {
				$("#hiddenID").val(obj[i]);//设置修改主键
			}
			k++;
		}

		for ( var o in obj) {
			if ($("#" + global_variable.model + "_form input[name='" + o + "']")
					.attr('type') == 'radio') {
				$(
						"#" + global_variable.model + "_form input[value='"
								+ obj[o] + "']").attr("checked", "checked");
			}
			if ($("#" + global_variable.model + "_form input[name='" + o + "']")
					.attr('type') == 'text'
					|| $(
							"#" + global_variable.model + "_form input[name='"
									+ o + "']").attr('type') == undefined) {
				$("#" + global_variable.model + "_form input[name='" + o + "']")
						.val(obj[o]);
			}
			if (o == "html") {
				$("#" + global_variable.model + "_form textarea").val(obj[o]);
			}
			if (o == "type") {
				if (obj[o] != null) {
					var values = obj[o];
					$('option', $('#add_sizeType')).each(
							function(element) {
								$(this).removeAttr('selected').prop('selected',
										false);
								if (values == $(this).val()) {
									$(this).removeAttr('selected').prop(
											'selected', true);
								}
							});
					$("#add_sizeType").multiselect('refresh');
				}
			}
			if (o == "modaltype") {
				if (obj[o] != null) {
					var values = obj[o];
					$('option', $('#modaltype_show')).each(
							function(element) {
								$(this).removeAttr('selected').prop('selected',
										false);
								if (values == $(this).val()) {
									$(this).removeAttr('selected').prop(
											'selected', true);
								}
							});
					$("#modaltype_show").multiselect('refresh');
				}
			}
		}
		editShow(obj.type, "_form input");
	}
	object_form_operate.setViewValue = function(obj, parentName) {
		if (!parentName) {
			parentName = "";
		} else {
			parentName += ".";
		}

		for ( var o in obj) {
			$(
					"#" + global_variable.model + "_view_form label[name='"
							+ parentName + o + "']").text(obj[o]);
			if (obj[o] instanceof Object) {
				object_form_operate.setViewValue(obj[o], parentName + o);
			}
			if (o == "type") {
				if (obj[o] != null) {
					var values = obj[o];
					if (values == 1) {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='type']")
								.text("自定义尺寸");
					} else if (values == 2) {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='type']")
								.text("常规尺寸");
					} else if (values == 3) {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='type']")
								.text("普通图片");
					} else {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='type']")
								.text("普通flash");
					}
				}
			}
			if (o == "modaltype") {
				if (obj[o] != null) {
					var values = obj[o];
					if (values == 1) {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='modaltype']")
								.text("普通模板");
					} else if (values == 2) {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='modaltype']")
								.text("横幅模板");
					} else {
						$(
								"#" + global_variable.model
										+ "_view_form label[name='modaltype']")
								.text("全屏模板");
					}
				}
			}
		}
		editShow(obj.type, "_view_form label");
		if (obj.html.substring(1, 7).indexOf("xml")>0) {
			readXml(obj.html,"#preview");
		} else {
			readJs(obj.html,"#preview");
		}
	}
	object_form_operate.reset = function() {
		$("#" + global_variable.model + "_form")[0].reset();
		$("#" + global_variable.model + "_form input[type='hidden']").val("");
	}
	global_variable.height = 550;

	function ajaxHander(url, data, type, value) {
		$.ajax({
			async : false,
			type : type,
			beforeSend : function() {
			},
			data : data,
			url : url,
			success : function(data) { //请求成功后处理函数。 				
				alert(data.msg);
				object_operate.success();
			}
		});
	}

	function checkForm() {
		//验证
		return $("#" + global_variable.model + "_form").serialize();
	}
	function dateFormat(time, format) {
		var t = new Date(time);
		var tf = function(i) {
			return (i < 10 ? '0' : '') + i
		};
		return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
			switch (a) {
			case 'yyyy':
				return tf(t.getFullYear());
				break;
			case 'MM':
				return tf(t.getMonth() + 1);
				break;
			case 'mm':
				return tf(t.getMinutes());
				break;
			case 'dd':
				return tf(t.getDate());
				break;
			case 'HH':
				return tf(t.getHours());
				break;
			case 'ss':
				return tf(t.getSeconds());
				break;
			}
		})
	}
	function editShow(type, id) {
		if (type == 1) {
			$("#" + global_variable.model + id + "[name='name']").parent()
					.parent().show();
			$("#" + global_variable.model + "_form select[name='modaltype']")
					.parent().parent().show();//修改
			$("#" + global_variable.model + id + "[name='modaltype']").parent()
					.parent().show();//详情
			$("#" + global_variable.model + id + "[name='html']").parent()
					.parent().show();
			$("#" + global_variable.model + "_form textarea[name='html']")
					.parent().parent().show();
			$("#" + global_variable.model + id + "[name='width']").parent()
					.parent().hide();
			$("#" + global_variable.model + id + "[name='height']").parent()
					.parent().hide();
		} else if (type == 2) {
			$("#" + global_variable.model + id + "[name='name']").parent()
					.parent().show();
			$("#" + global_variable.model + "_form select[name='modaltype']")
					.parent().parent().hide();
			$("#" + global_variable.model + id + "[name='modaltype']").parent()
					.parent().hide();//详情
			$("#" + global_variable.model + id + "[name='html']").parent()
					.parent().hide();
			$("#" + global_variable.model + "_form textarea[name='html']")
					.parent().parent().hide();
			$("#" + global_variable.model + id + "[name='width']").parent()
					.parent().show();
			$("#" + global_variable.model + id + "[name='height']").parent()
					.parent().show();
		} else {
			$("#" + global_variable.model + id + "[name='name']").parent()
					.parent().hide();
			$("#" + global_variable.model + "_form [name='modaltype']")
					.parent().parent().hide();
			$("#" + global_variable.model + id + "[name='modaltype']").parent()
					.parent().hide();//详情
			$("#" + global_variable.model + id + "[name='html']").parent()
					.parent().show();
			$("#" + global_variable.model + "_form textarea[name='html']")
					.parent().parent().show();
			$("#" + global_variable.model + id + "[name='width']").parent()
					.parent().hide();
			$("#" + global_variable.model + id + "[name='height']").parent()
					.parent().hide();
			// $("#add_sizeType option:checked").prop("selected", false);
			//$("#add_sizeType option[value='4']").prop("selected",true); 
		}
	}
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <br>
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" style="display: block; text-decoration: none;" href="#searchSizeInfo"> 搜索 </a>
          </h4>
        </div>
        <div id="searchSizeInfo" class="panel-collapse collapse">
          <div class="panel-body">
            <form id="SizeInfo_searchForm">
              <!-- <div class="form-group input-group">
								<span class="input-group-addon">条件</span> <input type="text"
									class="form-control" style="width: 200px;" name="" />
							</div> 
              -->
              <div class="form-group input-group">
                <span class="input-group-addon">尺寸类型</span> <select name="type" id="sizeType" onchange=show();>
                  <option value="1" checked>自定义尺寸</option>
                  <option value="2">常规尺寸</option>
                  <option value="3">普通图片</option>
                  <option value="4">普通flash</option>
                </select>
              </div>

              <div class="form-group input-group">
                <span class="input-group-addon">尺寸名称</span> <input type="text" class="form-control"
                  style="width: 200px;" name="name" />
              </div>
              <div style="float: right;">
                <input type="button" value="搜索" id="SizeInfo_search" class="btn btn-Default" /> <input type="button"
                  value="清空" id="SizeInfo_searchReset" class="btn btn-Default" />
              </div>
            </form>
          </div>
        </div>
      </div>
      <!--功能菜单 -->
      <div id="SizeInfo_toolbar">
        <input type="button" class="btn btn-primary" value="新增" id="SizeInfo_add">
      </div>
      <table id="SizeInfo_table">
      </table>
    </div>

    <!--第一层模态框  -->
    <div class="modal fade" id="SizeInfo_Modal" tabindex="-1" role="dialog" aria-labelledby="SizeInfo_ModalTitle"
      aria-hidden="true">
      <div class="modal-dialog" style="width: 600x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="SizeInfo_ModalTitle"></h4>
          </div>
          <div class="modal-body">
            <form id="SizeInfo_form" class="form-horizontal">
              <input type='hidden' name='id' id='hiddenID'>
              <div class='form-group'>
                <label class='col-sm-3 control-label'><span style='color: red'>*</span>尺寸类型：</label>
                <div class='col-sm-9'>
                  <!--  <select name='type' id="add_sizeType" class="input-md form-control">   -->
                  <select name='type' id="add_sizeType" onchange="editShow(this.value,'_form input')">
                    <option value=1>自定义尺寸</option>
                    <option value=2>常规尺寸</option>
                    <option value=3>普通图片</option>
                    <option value=4>普通flash</option>
                  </select>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'><span style='color: red'>*</span>尺寸名称：</label>
                <div class='col-sm-9'>
                  <input class='form-control' name='name'>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'><span style='color: red'>*</span>模板类型：</label>
                <div class='col-sm-9'>
                  <select name='modaltype' id="modaltype_show">
                    <option value=1>普通模板</option>
                    <option value=2>全屏模板</option>
                    <option value=3>横幅模板</option>
                  </select>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'><span style='color: red'>*</span>html：</label>
                <div class='col-sm-9'>
                  <textarea class='form-control' name='html' rows="3"></textarea>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'><span style='color: red'>*</span>宽度：</label>
                <div class='col-sm-9'>
                  <input class='form-control' name='width'>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'><span style='color: red'>*</span>高度：</label>
                <div class='col-sm-9'>
                  <input class='form-control' name='height'>
                </div>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="SizeInfo_close">关闭</button>
            <button type="button" class="btn btn-primary" id="SizeInfo_submit">提交</button>
          </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="SizeInfo_view_Modal" tabindex="-1" role="dialog"
      aria-labelledby="SizeInfo_view_ModalTitle" aria-hidden="true">
      <div class="modal-dialog" style="width: 580x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="SizeInfo_view_ModalTitle">查看用户详情</h4>
          </div>
          <div class="modal-body">
            <form id="SizeInfo_view_form" class="form-horizontal">
              <div class='form-group'>
                <label class='col-sm-3 control-label'>id：</label>
                <div class='col-sm-9'>
                  <label name='id' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>尺寸类型：</label>
                <div class='col-sm-9'>
                  <label name='type' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>尺寸名称:</label>
                <div class='col-sm-9'>
                  <label name='name' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>模板类型：</label>
                <div class='col-sm-9'>
                  <label name='modaltype' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>html：</label>
                <div class='col-sm-9'>
                  <label name='html' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>宽度：</label>
                <div class='col-sm-9'>
                  <label name='width' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>高度：</label>
                <div class='col-sm-9'>
                  <label name='height' style='padding-top: 6px;'></label>
                </div>
              </div>
              <div class='form-group'>
                <label class='col-sm-3 control-label'>预览：</label>
                <div class='col-sm-9'>
                   <label name='' style='padding-top: 6px;'>下面为预览页面：</label>
                   <input type="button" onclick="bulidJs();"/>
                   <input type="button" onclick="bulidXml();"/>
                </div>
              </div>
              <div class='form-group' id="preview"></div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
