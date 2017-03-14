$(function() {
    'use strict';
    $(window).resize(function () {
        $('table[data-toggle="table"]').add($('table[id]')).bootstrapTable('resetView');
    });
});