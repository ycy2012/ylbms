//bill input common js
var temp;
function _deleteRow(rowID) {
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
