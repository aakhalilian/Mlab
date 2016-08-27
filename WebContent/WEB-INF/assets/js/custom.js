$(document).ready(function(){
	$(document).on("click","nav", function(){
		var n={
			type	: "info",
			title 	: "My Error",
			message : "heeeeeeeeeeeha",
			timeOut : 15000
		};
		notification(n);
	});

})