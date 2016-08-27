var counter=0;
var n={
		type	: "info",
		title 	: "My Error",
		message : "heeeeeeeeeeeha",
		timeOut : 15000
	};
	var l={
			type	: "danger",
			title 	: "My Error",
			message : "heeeeeeeeeeeha",
			timeOut : 15000
		};
	var k={
			type	: "warning",
			title 	: "My Error",
			message : "heeeeeeeeeeeha",
			timeOut : 15000
		};
	var i={
			type	: "primary",
			title 	: "My Error",
			message : "heeeeeeeeeeeha",
			timeOut : 15000
		};
	var m={
			title 	: "My Error",
			message : "heeeeeeeeeeeha",
		};
	
$(document).ready(function(){
	$(document).on("click","nav", function(){
		
		switch(counter){
		case 0:
			notification(n);
			break;
		case 1:
			notification(l);
			break;
		case 2:
			notification(k);
			break;
		case 3:
			notification(i);
			break;
		case 4:
			notification(m);
			break;
		}
		counter++;
		counter=counter%5;
	});

})