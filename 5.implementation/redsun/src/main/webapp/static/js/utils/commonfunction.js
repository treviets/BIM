//create pdf
function createPDF(fileName, canvasId){
	 html2canvas($("#"+canvasId), {
            onrendered: function(canvas) {         
                var imgData = canvas.toDataURL(
                    'image/png');              
                var doc = new jsPDF('p', 'mm', 'a4');
                doc.addImage(imgData, 'PNG', 1, 1);
                doc.save(fileName + '.pdf');
            }
        });
}

//export excel from inner body.
function createExcel(fileName, canvasId) {
	var tab_text = '<!DOCTYPE html><html><head>';
		tab_text = tab_text + '<meta http-equiv="Content-Type" content="text/html; charset=utf-8">';
		tab_text = tab_text + '</head><body>';
		tab_text = tab_text + document.getElementById(canvasId).innerHTML;
		tab_text = tab_text + '</body></html>';      
	var blob = new Blob([tab_text], {
		type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	});
	saveAs(blob, fileName + ".xls");
};

//export excel from html file.
function createExcelFromHtml(fileName, canvasId) {
	var blob = new Blob([document.getElementById(canvasId).innerHTML], {
		type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	});
	saveAs(blob, fileName + ".xls");
};
