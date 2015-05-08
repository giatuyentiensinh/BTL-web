app.controller('productCtrl', ['$scope', '$http', '$state', '$cookieStore', 
                               function($scope, $http, $state, $cookieStore) {
	$http.get('/store/phone')
		.success(function(data, status, header, config) {
			$scope.data = data;
		})
		.error(function(data, status, header, config) {
			console.log('Error');
		});
	
	$scope.rederDetail = function(product_id) {
		$state.go('product.detail', {id: product_id});
	}
	
	$scope.addCart = function(product_id, price) {
		var check = true;
		var cart = [];
		for(phone in $cookieStore.get('phone_cart')) {
			cart.push($cookieStore.get('phone_cart')[phone]);
			if($cookieStore.get('phone_cart')[phone].id === product_id) {
				alert('San pham nay da co trong gio cua ban');
				check = false;
			}
		}
		if(check) {
			cart.push({id: product_id, quantity: 1});
			$cookieStore.put('phone_cart', cart);
			$state.go('cart.cart', {});
		}
	}
}]);