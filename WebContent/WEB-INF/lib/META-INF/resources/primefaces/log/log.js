PrimeFaces.widget.Log=PrimeFaces.widget.BaseWidget.extend({init:function(b){this._super(b);this.header=this.jq.children(".ui-log-header");this.content=this.jq.children(".ui-log-content");this.itemsContainer=this.content.find(".ui-log-items");this.filters=this.header.children(".ui-log-button");this.severity="all";var a=this;this.jq.draggable({handle:this.header});this.header.mousedown(function(c){a.jq.zIndex(++PrimeFaces.zindex)});this.bindEvents();this.jq.appendTo("body");PrimeFaces.logger=this},bindEvents:function(){var a=this;this.header.children(".ui-log-button").mouseover(function(){var b=$(this);if(!b.hasClass("ui-state-active")){$(this).addClass("ui-state-hover")}}).mouseout(function(){$(this).removeClass("ui-state-hover")});this.header.children(".ui-log-clear").click(function(b){a.itemsContainer.html("");a.filters.filter(".ui-state-active").removeClass("ui-state-active");a.filters.filter(".ui-log-all").addClass("ui-state-active");a.severity="all";b.preventDefault()});this.header.children(".ui-log-all").click(function(b){a.itemsContainer.children().show();a.filters.filter(".ui-state-active").removeClass("ui-state-active");$(this).addClass("ui-state-active").removeClass("ui-state-hover");a.severity="all";b.preventDefault()});this.header.children(".ui-log-info").click(function(b){a.handleFilterClick(b,".ui-log-item-info","info",$(this))});this.header.children(".ui-log-warn").click(function(b){a.handleFilterClick(b,".ui-log-item-warn","warn",$(this))});this.header.children(".ui-log-debug").click(function(b){a.handleFilterClick(b,".ui-log-item-debug","debug",$(this))});this.header.children(".ui-log-error").click(function(b){a.handleFilterClick(b,".ui-log-item-error","error",$(this))})},info:function(a){this.add(a,"info","ui-icon-info")},warn:function(a){this.add(a,"warn","ui-icon-notice")},debug:function(a){this.add(a,"debug","ui-icon-search")},error:function(a){this.add(a,"error","ui-icon-alert")},add:function(f,a,c){var e=this.severity==a||this.severity=="all",b=e?"display:block":"display:none";var d='<li class="ui-log-item ui-log-item-'+a+' ui-helper-clearfix" style="'+b+'"><span class="ui-icon '+c+'"></span>'+new Date().toLocaleString()+" : "+f+"</li>";this.itemsContainer.append(d)},filter:function(a){this.itemsContainer.children().hide().filter(a).show()},handleFilterClick:function(c,d,a,b){this.filter(d);this.filters.filter(".ui-state-active").removeClass("ui-state-active");b.addClass("ui-state-active").removeClass("ui-state-hover");this.severity=a;c.preventDefault()}});