app.controller('cartCtrl', ['$scope', '$cookieStore', '$state', 'PhoneService',
                	function($scope, $cookieStore, $state, PhoneService) {

    $scope.check = true;
    $scope.checkError = true;

	$scope.loadItem = function() {
		// load data item in cookies
		PhoneService.readCart()
			.success(function(data, status, headers, config) {
				var cookies = document.cookie.split('; ');
				var phoneInCart = [];
				for(var i = 0; i < data.length; i++)
					for(var j = 0; j < cookies.length; j++) {
						// field = ['cart_55438e16505f66d57fcf4cd8', '1']
						var field = cookies[j].split('=');
						// console.log(field);
						// console.log(data[i].id);
						if(field[0] === 'cart_' + data[i].id) {
							data[i].quantity = parseInt(field[1]);
							phoneInCart.push(data[i]);
							// console.log(data[i]);
							break;
						}
					}
				
				$scope.carts = phoneInCart;
				// check reload
				if($cookieStore.get('reload')) {
					$state.go($state.current, {}, {reload: true});
					$cookieStore.remove('reload');
				}

				// console.log('End');
				// console.log(phoneInCart);
			})
			.error(function(data, status, headers, config) {
				console.log("error");
				console.log(data);
			});
	}


	$scope.itemDetele = function(id) {
		$cookieStore.remove('cart_' + id);
		$state.go($state.current, {}, {reload: true});
	}

	$scope.totalCost = function() {
		var cost = 0;
		$scope.count = 0;
		if($scope.carts === undefined)
			return 0;
		for (var i = 0; i < $scope.carts.length; i++) {
			if($scope.carts[i].quantity === undefined) 
				$scope.isCollapsed = true;
			$scope.count += $scope.carts[i].quantity;
			cost += $scope.carts[i].price * $scope.carts[i].quantity;
		}
		return cost;
	}
	
	// update quantity Phone
	$scope.increPhone = function(id, count) {
		var newcookies = new Array();
		if(count > 0) {
			$cookieStore.remove('cart_' + id);
			$cookieStore.put('cart_' + id, count);
		}
	}

	$scope.closeAlert = function() {
		$scope.check = true;
		$scope.checkError = true;
		var cookies = document.cookie.split('; ');
		for (var i in cookies) {
			$cookieStore.remove(cookies[i].split("=")[0]);

		}
		// console.log(document.cookie);
		$state.go('main.home');
	}

	$scope.submit = function() {

		var infoUser = {
			"username": $scope.username,
			"phonenumber": $scope.phonenumber,
			"email": $scope.email,
			"address": $scope.address
		};

		console.log(infoUser);

		var items = [];

		var cookies = document.cookie.split('; ');
		for (var i = 0; i < cookies.length; i++) {
			if('cart' === cookies[i].substring(0, 4)) {
				var cookie = cookies[i].split("=");

				var quantity = cookie[1];
				var id = cookie[0].split('_');
				// console.log({"id": id[1], "quantity": quantity});
				items.push({"id": id[1], "quantity": quantity});
				$cookieStore.remove(cookie[0].substr(1))
			}
		}
		console.log(items);
		PhoneService.buy(infoUser, items)
			.success(function(data, status, headers, config) {
				console.log("Success");
				console.log(data);
				$scope.check = false;
			})
			.error(function(data, status, headers, config) {
				console.log("Error");
				console.log(data);
				$scope.check = false;
			})
	}
}]);