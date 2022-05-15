
//Hide the alters on page load
$(document).ready(function()
{
	$("#alertSuccess").hide();
 	$("#alertError").hide();
});


//Add an event handler for the click event of the save button
$(document).on("click", "#btnSave", function(event)
{
 	// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();


// Form validation-------------------
	var status = validateApplicationForm();
	if (status != true)
 	{
 		$("#alertError").text(status);
 		$("#alertError").show();
 		return;
 	}
// If valid------------------------
	var type = ($("#hidApplicationIDSave").val() == "") ? "POST" : "PUT";
	console.log(type);
 	$.ajax(
 	{
		url : "applicationsAPI",
		type : type,
 		data : $("#formApplication").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onApplicationSaveComplete(response.responseText, status);
 		}
 	}); 
}); 


//implement validation
function validateApplicationForm()
{

// NAME
	if ($("#name").val().trim() == "")
 	{
 		return "Insert Name.";
 	}

// NIC
	if ($("#nic").val().trim() == "")
 	{
 		return "Insert NIC.";
 	}

// ADDRESS
	if ($("#address").val().trim() == "")
 	{
		return "Insert Address.";
 	}

// PHONE
	if ($("#phone").val().trim() == "")
 	{
 		return "Insert Phone Number.";
 	}

// E-MAIL
	if ($("#email").val().trim() == "")
 	{
 		return "Insert E-mail.";
 	}
// AREA
	if ($("#area").val().trim() == "")
 	{
 		return "Insert Area.";
 	}
// SERVICE CENTER
	if ($("#service_center").val().trim() == "")
 	{
		return "Insert Service Center.";
 	}
// SOLAR PANEL
	if ($("#solar_panel").val().trim() == "")
 	{
 		return "What Solar Panel do you want?.";
 	}
	return true;
}


//Update
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidApplicationIDSave").val($(this).data("applicationid"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#nic").val($(this).closest("tr").find('td:eq(1)').text());
 $("#address").val($(this).closest("tr").find('td:eq(2)').text());
 $("#phone").val($(this).closest("tr").find('td:eq(3)').text());
 $("#email").val($(this).closest("tr").find('td:eq(0)').text());
 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
 $("#service_center").val($(this).closest("tr").find('td:eq(2)').text());
 $("#solar_panel").val($(this).closest("tr").find('td:eq(3)').text());
});


function onApplicationSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully Saved.");
 			$("#alertSuccess").show();
			$("#divApplicationGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}

 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while Saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while Saving..");
 		$("#alertError").show();
 	}
	$("#hidApplicationIDSave").val("");
 	$("#formApplication")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
{
	var id = $(this).data("applicationid");
	console.log("id is :"+id)
	
	 $.ajax(
	 {
		 url : "applicationsAPI",
 		 type : "DELETE",
 		 data : "applicationID=" + id,
 		 dataType : "text",
  		 complete : function(response, status)
 		 {
 				onApplicationDeleteComplete(response.responseText, status);
 		 }
 	 });
});


function onApplicationDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully Deleted.");
 			$("#alertSuccess").show();
 			$("#divApplicationGrid").html(resultSet.data);
 		} else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while Deleting.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while Deleting..");
		$("#alertError").show();
 	}
	$("#hidApplicationIDSave").val(""); 
	$("#formApplication")[0].reset(); 
}