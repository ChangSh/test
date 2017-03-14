/**
 * 页面加载设置高度
 * @author: pengmaokui
 */

(function ($) {
    'use strict';
	
    $.extend($.fn.bootstrapTable.defaults, {
        onPostLoad: function(){
            var height = $(document.body).height()+250;
            window.parent.initHeight(height);
        }
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _trigger = BootstrapTable.prototype.trigger;

   BootstrapTable.prototype.trigger = function (name) {
        _trigger.apply(this, Array.prototype.slice.apply(arguments));
        if(name === "load-success" || name === "load-error" || (!this.options.url && name === "post-body")) {
        	this.trigger('post-load');
        }
    };
    
    $.extend(BootstrapTable.EVENTS, {
        	'post-load.bs.table': 'onPostLoad',
    });

})(jQuery);