$(function(){
	$(".cancelButton").hide();
	$(".thumbnailupdatebtn").hide();
})


var originalPrice;
var originalName;
var originalQuantity;
var category_;
function editSelectedItem(category, currentObj){
	performOnEditBtnSelected(category, currentObj);
}

function performOnEditBtnSelected(category, currentObj){
	category_ = category;
	$(currentObj).parent().siblings(".cancelButton").show();
	$(currentObj).parent().siblings().find(".thumbnailupdatebtn").show();
	var priceSelector = $(currentObj).parent().siblings(".price");
	var priceValue = originalPrice = $(priceSelector).contents().filter(function() {
	    return this.nodeType == 3;
	}).text();
	var quantitySelector = $(currentObj).parent().siblings(".quantity");
	var quantityValue = originalQuantity =  $(quantitySelector).html();
	var typeSelector = $(currentObj).parent().siblings(".type");
	var typeValue = originalName = $(typeSelector).html();
	$(priceSelector).html("<input type='text' id='newPrice' class='form-control col-md-2' value='"+priceValue+"'name='price'>");
	$(quantitySelector).html("<input type='text' id='newQuantity' class='form-control col-md-2' value='"+quantityValue+"' name='quantity'>");
	$(typeSelector).html("<input type='text' id='newType' class='form-control col-md-2' value='"+typeValue+"' name='name'>");
	var updateBtn = "<input type='submit' value='Update' class='btn btn-success' id='update_btn' onclick=submitForm(this)>";
	$(currentObj).parent().html(updateBtn);
	$("input:text").focus(function(){
		$(this).select();
	});
}
function deleteSelectedItem(category, currentObj){
	var selectedItemName = $(currentObj).parent().siblings(".type").html();
	var sendData = {
			categoryName : selectedItemName,
			category :category 
		}	
	$.ajax({
		url : '/GroceryApp/deleteItem',
		type : 'POST',
		data : JSON.stringify(sendData),
		contentType : 'application/json',
		dataType : 'text',
		success : function(returndata) {
			$(currentObj).parents(".wrapper").hide();
		},
		error : function(xhRequest, ErrorText, thrownError) {
			console.log("xhRequest:", xhRequest, "\n", 'ErrorText: ',
					ErrorText, "\n", 'thrownError: ', thrownError, "\n");
			}
		});
}


function submitForm(currentObj){
	$("#itemForm").submit(function(e){
		e.preventDefault();
		var formData = new FormData($("#itemForm")[0]);
		formData.append("availableQuantity", $("#newQuantity").val());
		formData.append("price", $("#newPrice").val());
		formData.append("originalName", originalName);
		formData.append("category", category_);
		$.ajax({
			url: '/GroceryApp/updateItemDetails',
		    type: 'POST',
		    data: formData,
		    cache: false,
		    contentType: false,   
		    processData: false, 
		    success: function (item) {
		    	$(currentObj).parent().siblings().children(".thumbnailupdatebtn").hide();
		    	$(currentObj).parent().siblings(".cancelButton").hide();
		    	$(currentObj).parent().siblings(".price").html(item.price);
		    	$(currentObj).parent().siblings(".quantity").html(item.availableQuantity);
		    	$(currentObj).parent().siblings(".type").html(item.name);
		    	$(currentObj).parent().siblings(".image").children("img").attr("src", '');
		    	$(currentObj).parent().siblings(".image").children("img").attr("src", item.thumbnail);
		    	var editBtn = "<input type='button' value='Edit' class='btn btn-info edit_btn' onclick = editSelectedItem(category_,this);>";
		    	$(currentObj).parent().html(editBtn);
		    }
		});
	});
}

function cancelSelectedAction(currentObj){
	var priceSelector = $(currentObj).parent().siblings(".price");
	var priceValue = $(priceSelector).html("<span id='currency'>$</span>"+originalPrice);
	var quantitySelector = $(currentObj).parent().siblings(".quantity");
	var quantityValue = $(quantitySelector).html(originalQuantity);
	var typeSelector = $(currentObj).parent().siblings(".type");
	var typeValue = $(typeSelector).html(originalName);
	var editBtn = "<input type='button' value='Edit' class='btn btn-info edit_btn' onclick = editSelectedItem(category_,this);>";
	$(currentObj).parent().siblings(".editButton").html(editBtn);
	$(currentObj).parent().siblings().find(".thumbnailupdatebtn").hide();
	$(currentObj).parent().hide();
	}