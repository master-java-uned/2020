$(document).on("click", "#btnlogin", function (event) 
{
	var $form = $("#formlogin");

	$.post("./login", $form.serialize(), function (response) 
	{
		if (response.status == "REDIRECT") 
		{
			window.location = response.data;
			return;
		}
		else
		{
			$(".toast-body").html(response.data);
			$("#myToast").toast({
				delay: 3000
			});
			$('#myToast').toast('show');
		}
	});
	event.preventDefault();
});