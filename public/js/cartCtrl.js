app.controller('cartCtrl', ['$scope', '$cookieStore', '$http', '$state', 
                                function($scope, $cookieStore, $http, $state) {

	$http.get('/store/phone')
		.success(function(data, status, header, config) {
			var phoneInCart = [];
			for(var i = 0; i < data.length; i++) 
				for (j in $cookieStore.get('phone_cart')) 
					if(data[i].id === $cookieStore.get('phone_cart')[j].id) {
						data[i].quantity = $cookieStore.get('phone_cart')[j].quantity;
						phoneInCart.push(data[i]);
					}
			$scope.carts = phoneInCart;
			console.log(phoneInCart);
		})
		.error(function(data, status, header, config) {
			console.log('Error');
	});
	
	$scope.itemDetele = function(id) {
		var newCookies = $cookieStore.get('phone_cart');
		$cookieStore.remove('phone_cart');
		for(var i = 0; i < newCookies.length; i++)
			if(newCookies[i].id === id) {
				for(var j = i + 1; j < newCookies.length; j++)
					newCookies[i] = newCookies[j];
				newCookies.pop();
				break;
			}
		$cookieStore.put('phone_cart', newCookies);
		$state.go($state.current, {}, {reload: true});
	}

	$scope.totalCost = function() {
		var cost = 0;
		$scope.count = 0;
		if($scope.carts === undefined)
			return 0;
		for (var i = 0; i < $scope.carts.length; i++) {
			$scope.count += $scope.carts[i].quantity;
			cost += $scope.carts[i].price * $scope.carts[i].quantity;
		}
		return cost;
	}
	
	$scope.increPhone = function(id, count) {
		var newcookies = new Array();
		if(count > 0) {
			for (phone in $cookieStore.get('phone_cart')) {
				if($cookieStore.get('phone_cart')[phone].id != id) 
					newcookies.push($cookieStore.get('phone_cart')[phone]);
				else
					newcookies.push({id: id, quantity: count});
			}
			console.log(newcookies);
			$cookieStore.remove('phone_cart');
			$cookieStore.put('phone_cart', newcookies);
		}
	}

	$scope.submit = function() {
		$cookieStore.remove('phone_cart');
	}
}]);