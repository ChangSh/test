/**
 * 多列排序
 * @author: pengmaokui
 */

(function ($) {
    'use strict';

    var arrowAsc = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAAZ' +
        '0lEQVQ4y2NgGLKgquEuFxBPAGI2ahhWCsS/gDibUoO0gPgxEP8H4ttArEyuQYxAPBd' +
        'qEAxPBImTY5gjEL9DM+wTENuQahAvEO9DMwiGdwAxOymGJQLxTyD+jgWDxCMZRsEoGAVo' +
        'AADeemwtPcZI2wAAAABJRU5ErkJggg==',
        arrowDesc = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAYAAAByUDbMAAAAZUlEQVQ4y2NgGAWj' +
        'YBSggaqGu5FA/BOIv2PBIPFEUgxjB+IdQPwfC94HxLykus4GiD+hGfQOiB3J8SojEE9EM2wuSJ' +
        'zcsFMG4ttQgx4DsRalkZENxL+AuJQaMcsGxBOAmGvopk8AVz1sLZgg0bsAAAAASUVORK5CYII= ',
        arrowBoth = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABMAAAATCAQAAADYWf5HAAAAkElEQVQoz7X' +
        'QMQ5AQBCF4dWQSJxC5wwax1Cq1e7BAdxD5SL+Tq/QCM1oNiJidwox0355mXnG/DrEtIQ6azio' +
        'NZQxI0ykPhTQIwhCR+BmBYtlK7kLJYwWCcJA9M4qdrZrd8pPjZWPtOqdRQy320YSV17OatFC4eut' +
        's6z39GYMKRPCTKY9UnPQ6P+GtMRfGtPnBCiqhAeJPmkqAAAAAElFTkSuQmCC';
    
	var calculateObjectValue = function (self, name, args, defaultValue) {
        var func = name;

        if (typeof name === 'string') {
            // support obj.func1.func2
            var names = name.split('.');

            if (names.length > 1) {
                func = window;
                $.each(names, function (i, f) {
                    func = func[f];
                });
            } else {
                func = window[name];
            }
        }
        if (typeof func === 'object') {
            return func;
        }
        if (typeof func === 'function') {
            return func.apply(self, args);
        }
        if (!func && typeof name === 'string' && sprintf.apply(this, [name].concat(args))) {
            return sprintf.apply(this, [name].concat(args));
        }
        return defaultValue;
    };
	
    $.extend($.fn.bootstrapTable.defaults, {
        multiSort: false,
		sortNameArr: [],
		sortOrderArr: []
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _onSort = BootstrapTable.prototype.onSort,
        _initServer = BootstrapTable.prototype.initServer,
        _initSort = BootstrapTable.prototype.initSort;

    // init save data after initTable function
    BootstrapTable.prototype.initServer = function (silent, query) {
       var that = this,
            data = {},
            params = {
                pageSize: this.options.pageSize === this.options.formatAllRows() ?
                    this.options.totalRows : this.options.pageSize,
                pageNumber: this.options.pageNumber,
                searchText: this.searchText,
                sortName: this.options.sortName,
                sortOrder: this.options.sortOrder
            },
            request;

        if (!this.options.url && !this.options.ajax) {
            return;
        }
		
		if(this.options.multiSort) {
			if (this.options.sortNameArr.length !== this.options.sortOrderArr.length) {
				alert("多列排序，sortName，sortOrder长度不相等");
				return ;
			}
			params.sortName = this.options.sortNameArr.join(",");
			params.sortOrder = this.options.sortOrderArr.join(",");
		}
		
        if (this.options.queryParamsType === 'limit') {
            params = {
                search: params.searchText,
                sort: params.sortName,
                order: params.sortOrder
            };
            if (this.options.pagination) {
                params.limit = this.options.pageSize === this.options.formatAllRows() ?
                    this.options.totalRows : this.options.pageSize;
                params.offset = this.options.pageSize === this.options.formatAllRows() ?
                    0 : this.options.pageSize * (this.options.pageNumber - 1);
            }
        }

        if (!($.isEmptyObject(this.filterColumnsPartial))) {
            params['filter'] = JSON.stringify(this.filterColumnsPartial, null);
        }

        data = calculateObjectValue(this.options, this.options.queryParams, [params], data);

        $.extend(data, query || {});

        // false to stop request
        if (data === false) {
            return;
        }

        if (!silent) {
            this.$tableLoading.show();
        }
        request = $.extend({}, calculateObjectValue(null, this.options.ajaxOptions), {
            type: this.options.method,
            url: this.options.url,
            data: this.options.contentType === 'application/json' && this.options.method === 'post' ?
                JSON.stringify(data) : data,
            cache: this.options.cache,
            contentType: this.options.contentType,
            dataType: this.options.dataType,
            success: function (res) {
                res = calculateObjectValue(that.options, that.options.responseHandler, [res], res);

                that.load(res);
                that.trigger('load-success', res);
            },
            error: function (res) {
                that.trigger('load-error', res.status);
            },
            complete: function () {
                if (!silent) {
                    that.$tableLoading.hide();
                }
            }
        });

        if (this.options.ajax) {
            calculateObjectValue(this, this.options.ajax, [request], null);
        } else {
            $.ajax(request);
        }
    };
	
	BootstrapTable.prototype.onSort = function (event) {
        var $this = $(event.currentTarget).parent(),
            $this_ = this.$header.find('th').eq($this.index()),
            $sortName_ = $this.data("sortName")?$this.data("sortName"):$this.data("field");
           
        this.$header.add(this.$header_).find('span.order').remove();

		if(!this.options.multiSort){
			if (this.options.sortName === $sortName_) {
				this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
			} else {
				this.options.sortName = $sortName_;
				this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
			}
			this.trigger('sort', this.options.sortName, this.options.sortOrder);
			$this.add($this_).data('order', this.options.sortOrder);
		}else {
			var $isExist = false;
			for(var i = 0; i < this.options.sortNameArr.length; i++) {
				var $sortName = this.options.sortNameArr[i];
				var $sortOrder = this.options.sortOrderArr[i];
				if($sortName === $sortName_) {
					switch ($sortOrder) {
						case 'asc': 
							ArrayRemoveIndex(this.options.sortNameArr,i);
							ArrayRemoveIndex(this.options.sortOrderArr,i);
							break;
						case 'desc':
							this.options.sortOrderArr[i] = 'asc';
							break;
						case 'both':
							this.options.sortOrderArr[i] = 'desc';
							break;
					}
					$isExist = true;
					break;
				}
			}
			if(!$isExist) {
				this.options.sortNameArr.push($sortName_);
				this.options.sortOrderArr.push('desc');
			}
		}

        // Assign the correct sortable arrow
        this.getCaretHtml();
        if (this.options.sidePagination === 'server') {
            this.initServer();
            return;
        }

        this.initSort();
        this.initBody();
    };
	
	BootstrapTable.prototype.initSort = function () {
		var that = this;
		if(!this.options.multiSort) {
			var name = this.options.sortName,
				order = this.options.sortOrder === 'desc' ? -1 : 1,
				index = $.inArray(this.options.sortName, this.header.fields);

			if (index !== -1) {
				this.data.sort(function (a, b) {
					if (that.header.sortNames[index]) {
						name = that.header.sortNames[index];
					}
					var aa = a[name],
						bb = b[name],
						value = calculateObjectValue(that.header, that.header.sorters[index], [aa, bb]);

					if (value !== undefined) {
						return order * value;
					}

					// Fix #161: undefined or null string sort bug.
					if (aa === undefined || aa === null) {
						aa = '';
					}
					if (bb === undefined || bb === null) {
						bb = '';
					}

					// IF both values are numeric, do a numeric comparison
					if ($.isNumeric(aa) && $.isNumeric(bb)) {
						// Convert numerical values form string to float.
						aa = parseFloat(aa);
						bb = parseFloat(bb);
						if (aa < bb) {
							return order * -1;
						}
						return order;
					}

					if (aa === bb) {
						return 0;
					}

					// If value is not a string, convert to string
					if (typeof aa !== 'string') {
						aa = aa.toString();
					}

					if (aa.localeCompare(bb) === -1) {
						return order * -1;
					}

					return order;
				});
			}
		}else {
			var cmp = function(x, y) {
				return x > y ? 1 : x < y ? -1 : 0;
			};
			
			var arrayCmp = function(a, b) {
				var arr1 = [],
					arr2 = [];

				for (var i = 0; i < that.options.sortNameArr.length; i++) {
					var order = that.options.sortOrderArr[i] === 'desc' ? -1 : 1,
						aa = a[that.options.sortNameArr[i]],
						bb = b[that.options.sortNameArr[i]];

					if (aa === undefined || aa === null) {
						aa = '';
					}
					if (bb === undefined || bb === null) {
						bb = '';
					}
					if ($.isNumeric(aa) && $.isNumeric(bb)) {
						aa = parseFloat(aa);
						bb = parseFloat(bb);
					}
					if (typeof aa !== 'string') {
						aa = aa.toString();
					}

					arr1.push(
						order * cmp(aa, bb));
					arr2.push(
						order * cmp(bb, aa));
				}
				
				return cmp(arr1, arr2);
			};
			
			this.data.sort(function(a, b) {
				return arrayCmp(a, b);
			});
		}
    };
	
	BootstrapTable.prototype.getCaretHtml = function () {
        var that = this;	
		if(!this.options.multiSort){
			$.each(this.$header.find('th'), function (i, th) {
				var $sortName = $(th).data("sortName")?$(th).data("sortName"):$(th).data("field");
				if ($sortName === that.options.sortName) {
					$(th).find('.sortable').css('background-image', 'url(' + (that.options.sortOrder === 'desc' ? arrowDesc : arrowAsc) + ')');
				} else {
					$(th).find('.sortable').css('background-image', 'url(' + arrowBoth +')');
				}
			});
		} else {
			$.each(this.$header.find('th'), function (i, th) {
				var $exist = false;
				var $sortName = $(th).data("sortName")?$(th).data("sortName"):$(th).data("field");
				$.each(that.options.sortNameArr, function (j, sn) {
					if ($sortName === sn) {
						$(th).find('.sortable').css('background-image', 'url(' + (that.options.sortOrderArr[j] === 'desc' ? arrowDesc : arrowAsc) + ')');
						$exist = true;
						return false;
					}
				});
				if(!$exist) {
					$(th).find('.sortable').css('background-image', 'url(' + arrowBoth +')');
				}
			});
		}
    };
})(jQuery);