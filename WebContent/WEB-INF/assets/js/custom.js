$(document).ready(function(){
	$(document).on("click","button#send-button", function(e){
		e.preventDefault();
		var button=$(this);
		button.prop("disabled",true);
		$.post("",
		        {
			email: relationTitle,
			subject: relationDesc,
			body: relationPhrase
		        },
		        function(status){
		            if(status=="success"){
		            	$('form#contact-form').trigger("reset");
		            	throwSuccess("Message Has been Sent!","<p>We will send you an email to confirm that we recieved your message!</p>");
		            	button.prop("disabled",true);
		            }
		            else if(status=="error"){
		            	throwError('Something is Wrong!!!','<p>Failed to send the Message!</p>');
		            	button.prop("disabled",true);
		            }
		            else
		            	console.log(status);
		});
	});
})
function throwError(title,messag){
	var n={
		type	: "danger",
		title 	: title,
		message : messag,
	};
	notification(n);	
}
function throwSuccess(title,messag){
	var n={
		type	: "success",
		title 	: title,
		message : messag,
	};
	notification(n);	
}