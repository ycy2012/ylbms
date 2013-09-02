//bill input common js
var temp;
var j=0;
function _deleteRow(rowID) {
	j -= 1;
	var otable = document.getElementById("mxTable");
	var i = rowID.parentNode.parentNode.rowIndex;
	otable.deleteRow(parseInt(i));
}
function getMids() {
	var mids = "";
	$("#mxTable").find("input[id=mid]").each(function() {
		mids += $(this).val() + ",";
	});
	return mids;
}
function setURL(dd) {
	temp = dd;
}
function getURL() {
	return temp;
}
