<html lang="en">
<head>
<title>Music shopping application</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

h1 {
	text-align: center;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function loadShoppingList() {
		$.ajax({
		    url: "ShoppingListServlet",
		    type: "get", 
		    success: function(response) {
					document.getElementById("ShoppingList").innerHTML = response;
			},
		    error: function(xhr) {
		    	  alert(xhr.responseText);
		    }
		});
	}
	
	function loadCart() {
		var username = document.getElementById("username").value;
		if(username == "") alert("Please enter your username");
		else{
			$.ajax({
			    url: "CartListServlet",
			    type: "get", 
			    data: {"username" : username},
			    success: function(response) {
						document.getElementById("Cart").innerHTML = response;
				},
			    error: function(xhr) {
			    	  alert(xhr.responseText);
			    }
			});
		}
	}
	
	function hideDescription() {
			document.getElementById("ItemDescription").innerHTML = "";
	}
	
	function hideCart() {
			document.getElementById("Cart").innerHTML = "";
	}
	
	function loadDescription(id) {
		$.ajax({
		    url: "ItemDescriptionServlet",
		    type: "get", 
		    data: {id},
		    success: function(response) {
					document.getElementById("ItemDescription").innerHTML = response;
			},
		    error: function(xhr) {
		    	  alert(xhr.responseText);
		    }
		});
	}
	
	function addToCart(id) {
		var username = document.getElementById("username").value;
		if(username == "") alert("Please enter your username");
		else{
			$.ajax({
			    url: "AddToCartServlet",
			    type: "post", 
			    data: {id,"username" : username},
			    success: function(response) {
			    	loadCart();
				},
			    error: function(xhr){
			    	// this happen when you add a duplicate item to your cart and it is a normal error message. 
			    	//TODO add better error validation
			    	alert("Item present in cart");
			    }
			});
		}
	}
</script>
</head>
<body onload="loadShoppingList()";>
	Username :
	<input type="text" id="username" value="">
	<button onclick="loadCart()" type="button">Show cart</button>
	<div id="Cart"></div>
	<div id="ItemDescription" style="margin: 5px; background: #E8E8E8;"></div>
	<h1>Shopping list</h1>
	<div id="ShoppingList"></div>
</body>
</html>
