$(function(){
	$(".successMsg").hide();
})
function addToWishList(name, price, category, currentObj) {
	var item = {
		categoryName : name,
		category : category,
		price : price
	}
	$.ajax({
		url : '/GroceryApp/addToWishList',
		type : 'POST',
		contentType : "application/json",
		data : JSON.stringify(item),
		cache : false,
		success : function(returndata) {
			var messageElement = $(currentObj).parent().siblings(".message");
			if (returndata == true) {
				$(messageElement).html("Item successfully added in WishList.");
			}
			else{
				$(messageElement).html("Item already exists in the WishList.");
			}
			$($(messageElement)).fadeIn(500, function(){
				$(this).fadeOut(6000, function(){$(this).css({"color":"red"})})
			});
			$(currentObj).parent().css({"background-color":"red"});
		}
	});
}

function removeItemFromWishList(name, category, currentObj){
	var item = {
		categoryName : name,
		category : category
	}
	$.ajax({
		url : '/GroceryApp/removeFromWishList',
		type : 'POST',
		contentType : "application/json",
		data : JSON.stringify(item),
		cache : false,
		success : function(returndata) {
			if (returndata === 'success') {
				$(".message").html("Selected item removed from WishList");
				$(currentObj).parent().parents(".wishListWrapper").hide();
			}
			$(".message").fadeIn(500, function(){
				$(this).fadeOut(6000, function(){$(this).css({"color":"red"})})
			});
		}
	});
}

$(function(){
	$(".icon .wishListIcon_taken").click(function(){
		$(this).parent().siblings(".message").html("Item already exists in the WishList.").fadeOut(6000);
	})
});
