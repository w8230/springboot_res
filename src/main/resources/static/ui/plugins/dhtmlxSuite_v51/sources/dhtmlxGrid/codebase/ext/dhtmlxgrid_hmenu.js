/*
Product Name: dhtmlxSuite 
Version: 5.1.0 
Edition: Professional 
License: content of this file is covered by DHTMLX Commercial or Enterprise license. Usage without proper license is prohibited. To obtain it contact sales@dhtmlx.com
Copyright UAB Dinamenta http://www.dhtmlx.com
*/

/**
*	@desc: enable pop up menu which allows hidding/showing columns
*	@edition: Professional
*	@type: public
*/
dhtmlXGridObject.prototype.enableHeaderMenu=function(columns)
{
	if (!window.dhtmlXMenuObject)
		return dhtmlx.message("You need to include DHTMLX Menu");

	if (!this._header_menu){
		var menu = this._header_menu = new dhtmlXMenuObject();
		menu.renderAsContextMenu();

		var that=this;
		menu.attachEvent("onBeforeContextMenu", function(){
			that._showHContext(columns);
			return true;
		});
		menu.attachEvent("onClick", function(id){
			var checked = this.getCheckboxState(id);

			var row = that.hdr.rows[1];
			for (var j=0; j<row.cells.length; j++){
				var c = row.cells[j];
				if (c._cellIndexS == id){
					var len = c.colSpan || 1;
					for (var i=0; i<len; i++)
						that.setColumnHidden(id*1+i,!checked);
				}
			}
		});

		this.attachEvent("onInit",function(){
			menu.addContextZone(this.hdr);
		});
		if (this.hdr.rows.length) this.callEvent("onInit",[]);
	}
};

dhtmlXGridObject.prototype.getHeaderMenu=function(columns)
{
	return this._header_menu;
};

dhtmlXGridObject.prototype._hideHContext=function(){
	if (this._header_menu)
		this._header_menu.hide();
};

dhtmlXGridObject.prototype._showHContext=function(columns)
{
	if (typeof columns == "string")
		columns = columns.split(this.delim);
	
	var true_ind = 0;
	var j = 0;
	this._header_menu.clearAll();

	for (var i=0; i<this.hdr.rows[1].cells.length; i++){
		var c = this.hdr.rows[1].cells[i];
		if (!columns || (columns[true_ind] &&  columns[true_ind] != "false")){
			if (c.firstChild && c.firstChild.tagName=="DIV") var val=c.firstChild.innerHTML;
			else var val = c.innerHTML;
			val = val.replace(/<[^>]*>/gi,"");
			var visible = !(this.isColumnHidden(true_ind) || (this.getColWidth(true_ind)==0));
			this._header_menu.addCheckbox("child", this._header_menu.topId, j, true_ind, val, visible);
			j++;
		}
		true_ind+=(c.colSpan||1);
	}	
}
//(c)dhtmlx ltd. www.dhtmlx.com

